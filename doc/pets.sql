--宠物数据库
CREATE DATABASE IF NOT EXISTS pets default charset utf8 COLLATE utf8_general_ci;
--宠物表
create table pets(
petId int auto_increment not null primary key,
petName varchar(20) not null COMMENT '宠物名',
price FLOAT DEFAULT 0 COMMENT '价格',
lifetime INT unsigned DEFAULT 0 COMMENT '寿命',
liked INT unsigned DEFAULT 0 COMMENT '点赞数',
collected INT unsigned DEFAULT 0 COMMENT '收藏数',
created datetime DEFAULT NULL COMMENT '创建时间',
updated datetime DEFAULT NULL COMMENT '更新时间'
);
--宠物用品
create table pet_supplies(
petSupplyId int auto_increment not null primary key,
title varchar(20) not null COMMENT '用品名',
price FLOAT DEFAULT 0 COMMENT '价格',
totalCount INT unsigned DEFAULT 0 COMMENT '商品总量',
image varchar(200) DEFAULT NULL COMMENT '商品图片',
status tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品状态，1-正常，2-下架，3-删除',
liked INT unsigned DEFAULT 0 COMMENT '点赞数',
collected INT unsigned DEFAULT 0 COMMENT '收藏数',
created datetime DEFAULT NULL COMMENT '创建时间',
updated datetime DEFAULT NULL COMMENT '更新时间'
);
--用户
create table users(
userId int auto_increment not null primary key,
userName varchar(20) not null  COMMENT '用户名',
-- password varchar(20) not null COMMENT '密码',
phone varchar(11) not null COMMENT '手机号码',
-- like varchar(200) DEFAULT NULL COMMENT '点赞过，存储对应的ID',
-- collection varchar(4) DEFAULT NULL COMMENT '已收藏，存储对应的ID',
created datetime DEFAULT NULL COMMENT '创建时间',
updated datetime DEFAULT NULL COMMENT '更新时间'
);
--用户与宠物中间表
create table pets_users(
userId INT COMMENT '用户ID',
petId INT COMMENT '宠物ID',
created datetime DEFAULT NULL COMMENT '创建时间',
updated datetime DEFAULT NULL COMMENT '更新时间',
FOREIGN KEY(userId) REFERENCES users(userId) ON DELETE CASCADE,
FOREIGN KEY(petId) REFERENCES pets(petId) ON DELETE CASCADE
);
--用户与宠物用品中间表
create table users_pet_supplies(
userId INT COMMENT '用户ID',
petSupplyId INT COMMENT '宠物用品ID',
created datetime DEFAULT NULL COMMENT '创建时间',
updated datetime DEFAULT NULL COMMENT '更新时间',
FOREIGN KEY(userId) REFERENCES users(userId) ON DELETE CASCADE,
FOREIGN KEY(petId) REFERENCES pets(petId) ON DELETE CASCADE
);