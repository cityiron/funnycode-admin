package com.fc.common.utils.mybatis.plus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author tiecheng
 * @version 1.0
 * @className MetaObjectHandler
 * @description
 * @date 2024/06/26 16:35
 */
public class CustomMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictUpdateFill(metaObject, "modifyTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "creator", String.class, "System");
        this.strictUpdateFill(metaObject, "modifier", String.class, "System");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "modifyTime", Date.class, new Date());
        this.strictUpdateFill(metaObject, "modifier", String.class, "System");
    }

}
