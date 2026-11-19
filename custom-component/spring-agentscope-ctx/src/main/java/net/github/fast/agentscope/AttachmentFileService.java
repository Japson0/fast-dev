package net.github.fast.agentscope;

import java.util.Map;

/** Business integration point for parsing an uploaded text attachment. */
public interface AttachmentFileService {
    ParsedAttachment parse(String agentId, String objectKey, UserContext owner);

    record ParsedAttachment(boolean result, String code, String message, String fileId, String originalFileName,
                            String mediaType, String content, Map<String, String> metadata, boolean truncated) {
        public ParsedAttachment {
            metadata = metadata == null ? Map.of() : Map.copyOf(metadata);
        }
    }
}
