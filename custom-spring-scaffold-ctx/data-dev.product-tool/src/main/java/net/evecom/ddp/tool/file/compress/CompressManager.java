/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.ddp.tool.file.compress;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 压缩包管理器
 *
 * @author Nick Lv
 * @created 2020 /8/11 11:51
 */
public interface CompressManager {

    /**
     * 解压
     *
     * @param sourcePath the source path
     * @param targetPath the target path
     * @return list
     * @author Nick Lv
     * @created 2020 /08/12 11:56:40 Decompression list.
     */
    List<String> decompression(String sourcePath, String targetPath) throws IOException;

    /**
     * 解压
     *
     * @param file       the file
     * @param targetPath the target path
     * @return list
     * @author Nick Lv
     * @created 2020 /08/12 11:56:41 Decompression list.
     */
    List<String> decompression(File file, String targetPath);

    /**
     * 压缩
     *
     * @param sourcePath the source path
     * @return the string
     * @author Nick Lv
     * @created 2020 /08/12 11:56:41 Compression string.
     */
    String compression(String sourcePath, String targetPath, String zipName) throws IOException;

    /**
     * 递归压缩指定文件夹
     *
     * @param sourcePath
     * @param targetPath
     * @param keepDirStructure
     * @return
     */
    String compression(String sourcePath, String targetPath, String zipName, boolean keepDirStructure) throws IOException;

    /**
     * 将压缩包写入输出流中
     *
     * @param sourcePath
     * @param outputStream
     * @param zipName
     * @param keepDirStructure
     */
    void compression(String sourcePath, OutputStream outputStream, String zipName, boolean keepDirStructure) throws IOException;
}



