/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.custom.minio;

import io.minio.*;
import io.minio.messages.Item;
import net.evecom.custom.minio.driver.MinioDriver;
import net.evecom.custom.minio.exception.MinioExcepition;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * FileManager
 *
 * @author Nick Lv
 * @created 2022/10/12 18:04
 */
public class FileManager {
    private MinioDriver driver;

    public FileManager(MinioDriver driver) {
        this.driver = driver;
    }

    /**
     * 获取文件目录
     *
     * @param path
     * @return
     */
    public List<String> listFiles(String path) {
        List<String> fileNames = new ArrayList<>();
        Iterable<Result<Item>> results = driver.getClient().listObjects(ListObjectsArgs.builder().bucket(driver.getServerInfo().getBucket()).prefix(path).build());
        results.forEach(result -> {
            try {
                Item item = result.get();
                if (!item.isDir()) {
                    fileNames.add(item.objectName());
                }
            } catch (Exception e) {
                throw new MinioExcepition(e, "获取文件目录下的文件异常");
            }
        });
        return fileNames;
    }

    /**
     * Put object *
     *
     * @param objPath obj path
     * @param data    data
     * @throws Exception exception
     */
    public void uploadData(String objPath, String data) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        driver.getClient().putObject(PutObjectArgs.builder().bucket(driver.getServerInfo().getBucket()).object(objPath).stream(
                inputStream, inputStream.available(), -1).build());
    }

    /**
     * 上传文件
     *
     * @param objPath
     * @param localFilePath
     * @throws Exception
     */
    public void uploadFile(String objPath, String localFilePath) throws Exception {
        driver.getClient().uploadObject(UploadObjectArgs.builder().bucket(this.driver.getServerInfo().getBucket()).filename(localFilePath).object(objPath).build());
    }


    /**
     * 下载文件
     *
     * @param filePath
     * @param downloadFile
     * @return
     */
    public void downloadFile(String filePath, String downloadFile) throws Exception {
        driver.getClient().downloadObject(
                DownloadObjectArgs.builder()
                        .bucket(driver.getServerInfo().getBucket())
                        .object(filePath)
                        .filename(downloadFile)
                        .build());
    }

    /**
     * Gets object *
     *
     * @param objPath obj path
     * @return the object
     * @throws Exception exception
     */
    public InputStream downloadFile(String objPath) throws Exception {
        return driver.getClient().getObject(GetObjectArgs.builder().bucket(driver.getServerInfo().getBucket()).object(objPath).build());
    }

    /**
     * 删除文件
     *
     * @param fileName
     * @param storagePath
     * @return
     */
    protected void deleteFile(String fileName, String storagePath) throws Exception {
        if (!storagePath.endsWith(File.separator)) {
            storagePath += File.separator;
        }
        this.deleteFile(storagePath + fileName);
    }

    /**
     * 删除对象
     *
     * @param objPath
     * @throws Exception
     */
    public void deleteFile(String objPath) throws Exception {
        driver.getClient().removeObject(RemoveObjectArgs.builder().bucket(this.driver.getServerInfo().getBucket()).object(objPath).build());
    }


}



