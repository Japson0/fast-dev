/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.ddp.tool.doc.convert;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;

/**
 * @author Nick Lv
 * @created 2021/3/25 16:27
 */
public class OfficeToPdfTransform implements FileTransform {


    @Override
    public String transform(String filePath, String targetPath, String filename) throws Exception {
        String pdfName = filename + ".pdf";
        Document document = new Document(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(targetPath + File.separator + pdfName);
        document.save(fileOutputStream, SaveFormat.PDF);
        return targetPath + File.separator + pdfName;
    }

    @Override
    public String transform(Path filePath, String targetPath, String filename) {

        return null;
    }
}



