DROP TABLE IF EXISTS `m4g_real_tags`;
CREATE TABLE `m4g_real_tags`
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
  DEFAULT CHARACTER SET utf8mb4 COMMENT ='多标签管理';

DROP TABLE IF EXISTS `m4g_subs_real_tags`;
CREATE TABLE `m4g_subs_real_tags`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `subs_id` bigint,
    `tag_id` bigint,
    `owned_by` bigint COMMENT '所属业务员',
    PRIMARY KEY (`id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8mb4 COMMENT ='sub-tags-link';