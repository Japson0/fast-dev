/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.ddp.tool.file.compress;

/**
 * @author Nick Lv
 * @created 2020/8/11 11:53
 */
public class CompressManagerFactory {
    /**
     * 获取压缩包管理器
     *
     * @param type
     * @return
     */
    public static CompressManager getManager(String type) {
        CompressManager manager = null;
        switch (type) {
            case "rar":
                manager = new RARManager();
                break;
            case "zip":
                manager = new ZIPManager();
                break;
            default:
                break;
        }
        return manager;
    }

}



