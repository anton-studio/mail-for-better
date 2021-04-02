DROP TABLE IF EXISTS `m4g_subscriber`;
CREATE TABLE `m4g_subscriber`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `email` varchar(255) COMMENT '邮箱地址',
    `name` varchar(255) comment '名字',
    `owned_by` bigint COMMENT '所属业务员',
    `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    `delete_time` datetime(3) DEFAULT NULL,
    `order_num` int COMMENT '排序',
    PRIMARY KEY (`id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8mb4 COMMENT ='邮箱管理';

DROP TABLE IF EXISTS `m4g_tags`;
CREATE TABLE `m4g_tags`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `tag` varchar(255) COMMENT '标签',
    `owned_by` bigint COMMENT '所属业务员',
    `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    `delete_time` datetime(3) DEFAULT NULL,
    `order_num` int COMMENT '排序',
    PRIMARY KEY (`id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8mb4 COMMENT ='标签管理';

DROP TABLE IF EXISTS `m4g_templates`;
CREATE TABLE `m4g_templates`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `category` varchar(255) COMMENT '模版分类',
    `owned_by` bigint COMMENT '所属业务员',
    `public` boolean COMMENT '是否公开模版',
    `subject` varchar(255) COMMENT '邮箱标题',
    `body` text COMMENT '邮箱正文',
    `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    `delete_time` datetime(3) DEFAULT NULL,
    `order_num` int COMMENT '排序',
    PRIMARY KEY (`id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8mb4 COMMENT ='模版管理';

DROP TABLE IF EXISTS `m4g_campaigns`;
CREATE TABLE `m4g_campaigns`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `owned_by` bigint COMMENT '所属业务员',
    `subject` varchar(255) COMMENT '邮箱标题',
    `body` text COMMENT '邮箱正文',
    `from_email` varchar(255) COMMENT '发件人',
    `status` int COMMENT '1 未发送 2 已定时 3 正在发送 4 发送完成 5 已取消',
    `send_time` timestamp COMMENT '定时发送时间',
    `complaint_count` bigint COMMENT 'complaint 数量',
    `permanent_bounce_count` bigint COMMENT 'permanent bounce 数量',
    `transient_bounce_count` bigint COMMENT 'transient bounce 数量',
    `undetermined_bounce_count` bigint COMMENT 'undetermined bounce数量',
    `click_through_count` bigint COMMENT 'click through 数量',
    `open_count` bigint COMMENT 'open 数量',
    `total_sent_count` bigint COMMENT 'total sent 数量',
    `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    `delete_time` datetime(3) DEFAULT NULL,
    `order_num` int COMMENT '排序',
    PRIMARY KEY (`id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8mb4 COMMENT ='群发管理';

DROP TABLE IF EXISTS `m4g_campaign_emails`;
CREATE TABLE `m4g_campaign_emails`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `campaign_id` bigint COMMENT '活动ID',
    `email_id` bigint COMMENT '邮箱ID',
    `first_open` timestamp COMMENT '首次打开邮件时间',
    `tracking_id` bigint COMMENT 'tracking ID',
    `send_time` datetime(3) COMMENT '邮件发送时间',
    `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    PRIMARY KEY (`id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8mb4 COMMENT ='群发邮件统计';

DROP TABLE IF EXISTS `m4g_campaign_open_tracking`;
CREATE TABLE `m4g_campaign_open_tracking`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `tracking_id` bigint COMMENT 'tracking ID',
    `create_time` timestamp COMMENT '首次打开邮件时间',
    `country` varchar(255) COMMENT '国家',
    `region` varchar(255) COMMENT '区域',
    `city` varchar(255) COMMENT '城市',
    `ip_address` varchar(255) COMMENT 'IP 地址',
    `operating_system` varchar(255) COMMENT '操作系统',
    `device_type` varchar(255) COMMENT '设备类型',
    `device_vendor` varchar(255) COMMENT '设备厂商',
    `browser_name` varchar(255) COMMENT '浏览器名字',
    PRIMARY KEY (`id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8mb4 COMMENT ='群发打开历史统计';


