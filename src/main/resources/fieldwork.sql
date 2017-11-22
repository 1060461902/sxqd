/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50133
Source Host           : localhost:3306
Source Database       : fieldwork

Target Server Type    : MYSQL
Target Server Version : 50133
File Encoding         : 65001

Date: 2017-11-20 11:49:10

*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `approvalreport`
-- ----------------------------
DROP TABLE IF EXISTS `approvalreport`;
CREATE TABLE `approvalreport` (
  `id` varchar(255) NOT NULL,
  `mark` int(11) DEFAULT NULL COMMENT '1表示周报，2月报',
  `approvalTime` datetime DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  `userIdentity` varchar(255) DEFAULT NULL,
  `reportId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`userId`),
  KEY `report_id` (`reportId`),
  CONSTRAINT `report_id` FOREIGN KEY (`reportId`) REFERENCES `report` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of approvalreport
-- ----------------------------

-- ----------------------------
-- Table structure for `collectionrecruitment`
-- ----------------------------
DROP TABLE IF EXISTS `collectionrecruitment`;
CREATE TABLE `collectionrecruitment` (
  `id` varchar(255) NOT NULL,
  `recruitmentId` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `recruitement_id` (`recruitmentId`),
  KEY `user_iid` (`userId`),
  CONSTRAINT `recruitement_id` FOREIGN KEY (`recruitmentId`) REFERENCES `recruitmentinfo` (`id`),
  CONSTRAINT `user_iid` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collectionrecruitment
-- ----------------------------

-- ----------------------------
-- Table structure for `companyimage`
-- ----------------------------
DROP TABLE IF EXISTS `companyimage`;
CREATE TABLE `companyimage` (
  `id` varchar(255) NOT NULL,
  `companyId` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `company_idd` (`companyId`),
  CONSTRAINT `company_idd` FOREIGN KEY (`companyId`) REFERENCES `companyinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of companyimage
-- ----------------------------

-- ----------------------------
-- Table structure for `companyinfo`
-- ----------------------------
DROP TABLE IF EXISTS `companyinfo`;
CREATE TABLE `companyinfo` (
  `id` varchar(255) NOT NULL,
  `companyname` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL COMMENT '负责人id',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(255) DEFAULT NULL COMMENT '邮件',
  `type` varchar(255) DEFAULT NULL COMMENT '企业类型',
  `logo` varchar(255) DEFAULT NULL COMMENT '标志',
  `network` varchar(255) DEFAULT NULL COMMENT '网址',
  `size` varchar(255) DEFAULT NULL COMMENT '规模',
  `stage` varchar(255) DEFAULT NULL COMMENT '阶段',
  `slogans` varchar(255) DEFAULT NULL COMMENT '标语',
  `intruction` varchar(1255) DEFAULT NULL COMMENT '简介',
  `checked` tinyint(1) DEFAULT '0' COMMENT '0代表未审批，1代表已审批',
  `pass` tinyint(1) DEFAULT '0' COMMENT '0代表审批未通过，1代表审批通过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of companyinfo
-- ----------------------------
INSERT INTO `companyinfo` VALUES ('1', '杭州富远有限公司', '杭州市江干区学正街204号', '4', '2017-10-24 14:42:38', null, null, null, null, null, null, null, null, '1', '0');

-- ----------------------------
-- Table structure for `companymark`
-- ----------------------------
DROP TABLE IF EXISTS `companymark`;
CREATE TABLE `companymark` (
  `id` varchar(255) NOT NULL,
  `companyId` varchar(255) DEFAULT NULL,
  `mark` varchar(255) DEFAULT NULL COMMENT '企业标签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of companymark
-- ----------------------------

-- ----------------------------
-- Table structure for `consulting`
-- ----------------------------
DROP TABLE IF EXISTS `consulting`;
CREATE TABLE `consulting` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of consulting
-- ----------------------------

-- ----------------------------
-- Table structure for `dailycheck`
-- ----------------------------
DROP TABLE IF EXISTS `dailycheck`;
CREATE TABLE `dailycheck` (
  `id` varchar(11) NOT NULL,
  `userId` varchar(255) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `startCheck` tinyint(4) DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `endCheck` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dailycheck
-- ----------------------------

-- ----------------------------
-- Table structure for `dynamicapproval`
-- ----------------------------
DROP TABLE IF EXISTS `dynamicapproval`;
CREATE TABLE `dynamicapproval` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `imageUrl` varchar(255) DEFAULT NULL COMMENT '图片url',
  `detail` varchar(1255) DEFAULT NULL COMMENT '文章内容',
  `deleteTag` tinyint(1) DEFAULT '1' COMMENT '删除标志',
  `passing` tinyint(1) DEFAULT '0' COMMENT '0未通过',
  `dopassing` tinyint(1) DEFAULT NULL COMMENT '是否被审批',
  `user_id` varchar(255) DEFAULT NULL COMMENT '申请人id',
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dynamicapproval
-- ----------------------------

-- ----------------------------
-- Table structure for `image`
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `reportId` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '图片url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------

-- ----------------------------
-- Table structure for `monthstatistics`
-- ----------------------------
DROP TABLE IF EXISTS `monthstatistics`;
CREATE TABLE `monthstatistics` (
  `id` varchar(255) NOT NULL,
  `companyCount` varchar(255) DEFAULT NULL COMMENT '企业注册数',
  `recruitmentCount` varchar(255) DEFAULT NULL COMMENT '在招岗位数',
  `recruitmentPeople` varchar(255) DEFAULT NULL COMMENT '招聘人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monthstatistics
-- ----------------------------

-- ----------------------------
-- Table structure for `recruitmentinfo`
-- ----------------------------
DROP TABLE IF EXISTS `recruitmentinfo`;
CREATE TABLE `recruitmentinfo` (
  `id` varchar(255) NOT NULL,
  `companyID` varchar(255) DEFAULT NULL COMMENT '公司id',
  `post` varchar(255) DEFAULT NULL COMMENT '招聘岗位',
  `currentNumber` int(10) DEFAULT NULL COMMENT '现在招收人数',
  `totalNumber` int(10) DEFAULT NULL COMMENT '总招募人数',
  `postInfo` varchar(255) DEFAULT NULL COMMENT '职位信息',
  `checked` tinyint(1) DEFAULT '0' COMMENT '0代表未审批，1代表已审批',
  `pass` tinyint(1) DEFAULT '0' COMMENT '0代表审批未通过，1代表审批通过',
  `delete` tinyint(1) DEFAULT '0' COMMENT '标记删除，0-false，1-true',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `skillRequirement` varchar(255) DEFAULT NULL COMMENT '技能要求',
  `postTime` datetime DEFAULT NULL COMMENT '招聘时间',
  `remove` tinyint(1) DEFAULT '0' COMMENT '是否被撤下0-false,1-true',
  `forbidden` tinyint(1) DEFAULT '0' COMMENT '0-没有被禁用，1-禁用',
  `salary` varchar(255) DEFAULT NULL COMMENT '薪资待遇',
  `contact` varchar(255) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `recruitment` tinyint(1) DEFAULT '1' COMMENT '招聘状态，1-招聘中，0-招聘结束',
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recruitmentinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `report`
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  `projectName` varchar(255) DEFAULT NULL,
  `passingMark` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '存放地址',
  `statusId` int(11) DEFAULT NULL COMMENT '1表示周报2表示月报',
  `content` varchar(15000) DEFAULT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report
-- ----------------------------

-- ----------------------------
-- Table structure for `stuclub`
-- ----------------------------
DROP TABLE IF EXISTS `stuclub`;
CREATE TABLE `stuclub` (
  `id` varchar(255) NOT NULL,
  `clubName` varchar(255) DEFAULT NULL,
  `indentity` varchar(255) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `instruction` varchar(1255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stuclub
-- ----------------------------

-- ----------------------------
-- Table structure for `stuhonor`
-- ----------------------------
DROP TABLE IF EXISTS `stuhonor`;
CREATE TABLE `stuhonor` (
  `id` varchar(255) NOT NULL,
  `userId` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `instruction` varchar(255) DEFAULT NULL,
  `honorName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stuhonor
-- ----------------------------

-- ----------------------------
-- Table structure for `stuinfo`
-- ----------------------------
DROP TABLE IF EXISTS `stuinfo`;
CREATE TABLE `stuinfo` (
  `id` varchar(255) NOT NULL,
  `userID` varchar(255) DEFAULT NULL,
  `companyID` varchar(255) DEFAULT NULL COMMENT '公司联系人代表公司id学生根据状态表示想去的公司',
  `teacherID` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT '0' COMMENT '实习状态0未实习1申请中2待实习3实习中4已结业',
  `zipFile` tinyint(1) DEFAULT '0' COMMENT '0默认未归档1归档',
  `post` varchar(255) DEFAULT NULL COMMENT '实习岗位',
  `deleteTag` tinyint(1) DEFAULT '1' COMMENT '1存在 0删除 默认1',
  `sex` tinyint(1) DEFAULT '0' COMMENT '0男1女',
  `nation` varchar(255) DEFAULT NULL COMMENT '民族',
  `english` tinyint(1) DEFAULT NULL COMMENT '1熟练2一般',
  `fallYear` datetime DEFAULT NULL COMMENT '毕业年份',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stuinfo
-- ----------------------------
INSERT INTO `stuinfo` VALUES ('1', '2', '1', '2', '1', '0', 'UI设计师', '1', '0', null, null, '2017-11-06 18:12:43');
INSERT INTO `stuinfo` VALUES ('2', '3', '1', '2', '0', '0', '', '1', '0', '', null, '2017-11-19 18:12:39');
INSERT INTO `stuinfo` VALUES ('3', '4', '1', '2', '0', '0', null, '1', '0', null, null, '2017-11-15 18:12:54');

-- ----------------------------
-- Table structure for `stuproject`
-- ----------------------------
DROP TABLE IF EXISTS `stuproject`;
CREATE TABLE `stuproject` (
  `id` varchar(255) NOT NULL,
  `projectName` varchar(255) DEFAULT NULL,
  `identity` varchar(255) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `instruction` varchar(1255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userIdd` (`userId`),
  CONSTRAINT `userIdd` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stuproject
-- ----------------------------

-- ----------------------------
-- Table structure for `sturecruitment`
-- ----------------------------
DROP TABLE IF EXISTS `sturecruitment`;
CREATE TABLE `sturecruitment` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `recruitmentId` varchar(255) DEFAULT NULL,
  `passing` tinyint(4) DEFAULT NULL COMMENT '1审批通过，2审批未通过，3待审批',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sturecruitment
-- ----------------------------

-- ----------------------------
-- Table structure for `stuskill`
-- ----------------------------
DROP TABLE IF EXISTS `stuskill`;
CREATE TABLE `stuskill` (
  `id` varchar(255) NOT NULL,
  `skill` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stuskill
-- ----------------------------

-- ----------------------------
-- Table structure for `summary`
-- ----------------------------
DROP TABLE IF EXISTS `summary`;
CREATE TABLE `summary` (
  `id` varchar(255) NOT NULL COMMENT '参考report表格',
  `userId` varchar(255) DEFAULT NULL,
  `projectName` varchar(255) DEFAULT NULL,
  `passingMark` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `statusId` int(11) DEFAULT NULL,
  `content` varchar(15000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of summary
-- ----------------------------

-- ----------------------------
-- Table structure for `teacherinfo`
-- ----------------------------
DROP TABLE IF EXISTS `teacherinfo`;
CREATE TABLE `teacherinfo` (
  `id` varchar(255) NOT NULL,
  `userID` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0' COMMENT '0未指导，1指导中',
  PRIMARY KEY (`id`),
  KEY `userID` (`userID`),
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacherinfo
-- ----------------------------
INSERT INTO `teacherinfo` VALUES ('1', '5', '0');
INSERT INTO `teacherinfo` VALUES ('2', '6', '0');
INSERT INTO `teacherinfo` VALUES ('3', '7', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(1255) DEFAULT NULL COMMENT '密文',
  `phone` varchar(20) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `deleteTag` tinyint(1) DEFAULT '1' COMMENT '1存在0删除 默认1',
  `roleID` varchar(255) DEFAULT NULL COMMENT '1.2.3.4管理员，学生，教师，公司',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `forbidden` tinyint(1) DEFAULT '0' COMMENT '0-没有被禁用，1-禁用',
  PRIMARY KEY (`id`),
  KEY `user_ibfk_2` (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '15967180225', 'baby', '1', '1', '2017-10-23 19:39:53', '1');
INSERT INTO `user` VALUES ('2', '1511050124', 'e10adc3949ba59abbe56e057f20f883e', '13587797198', '詹韩峰', '1', '2', '2017-11-19 18:09:48', '1');
INSERT INTO `user` VALUES ('3', '11050146', 'e10adc3949ba59abbe56e057f20f883e', '13968754020', '白求恩', '1', '3', '2017-11-19 18:09:52', '1');
INSERT INTO `user` VALUES ('4', '849207436', 'e10adc3949ba59abbe56e057f20f883e', '18867714413', '范某', '1', '4', '2017-11-19 18:10:30', '1');
INSERT INTO `user` VALUES ('5', 'sawei', 'sdfsdfsa', '1567147836', 'hangsenjiang', '1', '3', '2017-11-16 21:43:42', '0');
INSERT INTO `user` VALUES ('6', 'jiasd', '234', '234213', 'df', '1', '3', '2017-11-16 21:43:42', '0');
INSERT INTO `user` VALUES ('7', 'sdf', '52435', '7483956', 'dsf', '1', '3', '2017-11-16 21:43:43', '0');
