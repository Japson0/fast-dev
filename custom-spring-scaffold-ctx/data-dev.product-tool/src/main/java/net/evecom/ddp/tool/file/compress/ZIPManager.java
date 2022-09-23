/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.ddp.tool.file.compress;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 描述 The type Zip manager.
 *
 * @author Nick Lv
 * @created 2020 /8/11 11:53
 */
public class ZIPManager implements CompressManager {

    @Override
    public List<String> decompression(String sourcePath, String targetPath) throws IOException {
        // sourcePath为压缩所在位置
        // targetPath为解压的目标位置，还需要拼接上压缩文件的名称
        List<String> compressionFiles = new ArrayList<>();
        File folder = new File(targetPath + File.separator + sourcePath.substring(sourcePath.lastIndexOf(File.separator) + 1, sourcePath.lastIndexOf(".")));
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try (ZipFile zipFile = getZipFile(new File(sourcePath))) {
            Enumeration<ZipArchiveEntry> zipArchiveEntryEnumeration = zipFile.getEntries();
            while (zipArchiveEntryEnumeration.hasMoreElements()) {
                ZipArchiveEntry ze = zipArchiveEntryEnumeration.nextElement();
                // 如果是目录，则建立目录
                if (ze.isDirectory()) {
                    new File(folder.getAbsolutePath() + File.separator + ze.getName()).mkdirs();
                } else {
                    if (ze.getName().contains("/")) {
                        //存在查不到子文件夹，但实际上有的情况
                        new File(folder.getAbsolutePath() + File.separator + ze.getName().substring(0, ze.getName().lastIndexOf("/"))).mkdirs();
                    }
                    String tempFileName = new Date().getTime() + "-" + ze.getName().substring(ze.getName().lastIndexOf("/") + 1);
                    StringBuilder sb = new StringBuilder();
                    sb.append(folder.getAbsolutePath() + File.separator);
                    if (ze.getName().lastIndexOf("/") != -1) {
                        sb.append(ze.getName(), 0, ze.getName().lastIndexOf("/"));
                    }
                    sb.append(File.separator + tempFileName);
//                    folder.getAbsolutePath() + File.separator + ze.getName().substring(0, ze.getName().lastIndexOf("/")) + File.separator + tempFileName
                    File newFile = new File(sb.toString());
                    FileOutputStream fos = new FileOutputStream(newFile);
                    write(zipFile.getInputStream(ze), fos);
                    fos.close();
                    compressionFiles.add(newFile.getAbsolutePath());
                }
            }

        } catch (IOException e) {
            throw new IOException("解压文件异常,文件名称\" + sourcePath + \"解压目录\" + targetPath", e);
        }
        return compressionFiles;
    }

    /**
     * Gets zip file *
     *
     * @param zipFile zip file
     * @return the zip file
     * @throws Exception exception
     */
    private static ZipFile getZipFile(File zipFile) throws IOException {
        return new ZipFile(zipFile, "gbk");
    }

    @Override
    public List<String> decompression(File file, String targetPath) {

        return null;
    }

//    public static void main(String[] args) {
//        CompressManager manager = new ZIPManager();
//        manager.compression("D:\\workspace\\kgap\\dev\\kgap\\upload", "D:\\workspace\\kgap\\dev\\kgap", "数据集1-" + new Date().getTime(), true);
//    }

    @Override
    public String compression(String sourcePath, String targetPath, String zipName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(targetPath + File.separator + zipName);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            File folder = new File(sourcePath);
            if (folder.isDirectory()) {
                List<File> files = Arrays.asList(folder.listFiles());
                for (File file : files) {
                    byte[] buf = new byte[1024];
                    zos.putNextEntry(new ZipEntry(file.getName()));
                    try (FileInputStream in = new FileInputStream(file)) {
                        int len;
                        while ((len = in.read(buf)) != -1) {
                            zos.write(buf, 0, len);
                        }
                    }
                }
                zos.closeEntry();
            }
        }
        return targetPath + File.separator + zipName;
    }

    @Override
    public String compression(String sourcePath, String targetPath, String zipName, boolean keepDirStructure) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(new File(targetPath + File.separator + zipName + ".zip"));
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            compress(new File(sourcePath), zos, zipName, keepDirStructure);
        }
        return targetPath + File.separator + zipName + ".zip";
    }

    /**
     * 递归压缩
     *
     * @param sourceFile
     * @param zos
     * @param name
     * @param keepDirStructure
     * @throws IOException
     */
    private void compress(File sourceFile, ZipOutputStream zos, String name, boolean keepDirStructure) throws IOException {
        byte[] buf = new byte[1024];
        if (sourceFile.isFile()) {
            zos.putNextEntry(new ZipEntry(name));
            try (FileInputStream in = new FileInputStream(sourceFile)) {
                int len;
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
            }
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                if (keepDirStructure) {
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    if (keepDirStructure) {
                        compress(file, zos, name + "/" + file.getName(), keepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), keepDirStructure);
                    }
                }
            }
        }
    }

    @Override
    public void compression(String sourcePath, OutputStream outputStream, String zipName, boolean keepDirStructure) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(outputStream)) {
            compress(new File(sourcePath), zos, zipName, keepDirStructure);
        }
    }

    /**
     * 写数据
     *
     * @param in
     * @param out
     */
    private void write(InputStream in, OutputStream out) throws IOException {
        int readSize; // 读取到的数据长度
        byte[] buffer = new byte[1024]; // 通过 byte 作为数据中转，用于存放循环读取的临时数据
        while ((readSize = in.read(buffer, 0, 1024)) != -1) {
            out.write(buffer, 0, readSize);
        }
        out.flush();
    }
}



