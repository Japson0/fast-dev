package net.github.fastdev.common.utils;

/**
 * <P><B>缓存key常量:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年08月17日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class KeyCacheUtils {

    private static final String USER_KEY = "USER_INFO_KEY:v3:%d";

    public static String userKey(Long userId){
        return String.format(USER_KEY, userId);
    }
}
