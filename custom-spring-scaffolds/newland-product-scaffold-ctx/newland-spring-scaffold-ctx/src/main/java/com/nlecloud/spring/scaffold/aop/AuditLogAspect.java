package com.nlecloud.spring.scaffold.aop;

import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.property.AuditLogProperty;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.core.RocketMQClientTemplate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    private final Map<String, Expression> expressionCache = new ConcurrentHashMap<>();

    public AuditLogAspect(RocketMQClientTemplate rocketMQClientTemplate,
                          AuditLogProperty auditLogProperty) {
        this.rocketMQClientTemplate = rocketMQClientTemplate;
        this.auditLogProperty = auditLogProperty;
    }

    @AfterReturning("@within(auditLog) || @annotation(auditLog)")
    public void afterReturning(JoinPoint joinPoint, com.nlecloud.spring.annotation.AuditLog auditLog) {
        if(!UserContext.isLogin()){
            return;
        }

        com.nlecloud.spring.scaffold.common.AuditLog audit = getAudit(auditLog, joinPoint);
        rocketMQClientTemplate.syncSendNormalMessage(auditLogProperty.getTopic(), audit);
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

    /**
     * 构建审计对象
     */
    private com.nlecloud.spring.scaffold.common.AuditLog getAudit(
            com.nlecloud.spring.annotation.AuditLog auditLog, JoinPoint joinPoint) {
        com.nlecloud.spring.scaffold.common.AuditLog audit =
                new com.nlecloud.spring.scaffold.common.AuditLog();

        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        audit.setClassName(methodSignature.getDeclaringTypeName());
        audit.setMethodName(methodSignature.getName());

        audit.setUserId(UserContext.getUserId());
        audit.setTenantId(UserContext.getTenantId());
        audit.setUserName(UserContext.getUserName());

        String operation = auditLog.operation();
        if (operation.contains("#")) {
            operation = getValBySpEL(
                    operation, methodSignature, joinPoint.getTarget(), joinPoint.getArgs());
        }
        audit.setOperation(operation);

        return audit;
    }
}
