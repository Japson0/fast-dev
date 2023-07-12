package net.evecom.custom.hdfs;

/**
 * <P><B>认证模式:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年10月12日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public enum AuthMode {

    /**
     * 无认证
     */
    None("null"),
    /**
     * kerberos
     */
    Kerberos("Kerberos");

    /**
     * 认证类型
     */
    private final String name;

    AuthMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
