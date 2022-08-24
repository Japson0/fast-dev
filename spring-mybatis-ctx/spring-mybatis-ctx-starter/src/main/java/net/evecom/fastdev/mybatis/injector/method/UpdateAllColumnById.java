/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2011-2020, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.evecom.fastdev.mybatis.injector.method;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;

/**
 * 根据 ID 更新固定的那几个字段(但是不包含逻辑删除)
 *
 * <p>
 * 自己的通用 mapper 如下使用:
 * <pre>
 * int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) T entity);
 * </pre>
 * </p>
 *
 * @author hubin
 * @since 2019-04-12
 */
public class UpdateAllColumnById extends AlwaysUpdateSomeColumnById {

    public UpdateAllColumnById() {

        super("updateAllColumnById", t -> t.getUpdateStrategy() != FieldStrategy.NEVER);
    }
}
