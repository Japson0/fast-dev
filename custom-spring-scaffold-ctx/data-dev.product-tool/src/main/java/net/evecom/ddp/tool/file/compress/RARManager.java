/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.ddp.tool.file.compress;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nick Lv
 * @created 2020/8/11 11:52
 */
public class RARManager implements CompressManager {

    @Override
    public List<String> decompression(String sourcePath, String targetPath) throws IOException {
        List<String> compressionFiles = new ArrayList<>();
        File folder = new File(targetPath + File.separator + sourcePath.substring(sourcePath.lastIndexOf(File.separator) + 1, sourcePath.lastIndexOf(".")));
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try (Archive archive = new Archive(new File(sourcePath));
             FileOutputStream fos = new FileOutputStream(folder.getAbsolutePath())) {
            FileHeader fh = archive.nextFileHeader();
            while (fh != null) {
                archive.extractFile(fh, fos);
                fh = archive.nextFileHeader();
            }
        } catch (IOException | RarException e) {
            throw new IOException("解压文件异常,文件名称" + sourcePath + "解压目录" + targetPath, e);
        }
        return compressionFiles;
    }

    @Override
    public List<String> decompression(File file, String targetPath) {
        return null;
    }

    @Override
    public String compression(String sourcePath, String targetPath, String zipName) {

        return null;
    }

    @Override
    public String compression(String sourcePath, String targetPath, String zipName, boolean keepDirStructure) {
        return null;
    }

    @Override
    public void compression(String sourcePath, OutputStream outputStream, String zipName, boolean keepDirStructure) {

    }
}



