/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : db_zhibo

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 06/05/2021 09:46:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_announce
-- ----------------------------
DROP TABLE IF EXISTS `tb_announce`;
CREATE TABLE `tb_announce` (
  `aid` int NOT NULL AUTO_INCREMENT COMMENT '通告，公告板',
  `title` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '详细内容',
  `update_time` datetime DEFAULT NULL COMMENT '发布时间',
  `uid` int NOT NULL COMMENT '发布人',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_announce
-- ----------------------------
BEGIN;
INSERT INTO `tb_announce` VALUES (1, 'test', 'eee', '2021-04-01 11:29:35', 41);
INSERT INTO `tb_announce` VALUES (2, '最新通告！', '通知：1112312', '2021-04-02 15:17:10', 41);
INSERT INTO `tb_announce` VALUES (3, 'z123', '555', '2021-04-06 09:44:11', 41);
INSERT INTO `tb_announce` VALUES (4, '新标题', '新的内容', '2021-04-08 17:45:51', 43);
INSERT INTO `tb_announce` VALUES (6, '新的通知', '通知内容，', '2021-04-14 15:13:45', 68);
INSERT INTO `tb_announce` VALUES (7, '测试通知', '测试内容123', '2021-04-29 11:01:39', 43);
INSERT INTO `tb_announce` VALUES (8, '5.1假期安排', '大家玩的愉快', '2021-05-02 11:49:51', 41);
COMMIT;

-- ----------------------------
-- Table structure for tb_class_name
-- ----------------------------
DROP TABLE IF EXISTS `tb_class_name`;
CREATE TABLE `tb_class_name` (
  `id` tinyint NOT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '班级名称/教师职称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_class_name
-- ----------------------------
BEGIN;
INSERT INTO `tb_class_name` VALUES (1, '计科211');
INSERT INTO `tb_class_name` VALUES (2, '软工212');
INSERT INTO `tb_class_name` VALUES (3, '计网213');
INSERT INTO `tb_class_name` VALUES (4, '计应214');
COMMIT;

-- ----------------------------
-- Table structure for tb_counselor
-- ----------------------------
DROP TABLE IF EXISTS `tb_counselor`;
CREATE TABLE `tb_counselor` (
  `cid` int NOT NULL AUTO_INCREMENT,
  `stu_uid` int DEFAULT NULL,
  `teach_uid` int DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_counselor
-- ----------------------------
BEGIN;
INSERT INTO `tb_counselor` VALUES (1, 1, 41);
INSERT INTO `tb_counselor` VALUES (2, 2, 41);
INSERT INTO `tb_counselor` VALUES (3, 3, 41);
INSERT INTO `tb_counselor` VALUES (4, 4, 41);
INSERT INTO `tb_counselor` VALUES (5, 5, 41);
INSERT INTO `tb_counselor` VALUES (6, 6, 41);
INSERT INTO `tb_counselor` VALUES (7, 7, 41);
INSERT INTO `tb_counselor` VALUES (8, 8, 41);
INSERT INTO `tb_counselor` VALUES (9, 9, 41);
INSERT INTO `tb_counselor` VALUES (10, 10, 41);
INSERT INTO `tb_counselor` VALUES (11, 11, 42);
INSERT INTO `tb_counselor` VALUES (12, 12, 42);
INSERT INTO `tb_counselor` VALUES (13, 13, 42);
INSERT INTO `tb_counselor` VALUES (14, 14, 42);
INSERT INTO `tb_counselor` VALUES (15, 15, 42);
INSERT INTO `tb_counselor` VALUES (16, 16, 42);
INSERT INTO `tb_counselor` VALUES (17, 17, 42);
INSERT INTO `tb_counselor` VALUES (18, 18, 42);
INSERT INTO `tb_counselor` VALUES (19, 19, 42);
INSERT INTO `tb_counselor` VALUES (20, 20, 42);
INSERT INTO `tb_counselor` VALUES (21, 21, 43);
INSERT INTO `tb_counselor` VALUES (22, 22, 43);
INSERT INTO `tb_counselor` VALUES (23, 23, 43);
INSERT INTO `tb_counselor` VALUES (24, 24, 43);
INSERT INTO `tb_counselor` VALUES (25, 25, 43);
INSERT INTO `tb_counselor` VALUES (26, 26, 43);
INSERT INTO `tb_counselor` VALUES (27, 27, 43);
INSERT INTO `tb_counselor` VALUES (28, 28, 43);
INSERT INTO `tb_counselor` VALUES (29, 29, 43);
INSERT INTO `tb_counselor` VALUES (30, 30, 43);
INSERT INTO `tb_counselor` VALUES (31, 31, 44);
INSERT INTO `tb_counselor` VALUES (32, 32, 44);
INSERT INTO `tb_counselor` VALUES (33, 33, 44);
INSERT INTO `tb_counselor` VALUES (34, 34, 44);
INSERT INTO `tb_counselor` VALUES (35, 35, 44);
INSERT INTO `tb_counselor` VALUES (36, 36, 44);
INSERT INTO `tb_counselor` VALUES (37, 37, 44);
INSERT INTO `tb_counselor` VALUES (38, 38, 44);
INSERT INTO `tb_counselor` VALUES (39, 39, 44);
INSERT INTO `tb_counselor` VALUES (40, 40, 44);
INSERT INTO `tb_counselor` VALUES (43, 64, 44);
INSERT INTO `tb_counselor` VALUES (44, 65, 63);
INSERT INTO `tb_counselor` VALUES (45, 66, 63);
COMMIT;

-- ----------------------------
-- Table structure for tb_dept
-- ----------------------------
DROP TABLE IF EXISTS `tb_dept`;
CREATE TABLE `tb_dept` (
  `id` tinyint NOT NULL COMMENT '系部id',
  `name` varchar(20) DEFAULT NULL COMMENT '系部名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_dept
-- ----------------------------
BEGIN;
INSERT INTO `tb_dept` VALUES (1, '人工智能学院');
INSERT INTO `tb_dept` VALUES (2, '信息技术系');
COMMIT;

-- ----------------------------
-- Table structure for tb_disser_stu
-- ----------------------------
DROP TABLE IF EXISTS `tb_disser_stu`;
CREATE TABLE `tb_disser_stu` (
  `did` int NOT NULL COMMENT '论文题目id',
  `uid` int DEFAULT NULL COMMENT '选题学生uid',
  PRIMARY KEY (`did`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_disser_stu
-- ----------------------------
BEGIN;
INSERT INTO `tb_disser_stu` VALUES (6, 64);
INSERT INTO `tb_disser_stu` VALUES (7, 3);
INSERT INTO `tb_disser_stu` VALUES (11, 43);
INSERT INTO `tb_disser_stu` VALUES (12, 2);
INSERT INTO `tb_disser_stu` VALUES (13, 1);
INSERT INTO `tb_disser_stu` VALUES (21, 5);
INSERT INTO `tb_disser_stu` VALUES (22, 6);
COMMIT;

-- ----------------------------
-- Table structure for tb_dissertation
-- ----------------------------
DROP TABLE IF EXISTS `tb_dissertation`;
CREATE TABLE `tb_dissertation` (
  `did` int NOT NULL AUTO_INCREMENT COMMENT '论文id',
  `name` varchar(64) NOT NULL COMMENT '论文题目',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '论文路径',
  `uid` int NOT NULL COMMENT '出题老师uid',
  `status` tinyint(1) DEFAULT '0' COMMENT '-0 选题进行中 -1 已完成选题\n *     -2 初审中 -3 初审退回 -4 初审通过\n *     -5 中期鉴定中 -6 中期鉴定退回  -7 中期鉴定通过\n *     -8 最终审核中 -9 最终审核退回 -10 最终审核通过-0 选题进行中 -1 已完成选题\n *     -2 初审中 -3 初审退回 -4 初审通过\n *     -5 中期鉴定中 -6 中期鉴定退回  -7 中期鉴定通过\n *     -8 最终审核中 -9 最终审核退回 -10 最终审核通过',
  `audit_path` varchar(255) DEFAULT NULL COMMENT '提交的审核文档路径',
  `advice` varchar(255) DEFAULT NULL COMMENT '教师意见',
  PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_dissertation
-- ----------------------------
BEGIN;
INSERT INTO `tb_dissertation` VALUES (2, 'test2', '2021/04/01/85004ce9-6cbf-4e9a-bf91-1dd3c2192285.pdf', 42, 0, NULL, NULL);
INSERT INTO `tb_dissertation` VALUES (5, '李老师2', '2021/04/02/21bdc044-c90a-468c-9c85-3b1ea1176a19.pdf', 42, 1, NULL, NULL);
INSERT INTO `tb_dissertation` VALUES (6, 'z2', '2021/04/02/a9975ec1-2197-4566-9635-13be9ed5f54a.pdf', 41, 1, NULL, NULL);
INSERT INTO `tb_dissertation` VALUES (7, 'z1', '2021/04/06/be33f639-713a-4fa8-8f30-ab65356761ae.pdf', 41, 4, '2021/05/02/418f1a89-aaf3-46e0-99cd-8ed7469b2bfc.pdf', '');
INSERT INTO `tb_dissertation` VALUES (8, 'w3', '2021/04/06/1fc4e8a8-fe34-4fb4-90a9-a2885cb804fc.pdf', 43, 1, NULL, NULL);
INSERT INTO `tb_dissertation` VALUES (9, '新论文', '2021/04/08/788a7a78-f6ed-491c-bc05-1753d6d66653.pdf', 43, 0, NULL, NULL);
INSERT INTO `tb_dissertation` VALUES (10, '5212', '2021/04/09/14320bba-18c2-40e3-8529-8d4e0e72ea4f.pdf', 43, 0, NULL, NULL);
INSERT INTO `tb_dissertation` VALUES (12, 'z4', '2021/04/09/2308c136-a8db-443a-8710-4f26c8c9efb3.pdf', 41, 6, '2021/05/02/9733142b-9547-4f55-af92-9b38a6b9c8c9.pdf', '');
INSERT INTO `tb_dissertation` VALUES (13, 'z5', '2021/04/09/d466edcf-acc4-4c32-a305-b3f23131cfc4.pdf', 41, 0, '2021/05/02/9c0672e7-1c95-4c62-8768-59820d6fa8f3.pdf', '');
INSERT INTO `tb_dissertation` VALUES (14, '吴老师的题', '2021/04/14/24585407-ddc7-4d21-b007-764c2fa8a291.pdf', 63, 0, NULL, NULL);
INSERT INTO `tb_dissertation` VALUES (15, '李老师的选题', '2021/04/14/3827ea76-c1fd-4ccb-9f2b-497e394a236d.pdf', 68, 0, NULL, NULL);
INSERT INTO `tb_dissertation` VALUES (16, '1234', '2021/04/29/2fa2bf68-6b9d-4e71-a9b3-91ad121a7f33.pdf', 43, 0, NULL, NULL);
INSERT INTO `tb_dissertation` VALUES (17, '111', '2021/04/29/40fcb091-1f5b-430c-ad37-5945078f5f20.pdf', 43, 0, NULL, NULL);
INSERT INTO `tb_dissertation` VALUES (18, '34', '2021/04/29/b0487708-4cf1-46da-b0cf-7425aff8fb44.pdf', 43, 0, NULL, NULL);
INSERT INTO `tb_dissertation` VALUES (19, '1434', '2021/04/29/95e09cce-efd7-4f12-a84d-84dab2454da7.pdf', 43, 2, '2021/05/02/9503d706-1790-4e5f-ba4f-aa0e3ae46219.pdf', NULL);
INSERT INTO `tb_dissertation` VALUES (20, '66', '2021/04/29/8f1c8189-a477-4e07-920b-e98e940d27e6.pdf', 43, 0, NULL, NULL);
INSERT INTO `tb_dissertation` VALUES (21, 'z6', '2021/05/02/42a3e2e7-c9e0-4418-b018-fa434d2b0a43.pdf', 41, 3, '2021/05/02/84f8e2cb-fcfd-484b-b3a1-f8472e254b53.pdf', '未达到要求');
INSERT INTO `tb_dissertation` VALUES (22, 'z8', '2021/05/02/3586ccc5-e655-4976-9fe5-fcdc8b9abd01.pdf', 41, 6, '2021/05/04/4c1598b9-e995-44d1-b54b-df4fb2854e5d.pdf', '内容不够充实');
INSERT INTO `tb_dissertation` VALUES (23, 'z7', '2021/05/02/94425f0d-e735-4d2c-b804-e2a15fe8983e.pdf', 41, 10, '2021/05/02/d6c681f1-3158-4126-a179-bd5553d8407e.pdf', '');
COMMIT;

-- ----------------------------
-- Table structure for tb_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_file
-- ----------------------------
BEGIN;
INSERT INTO `tb_file` VALUES (1, 41, 'Java面试基础-1617348473861.pdf');
INSERT INTO `tb_file` VALUES (2, 41, 'Java面试基础-1617348850004.pdf');
INSERT INTO `tb_file` VALUES (3, 41, '3、毕业实习要求-1617349081424.doc');
INSERT INTO `tb_file` VALUES (4, 41, 'Java面试基础-1617673465027.pdf');
INSERT INTO `tb_file` VALUES (5, 43, 'Java面试基础-1617673965691.pdf');
INSERT INTO `tb_file` VALUES (6, 43, 'Java面试基础-1617951067958.pdf');
INSERT INTO `tb_file` VALUES (7, 41, 'Java面试基础-1617952144153.pdf');
INSERT INTO `tb_file` VALUES (8, 68, 'Spring面试38道题-1618384462474.pdf');
COMMIT;

-- ----------------------------
-- Table structure for tb_major
-- ----------------------------
DROP TABLE IF EXISTS `tb_major`;
CREATE TABLE `tb_major` (
  `id` tinyint NOT NULL COMMENT '专业id',
  `name` varchar(255) DEFAULT NULL COMMENT '专业名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_major
-- ----------------------------
BEGIN;
INSERT INTO `tb_major` VALUES (1, '计算机科学与技术');
INSERT INTO `tb_major` VALUES (2, '软件工程');
INSERT INTO `tb_major` VALUES (3, '计算机网络');
INSERT INTO `tb_major` VALUES (4, '计算机应用');
COMMIT;

-- ----------------------------
-- Table structure for tb_student_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_student_info`;
CREATE TABLE `tb_student_info` (
  `uid` int NOT NULL COMMENT '用户uid',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `dept_id` tinyint DEFAULT NULL COMMENT '系部编号id',
  `major_id` tinyint DEFAULT NULL COMMENT '专业编号id',
  `phone` char(11) DEFAULT NULL COMMENT '电话，11位定长',
  `class_id` tinyint DEFAULT NULL COMMENT '班级编号id',
  `graduation` int DEFAULT NULL COMMENT '毕业年份',
  PRIMARY KEY (`uid`) USING BTREE,
  KEY `uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_student_info
-- ----------------------------
BEGIN;
INSERT INTO `tb_student_info` VALUES (1, '周琳', 1, 1, '15803716855', 1, 2025);
INSERT INTO `tb_student_info` VALUES (2, '周二琳', 1, 1, '15803716855', 1, 2025);
INSERT INTO `tb_student_info` VALUES (3, '周三琳', 1, 1, '15803716855', 1, 2025);
INSERT INTO `tb_student_info` VALUES (4, '周四琳', 1, 1, '15803716855', 1, 2025);
INSERT INTO `tb_student_info` VALUES (5, '周五琳', 1, 1, '15803716855', 1, 2025);
INSERT INTO `tb_student_info` VALUES (6, '周六琳', 1, 1, '15803716855', 1, 2025);
INSERT INTO `tb_student_info` VALUES (7, '周七琳', 1, 1, '15803716855', 1, 2025);
INSERT INTO `tb_student_info` VALUES (8, '周八琳', 1, 1, '15803716855', 1, 2025);
INSERT INTO `tb_student_info` VALUES (9, '周九琳', 1, 1, '15803716855', 1, 2025);
INSERT INTO `tb_student_info` VALUES (10, '周十琳', 2, 2, '15803716855', 2, 2025);
INSERT INTO `tb_student_info` VALUES (11, '刘平', 2, 2, '15803716855', 2, 2025);
INSERT INTO `tb_student_info` VALUES (12, '刘二平', 2, 2, '15803716855', 2, 2025);
INSERT INTO `tb_student_info` VALUES (13, '刘三平', 2, 2, '15803716855', 2, 2025);
INSERT INTO `tb_student_info` VALUES (14, '刘四平', 2, 2, '15803716855', 2, 2025);
INSERT INTO `tb_student_info` VALUES (15, '刘五平', 2, 2, '15803716855', 2, 2025);
INSERT INTO `tb_student_info` VALUES (16, '刘六平', 2, 2, '15803716855', 2, 2025);
INSERT INTO `tb_student_info` VALUES (17, '刘七平', 2, 2, '15803716855', 2, 2025);
INSERT INTO `tb_student_info` VALUES (18, '刘八平', 2, 2, '15803716855', 2, 2025);
INSERT INTO `tb_student_info` VALUES (19, '刘九平', 2, 2, '15803716855', 2, 2025);
INSERT INTO `tb_student_info` VALUES (20, '刘十平', 1, 3, '15803716855', 3, 2025);
INSERT INTO `tb_student_info` VALUES (21, '吴有', 1, 3, '15803716855', 3, 2025);
INSERT INTO `tb_student_info` VALUES (22, '吴二有', 1, 3, '15803716855', 3, 2025);
INSERT INTO `tb_student_info` VALUES (23, '吴三有', 1, 3, '15803716855', 3, 2025);
INSERT INTO `tb_student_info` VALUES (24, '吴四有', 1, 3, '15803716855', 3, 2025);
INSERT INTO `tb_student_info` VALUES (25, '吴五有', 1, 3, '15803716855', 3, 2025);
INSERT INTO `tb_student_info` VALUES (26, '吴六有', 1, 3, '15803716855', 3, 2025);
INSERT INTO `tb_student_info` VALUES (27, '吴七有', 1, 3, '15803716855', 3, 2025);
INSERT INTO `tb_student_info` VALUES (28, '吴八有', 1, 3, '15803716855', 3, 2025);
INSERT INTO `tb_student_info` VALUES (29, '吴九有', 1, 3, '15803716855', 3, 2025);
INSERT INTO `tb_student_info` VALUES (30, '吴十有', 2, 4, '15803716855', 4, 2025);
INSERT INTO `tb_student_info` VALUES (31, '董秋', 2, 4, '15803716855', 4, 2025);
INSERT INTO `tb_student_info` VALUES (32, '董二秋', 2, 4, '15803716855', 4, 2025);
INSERT INTO `tb_student_info` VALUES (33, '董三秋', 2, 4, '15803716855', 4, 2025);
INSERT INTO `tb_student_info` VALUES (34, '董四秋', 2, 4, '15803716855', 4, 2025);
INSERT INTO `tb_student_info` VALUES (35, '董五秋', 2, 4, '15803716855', 4, 2025);
INSERT INTO `tb_student_info` VALUES (36, '董六秋', 2, 4, '15803716855', 4, 2025);
INSERT INTO `tb_student_info` VALUES (37, '董七秋', 2, 4, '15803716855', 4, 2025);
INSERT INTO `tb_student_info` VALUES (38, '董八秋', 2, 4, '15803716855', 4, 2025);
INSERT INTO `tb_student_info` VALUES (39, '董九秋', 2, 4, '15803716855', 4, 2025);
INSERT INTO `tb_student_info` VALUES (40, '董十秋', 2, 4, '15803716855', 4, 2025);
INSERT INTO `tb_student_info` VALUES (64, '张同学', 1, 1, '13522221111', 1, 2021);
INSERT INTO `tb_student_info` VALUES (65, '刘同学', 1, 4, '13335674565', 4, 2021);
INSERT INTO `tb_student_info` VALUES (66, '刘同学二', 1, 3, '13555552555', 4, 2021);
COMMIT;

-- ----------------------------
-- Table structure for tb_teacher_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher_info`;
CREATE TABLE `tb_teacher_info` (
  `uid` int NOT NULL,
  `name` varchar(12) DEFAULT NULL COMMENT '姓名',
  `dept_id` int DEFAULT NULL COMMENT '系部',
  `major_id` int NOT NULL COMMENT '指导专业',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机',
  `job_title` varchar(12) DEFAULT NULL COMMENT '职称',
  `degree` varchar(12) DEFAULT NULL COMMENT '学位',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_teacher_info
-- ----------------------------
BEGIN;
INSERT INTO `tb_teacher_info` VALUES (41, '张明远', 1, 1, '13566668888', '副教授', '硕士');
INSERT INTO `tb_teacher_info` VALUES (42, '李文平', 2, 2, '13566668888', '副教授', '硕士');
INSERT INTO `tb_teacher_info` VALUES (43, '王一', 1, 3, '13566668888', '讲师', '硕士');
INSERT INTO `tb_teacher_info` VALUES (44, '赵明德', 2, 4, '13566668888', '教授', '博士');
INSERT INTO `tb_teacher_info` VALUES (63, '吴老师', 1, 1, '13502220111', '教授', '博士');
INSERT INTO `tb_teacher_info` VALUES (67, '吴老师题', 1, 2, '135622233', '副教授', '硕士');
INSERT INTO `tb_teacher_info` VALUES (68, '李老师', 2, 2, '13255552222', '副教授', '硕士');
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `uid` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名，也是登录账户',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '密码',
  `role_id` tinyint(1) NOT NULL DEFAULT '2' COMMENT '角色id，-0管理员 -1教师 -2学生 -4已删除',
  `enabled` bigint NOT NULL DEFAULT '1' COMMENT '用户状态，0-被禁用，1-正常， 4-软删除',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `account` (`username`) USING BTREE COMMENT '给账户添加唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES (1, '202101014101', '$2a$10$Bae75CwcnW0n5w/TQW0yNOsktQTU5bw1R8ArKOS7T2aWI4wzPTGgu', 2, 1);
INSERT INTO `tb_user` VALUES (2, '202101014102', '$2a$10$x2XV9XfSbUD4ccnWPaM1ceUVbQb.wUzymdwu3uHYrICxntUaOISZi', 2, 1);
INSERT INTO `tb_user` VALUES (3, '202101014103', '$2a$10$AsORB7JuQb00LKuAlefcUuUiAFJ9LsQWbybNutFw71PvyzwQDGHvm', 2, 1);
INSERT INTO `tb_user` VALUES (4, '202101014104', '$2a$10$W9CS776uQe41JF7eUFqeju7DSHvdMfhjXeJ/i23AWkU4mOGFS.Qo2', 2, 1);
INSERT INTO `tb_user` VALUES (5, '202101014105', '$2a$10$ANjlGiQhLm7236NuNeGjmOfEEsxox6Z/eQQNWj30XIp9DjyM2hBCu', 2, 1);
INSERT INTO `tb_user` VALUES (6, '202101014106', '$2a$10$6GkOG/nmJKra8ivSqWMIROqQ0tFxECnVb9RLx3zC4tJ8vOHGF5js6', 2, 1);
INSERT INTO `tb_user` VALUES (7, '202101014107', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (8, '202101014108', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (9, '202101014109', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (10, '202101014110', '', 2, 1);
INSERT INTO `tb_user` VALUES (11, '202101024101', '', 2, 1);
INSERT INTO `tb_user` VALUES (12, '202101024102', '', 2, 1);
INSERT INTO `tb_user` VALUES (13, '202101024103', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (14, '202101024104', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (15, '202101024105', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (16, '202101024106', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (17, '202101024107', '', 2, 1);
INSERT INTO `tb_user` VALUES (18, '202101024108', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (19, '202101024109', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (20, '202101024110', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (21, '202101034101', '', 2, 1);
INSERT INTO `tb_user` VALUES (22, '202101034102', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (23, '202101034103', '', 2, 1);
INSERT INTO `tb_user` VALUES (24, '202101034104', '', 2, 1);
INSERT INTO `tb_user` VALUES (25, '202101034105', '', 2, 1);
INSERT INTO `tb_user` VALUES (26, '202101034106', '', 2, 1);
INSERT INTO `tb_user` VALUES (27, '202101034107', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (28, '202101034108', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (29, '202101034109', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (30, '202101034110', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (31, '202101044101', '', 2, 1);
INSERT INTO `tb_user` VALUES (32, '202101044102', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (33, '202101044103', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (34, '202101044104', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (35, '202101044105', '', 2, 1);
INSERT INTO `tb_user` VALUES (36, '202101044106', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (37, '202101044107', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (38, '202101044108', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (39, '202101044109', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (40, '202101044110', NULL, 2, 1);
INSERT INTO `tb_user` VALUES (41, '15010155', '$2a$10$1ICwDC6qhiSlUKDoWowWjuEfMSON0ehk0B7h7DMpjTa2lJQRPl8gS', 1, 1);
INSERT INTO `tb_user` VALUES (42, '16010166', '$2a$10$3cSJCUMIEau4QhC81oZvJuoBtlqlbIQpbQ.0QzqjRX7e3s8irNLx.', 1, 1);
INSERT INTO `tb_user` VALUES (43, '17010177', '$2a$10$5DkvR4S0S32nxrUPLER4euksyet32YOVnRdfy6aD1zUgxs4vhklr.', 1, 1);
INSERT INTO `tb_user` VALUES (44, '18010188', '$2a$10$iVHrV10WKJEtArrQw94t3uFaDWZCQ/JUMCkpeWhKa60kNB6K3gSi.', 1, 1);
INSERT INTO `tb_user` VALUES (45, 'admin', '$2a$10$fGkICzEHFtClVXrF16ysz.GdvlLaGEl0Zyj4ekUGQiLvzMpOnJ0ku', 0, 1);
INSERT INTO `tb_user` VALUES (63, '20100101', '', 1, 1);
INSERT INTO `tb_user` VALUES (64, '202001012122', '', 2, 1);
INSERT INTO `tb_user` VALUES (65, '202001012010', '', 2, 1);
INSERT INTO `tb_user` VALUES (66, '202010101121', '', 2, 1);
INSERT INTO `tb_user` VALUES (67, '20100021', '', 1, 1);
INSERT INTO `tb_user` VALUES (68, '20100011', '', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` tinyint(1) NOT NULL AUTO_INCREMENT,
  `role_id` tinyint(1) NOT NULL COMMENT '角色id',
  `role_name` varchar(16) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_role` VALUES (1, 0, '管理员');
INSERT INTO `tb_user_role` VALUES (2, 1, '教师');
INSERT INTO `tb_user_role` VALUES (3, 2, '学生');
COMMIT;

-- ----------------------------
-- View structure for v_student_info
-- ----------------------------
DROP VIEW IF EXISTS `v_student_info`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_student_info` AS select `i`.`uid` AS `uid`,`i`.`name` AS `name`,`d`.`name` AS `dept`,`m`.`name` AS `major`,`c`.`name` AS `class_name`,`i`.`phone` AS `phone`,`i`.`graduation` AS `graduation` from (((`tb_student_info` `i` join `tb_dept` `d` on((`i`.`dept_id` = `d`.`id`))) join `tb_major` `m` on((`i`.`major_id` = `m`.`id`))) join `tb_class_name` `c` on((`i`.`class_id` = `c`.`id`)));

-- ----------------------------
-- View structure for v_teacher_info
-- ----------------------------
DROP VIEW IF EXISTS `v_teacher_info`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_teacher_info` AS select `i`.`uid` AS `uid`,`i`.`name` AS `name`,`d`.`name` AS `dept`,`m`.`name` AS `major`,`i`.`phone` AS `phone`,`i`.`job_title` AS `job_title`,`i`.`degree` AS `degree` from ((`tb_teacher_info` `i` join `tb_dept` `d` on((`i`.`dept_id` = `d`.`id`))) join `tb_major` `m` on((`i`.`major_id` = `m`.`id`)));

-- ----------------------------
-- View structure for v_user_info
-- ----------------------------
DROP VIEW IF EXISTS `v_user_info`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_user_info` AS select `tb_student_info`.`uid` AS `uid`,`tb_student_info`.`name` AS `name`,`tb_user`.`username` AS `username`,`tb_user`.`password` AS `password` from (`tb_student_info` join `tb_user` on((`tb_student_info`.`uid` = `tb_user`.`uid`))) union select `tb_teacher_info`.`uid` AS `uid`,`tb_teacher_info`.`name` AS `name`,`tb_user`.`username` AS `username`,`tb_user`.`password` AS `password` from (`tb_teacher_info` join `tb_user` on((`tb_teacher_info`.`uid` = `tb_user`.`uid`)));

SET FOREIGN_KEY_CHECKS = 1;
