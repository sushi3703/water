/*
Navicat MySQL Data Transfer

Source Server         : sand
Source Server Version : 50158
Source Host           : localhost:3306
Source Database       : water

Target Server Type    : MYSQL
Target Server Version : 50158
File Encoding         : 65001

Date: 2013-11-15 18:21:57
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sec_resource
-- ----------------------------
INSERT INTO `sec_resource` VALUES ('1', 'r1', '0', '1', '1,2', '1');
INSERT INTO `sec_resource` VALUES ('2', 'r2', '1', '1', '1', '1');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sec_res_group
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of sec_url
-- ----------------------------
INSERT INTO `sec_url` VALUES ('1', 'test1', '1', '/admin/sa/t1.action', '0', '1', '2', '0');
INSERT INTO `sec_url` VALUES ('2', 'aaaaaaaaaaaa', '1', '/admin/subQuestion/index.action', '0', '2', '2', '0');

-- ----------------------------
-- Table structure for `sec_user`
-- ----------------------------
DROP TABLE IF EXISTS `sec_user`;
CREATE TABLE `sec_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '对应cl_user表的用户id',
  `givenName` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `displayName` varchar(30) DEFAULT NULL COMMENT '用户名',
  `app_ids` varchar(200) DEFAULT NULL COMMENT '有权访问的项目',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态，1有效，0删除',
  `department` varchar(200) DEFAULT NULL COMMENT '所属部门',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sec_user
-- ----------------------------

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
-- Table structure for `user_login`
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(200) DEFAULT NULL,
  `upwd` varchar(50) DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL,
  `type` tinyint(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_login
-- ----------------------------
INSERT INTO `user_login` VALUES ('1', 'admin', 'a', '1', '1', '2013-10-12 17:51:51');
