/*
 Navicat Premium Data Transfer

 Source Server         : testDB
 Source Server Type    : SQLite
 Source Server Version : 3021000
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3021000
 File Encoding         : 65001

 Date: 01/03/2019 16:08:30
*/

PRAGMA foreign_keys = false;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS "account";
CREATE TABLE "account" (
  "id" TEXT(255),
  "deal_type" TEXT(255),
  "flower_id" TEXT(255),
  "seller_id" TEXT(255),
  "buyer_id" TEXT(255),
  "price" TEXT(255),
  "deal_time" TEXT(255)
);

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO "account" VALUES (20, 1, 10, 0, 1, 125, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (21, 1, 10, 2, 1, 125, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (22, 1, 10, 0, 2, 125, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (23, 1, 10, 2, 1, 125, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (24, 1, 10, 0, 2, 125, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (25, 1, 10, 2, 1, 125, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (26, 1, 3, 2, 1, 200, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (27, 2, 3, 1, 2, 200, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (28, 1, 7, 1, 1, 345, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (29, 1, 11, 1, 1, 100, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (30, 1, 7, 1, 1, 345, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (31, 2, 7, 1, 1, 345, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (32, 1, 7, 1, 1, 345, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (33, 2, 7, 1, 2, 345, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (34, 1, 4, 1, 1, 100, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (35, 2, 4, 1, 2, 100, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (36, 1, 4, 1, 1, 100, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (37, 2, 4, 1, 2, 100, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (38, 1, 4, 1, 1, 100, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (39, 2, 4, 1, 2, 100, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (40, 1, 4, 1, 1, 100, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (41, 2, 4, 1, 2, 100, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (42, 1, 4, 1, 1, 100, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (43, 2, 4, 1, 2, 100, '13/8/2018 00:00:00');
INSERT INTO "account" VALUES (44, 1, 10, 0, 2, 125, '13/8/2018 00:00:00');

-- ----------------------------
-- Table structure for flower
-- ----------------------------
DROP TABLE IF EXISTS "flower";
CREATE TABLE "flower" (
  "id" TEXT(255),
  "name" TEXT(255),
  "typeName" TEXT(255),
  "owner_id" TEXT(255),
  "store_id" TEXT(255),
  "price" TEXT(255)
);

-- ----------------------------
-- Records of flower
-- ----------------------------
INSERT INTO "flower" VALUES (1, '镜花水月', '香槟玫瑰', NULL, 1, 340);
INSERT INTO "flower" VALUES (2, '郁金香瓶花', '郁金香', 1, NULL, 489);
INSERT INTO "flower" VALUES (3, '最美的时光', '白玫瑰', NULL, 2, 200);
INSERT INTO "flower" VALUES (4, '暖意满满', '向日葵', NULL, 2, 100);
INSERT INTO "flower" VALUES (5, '似水伊人', '粉绣球', 1, NULL, 123);
INSERT INTO "flower" VALUES (6, '风中谜语', '香槟玫瑰', NULL, 2, 234);
INSERT INTO "flower" VALUES (7, '百事合心', '香水百合', NULL, 1, 345);
INSERT INTO "flower" VALUES (8, '绚丽多彩', '粉玫瑰', 2, NULL, 234);
INSERT INTO "flower" VALUES (9, '风花雪月', '粉玫瑰', NULL, 1, 123);
INSERT INTO "flower" VALUES (10, '蓝色的梦', '黄莺', NULL, 1, 125);
INSERT INTO "flower" VALUES (11, '粉玫瑰', '粉玫瑰', 1, NULL, 100);

-- ----------------------------
-- Table structure for flowerowner
-- ----------------------------
DROP TABLE IF EXISTS "flowerowner";
CREATE TABLE "flowerowner" (
  "id" TEXT(255),
  "name" TEXT(255),
  "password" TEXT(255),
  "money" TEXT(255)
);

-- ----------------------------
-- Records of flowerowner
-- ----------------------------
INSERT INTO "flowerowner" VALUES (1, '小红', 123, 8000);
INSERT INTO "flowerowner" VALUES (2, '丽丽', 123, 8125);

-- ----------------------------
-- Table structure for flowerstore
-- ----------------------------
DROP TABLE IF EXISTS "flowerstore";
CREATE TABLE "flowerstore" (
  "id" TEXT(255),
  "name" TEXT(255),
  "password" TEXT(255),
  "balance" TEXT(255)
);

-- ----------------------------
-- Records of flowerstore
-- ----------------------------
INSERT INTO "flowerstore" VALUES (1, '小丑鲜花店', 456, 8720);
INSERT INTO "flowerstore" VALUES (2, '爱慕鲜花店', 123, 7155);
INSERT INTO "flowerstore" VALUES (3, '爱客鲜花店', '123a', 8000);

PRAGMA foreign_keys = true;
