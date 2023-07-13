/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.custom.minio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.evecom.custom.minio.driver.MinioDriver;
import net.evecom.custom.minio.exception.MinioExcepition;
import net.evecom.custom.minio.policy.DefaultPolicy;
import okhttp3.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * IAMManger
 *
 * @author Nick Lv
 * @created 2022/10/14 11:08
 */
public class IAMManger {

    private MinioDriver driver;

    /**
     * contentType
     */
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    /**
     * 客户端
     */
    private final static OkHttpClient CLIENT = new OkHttpClient();

    /**
     * 序列化器
     */
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public IAMManger(MinioDriver driver) {
        this.driver = driver;
    }

    /**
     * 登陆后获取到的返回的header对象
     */
    private String cookie;

    /**
     * @throws IOException
     */
    private void setCookie() throws IOException {
        Request.Builder requestBuilder = new Request.Builder().url(buildUrl("/api/v1/login"));
        Map<String, Object> param = new HashMap<>();
        param.put("accessKey", driver.getServerInfo().getUsername());
        param.put("secretKey", driver.getServerInfo().getPassword());
        requestBuilder.post(RequestBody.create(param.toString(), MEDIA_TYPE));
        Response response = CLIENT.newCall(requestBuilder.build())
                .execute();
        if (response.isSuccessful()) {
            this.cookie = response.header("Set-Cookie");
        } else {
            throw new RuntimeException("登录对象存储服务器minio异常");
        }

    }

