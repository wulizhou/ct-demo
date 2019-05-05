--宠物数据库
CREATE DATABASE IF NOT EXISTS pets default charset utf8 COLLATE utf8_general_ci;
--宠物表
create table pets(
petId int auto_increment not null primary key,
petType CHAR(1) NOT NULL DEFAULT '1' COMMENT '宠物类型，1-狗，2-猫，3-其它',
chineseName varchar2(20) not null COMMENT '宠物中文名',
englishName varchar2(20) not null COMMENT '宠物英文名',
original varchar2(20) not null COMMENT '起源',
ancestralHome varchar2(20) not null COMMENT '祖籍',
marketValue varchar2(20) COMMENT '市场价格',
lifetime varchar2(20) COMMENT '寿命',
height varchar2(20) not null COMMENT '身高',
weight varchar2(20) not null COMMENT '体重',
build varchar2(20) not null COMMENT '体形',
use varchar2(20) not null COMMENT '用途',
images varchar2(500) not null COMMENT '图片',
morphological text not null COMMENT '形态特征',
personality text not null COMMENT '性格特点',
maintenance text not null COMMENT '养护知识',
feed text not null COMMENT '喂食要点',
liked INT unsigned DEFAULT 0 COMMENT '点赞数',
collected INT unsigned DEFAULT 0 COMMENT '收藏数',
createTime datetime DEFAULT NULL COMMENT '创建时间',
updateTime datetime DEFAULT NULL COMMENT '更新时间'
);
--宠物用品
create table pet_supplies(
petSupplyId int auto_increment not null primary key,
title varchar2(20) not null COMMENT '用品名',
price FLOAT DEFAULT 0 COMMENT '价格',
totalCount INT unsigned DEFAULT 0 COMMENT '商品总量',
image varchar2(500) DEFAULT NULL COMMENT '商品图片',
status CHAR(1) NOT NULL DEFAULT '1' COMMENT '商品状态，1-正常，2-下架，3-删除',
liked INT unsigned DEFAULT 0 COMMENT '点赞数',
collected INT unsigned DEFAULT 0 COMMENT '收藏数',
createTime datetime DEFAULT NULL COMMENT '创建时间',
updateTime datetime DEFAULT NULL COMMENT '更新时间'
);
--用户
create table users(
userId int auto_increment not null primary key,
userName varchar2(20) not null  COMMENT '用户名',
avatar varchar2(50) not null  COMMENT '用户头像',
-- password varchar2(20) not null COMMENT '密码',
phone varchar2(11) not null COMMENT '手机号码',
-- like varchar2(200) DEFAULT NULL COMMENT '点赞过，存储对应的ID',
-- collection varchar2(4) DEFAULT NULL COMMENT '已收藏，存储对应的ID',
createTime datetime DEFAULT NULL COMMENT '创建时间',
updateTime datetime DEFAULT NULL COMMENT '更新时间'
);
--用户与宠物中间表
create table pets_users(
userId INT COMMENT '用户ID',
petId INT COMMENT '宠物ID',
createTime datetime DEFAULT NULL COMMENT '创建时间',
updateTime datetime DEFAULT NULL COMMENT '更新时间'
);
--用户与宠物用品中间表
create table users_pet_supplies(
userId INT COMMENT '用户ID',
petSupplyId INT COMMENT '宠物用品ID',
createTime datetime DEFAULT NULL COMMENT '创建时间',
updateTime datetime DEFAULT NULL COMMENT '更新时间'
);

############
INSERT into pets VALUES ('1',)