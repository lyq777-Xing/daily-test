/*
SQLyog Trial v12.4.1 (64 bit)
MySQL - 8.0.30 : Database - pratice
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pratice` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `pratice`;

/*Table structure for table `t_book_info` */

DROP TABLE IF EXISTS `t_book_info`;

CREATE TABLE `t_book_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `author` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '作者',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '描述',
  `inventory` int DEFAULT NULL COMMENT '总数',
  `language` int DEFAULT NULL COMMENT '语言1:中文，2：英文，3其它',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '书名',
  `number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '编号',
  `publication_date` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '出版日期',
  `publishing_house` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '出版社',
  `typeid` bigint DEFAULT NULL COMMENT '类别ID',
  `status` int DEFAULT '2' COMMENT '借阅状态:1、借出，2、未借出',
  `lendid` bigint DEFAULT NULL COMMENT '借阅人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='图书信息';

/*Data for the table `t_book_info` */

insert  into `t_book_info`(`id`,`create_by`,`create_time`,`modify_by`,`modify_time`,`author`,`description`,`inventory`,`language`,`name`,`number`,`publication_date`,`publishing_house`,`typeid`,`status`,`lendid`) values 
(2,NULL,NULL,NULL,'2023-11-13 01:50:04','yyyx',NULL,NULL,3,'JAVA','N000002','2023-11-22',NULL,NULL,2,NULL),
(3,NULL,'2023-11-13 02:16:23',1,'2023-11-18 01:45:28','','',NULL,NULL,'abc','','','',NULL,2,NULL),
(4,NULL,'2023-11-13 02:21:16',1,'2023-11-18 01:38:34','','',NULL,2,'aaa','','','',NULL,2,NULL),
(10,NULL,'2023-11-13 12:07:20',1,'2023-11-18 01:47:33','','',NULL,1,'C语言基础','N44f03726509740078e38c90d1d93af5e','2023-11-15','',NULL,2,NULL),
(11,1,'2023-11-13 18:40:38',1,'2023-11-23 08:42:12','','',NULL,1,'HTML','N712d605a40e54a21a39a961029120d33','2023-11-14','',NULL,2,NULL),
(12,1,'2023-11-23 08:11:59',1,'2023-11-23 08:15:12','','',NULL,1,'C++','Nd861f21460fe4206','2023-11-14','',NULL,2,NULL);

/*Table structure for table `t_book_type` */

DROP TABLE IF EXISTS `t_book_type`;

CREATE TABLE `t_book_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '类别名称',
  `num` int DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='图书类别';

/*Data for the table `t_book_type` */

insert  into `t_book_type`(`id`,`create_by`,`create_time`,`modify_by`,`modify_time`,`name`,`num`) values 
(2,NULL,'2023-11-13 10:45:14',NULL,'2023-11-13 10:47:33','课程1',1),
(3,NULL,'2023-11-13 10:53:01',NULL,'2023-11-13 11:00:06','课程4',4),
(4,NULL,'2023-11-13 10:59:45',NULL,'2023-11-13 10:59:45','课程2',2),
(5,1,'2023-11-13 18:48:20',NULL,'2023-11-13 18:48:20','课程3',NULL),
(6,1,'2023-11-13 18:49:53',NULL,'2023-11-13 18:49:53','add',NULL);

/*Table structure for table `t_borrowing_record` */

DROP TABLE IF EXISTS `t_borrowing_record`;

CREATE TABLE `t_borrowing_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `bookid` bigint DEFAULT NULL COMMENT '图书ID',
  `return_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '归还时间',
  `lendid` bigint DEFAULT NULL COMMENT '借阅人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='借阅记录';

/*Data for the table `t_borrowing_record` */

insert  into `t_borrowing_record`(`id`,`create_by`,`create_time`,`modify_by`,`modify_time`,`bookid`,`return_time`,`lendid`) values 
(4,1,'2023-11-18 01:41:26',1,'2023-11-18 01:47:36',10,'2023-11-18 01:47:36.443',NULL),
(6,1,'2023-11-18 01:44:20',1,'2023-11-23 08:42:13',11,'2023-11-23 08:42:12.648',NULL);

/*Table structure for table `t_sys_cfg` */

DROP TABLE IF EXISTS `t_sys_cfg`;

CREATE TABLE `t_sys_cfg` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `cfg_desc` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
  `cfg_name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '参数名',
  `cfg_value` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '参数值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='系统参数';

/*Data for the table `t_sys_cfg` */

insert  into `t_sys_cfg`(`id`,`create_by`,`create_time`,`modify_by`,`modify_time`,`cfg_desc`,`cfg_name`,`cfg_value`) values 
(1,NULL,NULL,1,'2019-04-15 21:36:07','应用名称update by 2019-03-27 11:47:04','system.app.name','web-flash'),
(2,NULL,NULL,1,'2019-04-15 21:36:17','系统默认上传文件路径','system.file.upload.path','/data/web-flash/runtime/upload'),
(3,NULL,NULL,1,'2019-04-15 21:36:17','腾讯sms接口appid','api.tencent.sms.appid','需要去申请咯'),
(4,NULL,NULL,1,'2019-04-15 21:36:17','腾讯sms接口appkey','api.tencent.sms.appkey','需要去申请咯'),
(5,NULL,NULL,1,'2019-04-15 21:36:17','腾讯sms接口签名参数','api.tencent.sms.sign','需要去申请咯'),
(6,NULL,NULL,1,'2019-04-15 21:36:17','阿里云sms接口accesskey','api.aliyun.sms.access.key.id','需要去申请咯'),
(7,NULL,NULL,1,'2019-04-15 21:36:17','阿里云sms接口access Secret','api.aliyun.sms.access.secret','需要去申请咯'),
(8,NULL,NULL,1,'2019-04-15 21:36:17','阿里云sms接口地域id','api.aliyun.sms.region.id','需要去申请咯');

/*Table structure for table `t_sys_dept` */

DROP TABLE IF EXISTS `t_sys_dept`;

CREATE TABLE `t_sys_dept` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `account` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '货代FTP账号',
  `forwarder` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '货代代码',
  `ftp_path` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'FTP目录',
  `fullname` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '部门/组织全称',
  `num` int DEFAULT NULL COMMENT '排序',
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '货代FTP密码',
  `pid` bigint DEFAULT NULL COMMENT '父组织id',
  `pids` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '所有上级组织id列表',
  `simplename` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '部门/组织简称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='组织/部门';

/*Data for the table `t_sys_dept` */

insert  into `t_sys_dept`(`id`,`create_by`,`create_time`,`modify_by`,`modify_time`,`account`,`forwarder`,`ftp_path`,`fullname`,`num`,`password`,`pid`,`pids`,`simplename`) values 
(1,NULL,NULL,1,'2022-06-15 10:47:13',NULL,NULL,NULL,'NB209',1,NULL,0,'[0],','总公司'),
(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'开发部',2,NULL,1,'[0],[1],','开发部'),
(3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'运营部',3,NULL,1,'[0],[1],','运营部'),
(4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'战略部',4,NULL,1,'[0],[1],','战略部');

/*Table structure for table `t_sys_dict` */

DROP TABLE IF EXISTS `t_sys_dict`;

CREATE TABLE `t_sys_dict` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '字典显示值',
  `num` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '字典值',
  `pid` bigint DEFAULT NULL COMMENT '字典记录所属组id',
  `tips` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='字典';

/*Data for the table `t_sys_dict` */

