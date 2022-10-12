package net.evecom.custom.hdfs.utils;

import net.evecom.custom.hdfs.ParquetDTO;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroup;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.example.ExampleParquetWriter;
import org.apache.parquet.hadoop.example.GroupReadSupport;
import org.apache.parquet.hadoop.example.GroupWriteSupport;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Type;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <P><B>parquet工具:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年07月22日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ParquetUtils {


    public static ParquetDTO read2Parquet(Path path) throws IOException {
        ParquetDTO parquetDTO = new ParquetDTO();
        List<Map<String, Object>> ans = read(path, f -> {
            HashMap<String, Object> values = new HashMap<>(f.getType().getFieldCount());
            List<Type> fields = f.getType().getFields();
            for (int i = 0; i < fields.size(); i++) {
                if (f.getFieldRepetitionCount(i) > 0) {
                    values.put(fields.get(i).getName(), getValue(f, i,
                            fields.get(i).asPrimitiveType().getPrimitiveTypeName()));
                }
            }
            return values;
        });
        parquetDTO.setDatas(ans);
        return parquetDTO;
    }

    public static List<Group> read2Group(Path path) throws IOException {

        return read(path, f -> f);
    }

    public static <T> List<T> read(Path path, Function<Group, T> accept) throws IOException {
        ParquetReader.Builder<Group> builder = ParquetReader.builder(new GroupReadSupport(), path);
        SimpleGroup group;
        List<T> ans = new ArrayList<>();
        try (ParquetReader<Group> reader = builder.build()) {
            while ((group = (SimpleGroup) reader.read()) != null) {
                T object = accept.apply(group);
                ans.add(object);
            }
        }
        return ans;
    }


    public static void write(Path path, MessageType messageType, ParquetDTO parquetDTO, Configuration configuration) throws IOException {
        write(path, messageType, c -> {
            List<Map<String, Object>> datas = parquetDTO.getDatas();
            List<Type> fields = messageType.getFields();
            SimpleGroupFactory simpleGroupFactory = new SimpleGroupFactory(messageType);
            Group group;
            int fieldSize = fields.size();
            for (Map<String, Object> data : datas) {
                for (int i = 0; i < fieldSize; i++) {
                    group = simpleGroupFactory.newGroup();
                    if (data.containsKey(fields.get(i).getName())) {
                        setValue(group, i, fields.get(i).asPrimitiveType().getPrimitiveTypeName(),
                                data.get(fields.get(i).getName()));
                    }
                    try {
                        c.write(group);
                    } catch (IOException e) {
                        continue;
                    }
                }
            }
        }, configuration);
    }

    public static void write(Path path, List<Group> groups, Configuration configuration) throws IOException {
        Map<Type, List<Group>> groupMaps = groups.stream().collect(Collectors.groupingBy(Group::getType));
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumIntegerDigits(4);
        nf.setMinimumIntegerDigits(4);
        int index = 0;
        for (Map.Entry<Type, List<Group>> groupsMap : groupMaps.entrySet()) {
            String fileName = "part-%s-%s.snappy.parquet";
            Path tmp = new Path(path, new Path(String.format(fileName, nf.format(index++), UUID.randomUUID())));
            write(tmp, (MessageType) groupsMap.getKey(), c -> {
                try {
                    for (Group g : groupsMap.getValue()) {
                        c.write(g);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, configuration);
        }
    }


    public static void write(Path path, MessageType messageType, Consumer<ParquetWriter<Group>> consumer, Configuration configuration)
            throws IOException {
        GroupWriteSupport.setSchema(messageType, configuration);
        ExampleParquetWriter.Builder builder = ExampleParquetWriter.builder(path);
        builder.withWriteMode(ParquetFileWriter.Mode.OVERWRITE)
                .withCompressionCodec(CompressionCodecName.SNAPPY)
                .withType(messageType);
        try (ParquetWriter<Group> parquetWriter = builder.build()) {
            consumer.accept(parquetWriter);
        }

    }


    private static Object getValue(Group group, int index, PrimitiveType.PrimitiveTypeName primitiveTypeName) {
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

    private static void setValue(Group group, int index, PrimitiveType.PrimitiveTypeName primitiveTypeName,
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
