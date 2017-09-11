-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: danavtc
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
  `ghi_chu` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ten` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmchuc_vu`
--

LOCK TABLES `dmchuc_vu` WRITE;
/*!40000 ALTER TABLE `dmchuc_vu` DISABLE KEYS */;
INSERT INTO `dmchuc_vu` VALUES (1,NULL,'Trưởng phòng'),(2,NULL,'Phó phòng'),(3,NULL,'Thư ký'),(4,NULL,'Giáo vụ'),(5,NULL,'Nhân viên'),(6,NULL,'Giáo viên'),(7,NULL,'Kỹ thuật viên'),(8,NULL,'Trưởng khoa'),(9,NULL,'Phó khoa'),(10,NULL,'NULL');
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
  `ma` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ten` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dm_loai_don_vi_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dm_loai_don_vi_id` (`dm_loai_don_vi_id`),
  CONSTRAINT `FKjluc3wncqa6h2hudhholpg0i4` FOREIGN KEY (`dm_loai_don_vi_id`) REFERENCES `dmloai_don_vi` (`id`),
  CONSTRAINT `dmdon_vi_ibfk_1` FOREIGN KEY (`dm_loai_don_vi_id`) REFERENCES `dmloai_don_vi` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmdon_vi`
--

LOCK TABLES `dmdon_vi` WRITE;
/*!40000 ALTER TABLE `dmdon_vi` DISABLE KEYS */;
INSERT INTO `dmdon_vi` VALUES (1,'CB','khoa cơ bản',1),(2,'CDL','khoa cơ điện lạnh',1),(3,'CNTT','khoa công nghệ thông tin',1),(4,'DDT','khoa điện - điện tử',1),(5,'DL','khoa du lịch',1),(6,'M','khoa may',1),(7,'KT','khoa kinh tế',1),(8,'SPN','khoa sư phạm nghề',1),(9,'NN','khoa ngoại ngữ',1),(11,'DT','phòng Đào tạo',2),(12,'HSSV','phòng công tác học sinh, sinh viên',2),(13,'TCKT','phòng tài chính, kế toán',2),(14,'HTPT','phòng kiểm định và hợp tác, phát triển ',2),(15,'QTTB','phòng quản trị, thiết bị ',2),(16,'DTTX','trung tâm đào tạo thường xuyên',2),(17,'TSVL','tuyển sinh và đào tạo việc làm',2),(18,'TCHC','phòng tổ chức hành chính',2),(19,'GDTC','khoa giáo dục thể chất',1),(20,'CK','khoa cơ khí',1);
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
  `ma_giang_duong` varchar(10) DEFAULT NULL,
  `so_luong` int(4) DEFAULT '50',
  `tang` int(11) DEFAULT NULL,
  `ten` varchar(50) DEFAULT NULL,
  `dm_loai_phong_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dm_loai_phong_id` (`dm_loai_phong_id`),
  KEY `dm_loai_phong_id_2` (`dm_loai_phong_id`),
  CONSTRAINT `FK6f7ne4u17ll73phwwm6pejvnq` FOREIGN KEY (`dm_loai_phong_id`) REFERENCES `dmloai_phong` (`id`),
  CONSTRAINT `dmgiang_duong_ibfk_1` FOREIGN KEY (`dm_loai_phong_id`) REFERENCES `dmloai_phong` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmgiang_duong`
--

LOCK TABLES `dmgiang_duong` WRITE;
/*!40000 ALTER TABLE `dmgiang_duong` DISABLE KEYS */;
INSERT INTO `dmgiang_duong` VALUES (1,'A109',50,1,'Phòng thực hành khoa cơ bản',2),(2,'A110',50,1,'Phòng thực hành khoa cơ bản',2),(3,'A111',50,1,'Phòng thực hành khoa cơ khí',2),(4,'A112',50,1,'Phòng thực hành khoa cơ khí',2),(5,'A113',50,1,'Phòng thực hành khoa cơ điện lạnh',2),(6,'A114',50,1,'Phòng thực hành khoa cơ điện lạnh',2),(7,'A201',50,2,'Phòng thực hành khoa công nghệ thông tin',2),(8,'A202',50,2,'Phòng thực hành khoa công nghệ thông tin',2),(9,'A203',50,2,'Phòng thực hành khoa điện - điện tử ',2),(10,'A204',50,2,'Phòng thực hành khoa điện - điện tử',2),(11,'A205',50,2,'Phòng thực hành khoa du lịch',2),(12,'A206',50,2,'Phòng thực hành khoa du lịch',2),(13,'A207',50,2,'Phòng thực hành khoa may',2),(14,'A208',50,2,'Phòng thực hành khoa may',2),(23,'CK01',100,1,'CK01',1),(24,'CK02',100,1,'CK02',1),(25,'CK03',100,1,'CK03',1),(26,'CK04',100,1,'CK04',1),(27,'CK05',100,1,'CK05',1),(28,'CK06',100,1,'CK06',1),(29,'CK07',100,1,'CK07',1),(30,'CK08',100,1,'CK08',1),(31,'CK09',100,1,'CK09',1),(32,'CK10',100,1,'CK10',1),(33,'CK11',100,1,'CK11',1),(34,'CKTH1',100,1,'CKTH1',2),(35,'CKTH2',100,1,'CKTH2',2),(36,'CKTH3',100,1,'CKTH3',2),(37,'CNTTTH01',100,1,'CNTTTH01',2),(38,'CNTTTH02',100,1,'CNTTTH02',2),(39,'CNTTTH03',100,1,'CNTTTH03',2),(40,'CNTTTH04',100,1,'CNTTTH04',2),(41,'CNTTTH05',100,1,'CNTTTH05',2),(42,'CKTH4',100,1,'CKTH4',2);
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
  `ghi_chu` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ten` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `ten` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmloai_phong`
--