    /**
     * 添加用户
     *
     * @param username
     * @param password
     * @param groups
     * @param policies
     * @return
     */
    public boolean addUser(String username, String password, List<String> groups, List<String> policies) {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("accessKey", username);
            param.put("secretKey", password);
            param.put("groups", groups);
            param.put("policies", policies);
            Response httpResponse = this.sendHttpPostMethod(buildUrl("/api/v1/users"), param);
            return httpResponse.isSuccessful();
        } catch (Exception e) {
            throw new MinioExcepition(e.getCause(), "创建用户失败");
        }
    }


    /**
     * 修改用户访问策略
     *
     * @return
     */
    public boolean updateUserPolicies(String username, List<String> policies) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("entityName", username);
        param.put("entityType", "user");
        try {
            Request request = new Request.Builder()
                    .url(buildUrl("/api/v1/set-policy/" + policies.stream().collect(Collectors.joining(","))))
                    .header("Cookie", this.cookie)
                    .put(RequestBody.create(OBJECT_MAPPER.writeValueAsBytes(param), MEDIA_TYPE))
                    .build();
            return CLIENT.newCall(request).execute().isSuccessful();
        } catch (Exception e) {
            throw new MinioExcepition(e.getCause(), "修改用户访问策略异常");
        }
    }

    /**
     * 构建链接
     *
     * @param uri
     * @return
     */
    private String buildUrl(String uri) {
        return driver.getEndPoint() + uri;
    }

    /**
     * Delete user boolean
     *
     * @param username username
     * @return the boolean
     */
    public boolean deleteUser(String username) {
        Request request = new Request.Builder()
                .url(buildUrl("/api/v1/user?name=" + username))
                .header("Cookie", this.cookie)
                .delete()
                .build();
        try {
            return CLIENT.newCall(request).execute().isSuccessful();
        } catch (Exception e) {
            throw new MinioExcepition(e.getCause(), "删除用户失败");
        }
    }

    /**
     * 创建默认的用户访问策略
     * 默认的则为当前bucket的全量数据
     *
     * @return
     */
    public String createDefaultIAMPolicy() {
        try {
            return this.createIAMPolicy(OBJECT_MAPPER.writeValueAsString(DefaultPolicy.defaultPolicy(this.driver.getServerInfo().getBucket())));
        } catch (JsonProcessingException e) {
            throw new MinioExcepition(e, "序列化失败");
        }
    }

    /**
     * 创建用户访问策略
     *
     * @param iamPolicy
     * @return
     */
    public String createIAMPolicy(String iamPolicy) {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("name", driver.getServerInfo().getUsername());
            param.put("policy", iamPolicy);
            Response httpResponse = this.sendHttpPostMethod(buildUrl("/api/v1/policies"), param);
            if (httpResponse.isSuccessful()) {
                return this.driver.getServerInfo().getBucket();
            }
        } catch (Exception e) {
            throw new MinioExcepition(e.getCause(), "创建用户访问策略失败");
        }
        return null;
    }

    /**
     * 修改用户访问策略
     *
     * @param policyContent
     * @return
     */
    protected boolean updateVisitPolicy(String policyContent) {
        return this.createIAMPolicy(policyContent) != null;
    }

    /**
     * 删除用户访问策略
     *
     * @param policyName
     * @return
     */
    protected boolean deleteVisitPolicy(String policyName) {
        Request request = new Request.Builder()
                .url(buildUrl("/api/v1/policy?name=" + policyName))
                .header("Cookie", this.cookie)
                .build();
        try {
            Response response = CLIENT.newCall(request).execute();
            return response.isSuccessful();
        } catch (Exception e) {
            throw new MinioExcepition(e.getCause(), "删除用户访问策略失败");
        }
    }

    /**
     * 设置桶的容量
     *
     * @param amount
     * @return
     */
    protected boolean setQuota(Long amount) {
        Map<String, Object> param = new HashMap<>();
        param.put("amount", amount);
        param.put("enabled", true);
        param.put("quota_type", "hard");
        try {
            Request request = new Request.Builder()
                    .url(buildUrl("/api/v1/buckets/" + this.driver.getServerInfo() + "/quota"))
                    .header("Cookie", this.cookie)
                    .put(RequestBody.create(OBJECT_MAPPER.writeValueAsBytes(param), MEDIA_TYPE))
                    .build();
            return CLIENT.newCall(request).execute().isSuccessful();
        } catch (Exception e) {
            throw new MinioExcepition(e.getCause(), "设置桶容量异常");

        }
    }

    /**
     * 取消桶的容量
     *
     * @return
     */
    public boolean cancelQuota() {
        Map<String, Object> param = new HashMap<>();
        param.put("amount", 0);
        param.put("enabled", false);
        param.put("quota_type", "hard");
        try {
            Request request = new Request.Builder()
                    .url(buildUrl("/api/v1/buckets/" + this.driver.getServerInfo() + "/quota"))
                    .header("Cookie", this.cookie)
                    .put(RequestBody.create(OBJECT_MAPPER.writeValueAsBytes(param), MEDIA_TYPE))
                    .build();
            return CLIENT.newCall(request).execute().isSuccessful();
        } catch (Exception e) {
            throw new MinioExcepition(e.getCause(), "修改用户失败");
        }
    }

    /**
     * 获取所有用户访问策略
     *
     * @return
     */
    protected List<String> getVisitPolicies() {
        try {
            Request request = new Request.Builder()
                    .url(buildUrl("/api/v1/policies"))
                    .header("Cookie", this.cookie)
                    .build();
            Response response = CLIENT.newCall(request).execute();
            if (response.isSuccessful()) {
                JsonNode jsonNode = OBJECT_MAPPER.readTree(response.body().bytes());
                JsonNode policies = jsonNode.get("policies");
                if (policies.isArray()) {
                    List<String> result = new ArrayList<>(policies.size());
                    for (JsonNode policy : policies) {
                        result.add(policy.get("name").asText());
                    }
                    return result;
                }
            }
            return Collections.emptyList();
        } catch (Exception e) {
            throw new MinioExcepition(e.getCause(), "获取所有的用户访问策略异常");
        }
    }


    /**
     * 发送http请求
     *
     * @param url
     * @param param
     * @return
     * @throws IOException
     */
    private Response sendHttpPostMethod(String url, Map<String, Object> param) throws IOException {

        Request request = new Request.Builder().url(url)
                .header("Cookie", this.cookie)
                .post(RequestBody.create(OBJECT_MAPPER.writeValueAsBytes(param), MEDIA_TYPE))
                .build();
        try {
            Response response = CLIENT.newCall(request).execute();
            return response;
        } catch (Exception e) {
            throw new MinioExcepition(e.getCause(), "发送http请求异常");
        }
    }

}



