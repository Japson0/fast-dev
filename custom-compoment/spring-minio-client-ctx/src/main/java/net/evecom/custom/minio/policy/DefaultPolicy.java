/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.custom.minio.policy;

import java.util.Arrays;
import java.util.List;

/**
 * 默认策略
 *
 * @author Nick Lv
 * @created 2021/9/13 17:10
 */
public class DefaultPolicy {
    /**
     * {
     *     "Version": "2012-10-17",
     *     "Statement": [
     *         {
     *             "Effect": "Allow",
     *             "Action": [
     *                 "s3:*"
     *             ],
     *             "Resource": [
     *                 "arn:aws:s3:::tenant+租户id/*"
     *             ]
     *         }
     *     ]
     * }
     */
    /**
     * 对应的minio版本
     */
    private String Version;
    /**
     * 策略
     */
    private List<PolicyStatement> Statement;

    /**
     * Default policy default policy
     *
     * @param bucketName bucket name
     * @return the default policy
     */
    public static DefaultPolicy defaultPolicy(String bucketName) {
        DefaultPolicy defaultPolicy = new DefaultPolicy();
        defaultPolicy.Version = "2012-10-17";
        PolicyStatement policyStatement = new PolicyStatement();
        policyStatement.setEffect("Allow");
        policyStatement.setAction(Arrays.asList("s3:*"));
        policyStatement.setResource(Arrays.asList("arn:aws:s3:::" + bucketName + "/*"));
        defaultPolicy.Statement = Arrays.asList(policyStatement);
        return defaultPolicy;
    }

    /**
     * Gets version *
     *
     * @return the version
     */
    public String getVersion() {
        return Version;
    }

    /**
     * Sets version *
     *
     * @param version version
     */
    public void setVersion(String version) {
        Version = version;
    }

    /**
     * Gets statement *
     *
     * @return the statement
     */
    public List<PolicyStatement> getStatement() {
        return Statement;
    }

    /**
     * Sets statement *
     *
     * @param statement statement
     */
    public void setStatement(List<PolicyStatement> statement) {
        Statement = statement;
    }
}

/**
 * Policy statement
 *
 * @author Nick Lv
 */
class PolicyStatement {
    /**
     * Effect
     */
    private String Effect;
    /**
     * Action
     */
    private List<String> Action;
    /**
     * Resource
     */
    private List<String> Resource;


    /**
     * Gets effect *
     *
     * @return the effect
     */
    public String getEffect() {
        return Effect;
    }

    /**
     * Sets effect *
     *
     * @param effect effect
     */
    public void setEffect(String effect) {
        Effect = effect;
    }

    /**
     * Gets action *
     *
     * @return the action
     */
    public List<String> getAction() {
        return Action;
    }

    /**
     * Sets action *
     *
     * @param action action
     */
    public void setAction(List<String> action) {
        Action = action;
    }

    /**
     * Gets resource *
     *
     * @return the resource
     */
    public List<String> getResource() {
        return Resource;
    }

    /**
     * Sets resource *
     *
     * @param resource resource
     */
    public void setResource(List<String> resource) {
        Resource = resource;
    }
}

