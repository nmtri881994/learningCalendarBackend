-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: temp
-- ------------------------------------------------------
-- Server version	5.7.15-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dmchuc_vu`
--

DROP TABLE IF EXISTS `dmchuc_vu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmchuc_vu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ghi_chu` varchar(255) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmchuc_vu`
--

LOCK TABLES `dmchuc_vu` WRITE;
/*!40000 ALTER TABLE `dmchuc_vu` DISABLE KEYS */;
INSERT INTO `dmchuc_vu` VALUES (1,NULL,'Trưởng'),(2,NULL,'Phó'),(3,NULL,'Thư ký'),(4,NULL,'Giáo vụ'),(5,NULL,'Nhân viên'),(6,NULL,'Giáo viên');
/*!40000 ALTER TABLE `dmchuc_vu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dmdon_vi`
--

DROP TABLE IF EXISTS `dmdon_vi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmdon_vi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ma` varchar(255) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `dm_loai_don_vi_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjluc3wncqa6h2hudhholpg0i4` (`dm_loai_don_vi_id`),
  CONSTRAINT `FKjluc3wncqa6h2hudhholpg0i4` FOREIGN KEY (`dm_loai_don_vi_id`) REFERENCES `dmloai_don_vi` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmdon_vi`
--

LOCK TABLES `dmdon_vi` WRITE;
/*!40000 ALTER TABLE `dmdon_vi` DISABLE KEYS */;
INSERT INTO `dmdon_vi` VALUES (1,'CNTT','Công nghệ thông tin',1),(2,'DTVT','Điện tử viễn thông',1);
/*!40000 ALTER TABLE `dmdon_vi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dmgiang_duong`
--

DROP TABLE IF EXISTS `dmgiang_duong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmgiang_duong` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ma_giang_duong` varchar(10) NOT NULL,
  `so_luong` int(4) DEFAULT '50',
  `tang` int(11) NOT NULL,
  `ten` varchar(50) NOT NULL,
  `dm_loai_phong_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6f7ne4u17ll73phwwm6pejvnq` (`dm_loai_phong_id`),
  CONSTRAINT `FK6f7ne4u17ll73phwwm6pejvnq` FOREIGN KEY (`dm_loai_phong_id`) REFERENCES `dmloai_phong` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmgiang_duong`
--

LOCK TABLES `dmgiang_duong` WRITE;
/*!40000 ALTER TABLE `dmgiang_duong` DISABLE KEYS */;
INSERT INTO `dmgiang_duong` VALUES (2,'F102',150,1,'F102',1),(3,'F201',50,2,'F201',1),(4,'F202',150,2,'F202',1),(5,'C101',150,1,'C101',2),(6,'C102',50,1,'C102',2),(8,'C103',150,1,'C103',2),(9,'C104',50,1,'C104',2),(10,'GDTC',200,1,'GDTC',1);
/*!40000 ALTER TABLE `dmgiang_duong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dmloai_don_vi`
--

DROP TABLE IF EXISTS `dmloai_don_vi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmloai_don_vi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ghi_chu` varchar(255) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmloai_don_vi`
--

LOCK TABLES `dmloai_don_vi` WRITE;
/*!40000 ALTER TABLE `dmloai_don_vi` DISABLE KEYS */;
INSERT INTO `dmloai_don_vi` VALUES (1,NULL,'Giáo dục'),(2,NULL,'Quản lý');
/*!40000 ALTER TABLE `dmloai_don_vi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dmloai_phong`
--

DROP TABLE IF EXISTS `dmloai_phong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmloai_phong` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ten` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmloai_phong`
--

LOCK TABLES `dmloai_phong` WRITE;
/*!40000 ALTER TABLE `dmloai_phong` DISABLE KEYS */;
INSERT INTO `dmloai_phong` VALUES (1,'Dãy nhà lý thuyết'),(2,'Dãy nhà thực hành');
/*!40000 ALTER TABLE `dmloai_phong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dmlop_hoc`
--

DROP TABLE IF EXISTS `dmlop_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmlop_hoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ma` varchar(20) NOT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `tkb_khoa_khoa_hoc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3cqh9gun9k5277k5jaotobmhm` (`tkb_khoa_khoa_hoc_id`),
  CONSTRAINT `FK3cqh9gun9k5277k5jaotobmhm` FOREIGN KEY (`tkb_khoa_khoa_hoc_id`) REFERENCES `tkb_khoa_khoa_hoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmlop_hoc`
--

LOCK TABLES `dmlop_hoc` WRITE;
/*!40000 ALTER TABLE `dmlop_hoc` DISABLE KEYS */;
INSERT INTO `dmlop_hoc` VALUES (1,'12T2','12T2',17),(2,'13T2','13T2',18);
/*!40000 ALTER TABLE `dmlop_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dmlop_mon_hoc`
--

DROP TABLE IF EXISTS `dmlop_mon_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmlop_mon_hoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gioi_han_tuan_bat_dau` int(11) NOT NULL,
  `gioi_han_tuan_ket_thuc` int(11) NOT NULL,
  `so_luong_toi_da` int(4) DEFAULT '100',
  `so_tiet_ly_thuyet` int(11) NOT NULL,
  `so_tiet_thuc_hanh` int(11) NOT NULL,
  `dm_mon_hoc_id` int(11) NOT NULL,
  `dm_nganh_id` int(11) DEFAULT NULL,
  `dm_nhan_vien_id` int(11) NOT NULL,
  `tkb_khoa_khoa_hoc_id` int(11) DEFAULT NULL,
  `tkb_ki_hoc_nam_hoc_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5io2d37g6la49e0ksgeeoixva` (`dm_mon_hoc_id`),
  KEY `FKkttss23okgtm332fbc377uys1` (`dm_nganh_id`),
  KEY `FKrveiqpjxdmoidc3ksn973ffel` (`dm_nhan_vien_id`),
  KEY `FKdwh4k8vvlp6rso2u395wvq13u` (`tkb_khoa_khoa_hoc_id`),
  KEY `FK2a8x992klkdrmvu7t1490jix4` (`tkb_ki_hoc_nam_hoc_id`),
  CONSTRAINT `FK2a8x992klkdrmvu7t1490jix4` FOREIGN KEY (`tkb_ki_hoc_nam_hoc_id`) REFERENCES `tkb_ki_hoc_nam_hoc` (`id`),
  CONSTRAINT `FK5io2d37g6la49e0ksgeeoixva` FOREIGN KEY (`dm_mon_hoc_id`) REFERENCES `dmmon_hoc` (`id`),
  CONSTRAINT `FKdwh4k8vvlp6rso2u395wvq13u` FOREIGN KEY (`tkb_khoa_khoa_hoc_id`) REFERENCES `tkb_khoa_khoa_hoc` (`id`),
  CONSTRAINT `FKkttss23okgtm332fbc377uys1` FOREIGN KEY (`dm_nganh_id`) REFERENCES `dmnganh` (`id`),
  CONSTRAINT `FKrveiqpjxdmoidc3ksn973ffel` FOREIGN KEY (`dm_nhan_vien_id`) REFERENCES `dmnhan_vien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmlop_mon_hoc`
--

LOCK TABLES `dmlop_mon_hoc` WRITE;
/*!40000 ALTER TABLE `dmlop_mon_hoc` DISABLE KEYS */;
INSERT INTO `dmlop_mon_hoc` VALUES (3,2,20,100,20,10,1,1,25,18,30),(4,2,20,100,20,10,1,1,25,18,32);
/*!40000 ALTER TABLE `dmlop_mon_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dmlop_mon_hoc_sinh_vien`
--

DROP TABLE IF EXISTS `dmlop_mon_hoc_sinh_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmlop_mon_hoc_sinh_vien` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dm_lop_mon_hoc_id` int(11) NOT NULL,
  `dm_sinh_vien_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrubb7s79q6jp3l3xdnjky7mwf` (`dm_lop_mon_hoc_id`),
  KEY `FKgq0r5s4y2jgy5bifvjcgrvt4f` (`dm_sinh_vien_id`),
  CONSTRAINT `FKgq0r5s4y2jgy5bifvjcgrvt4f` FOREIGN KEY (`dm_sinh_vien_id`) REFERENCES `dmsinh_vien` (`id`),
  CONSTRAINT `FKrubb7s79q6jp3l3xdnjky7mwf` FOREIGN KEY (`dm_lop_mon_hoc_id`) REFERENCES `dmlop_mon_hoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmlop_mon_hoc_sinh_vien`
--

LOCK TABLES `dmlop_mon_hoc_sinh_vien` WRITE;
/*!40000 ALTER TABLE `dmlop_mon_hoc_sinh_vien` DISABLE KEYS */;
INSERT INTO `dmlop_mon_hoc_sinh_vien` VALUES (6,3,12),(7,4,11),(8,4,12),(11,3,11);
/*!40000 ALTER TABLE `dmlop_mon_hoc_sinh_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dmmon_hoc`
--

DROP TABLE IF EXISTS `dmmon_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmmon_hoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ma_mon_hoc` varchar(10) NOT NULL,
  `so_tin_chi` float NOT NULL,
  `ten` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmmon_hoc`
--

LOCK TABLES `dmmon_hoc` WRITE;
/*!40000 ALTER TABLE `dmmon_hoc` DISABLE KEYS */;
INSERT INTO `dmmon_hoc` VALUES (1,'THDC',2.5,'Tin học đại cương'),(2,'LTHT',3,'Lập trình hệ thống'),(3,'AV1',3,'Anh văn 1'),(4,'GDTC1',0,'Giáo dục thể chất 1'),(5,'HH',2,'Hình họa'),(6,'NL1',2,'Nguyên lý 1 (Triết)'),(7,'GT1',4,'Giải tích 1'),(8,'HDC',2,'Hóa đại cương');
/*!40000 ALTER TABLE `dmmon_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dmmon_hoc_giang_duong`
--

DROP TABLE IF EXISTS `dmmon_hoc_giang_duong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmmon_hoc_giang_duong` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dm_giang_duong_id` int(11) NOT NULL,
  `dm_mon_hoc_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1ihj2w3vn0nck04cfwm6lsl1k` (`dm_giang_duong_id`),
  KEY `FK1xabmvjaas6qvv5nym53fq41` (`dm_mon_hoc_id`),
  CONSTRAINT `FK1ihj2w3vn0nck04cfwm6lsl1k` FOREIGN KEY (`dm_giang_duong_id`) REFERENCES `dmgiang_duong` (`id`),
  CONSTRAINT `FK1xabmvjaas6qvv5nym53fq41` FOREIGN KEY (`dm_mon_hoc_id`) REFERENCES `dmmon_hoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmmon_hoc_giang_duong`
--

LOCK TABLES `dmmon_hoc_giang_duong` WRITE;
/*!40000 ALTER TABLE `dmmon_hoc_giang_duong` DISABLE KEYS */;
INSERT INTO `dmmon_hoc_giang_duong` VALUES (1,2,1),(2,3,1),(3,4,1),(4,5,1),(5,6,1),(6,8,1),(7,9,1);
/*!40000 ALTER TABLE `dmmon_hoc_giang_duong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dmnganh`
--

DROP TABLE IF EXISTS `dmnganh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmnganh` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ten` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmnganh`
--

LOCK TABLES `dmnganh` WRITE;
/*!40000 ALTER TABLE `dmnganh` DISABLE KEYS */;
INSERT INTO `dmnganh` VALUES (1,'Công nghệ phần mềm'),(2,'Hệ thống thông tin'),(3,'Hệ thống nhúng'),(4,'Mạng'),(5,'Điện tử'),(6,'Viễn thông');
/*!40000 ALTER TABLE `dmnganh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dmnhan_vien`
--

DROP TABLE IF EXISTS `dmnhan_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmnhan_vien` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ho_dem` varchar(50) NOT NULL,
  `ma_nhan_vien` varchar(20) DEFAULT NULL,
  `ten` varchar(20) NOT NULL,
  `dm_don_vi_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKx0e5g5k5sh793vp8tmkhq3vw` (`dm_don_vi_id`),
  CONSTRAINT `FKx0e5g5k5sh793vp8tmkhq3vw` FOREIGN KEY (`dm_don_vi_id`) REFERENCES `dmdon_vi` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmnhan_vien`
--

LOCK TABLES `dmnhan_vien` WRITE;
/*!40000 ALTER TABLE `dmnhan_vien` DISABLE KEYS */;
INSERT INTO `dmnhan_vien` VALUES (25,'Huỳnh Hữu','111222333','Hưng',1),(26,'Mai Văn','111111111','Hà',1),(27,'Trần Thị Thùy',NULL,'Hương',1),(28,'Trần',NULL,'Chín',1),(29,'Khoa giáo dục thể chất',NULL,'',1),(30,'Phan',NULL,'Tường',1),(31,'Ngô Minh',NULL,'Đức',1),(32,'Lê Hữu',NULL,'Ái',1),(33,'Nguyễn Văn','111111','Nguyên',1);
/*!40000 ALTER TABLE `dmnhan_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dmnhan_vien_chuc_vu`
--

DROP TABLE IF EXISTS `dmnhan_vien_chuc_vu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmnhan_vien_chuc_vu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dm_chuc_vu_id` int(11) DEFAULT NULL,
  `dm_nhan_vien_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkd3k3se4b5gb6i8i74bj50jae` (`dm_chuc_vu_id`),
  KEY `FKl9ux45h44g0uki17mxem5b409` (`dm_nhan_vien_id`),
  CONSTRAINT `FKkd3k3se4b5gb6i8i74bj50jae` FOREIGN KEY (`dm_chuc_vu_id`) REFERENCES `dmchuc_vu` (`id`),
  CONSTRAINT `FKl9ux45h44g0uki17mxem5b409` FOREIGN KEY (`dm_nhan_vien_id`) REFERENCES `dmnhan_vien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmnhan_vien_chuc_vu`
--

LOCK TABLES `dmnhan_vien_chuc_vu` WRITE;
/*!40000 ALTER TABLE `dmnhan_vien_chuc_vu` DISABLE KEYS */;
INSERT INTO `dmnhan_vien_chuc_vu` VALUES (1,4,25),(2,6,25),(3,6,26),(4,4,33),(5,6,33);
/*!40000 ALTER TABLE `dmnhan_vien_chuc_vu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dmsinh_vien`
--

DROP TABLE IF EXISTS `dmsinh_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dmsinh_vien` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ho_dem` varchar(50) NOT NULL,
  `ma_sinh_vien` varchar(25) NOT NULL,
  `ten` varchar(20) NOT NULL,
  `dm_lop_hoc_id` int(11) NOT NULL,
  `dm_nganh_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKor8mnp36qn98eej606osdic2t` (`dm_lop_hoc_id`),
  KEY `FKksnvtr0k9vt5dhn1x4nkrpnjm` (`dm_nganh_id`),
  CONSTRAINT `FKksnvtr0k9vt5dhn1x4nkrpnjm` FOREIGN KEY (`dm_nganh_id`) REFERENCES `dmnganh` (`id`),
  CONSTRAINT `FKor8mnp36qn98eej606osdic2t` FOREIGN KEY (`dm_lop_hoc_id`) REFERENCES `dmlop_hoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmsinh_vien`
--

LOCK TABLES `dmsinh_vien` WRITE;
/*!40000 ALTER TABLE `dmsinh_vien` DISABLE KEYS */;
INSERT INTO `dmsinh_vien` VALUES (10,'Nguyễn Minh','102120160','Trí',1,1),(11,'Nguyễn Huy','102120161','Hùng',2,1),(12,'Nguyễn Hải','102120162','Phong',2,1);
/*!40000 ALTER TABLE `dmsinh_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tk_chuc_nang`
--

DROP TABLE IF EXISTS `tk_chuc_nang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_chuc_nang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ten_chuc_nang` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tk_chuc_nang`
--

LOCK TABLES `tk_chuc_nang` WRITE;
/*!40000 ALTER TABLE `tk_chuc_nang` DISABLE KEYS */;
/*!40000 ALTER TABLE `tk_chuc_nang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tk_tai_khoan_he_thong`
--

DROP TABLE IF EXISTS `tk_tai_khoan_he_thong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_tai_khoan_he_thong` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ho_va_ten` varchar(70) NOT NULL,
  `mat_khau` varchar(50) NOT NULL,
  `ten_dang_nhap` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tk_tai_khoan_he_thong`
--

LOCK TABLES `tk_tai_khoan_he_thong` WRITE;
/*!40000 ALTER TABLE `tk_tai_khoan_he_thong` DISABLE KEYS */;
INSERT INTO `tk_tai_khoan_he_thong` VALUES (1,'Nguyễn Minh Trí','123456','102120160'),(3,'Huỳnh Hữu Hưng','123456','111222333'),(4,'Mai Văn Hà','123456','111111111'),(5,'Nguyễn Văn Nguyên','123456','111111'),(6,'Nguyễn Huy Hùng','123456','102120161'),(7,'Nguyễn Hải Phong','123456','102120162');
/*!40000 ALTER TABLE `tk_tai_khoan_he_thong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tk_tai_khoan_he_thong_vai_tro`
--

DROP TABLE IF EXISTS `tk_tai_khoan_he_thong_vai_tro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_tai_khoan_he_thong_vai_tro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tk_tai_khoan_he_thong_id` int(11) DEFAULT NULL,
  `tk_vai_tro_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq8a66xdr9w4tcx95rk27c27uo` (`tk_tai_khoan_he_thong_id`),
  KEY `FKim6lnsbuactsjmyjll30g247h` (`tk_vai_tro_id`),
  CONSTRAINT `FKim6lnsbuactsjmyjll30g247h` FOREIGN KEY (`tk_vai_tro_id`) REFERENCES `tk_vai_tro` (`id`),
  CONSTRAINT `FKq8a66xdr9w4tcx95rk27c27uo` FOREIGN KEY (`tk_tai_khoan_he_thong_id`) REFERENCES `tk_tai_khoan_he_thong` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tk_tai_khoan_he_thong_vai_tro`
--

LOCK TABLES `tk_tai_khoan_he_thong_vai_tro` WRITE;
/*!40000 ALTER TABLE `tk_tai_khoan_he_thong_vai_tro` DISABLE KEYS */;
INSERT INTO `tk_tai_khoan_he_thong_vai_tro` VALUES (1,1,1),(2,3,2),(3,3,3),(4,5,3),(5,6,1),(6,7,1),(7,4,2),(8,5,2);
/*!40000 ALTER TABLE `tk_tai_khoan_he_thong_vai_tro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tk_vai_tro`
--

DROP TABLE IF EXISTS `tk_vai_tro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_vai_tro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ten_vai_tro` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tk_vai_tro`
--

LOCK TABLES `tk_vai_tro` WRITE;
/*!40000 ALTER TABLE `tk_vai_tro` DISABLE KEYS */;
INSERT INTO `tk_vai_tro` VALUES (1,'ROLE_SINHVIEN'),(2,'ROLE_GIANGVIEN'),(3,'ROLE_GIAOVU');
/*!40000 ALTER TABLE `tk_vai_tro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tk_vai_tro_chuc_nang`
--

DROP TABLE IF EXISTS `tk_vai_tro_chuc_nang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_vai_tro_chuc_nang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tk_chuc_nang_id` int(11) NOT NULL,
  `tk_vai_tro_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgytvhd7hj2gof3g82e2ti5jla` (`tk_chuc_nang_id`),
  KEY `FKt221sd41fyw30qyjh9tof6l2s` (`tk_vai_tro_id`),
  CONSTRAINT `FKgytvhd7hj2gof3g82e2ti5jla` FOREIGN KEY (`tk_chuc_nang_id`) REFERENCES `tk_chuc_nang` (`id`),
  CONSTRAINT `FKt221sd41fyw30qyjh9tof6l2s` FOREIGN KEY (`tk_vai_tro_id`) REFERENCES `tk_vai_tro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tk_vai_tro_chuc_nang`
--

LOCK TABLES `tk_vai_tro_chuc_nang` WRITE;
/*!40000 ALTER TABLE `tk_vai_tro_chuc_nang` DISABLE KEYS */;
/*!40000 ALTER TABLE `tk_vai_tro_chuc_nang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_dieu_kien_tu_dong`
--

DROP TABLE IF EXISTS `tkb_dieu_kien_tu_dong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_dieu_kien_tu_dong` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bat_buoc` int(11) DEFAULT NULL,
  `ma` int(11) NOT NULL,
  `max_point` int(11) NOT NULL,
  `min_point` int(11) NOT NULL,
  `noi_dung` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_dieu_kien_tu_dong`
--

LOCK TABLES `tkb_dieu_kien_tu_dong` WRITE;
/*!40000 ALTER TABLE `tkb_dieu_kien_tu_dong` DISABLE KEYS */;
INSERT INTO `tkb_dieu_kien_tu_dong` VALUES (1,0,1,30,10,'Giáo viên dạy không quá 8 giờ/ngày'),(2,0,2,30,10,'Giáo viên dạy không quá 6 giờ lý thuyết/ngày'),(3,0,3,35,15,'Giáo viên dạy không quá 30 giờ lý thuyết/tuần'),(4,0,4,40,20,'Lớp môn học không xung đột với ngày nghỉ trong tuần của giáo viên'),(5,0,5,50,30,'Các lịch của một lớp môn học không trùng thứ'),(6,1,6,200,100,'Lớp môn học không xung đột với lịch của giáo viên'),(7,1,7,200,100,'Lớp môn học không xung đột với lịch của lớp khác cùng khoa-khóa học-ngành'),(8,1,8,200,100,'Phòng học không có nhiều hơn 1 lớp trong cùng 1 khoảng thời gian'),(9,1,9,200,100,'1 khoa-khóa học-ngành không có nhiều hơn 3 lớp học cùng 1 khoảng thời gian');
/*!40000 ALTER TABLE `tkb_dieu_kien_tu_dong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_khoa_hoc`
--

DROP TABLE IF EXISTS `tkb_khoa_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_khoa_hoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nam` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_khoa_hoc`
--

LOCK TABLES `tkb_khoa_hoc` WRITE;
/*!40000 ALTER TABLE `tkb_khoa_hoc` DISABLE KEYS */;
INSERT INTO `tkb_khoa_hoc` VALUES (1,2012),(2,2013),(3,2014),(4,2015),(5,2016);
/*!40000 ALTER TABLE `tkb_khoa_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_khoa_khoa_hoc`
--

DROP TABLE IF EXISTS `tkb_khoa_khoa_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_khoa_khoa_hoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dm_don_vi_id` int(11) DEFAULT NULL,
  `ki_bat_dau_id` int(11) DEFAULT NULL,
  `ki_ket_thuc_id` int(11) DEFAULT NULL,
  `ki_phan_nganh_id` int(11) DEFAULT NULL,
  `tkb_khoa_hoc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1f9sipyrdj9jmanv9hpd4kkal` (`dm_don_vi_id`),
  KEY `FKcr2cll15xgdwq35h65hx2lfkn` (`ki_bat_dau_id`),
  KEY `FK9pplc4vhrq07g0v53hrovmdj2` (`ki_ket_thuc_id`),
  KEY `FK8dux9yvsbu0cde0o658tyegwk` (`ki_phan_nganh_id`),
  KEY `FK4tvjeo184h2rj9nsme6ick364` (`tkb_khoa_hoc_id`),
  CONSTRAINT `FK1f9sipyrdj9jmanv9hpd4kkal` FOREIGN KEY (`dm_don_vi_id`) REFERENCES `dmdon_vi` (`id`),
  CONSTRAINT `FK4tvjeo184h2rj9nsme6ick364` FOREIGN KEY (`tkb_khoa_hoc_id`) REFERENCES `tkb_khoa_hoc` (`id`),
  CONSTRAINT `FK8dux9yvsbu0cde0o658tyegwk` FOREIGN KEY (`ki_phan_nganh_id`) REFERENCES `tkb_ki_hoc_nam_hoc` (`id`),
  CONSTRAINT `FK9pplc4vhrq07g0v53hrovmdj2` FOREIGN KEY (`ki_ket_thuc_id`) REFERENCES `tkb_ki_hoc_nam_hoc` (`id`),
  CONSTRAINT `FKcr2cll15xgdwq35h65hx2lfkn` FOREIGN KEY (`ki_bat_dau_id`) REFERENCES `tkb_ki_hoc_nam_hoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_khoa_khoa_hoc`
--

LOCK TABLES `tkb_khoa_khoa_hoc` WRITE;
/*!40000 ALTER TABLE `tkb_khoa_khoa_hoc` DISABLE KEYS */;
INSERT INTO `tkb_khoa_khoa_hoc` VALUES (17,1,33,29,28,1),(18,1,36,31,30,2),(19,1,39,47,46,3),(20,1,42,50,49,4),(21,1,28,53,52,5),(22,2,39,47,46,3),(23,2,42,50,49,4),(24,2,28,53,52,5);
/*!40000 ALTER TABLE `tkb_khoa_khoa_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_khoa_khoa_hoc_nganh`
--

DROP TABLE IF EXISTS `tkb_khoa_khoa_hoc_nganh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_khoa_khoa_hoc_nganh` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dm_nganh_id` int(11) DEFAULT NULL,
  `tkb_khoa_khoa_hoc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpy8ueyebx4xdmv58knm00nfc2` (`dm_nganh_id`),
  KEY `FKbfuu1vlqlj0ris8mfdclr4amt` (`tkb_khoa_khoa_hoc_id`),
  CONSTRAINT `FKbfuu1vlqlj0ris8mfdclr4amt` FOREIGN KEY (`tkb_khoa_khoa_hoc_id`) REFERENCES `tkb_khoa_khoa_hoc` (`id`),
  CONSTRAINT `FKpy8ueyebx4xdmv58knm00nfc2` FOREIGN KEY (`dm_nganh_id`) REFERENCES `dmnganh` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_khoa_khoa_hoc_nganh`
--

LOCK TABLES `tkb_khoa_khoa_hoc_nganh` WRITE;
/*!40000 ALTER TABLE `tkb_khoa_khoa_hoc_nganh` DISABLE KEYS */;
INSERT INTO `tkb_khoa_khoa_hoc_nganh` VALUES (5,1,18),(6,2,18),(7,3,18),(8,4,18);
/*!40000 ALTER TABLE `tkb_khoa_khoa_hoc_nganh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_ki_hoc`
--

DROP TABLE IF EXISTS `tkb_ki_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_ki_hoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ten` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_ki_hoc`
--

LOCK TABLES `tkb_ki_hoc` WRITE;
/*!40000 ALTER TABLE `tkb_ki_hoc` DISABLE KEYS */;
INSERT INTO `tkb_ki_hoc` VALUES (1,'Kỳ 1'),(2,'Kỳ 2'),(3,'Kỳ hè');
/*!40000 ALTER TABLE `tkb_ki_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_ki_hoc_nam_hoc`
--

DROP TABLE IF EXISTS `tkb_ki_hoc_nam_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_ki_hoc_nam_hoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `da_sinh_lich` bit(1) DEFAULT b'0',
  `ngay_bat_dau` date NOT NULL,
  `ngay_ket_thuc` date NOT NULL,
  `tkb_ki_hoc_id` int(11) NOT NULL,
  `tkb_nam_hoc_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKarhqm9u9i1wu1cox1353bhso5` (`tkb_ki_hoc_id`),
  KEY `FKbytxqvushxdlco60vedt5si1` (`tkb_nam_hoc_id`),
  CONSTRAINT `FKarhqm9u9i1wu1cox1353bhso5` FOREIGN KEY (`tkb_ki_hoc_id`) REFERENCES `tkb_ki_hoc` (`id`),
  CONSTRAINT `FKbytxqvushxdlco60vedt5si1` FOREIGN KEY (`tkb_nam_hoc_id`) REFERENCES `tkb_nam_hoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_ki_hoc_nam_hoc`
--

LOCK TABLES `tkb_ki_hoc_nam_hoc` WRITE;
/*!40000 ALTER TABLE `tkb_ki_hoc_nam_hoc` DISABLE KEYS */;
INSERT INTO `tkb_ki_hoc_nam_hoc` VALUES (28,'\0','2016-08-15','2016-12-25',1,1),(29,'\0','2017-01-02','2017-05-28',2,1),(30,'\0','2017-08-15','2017-12-25',1,3),(31,'\0','2018-01-02','2018-05-28',2,3),(32,'\0','2017-06-05','2017-08-01',3,1),(33,'\0','2012-08-15','2012-12-25',1,7),(34,'\0','2013-01-02','2013-05-28',2,7),(35,'\0','2013-05-28','2013-08-01',3,7),(36,'\0','2013-08-15','2013-12-25',1,8),(37,'\0','2014-01-02','2014-05-28',2,8),(38,'\0','2014-05-28','2014-08-01',3,8),(39,'\0','2014-08-15','2014-12-25',1,9),(40,'\0','2015-01-02','2015-05-28',2,9),(41,'\0','2015-05-28','2015-08-01',3,9),(42,'\0','2015-08-15','2015-12-25',1,2),(43,'\0','2016-01-02','2016-05-28',2,2),(44,'\0','2016-05-28','2016-08-01',3,2),(45,'\0','2018-05-28','2018-08-01',3,3),(46,'\0','2018-08-15','2018-12-25',1,4),(47,'\0','2019-01-02','2019-05-28',2,4),(48,'\0','2019-05-28','2019-08-01',3,4),(49,'\0','2019-08-15','2019-12-25',1,5),(50,'\0','2020-01-02','2020-05-28',2,5),(51,'\0','2020-05-28','2020-08-01',3,5),(52,'\0','2020-08-15','2020-12-25',1,6),(53,'\0','2021-01-02','2021-05-28',2,6),(54,'\0','2021-05-28','2021-08-01',3,6);
/*!40000 ALTER TABLE `tkb_ki_hoc_nam_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_ki_hoc_nam_hoc_dieu_kien`
--

DROP TABLE IF EXISTS `tkb_ki_hoc_nam_hoc_dieu_kien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_ki_hoc_nam_hoc_dieu_kien` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tkb_dieu_kien_tu_dong_id` int(11) DEFAULT NULL,
  `tkb_ki_hoc_nam_hoc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd139q2uiwhuxu1gkg6d0pj50h` (`tkb_dieu_kien_tu_dong_id`),
  KEY `FKbl3heto8flcoobtor16ds2o8l` (`tkb_ki_hoc_nam_hoc_id`),
  CONSTRAINT `FKbl3heto8flcoobtor16ds2o8l` FOREIGN KEY (`tkb_ki_hoc_nam_hoc_id`) REFERENCES `tkb_ki_hoc_nam_hoc` (`id`),
  CONSTRAINT `FKd139q2uiwhuxu1gkg6d0pj50h` FOREIGN KEY (`tkb_dieu_kien_tu_dong_id`) REFERENCES `tkb_dieu_kien_tu_dong` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_ki_hoc_nam_hoc_dieu_kien`
--

LOCK TABLES `tkb_ki_hoc_nam_hoc_dieu_kien` WRITE;
/*!40000 ALTER TABLE `tkb_ki_hoc_nam_hoc_dieu_kien` DISABLE KEYS */;
INSERT INTO `tkb_ki_hoc_nam_hoc_dieu_kien` VALUES (1,1,30),(2,2,30),(3,3,30),(4,4,30),(5,5,30),(6,6,30),(7,7,30),(8,8,30),(9,9,30);
/*!40000 ALTER TABLE `tkb_ki_hoc_nam_hoc_dieu_kien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_lich_hoc_theo_ngay`
--

DROP TABLE IF EXISTS `tkb_lich_hoc_theo_ngay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_lich_hoc_theo_ngay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `giao_vien_ghi_chu` varchar(500) DEFAULT NULL,
  `giao_vien_nhan` varchar(500) DEFAULT NULL,
  `ngay` date DEFAULT NULL,
  `thi_cuoi_ky` bit(1) DEFAULT b'0',
  `thi_giua_ky` bit(1) DEFAULT b'0',
  `dm_giang_duong_id` int(11) NOT NULL,
  `dm_lop_mon_hoc_id` int(11) NOT NULL,
  `tkb_thu_id` int(11) NOT NULL,
  `tkb_tiet_cuoi_cung_id` int(11) NOT NULL,
  `tkb_tiet_dau_tien_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7a01ydw23qkdb5atjtat7jj6j` (`dm_giang_duong_id`),
  KEY `FKqk9uf0nh9uduirjo0ogvwcjou` (`dm_lop_mon_hoc_id`),
  KEY `FKri4tuosa4rgbrhdrbkim716qg` (`tkb_thu_id`),
  KEY `FK1yqbqahyisn3x9qs2p5db8p9e` (`tkb_tiet_cuoi_cung_id`),
  KEY `FKsu2nn7gjf8tvmnsnx2kp2p1uw` (`tkb_tiet_dau_tien_id`),
  CONSTRAINT `FK1yqbqahyisn3x9qs2p5db8p9e` FOREIGN KEY (`tkb_tiet_cuoi_cung_id`) REFERENCES `tkb_tiet` (`id`),
  CONSTRAINT `FK7a01ydw23qkdb5atjtat7jj6j` FOREIGN KEY (`dm_giang_duong_id`) REFERENCES `dmgiang_duong` (`id`),
  CONSTRAINT `FKqk9uf0nh9uduirjo0ogvwcjou` FOREIGN KEY (`dm_lop_mon_hoc_id`) REFERENCES `dmlop_mon_hoc` (`id`),
  CONSTRAINT `FKri4tuosa4rgbrhdrbkim716qg` FOREIGN KEY (`tkb_thu_id`) REFERENCES `tkb_thu` (`id`),
  CONSTRAINT `FKsu2nn7gjf8tvmnsnx2kp2p1uw` FOREIGN KEY (`tkb_tiet_dau_tien_id`) REFERENCES `tkb_tiet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_lich_hoc_theo_ngay`
--

LOCK TABLES `tkb_lich_hoc_theo_ngay` WRITE;
/*!40000 ALTER TABLE `tkb_lich_hoc_theo_ngay` DISABLE KEYS */;
INSERT INTO `tkb_lich_hoc_theo_ngay` VALUES (1,'dsaaaaaaaaaaaaaaaaaaaaaaa','dsadsa','2017-07-08','\0','\0',4,3,7,5,1);
/*!40000 ALTER TABLE `tkb_lich_hoc_theo_ngay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_lich_hoc_theo_ngay_diem_danh`
--

DROP TABLE IF EXISTS `tkb_lich_hoc_theo_ngay_diem_danh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_lich_hoc_theo_ngay_diem_danh` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `presented` bit(1) NOT NULL,
  `dm_sinh_vien_id` int(11) DEFAULT NULL,
  `tkb_lich_hoc_theo_ngay_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKilvsobe8aqbuy93ce5q61o0au` (`dm_sinh_vien_id`),
  KEY `FK8kkwug6rtl0qaa4n85dq6bh6m` (`tkb_lich_hoc_theo_ngay_id`),
  CONSTRAINT `FK8kkwug6rtl0qaa4n85dq6bh6m` FOREIGN KEY (`tkb_lich_hoc_theo_ngay_id`) REFERENCES `tkb_lich_hoc_theo_ngay` (`id`),
  CONSTRAINT `FKilvsobe8aqbuy93ce5q61o0au` FOREIGN KEY (`dm_sinh_vien_id`) REFERENCES `dmsinh_vien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_lich_hoc_theo_ngay_diem_danh`
--

LOCK TABLES `tkb_lich_hoc_theo_ngay_diem_danh` WRITE;
/*!40000 ALTER TABLE `tkb_lich_hoc_theo_ngay_diem_danh` DISABLE KEYS */;
INSERT INTO `tkb_lich_hoc_theo_ngay_diem_danh` VALUES (2,'\0',11,1),(3,'\0',12,1);
/*!40000 ALTER TABLE `tkb_lich_hoc_theo_ngay_diem_danh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_lich_hoc_theo_ngay_sinh_vien_ghi_chu`
--

DROP TABLE IF EXISTS `tkb_lich_hoc_theo_ngay_sinh_vien_ghi_chu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_lich_hoc_theo_ngay_sinh_vien_ghi_chu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sinh_vien_ghi_chu` varchar(255) DEFAULT NULL,
  `dm_sinh_vien_id` int(11) DEFAULT NULL,
  `tkb_lich_hoc_theo_ngay_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg1nfb3llihxkoup7trc17kxdm` (`dm_sinh_vien_id`),
  KEY `FKqv1ctc4vtq6dafustamxe1wth` (`tkb_lich_hoc_theo_ngay_id`),
  CONSTRAINT `FKg1nfb3llihxkoup7trc17kxdm` FOREIGN KEY (`dm_sinh_vien_id`) REFERENCES `dmsinh_vien` (`id`),
  CONSTRAINT `FKqv1ctc4vtq6dafustamxe1wth` FOREIGN KEY (`tkb_lich_hoc_theo_ngay_id`) REFERENCES `tkb_lich_hoc_theo_ngay` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_lich_hoc_theo_ngay_sinh_vien_ghi_chu`
--

LOCK TABLES `tkb_lich_hoc_theo_ngay_sinh_vien_ghi_chu` WRITE;
/*!40000 ALTER TABLE `tkb_lich_hoc_theo_ngay_sinh_vien_ghi_chu` DISABLE KEYS */;
INSERT INTO `tkb_lich_hoc_theo_ngay_sinh_vien_ghi_chu` VALUES (1,'dsadsa',11,1);
/*!40000 ALTER TABLE `tkb_lich_hoc_theo_ngay_sinh_vien_ghi_chu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_lich_hoc_theo_tuan`
--

DROP TABLE IF EXISTS `tkb_lich_hoc_theo_tuan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_lich_hoc_theo_tuan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `so_tiet` int(11) NOT NULL,
  `tuan_bat_dau` int(11) NOT NULL,
  `tuan_ket_thuc` int(11) NOT NULL,
  `dm_giang_duong_id` int(11) DEFAULT NULL,
  `dm_lop_mon_hoc_id` int(11) DEFAULT NULL,
  `tkb_thu_id` int(11) DEFAULT NULL,
  `tkb_tiet_cuoi_cung_id` int(11) NOT NULL,
  `tkb_tiet_dau_tien_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe6rbx6o3sn2cagda79begl5op` (`dm_giang_duong_id`),
  KEY `FKorq1rygvg1ppxybborawi0uja` (`dm_lop_mon_hoc_id`),
  KEY `FKlb84g157ps26arusu6mkxtiwo` (`tkb_thu_id`),
  KEY `FKdy1a1c37p890pmgug39vpffrs` (`tkb_tiet_cuoi_cung_id`),
  KEY `FKowxm64ugqoo7kxa2w2xbuvsls` (`tkb_tiet_dau_tien_id`),
  CONSTRAINT `FKdy1a1c37p890pmgug39vpffrs` FOREIGN KEY (`tkb_tiet_cuoi_cung_id`) REFERENCES `tkb_tiet` (`id`),
  CONSTRAINT `FKe6rbx6o3sn2cagda79begl5op` FOREIGN KEY (`dm_giang_duong_id`) REFERENCES `dmgiang_duong` (`id`),
  CONSTRAINT `FKlb84g157ps26arusu6mkxtiwo` FOREIGN KEY (`tkb_thu_id`) REFERENCES `tkb_thu` (`id`),
  CONSTRAINT `FKorq1rygvg1ppxybborawi0uja` FOREIGN KEY (`dm_lop_mon_hoc_id`) REFERENCES `dmlop_mon_hoc` (`id`),
  CONSTRAINT `FKowxm64ugqoo7kxa2w2xbuvsls` FOREIGN KEY (`tkb_tiet_dau_tien_id`) REFERENCES `tkb_tiet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_lich_hoc_theo_tuan`
--

LOCK TABLES `tkb_lich_hoc_theo_tuan` WRITE;
/*!40000 ALTER TABLE `tkb_lich_hoc_theo_tuan` DISABLE KEYS */;
INSERT INTO `tkb_lich_hoc_theo_tuan` VALUES (5,2,2,4,2,4,3,7,6),(6,1,2,4,8,4,6,8,7),(7,2,5,7,2,4,6,7,6),(8,1,5,7,8,4,5,4,1),(9,2,8,11,2,4,6,7,6),(10,1,8,11,8,4,5,4,4),(11,0,1,6,2,3,2,4,1);
/*!40000 ALTER TABLE `tkb_lich_hoc_theo_tuan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_lich_nghi_cua_nhan_vien`
--

DROP TABLE IF EXISTS `tkb_lich_nghi_cua_nhan_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_lich_nghi_cua_nhan_vien` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ngay` date DEFAULT NULL,
  `dm_nhan_vien_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9x5s3l5wrbw23yrikegqqrsrn` (`dm_nhan_vien_id`),
  CONSTRAINT `FK9x5s3l5wrbw23yrikegqqrsrn` FOREIGN KEY (`dm_nhan_vien_id`) REFERENCES `dmnhan_vien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_lich_nghi_cua_nhan_vien`
--

LOCK TABLES `tkb_lich_nghi_cua_nhan_vien` WRITE;
/*!40000 ALTER TABLE `tkb_lich_nghi_cua_nhan_vien` DISABLE KEYS */;
/*!40000 ALTER TABLE `tkb_lich_nghi_cua_nhan_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_lich_nghi_cua_truong`
--

DROP TABLE IF EXISTS `tkb_lich_nghi_cua_truong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_lich_nghi_cua_truong` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ngay` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_lich_nghi_cua_truong`
--

LOCK TABLES `tkb_lich_nghi_cua_truong` WRITE;
/*!40000 ALTER TABLE `tkb_lich_nghi_cua_truong` DISABLE KEYS */;
/*!40000 ALTER TABLE `tkb_lich_nghi_cua_truong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_nam_hoc`
--

DROP TABLE IF EXISTS `tkb_nam_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_nam_hoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `ngay_bat_dau` date DEFAULT NULL,
  `ngay_ket_thuc` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_nam_hoc`
--

LOCK TABLES `tkb_nam_hoc` WRITE;
/*!40000 ALTER TABLE `tkb_nam_hoc` DISABLE KEYS */;
INSERT INTO `tkb_nam_hoc` VALUES (1,'2016-2017','2016-08-15','2017-08-06'),(2,'2015-2016','2015-08-15','2016-08-06'),(3,'2017-2018','2017-08-15','2018-08-06'),(4,'2018-2019','2018-08-15','2019-08-06'),(5,'2019-2020','2019-08-15','2020-08-06'),(6,'2020-2021','2020-08-15','2021-08-06'),(7,'2012-2013','2012-08-15','2013-08-06'),(8,'2013-2014','2013-08-15','2014-08-06'),(9,'2014-2015','2014-08-15','2015-08-06');
/*!40000 ALTER TABLE `tkb_nam_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_nhan_vien_ngay_nghi_trong_tuan`
--

DROP TABLE IF EXISTS `tkb_nhan_vien_ngay_nghi_trong_tuan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_nhan_vien_ngay_nghi_trong_tuan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dm_nhan_vien_id` int(11) DEFAULT NULL,
  `tkb_thu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd46aqnhg97r7v22rbmop57y5l` (`dm_nhan_vien_id`),
  KEY `FKgsf8c38s12ujnrs8jdgy8hw7b` (`tkb_thu_id`),
  CONSTRAINT `FKd46aqnhg97r7v22rbmop57y5l` FOREIGN KEY (`dm_nhan_vien_id`) REFERENCES `dmnhan_vien` (`id`),
  CONSTRAINT `FKgsf8c38s12ujnrs8jdgy8hw7b` FOREIGN KEY (`tkb_thu_id`) REFERENCES `tkb_thu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_nhan_vien_ngay_nghi_trong_tuan`
--

LOCK TABLES `tkb_nhan_vien_ngay_nghi_trong_tuan` WRITE;
/*!40000 ALTER TABLE `tkb_nhan_vien_ngay_nghi_trong_tuan` DISABLE KEYS */;
INSERT INTO `tkb_nhan_vien_ngay_nghi_trong_tuan` VALUES (1,25,2),(2,26,3);
/*!40000 ALTER TABLE `tkb_nhan_vien_ngay_nghi_trong_tuan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_sinh_vien_lo_trinh_mon_hoc`
--

DROP TABLE IF EXISTS `tkb_sinh_vien_lo_trinh_mon_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_sinh_vien_lo_trinh_mon_hoc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `co_the_dang_ky` bit(1) DEFAULT NULL,
  `ki` int(11) NOT NULL,
  `dm_mon_hoc_id` int(11) NOT NULL,
  `dm_sinh_vien_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKskf9p7dh3l8k74u4iwohi9lyq` (`dm_mon_hoc_id`),
  KEY `FKi8fmjf9cq0ojj5ghx5lu1ke8a` (`dm_sinh_vien_id`),
  CONSTRAINT `FKi8fmjf9cq0ojj5ghx5lu1ke8a` FOREIGN KEY (`dm_sinh_vien_id`) REFERENCES `dmsinh_vien` (`id`),
  CONSTRAINT `FKskf9p7dh3l8k74u4iwohi9lyq` FOREIGN KEY (`dm_mon_hoc_id`) REFERENCES `dmmon_hoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_sinh_vien_lo_trinh_mon_hoc`
--

LOCK TABLES `tkb_sinh_vien_lo_trinh_mon_hoc` WRITE;
/*!40000 ALTER TABLE `tkb_sinh_vien_lo_trinh_mon_hoc` DISABLE KEYS */;
INSERT INTO `tkb_sinh_vien_lo_trinh_mon_hoc` VALUES (1,'',3,1,11),(2,'',3,1,12);
/*!40000 ALTER TABLE `tkb_sinh_vien_lo_trinh_mon_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_thoi_gian_dang_ky`
--

DROP TABLE IF EXISTS `tkb_thoi_gian_dang_ky`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_thoi_gian_dang_ky` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `tkb_khoa_khoa_hoc_id` int(11) DEFAULT NULL,
  `tkb_ki_hoc_nam_hoc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK53c2t2m4r43qle9ftu1opsdme` (`tkb_khoa_khoa_hoc_id`),
  KEY `FK7teel9p7l4cphl1ew3uwb5dn2` (`tkb_ki_hoc_nam_hoc_id`),
  CONSTRAINT `FK53c2t2m4r43qle9ftu1opsdme` FOREIGN KEY (`tkb_khoa_khoa_hoc_id`) REFERENCES `tkb_khoa_khoa_hoc` (`id`),
  CONSTRAINT `FK7teel9p7l4cphl1ew3uwb5dn2` FOREIGN KEY (`tkb_ki_hoc_nam_hoc_id`) REFERENCES `tkb_ki_hoc_nam_hoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_thoi_gian_dang_ky`
--

LOCK TABLES `tkb_thoi_gian_dang_ky` WRITE;
/*!40000 ALTER TABLE `tkb_thoi_gian_dang_ky` DISABLE KEYS */;
INSERT INTO `tkb_thoi_gian_dang_ky` VALUES (1,'2017-08-08 20:00:00','2017-06-06 07:30:00','',18,30);
/*!40000 ALTER TABLE `tkb_thoi_gian_dang_ky` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_thu`
--

DROP TABLE IF EXISTS `tkb_thu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_thu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ten` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_thu`
--

LOCK TABLES `tkb_thu` WRITE;
/*!40000 ALTER TABLE `tkb_thu` DISABLE KEYS */;
INSERT INTO `tkb_thu` VALUES (2,'Thứ 2'),(3,'Thứ 3'),(4,'Thứ 4'),(5,'Thứ 5'),(6,'Thứ 6'),(7,'Thứ 7'),(8,'Chủ nhật');
/*!40000 ALTER TABLE `tkb_thu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_tiet`
--

DROP TABLE IF EXISTS `tkb_tiet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_tiet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ten` varchar(20) NOT NULL,
  `thu_tu` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_tiet`
--

LOCK TABLES `tkb_tiet` WRITE;
/*!40000 ALTER TABLE `tkb_tiet` DISABLE KEYS */;
INSERT INTO `tkb_tiet` VALUES (1,'Tiết 1',1),(2,'Tiết 2',2),(3,'Tiết 3',3),(4,'Tiết 4',4),(5,'Tiết 5',5),(6,'Tiết 6',6),(7,'Tiết 7',7),(8,'Tiết 8',8),(9,'Tiết 9',9),(10,'Tiết 10',10);
/*!40000 ALTER TABLE `tkb_tiet` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-26 13:08:54
