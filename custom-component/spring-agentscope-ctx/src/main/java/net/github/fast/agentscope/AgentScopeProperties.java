package net.github.fast.agentscope;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fast.agentscope")
public class AgentScopeProperties {
    private String modelBaseUrl = "${AGI_CHAT_URL:${agi-chat.url:}}";
    private String modelApiKey;
    private String userIdHeader = "X-User-Id";
    private String tenantIdHeader = "X-Tenant-Id";
    private String visionModelName = "agi:vl-llm-latest";
    public String getModelBaseUrl() { return modelBaseUrl; }
    public void setModelBaseUrl(String modelBaseUrl) {
        if (modelBaseUrl != null && modelBaseUrl.startsWith("${") && modelBaseUrl.endsWith("}")) return;
        this.modelBaseUrl = modelBaseUrl;
    }
    public String getModelApiKey() { return modelApiKey; }
    public void setModelApiKey(String modelApiKey) { this.modelApiKey = modelApiKey; }
    public String getUserIdHeader() { return userIdHeader; }
    public void setUserIdHeader(String userIdHeader) { this.userIdHeader = userIdHeader; }
    public String getTenantIdHeader() { return tenantIdHeader; }
    public void setTenantIdHeader(String tenantIdHeader) { this.tenantIdHeader = tenantIdHeader; }
    public String getVisionModelName() { return visionModelName; }
    public void setVisionModelName(String visionModelName) { this.visionModelName = visionModelName; }
}
