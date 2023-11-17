/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.handle;

/**
 * <P><B>资源清理接口:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月13日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public interface ResourceClean {

    /**
     * 会在拦截器postHandle执行自动调用
     * RevisionTrail:(Date/Author/Description)
     * 2021年11月13日
     *
     * @author Japson Huang
     */
    void clean();
}
