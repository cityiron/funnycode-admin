-- 场馆表
CREATE TABLE `fc_place`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
    `code`        VARCHAR(10) NOT NULL COMMENT '唯一编码',
    `name`        VARCHAR(64) NOT NULL COMMENT '名称',
    `position`    VARCHAR(256)         DEFAULT NULL COMMENT '位置说明',
    `type`        VARCHAR(6)           DEFAULT NULL COMMENT '类型(ymq羽毛球,ppq乒乓球,fp飞盘,lq篮球,zq足球,yy游泳)',
    `plan_pic`    VARCHAR(256)         DEFAULT NULL COMMENT '平面图',
    `brief`       VARCHAR(256)         DEFAULT NULL COMMENT '简介',
    `facs_dict`   VARCHAR(256)         DEFAULT NULL COMMENT '设施',
    `phone`       VARCHAR(15)          DEFAULT NULL COMMENT '手机号',
    `tel`         VARCHAR(45)          DEFAULT NULL COMMENT '座机',
    `is_open`     TINYINT(1) UNSIGNED DEFAULT 1 COMMENT '营业状态(0-未营业,1-营业)',
    `max_day`     INT(11) UNSIGNED DEFAULT 20180808 COMMENT '生成预定信息截止日期',
    `start_time`  VARCHAR(32) NOT NULL COMMENT '预定信息起始时间',
    `end_time`    VARCHAR(32) NOT NULL COMMENT '预定信息结束时间',
    `is_deleted`  TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '删除状态(0-正常,1-删除)',
    `creator`     VARCHAR(64)          DEFAULT NULL COMMENT '创建人',
    `modifier`    VARCHAR(64)          DEFAULT NULL COMMENT '修改人',
    `create_time` DATETIME    NOT NULL DEFAULT now() COMMENT '创建时间',
    `modify_time` DATETIME    NOT NULL DEFAULT now() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code` (`code`),
    UNIQUE KEY `uk_phone` (`phone`),
    KEY           `idx_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '场馆表';

