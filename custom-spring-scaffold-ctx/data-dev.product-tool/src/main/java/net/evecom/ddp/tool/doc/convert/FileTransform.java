/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.ddp.tool.doc.convert;

import java.nio.file.Path;

/**
 * 文件转换
 *
 * @author Nick Lv
 * @created 2020/8/14 10:13
 */
public interface FileTransform {
    /**
     * 文件转换
     *
     * @param filePath
     * @return
     */
    String transform(String filePath, String targetPath, String filename) throws Exception;

    /**
     * 文件转换
     *
     * @param filePath
     * @return
     */
    String transform(Path filePath, String targetPath, String filename);
}
