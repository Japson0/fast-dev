package net.github.fast.agentscope;

import io.agentscope.core.agent.RuntimeContext;
import io.agentscope.core.message.ImageBlock;
import io.agentscope.core.message.Msg;
import io.agentscope.core.message.TextBlock;
import io.agentscope.core.message.ToolResultBlock;
import io.agentscope.core.message.URLSource;
import io.agentscope.core.message.UserMessage;
import io.agentscope.core.model.GenerateOptions;
import io.agentscope.core.model.Model;
import io.agentscope.core.model.ModelRegistry;
import io.agentscope.core.tool.Tool;
import io.agentscope.core.tool.ToolParam;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/** Reads an uploaded image with a dedicated vision model under the verified session identity. */
public final class ReadImageAttachmentTool {
    private static final Duration TIMEOUT = Duration.ofSeconds(120);
    private static final String PROMPT = "请识别这张图片的内容，完整转写文字并说明图表或场景，只输出识别结果。";
    private static final Map<String, String> MIME = Map.of("png", "image/png", "jpg", "image/jpeg",
            "jpeg", "image/jpeg", "gif", "image/gif", "webp", "image/webp", "bmp", "image/bmp");
    private final String agentId;
    private final Model visionModel;

    public ReadImageAttachmentTool(String agentId, UserContext owner, String modelName) {
        this.agentId = agentId;
        RuntimeContext session = RuntimeContext.builder().userId(owner.tenantKey() + "_" + owner.userId())
                .put(UserContext.class, owner).build();
        this.visionModel = AgiRelayModel.forSession(ModelRegistry.resolve(modelName), session);
    }

    @Tool(name = "read_image_attachment", description = "使用视觉模型识别用户上传图片，返回文字转写、图表说明或场景描述。objectKey 与 fileUrl 必须原样传入。", readOnly = true)
    public ToolResultBlock readImageAttachment(
            @ToolParam(name = "objectKey", description = "当前智能体目录下的图片对象 key") String objectKey,
            @ToolParam(name = "fileUrl", description = "可供视觉模型访问的 HTTP(S) 图片地址") String fileUrl) {
        var check = AttachmentToolSupport.check(agentId, objectKey);
        if (check.failure() != null) return check.failure();
        if (fileUrl == null || !(fileUrl.startsWith("https://") || fileUrl.startsWith("http://")))
            return AttachmentToolSupport.response(false, AttachmentToolSupport.FAILED, null,
                    "图片附件必须提供 HTTP/HTTPS fileUrl");
        try {
            String fileName = objectKey.substring(objectKey.lastIndexOf('/') + 1);
            String extension = extension(fileName);
            String mimeType = MIME.getOrDefault(extension, "image/png");
            List<Msg> messages = List.of(new UserMessage(TextBlock.builder().text(PROMPT).build(),
                    new ImageBlock(new URLSource(fileUrl, mimeType))));
            String text = visionModel.stream(messages, List.of(), GenerateOptions.builder().build())
                    .concatMapIterable(response -> response.getContent() == null ? List.of() : response.getContent())
                    .ofType(TextBlock.class).map(TextBlock::getText).collect(Collectors.joining()).block(TIMEOUT);
            if (text == null || text.isBlank())
                return AttachmentToolSupport.response(false, AttachmentToolSupport.SYSTEM_ERROR, null,
                        "视觉模型未返回图片识别结果");
            return AttachmentToolSupport.response(true, AttachmentToolSupport.SUCCEED,
                    Map.of("fileId", objectKey, "originalFileName", fileName, "mediaType", mimeType,
                            "content", text, "metadata", Map.of(), "truncated", false), null);
        } catch (Exception failure) {
            return AttachmentToolSupport.response(false, AttachmentToolSupport.SYSTEM_ERROR, null,
                    "图片识别失败：" + abbreviate(failure.getMessage()));
        }
    }

    private static String extension(String name) {
        int dot = name.lastIndexOf('.');
        return dot < 0 || dot == name.length() - 1 ? "" : name.substring(dot + 1).toLowerCase(Locale.ROOT);
    }

    private static String abbreviate(String message) {
        if (message == null || message.isBlank()) return "无详细信息";
        String text = message.strip();
        return text.length() <= 200 ? text : text.substring(0, 200);
    }
}
