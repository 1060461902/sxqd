/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : fieldwork

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001


Date: 2017-11-30 12:17:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for approve_report
-- ----------------------------
DROP TABLE IF EXISTS `approve_report`;
CREATE TABLE `approve_report` (
  `id` varchar(255) NOT NULL,
  `mark` int(11) DEFAULT NULL COMMENT '1表示周报，2月报',
  `approval_time` datetime DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `user_id` varchar(255) NOT NULL,
  `user_identity` varchar(255) DEFAULT NULL,
  `report_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `report_id` (`report_id`) USING BTREE,
  CONSTRAINT `approve_report_ibfk_1` FOREIGN KEY (`report_id`) REFERENCES `report` (`id`),
  CONSTRAINT `approve_report_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of approve_report
-- ----------------------------

-- ----------------------------
-- Table structure for collection_recruitment
-- ----------------------------
DROP TABLE IF EXISTS `collection_recruitment`;
CREATE TABLE `collection_recruitment` (
  `id` varchar(255) NOT NULL,
  `recruitment_id` varchar(255) NOT NULL,
  `student_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `recruitement_id` (`recruitment_id`) USING BTREE,
  KEY `student_id` (`student_id`),
  CONSTRAINT `collection_recruitment_ibfk_1` FOREIGN KEY (`recruitment_id`) REFERENCES `recruitment` (`id`),
  CONSTRAINT `collection_recruitment_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collection_recruitment
-- ----------------------------

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` varchar(255) NOT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) NOT NULL COMMENT '负责人id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` varchar(255) DEFAULT NULL COMMENT '企业类型',
  `logo` varchar(255) DEFAULT NULL COMMENT '标志',
  `network` varchar(255) DEFAULT NULL COMMENT '网址',
  `size` varchar(255) DEFAULT NULL COMMENT '规模',
  `stage` varchar(255) DEFAULT NULL COMMENT '阶段',
  `slogans` varchar(255) DEFAULT NULL COMMENT '标语',
  `intruction` varchar(1255) DEFAULT NULL COMMENT '简介',
  `checked` tinyint(1) DEFAULT '0' COMMENT '0代表未审批，1代表已审批',
  `pass` tinyint(1) DEFAULT '0' COMMENT '0代表审批未通过，1代表审批通过',
  `licence` varchar(255) DEFAULT NULL,
  `tax_registration` varchar(255) DEFAULT NULL,
  `organization_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `company_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------

INSERT INTO `company` VALUES ('1', '杭州新东方有限公司', '杭州市江干区学正街204号', '4', '2017-11-29 20:03:37', '135@163.com', '餐饮', '1', 'www.baidu.com', '20人不到', '初创阶段', '粒粒皆辛苦', '公司主要从事餐饮方面', '1', '1', null, null, null);
INSERT INTO `company` VALUES ('2', '天娱传媒', '杭州上城区501号', '8', '2017-11-29 20:03:40', '789@qq.com', '广告', '2', 'www.123.com', '200多人', '大型公司', '好好炒作', '公司喜欢打明星', '1', '1', null, null, null);
INSERT INTO `company` VALUES ('3', '陈氏豆腐乳', '天津一号', '9', '2017-11-25 14:51:40', '146@email.com', '食品', '351', 'www.hao.com', '50多人', '中等公司', '二和', '产品好吃不臭', '1', '0', null, null, null);

-- ----------------------------
-- Table structure for company_image
-- ----------------------------
DROP TABLE IF EXISTS `company_image`;
CREATE TABLE `company_image` (
  `id` varchar(255) NOT NULL,
  `company_id` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`) USING BTREE,
  CONSTRAINT `company_image_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company_image
-- ----------------------------
INSERT INTO `company_image` VALUES ('1', '1', 'enen');
INSERT INTO `company_image` VALUES ('2', '2', '2');
INSERT INTO `company_image` VALUES ('3', '2', '3');

-- ----------------------------
-- Table structure for company_mark
-- ----------------------------
DROP TABLE IF EXISTS `company_mark`;
CREATE TABLE `company_mark` (
  `id` varchar(255) NOT NULL,
  `company_id` varchar(255) NOT NULL,
  `mark` varchar(255) DEFAULT NULL COMMENT '企业标签',
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`),
  CONSTRAINT `company_mark_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company_mark
-- ----------------------------
INSERT INTO `company_mark` VALUES ('1', '1', 'sd');
INSERT INTO `company_mark` VALUES ('2', '2', 'duidui ');
INSERT INTO `company_mark` VALUES ('3', '2', 'frfr');

-- ----------------------------
-- Table structure for consult
-- ----------------------------
DROP TABLE IF EXISTS `consult`;
CREATE TABLE `consult` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of consult
-- ----------------------------

-- ----------------------------
-- Table structure for daily_check
-- ----------------------------
DROP TABLE IF EXISTS `daily_check`;
CREATE TABLE `daily_check` (
  `id` varchar(11) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `start_check` tinyint(4) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `end_check` tinyint(4) DEFAULT NULL,
  `company_id` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `daily_check_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of daily_check
-- ----------------------------

-- ----------------------------
-- Table structure for dynamic_approve
-- ----------------------------
DROP TABLE IF EXISTS `dynamic_approve`;
CREATE TABLE `dynamic_approve` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `image_url` varchar(255) DEFAULT NULL COMMENT '图片url',
  `detail` varchar(1255) DEFAULT NULL COMMENT '文章内容',
  `delete_tag` tinyint(1) DEFAULT '1' COMMENT '删除标志',
  `passing` tinyint(1) DEFAULT '0' COMMENT '0未通过',
  `dopassing` tinyint(1) DEFAULT NULL COMMENT '是否被审批',
  `user_id` varchar(255) NOT NULL COMMENT '申请人id',
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `dynamic_approve_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dynamic_approve
-- ----------------------------

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `report_id` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '图片url',
  PRIMARY KEY (`id`),
  KEY `report_id` (`report_id`),
  CONSTRAINT `image_ibfk_1` FOREIGN KEY (`report_id`) REFERENCES `report` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------

-- ----------------------------
-- Table structure for month_statistics
-- ----------------------------
DROP TABLE IF EXISTS `month_statistics`;
CREATE TABLE `month_statistics` (
  `id` varchar(255) NOT NULL,
  `company_count` varchar(255) DEFAULT NULL COMMENT '企业注册数',
  `recruitment_count` varchar(255) DEFAULT NULL COMMENT '在招岗位数',
  `recruitment_number` varchar(255) DEFAULT NULL COMMENT '招聘人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of month_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for recruitment
-- ----------------------------
DROP TABLE IF EXISTS `recruitment`;
CREATE TABLE `recruitment` (
  `id` varchar(255) NOT NULL,
  `company_id` varchar(255) NOT NULL COMMENT '公司id',
  `post` varchar(255) DEFAULT NULL COMMENT '招聘岗位',
  `current_number` int(10) DEFAULT NULL COMMENT '现在招收人数',
  `total_number` int(10) DEFAULT NULL COMMENT '总招募人数',
  `post_info` varchar(255) DEFAULT NULL COMMENT '职位信息',
  `checked` tinyint(1) DEFAULT '0' COMMENT '0代表未审批，1代表已审批',
  `pass` tinyint(1) DEFAULT '0' COMMENT '0代表审批未通过，1代表审批通过',
  `delete_tag` tinyint(1) DEFAULT '1' COMMENT '标记删除，0-false，1-true',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `skill_require` varchar(255) DEFAULT NULL COMMENT '技能要求',
  `end_time` varchar(255) DEFAULT NULL COMMENT '招聘时间',
  `start_time` varchar(255) DEFAULT NULL COMMENT '招聘时间',
  `remove` tinyint(1) DEFAULT '0' COMMENT '是否被撤下0-false,1-true',
  `forbidden` tinyint(1) DEFAULT '0' COMMENT '0-没有被禁用，1-禁用',
  `salary` varchar(255) DEFAULT NULL COMMENT '薪资待遇',
  `contact` varchar(255) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `recruit_status` tinyint(1) DEFAULT '1' COMMENT '招聘状态，1-招聘中，0-招聘结束',
  `release_time` datetime DEFAULT NULL COMMENT '审批通过时间',
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`),
  CONSTRAINT `recruitment_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recruitment
-- ----------------------------

INSERT INTO `recruitment` VALUES ('1', '1', '厨师', '5', '8', '要新东方毕业', '0', '0', '1', '江干区钱江湾生活区', '刀工5级', '11.24-11.30', '0', '0', '50000', '金秀波', '13587794512', '815027104@qq.com', '1', '1');
INSERT INTO `recruitment` VALUES ('2', '2', '码畜', '1', '4', '要每天1500代码行', '0', '0', '1', '3', '代码8级', '11.12-11.18', '0', '0', '600000', '詹韩峰', '15967180225', '156@163.com', '1', '2');

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) NOT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `passing_mark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '存放地址',
  `status_id` int(11) DEFAULT NULL COMMENT '1表示周报2表示月报',
  `content` varchar(15000) DEFAULT NULL,
  KEY `id` (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `company_id` varchar(255) NOT NULL COMMENT '公司联系人代表公司id学生根据状态表示想去的公司',
  `teacher_id` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT '0' COMMENT '实习状态0未实习1申请中2待实习3实习中4已结业',
  `zip_file` tinyint(1) DEFAULT '0' COMMENT '0默认未归档1归档',
  `post` varchar(255) DEFAULT NULL COMMENT '实习岗位',
  `delete_tag` tinyint(1) DEFAULT '1' COMMENT '1存在 0删除 默认1',
  `sex` tinyint(1) DEFAULT '0' COMMENT '0男1女',
  `nation` varchar(255) DEFAULT NULL COMMENT '民族',
  `english` tinyint(1) DEFAULT NULL COMMENT '1熟练2一般',
  `major` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL COMMENT '年级（例如2015级...）',
  `clss` varchar(255) DEFAULT NULL COMMENT '班级',
  `birth_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `company_id` (`company_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `student_ibfk_3` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '2', '1', '2', '1', '0', 'UI设计师', '1', '0', null, null, null, '2017-11-06 18:12:43', null, null);
INSERT INTO `student` VALUES ('2', '3', '1', '2', '0', '0', '', '1', '0', '', null, null, '2017-11-19 18:12:39', null, null);
INSERT INTO `student` VALUES ('3', '4', '1', '2', '0', '0', null, '1', '0', null, null, null, '2017-11-15 18:12:54', null, null);

-- ----------------------------
-- Table structure for student_club
-- ----------------------------
DROP TABLE IF EXISTS `student_club`;
CREATE TABLE `student_club` (
  `id` varchar(255) NOT NULL,
  `club_name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `instruction` varchar(1255) DEFAULT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `student_club_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_club
-- ----------------------------

-- ----------------------------
-- Table structure for student_honor
-- ----------------------------
DROP TABLE IF EXISTS `student_honor`;
CREATE TABLE `student_honor` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `time` datetime DEFAULT NULL,
  `instruction` varchar(255) DEFAULT NULL,
  `honor_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `student_honor_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_honor
-- ----------------------------

-- ----------------------------
-- Table structure for student_project
-- ----------------------------
DROP TABLE IF EXISTS `student_project`;
CREATE TABLE `student_project` (
  `id` varchar(255) NOT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL COMMENT '担任的角色',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `instruction` varchar(1255) DEFAULT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `student_project_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_project
-- ----------------------------

-- ----------------------------
-- Table structure for student_recruitment
-- ----------------------------
DROP TABLE IF EXISTS `student_recruitment`;
CREATE TABLE `student_recruitment` (
  `id` varchar(255) NOT NULL,
  `student_id` varchar(255) NOT NULL,
  `recruitment_id` varchar(255) NOT NULL,
  `passing` tinyint(4) DEFAULT NULL COMMENT '1审批通过，2审批未通过，3待审批',
  PRIMARY KEY (`id`),
  KEY `recruitment_id` (`recruitment_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `student_recruitment_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `student_recruitment_ibfk_2` FOREIGN KEY (`recruitment_id`) REFERENCES `recruitment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_recruitment
-- ----------------------------

-- ----------------------------
-- Table structure for student_skill
-- ----------------------------
DROP TABLE IF EXISTS `student_skill`;
CREATE TABLE `student_skill` (
  `id` varchar(255) NOT NULL,
  `skill` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) NOT NULL,
  KEY `user_id` (`user_id`),
  CONSTRAINT `student_skill_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_skill
-- ----------------------------

-- ----------------------------
-- Table structure for summary
-- ----------------------------
DROP TABLE IF EXISTS `summary`;
CREATE TABLE `summary` (
  `id` varchar(255) NOT NULL COMMENT '参考report表格',
  `user_id` varchar(255) NOT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `passing_mark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  `content` varchar(15000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `summary_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of summary
-- ----------------------------

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `major` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '5', '1', null);
INSERT INTO `teacher` VALUES ('2', '6', '0', null);
INSERT INTO `teacher` VALUES ('3', '7', '0', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(1255) DEFAULT NULL COMMENT '密文',
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `delete_tag` tinyint(1) DEFAULT '1' COMMENT '1存在0删除 默认1',
  `role_id` varchar(255) DEFAULT NULL COMMENT '1.2.3.4管理员，学生，教师，公司',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `forbidden` tinyint(1) DEFAULT '0' COMMENT '0-没有被禁用，1-禁用',
  `image` varchar(255) DEFAULT NULL COMMENT '图片url',
  PRIMARY KEY (`id`),
  KEY `user_ibfk_2` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

INSERT INTO `user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '15967180225', 'baby', '1', '1', '2017-10-23 19:39:53', '1');
INSERT INTO `user` VALUES ('2', '1511050124', 'e10adc3949ba59abbe56e057f20f883e', '13587797198', '詹韩峰', '1', '2', '2017-11-19 18:09:48', '1');
INSERT INTO `user` VALUES ('3', '11050146', 'e10adc3949ba59abbe56e057f20f883e', '13968754020', '白求恩', '1', '3', '2017-11-19 18:09:52', '1');
INSERT INTO `user` VALUES ('4', '849207436', 'e10adc3949ba59abbe56e057f20f883e', '18867714413', '范某', '1', '4', '2017-11-19 18:10:30', '1');
INSERT INTO `user` VALUES ('5', 'sawei', 'sdfsdfsa', '1567147836', 'hangsenjiang', '1', '3', '2017-11-25 13:35:00', '1');
INSERT INTO `user` VALUES ('6', 'jiasd', '234', '234213', 'df', '1', '3', '2017-11-25 13:35:00', '1');
INSERT INTO `user` VALUES ('7', 'sdf', '52435', '7483956', 'dsf', '1', '3', '2017-11-25 13:35:01', '1');
INSERT INTO `user` VALUES ('8', 'tianyu', '21232f297a57a5a743894a0e4a801fc3', '13568779461', '天宇', '1', '4', '2017-11-25 13:36:56', '1');
INSERT INTO `user` VALUES ('9', 'fuckchen', '21232f297a57a5a743894a0e4a801fc3', '110', '陈狗', '1', '4', '2017-11-25 13:37:26', '1');

-- ----------------------------
-- View structure for company_view
-- ----------------------------
DROP VIEW IF EXISTS `company_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `company_view` AS select `company`.`id` AS `id`,`company`.`company_name` AS `company_name`,`company`.`address` AS `address`,`company`.`user_id` AS `user_id`,`company`.`create_time` AS `create_time`,`company`.`email` AS `email`,`company`.`type` AS `type`,`company`.`logo` AS `logo`,`company`.`network` AS `network`,`company`.`size` AS `size`,`company`.`stage` AS `stage`,`company`.`slogans` AS `slogans`,`company`.`intruction` AS `intruction`,`company`.`checked` AS `checked`,`company`.`pass` AS `pass`,`user`.`user_name` AS `user_name`,`user`.`password` AS `password`,`user`.`phone` AS `phone`,`user`.`nick_name` AS `nick_name`,`user`.`delete_tag` AS `delete_tag`,`user`.`role_id` AS `role_id`,`user`.`forbidden` AS `forbidden` from (`company` join `user` on((`company`.`user_id` = `user`.`id`))) where (`user`.`role_id` = 4) ;
