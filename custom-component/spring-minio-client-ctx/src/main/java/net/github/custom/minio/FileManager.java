
package net.github.custom.minio;

import io.minio.*;
import io.minio.messages.Item;
import net.github.custom.minio.exception.MinioExcepition;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
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
    /**
     * minio驱动信息
     */
    private MinioClient minioClient;

    /**
     * 默认桶
     */
    private String defaultBucket;

    public FileManager(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * 获取文件目录
     *
     * @param path 指定名录，为桶内路径
     * @return
     */
    public List<String> listFiles(String bucket,String path) {
        List<String> fileNames = new ArrayList<>();
        Iterable<Result<Item>> results = this.minioClient.listObjects(ListObjectsArgs.builder().bucket(bucket).prefix(path).build());
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
    public void uploadContent(String bucket,String objPath, String data) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        uploadFile(bucket,objPath, inputStream);
    }


    /**
     * 上传文件
     *
     * @param objPath       minio文件存放路径，例如/dataset/abc.txt
     * @param localFilePath 本地文件路径
     * @throws Exception
     */
    public void uploadFile(String bucket,String objPath, String localFilePath) throws Exception {

        uploadFile(bucket,objPath, new File(localFilePath));
    }



    /**
     * 上传文件流
     * RevisionTrail:(Date/Author/Description)
     * 2022年12月30日
     *
     * @author Japson Huang
     */
    public void uploadFile(String bucket,String objPath, InputStream inputStream) throws Exception {
        this.minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .object(objPath)
                .stream(inputStream, inputStream.available(), -1).build());
    }


    /**
     * 上传文件
     * RevisionTrail:(Date/Author/Description)
     * 2022年12月30日
     *
     * @author Japson Huang
     */
    public void uploadFile(String bucket,String objPath, File file) throws Exception {
        if (file.exists()) {
            this.minioClient.uploadObject(UploadObjectArgs.builder()
                    .bucket(bucket)
                    .filename(file.getAbsolutePath())
                    .object(objPath).build());
        } else {
            throw new FileNotFoundException("minio上传失败，原因：找不到对应的本地文件");
        }
    }



    /**
     * 下载文件
     *
     * @param filePath     minio文件存放路径，例如/data/abc.txt
     * @param downloadFile 下载后的文件存放路径
     * @return
     */
    public void downloadFile(String bucket,String filePath, String downloadFile) throws Exception {
        downloadFile(bucket,filePath, new File(downloadFile));
    }


    /**
     * 下载文件
     * RevisionTrail:(Date/Author/Description)
     * 2022年12月30日
     *
     * @author Japson Huang
     */
    public void downloadFile(String bucket,String filePath, File targetFile) throws Exception {
        if (!targetFile.getParentFile().exists()) {
            if (!targetFile.getParentFile().mkdirs()) {
                throw new UnsupportedOperationException(targetFile.getParent() + "路径不存在，" +
                        "无法创建路径，请检查是否拥有创建目录的权限!");
            }
        }
        this.minioClient.downloadObject(
                DownloadObjectArgs.builder()
                        .bucket(bucket)
                        .object(filePath)
                        .filename(targetFile.getAbsolutePath())
                        .build());
    }


    /**
     * 下载文件，获取文件流
     *
     * @param objPath minio文件的存放路径，例如/data/abc.txt
     * @return the object
     * @throws Exception exception
     */
    public InputStream downloadFile(String bucket,String objPath) throws Exception {

        return this.minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucket)
                .object(objPath).build());
    }

    /**
     * 删除文件
     *
     * @param bucket    桶
     * @param objPath 文件存放路径 ,/data/
     * @return
     */
    protected void deleteFile(String bucket,String objPath) throws Exception {
        this.minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(objPath).build());
    }



}