LOCK TABLES `dmloai_phong` WRITE;
/*!40000 ALTER TABLE `dmloai_phong` DISABLE KEYS */;
INSERT INTO `dmloai_phong` VALUES (1,'Phòng lý thuyết'),(2,'Phòng thực hành'),(3,'Phòng làm việc');
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmlop_hoc`
--

LOCK TABLES `dmlop_hoc` WRITE;
/*!40000 ALTER TABLE `dmlop_hoc` DISABLE KEYS */;
INSERT INTO `dmlop_hoc` VALUES (3,'15CK1A','15CK1A',4),(4,'15CK1B','15CK1B',4),(5,'15CK1C','15CK1C',4),(6,'15CK1D','15CK1D',4),(7,'15CK6A','15CK6A',4),(8,'15CK6B','15CK6B',4),(9,'16CK1A','16CK1A',4),(10,'16CK1B','16CK1B',4),(11,'16CK1C','16CK1C',4),(12,'16CK1D','16CK1D',4),(13,'16CK1E','16CK1E',4),(14,'16CK1F','16CK1E',4),(15,'16CK6A','16CK6A',4),(16,'16CK6B','16CK6B',4);
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
  `gioi_han_tuan_bat_dau` int(11) DEFAULT NULL,
  `gioi_han_tuan_ket_thuc` int(11) DEFAULT NULL,
  `so_luong_toi_da` int(4) DEFAULT '100',
  `so_tiet_ly_thuyet` int(11) DEFAULT NULL,
  `so_tiet_thuc_hanh` int(11) DEFAULT NULL,
  `dm_nganh_id` int(11) DEFAULT NULL,
  `tkb_khoa_khoa_hoc_id` int(11) DEFAULT NULL,
  `tkb_ki_hoc_nam_hoc_id` int(11) DEFAULT NULL,
  `dm_mon_hoc_id` int(11) NOT NULL,
  `dm_nhan_vien_id` int(11) NOT NULL,
  `tkb_khoa_khoa_hoc_nganh_nhom_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkttss23okgtm332fbc377uys1` (`dm_nganh_id`),
  KEY `FKdwh4k8vvlp6rso2u395wvq13u` (`tkb_khoa_khoa_hoc_id`),
  KEY `FK2a8x992klkdrmvu7t1490jix4` (`tkb_ki_hoc_nam_hoc_id`),
  KEY `FK5io2d37g6la49e0ksgeeoixva` (`dm_mon_hoc_id`),
  KEY `FKrveiqpjxdmoidc3ksn973ffel` (`dm_nhan_vien_id`),
  KEY `FKes2cash3tj106k3wn7ghggph` (`tkb_khoa_khoa_hoc_nganh_nhom_id`),
  CONSTRAINT `FK2a8x992klkdrmvu7t1490jix4` FOREIGN KEY (`tkb_ki_hoc_nam_hoc_id`) REFERENCES `tkb_ki_hoc_nam_hoc` (`id`),
  CONSTRAINT `FK5io2d37g6la49e0ksgeeoixva` FOREIGN KEY (`dm_mon_hoc_id`) REFERENCES `dmmon_hoc` (`id`),
  CONSTRAINT `FKdwh4k8vvlp6rso2u395wvq13u` FOREIGN KEY (`tkb_khoa_khoa_hoc_id`) REFERENCES `tkb_khoa_khoa_hoc` (`id`),
  CONSTRAINT `FKes2cash3tj106k3wn7ghggph` FOREIGN KEY (`tkb_khoa_khoa_hoc_nganh_nhom_id`) REFERENCES `tkb_khoa_khoa_hoc_nganh_nhom` (`id`),
  CONSTRAINT `FKkttss23okgtm332fbc377uys1` FOREIGN KEY (`dm_nganh_id`) REFERENCES `dmnganh` (`id`),
  CONSTRAINT `FKrveiqpjxdmoidc3ksn973ffel` FOREIGN KEY (`dm_nhan_vien_id`) REFERENCES `dmnhan_vien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmlop_mon_hoc`
--

LOCK TABLES `dmlop_mon_hoc` WRITE;
/*!40000 ALTER TABLE `dmlop_mon_hoc` DISABLE KEYS */;
INSERT INTO `dmlop_mon_hoc` VALUES (32,1,20,80,70,35,13,4,1,29,37,NULL),(34,1,20,80,100,50,13,4,1,30,39,NULL),(37,1,20,80,70,40,13,4,1,31,35,NULL),(39,1,20,80,70,35,13,4,1,32,54,NULL),(41,1,20,80,30,0,13,4,1,38,58,NULL),(42,1,20,80,45,0,13,4,1,39,42,NULL),(45,1,20,80,45,0,13,4,1,42,42,NULL),(47,1,20,80,0,45,13,4,1,45,45,NULL),(48,1,20,80,30,15,14,4,1,22,35,NULL),(49,1,20,80,30,15,14,4,1,23,35,NULL),(50,1,20,80,60,30,14,4,1,37,36,NULL),(51,1,20,80,80,40,14,5,1,24,43,NULL),(52,1,20,80,60,30,14,5,1,26,40,NULL),(53,1,20,80,40,20,14,5,1,27,40,NULL),(54,1,20,80,70,35,14,5,1,28,46,NULL),(55,1,20,80,30,0,14,5,1,34,51,NULL),(56,1,20,80,15,0,14,5,1,44,60,NULL),(57,1,20,80,0,75,14,5,1,47,55,NULL),(58,1,20,80,45,0,14,5,1,52,63,NULL),(59,1,20,80,45,0,14,5,1,50,38,NULL),(60,1,20,80,100,50,13,5,1,25,43,NULL),(61,1,20,80,90,0,13,5,1,33,51,NULL),(62,1,20,80,60,0,14,5,1,35,45,NULL),(63,1,20,80,45,0,13,5,1,36,48,NULL),(64,1,20,80,30,0,14,5,1,51,73,NULL),(65,1,20,80,120,0,13,5,1,40,69,NULL),(66,1,20,80,30,0,13,5,1,43,60,NULL),(67,1,20,80,0,45,13,5,1,46,49,NULL),(68,1,20,80,30,0,13,5,1,48,64,NULL),(69,1,20,80,45,0,13,5,1,49,63,NULL),(74,23,40,100,40,20,13,4,2,29,37,30),(75,23,40,100,40,20,13,4,2,29,37,32),(76,23,40,100,100,50,13,4,2,30,40,30),(77,23,40,100,100,50,13,4,2,30,40,32),(78,30,40,100,70,35,13,4,2,31,41,30),(79,30,40,100,70,35,13,4,2,31,41,32),(80,23,40,100,70,35,13,4,2,32,39,30),(81,23,40,100,70,35,13,4,2,32,39,32),(82,23,28,100,30,0,13,4,2,38,44,30),(83,23,28,100,30,0,13,4,2,38,58,32),(84,23,31,100,45,0,13,4,2,39,42,30),(85,32,40,100,45,0,13,4,2,39,42,32),(86,32,40,100,45,0,13,4,2,42,43,30),(87,23,31,100,45,0,13,4,2,42,43,32),(88,23,31,100,0,45,13,4,2,45,38,30),(89,23,27,100,0,45,13,4,2,45,38,32),(90,23,28,100,45,0,14,4,2,22,35,34),(91,23,28,100,45,0,14,4,2,22,35,35),(92,23,40,100,75,0,14,4,2,37,36,34),(93,23,40,100,75,0,14,4,2,37,36,35),(94,23,40,100,100,50,13,5,2,25,47,25),(95,23,40,100,100,50,13,5,2,25,47,36),(96,23,40,100,90,0,13,5,2,33,66,25),(97,23,40,100,90,0,13,5,2,33,51,36),(98,23,31,100,45,0,13,5,2,36,62,25),(99,23,31,100,45,0,13,5,2,36,48,36),(100,23,40,100,120,0,13,5,2,40,61,25),(101,23,40,100,120,0,13,5,2,40,61,36),(102,35,40,100,30,0,13,5,2,43,60,25),(103,35,40,100,30,0,13,5,2,43,60,36),(104,32,40,100,0,45,13,5,2,46,49,25),(105,32,40,100,0,45,13,5,2,46,49,36),(106,23,28,100,30,0,13,5,2,48,64,25),(107,35,40,100,30,0,13,5,2,48,64,36),(108,23,31,100,45,0,13,5,2,49,38,25),(109,23,31,100,45,0,13,5,2,49,38,36),(113,23,40,100,60,30,14,5,2,26,40,37),(114,23,40,100,60,30,14,5,2,26,40,38),(115,23,40,100,40,20,14,5,2,27,40,37),(116,23,40,100,40,20,14,5,2,27,40,38),(117,23,40,100,70,35,14,5,2,28,46,37),(118,23,40,100,70,35,14,5,2,28,46,38),(119,23,28,100,30,0,14,5,2,34,66,37),(120,35,40,100,30,0,14,5,2,34,66,38),(121,23,40,100,40,20,14,5,2,35,45,37),(122,23,40,100,40,20,14,5,2,35,45,38),(123,35,40,100,30,0,14,5,2,51,73,37),(124,23,28,100,30,0,14,5,2,51,73,38),(125,38,40,100,15,0,14,5,2,44,67,37),(126,38,40,100,15,0,14,5,2,44,67,38),(127,23,40,100,0,75,14,5,2,47,55,37),(128,23,40,100,0,75,14,5,2,47,55,38),(129,33,40,100,45,0,14,5,2,52,63,37),(130,33,40,100,45,0,14,5,2,52,63,38),(131,23,40,100,80,40,14,5,2,24,43,37),(132,23,40,100,80,40,14,5,2,24,43,38);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmlop_mon_hoc_sinh_vien`
--

LOCK TABLES `dmlop_mon_hoc_sinh_vien` WRITE;
/*!40000 ALTER TABLE `dmlop_mon_hoc_sinh_vien` DISABLE KEYS */;
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
  `ma_mon_hoc` varchar(10) DEFAULT NULL,
  `so_tin_chi` float DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmmon_hoc`
--

LOCK TABLES `dmmon_hoc` WRITE;
/*!40000 ALTER TABLE `dmmon_hoc` DISABLE KEYS */;
INSERT INTO `dmmon_hoc` VALUES (22,'BDHTLDKDT',3,'Bảo dưỡng hệ thống lái điều khiển điện tử'),(23,'BDHTTDKDT',3,'Bảo dưỡng hệ thống treo điều khiển điện tử'),(24,'QuaDai1',8,'Bảo dưỡng và sửa chữa cơ cấu trục khuỷu - thanh truyền và bộ phận cố định của động cơ 1'),(25,'QuaDai2',10,'Bảo dưỡng và sửa chữa cơ cấu trục khuỷu - thanh truyền và bộ phận cố định của động cơ 2'),(26,'BDVSCHTDC',6,'Bảo dưỡng và sửa chữa hệ thống di chuyển'),(27,'BDVSCHTL',4,'Bảo dưỡng và sửa chữa hệ thống lái'),(28,'BDVSCHTPPK',7,'Bảo dưỡng và sửa chữa hệ thống phân phối khí'),(29,'BDVSCHTP',7,'Bảo dưỡng và sửa chữa hệ thống phanh'),(30,'BDVSCHTTL',10,'Bảo dưỡng và sửa chữa hệ thống truyền lực'),(31,'HTDHKKTOT',7,'BDSC HT điều hòa không khí trên ô tô'),(32,'HTNLDCD',7,'BDSC HT nhiên liệu động cơ diesel'),(33,'CT1',6,'Chính trị'),(34,'CT2',2,'Chính Trị'),(35,'CUD',4,'Cơ ứng dụng'),(36,'DTCB',3,'Điện tử cơ bản'),(37,'KTVSCPOT',5,'Kiểm tra và sửa chữa PAN ô tô'),(38,'KNM1',2,'Kỹ năng mềm'),(39,'LTOT',3,'Lý thuyết ôtô'),(40,'AV',7,'Ngoại ngữ (Anh văn)'),(41,'NLDC',3,'Nguyên lý động cơ'),(42,'NLDCDT',3,'Nguyên lý động cơ đốt trong'),(43,'PL1',2,'Pháp luật'),(44,'PL2',1,'Pháp luật'),(45,'THA',3,'Thực hành AUTOCAD'),(46,'THHCB',3,'Thực hành Hàn cơ bản'),(47,'THMDOT',5,'Thực hành mạch điện ô tô'),(48,'TCQLSX',2,'Tổ chức quản lý sản xuất'),(49,'VLH1',3,'Vật liệu học'),(50,'VKT',3,'Vẽ kỹ thuật'),(51,'KNM2',2,'Kỹ năng mềm'),(52,'VLH2',3,'Vật liệu học');
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
  `dm_giang_duong_id` int(11) DEFAULT NULL,
  `dm_mon_hoc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dm_mon_hoc_id` (`dm_mon_hoc_id`),
  KEY `dm_giang_duong_id` (`dm_giang_duong_id`),
  CONSTRAINT `FK1ihj2w3vn0nck04cfwm6lsl1k` FOREIGN KEY (`dm_giang_duong_id`) REFERENCES `dmgiang_duong` (`id`),
  CONSTRAINT `FK1xabmvjaas6qvv5nym53fq41` FOREIGN KEY (`dm_mon_hoc_id`) REFERENCES `dmmon_hoc` (`id`),
  CONSTRAINT `dmmon_hoc_giang_duong_ibfk_1` FOREIGN KEY (`dm_mon_hoc_id`) REFERENCES `dmmon_hoc` (`id`),
  CONSTRAINT `dmmon_hoc_giang_duong_ibfk_2` FOREIGN KEY (`dm_giang_duong_id`) REFERENCES `dmgiang_duong` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmmon_hoc_giang_duong`