insert  into `t_sys_dict`(`id`,`create_by`,`create_time`,`modify_by`,`modify_time`,`name`,`num`,`pid`,`tips`) values 
(16,1,'2019-01-13 14:18:21',1,'2020-07-18 21:43:41','状态','0',0,NULL),
(17,1,'2019-01-13 14:18:21',1,'2020-07-18 21:43:41','启用','1',16,NULL),
(18,1,'2019-01-13 14:18:21',1,'2020-07-18 21:43:41','禁用','2',16,NULL),
(29,1,'2019-01-13 14:18:21',1,'2020-07-18 21:43:41','性别','0',0,NULL),
(30,1,'2019-01-13 14:18:21',1,'2020-07-18 21:43:41','男','1',29,NULL),
(31,1,'2019-01-13 14:18:21',1,'2020-07-18 21:43:41','女','2',29,NULL),
(35,1,'2019-01-13 14:18:21',1,'2020-07-18 21:43:41','账号状态','0',0,NULL),
(36,1,'2019-01-13 14:18:21',1,'2020-07-18 21:43:41','启用','1',35,NULL),
(37,1,'2019-01-13 14:18:21',1,'2020-07-18 21:43:41','冻结','2',35,NULL),
(38,1,'2019-01-13 14:18:21',1,'2020-07-18 21:43:41','已删除','3',35,NULL),
(53,1,'2019-01-13 14:18:21',1,'2020-07-18 21:43:41','证件类型','0',0,NULL),
(54,1,'2019-01-13 14:18:21',1,'2020-07-18 21:43:41','身份证','1',53,NULL),
(55,1,'2019-01-13 14:18:21',1,'2020-07-18 21:43:41','护照','2',53,NULL),
(68,1,'2019-01-13 14:18:21',1,'2019-01-13 14:18:21','是否','0',0,NULL),
(69,1,'2019-01-13 14:18:21',1,'2019-01-13 14:18:21','是','1',68,NULL),
(70,1,'2019-01-13 14:18:21',1,'2019-01-13 14:18:21','否','0',68,NULL),
(71,1,'2020-07-18 21:43:41',1,'2020-07-18 21:43:41','日志类型','0',0,NULL),
(72,1,'2020-07-18 21:43:41',1,'2020-07-18 21:43:41','业务日志','1',71,NULL),
(73,1,'2019-01-13 14:18:21',1,'2020-07-18 21:43:41','异常日志','2',71,NULL),
(74,1,'2021-07-24 22:27:00',1,'2021-07-24 22:27:00','工作流实例状态','0',0,NULL),
(75,1,'2021-07-24 22:27:00',1,'2021-07-24 22:27:00','进行中','0',74,NULL),
(76,1,'2021-07-24 22:27:00',1,'2021-07-24 22:27:00','成功','1',74,NULL),
(77,1,'2021-07-24 22:27:00',1,'2021-07-24 22:27:00','失败','2',74,NULL);

/*Table structure for table `t_sys_file_info` */

DROP TABLE IF EXISTS `t_sys_file_info`;

CREATE TABLE `t_sys_file_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `original_file_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '原始文件名称',
  `real_file_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '文件存储在磁盘中的真正名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='文件';

/*Data for the table `t_sys_file_info` */

/*Table structure for table `t_sys_login_log` */

DROP TABLE IF EXISTS `t_sys_login_log`;

CREATE TABLE `t_sys_login_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `ip` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '操作ip',
  `logname` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '日志表述',
  `message` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '日志详情',
  `succeed` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '操作结果标识',
  `userid` int DEFAULT NULL COMMENT '操作用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='登录日志';

/*Data for the table `t_sys_login_log` */

insert  into `t_sys_login_log`(`id`,`create_time`,`ip`,`logname`,`message`,`succeed`,`userid`) values 
(71,'2019-05-10 13:17:43','127.0.0.1','登录日志',NULL,'成功',1),
(72,'2019-05-12 13:36:56','127.0.0.1','登录日志',NULL,'成功',1),
(73,'2022-06-14 10:43:40','127.0.0.1','登录日志',NULL,'成功',1),
(74,'2022-06-14 10:47:46','127.0.0.1','登录日志',NULL,'成功',1),
(75,'2022-06-14 10:49:49','127.0.0.1','登录日志',NULL,'成功',1),
(76,'2022-06-14 10:58:42','127.0.0.1','登录日志',NULL,'成功',1),
(77,'2022-06-15 10:41:41','127.0.0.1','登录日志',NULL,'成功',1),
(78,'2022-06-15 10:43:46','127.0.0.1','登录日志',NULL,'成功',1),
(79,'2023-07-09 20:05:34','127.0.0.1','登录日志',NULL,'成功',1),
(80,'2023-07-09 20:43:55','127.0.0.1','登录日志',NULL,'成功',1),
(81,'2023-07-09 20:47:38','127.0.0.1','登录日志',NULL,'成功',1),
(82,'2023-07-09 20:50:23','127.0.0.1','登录日志',NULL,'成功',5),
(83,'2023-07-09 20:50:38','127.0.0.1','登录日志',NULL,'成功',1),
(84,'2023-07-09 21:21:22','127.0.0.1','登录日志',NULL,'成功',1),
(85,'2023-07-09 21:23:19','127.0.0.1','登录日志',NULL,'成功',1),
(86,'2023-07-09 21:28:20','127.0.0.1','登录日志',NULL,'成功',1),
(87,'2023-07-09 21:54:19','127.0.0.1','登录日志',NULL,'成功',1),
(88,'2023-07-09 21:54:41','127.0.0.1','登录日志',NULL,'成功',5),
(89,'2023-07-09 21:54:48','127.0.0.1','登录日志',NULL,'成功',1),
(90,'2023-07-09 21:55:09','127.0.0.1','登录日志',NULL,'成功',5),
(91,'2023-07-09 21:55:19','127.0.0.1','登录日志',NULL,'成功',1),
(92,'2023-07-09 22:04:10','127.0.0.1','登录日志',NULL,'成功',1),
(93,'2023-07-09 22:18:07','127.0.0.1','登录日志',NULL,'成功',1),
(94,'2023-07-09 22:50:39','127.0.0.1','登录日志',NULL,'成功',1),
(95,'2023-07-09 23:27:33','127.0.0.1','登录日志',NULL,'成功',1),
(96,'2023-07-09 23:46:02','127.0.0.1','登录日志',NULL,'成功',1),
(97,'2023-07-10 09:26:03','127.0.0.1','登录日志',NULL,'成功',1),
(98,'2023-11-12 16:32:26','127.0.0.1','登录日志',NULL,'成功',1),
(99,'2023-11-12 20:16:48','127.0.0.1','登录日志',NULL,'成功',1),
(100,'2023-11-12 20:48:04','127.0.0.1','登录日志',NULL,'成功',1),
(101,'2023-11-12 21:03:59','127.0.0.1','登录日志',NULL,'成功',1),
(102,'2023-11-12 23:34:55','127.0.0.1','登录日志',NULL,'成功',1),
(103,'2023-11-13 00:39:51','127.0.0.1','登录日志',NULL,'成功',1),
(104,'2023-11-13 00:43:25','127.0.0.1','登录日志',NULL,'成功',1),
(105,'2023-11-13 01:10:23','127.0.0.1','登录日志',NULL,'成功',1),
(106,'2023-11-13 01:39:49','127.0.0.1','登录日志',NULL,'成功',1),
(107,'2023-11-13 02:00:45','127.0.0.1','登录日志',NULL,'成功',1),
(108,'2023-11-13 02:16:12','127.0.0.1','登录日志',NULL,'成功',1),
(109,'2023-11-13 09:52:10','127.0.0.1','登录日志',NULL,'成功',1),
(110,'2023-11-13 10:22:50','127.0.0.1','登录日志',NULL,'成功',1),
(111,'2023-11-13 10:30:53','127.0.0.1','登录日志',NULL,'成功',1),
(112,'2023-11-13 10:39:33','127.0.0.1','登录日志',NULL,'成功',1),
(113,'2023-11-13 10:45:04','127.0.0.1','登录日志',NULL,'成功',1),
(114,'2023-11-13 11:19:54','127.0.0.1','登录日志',NULL,'成功',1),
(115,'2023-11-13 11:32:34','127.0.0.1','登录日志',NULL,'成功',1),
(116,'2023-11-13 12:06:58','127.0.0.1','登录日志',NULL,'成功',1),
(117,'2023-11-13 12:08:30','127.0.0.1','登录日志',NULL,'成功',5),
(118,'2023-11-13 12:10:33','127.0.0.1','登录日志',NULL,'成功',4),
(119,'2023-11-13 18:26:39','127.0.0.1','登录日志',NULL,'成功',1),
(120,'2023-11-13 18:40:24','127.0.0.1','登录日志',NULL,'成功',1),
(121,'2023-11-13 18:43:14','127.0.0.1','登录日志',NULL,'成功',1),
(122,'2023-11-18 00:59:54','127.0.0.1','登录日志',NULL,'成功',1),
(123,'2023-11-18 01:28:11','127.0.0.1','登录日志',NULL,'成功',1),
(124,'2023-11-18 01:38:26','127.0.0.1','登录日志',NULL,'成功',1),
(125,'2023-11-23 07:53:47','127.0.0.1','登录日志',NULL,'成功',1),
(126,'2023-11-23 08:11:37','127.0.0.1','登录日志',NULL,'成功',1),
(127,'2023-11-23 08:41:55','127.0.0.1','登录日志',NULL,'成功',1);

