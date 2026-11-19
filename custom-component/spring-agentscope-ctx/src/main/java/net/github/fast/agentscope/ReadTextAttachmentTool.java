package net.github.fast.agentscope;

import io.agentscope.core.message.ToolResultBlock;
import io.agentscope.core.tool.Tool;
import io.agentscope.core.tool.ToolParam;
import java.util.Map;

/** Delegates uploaded text and document parsing to the host application's file service. */
public final class ReadTextAttachmentTool {
    private final String agentId;
    private final UserContext owner;
    private final AttachmentFileService files;

    public ReadTextAttachmentTool(String agentId, UserContext owner, AttachmentFileService files) {
        this.agentId = agentId;
        this.owner = owner;
        this.files = files;
    }

    @Tool(name = "read_text_attachment", description = "读取用户上传的文档、表格、PDF 或文本附件并返回解析内容。objectKey 必须原样使用。", readOnly = true)
    public ToolResultBlock readTextAttachment(
            @ToolParam(name = "objectKey", description = "附件 object key，必须使用用户消息 document metadata 中的原值")
            String objectKey) {
        var check = AttachmentToolSupport.check(agentId, objectKey);
        if (check.failure() != null) return check.failure();
        if (owner == null)
            return AttachmentToolSupport.response(false, AttachmentToolSupport.FAILED, null, "缺少可信用户身份");
        try {
            AttachmentFileService.ParsedAttachment parsed = files.parse(agentId, objectKey, owner);
            if (parsed == null)
                return AttachmentToolSupport.response(false, AttachmentToolSupport.SYSTEM_ERROR, null,
                        "文件解析服务未返回结果");
            String fileName = parsed.originalFileName() == null || parsed.originalFileName().isBlank()
                    ? objectKey.substring(objectKey.lastIndexOf('/') + 1) : parsed.originalFileName();
            var data = Map.of("fileId", value(parsed.fileId()), "originalFileName", fileName,
                    "mediaType", value(parsed.mediaType()), "content", value(parsed.content()),
                    "metadata", parsed.metadata(), "truncated", parsed.truncated());
            return AttachmentToolSupport.response(parsed.result(), parsed.code(), data, parsed.message());
        } catch (Exception failure) {
            return AttachmentToolSupport.response(false, AttachmentToolSupport.SYSTEM_ERROR, null, "附件解析失败");
        }
    }

    private static String value(String value) { return value == null ? "" : value; }
}
