package net.evecom.custom.hdfs;


import net.evecom.custom.hdfs.exception.HdfsIoException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsPermission;
import org.springframework.beans.factory.DisposableBean;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.hadoop.fs.CommonConfigurationKeysPublic.FS_DEFAULT_NAME_KEY;

/**
 * <P><B>Hdfs客户端:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年10月11日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class HdfsClient implements Closeable, DisposableBean {


    /**
     * 根路径
     */
    private final Path rootPath;


    /**
     * 根路径
     */
    private final String rootPathName;

    /**
     * 配置
     */
    private final Configuration configuration;

    /**
     * hdsf文件系统
     */
    private final FileSystem fileSystem;


    public HdfsClient(String rootPath, Configuration configuration) throws IOException {
        this.configuration = configuration;
        String defaultService = configuration.get(FS_DEFAULT_NAME_KEY);
        if (rootPath == null) {
            rootPath = "/";
        } else if (!rootPath.startsWith("/")) {
            rootPath = "/" + rootPath;
        }
        this.rootPathName = rootPath;
        this.rootPath = new Path(defaultService + rootPath);
        fileSystem = FileSystem.get(this.configuration);
    }


    /**
     * List file by directory list
     *
     * @param directory directory
     * @return the list
     * @throws IOException io exception
     */
    public List<String> listFileNameByDirectory(String directory) {

        try {

            RemoteIterator<LocatedFileStatus> iterator = fileSystem.listFiles(new Path(rootPath, directory), false);
            List<String> filePaths = new ArrayList<>();
            while (iterator.hasNext()) {
                LocatedFileStatus fileStatus = iterator.next();
                if (fileStatus.isFile()) {
                    filePaths.add(fileStatus.getPath().getName());
                }
            }
            return filePaths;
        } catch (IOException i) {
            throw new HdfsIoException(i, "hdfs:listFileNameByDirectory失败");
        }
    }

    /**
     * 删除目录
     * RevisionTrail:(Date/Author/Description)
     * 2023年02月09日
     *
     * @author Japson Huang
     */
    public void removeDir(String directory) throws IOException {
        try {
            fileSystem.removeAcl(new Path(rootPath, directory));
        } catch (IOException e) {
            throw new HdfsIoException(e, "hdfs:removeDir失败");
        }
    }


    /**
     * List file by directory list
     *
     * @param directory directory
     * @return the list
     * @throws IOException io exception
     */
    public List<FileStatus> list(String directory) throws IOException {

        try {
            return Arrays.asList(fileSystem.listStatus(new Path(rootPath, directory)));
        } catch (IOException i) {
            throw new HdfsIoException(i, "hdfs：list执行失败");
        }
    }


    /**
     * 下载文件
     *
     * @param srcFilePath    the src file path
     * @param fileName       the file name
     * @param targetFilePath the target file path
     * @author Nick Lv
     * @created 2020 /07/23 09:44:39 Download file.
     */
    public String downloadFile(String srcFilePath, String targetFilePath, String fileName) {
        Path srcPath = new Path(srcFilePath);
        Path targetPath = new Path(targetFilePath + File.separator + fileName);
        try {
            fileSystem.copyToLocalFile(srcPath, targetPath);
        } catch (IOException e) {
            throw new HdfsIoException(e, "hdfs下载失败，源路径为:" + srcFilePath + "目标路径为" + targetFilePath);
        }
        return targetFilePath + File.separator + fileName;
    }

    /**
     * 创建目录
     * RevisionTrail:(Date/Author/Description)
     * 2023年02月09日
     *
     * @author Japson Huang
     */
    public void mkdirs(String dir) throws IOException {
        fileSystem.mkdirs(new Path(dir));
    }

    /**
     * 创建目录
     * RevisionTrail:(Date/Author/Description)
     * 2023年02月09日
     *
     * @author Japson Huang
     */
    public void mkdirs(String dir, FsPermission fsPermission) throws IOException {
        fileSystem.mkdirs(new Path(dir), fsPermission);
    }

    /**
     * 上传文件到HDSF
     * RevisionTrail:(Date/Author/Description)
     * 2022年11月02日
     *
     * @author Japson Huang
     */
    public void uploadFile(String srcFilePath, String targetFilePath, String fileName) throws IOException {
        fileSystem.copyFromLocalFile(new Path(srcFilePath + Path.SEPARATOR + fileName),
                new Path(targetFilePath + Path.SEPARATOR + fileName));
    }

    public Path getRootPath() {
        return rootPath;
    }

    public String getRootPathName() {
        return rootPathName;
    }

    @Override
    public void close() throws IOException {
        FileSystem.closeAll();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public void destroy() throws Exception {
        close();
    }
}
