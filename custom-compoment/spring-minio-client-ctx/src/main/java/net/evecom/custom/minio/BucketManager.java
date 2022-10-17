/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.custom.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.RemoveBucketArgs;
import net.evecom.custom.minio.driver.MinioDriver;
import net.evecom.custom.minio.exception.MinioExcepition;
import net.evecom.custom.minio.response.Result;

/**
 * BucketManager
 *
 * @author Nick Lv
 * @created 2022/10/12 17:56
 */
public class BucketManager {

    private MinioDriver driver;


    public BucketManager(MinioDriver driver) {
        this.driver = driver;
    }

    /**
     * 创建桶
     */
    public Result createBucket() {
        return createBucket(driver.getServerInfo().getBucket());
    }

    /**
     * 创建桶
     *
     * @param bucket 桶名
     * @return
     */
    public Result createBucket(String bucket) {
        MakeBucketArgs create = MakeBucketArgs.builder().bucket(bucket).build();
        try {
            driver.getClient().makeBucket(create);
            return Result.SUCCESS;
        } catch (Exception e) {
            throw new MinioExcepition(e.getCause(), "创建桶[" + bucket + "]出现异常");
        }
    }

    /**
     * 移除桶
     *
     * @return
     */
    public Result removeBucket() {
        return removeBucket(driver.getServerInfo().getBucket());
    }

    public Result removeBucket(String bucket) {
        try {
            if (this.checkBucketExists()) {
                RemoveBucketArgs remove = RemoveBucketArgs.builder().bucket(bucket).build();
                driver.getClient().removeBucket(remove);
            }
            return Result.SUCCESS;
        } catch (Exception e) {
            throw new MinioExcepition(e.getCause(), "移除桶[" + bucket + "]出现异常");
        }
    }

    /**
     * 检查桶是否存在
     *
     * @return
     * @throws Exception
     */
    public boolean checkBucketExists() {
        return checkBucketExists(driver.getServerInfo().getBucket());
    }

    /**
     * 根据桶名来判断桶是否存在
     *
     * @param bucket
     * @return
     * @throws Exception
     */
    public boolean checkBucketExists(String bucket) {
        try {
            BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(bucket).build();
            return driver.getClient().bucketExists(bucketExistsArgs);
        } catch (Exception e) {
            throw new MinioExcepition(e.getCause(), "检测桶[" + bucket + "]是否存在出现异常");
        }
    }
}



