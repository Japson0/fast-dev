package net.evecom.custom.hdfs.utils;

import org.apache.parquet.example.data.Group;
import org.apache.parquet.schema.PrimitiveType;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年10月12日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ParquetConvert {

    public static Object getValue(Group group, int index, PrimitiveType.PrimitiveTypeName primitiveTypeName) {
        switch (primitiveTypeName) {
            case INT32:
                return group.getInteger(index, 0);
            case INT64:
                return group.getLong(index, 0);
            case INT96:
                return group.getInt96(index, 0);
            case DOUBLE:
                return group.getDouble(index, 0);
            case FLOAT:
                return group.getFloat(index, 0);
            case BOOLEAN:
                return group.getBoolean(index, 0);
            default:
                return group.getValueToString(index, 0);
        }
    }

    public static void setValue(Group group, int index, PrimitiveType.PrimitiveTypeName primitiveTypeName,
                                Object value) {
        switch (primitiveTypeName) {
            case INT32:
                group.add(index, (int) value);
            case INT64:
                group.add(index, (long) value);
            case DOUBLE:
                group.add(index, (double) value);
            case FLOAT:
                group.add(index, (float) value);
            case BOOLEAN:
                group.add(index, (boolean) value);
            default:
                group.add(index, value.toString());
        }
    }
}
