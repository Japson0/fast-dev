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
 * minio的文件管理器
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
     * @param path 指定名录，为桶内路径
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
     * 上传文本内容
     *
     * @param objPath 上传文本内容的路径
     * @param data    文本内容
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
     * @param objPath       minio文件存放路径，例如/dataset/abc.txt
     * @param localFilePath 本地文件路径
     * @throws Exception
     */
    public void uploadFile(String objPath, String localFilePath) throws Exception {
        driver.getClient().uploadObject(UploadObjectArgs.builder().bucket(this.driver.getServerInfo().getBucket()).filename(localFilePath).object(objPath).build());
    }


    /**
     * 下载文件
     *
     * @param filePath     minio文件存放路径，例如/data/abc.txt
     * @param downloadFile 下载后的文件存放路径
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
     * 下载文件，获取文件流
     *
     * @param objPath minio文件的存放路径，例如/data/abc.txt
     * @return the object
     * @throws Exception exception
     */
    public InputStream downloadFile(String objPath) throws Exception {
        return driver.getClient().getObject(GetObjectArgs.builder().bucket(driver.getServerInfo().getBucket()).object(objPath).build());
    }

    /**
     * 删除文件
     *
     * @param fileName    文件名称 abc.txt
     * @param storagePath 文件存放路径 ,/data/
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
     * @param objPath 文件存放路径，例如/data/abc.txt
     * @throws Exception
     */
    public void deleteFile(String objPath) throws Exception {
        driver.getClient().removeObject(RemoveObjectArgs.builder().bucket(this.driver.getServerInfo().getBucket()).object(objPath).build());
    }


}



