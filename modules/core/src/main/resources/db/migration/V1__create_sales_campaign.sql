create table `sales_campaign`
(
    `id`                              bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `version`                         int unsigned    NOT NULL DEFAULT 0 COMMENT '버전',
    `name`                            varchar(100)    NOT NULL COMMENT '이름',
    `status`                          varchar(50)     NOT NULL COMMENT '상태',
    `start_at`                        datetime(6)     NOT NULL COMMENT '시작일시',
    `end_at`                          datetime(6)     NOT NULL COMMENT '종료일시',
    `enabled`                         char(1)         NOT NULL DEFAULT 'N' COMMENT '활성여부',
    `deleted`                         char(1)         NOT NULL DEFAULT 'N' COMMENT '삭제여부',
    `created_by`                      varchar(100)    DEFAULT NULL COMMENT '생성자',
    `created_at`                      datetime(6)     NOT NULL default CURRENT_TIMESTAMP(6) COMMENT '생성일시',
    `updated_by`                      varchar(100)    DEFAULT NULL COMMENT '수정자',
    `updated_at`                      datetime(6)     NOT NULL default CURRENT_TIMESTAMP(6) COMMENT '수정일시',
    PRIMARY KEY (`id`)
) collate = utf8mb4_general_ci comment = '세일즈캠페인';
create index `idx_sales_campaign_range` on `sales_campaign` (`start_at`, `end_at`) ALGORITHM=INPLACE LOCK=NONE;

create table `sales_campaign_history`
(
    `id`                              bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `sales_campaign_id`               bigint unsigned NOT NULL COMMENT '세일즈캠페인 ID',
    `description`                     varchar(255)    NOT NULL COMMENT '설명',
    `changes`                         json            NOT NULL COMMENT '변경사항',
    `deleted`                         char(1)         NOT NULL DEFAULT 'N' COMMENT '삭제여부',
    `created_by`                      varchar(100)    DEFAULT NULL COMMENT '생성자',
    `created_at`                      datetime(6)     NOT NULL default CURRENT_TIMESTAMP(6) COMMENT '생성일시',
    PRIMARY KEY (`id`)
) collate = utf8mb4_general_ci comment = '세일즈캠페인 히스토리';
create index `idx_sales_campaign_history_fk` on `sales_campaign_history` (`sales_campaign_id`) ALGORITHM=INPLACE LOCK=NONE;
