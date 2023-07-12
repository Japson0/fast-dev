package net.evecom.fastdev.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.service.Contact;

/**
 * <P><B>Swagger配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年07月22日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@ConfigurationProperties(prefix = "evecom.swagger")
public class SwaggerProperties {

    /**
     * 是否开启
     */
    private boolean enable = true;

    /**
     * 标题
     */
    private String title = "swagger文档";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 描述
     */
    private String description = "交接文档";

    /**
     * 指定AIP路径，如果为空则为整个项目API
     */
    private String basePack;

    /**
     * 文档作者
     */
    private Contact contact;

    /**
     * host路径.
     */
    private String host;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBasePack() {
        return basePack;
    }

    public void setBasePack(String basePack) {
        this.basePack = basePack;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

}
