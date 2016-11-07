-- ----------------------------
-- Table structure for FeePackage
-- ----------------------------
DROP TABLE IF EXISTS `FeePackage`;
CREATE TABLE `FeePackage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '套餐ID',
  `number` varchar(10) NOT NULL COMMENT '套餐编号',
  `name` varchar(25) NOT NULL COMMENT '套餐名称',
  `description` varchar(500) NOT NULL COMMENT '套餐描述',
  `provider` varchar(50) NOT NULL COMMENT '套餐服务提供者名称',
  `phone` varchar(20) NOT NULL COMMENT '套餐服务提供者联系电话',
  `price` double(5,2) NOT NULL COMMENT '价格',
  `discount` double(5,2) NOT NULL COMMENT '当前折扣',
  `leastPurchase` int(11) NOT NULL COMMENT '最少购买商品数',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '套餐状态 0正常 1停产',
  `creator` varchar(20) NOT NULL COMMENT '套餐创建人员',
  `createTime` datetime NOT NULL COMMENT '套餐创建时间',
  `lastUpdateTime` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  `type` char(1) NOT NULL DEFAULT '0' COMMENT '套餐类型 0主体 1增值 2活动 3补充 4服务',
  `payType` char(1) NOT NULL DEFAULT '0' COMMENT '付费类型 0预付费 1后付费',
  `chargingWay` char(1) NOT NULL DEFAULT '0' COMMENT '计费方式 0累积 1当月有效',
  `stockCount` int(11) NOT NULL COMMENT '库存数量',
  `soldCount` int(11) DEFAULT '0' COMMENT '已售数量',
  `flux` double(20,2) DEFAULT NULL COMMENT '套餐内流量 单位:MB',
  PRIMARY KEY (`id`),
  KEY `idx_stockCount` (`stockCount`),
  KEY `idx_soldCount` (`soldCount`),
  KEY `idx_type` (`type`),
  KEY `idx_status` (`status`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='套餐表';

-- ----------------------------
-- Records of FeePackage
-- ----------------------------
INSERT INTO `FeePackage` VALUES ('1', '140101', '1M流量加油包', '全国流量，当月有效，次月失效；可以重复购买（重复购买时，累积到当月）\r\n', 'autophone', '13253214568', '0.02', '0.01', '1', '0', '管理员', '2016-04-19 16:09:48', '2016-05-23 15:33:40', '3', '0', '1', '98', '3', '51200.00');
INSERT INTO `FeePackage` VALUES ('2', '140102', '500M流量加油包', '全国流量，当月有效，次月失效；可以重复购买（重复购买时，累积到当月）', 'autophone', '13261254351', '0.01', '0.00', '1', '0', '管理员', '2016-04-19 16:12:39', '2016-04-19 16:13:41', '3', '0', '1', '100', '0', '512000.00');
INSERT INTO `FeePackage` VALUES ('3', '110101', '基本套餐服务A', '每月500M全国流量（超出停机），12个月的服务期', 'autophone', '13261250447', '0.01', '0.00', '6', '0', '管理员', '2016-04-19 16:15:56', '2016-06-01 14:12:43', '0', '0', '0', '9999964', '78', '512000.00');

-- ----------------------------
-- Table structure for MyPackage
-- ----------------------------
DROP TABLE IF EXISTS `MyPackage`;
CREATE TABLE `MyPackage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `telNum` varchar(255) DEFAULT NULL COMMENT '车联终端号',
  `packageId` int(11) DEFAULT NULL COMMENT '套餐id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `startTime` datetime DEFAULT NULL COMMENT '开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '结束时间',
  `packageType` int(11) DEFAULT NULL COMMENT '套餐类型 0主体 1增值 2活动 3补充 4服务',
  `chargingWay` int(11) DEFAULT NULL COMMENT '计费方式 0累积 1当月有效',
  `userId` int(11) DEFAULT NULL COMMENT '设备账户',
  PRIMARY KEY (`id`),
  KEY `forkey` (`packageId`),
  KEY `idx_packageType` (`packageType`),
  KEY `idx_userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

