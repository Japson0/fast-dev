/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.mybatis.injector.method;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

/**
 * @author Japson Huang
 * @version 1.0
 * @see com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn
 * 完全就是InsertBatchSomeColumn，为什么再写一个。
 * 因为InsertBatchSomeColumn是读取List参数，这里改写Collection:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年08月28日 CREATE
 */
public class InsertBatch extends InsertBatchSomeColumn {

    public InsertBatch() {
        super("insertBatch", t -> t.getInsertStrategy() != FieldStrategy.NEVER);
    }
}
