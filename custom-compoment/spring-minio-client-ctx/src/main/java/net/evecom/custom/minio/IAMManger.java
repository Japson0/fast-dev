/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.custom.minio;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import net.evecom.custom.minio.driver.MinioDriver;
import net.evecom.custom.minio.exception.MinioExcepition;
import net.evecom.custom.minio.policy.DefaultPolicy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * IAMManger
 *
 * @author Nick Lv
 * @created 2022/10/14 11:08
 */
public class IAMManger {

    private MinioDriver driver;

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
        HttpPost httpPost = new HttpPost(buildUrl("/api/v1/login"));
        Map<String, Object> param = new HashMap<>();
        param.put("accessKey", driver.getServerInfo().getUsername());
        param.put("secretKey", driver.getServerInfo().getPassword());
        StringEntity stringEntity = new StringEntity(JSON.toJSONString(param), StandardCharsets.UTF_8);
        stringEntity.setContentEncoding(StandardCharsets.UTF_8.toString());
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
            HttpEntity entity = httpResponse.getEntity();
            if (HttpStatus.SC_CREATED == httpResponse.getStatusLine().getStatusCode()) {
                this.cookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
            } else {
                throw new RuntimeException("登录对象存储服务器minio异常");
            }
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
            CloseableHttpResponse httpResponse = this.sendHttpPostMethod(buildUrl("/api/v1/users"), param);
            Asserts.check(httpResponse != null, "发送请求异常");
            HttpEntity entity = httpResponse.getEntity();
            return HttpStatus.SC_CREATED == httpResponse.getStatusLine().getStatusCode();
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
        HttpPut httpPut = new HttpPut(buildUrl("/api/v1/set-policy/" + policies.stream().collect(Collectors.joining(","))));
        httpPut.setHeader("Cookie", cookie);
        Map<String, Object> param = new HashMap<>();
        param.put("entityName", username);
        param.put("entityType", "user");
        StringEntity stringEntity = new StringEntity(JSON.toJSONString(param), StandardCharsets.UTF_8);
        stringEntity.setContentEncoding(StandardCharsets.UTF_8.toString());
        stringEntity.setContentType("application/json");
        httpPut.setEntity(stringEntity);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpPut)) {
            HttpEntity entity = httpResponse.getEntity();
            return HttpStatus.SC_NO_CONTENT == httpResponse.getStatusLine().getStatusCode();
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
        HttpDelete httpDelete = new HttpDelete(buildUrl("/api/v1/user?name=" + username));
        httpDelete.setHeader("Cookie", cookie);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpDelete)) {
            HttpEntity entity = httpResponse.getEntity();
            return HttpStatus.SC_NO_CONTENT == httpResponse.getStatusLine().getStatusCode();
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
        return this.createIAMPolicy(JSONObject.toJSONString(DefaultPolicy.defaultPolicy(this.driver.getServerInfo().getBucket())));
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
            CloseableHttpResponse httpResponse = this.sendHttpPostMethod(buildUrl("/api/v1/policies"), param);
            Asserts.check(httpResponse != null, "发送请求异常");
            HttpEntity entity = httpResponse.getEntity();
            if (HttpStatus.SC_CREATED == httpResponse.getStatusLine().getStatusCode()) {
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
        HttpDelete httpDelete = new HttpDelete(buildUrl("/api/v1/policy?name=" + policyName));
        httpDelete.setHeader("Cookie", cookie);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpDelete)) {
            HttpEntity entity = httpResponse.getEntity();
            return HttpStatus.SC_NO_CONTENT == httpResponse.getStatusLine().getStatusCode();
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
        HttpPut httpPut = new HttpPut(buildUrl("/api/v1/buckets/" + this.driver.getServerInfo() + "/quota"));
        httpPut.setHeader("Cookie", cookie);
        Map<String, Object> param = new HashMap<>();
        param.put("amount", amount);
        param.put("enabled", true);
        param.put("quota_type", "hard");
        StringEntity stringEntity = new StringEntity(JSON.toJSONString(param), StandardCharsets.UTF_8);
        stringEntity.setContentEncoding(StandardCharsets.UTF_8.toString());
        stringEntity.setContentType("application/json");
        httpPut.setEntity(stringEntity);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpPut)) {
            HttpEntity entity = httpResponse.getEntity();
            return HttpStatus.SC_OK == httpResponse.getStatusLine().getStatusCode();
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
        HttpPut httpPut = new HttpPut(buildUrl("/api/v1/buckets/" + this.driver.getServerInfo() + "/quota"));
        httpPut.setHeader("Cookie", cookie);
        Map<String, Object> param = new HashMap<>();
        param.put("amount", 0);
        param.put("enabled", false);
        param.put("quota_type", "hard");
        StringEntity stringEntity = new StringEntity(JSON.toJSONString(param), StandardCharsets.UTF_8);
        stringEntity.setContentEncoding(StandardCharsets.UTF_8.toString());
        stringEntity.setContentType("application/json");
        httpPut.setEntity(stringEntity);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpPut)) {
            HttpEntity entity = httpResponse.getEntity();
            return HttpStatus.SC_OK == httpResponse.getStatusLine().getStatusCode();
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
        HttpGet httpGet = new HttpGet(buildUrl("/api/v1/policies"));
        httpGet.setHeader("Cookie", cookie);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
            HttpEntity entity = httpResponse.getEntity();
            if (HttpStatus.SC_OK == httpResponse.getStatusLine().getStatusCode()) {
                JSONObject policies = JSONObject.parseObject(EntityUtils.toString(entity));
                return policies.getJSONArray("policies").stream().map(item -> {
                    JSONObject policy = (JSONObject) item;
                    return policy.getString("name");
                }).collect(Collectors.toList());
            }
        } catch (Exception e) {
            throw new MinioExcepition(e.getCause(), "获取所有的用户访问策略异常");
        }
        return new ArrayList<>();
    }


    /**
     * 发送http请求
     *
     * @param url
     * @param param
     * @return
     * @throws IOException
     */
    private CloseableHttpResponse sendHttpPostMethod(String url, Map<String, Object> param) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Cookie", cookie);
        StringEntity stringEntity = new StringEntity(JSONObject.toJSONString(param), StandardCharsets.UTF_8);
        stringEntity.setContentEncoding(StandardCharsets.UTF_8.toString());
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            return httpResponse;
        } catch (Exception e) {
            throw new MinioExcepition(e.getCause(), "发送http请求异常");
        }
    }

}



