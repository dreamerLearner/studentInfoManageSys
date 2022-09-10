/*
SQLyog Enterprise v12.08 (32 bit)
MySQL - 5.7.33-log : Database - education_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`education_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `education_db`;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '院系id',
  `name` varchar(20) NOT NULL COMMENT '院系名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`id`,`name`) values (1,'土建学院'),(2,'计算机学院'),(3,'传媒学院');

/*Table structure for table `lesson` */

DROP TABLE IF EXISTS `lesson`;

CREATE TABLE `lesson` (
  `id` int(30) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `CourseName` varchar(20) NOT NULL COMMENT '课程名',
  `peirodTime` int(20) NOT NULL COMMENT '学时',
  `teacherName` varchar(20) NOT NULL COMMENT '授课教师',
  `CourseType` varchar(20) NOT NULL COMMENT '课程类型',
  `date` date NOT NULL COMMENT '授课时间',
  `address` varchar(70) NOT NULL COMMENT '授课地点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `lesson` */

/*Table structure for table `manager` */

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `id` int(50) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `name` varchar(20) NOT NULL COMMENT '管理员姓名',
  `account` varchar(100) NOT NULL COMMENT '管理员账号',
  `password` varchar(100) NOT NULL COMMENT '管理员密码',
  `phone` bigint(20) NOT NULL COMMENT '管理员手机号码',
  PRIMARY KEY (`id`,`account`,`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `manager` */

insert  into `manager`(`id`,`name`,`account`,`password`,`phone`) values (1,'张涛','admin','e10adc3949ba59abbe56e057f20f883e',17628068535),(3,'张三','admin02','e10adc3949ba59abbe56e057f20f883e',18688012646),(4,'管理员','admina','e10adc3949ba59abbe56e057f20f883e',0),(5,'大兵','8646544','123456',0),(9,'陈博林','cbl','e10adc3949ba59abbe56e057f20f883e',13809657780),(10,'游钦展','yy','e10adc3949ba59abbe56e057f20f883e',18814386384);

/*Table structure for table `profession` */

DROP TABLE IF EXISTS `profession`;

CREATE TABLE `profession` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '专业id',
  `name` varchar(20) NOT NULL COMMENT '专业名',
  `departmentId` int(20) NOT NULL COMMENT '所属院系id',
  PRIMARY KEY (`id`),
  KEY `departmentId` (`departmentId`),
  CONSTRAINT `profession_ibfk_1` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `profession` */

insert  into `profession`(`id`,`name`,`departmentId`) values (1,'软件工程',2),(2,'土木工程',1),(3,'新闻学',3);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(20) NOT NULL COMMENT '学生学号',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '学生姓名',
  `sex` int(1) NOT NULL COMMENT '学生性别（0为男，1为女）',
  `professionId` int(20) NOT NULL COMMENT '学生专业id',
  `departmentId` int(20) NOT NULL COMMENT '学生院系id',
  `grade` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '学生年级',
  PRIMARY KEY (`id`),
  KEY `departmentId` (`departmentId`),
  KEY `professionId` (`professionId`),
  CONSTRAINT `departmentId` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `professionId` FOREIGN KEY (`professionId`) REFERENCES `profession` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`sex`,`professionId`,`departmentId`,`grade`) values (1,'小缪',0,2,1,'2019级'),(2,'陈博林',0,1,2,'2019级'),(3,'张三',1,2,1,'2018级'),(4,'李辉',0,2,1,'2018级'),(5,'小汪',0,2,1,'2019级'),(6,'李红',1,3,3,'2018级'),(7,'提莫',0,3,3,'2019级'),(8,'细雨',0,2,2,'2019级'),(9,'小游',0,2,2,'2019级'),(10,'小兰',1,1,1,'2019级'),(11,'小蔡',0,2,2,'2019级'),(12,'小麦',0,1,2,'2019级'),(13,'小陈',0,1,2,'2019级'),(14,'小周',1,3,3,'2019级');

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '教师id',
  `name` varchar(20) NOT NULL COMMENT '教师姓名',
  `courseId` int(20) NOT NULL COMMENT '教授课程id',
  PRIMARY KEY (`id`),
  KEY `courseId` (`courseId`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`courseId`) REFERENCES `lesson` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
