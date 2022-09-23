/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.ddp.tool.doc.convert;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * pdf工具类
 *
 * @author Nick Lv
 * @created 2020/8/14 10:30
 */
public class PdfUtils {
    /**
     * 图片格式
     */
    private static final String IMAGE_FILE_SUFFIX = ".jpg";
    /**
     * png图片格式
     */
    private static final String IMAGE_FILE_PNG_SUFFIX = ".png";
    /**
     * 大小
     */
    private static final int SIZE = 800;

    /**
     * pdf转jpg,返回图片的链接
     *
     * @param filePath
     * @return
     */
    public static String pdf2jpg(String filePath, String targetPath) {
        File pdfFile = new File(filePath);
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.lastIndexOf("."));
        try (PDDocument doc = PDDocument.load(pdfFile)) {
            int pageCount = doc.getNumberOfPages();
            if (pageCount > 0) {
                PDFRenderer pdfRenderer = new PDFRenderer(doc);
                File folder = new File(targetPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                String imageFilePath;
                imageFilePath = folder + File.separator + fileName + IMAGE_FILE_SUFFIX;
                BufferedImage image = pdfRenderer.renderImageWithDPI(0, 105, ImageType.RGB);
                ImageIOUtil.writeImage(image, imageFilePath, 105);
                return imageFilePath;
            }
        } catch (Exception e) {
            throw new RuntimeException("pdf转图片失败");
        }
        return null;
    }

    /**
     * pdf转图片集,返回图片集的链接
     *
     * @param filePath
     * @param targetPath 路径/文档名称
     * @return
     */
    public static List<String> pdf2jpgs(String filePath, String targetPath) {
        List<String> imageUrls = new ArrayList<>();
        File pdfFile = new File(filePath);
        try (PDDocument doc = PDDocument.load(pdfFile)) {
            int pageCount = doc.getNumberOfPages();
            PDFRenderer pdfRenderer = new PDFRenderer(doc);
            File folder = new File(targetPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String imageFilePath;
            for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
                imageFilePath = folder + File.separator + pageIndex + IMAGE_FILE_SUFFIX;
                BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 75, ImageType.RGB);
                ImageIOUtil.writeImage(image, imageFilePath, 75);
                imageUrls.add(targetPath + File.separator + pageIndex + IMAGE_FILE_SUFFIX);
            }
        } catch (Exception e) {
            throw new RuntimeException("pdf转图片失败");
        }
        return imageUrls;
    }

    /**
     * pdf转图片集,返回图片集的链接
     *
     * @param filePath
     * @param targetPath 路径/文档名称
     * @return
     */
    public static List<Map<String, Object>> pdf2pngs(String filePath, String targetPath) {
        List<Map<String, Object>> imageUrls = new ArrayList<>();
        File pdfFile = new File(filePath);
        try (PDDocument doc = PDDocument.load(pdfFile)) {
            int pageCount = doc.getNumberOfPages();
            PDFRenderer pdfRenderer = new PDFRenderer(doc);
            File folder = new File(targetPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            CompletionService<Map<String, Object>> completionService
                    = new ExecutorCompletionService<>(
                    executorService);
            for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
                int finalPageIndex1 = pageIndex;
                completionService.submit(() -> {
                    String imageFilePath = folder + File.separator + finalPageIndex1 + IMAGE_FILE_SUFFIX;
                    BufferedImage image = pdfRenderer.renderImageWithDPI(finalPageIndex1, 75);
                    int imageWidth = image.getWidth();
                    int imageHeight = image.getHeight();
                    float scale = (float) SIZE / (float) imageWidth;
                    BufferedImage bi = new BufferedImage(SIZE, Math.round(imageHeight * scale), image.getType());
                    bi.getGraphics().drawImage(image.getScaledInstance(SIZE, Math.round(imageHeight * scale), Image.SCALE_SMOOTH), 0, 0, null);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    ImageIO.write(bi, "jpg", out);
                    InputStream in = new ByteArrayInputStream(out.toByteArray());
                    Files.copy(in, Paths.get(imageFilePath), StandardCopyOption.REPLACE_EXISTING);
//                ImageIOUtil.writeImage(image, imageFilePath, 144);
                    int finalPageIndex = finalPageIndex1;
                    return new HashMap<String, Object>() {{
                        put("url", targetPath + File.separator + finalPageIndex + IMAGE_FILE_SUFFIX);
                        put("width", image.getWidth());
                        put("height", image.getHeight());
                    }};
                });
            }

            for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
                Future<Map<String, Object>> future = completionService.take();
                Map<String, Object> data = future.get();
                if (data != null) {
                    imageUrls.add(data);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("pdf转图片失败");
        }
        return imageUrls;
    }

    /**
     * Main
     *
     * @param args args
     */
    public static void main(String[] args) {
        pdf2jpgs("D:\\备份文件.docx", "D:\\备份文件").forEach(System.out::println);
    }

}