/*Table structure for table `t_sys_menu` */

DROP TABLE IF EXISTS `t_sys_menu`;

CREATE TABLE `t_sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `code` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '编号',
  `component` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '页面组件',
  `hidden` tinyint DEFAULT NULL COMMENT '是否隐藏',
  `icon` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '图标',
  `ismenu` int NOT NULL COMMENT '是否是菜单1:菜单,0:按钮',
  `isopen` int DEFAULT NULL COMMENT '是否默认打开1:是,0:否',
  `levels` int NOT NULL COMMENT '级别',
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '名称',
  `num` int NOT NULL COMMENT '顺序',
  `pcode` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '父菜单编号',
  `pcodes` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '递归父级菜单编号',
  `tips` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '鼠标悬停提示信息',
  `url` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '资源/权限标识',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UK_s37unj3gh67ujhk83lqva8i1t` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='菜单';

/*Data for the table `t_sys_menu` */

insert  into `t_sys_menu`(`id`,`create_by`,`create_time`,`modify_by`,`modify_time`,`code`,`component`,`hidden`,`icon`,`ismenu`,`isopen`,`levels`,`name`,`num`,`pcode`,`pcodes`,`tips`,`url`) values 
(1,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','system','layout',0,'system',1,1,1,'系统管理',1,'0','[0],',NULL,'/system'),
(3,1,'2019-07-31 22:04:30',1,'2020-07-25 18:12:57','operationMgr','layout',0,'operation',1,NULL,1,'运维管理',2,'0','[0],',NULL,'/optionMgr'),
(4,1,'2019-07-31 22:04:30',1,'2019-04-16 18:59:15','mgr','views/system/user/index',0,'user',1,NULL,2,'用户管理',1,'system','[0],[system],',NULL,'/mgr'),
(5,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','mgrAdd',NULL,0,NULL,0,NULL,3,'添加用户',1,'mgr','[0],[system],[mgr],',NULL,'/mgr/add'),
(6,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','mgrEdit',NULL,0,NULL,0,NULL,3,'修改用户',2,'mgr','[0],[system],[mgr],',NULL,'/mgr/edit'),
(7,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','mgrDelete',NULL,0,NULL,0,0,3,'删除用户',3,'mgr','[0],[system],[mgr],',NULL,'/mgr/delete'),
(8,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','mgrReset',NULL,0,NULL,0,0,3,'重置密码',4,'mgr','[0],[system],[mgr],',NULL,'/mgr/reset'),
(9,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','setRole',NULL,0,NULL,0,0,3,'分配角色',5,'mgr','[0],[system],[mgr],',NULL,'/mgr/setRole'),
(10,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','mgrUnfreeze',NULL,0,NULL,0,0,3,'解除冻结用户',6,'mgr','[0],[system],[mgr],',NULL,'/mgr/unfreeze'),
(11,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','mgrSetRole',NULL,0,NULL,0,0,3,'分配角色',7,'mgr','[0],[system],[mgr],',NULL,'/mgr/setRole'),
(12,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','role','views/system/role/index',0,'peoples',1,0,2,'角色管理',2,'system','[0],[system],',NULL,'/role'),
(13,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','roleAdd',NULL,0,NULL,0,0,3,'添加角色',1,'role','[0],[system],[role],',NULL,'/role/add'),
(14,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','roleEdit',NULL,0,NULL,0,0,3,'修改角色',2,'role','[0],[system],[role],',NULL,'/role/edit'),
(15,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','roleDelete',NULL,0,NULL,0,0,3,'删除角色',3,'role','[0],[system],[role],',NULL,'/role/remove'),
(16,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','roleSetAuthority',NULL,0,NULL,0,0,3,'配置权限',4,'role','[0],[system],[role],',NULL,'/role/setAuthority'),
(17,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','menu','views/system/menu/index',0,'menu',1,0,2,'菜单管理',4,'system','[0],[system],',NULL,'/menu'),
(18,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','menuAdd',NULL,0,NULL,0,0,3,'添加菜单',1,'menu','[0],[system],[menu],',NULL,'/menu/add'),
(19,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','menuEdit',NULL,0,NULL,0,0,3,'修改菜单',2,'menu','[0],[system],[menu],',NULL,'/menu/edit'),
(20,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','menuDelete',NULL,0,NULL,0,0,3,'删除菜单',3,'menu','[0],[system],[menu],',NULL,'/menu/remove'),
(21,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','dept','views/system/dept/index',0,'dept',1,NULL,2,'部门管理',3,'system','[0],[system],',NULL,'/dept'),
(22,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','dict','views/system/dict/index',0,'dict',1,NULL,2,'字典管理',4,'system','[0],[system],',NULL,'/dict'),
(23,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','deptEdit',NULL,0,NULL,0,NULL,3,'修改部门',1,'dept','[0],[system],[dept],',NULL,'/dept/update'),
(24,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','deptDelete',NULL,0,NULL,0,NULL,3,'删除部门',1,'dept','[0],[system],[dept],',NULL,'/dept/delete'),
(25,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','dictAdd',NULL,0,NULL,0,NULL,3,'添加字典',1,'dict','[0],[system],[dict],',NULL,'/dict/add'),
(26,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','dictEdit',NULL,0,NULL,0,NULL,3,'修改字典',1,'dict','[0],[system],[dict],',NULL,'/dict/update'),
(27,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','dictDelete',NULL,0,NULL,0,NULL,3,'删除字典',1,'dict','[0],[system],[dict],',NULL,'/dict/delete'),
(28,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','deptList',NULL,0,NULL,0,NULL,3,'部门列表',5,'dept','[0],[system],[dept],',NULL,'/dept/list'),
(29,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','deptDetail',NULL,0,NULL,0,NULL,3,'部门详情',6,'dept','[0],[system],[dept],',NULL,'/dept/detail'),
(30,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','dictList',NULL,0,NULL,0,NULL,3,'字典列表',5,'dict','[0],[system],[dict],',NULL,'/dict/list'),
(31,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','dictDetail',NULL,0,NULL,0,NULL,3,'字典详情',6,'dict','[0],[system],[dict],',NULL,'/dict/detail'),
(32,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','deptAdd',NULL,0,NULL,0,NULL,3,'添加部门',1,'dept','[0],[system],[dept],',NULL,'/dept/add'),
(33,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','cfg','views/system/cfg/index',0,'cfg',1,NULL,2,'参数管理',10,'system','[0],[system],',NULL,'/cfg'),
(34,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','cfgAdd',NULL,0,NULL,0,NULL,3,'添加参数',1,'cfg','[0],[system],[cfg],',NULL,'/cfg/add'),
(35,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','cfgEdit',NULL,0,NULL,0,NULL,3,'修改参数',2,'cfg','[0],[system],[cfg],',NULL,'/cfg/update'),
(36,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','cfgDelete',NULL,0,NULL,0,NULL,3,'删除参数',3,'cfg','[0],[system],[cfg],',NULL,'/cfg/delete'),
(37,1,'2019-07-31 22:04:30',1,'2020-07-25 18:08:05','task','views/system/task/index',0,'task',1,NULL,2,'任务管理',11,'system','[0],[system],',NULL,'/task'),
(38,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','taskAdd',NULL,0,NULL,0,NULL,3,'添加任务',1,'task','[0],[system],[task],',NULL,'/task/add'),
(39,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','taskEdit',NULL,0,NULL,0,NULL,3,'修改任务',2,'task','[0],[system],[task],',NULL,'/task/update'),
(40,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','taskDelete',NULL,0,NULL,0,NULL,3,'删除任务',3,'task','[0],[system],[task],',NULL,'/task/delete'),
(47,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','taskLog','views/system/task/taskLog',1,'task',1,NULL,2,'任务日志',4,'system','[0],[system],',NULL,'/taskLog'),
(48,1,'2019-07-31 22:04:30',1,'2019-06-02 10:25:31','log','views/operation/log/index',0,'log',1,NULL,2,'业务日志',6,'operationMgr','[0],[operationMgr],',NULL,'/log'),
(49,1,'2019-07-31 22:04:30',1,'2019-06-02 10:25:36','loginLog','views/operation/loginLog/index',0,'logininfor',1,NULL,2,'登录日志',6,'operationMgr','[0],[operationMgr],',NULL,'/loginLog'),
(50,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','logClear',NULL,0,NULL,0,NULL,3,'清空日志',3,'log','[0],[system],[log],',NULL,'/log/delLog'),
(51,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','logDetail',NULL,0,NULL,0,NULL,3,'日志详情',3,'log','[0],[system],[log],',NULL,'/log/detail'),
(52,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','loginLogClear',NULL,0,NULL,0,NULL,3,'清空登录日志',1,'loginLog','[0],[system],[loginLog],',NULL,'/loginLog/delLoginLog'),
(53,1,'2019-07-31 22:04:30',1,'2019-07-31 22:04:30','loginLogList',NULL,0,NULL,0,NULL,3,'登录日志列表',2,'loginLog','[0],[system],[loginLog],',NULL,'/loginLog/list'),
(54,1,'2019-06-02 10:10:20',1,'2019-06-02 10:10:20','druid','views/operation/druid/index',0,'monitor',1,NULL,2,'数据库管理',1,'operationMgr','[0],[operationMgr],',NULL,'/druid'),
(55,1,'2019-06-02 10:10:20',1,'2019-06-02 10:10:20','swagger','views/operation/api/index',0,'swagger',1,NULL,2,'接口文档',2,'operationMgr','[0],[operationMgr],',NULL,'/swagger'),
(86,1,'2023-07-09 23:42:14',1,'2023-11-12 21:00:49','stu','layout',0,'user0',1,NULL,1,'学生管理',7,'0','[0],',NULL,'/training'),
(87,1,'2023-07-09 23:44:28',1,'2023-07-09 23:44:34','asdf','views/training/student/index',0,'monitor',1,NULL,2,'学生',8,'stu','[0],[stu],',NULL,'/student'),
(88,1,'2023-11-12 21:00:06',1,'2023-11-12 21:01:17','book','layout',0,'education',1,NULL,1,'图书管理',3,'0','[0],',NULL,'/book'),
(94,1,'2023-11-12 21:03:05',1,'2023-11-13 02:08:52','catalogue','views/book/type/index',0,'channel',1,NULL,2,'分类管理',1,'book','[0],[book],',NULL,'/book/type'),
(95,1,'2023-11-12 21:32:27',1,'2023-11-12 21:32:27','information','views/book/information',0,'code',1,NULL,2,'信息管理',2,'book','[0],[book],',NULL,'/book/information'),
(96,1,'2023-11-13 11:08:35',1,'2023-11-13 11:13:38','lend','layout',0,'articleEdit',1,NULL,1,'借阅管理',4,'0','[0],',NULL,'/lend'),
(97,1,'2023-11-13 11:12:08',1,'2023-11-13 11:13:22','lendlist','views/lend/lendlist/index',0,'build',1,NULL,2,'借阅列表',2,'lend','[0],[lend],',NULL,'/lend/lendlist'),
(98,1,'2023-11-13 11:41:28',1,'2023-11-13 11:41:48','booklist','views/lend/booklist/index',0,'',1,NULL,2,'图书信息',1,'lend','[0],[lend],',NULL,'/lend/booklist');

/*Table structure for table `t_sys_notice` */

DROP TABLE IF EXISTS `t_sys_notice`;

CREATE TABLE `t_sys_notice` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '通知内容',
  `title` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '通知标题',
  `type` int DEFAULT NULL COMMENT '通知类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='通知';

/*Data for the table `t_sys_notice` */

insert  into `t_sys_notice`(`id`,`create_by`,`create_time`,`modify_by`,`modify_time`,`content`,`title`,`type`) values 
(1,1,'2017-01-11 08:53:20',1,'2019-01-08 23:30:58','欢迎使用web-flash后台管理系统','欢迎光临',10);

/*Table structure for table `t_sys_operation_log` */

DROP TABLE IF EXISTS `t_sys_operation_log`;

CREATE TABLE `t_sys_operation_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `classname` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '对应类名',
  `create_time` date DEFAULT NULL COMMENT '操作日期',
  `logname` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '日志名称',
  `logtype` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '日志类型',
  `message` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '详细信息',
  `method` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '对应方法名',
  `succeed` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '成功标识',
  `userid` int DEFAULT NULL COMMENT '操作用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='操作日志';

/*Data for the table `t_sys_operation_log` */

insert  into `t_sys_operation_log`(`id`,`classname`,`create_time`,`logname`,`logtype`,`message`,`method`,`succeed`,`userid`) values 
(13,'cn.enilu.flash.api.controller.system.MenuController','2022-06-15','编辑菜单','业务日志','name=学生管理;;;','save','成功',1),
(14,'cn.enilu.flash.api.controller.system.MenuController','2022-06-15','编辑菜单','业务日志','name=学生信息;;;','save','成功',1),
(15,'cn.enilu.flash.api.controller.system.RoleController','2022-06-15','配置角色权限','业务日志','roleId=1;permissions=1,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,23,24,28,29,32,22,25,26,27,30,31,33,34,35,36,37,38,39,40,47,3,48,50,51,49,52,53,54,55,80,81,;','setAuthority','成功',1),
(16,'cn.enilu.flash.api.controller.system.MenuController','2022-06-15','编辑菜单','业务日志','name=学生管理;;;','save','成功',1),
(17,'cn.enilu.flash.api.controller.system.DeptContoller','2022-06-15','编辑部门','业务日志','部门/组织简称=总公司;;;部门/组织全称:One Piece集团=>NB209','save','成功',1),
(18,'cn.enilu.flash.api.controller.system.TaskController','2022-06-15','禁用定时任务','业务日志','taskId=1;','disable','成功',1),
(19,'cn.enilu.flash.api.controller.training.StudentController','2022-06-15','新增学生信息','业务日志','birthday=123;name=ces;id=;age=11;','add','成功',1),
(20,'cn.enilu.flash.api.controller.training.StudentController','2022-06-15','删除学生信息','业务日志','id=1;','remove','成功',1),
(21,'cn.enilu.flash.api.controller.system.UserController','2023-07-09','编辑账号','业务日志','name=管理员;;;','save','成功',1),
(22,'cn.enilu.flash.api.controller.system.UserController','2023-07-09','编辑账号','业务日志','name=读者;;;','save','成功',1),
(23,'cn.enilu.flash.api.controller.system.UserController','2023-07-09','设置账号角色','业务日志','userId=4;roleIds=2,;','setRole','成功',1),
(24,'cn.enilu.flash.api.controller.system.UserController','2023-07-09','设置账号角色','业务日志','userId=5;roleIds=3,;','setRole','成功',1),
(25,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','编辑菜单','业务日志','name=图书管理;;;','save','成功',1),
(26,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','编辑菜单','业务日志','name=图书管理;;;','save','成功',1),
(27,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','编辑菜单','业务日志','name=图书管理;;;','save','成功',1),
(28,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','编辑菜单','业务日志','name=图书信息;;;','save','成功',1),
(29,'cn.enilu.flash.api.controller.system.RoleController','2023-07-09','配置角色权限','业务日志','roleId=3;permissions=82,83,;','setAuthority','成功',1),
(30,'cn.enilu.flash.api.controller.system.RoleController','2023-07-09','配置角色权限','业务日志','roleId=2;permissions=82,83,;','setAuthority','成功',1),
(31,'cn.enilu.flash.api.controller.system.RoleController','2023-07-09','配置角色权限','业务日志','roleId=1;permissions=1,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,23,24,28,29,32,22,25,26,27,30,31,33,34,35,36,37,38,39,40,47,3,48,50,51,49,52,53,54,55,80,81,82,83,;','setAuthority','成功',1),
(32,'cn.enilu.flash.api.controller.training.StudentController','2023-07-09','新增学生信息','业务日志','birthday=kkk;name=朱晗彬;id=;age=90;','add','成功',1),
(33,'cn.enilu.flash.api.controller.system.RoleController','2023-07-09','配置角色权限','业务日志','roleId=3;permissions=;','setAuthority','成功',1),
(34,'cn.enilu.flash.api.controller.system.RoleController','2023-07-09','配置角色权限','业务日志','roleId=2;permissions=;','setAuthority','成功',1),
(35,'cn.enilu.flash.api.controller.system.RoleController','2023-07-09','配置角色权限','业务日志','roleId=1;permissions=1,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,23,24,28,29,32,22,25,26,27,30,31,33,34,35,36,37,38,39,40,47,3,48,50,51,49,52,53,54,55,80,81,;','setAuthority','成功',1),
(36,'cn.enilu.flash.api.controller.system.RoleController','2023-07-09','配置角色权限','业务日志','roleId=1;permissions=1,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,23,24,28,29,32,22,25,26,27,30,31,33,34,35,36,37,38,39,40,47,3,48,50,51,49,52,53,54,55,;','setAuthority','成功',1),
(37,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','删除菜单','业务日志','id=82;','remove','成功',1),
(38,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','删除菜单','业务日志','id=80;','remove','成功',1),
(39,'cn.enilu.flash.api.controller.system.UserController','2023-07-09','编辑账号','业务日志','name=图书管理员;;;','save','成功',1),
(40,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','编辑菜单','业务日志','name=学生管理;;;','save','成功',1),
(41,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','编辑菜单','业务日志','name=学生管理;;;','save','成功',1),
(42,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','编辑菜单','业务日志','name=学生管理;;;','save','成功',1),
(43,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','编辑菜单','业务日志','name=学生管理;;;','save','成功',1),
(44,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','编辑菜单','业务日志','name=学生管理;;;','save','成功',1),
(45,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','编辑菜单','业务日志','name=xuesheng;;;','save','成功',1),
(46,'cn.enilu.flash.api.controller.system.RoleController','2023-07-09','配置角色权限','业务日志','roleId=1;permissions=1,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,23,24,28,29,32,22,25,26,27,30,31,33,34,35,36,37,38,39,40,47,3,48,50,51,49,52,53,54,55,84,85,;','setAuthority','成功',1),
(47,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','删除菜单','业务日志','id=84;','remove','成功',1),
(48,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','编辑菜单','业务日志','name=xuesheng;;;','save','成功',1),
(49,'cn.enilu.flash.api.controller.system.RoleController','2023-07-09','配置角色权限','业务日志','roleId=1;permissions=1,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,23,24,28,29,32,22,25,26,27,30,31,33,34,35,36,37,38,39,40,47,3,48,50,51,49,52,53,54,55,86,;','setAuthority','成功',1),
(50,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','编辑菜单','业务日志','name=xuesheng;;;','save','成功',1),
(51,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','编辑菜单','业务日志','name=x学生;;;','save','成功',1),
(52,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','编辑菜单','业务日志','name=学生;;;','save','成功',1),
(53,'cn.enilu.flash.api.controller.system.RoleController','2023-07-09','配置角色权限','业务日志','roleId=1;permissions=1,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,23,24,28,29,32,22,25,26,27,30,31,33,34,35,36,37,38,39,40,47,3,48,50,51,49,52,53,54,55,86,87,;','setAuthority','成功',1),
(54,'cn.enilu.flash.api.controller.training.StudentController','2023-07-09','删除学生信息','业务日志','id=2;','remove','成功',1),
(55,'cn.enilu.flash.api.controller.training.StudentController','2023-07-09','新增学生信息','业务日志','birthday=2020;name=张三;id=;age=17;','add','成功',1),
(56,'cn.enilu.flash.api.controller.training.StudentController','2023-07-09','更新学生信息','业务日志','birthday=2020/12/31;name=张三;id=3;age=17;','update','成功',1),
(57,'cn.enilu.flash.api.controller.system.MenuController','2023-07-09','编辑菜单','业务日志','name=学生管理;;;','save','成功',1),
(58,'cn.enilu.flash.api.controller.system.MenuController','2023-11-12','编辑菜单','业务日志','name=图书管理;;;','save','成功',1),
(59,'cn.enilu.flash.api.controller.system.MenuController','2023-11-12','编辑菜单','业务日志','name=图书管理;;;','save','成功',1),
(60,'cn.enilu.flash.api.controller.system.MenuController','2023-11-12','编辑菜单','业务日志','name=学生管理;;;','save','成功',1),
(61,'cn.enilu.flash.api.controller.system.MenuController','2023-11-12','编辑菜单','业务日志','name=图书管理;;;','save','成功',1),
(62,'cn.enilu.flash.api.controller.system.MenuController','2023-11-12','编辑菜单','业务日志','name=分类管理;;;','save','成功',1),
(63,'cn.enilu.flash.api.controller.system.MenuController','2023-11-12','编辑菜单','业务日志','name=分类管理;;;','save','成功',1),
(64,'cn.enilu.flash.api.controller.system.MenuController','2023-11-12','编辑菜单','业务日志','name=信息管理;;;','save','成功',1),
(65,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','修改书籍信息','业务日志','name=JAVA;;;','update','成功',1),
(66,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','修改书籍信息','业务日志','name=程序设计;;;','update','成功',1),
(67,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','修改书籍信息','业务日志','name=JAVA;;;','update','成功',1),
(68,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','修改书籍信息','业务日志','name=JAVA;;;','update','成功',1),
(69,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','修改书籍信息','业务日志','name=JAVA;;;','update','成功',1),
(70,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','修改书籍信息','业务日志','name=程序设计;;;','update','成功',1),
(71,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','修改书籍信息','业务日志','name=JAVA;;;','update','成功',1),
(72,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','修改书籍信息','业务日志','name=程序设计;;;','update','成功',1),
(73,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','删除书籍信息','业务日志','id=1;','remove','成功',1),
(74,'cn.enilu.flash.api.controller.system.MenuController','2023-11-13','编辑菜单','业务日志','name=分类管理;;;','save','成功',1),
(75,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','新增书籍信息','业务日志','modifyBy=;author=;description=;publishingHouse=;language=;inventory=;number=;createBy=;modifyTime=;createTime=;name=;typeid=;id=;publicationDate=;','add','成功',1),
(76,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','修改书籍信息','业务日志','name=abc;;;','update','成功',1),
(77,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','新增书籍信息','业务日志','modifyBy=;author=;description=;publishingHouse=;language=;inventory=;number=;createBy=;modifyTime=;createTime=;name=;typeid=;id=;publicationDate=;','add','成功',1),
(78,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','修改书籍信息','业务日志','name=;;;','update','成功',1),
(79,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','修改书籍信息','业务日志','name=aaa;;;','update','成功',1),
(80,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','新增分类信息','业务日志','modifyBy=;num=;createBy=;modifyTime=;createTime=;name=a;id=;','add','成功',1),
(81,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','新增书籍信息','业务日志','modifyBy=;author=;description=;publishingHouse=;language=;inventory=;number=;createBy=;modifyTime=;createTime=;name=;typeid=;id=;publicationDate=;','add','成功',1),
(82,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','删除书籍信息','业务日志','id=5;','remove','成功',1),
(83,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','新增书籍信息','业务日志','modifyBy=;author=;description=;publishingHouse=;language=;inventory=;number=;createBy=;modifyTime=;createTime=;name=;typeid=;id=;publicationDate=;','add','成功',1),
(84,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','删除书籍信息','业务日志','id=6;','remove','成功',1),
(85,'cn.enilu.flash.api.controller.training.StudentController','2023-11-13','新增学生信息','业务日志','birthday=;name=;id=;age=;','add','成功',1),
(86,'cn.enilu.flash.api.controller.training.StudentController','2023-11-13','删除学生信息','业务日志','id=4;','remove','成功',1),
(87,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','新增书籍信息','业务日志','modifyBy=;author=;description=;publishingHouse=;language=;inventory=;number=;createBy=;modifyTime=;createTime=;name=;typeid=;id=;publicationDate=;','add','成功',1),
(88,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','删除书籍信息','业务日志','id=7;','remove','成功',1),
(89,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','新增书籍信息','业务日志','modifyBy=;author=;description=;publishingHouse=;language=;inventory=;number=;createBy=;modifyTime=;createTime=;name=;typeid=;id=;publicationDate=;','add','成功',1),
(90,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','删除书籍信息','业务日志','id=8;','remove','成功',1),
(91,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','修改书籍信息','业务日志','name=aaa;;;','update','成功',1),
(92,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','新增书籍信息','业务日志','modifyBy=;author=a;description=;publishingHouse=;language=1;inventory=;number=;createBy=;modifyTime=;createTime=;name=aaa;typeid=;id=;publicationDate=;','add','成功',1),
(93,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','修改书籍信息','业务日志','name=aaa;;;','update','成功',1),
(94,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','修改书籍信息','业务日志','name=aaa;;;','update','成功',1),
(95,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','修改书籍信息','业务日志','name=aaa;;;','update','成功',1),
(96,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=b;;;','update','成功',1),
(97,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=b;;;','update','成功',1),
(98,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=b;;;','update','成功',1),
(99,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','新增分类信息','业务日志','modifyBy=;num=;createBy=;modifyTime=;createTime=;name=b;id=;','add','成功',1),
(100,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','新增分类信息','业务日志','modifyBy=;num=;createBy=;modifyTime=;createTime=;name=a;id=;','add','成功',1),
(101,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=课程1;;;','update','成功',1),
(102,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=课程2;;;','update','成功',1),
(103,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=课程2;;;','update','成功',1),
(104,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=课程2;;;','update','成功',1),
(105,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=课程2;;;','update','成功',1),
(106,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','新增分类信息','业务日志','modifyBy=;num=2;createBy=;modifyTime=;createTime=;name=课程3;id=;','add','成功',1),
(107,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=课程2;;;','update','成功',1),
(108,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','删除分类信息','业务日志','id=1;','remove','成功',1),
(109,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','新增分类信息','业务日志','modifyBy=;num=2;createBy=;modifyTime=;createTime=;name=课程1;id=;','add','成功',1),
(110,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','新增分类信息','业务日志','modifyBy=;num=2;createBy=;modifyTime=;createTime=;name=课程2;id=;','add','成功',1),
(111,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=课程3;;;','update','成功',1),
(112,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=课程2;;;','update','成功',1),
(113,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=课程2;;;','update','成功',1),
(114,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=课程2;;;','update','成功',1),
(115,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=课程4;;;','update','成功',1),
(116,'cn.enilu.flash.api.controller.system.MenuController','2023-11-13','编辑菜单','业务日志','name=借阅管理;;;','save','成功',1),
(117,'cn.enilu.flash.api.controller.system.MenuController','2023-11-13','编辑菜单','业务日志','name=借阅管理;;;','save','成功',1),
(118,'cn.enilu.flash.api.controller.system.MenuController','2023-11-13','编辑菜单','业务日志','name=借阅管理;;;','save','成功',1),
(119,'cn.enilu.flash.api.controller.system.MenuController','2023-11-13','编辑菜单','业务日志','name=借阅列表;;;','save','成功',1),
(120,'cn.enilu.flash.api.controller.system.MenuController','2023-11-13','编辑菜单','业务日志','name=借阅管理;;;','save','成功',1),
(121,'cn.enilu.flash.api.controller.system.MenuController','2023-11-13','编辑菜单','业务日志','name=借阅列表;;;','save','成功',1),
(122,'cn.enilu.flash.api.controller.system.MenuController','2023-11-13','编辑菜单','业务日志','name=借阅列表;;;','save','成功',1),
(123,'cn.enilu.flash.api.controller.system.MenuController','2023-11-13','编辑菜单','业务日志','name=借阅管理;;;','save','成功',1),
(124,'cn.enilu.flash.api.controller.system.MenuController','2023-11-13','编辑菜单','业务日志','name=booklist;;;','save','成功',1),
(125,'cn.enilu.flash.api.controller.system.MenuController','2023-11-13','编辑菜单','业务日志','name=booklist;;;','save','成功',1),
(126,'cn.enilu.flash.api.controller.system.MenuController','2023-11-13','编辑菜单','业务日志','name=图书信息;;;','save','成功',1),
(127,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','新增书籍信息','业务日志','modifyBy=;author=;description=;publishingHouse=;language=1;inventory=;number=;createBy=;modifyTime=;createTime=;name=C语言基础;typeid=;id=;publicationDate=2023-11-15;','add','成功',1),
(128,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','删除书籍信息','业务日志','id=9;','remove','成功',1),
(129,'cn.enilu.flash.api.controller.book.InformationController','2023-11-13','新增书籍信息','业务日志','modifyBy=;author=;description=;publishingHouse=;language=1;inventory=;number=;createBy=;modifyTime=;createTime=;name=HTML;typeid=;id=;publicationDate=2023-11-14;','add','成功',1),
(130,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','新增分类信息','业务日志','modifyBy=;num=;createBy=;modifyTime=;createTime=;name=课程3;id=;','add','成功',1),
(131,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','新增分类信息','业务日志','modifyBy=;num=;createBy=;modifyTime=;createTime=;name=add;id=;','add','成功',1),
(132,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=add;;;','update','成功',1),
(133,'cn.enilu.flash.api.controller.book.TypeController','2023-11-13','修改分类信息','业务日志','name=add;;;','update','成功',1),
(134,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=HTML;;;','update','成功',1),
(135,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=aaa;;;','update','成功',1),
(136,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=HTML;;;','update','成功',1),
(137,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=C语言基础;;;','update','成功',1),
(138,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=abc;;;','update','成功',1),
(139,'cn.enilu.flash.api.controller.lend.LendController','2023-11-18','新增借阅信息','业务日志','modifyBy=;returnTime=;bookid=3;createBy=;modifyTime=;createTime=;name=;id=;','add','成功',1),
(140,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=abc;;;','update','成功',1),
(141,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=aaa;;;','update','成功',1),
(142,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=C语言基础;;;','update','成功',1),
(143,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=HTML;;;','update','成功',1),
(144,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=C语言基础;;;','update','成功',1),
(145,'cn.enilu.flash.api.controller.lend.LendController','2023-11-18','新增借阅信息','业务日志','modifyBy=;returnTime=;bookid=10;createBy=;modifyTime=;createTime=;name=;id=;','add','成功',1),
(146,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=C语言基础;;;','update','成功',1),
(147,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=C语言基础;;;','update','成功',1),
(148,'cn.enilu.flash.api.controller.lend.LendController','2023-11-18','新增借阅信息','业务日志','modifyBy=;returnTime=;bookid=10;createBy=;modifyTime=;createTime=;name=;id=;','add','成功',1),
(149,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=C语言基础;;;','update','成功',1),
(150,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=HTML;;;','update','成功',1),
(151,'cn.enilu.flash.api.controller.lend.LendController','2023-11-18','新增借阅信息','业务日志','modifyBy=;returnTime=;bookid=11;createBy=;modifyTime=;createTime=;name=;id=;','add','成功',1),
(152,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=HTML;;;','update','成功',1),
(153,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=C语言基础;;;','update','成功',1),
(154,'cn.enilu.flash.api.controller.lend.LendController','2023-11-18','新增借阅信息','业务日志','modifyBy=;returnTime=;bookid=10;createBy=;modifyTime=;createTime=;name=;id=;','add','成功',1),
(155,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=abc;;;','update','成功',1),
(156,'cn.enilu.flash.api.controller.lend.LendController','2023-11-18','新增借阅信息','业务日志','modifyBy=;returnTime=;bookid=3;createBy=;modifyTime=;createTime=;name=;id=;','add','成功',1),
(157,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=abc;;;','update','成功',1),
(158,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=C语言基础;;;','update','成功',1),
(159,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=C语言基础;;;','update','成功',1),
(160,'cn.enilu.flash.api.controller.lend.LendController','2023-11-18','新增借阅信息','业务日志','modifyBy=;returnTime=;bookid=10;createBy=;modifyTime=;createTime=;name=;id=;','add','成功',1),
(161,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=C语言基础;;;','update','成功',1),
(162,'cn.enilu.flash.api.controller.lend.LendController','2023-11-18','修改借阅信息','业务日志','name=null;;;','update','成功',1),
(163,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=HTML;;;','update','成功',1),
(164,'cn.enilu.flash.api.controller.lend.LendController','2023-11-18','新增借阅信息','业务日志','modifyBy=;returnTime=;bookid=11;createBy=;modifyTime=;createTime=;name=;id=;','add','成功',1),
(165,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=HTML;;;','update','成功',1),
(166,'cn.enilu.flash.api.controller.lend.LendController','2023-11-18','修改借阅信息','业务日志','name=null;;;','update','成功',1),
(167,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=HTML;;;','update','成功',1),
(168,'cn.enilu.flash.api.controller.lend.LendController','2023-11-18','新增借阅信息','业务日志','modifyBy=;returnTime=;bookid=11;createBy=;modifyTime=;createTime=;name=;id=;','add','成功',1),
(169,'cn.enilu.flash.api.controller.book.InformationController','2023-11-18','修改书籍信息','业务日志','name=HTML;;;','update','成功',1),
(170,'cn.enilu.flash.api.controller.lend.LendController','2023-11-18','修改借阅信息','业务日志','name=null;;;','update','成功',1),
(171,'cn.enilu.flash.api.controller.book.InformationController','2023-11-23','新增书籍信息','业务日志','modifyBy=;lendid=;author=;description=;publishingHouse=;language=1;inventory=;number=;createBy=;modifyTime=;createTime=;name=C++;typeid=;id=;publicationDate=2023-11-14;status=2;','add','成功',1),
(172,'cn.enilu.flash.api.controller.book.InformationController','2023-11-23','修改书籍信息','业务日志','name=C++;;;','update','成功',1),
(173,'cn.enilu.flash.api.controller.lend.LendController','2023-11-23','新增借阅信息','业务日志','modifyBy=;returnTime=;bookid=12;createBy=;modifyTime=;createTime=;name=;id=;','add','成功',1),
(174,'cn.enilu.flash.api.controller.book.InformationController','2023-11-23','修改书籍信息','业务日志','name=C++;;;','update','成功',1),
(175,'cn.enilu.flash.api.controller.lend.LendController','2023-11-23','修改借阅信息','业务日志','name=null;;;','update','成功',1),
(176,'cn.enilu.flash.api.controller.book.InformationController','2023-11-23','修改书籍信息','业务日志','name=HTML;;;','update','成功',1),
(177,'cn.enilu.flash.api.controller.lend.LendController','2023-11-23','新增借阅信息','业务日志','modifyBy=;returnTime=;bookid=11;createBy=;modifyTime=;createTime=;name=;id=;','add','成功',1),
(178,'cn.enilu.flash.api.controller.book.InformationController','2023-11-23','修改书籍信息','业务日志','name=HTML;;;','update','成功',1),
(179,'cn.enilu.flash.api.controller.lend.LendController','2023-11-23','修改借阅信息','业务日志','name=null;;;','update','成功',1);

/*Table structure for table `t_sys_relation` */

DROP TABLE IF EXISTS `t_sys_relation`;

CREATE TABLE `t_sys_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `menuid` bigint DEFAULT NULL COMMENT '菜单id',
  `roleid` bigint DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=577 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='菜单角色关系';

/*Data for the table `t_sys_relation` */

insert  into `t_sys_relation`(`id`,`menuid`,`roleid`) values 
(314,NULL,3),
(315,NULL,2),
(513,1,1),
(514,4,1),
(515,5,1),
(516,6,1),
(517,7,1),
(518,8,1),
(519,9,1),
(520,10,1),
(521,11,1),
(522,12,1),
(523,13,1),
(524,14,1),
(525,15,1),
(526,16,1),
(527,17,1),
(528,18,1),
(529,19,1),
(530,20,1),
(531,21,1),
(532,23,1),
(533,24,1),
(534,28,1),
(535,29,1),
(536,32,1),
(537,22,1),
(538,25,1),
(539,26,1),
(540,27,1),
(541,30,1),
(542,31,1),
(543,33,1),
(544,34,1),
(545,35,1),
(546,36,1),
(547,37,1),
(548,38,1),
(549,39,1),
(550,40,1),
(551,47,1),
(552,3,1),
(553,48,1),
(554,50,1),
(555,51,1),
(556,49,1),
(557,52,1),
(558,53,1),
(559,54,1),
(560,55,1),
(561,86,1),
(562,87,1),
(563,88,1),
(564,94,1),
(565,95,1),
(566,96,1),
(567,97,1),
(568,98,1),
(569,98,3),
(570,88,2),
(571,94,2),
(572,95,2),
(573,96,2),
(574,97,2),
(575,98,2),
(576,96,3);

/*Table structure for table `t_sys_role` */

DROP TABLE IF EXISTS `t_sys_role`;

CREATE TABLE `t_sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `deptid` bigint DEFAULT NULL COMMENT '角色所属部门',
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '角色名称',
  `num` int DEFAULT NULL COMMENT '排序',
  `pid` bigint DEFAULT NULL COMMENT '父角色id',
  `tips` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '角色编码',
  `version` int DEFAULT NULL COMMENT '角色版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='角色';

/*Data for the table `t_sys_role` */

insert  into `t_sys_role`(`id`,`create_by`,`create_time`,`modify_by`,`modify_time`,`deptid`,`name`,`num`,`pid`,`tips`,`version`) values 
(1,NULL,NULL,NULL,NULL,1,'超级管理员',1,0,'administrator',1),
(2,NULL,NULL,NULL,NULL,1,'图书管理员',1,0,'gly',NULL),
(3,NULL,NULL,NULL,NULL,1,'读者',1,0,'dz',1);

/*Table structure for table `t_sys_task` */

DROP TABLE IF EXISTS `t_sys_task`;

CREATE TABLE `t_sys_task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `concurrent` tinyint DEFAULT NULL COMMENT '是否允许并发',
  `cron` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '定时规则',
  `data` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '执行参数',
  `disabled` tinyint DEFAULT NULL COMMENT '是否禁用',
  `exec_at` datetime DEFAULT NULL COMMENT '执行时间',
  `exec_result` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '执行结果',
  `job_class` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '执行类',
  `job_group` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '任务组名',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '任务名',
  `note` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '任务说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='定时任务';

/*Data for the table `t_sys_task` */

insert  into `t_sys_task`(`id`,`create_by`,`create_time`,`modify_by`,`modify_time`,`concurrent`,`cron`,`data`,`disabled`,`exec_at`,`exec_result`,`job_class`,`job_group`,`name`,`note`) values 
(1,1,'2018-12-28 09:54:00',1,'2022-06-15 10:47:23',0,'0 0/30 * * * ?','{\n\"appname\": \"web-flash\",\n\"version\":1\n}\n            \n            \n            \n            \n            \n            \n            \n            \n            \n            \n            \n            ',1,'2019-03-27 11:47:00','执行成功','cn.enilu.flash.service.task.job.HelloJob','default','测试任务','测试任务,每30分钟执行一次');

/*Table structure for table `t_sys_task_log` */

DROP TABLE IF EXISTS `t_sys_task_log`;

CREATE TABLE `t_sys_task_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `exec_at` datetime DEFAULT NULL COMMENT '执行时间',
  `exec_success` int DEFAULT NULL COMMENT '执行结果（成功:1、失败:0)',
  `id_task` bigint DEFAULT NULL COMMENT '对应任务id',
  `job_exception` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '抛出异常',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '任务名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='定时任务日志';

/*Data for the table `t_sys_task_log` */

/*Table structure for table `t_sys_user` */

DROP TABLE IF EXISTS `t_sys_user`;

CREATE TABLE `t_sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `account` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '账户',
  `avatar` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '头像',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `deptid` bigint DEFAULT NULL COMMENT '部门id',
  `email` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'email',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '姓名',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '密码',
  `phone` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '手机号',
  `roleid` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '角色id列表，以逗号分隔',
  `salt` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '密码盐',
  `sex` int DEFAULT NULL COMMENT '性别:1:男,2:女',
  `status` int DEFAULT NULL COMMENT '状态1:启用,2:禁用',
  `version` int DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='账号';

/*Data for the table `t_sys_user` */

insert  into `t_sys_user`(`id`,`create_by`,`create_time`,`modify_by`,`modify_time`,`account`,`avatar`,`birthday`,`deptid`,`email`,`name`,`password`,`phone`,`roleid`,`salt`,`sex`,`status`,`version`) values 
(-1,NULL,'2016-01-29 08:49:53',1,'2019-03-20 23:45:24','system',NULL,NULL,NULL,NULL,'应用系统',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(1,NULL,'2016-01-29 08:49:53',1,'2019-03-20 23:45:24','admin',NULL,'2017-05-05',2,'eniluzt@qq.com','管理员','b5a51391f271f062867e5984e2fcffee','15021222222','1','8pgby',2,1,2),
(4,1,'2023-07-09 20:49:02',1,'2023-07-09 22:12:59','guanliyuan',NULL,'2023-07-09',1,'1034482767@qq.com','图书管理员','f95f8a7ea478804b60c7b6ae65ceb0c2','1111','2,','or08g',1,1,NULL),
(5,1,'2023-07-09 20:49:49',1,'2023-07-09 20:50:06','reader',NULL,'2023-07-09',1,'2222','读者','e91b90bef2e5c4249c8f186a91439ddc','434324','3,','21wsy',1,1,NULL);

/*Table structure for table `t_test_boy` */

DROP TABLE IF EXISTS `t_test_boy`;

CREATE TABLE `t_test_boy` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `age` int DEFAULT NULL COMMENT '年龄',
  `birthday` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '生日',
  `has_girl_friend` tinyint DEFAULT NULL COMMENT '是否有女朋友',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='男孩';

/*Data for the table `t_test_boy` */

insert  into `t_test_boy`(`id`,`create_by`,`create_time`,`modify_by`,`modify_time`,`age`,`birthday`,`has_girl_friend`,`name`) values 
(1,NULL,NULL,NULL,NULL,18,'2000-01-01',1,'张三');

/*Table structure for table `t_test_girl` */

DROP TABLE IF EXISTS `t_test_girl`;

CREATE TABLE `t_test_girl` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `age` int DEFAULT NULL COMMENT '年龄',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `has_boy_friend` tinyint DEFAULT NULL COMMENT '是否有男朋友',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='女孩';

/*Data for the table `t_test_girl` */

/*Table structure for table `t_training_student` */

DROP TABLE IF EXISTS `t_training_student`;

CREATE TABLE `t_training_student` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间/注册时间',
  `modify_by` bigint DEFAULT NULL COMMENT '最后更新人',
  `modify_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `age` int DEFAULT NULL COMMENT '年龄',
  `birthday` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '生日',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='学生信息';

/*Data for the table `t_training_student` */

insert  into `t_training_student`(`id`,`create_by`,`create_time`,`modify_by`,`modify_time`,`age`,`birthday`,`name`) values 
(3,1,'2023-07-09 23:46:34',1,'2023-07-09 23:46:48',17,'2020/12/31','张三');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
