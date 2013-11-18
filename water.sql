/*
Navicat MySQL Data Transfer

Source Server         : sand
Source Server Version : 50158
Source Host           : localhost:3306
Source Database       : water

Target Server Type    : MYSQL
Target Server Version : 50158
File Encoding         : 65001

Date: 2013-11-18 18:16:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `base_log_dao`
-- ----------------------------
DROP TABLE IF EXISTS `base_log_dao`;
CREATE TABLE `base_log_dao` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dao_name` varchar(50) DEFAULT NULL COMMENT 'dao的名字',
  `sql` varchar(500) DEFAULT NULL COMMENT '执行的sql语句',
  `params` varchar(500) DEFAULT NULL COMMENT '参数',
  `create_time` datetime DEFAULT NULL COMMENT '执行时间',
  `user_id` int(11) DEFAULT NULL COMMENT '执行者ID',
  `other_key` varchar(200) DEFAULT NULL COMMENT '额外关键字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_log_dao
-- ----------------------------

-- ----------------------------
-- Table structure for `base_log_service`
-- ----------------------------
DROP TABLE IF EXISTS `base_log_service`;
CREATE TABLE `base_log_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `module_name` varchar(50) DEFAULT NULL COMMENT '模块',
  `service_name` varchar(50) DEFAULT NULL COMMENT '具体service的类名',
  `log_name` varchar(200) DEFAULT NULL COMMENT '记录的事件',
  `log_content` varchar(2000) DEFAULT NULL COMMENT '记录的内容',
  `create_time` datetime DEFAULT NULL COMMENT '执行时间',
  `user_id` int(11) DEFAULT NULL COMMENT '执行者ID',
  `other_key` varchar(200) DEFAULT NULL COMMENT '额外关键字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_log_service
-- ----------------------------

-- ----------------------------
-- Table structure for `sec_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sec_resource`;
CREATE TABLE `sec_resource` (
  `res_id` int(11) NOT NULL AUTO_INCREMENT,
  `res_name` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `app_type` tinyint(2) DEFAULT '1' COMMENT '所属项目',
  `app_menu` tinyint(2) DEFAULT '0' COMMENT '所属菜单',
  `url_ids` varchar(200) DEFAULT NULL COMMENT '资源包含的url',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态，1有效0删除',
  PRIMARY KEY (`res_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sec_resource
-- ----------------------------
INSERT INTO `sec_resource` VALUES ('1', 'r1', '0', '1', '1,2', '0');
INSERT INTO `sec_resource` VALUES ('2', 'r2', '1', '1', '1', '0');
INSERT INTO `sec_resource` VALUES ('3', 'url管理', '1', '1', '3,4,5,6', '1');
INSERT INTO `sec_resource` VALUES ('4', '资源管理', '1', '1', '7,8,9,10', '1');
INSERT INTO `sec_resource` VALUES ('5', '资源组管理', '1', '1', '11,12,13,14', '1');
INSERT INTO `sec_resource` VALUES ('6', 'test', '1', '2', '15,16,17', '1');

-- ----------------------------
-- Table structure for `sec_res_group`
-- ----------------------------
DROP TABLE IF EXISTS `sec_res_group`;
CREATE TABLE `sec_res_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(100) DEFAULT NULL COMMENT '组名',
  `owner_id` int(11) DEFAULT NULL COMMENT '所有者ID',
  `res_ids` varchar(2000) DEFAULT NULL COMMENT '包含的资源ID',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态，1有效0删除',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sec_res_group
-- ----------------------------
INSERT INTO `sec_res_group` VALUES ('1', '注册用户', '1', '5,6', '1');
INSERT INTO `sec_res_group` VALUES ('2', 'test', '1', '', '0');
INSERT INTO `sec_res_group` VALUES ('3', 'test2', '1', '3,4,6', '1');

-- ----------------------------
-- Table structure for `sec_url`
-- ----------------------------
DROP TABLE IF EXISTS `sec_url`;
CREATE TABLE `sec_url` (
  `url_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `url_name` varchar(50) DEFAULT NULL COMMENT 'URL名称',
  `url_method` tinyint(2) DEFAULT '1' COMMENT '提交方式(1get2post)',
  `url_path` varchar(255) DEFAULT NULL COMMENT '地址 /admin/product/index.action',
  `app_type` tinyint(2) DEFAULT '1' COMMENT '所属项目',
  `app_menu` tinyint(2) DEFAULT '0' COMMENT '所属菜单',
  `url_show` tinyint(2) DEFAULT '2' COMMENT '是否显示(1是2否)',
  `url_order` tinyint(2) DEFAULT '0' COMMENT '显示排序',
  PRIMARY KEY (`url_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of sec_url
-- ----------------------------
INSERT INTO `sec_url` VALUES ('3', 'url管理', '1', '/admin/secUrl/index.action', '1', '1', '1', '0');
INSERT INTO `sec_url` VALUES ('4', 'url编辑', '1', '/admin/secUrl/edit.action', '1', '1', '2', '0');
INSERT INTO `sec_url` VALUES ('5', 'url保存', '2', '/admin/secUrl/save.action', '1', '1', '2', '0');
INSERT INTO `sec_url` VALUES ('6', 'url删除', '2', '/admin/secUrl/destroy.actioin', '1', '1', '2', '0');
INSERT INTO `sec_url` VALUES ('7', '资源管理', '1', '/admin/secResource/index.action', '1', '1', '1', '0');
INSERT INTO `sec_url` VALUES ('8', '资源编辑', '1', '/admin/secResource/edit.action', '1', '1', '2', '0');
INSERT INTO `sec_url` VALUES ('9', '资源保存', '2', '/admin/secResource/save.action', '1', '1', '2', '0');
INSERT INTO `sec_url` VALUES ('10', '资源删除', '2', '/admin/secResource/destroy.action', '1', '1', '2', '0');
INSERT INTO `sec_url` VALUES ('11', '资源组管理', '1', '/admin/secResGroup/index.action', '1', '1', '1', '0');
INSERT INTO `sec_url` VALUES ('12', '资源组编辑', '1', '/admin/secResGroup/edit.action', '1', '1', '2', '0');
INSERT INTO `sec_url` VALUES ('13', '资源组保存', '2', '/admin/secResGroup/save.action', '1', '1', '2', '0');
INSERT INTO `sec_url` VALUES ('14', '资源组删除', '2', '/admin/secResGroup/destroy.action', '1', '1', '2', '0');
INSERT INTO `sec_url` VALUES ('15', 't1', '1', '/admin/test/t1.action', '1', '2', '1', '0');
INSERT INTO `sec_url` VALUES ('16', 't2', '1', '/admin/test/t2.action', '1', '2', '2', '0');
INSERT INTO `sec_url` VALUES ('17', 't3', '2', '/admin/test/t3.action', '1', '2', '2', '0');

-- ----------------------------
-- Table structure for `sec_user_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sec_user_resource`;
CREATE TABLE `sec_user_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `resource_id` int(11) DEFAULT NULL COMMENT '对应sa_resource表的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sec_user_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `user_base_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_base_info`;
CREATE TABLE `user_base_info` (
  `user_id` int(11) NOT NULL DEFAULT '0',
  `uname` varchar(100) DEFAULT NULL,
  `type` tinyint(2) DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_ip` varchar(50) DEFAULT NULL,
  `vip_time` datetime DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `job_title` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_base_info
-- ----------------------------

-- ----------------------------
-- Table structure for `user_login`
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login` (
  `user_id` int(11) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `upwd` varchar(50) DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL COMMENT '状态,1有效0删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_login
-- ----------------------------
INSERT INTO `user_login` VALUES ('1', 'admin', 'a', '1');
