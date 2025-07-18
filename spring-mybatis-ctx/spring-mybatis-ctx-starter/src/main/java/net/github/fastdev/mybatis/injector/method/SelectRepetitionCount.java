package net.github.fastdev.mybatis.injector.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import net.github.fastdev.mybatis.annotation.RepetitionColumn;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class SelectRepetitionCount extends AbstractMethod {

    private final static  String SQL = "<script>SELECT COUNT(%s) AS total FROM %s WHERE  %s  %S" +
            " <if test=\"et.%s!=null\">\n" +
            "           AND  %s.%s != #{et.%s}\n" +
            "         </if>" +
            "</script>";

    private final static String SQL_NAME="selectRepetitionCount";

    public SelectRepetitionCount() {
        this(SQL_NAME);
    }

    public SelectRepetitionCount(String name) {
        super(name);
    }

    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql =script(tableInfo);
        SqlSource sqlSource = super.createSqlSource(this.configuration, sql, modelClass);
        return this.addSelectMappedStatementForOther(mapperClass, this.methodName, sqlSource, Long.class);
    }


    public String script(TableInfo tableInfo){
        List<TableFieldInfo> repetition = tableInfo.getFieldList().stream().filter(f -> f.getField().getAnnotation(RepetitionColumn.class) != null).collect(Collectors.toList());
        String tableName=tableInfo.getTableName();


       return String.format(SQL, StringPool.ASTERISK,tableName,
                getRepetitionSqlWhere(tableName,repetition),
                tableInfo.getLogicDeleteSql(true, true),
                tableInfo.getKeyProperty(),
                tableName,tableInfo.getKeyColumn(),tableInfo.getKeyProperty());
    }

    public String getRepetitionSqlWhere( final String prefix,List<TableFieldInfo> fieldList) {

        return fieldList.stream()
                .map(i -> String.format(i.getCondition(), prefix+StringPool.DOT+i.getColumn(), Constants.ENTITY+StringPool.DOT+i.getProperty())).collect(joining(" AND "));
    }
}