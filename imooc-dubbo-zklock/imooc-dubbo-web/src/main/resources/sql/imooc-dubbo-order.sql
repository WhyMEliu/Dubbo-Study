/*
Navicat MySQL Data Transfer

Source Server         : .
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : imooc-dubbo-order

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-02-14 22:54:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` varchar(64) NOT NULL,
  `order_num` varchar(64) NOT NULL COMMENT '订单号',
  `item_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_orders_1` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('0136fca2711d4bcba33fe654ca0301a5', '0136fca2711d4bcba33fe654ca0301a5', '2');
INSERT INTO `orders` VALUES ('05303ea8347f408db5a35460a741c761', '05303ea8347f408db5a35460a741c761', '2');
INSERT INTO `orders` VALUES ('1001', '1001', '2');
INSERT INTO `orders` VALUES ('109a0a28441f4abb80ff6df1b4e5f520', '109a0a28441f4abb80ff6df1b4e5f520', '2');
INSERT INTO `orders` VALUES ('11d79dea73ff405ca09cc4c4b6d7e2fd', '11d79dea73ff405ca09cc4c4b6d7e2fd', '2');
INSERT INTO `orders` VALUES ('135d348357fb461c8c6f17c480992216', '135d348357fb461c8c6f17c480992216', '2');
INSERT INTO `orders` VALUES ('1efaf50c73874ca680b8404caeb1e3ec', '1efaf50c73874ca680b8404caeb1e3ec', '2');
INSERT INTO `orders` VALUES ('222ee71b303c4b3b8888131d06aa6b96', '222ee71b303c4b3b8888131d06aa6b96', '2');
INSERT INTO `orders` VALUES ('225e5bd163244980818d93ded227b4b0', '225e5bd163244980818d93ded227b4b0', '2');
INSERT INTO `orders` VALUES ('46e25b052c174067b1b62df27035d5d0', '46e25b052c174067b1b62df27035d5d0', '2');
INSERT INTO `orders` VALUES ('47e858b7af8440b0a84bdc650ac4e164', '47e858b7af8440b0a84bdc650ac4e164', '2');
INSERT INTO `orders` VALUES ('542dc71c56574f72835d4f3e1feec028', '542dc71c56574f72835d4f3e1feec028', '2');
INSERT INTO `orders` VALUES ('550abed0fe324d9388b896b4ce725f75', '550abed0fe324d9388b896b4ce725f75', '2');
INSERT INTO `orders` VALUES ('5623ccccf9c3493d80de4ed863093733', '5623ccccf9c3493d80de4ed863093733', '2');
INSERT INTO `orders` VALUES ('5918442caabd4ace9fbb8b2708f0b2fd', '5918442caabd4ace9fbb8b2708f0b2fd', '2');
INSERT INTO `orders` VALUES ('5f4ff543cd0e4cdea0b1233ae0ff8d1d', '5f4ff543cd0e4cdea0b1233ae0ff8d1d', '2');
INSERT INTO `orders` VALUES ('648b37aab0a548b6b4ba613731017513', '648b37aab0a548b6b4ba613731017513', '2');
INSERT INTO `orders` VALUES ('65e6dd4e77ce4c20854e8c7f176c8ba7', '65e6dd4e77ce4c20854e8c7f176c8ba7', '2');
INSERT INTO `orders` VALUES ('6e0bfaf942a547329599473e34ee4457', '6e0bfaf942a547329599473e34ee4457', '2');
INSERT INTO `orders` VALUES ('7172567ca57f4513a701e722db074208', '7172567ca57f4513a701e722db074208', '2');
INSERT INTO `orders` VALUES ('74c7312fb05445f1b5754daed9536eb6', '74c7312fb05445f1b5754daed9536eb6', '2');
INSERT INTO `orders` VALUES ('7aa8bdcdcb534935a789c6a1340a33e0', '7aa8bdcdcb534935a789c6a1340a33e0', '2');
INSERT INTO `orders` VALUES ('7c77f729ee354e7a8e4c209525f16356', '7c77f729ee354e7a8e4c209525f16356', '2');
INSERT INTO `orders` VALUES ('7e7348a6f1f9450e97610c2c44e5f72e', '7e7348a6f1f9450e97610c2c44e5f72e', '2');
INSERT INTO `orders` VALUES ('8288b4a6527d4afb999ea74c50058406', '8288b4a6527d4afb999ea74c50058406', '2');
INSERT INTO `orders` VALUES ('82d6f01484b24e81950257bc2e006ab9', '82d6f01484b24e81950257bc2e006ab9', '2');
INSERT INTO `orders` VALUES ('8a6f61ce171f4b5d8d2b9f5aa4deb7af', '8a6f61ce171f4b5d8d2b9f5aa4deb7af', '2');
INSERT INTO `orders` VALUES ('8f0dae7e12554bbf966e6eea31845f68', '8f0dae7e12554bbf966e6eea31845f68', '2');
INSERT INTO `orders` VALUES ('901c8bd1aec84ad89309a7531a5a169f', '901c8bd1aec84ad89309a7531a5a169f', '2');
INSERT INTO `orders` VALUES ('918f6fcd66da4cc38626e8dee88c00fc', '918f6fcd66da4cc38626e8dee88c00fc', '2');
INSERT INTO `orders` VALUES ('a2024a3610e3429dac30c0d24190b202', 'a2024a3610e3429dac30c0d24190b202', '2');
INSERT INTO `orders` VALUES ('ab9c37caa76f4f45b28cfb5ebd9e5b7f', 'ab9c37caa76f4f45b28cfb5ebd9e5b7f', '2');
INSERT INTO `orders` VALUES ('c31b41dcbf6e45b398d3a98b7409a62a', 'c31b41dcbf6e45b398d3a98b7409a62a', '2');
INSERT INTO `orders` VALUES ('c5f13edc4f0f4f7fad5a61e2345b3857', 'c5f13edc4f0f4f7fad5a61e2345b3857', '2');
INSERT INTO `orders` VALUES ('cbae7ebcdd904949a97e74aeb7d87b1a', 'cbae7ebcdd904949a97e74aeb7d87b1a', '2');
INSERT INTO `orders` VALUES ('cd87a028550c43219538385adf9515ed', 'cd87a028550c43219538385adf9515ed', '2');
INSERT INTO `orders` VALUES ('d3a415aaeaf34fb39b0a78f9a093e2aa', 'd3a415aaeaf34fb39b0a78f9a093e2aa', '2');
INSERT INTO `orders` VALUES ('d48095b57add49a89a5436408865888d', 'd48095b57add49a89a5436408865888d', '2');
INSERT INTO `orders` VALUES ('d53338002db34a458986913bc9f14b73', 'd53338002db34a458986913bc9f14b73', '2');
INSERT INTO `orders` VALUES ('d8fa9156c4eb4c258654e78b537a0ce6', 'd8fa9156c4eb4c258654e78b537a0ce6', '2');
INSERT INTO `orders` VALUES ('de8a89e777be4e00a774a55e987c9667', 'de8a89e777be4e00a774a55e987c9667', '2');
INSERT INTO `orders` VALUES ('e4f84426055f440c9719ea240258c82e', 'e4f84426055f440c9719ea240258c82e', '2');
INSERT INTO `orders` VALUES ('e96054829afa44189989072b60b525ab', 'e96054829afa44189989072b60b525ab', '2');
INSERT INTO `orders` VALUES ('ec68e989485043459512a75f6e9bb56e', 'ec68e989485043459512a75f6e9bb56e', '2');
