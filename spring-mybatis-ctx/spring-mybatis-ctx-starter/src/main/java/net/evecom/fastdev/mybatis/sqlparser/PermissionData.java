/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.mybatis.sqlparser;

import net.evecom.fastdev.mybatis.annotation.Operator;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <P><B>数据权限值:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年07月06日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class PermissionData {

    private String column;

    private Operator operator;

    private Expression value;

    private ExpressionList values;

    public static PermissionData buildLong(String column, Operator operator, Long value) {
        checkSingle(column, operator, value);
        return new PermissionData(column, operator, new LongValue(value));
    }

    public static PermissionData buildDouble(String column, Operator operator, Double value) {
        checkSingle(column, operator, value);
        return new PermissionData(column, operator, new DoubleValue(String.valueOf(value)));
    }

    public static PermissionData buildDate(String column, Operator operator, Date value) {
        checkSingle(column, operator, value);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" yyyy-MM-dd ");
        return new PermissionData(column, operator, new DateValue(simpleDateFormat.format(value)));
    }

    public static PermissionData buildString(String column, Operator operator, String value) {
        checkSingle(column, operator, value);
        return new PermissionData(column, operator, new StringValue(value));
    }

    public static PermissionData buildStringList(String column, List<String> value, boolean in) {
        checkList(column, value);
        List<Expression> expresses = value.stream().map(StringValue::new).collect(Collectors.toList());
        return new PermissionData(column, in ? Operator.IN : Operator.NOT_IN, new ExpressionList(expresses));
    }


    public static PermissionData buildLongList(String column, List<Long> value, boolean in) {
        checkList(column, value);
        List<Expression> expresses = value.stream().map(LongValue::new).collect(Collectors.toList());
        return new PermissionData(column, in ? Operator.IN : Operator.NOT_IN, new ExpressionList(expresses));
    }

    private PermissionData(String column, Operator Operator, Expression expression) {
        this.column = column;
        this.operator = Operator;
        this.value = expression;
    }

    private PermissionData(String column, Operator operator, ExpressionList values) {
        this.column = column;
        this.operator = operator;
        this.values = values;
    }

    private static void checkSingle(String column, Operator operator, Object value) {
        Assert.isTrue(operator != Operator.IN && operator != Operator.NOT_IN, "单值禁止使用In 或 not In 操作符");
        Assert.notNull(column, "数据权限表名不允许为空");
        Assert.notNull(operator, "数据权限操作符不允许为空");
        Assert.notNull(value, "数据权限值不允许为空");
    }

    private static void checkList(String column, Object value) {
        Assert.notNull(column, "数据权限表名不允许为空");
        Assert.notNull(value, "数据权限值不允许为空");
    }


    public String getColumn() {
        return column;
    }

    /**
     * 构造
     * RevisionTrail:(Date/Author/Description)
     * 2021年01月12日
     *
     * @author Japson Huang
     */
    public Expression buildExpress(Column realColum) {

        if (operator == Operator.IN || operator == Operator.NOT_IN) {
            InExpression expression = new InExpression(realColum, values);
            if (operator == Operator.NOT_IN) {
                expression.setNot(true);
            }
            return expression;
        }
        BinaryExpression expression;
        if (operator == Operator.LIKE) {
            expression = new LikeExpression();
        } else if (operator == Operator.EQ) {
            expression = new EqualsTo();
        } else if (operator == Operator.NOT_EQ) {
            expression = new NotEqualsTo();
        } else if (operator == Operator.GT) {
            expression = new GreaterThan();
        } else if (operator == Operator.GTE) {
            expression = new GreaterThanEquals();
        } else if (operator == Operator.LT) {
            expression = new MinorThan();
        } else if (operator == Operator.LTE) {
            expression = new MinorThanEquals();
        } else {
            throw new RuntimeException("unSupport Operator");
        }
        expression.setLeftExpression(realColum);
        expression.setRightExpression(value);
        return expression;
    }

}