--

LOCK TABLES `dmmon_hoc_giang_duong` WRITE;
/*!40000 ALTER TABLE `dmmon_hoc_giang_duong` DISABLE KEYS */;
INSERT INTO `dmmon_hoc_giang_duong` VALUES (57,31,22),(58,32,22),(59,33,22),(60,31,23),(61,32,23),(62,33,23),(63,31,24),(64,32,24),(65,33,24),(66,27,25),(67,28,25),(68,29,25),(69,30,25),(70,31,26),(71,32,26),(72,33,26),(73,31,27),(74,32,27),(75,33,27),(76,31,28),(77,32,28),(78,33,28),(79,23,29),(80,24,29),(81,25,29),(82,26,29),(83,23,30),(84,24,30),(85,25,30),(86,26,30),(87,23,31),(88,24,31),(89,25,31),(90,26,31),(91,23,32),(92,24,32),(93,25,32),(94,26,32),(95,27,33),(96,28,33),(97,29,33),(98,30,33),(99,31,34),(100,32,34),(101,33,34),(102,31,35),(103,32,35),(104,33,35),(105,27,36),(106,28,36),(107,29,36),(108,30,36),(109,31,37),(110,32,37),(111,33,37),(112,23,38),(113,24,38),(114,25,38),(115,26,38),(116,31,51),(117,32,51),(118,33,51),(119,23,39),(120,24,39),(121,25,39),(122,26,39),(123,27,40),(124,28,40),(125,29,40),(126,30,40),(127,23,42),(128,24,42),(129,25,42),(130,26,42),(131,27,43),(132,28,43),(133,29,43),(134,30,43),(135,31,44),(136,32,44),(137,33,44),(149,27,48),(150,28,48),(151,29,48),(152,30,48),(153,27,49),(154,28,49),(155,29,49),(156,30,49),(157,31,52),(158,32,52),(159,33,52),(160,31,50),(161,32,50),(162,33,50),(164,34,29),(165,35,29),(166,34,31),(167,35,31),(168,34,30),(169,35,30),(170,34,32),(171,35,32),(173,41,45),(174,40,45),(175,34,22),(176,35,22),(177,34,23),(178,35,23),(179,34,37),(180,35,37),(181,36,47),(182,42,47),(183,36,28),(184,42,28),(185,36,27),(186,42,27),(187,36,26),(188,42,26),(189,36,24),(190,42,24),(191,36,46),(192,42,46),(193,36,25),(194,42,25),(195,36,35),(196,42,35);
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmnganh`
--

LOCK TABLES `dmnganh` WRITE;
/*!40000 ALTER TABLE `dmnganh` DISABLE KEYS */;
INSERT INTO `dmnganh` VALUES (13,'CK1'),(14,'CK6');
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
  `ho_ten` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gioi_tinh` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dia_chi` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `so_dien_thoai` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dm_don_vi_id` int(11) DEFAULT NULL,
  `dm_giang_duong_id` int(11) DEFAULT NULL,
  `ho_dem` varchar(50) CHARACTER SET utf8 NOT NULL,
  `ma_nhan_vien` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ten` varchar(20) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  KEY `MaKhoa` (`dm_don_vi_id`),
  KEY `dm_don_vi_id` (`dm_don_vi_id`),
  KEY `dm_giang_duong_id` (`dm_giang_duong_id`),
  CONSTRAINT `FKx0e5g5k5sh793vp8tmkhq3vw` FOREIGN KEY (`dm_don_vi_id`) REFERENCES `dmdon_vi` (`id`),
  CONSTRAINT `dmnhan_vien_ibfk_6` FOREIGN KEY (`dm_don_vi_id`) REFERENCES `dmdon_vi` (`id`),
  CONSTRAINT `dmnhan_vien_ibfk_8` FOREIGN KEY (`dm_giang_duong_id`) REFERENCES `dmgiang_duong` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmnhan_vien`
--

LOCK TABLES `dmnhan_vien` WRITE;
/*!40000 ALTER TABLE `dmnhan_vien` DISABLE KEYS */;
INSERT INTO `dmnhan_vien` VALUES (2,'Nguyễn Quốc Anh','Nam','56 Yên Bái','0971336581','qanh@gmail.com',15,3,'',NULL,''),(3,'Nguyễn Thu Thủy ','Nữ','27 - Âu Cơ ','01656003571','tthuy@gmail.com',1,1,'',NULL,''),(4,'Trần Hào Quang','Nam','12 - Trần Phú','0986799731','hquang@gmail.com',15,2,'',NULL,''),(5,'Nguyễn Hồng Chinh','Nam','43 Thạc Gián','0987553196','chinhbk@gmail.com',15,NULL,'',NULL,''),(6,'Bùi Thanh Thảo','Nữ','23 Trường Chinh','0985228176','tubui@gmail.com',15,NULL,'',NULL,''),(7,'Hoàng Huỳnh Mẫn','Nam','27 Lê Độ','0165800397','manhoang@gmail.com',2,NULL,'',NULL,''),(8,'Nguyễn Thanh Minh','Nam','143 Lê Duẩn','0987650127','tminh@gmail.com',15,NULL,'',NULL,''),(9,'Nguyễn Thùy Minh','Nữ','26 CMTT','098355614','thuyminh@gmail.com',5,NULL,'',NULL,''),(10,'Hoàng Tuấn Nhân','Nam','671 Âu Cơ','0973455621','tnhan@gmail.com',15,NULL,'',NULL,''),(11,'Nguyễn Hoàng Phúc','Nam','65 Ngô Quyền','016655373','hphuc@gmail.com',7,NULL,'',NULL,''),(13,'Lê Mỹ Dung','Nữ','543 Túy Loan','0983227425','mdung@gmail.com',9,NULL,'',NULL,''),(14,'Phạm Thanh An','Nam','21 Lê Duẩn','0986553291','thanhan@gmail.com',15,NULL,'',NULL,''),(15,'Từ Thế Huy','Nam','14 Thạc Gián','0985736210','thehuy@gmail.com',15,NULL,'',NULL,''),(16,'Lê Y Thu','Nữ','43 Lê Đại Hành','0986533729','ythu83@gmail.com',9,NULL,'',NULL,''),(17,'Trần Bá Cảnh','Nam','26 Trần Đình Tri','0987553641','bacanh@gmail.com',8,NULL,'',NULL,''),(19,'Lê Quỳnh Anh','Nữ','23 Tôn Đản','097133582','qanh@gmail.com',6,NULL,'',NULL,''),(21,'Lê Thị Thu Phượng','Nữ','66 Âu Cơ','0986550379','tphuong@gmail.com',7,NULL,'',NULL,''),(22,'Phạm Ngọc Thịnh','Nam','67 Bạch Đằng','0166350673','nthinh@gmail.com',2,NULL,'',NULL,''),(23,'Lê Doãn An','Nam','68 Hàm Nghi','0965498127','danle@gmail.com',4,NULL,'',NULL,''),(24,'Phan Minh Đạt','Nam','94 Lê Duẩn','0961225078','pmdat@gmail.com',3,NULL,'',NULL,''),(25,'Nguyễn Đình Toàn','Nam','21 Lê Lợi','0905171296','dtoan96@gmail.com',4,NULL,'',NULL,''),(26,'Nguyễn Phước Thiện','Nam','12 Yên Bái','0914755698','pthien@gmail.com',4,NULL,'',NULL,''),(31,NULL,NULL,NULL,NULL,NULL,3,NULL,'Nguyễn Minh','111111','Trí'),(35,NULL,NULL,NULL,NULL,NULL,20,NULL,'Ngô Văn','1','Dũng'),(36,NULL,NULL,NULL,NULL,NULL,20,NULL,'Trần Phước','2','Phú'),(37,NULL,NULL,NULL,NULL,NULL,20,NULL,'Phan Thành','3','Tài'),(38,NULL,NULL,NULL,NULL,NULL,20,NULL,'Huỳnh Thị','4','Chung'),(39,NULL,NULL,NULL,NULL,NULL,20,NULL,'Trần Ngọc','5','Anh'),(40,NULL,NULL,NULL,NULL,NULL,20,NULL,'Nguyễn Văn','6','Tám'),(41,NULL,NULL,NULL,NULL,NULL,20,NULL,'Nguyễn Minh','7','Tân'),(42,NULL,NULL,NULL,NULL,NULL,20,NULL,'Lê Minh','8','Xuân'),(43,NULL,NULL,NULL,NULL,NULL,20,NULL,'Lê Đức Trọng','9','Nguyễn'),(44,NULL,NULL,NULL,NULL,NULL,20,NULL,'','10','Chung'),(45,NULL,NULL,NULL,NULL,NULL,20,NULL,'Hồ Khánh','11','Huy'),(46,NULL,NULL,NULL,NULL,NULL,20,NULL,'Nguyễn Văn','12','Trung'),(47,NULL,NULL,NULL,NULL,NULL,20,NULL,'Đỗ Xuân','13','Huy'),(48,NULL,NULL,NULL,NULL,NULL,20,NULL,'Lê Đình','14','Cảnh'),(49,NULL,NULL,NULL,NULL,NULL,20,NULL,'Tôn Thất Hoàng','15','Đạt'),(50,NULL,NULL,NULL,NULL,NULL,20,NULL,'Nguyễn Thị Tịnh','16','Giao'),(51,NULL,NULL,NULL,NULL,NULL,20,NULL,'Lê Đức','19','Thọ'),(52,NULL,NULL,NULL,NULL,NULL,20,NULL,'Nguyễn Thị Minh','20','Hương'),(53,NULL,NULL,NULL,NULL,NULL,20,NULL,'Ngô Thị Phước','21','Yên'),(54,NULL,NULL,NULL,NULL,NULL,20,NULL,'Nguyễn Duy','22','Linh'),(55,NULL,NULL,NULL,NULL,NULL,20,NULL,'Phan Minh','23','Mẫn'),(57,NULL,NULL,NULL,NULL,NULL,20,NULL,'Thúy','25','Thúy'),(58,NULL,NULL,NULL,NULL,NULL,20,NULL,'','26','Sang'),(59,NULL,NULL,NULL,NULL,NULL,20,NULL,'','27','Phương'),(60,NULL,NULL,NULL,NULL,NULL,20,NULL,'Lê Thị','28','Hồng'),(61,NULL,NULL,NULL,NULL,NULL,20,NULL,'Huỳnh Thị Kim','29','Sang'),(62,NULL,NULL,NULL,NULL,NULL,20,NULL,'Phạm Hồng','30','Giang'),(63,NULL,NULL,NULL,NULL,NULL,20,NULL,'Nguyễn Thanh','31','Thảo'),(64,NULL,NULL,NULL,NULL,NULL,20,NULL,'Phan Lê Trúc','32','Linh'),(65,NULL,NULL,NULL,NULL,NULL,20,NULL,'Đoàn Ngọc','33','Hoàng'),(66,NULL,NULL,NULL,NULL,NULL,20,NULL,'Lê Nguyễn Cao','34','Tài'),(67,NULL,NULL,NULL,NULL,NULL,20,NULL,'Hồ Thị Thanh','35','Tâm'),(68,NULL,NULL,NULL,NULL,NULL,20,NULL,'Trương Thị Minh','36','Hoa'),(69,NULL,NULL,NULL,NULL,NULL,20,NULL,'Phạm Thị Anh','37','Đài'),(71,NULL,NULL,NULL,NULL,NULL,20,NULL,'Phạm Thanh','39','Linh'),(72,NULL,NULL,NULL,NULL,NULL,20,NULL,'Đoàn Ngọc','40','Hoàng'),(73,NULL,NULL,NULL,NULL,NULL,20,NULL,'','41','Trang');
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
  KEY `dm_chuc_vu_id` (`dm_chuc_vu_id`,`dm_nhan_vien_id`),
  KEY `dm_nhan_vien_id` (`dm_nhan_vien_id`),
  CONSTRAINT `FKkd3k3se4b5gb6i8i74bj50jae` FOREIGN KEY (`dm_chuc_vu_id`) REFERENCES `dmchuc_vu` (`id`),
  CONSTRAINT `FKl9ux45h44g0uki17mxem5b409` FOREIGN KEY (`dm_nhan_vien_id`) REFERENCES `dmnhan_vien` (`id`),
  CONSTRAINT `dmnhan_vien_chuc_vu_ibfk_1` FOREIGN KEY (`dm_chuc_vu_id`) REFERENCES `dmchuc_vu` (`id`),
  CONSTRAINT `dmnhan_vien_chuc_vu_ibfk_2` FOREIGN KEY (`dm_nhan_vien_id`) REFERENCES `dmnhan_vien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmnhan_vien_chuc_vu`
