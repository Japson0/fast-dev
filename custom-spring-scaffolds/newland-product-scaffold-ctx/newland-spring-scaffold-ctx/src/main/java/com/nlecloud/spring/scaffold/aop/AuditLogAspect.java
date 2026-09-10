package com.nlecloud.spring.scaffold.aop;

import com.nlecloud.spring.annotation.enums.OperatorType;
import com.nlecloud.spring.scaffold.common.AuditObj;
import com.nlecloud.spring.scaffold.common.AuditValueChange;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.property.AuditLogProperty;
import net.github.fastdev.boot.utils.JacksonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.core.RocketMQClientTemplate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.javers.core.Javers;
import org.javers.core.diff.changetype.ValueChange;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 审计日志切面
 *
 * @author warrior
 */
@Aspect
public class AuditLogAspect {
    private static final SpelExpressionParser SPEL_PARSER = new SpelExpressionParser();
    private static final DefaultParameterNameDiscoverer PARAMETER_NAME_DISCOVERER =
            new DefaultParameterNameDiscoverer();

    private final RocketMQClientTemplate rocketMQClientTemplate;
    private final AuditLogProperty auditLogProperty;
    private final Javers javers;
    private final Map<String, Expression> expressionCache = new ConcurrentHashMap<>();

    public AuditLogAspect(RocketMQClientTemplate rocketMQClientTemplate,
                          AuditLogProperty auditLogProperty,
                          Javers javers) {
        this.rocketMQClientTemplate = rocketMQClientTemplate;
        this.auditLogProperty = auditLogProperty;
        this.javers = javers;
    }

    @AfterReturning(
            pointcut = "@within(auditLog) || @annotation(auditLog)",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, com.nlecloud.spring.annotation.AuditLog auditLog,
                               Object result) {
        if (!UserContext.isLogin()) {
            return;
        }
        String operator = null;
        OperatorType operatorType = OperatorType.UPDATE;
        String changes = null;
        Long targetTenantId = null;
        if (result instanceof AuditObj) {
            AuditObj auditObj = (AuditObj) result;
            operator = auditObj.getOperatorValue();
            targetTenantId = auditObj.getTargetTenantId();
            operatorType = auditObj.getOperatorType() == null
                    ? OperatorType.UPDATE : auditObj.getOperatorType();
            if (operatorType == OperatorType.UPDATE) {
                changes = JacksonUtils.toJson(getChanges(auditObj.getSource(), auditObj.getTarget()));
            } else if (operatorType == OperatorType.ADD || operatorType == OperatorType.DELETE) {
                changes = auditObj.getSource() == null
                        ? null : JacksonUtils.toJson(auditObj.getSource());
            }
        }

        operator = resolveOperation(auditLog, joinPoint, operator);
        com.nlecloud.spring.scaffold.common.AuditLog audit = getAudit(
                operator, operatorType, changes, targetTenantId,auditLog.businessCode());
        rocketMQClientTemplate.syncSendNormalMessage(auditLogProperty.getTopic(), audit);
    }

    private List<AuditValueChange> getChanges(Object source, Object target) {
        if (source == null || target == null) {
            return Collections.emptyList();
        }

        return javers.compare(source, target)
                .getChangesByType(ValueChange.class)
                .stream()
                .map(change -> new AuditValueChange(
                        change.getPropertyName(),
                        formatValue(change.getLeft()),
                        formatValue(change.getRight())))
                .collect(Collectors.toList());
    }

    private String formatValue(Object value) {
        return value == null ? "空" : String.valueOf(value);
    }

    /**
     * 解析spEL表达式
     */
    private String getValBySpEL(String spEL, MethodSignature methodSignature,
                                Object target, Object[] args) {
        Expression expression = expressionCache.computeIfAbsent(spEL, SPEL_PARSER::parseExpression);
        EvaluationContext context = new MethodBasedEvaluationContext(
                target, methodSignature.getMethod(), args, PARAMETER_NAME_DISCOVERER);
        String value = expression.getValue(context, String.class);
        return StringUtils.defaultIfBlank(value, spEL);
    }

    private String resolveOperation(com.nlecloud.spring.annotation.AuditLog auditLog,
                                    JoinPoint joinPoint, String operator) {
        if (StringUtils.isNotEmpty(operator)) {
            return operator;
        }

        operator = auditLog.value();
        if (!operator.contains("#")) {
            return operator;
        }

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return getValBySpEL(
                operator, methodSignature, joinPoint.getTarget(), joinPoint.getArgs());
    }

    /**
     * 构建审计对象
     */
    private com.nlecloud.spring.scaffold.common.AuditLog getAudit(
            String operation, OperatorType operatorType, String changes,
            Long targetTenantId,String businessCode) {
        com.nlecloud.spring.scaffold.common.AuditLog audit =
                new com.nlecloud.spring.scaffold.common.AuditLog();
        audit.setUserId(UserContext.getUserId());
        audit.setTenantId(UserContext.getTenantId());
        audit.setUserName(UserContext.getUserName());
        audit.setOperation(operation);
        audit.setOperatorType(operatorType);
        audit.setChanges(changes);
        audit.setBusinessCode(businessCode);
        audit.setTargetTenantId(targetTenantId);
        return audit;
    }
}
