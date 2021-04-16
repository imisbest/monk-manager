/*
Navicat MySQL Data Transfer

Source Server         : 106.13.236.137
Source Server Version : 50642
Source Host           : 106.13.236.137:3306
Source Database       : monk-manager

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2019-12-01 17:47:48
*/

SET
FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`
(
    `id`       varchar(255) NOT NULL,
    `username` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin`
VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album`
(
    `id`          varchar(255) NOT NULL,
    `title`       varchar(255) DEFAULT NULL,
    `score`       varchar(255) DEFAULT NULL,
    `author`      varchar(255) DEFAULT NULL,
    `broadcast`   varchar(255) DEFAULT NULL,
    `count`       varchar(255) DEFAULT NULL,
    `desc`        varchar(255) DEFAULT NULL,
    `status`      varchar(255) DEFAULT NULL,
    `create_date` date         DEFAULT NULL,
    `cover`       varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album
-- ----------------------------
INSERT INTO `album`
VALUES ('1192c211-f0b8-49f1-b26a-3b72965d63a0', '22', '22', '22', '22', '22', '22', '1', '1998-02-03',
        'http://192.168.43.1:8989/upload/img/1574905600497_ik1.PNG');
INSERT INTO `album`
VALUES ('9250b0ed-1568-41b6-b2f4-2c65ea142a47', 'cc', '33', '33', '33', '33', '33', '1', '1995-12-31',
        'http://192.168.43.1:8989/upload/img/1574995980992_日志系统开发.png');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`
(
    `id`           varchar(255) NOT NULL,
    `title`        varchar(255) DEFAULT NULL,
    `url`          varchar(255) DEFAULT NULL,
    `content`      varchar(255) DEFAULT NULL,
    `create_date`  date         DEFAULT NULL,
    `publish_date` date         DEFAULT NULL,
    `status`       varchar(255) DEFAULT NULL,
    `guru_id`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article`
VALUES ('803893e4-7813-4dde-a601-7476d9f97de5', 'sssss2',
        'http://192.168.43.1:8989/upload/articleImg/1575169431623_上传音频文件.png', '<strong>rfervgewbwht</strong>',
        '2019-12-01', '2019-12-01', '', '2');
INSERT INTO `article`
VALUES ('bc273eb1-3d40-40c5-b9ce-3bd39d960607', 'ss3',
        'http://192.168.43.1:8989/upload/articleImg/1575164404768_上传音频文件.png', '<strong>HTML内容3</strong>', '2019-12-01',
        '2019-12-01', '', '1');
INSERT INTO `article`
VALUES ('d0d61154-74b5-4728-ad88-969f96a540fd', 'ssd2',
        'http://192.168.43.1:8989/upload/articleImg/1575168885722_日志系统开发.png',
        'sssss<img src=\"/upload/articleImg/1575032075887_上传音频文件.png\" alt=\"\" width=\"80\" height=\"50\" title=\"\" align=\"\" />2',
        '2019-12-01', '2019-12-01', '', '3');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`
(
    `id`          varchar(255) NOT NULL,
    `title`       varchar(255) DEFAULT NULL,
    `url`         varchar(255) DEFAULT NULL,
    `href`        varchar(255) DEFAULT NULL,
    `create_date` date         DEFAULT NULL,
    `desc`        varchar(255) DEFAULT NULL,
    `status`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner`
VALUES ('12db8c38-6590-4778-b6cd-23cff720eb0d', 'ddd', 'http://192.168.43.1:8989/upload/img/1574868908073_ik1.PNG',
        'dd', null, 'dd', '1');
INSERT INTO `banner`
VALUES ('35f01122-4488-49f8-b1c2-d2d332a9bcc3', 'aaa', 'http://192.168.43.1:8989/upload/img/1574955390856_日志系统开发.png',
        '', null, 'aa', '1');
INSERT INTO `banner`
VALUES ('43175e27-579a-4834-a966-ff62c401719a', 'fff', 'http://192.168.43.1:8989/upload/img/1574908362393_ik2.PNG',
        'ff', null, 'ff', '1');

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter`
(
    `id`          varchar(255) NOT NULL,
    `title`       varchar(255) DEFAULT NULL,
    `url`         varchar(255) DEFAULT NULL,
    `size`        varchar(255) DEFAULT NULL,
    `time`        varchar(255) DEFAULT NULL,
    `create_time` date         DEFAULT NULL,
    `album_id`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter`
VALUES ('d60a8ebf-2d22-4004-9789-19bc1735013a', 'ss',
        'http://192.168.43.1:8989/upload/chapter/1574942770670_茅野愛衣,戸松遥,早見沙織 - secret base ~君がくれたもの~ (10 years after Ver.).mp3',
        '13MB', '5分52秒', '2018-12-30', '1192c211-f0b8-49f1-b26a-3b72965d63a0');

-- ----------------------------
-- Table structure for counter
-- ----------------------------
DROP TABLE IF EXISTS `counter`;
CREATE TABLE `counter`
(
    `id`          varchar(255) NOT NULL,
    `title`       varchar(255) DEFAULT NULL,
    `count`       varchar(255) DEFAULT NULL,
    `create_date` date         DEFAULT NULL,
    `user_id`     varchar(255) DEFAULT NULL,
    `course_id`   varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of counter
-- ----------------------------

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`
(
    `id`          varchar(255) NOT NULL,
    `title`       varchar(255) DEFAULT NULL,
    `user_id`     varchar(255) DEFAULT NULL,
    `type`        varchar(255) DEFAULT NULL,
    `create_date` date         DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------

-- ----------------------------
-- Table structure for guru
-- ----------------------------
DROP TABLE IF EXISTS `guru`;
CREATE TABLE `guru`
(
    `id`        varchar(255) NOT NULL,
    `name`      varchar(255) DEFAULT NULL,
    `photo`     varchar(255) DEFAULT NULL,
    `status`    varchar(255) DEFAULT NULL,
    `nick_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of guru
-- ----------------------------
INSERT INTO `guru`
VALUES ('1', '咕咕拉克', 'http://192.168.43.1:8989/upload/GuruImg/1575188779174_1.jpg', '1', '卡西吗');
INSERT INTO `guru`
VALUES ('2', '阿思八达', 'http://192.168.43.1:8989/upload/GuruImg/1575188821826_2.jpg', '2', '思密达');
INSERT INTO `guru`
VALUES ('d939a614-7237-489f-afc9-ea770ac97abb', 'bbb', 'http://192.168.43.1:8989/upload/GuruImg/1575188579174_6.jpg',
        '1', 'bbb');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          varchar(255) NOT NULL,
    `phone`       varchar(255) DEFAULT NULL,
    `password`    varchar(255) DEFAULT NULL,
    `salt`        varchar(255) DEFAULT NULL,
    `status`      varchar(255) DEFAULT NULL,
    `photo`       varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `nick_name`   varchar(255) DEFAULT NULL,
    `sex`         varchar(255) DEFAULT NULL,
    `sign`        varchar(255) DEFAULT NULL,
    `location`    varchar(255) DEFAULT NULL,
    `rigest_date` date         DEFAULT NULL,
    `last_login`  date         DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES ('1', '11111111111', '111111', '32314', '1', 'http://192.168.43.1:8989/upload/img/1574868908073_ik1.PNG', '张三',
        '小张', '男', '0', '梦琳乡，梦琳县', '1998-03-01', '2019-12-06');
