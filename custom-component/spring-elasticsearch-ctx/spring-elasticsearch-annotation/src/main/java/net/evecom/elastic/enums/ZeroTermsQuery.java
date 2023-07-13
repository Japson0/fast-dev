package net.evecom.elastic.enums;

/**
 * <P><B>ZeroTermsQuery:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年01月04日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public enum ZeroTermsQuery {

    ALL(),

    NONE();

    boolean isNull() {
        return this == NONE;
    }
}
