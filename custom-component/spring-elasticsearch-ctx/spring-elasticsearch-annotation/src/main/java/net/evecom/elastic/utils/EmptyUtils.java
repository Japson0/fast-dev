package net.evecom.elastic.utils;

/**
 * <P><B>空数据工具类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年01月04日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class EmptyUtils {

    public static boolean isSlop(int slop) {
        return slop < 0;
    }

    public static boolean isMaxExpansions(int maxExpansions) {
        return maxExpansions < 0;
    }


    public static boolean isNull(int value) {
        return value < 0;
    }

    public static boolean isNull(String value) {
        return "".equals(value);
    }

}