--

LOCK TABLES `dmnhan_vien_chuc_vu` WRITE;
/*!40000 ALTER TABLE `dmnhan_vien_chuc_vu` DISABLE KEYS */;
INSERT INTO `dmnhan_vien_chuc_vu` VALUES (1,1,2),(2,2,3),(3,3,4),(4,4,5),(5,5,6),(6,6,7),(7,7,8),(8,7,10);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dmsinh_vien`
--

LOCK TABLES `dmsinh_vien` WRITE;
/*!40000 ALTER TABLE `dmsinh_vien` DISABLE KEYS */;
/*!40000 ALTER TABLE `dmsinh_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_chi_thiet_thiet_bi_xuat`
--

DROP TABLE IF EXISTS `tb_chi_thiet_thiet_bi_xuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_chi_thiet_thiet_bi_xuat` (
  `ma_ctpx` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `ma_qr` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ma_phieu_xuat` int(11) DEFAULT NULL,
  `ma_tinh_trang` int(10) DEFAULT NULL,
  PRIMARY KEY (`ma_ctpx`),
  KEY `ma_tinh_trang` (`ma_tinh_trang`),
  KEY `ma_phieu_xuat` (`ma_phieu_xuat`),
  KEY `ma_phieu_xuat_2` (`ma_phieu_xuat`),
  CONSTRAINT `tb_chi_thiet_thiet_bi_xuat_ibfk_1` FOREIGN KEY (`ma_phieu_xuat`) REFERENCES `tb_phieu_xuat` (`ma_phieu_xuat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_chi_thiet_thiet_bi_xuat`
--

LOCK TABLES `tb_chi_thiet_thiet_bi_xuat` WRITE;
/*!40000 ALTER TABLE `tb_chi_thiet_thiet_bi_xuat` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_chi_thiet_thiet_bi_xuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_chi_tiet_nhap_xuat`
--

DROP TABLE IF EXISTS `tb_chi_tiet_nhap_xuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_chi_tiet_nhap_xuat` (
  `thiet_bi_id` int(10) NOT NULL AUTO_INCREMENT,
  `ma_thiet_bi` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ten_thiet_bi` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `don_vi_tinh` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `so_luong` int(10) DEFAULT NULL,
  `han_bao_hanh` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `so_hieu_tscd` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cong_xuat` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nguyen_gia` int(20) DEFAULT NULL,
  `ma_nuoc_san_xuat` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mo_ta` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `khau_hao` int(10) DEFAULT NULL,
  `ma_code` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ma_phieu_nhap` int(11) DEFAULT NULL,
  PRIMARY KEY (`thiet_bi_id`),
  KEY `ma_thiet_bi` (`ma_nuoc_san_xuat`),
  KEY `ma_nuoc_san_xuat` (`ma_nuoc_san_xuat`),
  KEY `ma_thiet_bi_2` (`ma_thiet_bi`),
  KEY `ma_phieu_nhap` (`ma_phieu_nhap`),
  CONSTRAINT `tb_chi_tiet_nhap_xuat_ibfk_10` FOREIGN KEY (`ma_phieu_nhap`) REFERENCES `tb_phieu_nhap` (`ma_phieu_nhap`),
  CONSTRAINT `tb_chi_tiet_nhap_xuat_ibfk_7` FOREIGN KEY (`ma_thiet_bi`) REFERENCES `tb_danh_muc_thiet_bi` (`ma_thiet_bi`),
  CONSTRAINT `tb_chi_tiet_nhap_xuat_ibfk_9` FOREIGN KEY (`ma_nuoc_san_xuat`) REFERENCES `tb_nuoc_san_xuat` (`ma_nuoc_san_xuat`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_chi_tiet_nhap_xuat`
--

LOCK TABLES `tb_chi_tiet_nhap_xuat` WRITE;
/*!40000 ALTER TABLE `tb_chi_tiet_nhap_xuat` DISABLE KEYS */;
INSERT INTO `tb_chi_tiet_nhap_xuat` VALUES (19,'TB00001','Bộ máy tính để bàn Dell optiplex 990','Bộ',4,'25 tháng','MTDTB00001','165W',4500000,'US','CPU vỏ đen, kích thước 50*20*40, HDD 500Gb Sata3 7200, Ram 4Gb bus 1333Mhz ',12,'JP2017TB00001',1),(20,'TB00003','Chuột máy tính Genius','Cái',5,'12 tháng','CHGTB00003','0',100000,'CN','Chuột dây kết nối cổng USB, Độ phân giải 1200 DPI',15,'CN2017TB00003',1),(21,'TB00026','Dây điện ovan 2 ruột mềm Tân Phú','Cuộn',8,'2 tháng','DDTTB00026','12',460000,'VN','400/500V CU/PVC 2 x 1mm, vỏ: nhựa PVC, ruột: đồng',10,'VN2017TB00026',2),(23,'TB00031','Mạch điện tử Arduino Uno R3','Cái',20,'15 tháng','ARDTB00031','20',120000,'US','Kích thước 10*10',12,'US2017TB00031',6),(24,'TB00005','Loa máy tính Ebus 818  ','Cai',1,'12 ','A','450',12000,'DE','a',12,'DE2017TB00005',8);
/*!40000 ALTER TABLE `tb_chi_tiet_nhap_xuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_chi_tiet_thiet_bi`
--

DROP TABLE IF EXISTS `tb_chi_tiet_thiet_bi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_chi_tiet_thiet_bi` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `ma_thiet_bi` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `thiet_bi_id` int(10) DEFAULT NULL,
  `ten_thiet_bi` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `don_vi_tinh` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `so_luong` int(10) DEFAULT NULL,
  `han_bao_hanh` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `so_hieu_tscd` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cong_xuat` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nguyen_gia` int(20) DEFAULT NULL,
  `nuoc_san_xuat` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `khau_hao` int(10) DEFAULT NULL,
  `dac_tinh_ky_thuat` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ma_phieu_nhap` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ma_phieu_xuat` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ma_code` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `thiet_bi_id` (`thiet_bi_id`),
  KEY `dac_tinh_ky_thuat` (`dac_tinh_ky_thuat`(255)),
  CONSTRAINT `tb_chi_tiet_thiet_bi_ibfk_1` FOREIGN KEY (`thiet_bi_id`) REFERENCES `tb_chi_tiet_nhap_xuat` (`thiet_bi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_chi_tiet_thiet_bi`
--

LOCK TABLES `tb_chi_tiet_thiet_bi` WRITE;
/*!40000 ALTER TABLE `tb_chi_tiet_thiet_bi` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_chi_tiet_thiet_bi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_danh_muc_chung_loai`
--

DROP TABLE IF EXISTS `tb_danh_muc_chung_loai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_danh_muc_chung_loai` (
  `ma_chung_loai` int(10) NOT NULL AUTO_INCREMENT,
  `ten_chung_loai` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ma_nhom_thiet_bi` int(10) DEFAULT NULL,
  `ghi_chu` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ma_chung_loai`),
  UNIQUE KEY `ten_chung_loai` (`ten_chung_loai`),
  KEY `ma_nhom_thiet_bi` (`ma_nhom_thiet_bi`),
  CONSTRAINT `tb_danh_muc_chung_loai_ibfk_1` FOREIGN KEY (`ma_nhom_thiet_bi`) REFERENCES `tb_nhom_thiet_bi` (`ma_nhom_thiet_bi`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_danh_muc_chung_loai`
--

LOCK TABLES `tb_danh_muc_chung_loai` WRITE;
/*!40000 ALTER TABLE `tb_danh_muc_chung_loai` DISABLE KEYS */;
INSERT INTO `tb_danh_muc_chung_loai` VALUES (1,'Thiết bị trình chiếu',1,''),(2,'Máy móc cơ học',2,''),(3,'Thiết bị cơ khí',1,NULL),(4,'Thiết bị nghề may',1,''),(5,'Thiết bị đo lường, thí nghiệm',1,NULL),(6,'Thiết bị ánh sáng',1,NULL),(7,'Thiết bị âm thanh',1,NULL),(8,'Máy móc, thiết bị khác',1,NULL),(9,'Thiết bị phục vụ giảng dạy khác',1,NULL),(10,'Bàn họp',2,NULL),(11,'Bàn làm việc',2,NULL),(12,'Ghế làm việc',2,NULL),(13,'Giá đặt tài liệu',2,NULL),(14,'Tủ chứa tài liệu',2,NULL),(16,'Máy photocopy',2,NULL),(17,'Máy in',2,NULL),(18,'Điều hòa không khi',2,NULL),(19,'Máy hút bụi',2,NULL),(20,'Thiết bị liên lạc',2,NULL),(21,'Quạt điện',2,NULL),(22,'Máy lọc nước',2,NULL),(30,'Thiết bị văn phòng khác',1,''),(32,'Thiết bị tin học, viễn thông',1,''),(34,'Thiết bị điện, điện tử',1,''),(35,'Máy vi tính văn phòng',1,'');
/*!40000 ALTER TABLE `tb_danh_muc_chung_loai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_danh_muc_thiet_bi`
--

DROP TABLE IF EXISTS `tb_danh_muc_thiet_bi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_danh_muc_thiet_bi` (
  `ma_thiet_bi` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `ten_thiet_bi` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dac_tinh_ky_thuat` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ma_chung_loai` int(10) DEFAULT NULL,
  PRIMARY KEY (`ma_thiet_bi`),
  UNIQUE KEY `ma_thiet_bi` (`ma_thiet_bi`),
  KEY `ma_chung_loai` (`ma_chung_loai`),
  CONSTRAINT `tb_danh_muc_thiet_bi_ibfk_1` FOREIGN KEY (`ma_chung_loai`) REFERENCES `tb_danh_muc_chung_loai` (`ma_chung_loai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_danh_muc_thiet_bi`
--

LOCK TABLES `tb_danh_muc_thiet_bi` WRITE;
/*!40000 ALTER TABLE `tb_danh_muc_thiet_bi` DISABLE KEYS */;
INSERT INTO `tb_danh_muc_thiet_bi` VALUES ('TB00001','Bộ máy tính để bàn Dell optiplex 990','CPU vỏ đen, kích thước 50*20*40, HDD 500Gb Sata3 7200, Ram 4Gb bus 1333Mhz ',32),('TB00002','Máy vi tính G41 Và Intel Core 2 Duo E7500','Bo mạch: Socket 775 G41/G45 Asus, GigabyteBộ xử lý: Intel (R) Core 2 Duo E7500 ',32),('TB00003','Chuột máy tính Genius','Chuột dây kết nối cổng USB, Độ phân giải 1200 DPI',32),('TB00004','Quạt trần Sanzo','Quạt 3 cánh, công suất 350W',21),('TB00005','Loa máy tính Ebus 818  ','Kiểu loa 2.1, công suất 80W, sử dụng điện 220V',7),('TB00006','Đồng hồ đo kim Pro\'skit MT-2017V.1','Model : MT-2017,DCV 0.1/2.5/10/50/250 V , ± (3.0% FSD), 1000V ± (4.0% FSD)\r\nACV 10/50/250 V ± (4.0% FSD), 1000V ±(5.0% FSD)\r\n\r\n10A ± (4.0% FSD)',5),('TB00007','Loa Creative SBS A250 2.1','Loa Creative SBS A250 gồm 2 loa vệ tinh và 1 loa siêu trầm, hệ thống loa 2.1,cho âm thanh chất lượng cao',7),('TB00008','Loa Treble rời AT-3040','Tần số thấp nhất (Hz) 1000Hz, trọng lượng: 1,8kg, trọn bộ sản phẩm có 2 loa',7),('TB00009','Máy quấn dây tự động Hoàng Hà-D1500','Tốc độ động cơ quấn (v/p) 3000, bước rải vô cấp 10mm, hành trình rải 100mm',2),('TB00010','Động cơ điện 1 pha Trường Mạnh YL90-4','Điện áp 220 (V) , dòng điện 10 (A), tốc độ vòng quay 1450 (v/phút) ',2),('TB00011','Động cơ điện 3 pha Hem 3K112S4','Động cơ không đồng bộ 3 pha, điện áp 220/380 (V), dòng điện 11.6/6.7 (A), tốc độ vòng quay 	1425 (v/phút)',2),('TB00012','Máy Tiện CNC - WASINO','Hệ điều khiển: Fanuc OT, đường kính mâm cặp: collet mm, hành trình X: 80 mm, hành trình Z: 150 mm, trục phay (C): No, tốc độ trục chính: 8000 rpm, kích thước (DxRxC): 3.2x1.2x2.2 m',3),('TB00013',' Máy bắt vít ﻿Bosch GSR6-45TE 701W','Mô-men xoắn, tối đa (các công việc vặn vít mềm) 12 Nm, đầu cặp Lục giác trong 1/4\", đầu ra công suất 355 W, tốc độ không tải 0 - 4500 vòng/phút, trọng lượng không tính cáp 1,4 kg',3),('TB00014','Bộ 32 món tua vít đa năng Jackly JK6066-B ','Kích thước sản phẩm 8x5x5, Trọng lượng 0.125 (KG)	',3),('TB00015','Máy photocopy Sharp AR-6023D  ','Tốc độ Copy: 23 bản/phút A4 ,  tốc độ In: 23 bản/phút A4, bộ Nhớ 64MB,  sao chụp liên tục 999 bản',16),('TB00016','Máy In Đa Năng Canon Mf 3010ae  ','Chức năng chuẩn : Copy - In - Scan màu, tốc độ : 18 trang / phút khổ A4, độ phân giải : 600 dpi x 600 dpi, bộ nhớ tiêu chuẩn: 64MB',17),('TB00017',' Máy may Brother GS2500','Kích thước sản phẩm: 37x14x29, trọng lượng 6(KG) ',4),('TB00018','Bàn ủi Philips HD1172','Chiều dài dây: 1.9m, điện áp: 220V/50Hz',4),('TB00019','Mỏ hàn chì CX-301','Trọng lượng 0.3 (KG)',34),('TB00020','Mũ hàn điện tử WH4111  ','Trọng lượng 0,5 Kg, vỏ đen nhám',3),('TB00021','Quạt Lỡ Công nghiệp FACO B4 C-LC1168','Đường kính cánh quạt 400mm, trọng lượng 5Kg',21),('TB00022',' Đồng hồ vạn năng Asaki AK-9180 ','Màn hình hiển thị số 3½ LCD, đo điện thế DC, 200mV - 100μV độ chính xác ± 2.5% + 5, 2000mV – 1mV độ chính xác ± 2.5% + 5',5),('TB00023','Ðồng hồ đo, ghi dữ liệu áp suất KDS DPR100-20 ','Bộ nhớ: 250000 giá trị, độ chính xác: 0.1%,SG, dải áp suất : (0~600) bar',5),('TB00024','Dầu động cơ Gold Star HD 50','Dung tích 4 lít',9),('TB00025',' Giấy nhám đĩa siêu min 3M Trizact Hookit Foam','Trizact 3000 siêu min (1 tờ), quy cách: đường kính 150mm (6in)',9),('TB00026','Dây điện ovan 2 ruột mềm Tân Phú','300/500V CU/PVC 2 x 1mm, vỏ: nhựa PVC, ruột: đồng',34),('TB00027','Kìm tuốt dây điện tự động VKS  ','Có nhiểu cỡ lưỡi dao cắt: 1.0mm;1.6mm;2.0mm;2.6mm;3.',2),('TB00028','Thiếc Hàn SN99.3 500G 0.6MM','Thiếc hàn 99.3 % thiếc, sợi 0.6mm, trọng lượng 500g',34),('TB00029','Laptop Asus X541UV-XX244D','CPU Intel core i3, loại CPU 6100U, 2.3GHz, dung lượng Ram 4 g, ổ cứng 500 GB, màn hình 15.6 inch',32),('TB00030','Rơle bán dẫn FOTEK SSR-25AA-H','Điện áp điều chỉnh 80-250VAC, điện áp tải: 90-480VAC / 25A, nhiệt độ hoạt động: -20-80C',34),('TB00031','Mạch điện tử Arduino Uno R3','Sử dụng vi điều khiển ATmega328, điện áp hoạt động: 5V, 14 chân I/0 số 110000',34);
/*!40000 ALTER TABLE `tb_danh_muc_thiet_bi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_kho`
--

DROP TABLE IF EXISTS `tb_kho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_kho` (
  `ma_kho` int(10) NOT NULL AUTO_INCREMENT,
  `ten_kho` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `thu_kho` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dm_don_vi_id` int(11) NOT NULL,
  PRIMARY KEY (`ma_kho`),
  KEY `ma_phong_ban` (`dm_don_vi_id`),
  KEY `dm_don_vi_id` (`dm_don_vi_id`),
  CONSTRAINT `tb_kho_ibfk_1` FOREIGN KEY (`dm_don_vi_id`) REFERENCES `dmdon_vi` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_kho`
--

LOCK TABLES `tb_kho` WRITE;
/*!40000 ALTER TABLE `tb_kho` DISABLE KEYS */;
INSERT INTO `tb_kho` VALUES (1,'Kho thiết bị của phòng Quản trị thiết bị','Hồ Tấn Lộc',1);
/*!40000 ALTER TABLE `tb_kho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_lich_su_bao_tri`
--

DROP TABLE IF EXISTS `tb_lich_su_bao_tri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_lich_su_bao_tri` (
  `ma_lsbt` int(20) NOT NULL AUTO_INCREMENT,
  `ma_xuat` int(10) DEFAULT NULL,
  `ngay_bao_tri` date DEFAULT NULL,
  `nguoi_bao_tri` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ma_tinh_trang` int(10) DEFAULT NULL,
  `chi_tiet_tinh_trang` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `noi_dung_bao_tri` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `chi_phi` int(20) DEFAULT NULL,
  PRIMARY KEY (`ma_lsbt`),
  KEY `ma_xuat` (`ma_xuat`),
  KEY `ma_phieu_xuat` (`ma_xuat`),
  KEY `ma_tinh_trang` (`ma_tinh_trang`),
  CONSTRAINT `tb_lich_su_bao_tri_ibfk_1` FOREIGN KEY (`ma_xuat`) REFERENCES `tb_xuat` (`ma_xuat`),
  CONSTRAINT `tb_lich_su_bao_tri_ibfk_2` FOREIGN KEY (`ma_tinh_trang`) REFERENCES `tb_tinh_trang_thiet_bi` (`ma_tinh_trang`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_lich_su_bao_tri`
--

LOCK TABLES `tb_lich_su_bao_tri` WRITE;
/*!40000 ALTER TABLE `tb_lich_su_bao_tri` DISABLE KEYS */;
INSERT INTO `tb_lich_su_bao_tri` VALUES (4,1,'2017-07-30','Nguyễn Hồng Chinh',3,'2 chuột hoạt động không hiệu quả','Kiểm tra hoạt động',50000),(6,1,'2017-07-31','Nguyễn Thanh Minh',2,'Các thiết bị hoạt động bình thường','Kiểm tra hoạt động',50000),(7,4,'2017-08-01','Nguyễn Thanh Minh',2,'a','a',20);
/*!40000 ALTER TABLE `tb_lich_su_bao_tri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_lich_su_nguoi_quan_ly`
--

DROP TABLE IF EXISTS `tb_lich_su_nguoi_quan_ly`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_lich_su_nguoi_quan_ly` (
  `ma_lsnql` int(10) NOT NULL AUTO_INCREMENT,
  `ma_phieu_xuat` int(11) DEFAULT NULL,
  `ma_nql` int(10) DEFAULT NULL,
  `ngay_bat_dau` date DEFAULT NULL,
  `ngay_ket_thuc` date DEFAULT NULL,
  PRIMARY KEY (`ma_lsnql`),
  KEY `ma_xuat` (`ma_phieu_xuat`),
  KEY `ma_phieu_xuat` (`ma_phieu_xuat`),
  KEY `ma_phieu_nhap` (`ma_phieu_xuat`),
  KEY `ma_phieu_xuat_2` (`ma_phieu_xuat`),
  CONSTRAINT `tb_lich_su_nguoi_quan_ly_ibfk_1` FOREIGN KEY (`ma_phieu_xuat`) REFERENCES `tb_phieu_xuat` (`ma_phieu_xuat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_lich_su_nguoi_quan_ly`
--

LOCK TABLES `tb_lich_su_nguoi_quan_ly` WRITE;
/*!40000 ALTER TABLE `tb_lich_su_nguoi_quan_ly` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_lich_su_nguoi_quan_ly` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_nhom_thiet_bi`
--

DROP TABLE IF EXISTS `tb_nhom_thiet_bi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_nhom_thiet_bi` (
  `ma_nhom_thiet_bi` int(10) NOT NULL AUTO_INCREMENT,
  `ten_nhom_thiet_bi` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ghi_chu` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ma_nhom_thiet_bi`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_nhom_thiet_bi`
--

LOCK TABLES `tb_nhom_thiet_bi` WRITE;
/*!40000 ALTER TABLE `tb_nhom_thiet_bi` DISABLE KEYS */;
INSERT INTO `tb_nhom_thiet_bi` VALUES (1,'Thiết bị phục vụ giảng dạy','Các loại thiết bị chuyển về các khoa phục vụ giảng dạy học viên phù hợp với từng học phần '),(2,'Thiết bị văn phòng, quản lý','Các loại thiết bị phục vụ công tác quản lý ở các phòng ban quản lý ');
/*!40000 ALTER TABLE `tb_nhom_thiet_bi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_nuoc_san_xuat`
--

DROP TABLE IF EXISTS `tb_nuoc_san_xuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_nuoc_san_xuat` (
  `ma_nuoc_san_xuat` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `nuoc_san_xuat` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ma_nuoc_san_xuat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_nuoc_san_xuat`
--

LOCK TABLES `tb_nuoc_san_xuat` WRITE;
/*!40000 ALTER TABLE `tb_nuoc_san_xuat` DISABLE KEYS */;
INSERT INTO `tb_nuoc_san_xuat` VALUES ('AU','Australia'),('CH','Thụy Sĩ'),('CN','Trung Quốc	'),('DE','Đức'),('FR','Pháp'),('GB','Anh'),('ID','Indonesia '),('IN','Ấn Độ'),('IT','Ý'),('JP','Nhật Bản'),('KR','Hàn Quốc'),('MY','Malaysia'),('NL','Hà Lan'),('RU','Nga'),('SG','Singapore'),('TH','Thái Lan'),('TL','Thái Lan'),('US','Hoa Kỳ'),('VN','Việt Nam');
/*!40000 ALTER TABLE `tb_nuoc_san_xuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_phieu_de_xuat_sua_chua`
--

DROP TABLE IF EXISTS `tb_phieu_de_xuat_sua_chua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_phieu_de_xuat_sua_chua` (
  `ma_phieu_de_xuat` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `nguoi_de_xuat` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngay_de_xuat` date DEFAULT NULL,
  `noi_dung_de_xuat` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `da_xac_nhan` tinyint(1) DEFAULT NULL,
  `ma_lsbt` int(20) DEFAULT NULL,
  PRIMARY KEY (`ma_phieu_de_xuat`),
  KEY `ma_lsbt` (`ma_lsbt`),
  CONSTRAINT `tb_phieu_de_xuat_sua_chua_ibfk_1` FOREIGN KEY (`ma_lsbt`) REFERENCES `tb_lich_su_bao_tri` (`ma_lsbt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_phieu_de_xuat_sua_chua`
--

LOCK TABLES `tb_phieu_de_xuat_sua_chua` WRITE;
/*!40000 ALTER TABLE `tb_phieu_de_xuat_sua_chua` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_phieu_de_xuat_sua_chua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_phieu_nhap`
--

DROP TABLE IF EXISTS `tb_phieu_nhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_phieu_nhap` (
  `ma_phieu_nhap` int(11) NOT NULL AUTO_INCREMENT,
  `so_phieu_nhap` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngay_nhap` date DEFAULT NULL,
  `nguoi_giao` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tong_gia_tri` int(20) DEFAULT NULL,
  `nguon_goc` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nguon_goc_tai_san` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ma_kho` int(10) DEFAULT NULL,
  `dm_nhan_vien_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ma_phieu_nhap`),
  KEY `ma_kho` (`ma_kho`),
  KEY `ma_nql` (`dm_nhan_vien_id`),
  CONSTRAINT `tb_phieu_nhap_ibfk_1` FOREIGN KEY (`ma_kho`) REFERENCES `tb_kho` (`ma_kho`),
  CONSTRAINT `tb_phieu_nhap_ibfk_2` FOREIGN KEY (`dm_nhan_vien_id`) REFERENCES `dmnhan_vien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_phieu_nhap`
--

LOCK TABLES `tb_phieu_nhap` WRITE;
/*!40000 ALTER TABLE `tb_phieu_nhap` DISABLE KEYS */;
INSERT INTO `tb_phieu_nhap` VALUES (1,'N0205171','2017-05-02','Trần Thu Thảo',7900000,'Công ty cung cấp thiết bị dạy nghề Ngọc Huy','Nguồn ngân sách trường phục vụ mua sắm thiết bị mới',1,2),(2,'N0405171','2017-05-04','Lê Trung Chính',8610000,'Công ty cổ phần thiết bị giáo dục','Nguồn ngân sách trường Cao Đẳng Nghề Đà Nẵng',1,2),(6,'N1706171','2017-06-17','Lê Khánh Hòa',2400000,'Công ty cổ phần thiết bị giáo dục','Nguồn ngân sách trường phục vụ mua sắm thiết bị giảng dạy',1,2),(8,'N0108171','2017-08-01','A',48000,'Cty A','B',1,4);
/*!40000 ALTER TABLE `tb_phieu_nhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_phieu_xuat`
--

DROP TABLE IF EXISTS `tb_phieu_xuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_phieu_xuat` (
  `ma_phieu_xuat` int(11) NOT NULL AUTO_INCREMENT,
  `so_phieu_xuat` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngay_xuat` date DEFAULT NULL,
  `nguoi_xuat` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tong_gia_tri` int(20) DEFAULT NULL,
  `ma_kho` int(10) DEFAULT NULL,
  `dm_nhan_vien_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ma_phieu_xuat`),
  KEY `ma_kho` (`dm_nhan_vien_id`),
  KEY `ma_kho_2` (`ma_kho`),
  CONSTRAINT `tb_phieu_xuat_ibfk_4` FOREIGN KEY (`ma_kho`) REFERENCES `tb_kho` (`ma_kho`),
  CONSTRAINT `tb_phieu_xuat_ibfk_5` FOREIGN KEY (`dm_nhan_vien_id`) REFERENCES `dmnhan_vien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_phieu_xuat`
--

LOCK TABLES `tb_phieu_xuat` WRITE;
/*!40000 ALTER TABLE `tb_phieu_xuat` DISABLE KEYS */;
INSERT INTO `tb_phieu_xuat` VALUES (3,'X1706171','2017-06-17','Nguyễn Quốc Anh',0,1,3),(4,'X1806171','2017-06-18','Nguyễn Quốc Anh',0,1,3),(5,'X2807171','2017-07-28','Nguyễn Quốc Anh',18436000,1,3);
/*!40000 ALTER TABLE `tb_phieu_xuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_quyen_truy_cap`
--

DROP TABLE IF EXISTS `tb_quyen_truy_cap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_quyen_truy_cap` (
  `ma_qtc` int(11) NOT NULL AUTO_INCREMENT,
  `quyen` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ma_qtc`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_quyen_truy_cap`
--

LOCK TABLES `tb_quyen_truy_cap` WRITE;
/*!40000 ALTER TABLE `tb_quyen_truy_cap` DISABLE KEYS */;
INSERT INTO `tb_quyen_truy_cap` VALUES (1,'Quản trị viên'),(2,'Người dùng');
/*!40000 ALTER TABLE `tb_quyen_truy_cap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tai_khoan_he_thong`
--

DROP TABLE IF EXISTS `tb_tai_khoan_he_thong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tai_khoan_he_thong` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ten_dang_nhap` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `mat_khau` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `nhan_vien_id` int(11) DEFAULT NULL,
  `ma_qtc` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ms_nhan_vien` (`nhan_vien_id`),
  KEY `ma_qtc` (`ma_qtc`),
  CONSTRAINT `tb_tai_khoan_he_thong_ibfk_1` FOREIGN KEY (`ma_qtc`) REFERENCES `tb_quyen_truy_cap` (`ma_qtc`),
  CONSTRAINT `tb_tai_khoan_he_thong_ibfk_2` FOREIGN KEY (`nhan_vien_id`) REFERENCES `dmnhan_vien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tai_khoan_he_thong`
--

LOCK TABLES `tb_tai_khoan_he_thong` WRITE;
/*!40000 ALTER TABLE `tb_tai_khoan_he_thong` DISABLE KEYS */;
INSERT INTO `tb_tai_khoan_he_thong` VALUES (1,'User001','12345',2,1),(2,'User002','12345',3,1),(3,'User003','12345',4,1),(4,'User004','12345',5,1),(5,'User006','12345',6,1),(6,'User007','12345',11,2);
/*!40000 ALTER TABLE `tb_tai_khoan_he_thong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tinh_trang_thiet_bi`
--

DROP TABLE IF EXISTS `tb_tinh_trang_thiet_bi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tinh_trang_thiet_bi` (
  `ma_tinh_trang` int(10) NOT NULL AUTO_INCREMENT,
  `tinh_trang` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ghi_chu` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ma_tinh_trang`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tinh_trang_thiet_bi`
--

LOCK TABLES `tb_tinh_trang_thiet_bi` WRITE;
/*!40000 ALTER TABLE `tb_tinh_trang_thiet_bi` DISABLE KEYS */;
INSERT INTO `tb_tinh_trang_thiet_bi` VALUES (1,'Tốt','Các thiết bị ở trạng thái hoạt động tốt, không hư hỏng'),(2,'Bình thường','Các thiết bị hoạt động bình thường, công suất có giảm không nhiều do nguyên nhân khách quan'),(3,'Kém','Các thiết bị hoạt động kém hiệu quả, công suất giảm, cần phải được bảo dưỡng, sủa chữa'),(4,'Xin thanh lý','Các thiết bị không hoạt động được, hiệu năng rất thấp cần phải được thanh lý');
/*!40000 ALTER TABLE `tb_tinh_trang_thiet_bi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_xuat`
--

DROP TABLE IF EXISTS `tb_xuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_xuat` (
  `ma_xuat` int(10) NOT NULL AUTO_INCREMENT,
  `ma_phieu_xuat` int(11) DEFAULT NULL,
  `thiet_bi_id` int(10) DEFAULT NULL,
  `so_luong_xuat` int(10) DEFAULT NULL,
  `tinh_trang_thiet_bi` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ma_xuat`),
  KEY `ma_phieu_xuat` (`ma_phieu_xuat`,`thiet_bi_id`),
  KEY `thiet_bi_id` (`thiet_bi_id`),
  CONSTRAINT `tb_xuat_ibfk_2` FOREIGN KEY (`thiet_bi_id`) REFERENCES `tb_chi_tiet_nhap_xuat` (`thiet_bi_id`),
  CONSTRAINT `tb_xuat_ibfk_3` FOREIGN KEY (`ma_phieu_xuat`) REFERENCES `tb_phieu_xuat` (`ma_phieu_xuat`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_xuat`
--

LOCK TABLES `tb_xuat` WRITE;
/*!40000 ALTER TABLE `tb_xuat` DISABLE KEYS */;
INSERT INTO `tb_xuat` VALUES (1,5,20,4,'Bình thường'),(2,5,19,4,'Tốt'),(4,5,24,3,'Bình thường');
/*!40000 ALTER TABLE `tb_xuat` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tk_tai_khoan_he_thong`
--

LOCK TABLES `tk_tai_khoan_he_thong` WRITE;
/*!40000 ALTER TABLE `tk_tai_khoan_he_thong` DISABLE KEYS */;
INSERT INTO `tk_tai_khoan_he_thong` VALUES (2,'Nguyễn Minh Trí123','102120160','102120160'),(3,'321312 321312','111','111'),(7,'Nguyễn Minh Trí','123456','111111'),(11,'Ngô Văn Dũng','1','1'),(12,'Trần Phước Phú','2','2'),(13,'Phan Thành Tài','3','3'),(14,'Huỳnh Thị Chung','4','4'),(15,'Trần Ngọc Anh','5','5'),(16,'Nguyễn Văn Tám','6','6'),(17,'Nguyễn Minh Tân','7','7'),(18,'Lê Minh Xuân','8','8'),(19,'Lê Đức Trọng Nguyễn','9','9'),(20,' Chung','10','10'),(21,'Hồ Khánh Huy','11','11'),(22,'Nguyễn Văn Trung','12','12'),(23,'Đỗ Xuân Huy','13','13'),(24,'Lê Đình Cảnh','14','14'),(25,'Tôn Thất Hoàng Đạt','15','15'),(26,'Nguyễn Thị Tịnh Giao','16','16'),(27,'Lê Đức Thọ','19','19'),(28,'Nguyễn Thị Minh Hương','20','20'),(29,'Ngô Thị Phước Yên','21','21'),(30,'Nguyễn Duy Linh','22','22'),(31,'Phan Minh Mẫn','23','23'),(33,'Thúy Thúy','25','25'),(34,' Sang','26','26'),(35,' Phương','27','27'),(36,'Lê Thị Hồng','28','28'),(37,'Huỳnh Thị Kim Sang','29','29'),(38,'Phạm Hồng Giang','30','30'),(39,'Nguyễn Thanh Thảo','31','31'),(40,'Phan Lê Trúc Linh','32','32'),(41,'Đoàn Ngọc Hoàng','33','33'),(42,'Lê Nguyễn Cao Tài','34','34'),(43,'Hồ Thị Thanh Tâm','35','35'),(44,'Trương Thị Minh Hoa','36','36'),(45,'Phạm Thị Anh Đài','37','37'),(47,'Phạm Thanh Linh','39','39'),(48,'Đoàn Ngọc Hoàng','40','40'),(49,' Trang','41','41');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tk_tai_khoan_he_thong_vai_tro`
--

LOCK TABLES `tk_tai_khoan_he_thong_vai_tro` WRITE;
/*!40000 ALTER TABLE `tk_tai_khoan_he_thong_vai_tro` DISABLE KEYS */;
INSERT INTO `tk_tai_khoan_he_thong_vai_tro` VALUES (4,2,1),(5,3,1),(6,7,3);
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
  `bat_buoc` int(11) NOT NULL,
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
INSERT INTO `tkb_dieu_kien_tu_dong` VALUES (1,0,1,30,10,'1. Giáo viên dạy không quá 8 giờ/ngày'),(2,0,2,30,10,'2. Giáo viên dạy không quá 6 giờ lý thuyết/ngày'),(3,0,3,35,15,'3. Giáo viên dạy không quá 30 giờ lý thuyết/tuần'),(4,0,4,40,20,'4. Lớp môn học không xung đột với ngày nghỉ trong tuần của giáo viên'),(5,0,5,50,30,'5. Các lịch của một lớp môn học không trùng thứ'),(6,1,6,200,100,'6. Lớp môn học không xung đột với lịch của giáo viên'),(7,1,7,200,100,'7. Lớp môn học không xung đột với lịch của lớp khác cùng khoa-khóa học-ngành-nhóm'),(8,1,8,200,100,'8. Phòng học không có nhiều hơn 1 lớp trong cùng 1 khoảng thời gian'),(9,1,9,200,100,'9. 1 khoa-khóa học-ngành không có nhiều hơn 3 lớp học cùng 1 khoảng thời gian');
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
INSERT INTO `tkb_khoa_hoc` VALUES (1,2017),(2,2018),(4,2015),(5,2016);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_khoa_khoa_hoc`
--

LOCK TABLES `tkb_khoa_khoa_hoc` WRITE;
/*!40000 ALTER TABLE `tkb_khoa_khoa_hoc` DISABLE KEYS */;
INSERT INTO `tkb_khoa_khoa_hoc` VALUES (4,20,9,2,9,4),(5,20,11,4,11,5);
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_khoa_khoa_hoc_nganh`
--

LOCK TABLES `tkb_khoa_khoa_hoc_nganh` WRITE;
/*!40000 ALTER TABLE `tkb_khoa_khoa_hoc_nganh` DISABLE KEYS */;
INSERT INTO `tkb_khoa_khoa_hoc_nganh` VALUES (20,13,4),(21,14,4),(23,13,5),(24,14,5);
/*!40000 ALTER TABLE `tkb_khoa_khoa_hoc_nganh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tkb_khoa_khoa_hoc_nganh_nhom`
--

DROP TABLE IF EXISTS `tkb_khoa_khoa_hoc_nganh_nhom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tkb_khoa_khoa_hoc_nganh_nhom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nhom` int(11) NOT NULL,
  `tkb_khoa_khoa_hoc_id` int(11) DEFAULT NULL,
  `tkb_khoa_khoa_hoc_nganh_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2sy4dffik5spcuwqwxd806wyf` (`tkb_khoa_khoa_hoc_id`),
  KEY `FKrfpr7et9s9eoff393dbis7tct` (`tkb_khoa_khoa_hoc_nganh_id`),
  CONSTRAINT `FK2sy4dffik5spcuwqwxd806wyf` FOREIGN KEY (`tkb_khoa_khoa_hoc_id`) REFERENCES `tkb_khoa_khoa_hoc` (`id`),
  CONSTRAINT `FKrfpr7et9s9eoff393dbis7tct` FOREIGN KEY (`tkb_khoa_khoa_hoc_nganh_id`) REFERENCES `tkb_khoa_khoa_hoc_nganh` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_khoa_khoa_hoc_nganh_nhom`
--

LOCK TABLES `tkb_khoa_khoa_hoc_nganh_nhom` WRITE;
/*!40000 ALTER TABLE `tkb_khoa_khoa_hoc_nganh_nhom` DISABLE KEYS */;
INSERT INTO `tkb_khoa_khoa_hoc_nganh_nhom` VALUES (15,13,4,NULL),(17,14,4,NULL),(18,11,5,NULL),(19,15,5,NULL),(25,15,NULL,23),(30,15,NULL,20),(32,16,NULL,20),(34,13,NULL,21),(35,14,NULL,21),(36,16,NULL,23),(37,13,NULL,24),(38,14,NULL,24);
/*!40000 ALTER TABLE `tkb_khoa_khoa_hoc_nganh_nhom` ENABLE KEYS */;
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
  `da_sinh_lich` bit(1) NOT NULL,
  `ngay_bat_dau` date NOT NULL,
  `ngay_ket_thuc` date NOT NULL,
  `tkb_ki_hoc_id` int(11) NOT NULL,
  `tkb_nam_hoc_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKarhqm9u9i1wu1cox1353bhso5` (`tkb_ki_hoc_id`),
  KEY `FKbytxqvushxdlco60vedt5si1` (`tkb_nam_hoc_id`),
  CONSTRAINT `FKarhqm9u9i1wu1cox1353bhso5` FOREIGN KEY (`tkb_ki_hoc_id`) REFERENCES `tkb_ki_hoc` (`id`),
  CONSTRAINT `FKbytxqvushxdlco60vedt5si1` FOREIGN KEY (`tkb_nam_hoc_id`) REFERENCES `tkb_nam_hoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_ki_hoc_nam_hoc`
--

LOCK TABLES `tkb_ki_hoc_nam_hoc` WRITE;
/*!40000 ALTER TABLE `tkb_ki_hoc_nam_hoc` DISABLE KEYS */;
INSERT INTO `tkb_ki_hoc_nam_hoc` VALUES (1,'','2017-08-21','2018-01-07',1,1),(2,'\0','2018-01-22','2018-06-03',2,1),(3,'\0','2018-08-20','2019-01-06',1,2),(4,'\0','2019-01-21','2019-06-09',2,2),(5,'\0','2019-08-19','2020-01-05',1,3),(6,'\0','2020-01-20','2020-06-07',2,3),(7,'\0','2020-08-17','2021-01-03',1,4),(8,'\0','2021-01-18','2021-06-06',2,4),(9,'\0','2015-08-24','2016-01-03',1,5),(10,'\0','2016-01-11','2016-06-05',2,5),(11,'\0','2016-08-22','2017-01-01',1,6),(12,'\0','2017-01-09','2017-06-04',2,6);
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_ki_hoc_nam_hoc_dieu_kien`
--

LOCK TABLES `tkb_ki_hoc_nam_hoc_dieu_kien` WRITE;
/*!40000 ALTER TABLE `tkb_ki_hoc_nam_hoc_dieu_kien` DISABLE KEYS */;
INSERT INTO `tkb_ki_hoc_nam_hoc_dieu_kien` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1),(7,7,1),(8,8,1),(9,9,1),(10,1,2),(11,2,2),(12,3,2),(13,4,2),(14,5,2),(15,6,2),(16,7,2),(17,8,2),(18,9,2);
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
  `thi_cuoi_ky` bit(1) NOT NULL DEFAULT b'0',
  `thi_giua_ky` bit(1) NOT NULL DEFAULT b'0',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_lich_hoc_theo_ngay`
--

LOCK TABLES `tkb_lich_hoc_theo_ngay` WRITE;
/*!40000 ALTER TABLE `tkb_lich_hoc_theo_ngay` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_lich_hoc_theo_ngay_diem_danh`
--

LOCK TABLES `tkb_lich_hoc_theo_ngay_diem_danh` WRITE;
/*!40000 ALTER TABLE `tkb_lich_hoc_theo_ngay_diem_danh` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_lich_hoc_theo_ngay_sinh_vien_ghi_chu`
--

LOCK TABLES `tkb_lich_hoc_theo_ngay_sinh_vien_ghi_chu` WRITE;
/*!40000 ALTER TABLE `tkb_lich_hoc_theo_ngay_sinh_vien_ghi_chu` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_lich_hoc_theo_tuan`
--

LOCK TABLES `tkb_lich_hoc_theo_tuan` WRITE;
/*!40000 ALTER TABLE `tkb_lich_hoc_theo_tuan` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_nam_hoc`
--

LOCK TABLES `tkb_nam_hoc` WRITE;
/*!40000 ALTER TABLE `tkb_nam_hoc` DISABLE KEYS */;
INSERT INTO `tkb_nam_hoc` VALUES (1,'2017-2018','2017-08-21','2018-06-03'),(2,'2018-2019','2018-08-20','2019-06-09'),(3,'2019-2020','2019-08-19','2020-06-07'),(4,'2020-2021','2020-08-17','2021-06-06'),(5,'2015-2016','2015-08-24','2016-06-05'),(6,'2016-2017','2016-08-22','2017-06-04');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_nhan_vien_ngay_nghi_trong_tuan`
--

LOCK TABLES `tkb_nhan_vien_ngay_nghi_trong_tuan` WRITE;
/*!40000 ALTER TABLE `tkb_nhan_vien_ngay_nghi_trong_tuan` DISABLE KEYS */;
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
  `co_the_dang_ky` bit(1) NOT NULL,
  `ki` int(11) NOT NULL,
  `dm_mon_hoc_id` int(11) NOT NULL,
  `dm_sinh_vien_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKskf9p7dh3l8k74u4iwohi9lyq` (`dm_mon_hoc_id`),
  KEY `FKi8fmjf9cq0ojj5ghx5lu1ke8a` (`dm_sinh_vien_id`),
  CONSTRAINT `FKi8fmjf9cq0ojj5ghx5lu1ke8a` FOREIGN KEY (`dm_sinh_vien_id`) REFERENCES `dmsinh_vien` (`id`),
  CONSTRAINT `FKskf9p7dh3l8k74u4iwohi9lyq` FOREIGN KEY (`dm_mon_hoc_id`) REFERENCES `dmmon_hoc` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_sinh_vien_lo_trinh_mon_hoc`
--

LOCK TABLES `tkb_sinh_vien_lo_trinh_mon_hoc` WRITE;
/*!40000 ALTER TABLE `tkb_sinh_vien_lo_trinh_mon_hoc` DISABLE KEYS */;
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
  `status` bit(1) NOT NULL,
  `tkb_khoa_khoa_hoc_id` int(11) DEFAULT NULL,
  `tkb_ki_hoc_nam_hoc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK53c2t2m4r43qle9ftu1opsdme` (`tkb_khoa_khoa_hoc_id`),
  KEY `FK7teel9p7l4cphl1ew3uwb5dn2` (`tkb_ki_hoc_nam_hoc_id`),
  CONSTRAINT `FK53c2t2m4r43qle9ftu1opsdme` FOREIGN KEY (`tkb_khoa_khoa_hoc_id`) REFERENCES `tkb_khoa_khoa_hoc` (`id`),
  CONSTRAINT `FK7teel9p7l4cphl1ew3uwb5dn2` FOREIGN KEY (`tkb_ki_hoc_nam_hoc_id`) REFERENCES `tkb_ki_hoc_nam_hoc` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tkb_thoi_gian_dang_ky`
--

LOCK TABLES `tkb_thoi_gian_dang_ky` WRITE;
/*!40000 ALTER TABLE `tkb_thoi_gian_dang_ky` DISABLE KEYS */;
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

-- Dump completed on 2017-09-06 14:49:20