-- 场地表
CREATE TABLE `fc_court`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
    `place_id`    BIGINT(20) UNSIGNED NOT NULL COMMENT '场馆ID',
    `name`        VARCHAR(64) NOT NULL COMMENT '名称',
    `type`        TINYINT(1) UNSIGNED DEFAULT 1 COMMENT '类型(1-普通,2-VIP)',
    `is_open`     TINYINT(1) UNSIGNED DEFAULT 1 COMMENT '营业状态(0-未营业,1-营业)',
    `sort`        INT(4) DEFAULT 0 COMMENT '场地顺序',
    `is_deleted`  TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '删除状态(0-正常,1-删除)',
    `creator`     VARCHAR(64)          DEFAULT NULL COMMENT '创建人',
    `modifier`    VARCHAR(64)          DEFAULT NULL COMMENT '修改人',
    `create_time` DATETIME    NOT NULL DEFAULT now() COMMENT '创建时间',
    `modify_time` DATETIME    NOT NULL DEFAULT now() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY           `idx_place_id` (`place_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '场地表';

-- 预定表
CREATE TABLE `fc_booking`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
    `court_id`    BIGINT(20) UNSIGNED NOT NULL COMMENT '场地ID',
    `court_name`  VARCHAR(64) NOT NULL COMMENT '场地名称',
    `name`        VARCHAR(64) NOT NULL COMMENT '名称',
    `price`       BIGINT(20) UNSIGNED NOT NULL COMMENT '价格',
    `day`         DATETIME    NOT NULL COMMENT '日期',
    `sort`        INT(11) UNSIGNED NOT NULL COMMENT '顺序',
    `start_time`  VARCHAR(32) NOT NULL COMMENT '起始时间',
    `end_time`    VARCHAR(32) NOT NULL COMMENT '结束时间',
    `user_id`     BIGINT(20) UNSIGNED DEFAULT NULL COMMENT '预订人',
    `status`      TINYINT(2) UNSIGNED DEFAULT 1 COMMENT '状态(9-已售出,1-未售出,2-被占用,3-锁定,4-过期)',
    `remark`      VARCHAR(256)         DEFAULT NULL COMMENT '备注',
    `is_deleted`  TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '删除状态(0-正常,1-删除)',
    `creator`     VARCHAR(64)          DEFAULT NULL COMMENT '创建人',
    `modifier`    VARCHAR(64)          DEFAULT NULL COMMENT '修改人',
    `create_time` DATETIME    NOT NULL DEFAULT now() COMMENT '创建时间',
    `modify_time` DATETIME    NOT NULL DEFAULT now() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY           `idx_court_id` (`court_id`),
    KEY           `idx_user_id` (`user_id`),
    KEY           `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '预定表';

-- 场地规则表
CREATE TABLE `fc_court_rule`
(
    `id`              BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
    `place_id`        BIGINT(20) UNSIGNED NOT NULL COMMENT '场馆ID',
    `name`            VARCHAR(64) NOT NULL COMMENT '名称',
    `min_price`       BIGINT(20) UNSIGNED NOT NULL COMMENT '最小价格',
    `effective_start` DATETIME             DEFAULT NULL COMMENT '有效起始时间',
    `effective_end`   DATETIME             DEFAULT NULL COMMENT '有效结束时间',
    `is_effect`       TINYINT(1) UNSIGNED DEFAULT 1 COMMENT '规则生效(0-未生效,1-生效)',
    `is_deleted`      TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '删除状态(0-正常,1-删除)',
    `creator`         VARCHAR(64)          DEFAULT NULL COMMENT '创建人',
    `modifier`        VARCHAR(64)          DEFAULT NULL COMMENT '修改人',
    `create_time`     DATETIME    NOT NULL DEFAULT now() COMMENT '创建时间',
    `modify_time`     DATETIME    NOT NULL DEFAULT now() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY               `idx_place_id` (`place_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '场地规则表';

-- 场地规则明细表
CREATE TABLE `fc_court_rule_detail`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
    `place_id`      BIGINT(20) UNSIGNED NOT NULL COMMENT '场馆ID',
    `court_rule_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '场地规则ID',
    `price`         BIGINT(20) UNSIGNED NOT NULL COMMENT '价格',
    `week_indexes`  VARCHAR(32) NOT NULL COMMENT '周期范围',
    `start_time`    TINYINT(2) UNSIGNED DEFAULT NULL COMMENT '起始时刻',
    `end_time`      TINYINT(2) UNSIGNED DEFAULT NULL COMMENT '结束时刻',
    `is_deleted`    TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '删除状态(0-正常,1-删除)',
    `creator`       VARCHAR(64)          DEFAULT NULL COMMENT '创建人',
    `modifier`      VARCHAR(64)          DEFAULT NULL COMMENT '修改人',
    `create_time`   DATETIME    NOT NULL DEFAULT now() COMMENT '创建时间',
    `modify_time`   DATETIME    NOT NULL DEFAULT now() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY             `idx_place_id` (`place_id`),
    KEY             `idx_place_id` (`court_rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '场地规则明细表';

-- 场地规则关联表
CREATE TABLE `fc_court_rule_association`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
    `court_id`      BIGINT(20) UNSIGNED NOT NULL COMMENT '场地ID',
    `court_rule_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '场地规则ID',
    `is_deleted`    TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '删除状态(0-正常,1-删除)',
    `creator`       VARCHAR(64)       DEFAULT NULL COMMENT '创建人',
    `modifier`      VARCHAR(64)       DEFAULT NULL COMMENT '修改人',
    `create_time`   DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
    `modify_time`   DATETIME NOT NULL DEFAULT now() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_court_and_rule` (`court_rule_id`, `court_id`),
    KEY             `idx_court_id` (`court_id`),
    KEY             `idx_court_rule_id` (`court_rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '场地规则关联表';