/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.49 : Database - flowershop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`flowershop` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `flowershop`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `deal_type` int(4) DEFAULT NULL,
  `flower_id` int(4) DEFAULT NULL,
  `seller_id` int(4) DEFAULT NULL,
  `buyer_id` int(4) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `deal_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=gbk;

/*Data for the table `account` */

insert  into `account`(`id`,`deal_type`,`flower_id`,`seller_id`,`buyer_id`,`price`,`deal_time`) values (20,1,10,0,1,125,'2018-08-13 00:00:00'),(21,1,10,2,1,125,'2018-08-13 00:00:00'),(22,1,10,0,2,125,'2018-08-13 00:00:00'),(23,1,10,2,1,125,'2018-08-13 00:00:00'),(24,1,10,0,2,125,'2018-08-13 00:00:00'),(25,1,10,2,1,125,'2018-08-13 00:00:00'),(26,1,3,2,1,200,'2018-08-13 00:00:00'),(27,2,3,1,2,200,'2018-08-13 00:00:00'),(28,1,7,1,1,345,'2018-08-13 00:00:00'),(29,1,11,1,1,100,'2018-08-13 00:00:00'),(30,1,7,1,1,345,'2018-08-13 00:00:00'),(31,2,7,1,1,345,'2018-08-13 00:00:00'),(32,1,7,1,1,345,'2018-08-13 00:00:00'),(33,2,7,1,2,345,'2018-08-13 00:00:00'),(34,1,4,1,1,100,'2018-08-13 00:00:00'),(35,2,4,1,2,100,'2018-08-13 00:00:00'),(36,1,4,1,1,100,'2018-08-13 00:00:00'),(37,2,4,1,2,100,'2018-08-13 00:00:00'),(38,1,4,1,1,100,'2018-08-13 00:00:00'),(39,2,4,1,2,100,'2018-08-13 00:00:00'),(40,1,4,1,1,100,'2018-08-13 00:00:00'),(41,2,4,1,2,100,'2018-08-13 00:00:00'),(42,1,4,1,1,100,'2018-08-13 00:00:00'),(43,2,4,1,2,100,'2018-08-13 00:00:00'),(44,1,10,0,2,125,'2018-08-13 00:00:00');

/*Table structure for table `flower` */

DROP TABLE IF EXISTS `flower`;

CREATE TABLE `flower` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `typeName` varchar(40) DEFAULT NULL,
  `owner_id` int(4) DEFAULT NULL,
  `store_id` int(4) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gbk;

/*Data for the table `flower` */

insert  into `flower`(`id`,`name`,`typeName`,`owner_id`,`store_id`,`price`) values (1,'镜花水月','香槟玫瑰',NULL,1,340),(2,'郁金香瓶花','郁金香',1,NULL,489),(3,'最美的时光','白玫瑰',NULL,2,200),(4,'暖意满满','向日葵',NULL,2,100),(5,'似水伊人','粉绣球',1,NULL,123),(6,'风中谜语','香槟玫瑰',NULL,2,234),(7,'百事合心','香水百合',NULL,1,345),(8,'绚丽多彩','粉玫瑰',2,NULL,234),(9,'风花雪月','粉玫瑰',NULL,1,123),(10,'蓝色的梦','黄莺',NULL,1,125),(11,'粉玫瑰','粉玫瑰',1,NULL,100);

/*Table structure for table `flowerowner` */

DROP TABLE IF EXISTS `flowerowner`;

CREATE TABLE `flowerowner` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `money` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

/*Data for the table `flowerowner` */

insert  into `flowerowner`(`id`,`name`,`password`,`money`) values (1,'小红','123',8000),(2,'丽丽','123',8125);

/*Table structure for table `flowerstore` */

DROP TABLE IF EXISTS `flowerstore`;

CREATE TABLE `flowerstore` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;

/*Data for the table `flowerstore` */

insert  into `flowerstore`(`id`,`name`,`password`,`balance`) values (1,'小丑鲜花店','123',8720),(2,'爱慕鲜花店','123',7155),(3,'爱客鲜花店','123a',8000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
