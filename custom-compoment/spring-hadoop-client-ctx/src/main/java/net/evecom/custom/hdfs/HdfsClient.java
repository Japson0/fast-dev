package net.evecom.custom.hdfs;


import net.evecom.custom.hdfs.exception.HdfsIoException;
import net.evecom.custom.hdfs.utils.ParquetUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.schema.MessageType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <P><B>Hdfs客户端:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年10月11日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class HdfsClient {

    /**
     * 根路径
     */
    private String rootPath;

    /**
     * 配置
     */
    private final Configuration configuration;

    /**
     * hdsf文件系统
     */
    private final FileSystem fileSystem;


    public HdfsClient(Configuration configuration) throws IOException {
        this.configuration = configuration;
        fileSystem = FileSystem.get(this.configuration);
    }


    /**
     * List file by directory list
     *
     * @param directory directory
     * @return the list
     * @throws IOException io exception
     */
    public List<String> listFileByDirectory(String directory) throws IOException {

        RemoteIterator<LocatedFileStatus> iterator = fileSystem.listFiles(new Path(rootPath, directory), false);
        List<String> filePaths = new ArrayList<>();
        while (iterator.hasNext()) {
            LocatedFileStatus fileStatus = iterator.next();
            if (fileStatus.isFile()) {
                filePaths.add(fileStatus.getPath().getName());
            }
        }
        return filePaths;
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
     * 获取文件的字节流
     *
     * @param file the file
     * @return byte [ ]
     * @author Nick Lv
     * @created 2020 /07/23 09:44:39 Get file byte array byte [ ].
     */
    private static byte[] getFileByteArray(File file) {
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            throw new HdfsIoException("文件过大");
        }
        byte[] buffer = null;
        try (FileInputStream fi = new FileInputStream(file)) {
            buffer = new byte[(int) fileSize];
            int offset = 0;
            int numRead = 0;
            while (offset < buffer.length
                    && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            // 确保所有数据均被读取
            if (offset != buffer.length) {
                throw new IOException("Could not completely read file "
                        + file.getName());
            }
        } catch (Exception e) {
            throw new HdfsIoException(e, "读取文件失败");
        }
        return buffer;
    }

    public ParquetDTO read4Parquet(Path path) throws IOException {
        return ParquetUtils.read2Parquet(path);
    }

    public List<Group> read2Group4Parquet(Path path) throws IOException {

        return ParquetUtils.read2Group(path);
    }

    public <T> List<T> read4Parquet(Path path, Function<Group, T> accept) throws IOException {
        return ParquetUtils.read(path, accept);
    }


    public void write2Parquet(Path path, MessageType messageType, ParquetDTO parquetDTO) throws IOException {
        ParquetUtils.write(path, messageType, parquetDTO, configuration);
    }

    public void write2Parquet(Path path, List<Group> groups) throws IOException {
        ParquetUtils.write(path, groups, configuration);
    }


    public void write2Parquet(Path path, MessageType messageType, Consumer<ParquetWriter<Group>> consumer)
            throws IOException {
        ParquetUtils.write(path, messageType, consumer, configuration);
    }


}
