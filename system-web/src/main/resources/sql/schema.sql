-- 用户表
CREATE TABLE `fc_user`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
    `name`        VARCHAR(64)  DEFAULT NULL COMMENT '名称',
    `nick_name`   VARCHAR(128) DEFAULT NULL COMMENT '昵称',
    `password`    VARCHAR(256) DEFAULT NULL COMMENT '密码',
    `salt`        VARCHAR(256) DEFAULT NULL COMMENT 'MD5密码盐',
    `type`        TINYINT(2) UNSIGNED DEFAULT 1 COMMENT '类型(1-普通,2-VIP,3-企业,4-内部,5-管理用户,6-过期VIP)',
    `birthday`    DATETIME     DEFAULT NULL COMMENT '生日',
    `avatar`      VARCHAR(256) DEFAULT NULL COMMENT '头像',
    `gender`      TINYINT(2) UNSIGNED DEFAULT 1 COMMENT '性别(1-未知,2-男,3-女)',
    `phone`       VARCHAR(15)  DEFAULT NULL COMMENT '手机号',
    `is_deleted`  TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '删除状态(0-正常,1-删除)',
    `creator`     VARCHAR(64)  DEFAULT NULL COMMENT '创建人',
    `modifier`    VARCHAR(64)  DEFAULT NULL COMMENT '修改人',
    `create_time` DATETIME     DEFAULT now() COMMENT '创建时间',
    `modify_time` DATETIME     DEFAULT now() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '用户表';

-- 用户角色表
CREATE TABLE `fc_user_role`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
    `user_id`     BIGINT(20) UNSIGNED NOT NULL COMMENT '用户ID',
    `role_id`     BIGINT(20) UNSIGNED NOT NULL COMMENT '角色ID',
    `is_deleted`  TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '删除状态(0-正常,1-删除)',
    `creator`     VARCHAR(64) DEFAULT NULL COMMENT '创建人',
    `modifier`    VARCHAR(64) DEFAULT NULL COMMENT '修改人',
    `create_time` DATETIME    DEFAULT now() COMMENT '创建时间',
    `modify_time` DATETIME    DEFAULT now() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY           `idx_user_id` (`user_id`),
    KEY           `idx_role_id` (`role_id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '用户角色表';

-- 收货地址表
CREATE TABLE `fc_address`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
    `user_id`     BIGINT(20) UNSIGNED NOT NULL COMMENT '用户ID',
    `name`        VARCHAR(64) NOT NULL COMMENT '收货人名称',
    `phone`       VARCHAR(15) NOT NULL COMMENT '手机号',
    `detail`      VARCHAR(256) DEFAULT NULL COMMENT '详细地址',
    `tag`         VARCHAR(128) DEFAULT NULL COMMENT '标签',
    `is_default`  TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '删除状态(0-非默认,1-默认)',
    `is_deleted`  TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '删除状态(0-正常,1-删除)',
    `creator`     VARCHAR(64)  DEFAULT NULL COMMENT '创建人',
    `modifier`    VARCHAR(64)  DEFAULT NULL COMMENT '修改人',
    `create_time` DATETIME     DEFAULT now() COMMENT '创建时间',
    `modify_time` DATETIME     DEFAULT now() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY           `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '收货地址表';

-- 字典类型表
CREATE TABLE `sys_dict`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
    `dict_name`   VARCHAR(100) DEFAULT '' COMMENT '字典名称',
    `dict_code`   VARCHAR(100) DEFAULT '' COMMENT '字典编码',
    `dict_type`   TINYINT(2) UNSIGNED DEFAULT 1 COMMENT '字典类型(1-字符串,2-数字)',
    `status`      TINYINT(2) UNSIGNED DEFAULT 1 COMMENT '状态（1-启动,2-停用）',
    `remark`             VARCHAR(256) NOT NULL COMMENT '备注',
    `is_deleted`  TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '删除状态(0-正常,1-删除)',
    `creator`     VARCHAR(64)  DEFAULT NULL COMMENT '创建人',
    `modifier`    VARCHAR(64)  DEFAULT NULL COMMENT '修改人',
    `create_time` DATETIME     DEFAULT now() COMMENT '创建时间',
    `modify_time` DATETIME     DEFAULT now() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_dict_code` (`dict_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典类型表';

-- 字典条目表
CREATE TABLE `sys_dict_item`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
    `dict_id`     BIGINT(20) UNSIGNED NOT NULL COMMENT '字典ID',
    `dict_sort`   INT(4) DEFAULT 0 COMMENT '字典排序',
    `dict_label`  VARCHAR(100) DEFAULT '' COMMENT '字典标签',
    `dict_value`  VARCHAR(100) DEFAULT '' COMMENT '字典键值',
    `is_default`  TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '删除状态(0-非默认,1-默认)',
    `status`      TINYINT(2) UNSIGNED DEFAULT 1 COMMENT '状态（1-启动,2-停用）',
    `remark`             VARCHAR(256) NOT NULL COMMENT '备注',
    `is_deleted`  TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '删除状态(0-正常,1-删除)',
    `creator`     VARCHAR(64)  DEFAULT NULL COMMENT '创建人',
    `modifier`    VARCHAR(64)  DEFAULT NULL COMMENT '修改人',
    `create_time` DATETIME     DEFAULT now() COMMENT '创建时间',
    `modify_time` DATETIME     DEFAULT now() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY           `idx_dict_id` (`dict_id`),
    KEY           `idx_dict_sort` (`dict_sort`),
    KEY           `idx_status` (`status`),
    KEY           `idx_dict_id_dict_label` (`dict_id`, `dict_label`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典条目表';