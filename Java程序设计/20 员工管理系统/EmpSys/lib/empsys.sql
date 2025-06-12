/*
Navicat MySQL Data Transfer

Source Server         : mycon
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : empsys

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2021-11-19 12:19:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `base_employee`
-- ----------------------------
DROP TABLE IF EXISTS `base_employee`;
CREATE TABLE `base_employee` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(255) default NULL,
  `pass` varchar(255) default NULL,
  `utype` int(11) default NULL,
  `uname` varchar(20) default NULL,
  `sex` varchar(20) default NULL,
  `politics` varchar(255) default NULL,
  `dept` varchar(255) default NULL,
  `age` int(11) default NULL,
  `birth` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `phone` varchar(255) default NULL,
  `per` varchar(255) default NULL,
  `state` int(11) default NULL,
  `mark` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_employee
-- ----------------------------
INSERT INTO `base_employee` VALUES ('64', 'zhuang', '123456', '1', '庄连英', '女', '中共党员', '机关', '32', '', '', '15530609800', '', '0', '');
INSERT INTO `base_employee` VALUES ('65', 'zhang', '123456', '1', '张丽', '女', '中共党员', '机关', '0', '', '', '123456', '', '1', '');
INSERT INTO `base_employee` VALUES ('66', 'lili', '123456', '1', '李莉', '女', '中共党员', '机关', '0', '', '', '12345453234', '', '1', '');
INSERT INTO `base_employee` VALUES ('67', 'zhou', '123456', '1', '周然', '男', '中共党员', '机关', '45', '', '', '1234232312', '', '1', '');
INSERT INTO `base_employee` VALUES ('68', 'zou', '123456', '1', '邹世豪', '男', '中共党员', '机关', '32', '', '', '12343425434', '', '1', '');
INSERT INTO `base_employee` VALUES ('69', 'ma', '123456', '1', '马明', '男', '中共党员', '机关', '32', '', '', '13323234545', '', '1', '');
