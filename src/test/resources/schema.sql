/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : user_service

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-06-07 01:05:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `description` varchar(255) DEFAULT NULL,
  `authorities` text COMMENT '权限标识',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted` int(1) DEFAULT '0' COMMENT '删除标识',
  `deleted_at` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_accounts
-- ----------------------------
DROP TABLE IF EXISTS `user_accounts`;
CREATE TABLE `user_accounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `balance` bigint(20) DEFAULT '0' COMMENT '荟币余额',
  `type` char(16) DEFAULT NULL,
  `version` int(11) DEFAULT '0' COMMENT '修改版本',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_metadata
-- ----------------------------
DROP TABLE IF EXISTS `user_metadata`;
CREATE TABLE `user_metadata` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` char(13) DEFAULT NULL COMMENT '用户名',
  `password` char(255) DEFAULT NULL COMMENT '密码',
  `password_salt` char(32) DEFAULT NULL,
  `type` char(128) DEFAULT NULL COMMENT '账号类型',
  `nickname` varchar(16) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像url',
  `profile_id` int(11) DEFAULT NULL,
  `expired_at` timestamp NULL DEFAULT NULL COMMENT '账号过期时间',
  `credentials_expired_at` timestamp NULL DEFAULT NULL COMMENT '登录凭据过期时间',
  `locked` int(1) DEFAULT '0' COMMENT '账号是否锁定(1: 锁定, 0: 未锁定)',
  `enabled` int(1) DEFAULT '1' COMMENT '账号是否禁用(1: 可用, 0: 不可用)',
  `deleted` int(1) DEFAULT '0' COMMENT '删除标识',
  `deleted_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickname` (`nickname`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
