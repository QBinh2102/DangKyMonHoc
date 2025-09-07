-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: database
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `buoi_hoc`
--

DROP TABLE IF EXISTS `buoi_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buoi_hoc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mon_hoc_id` int NOT NULL,
  `giang_vien_id` int DEFAULT NULL,
  `hoc_ky_id` int NOT NULL,
  `si_so` int DEFAULT '50',
  `loai` varchar(255) DEFAULT NULL,
  `lop_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mon_hoc_id` (`mon_hoc_id`,`hoc_ky_id`,`lop_id`),
  KEY `giang_vien_id` (`giang_vien_id`),
  KEY `hoc_ky_id` (`hoc_ky_id`),
  KEY `lop_id` (`lop_id`),
  CONSTRAINT `buoi_hoc_ibfk_1` FOREIGN KEY (`mon_hoc_id`) REFERENCES `mon_hoc` (`id`),
  CONSTRAINT `buoi_hoc_ibfk_2` FOREIGN KEY (`giang_vien_id`) REFERENCES `giang_vien` (`id`),
  CONSTRAINT `buoi_hoc_ibfk_3` FOREIGN KEY (`hoc_ky_id`) REFERENCES `hoc_ky` (`id`),
  CONSTRAINT `buoi_hoc_ibfk_4` FOREIGN KEY (`lop_id`) REFERENCES `lop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buoi_hoc`
--

LOCK TABLES `buoi_hoc` WRITE;
/*!40000 ALTER TABLE `buoi_hoc` DISABLE KEYS */;
INSERT INTO `buoi_hoc` VALUES (1,1,14,1,10,'LT-TH',1),(2,2,4,1,10,'LT-TH',1),(3,3,6,2,10,'LT',1),(4,4,2,2,10,'LT-TH',1),(5,5,5,3,10,'LT-TH',1),(6,6,2,3,10,'LT-TH',1),(7,7,7,3,10,'LT-TH',1),(8,8,17,4,10,'LT',1),(9,9,3,4,10,'LT-TH',1),(10,10,5,4,10,'LT-TH',1),(11,11,10,5,10,'LT-TH',1),(12,12,4,5,10,'LT',1),(13,13,9,5,10,'LT',1),(14,14,10,6,10,'LT-TH',1),(15,15,12,6,10,'LT-TH',1),(16,16,11,7,10,'LT-TH',1),(17,17,10,7,10,'LT-TH',1),(18,18,10,7,10,'LT-TH',1),(19,19,12,8,10,'LT-TH',1),(20,20,13,8,10,'LT-TH',1),(21,21,10,8,10,'LT-TH',1),(22,32,NULL,9,NULL,'TH',1),(23,33,NULL,10,NULL,'TH',1),(24,1,13,7,10,'LT-TH',2),(25,2,18,7,10,'LT-TH',2),(26,3,6,8,10,'LT',2),(27,4,18,8,10,'LT-TH',2),(28,10,15,9,10,'LT-TH',2),(29,22,15,9,10,'LT-TH',2),(30,23,4,9,10,'LT-TH',2),(31,1,13,7,10,'LT-TH',5),(32,2,18,7,10,'LT-TH',5),(33,3,6,8,10,'LT',5),(34,4,18,8,10,'LT-TH',5),(35,10,15,9,10,'LT-TH',5),(36,22,15,9,10,'LT-TH',5),(37,23,4,9,10,'LT-TH',5),(38,8,17,10,10,'LT',2),(39,8,1,10,1,'LT',3),(40,11,10,10,10,'LT-TH',2),(41,11,10,10,10,'LT-TH',4),(42,24,15,10,10,'LT-TH',2),(43,24,14,10,10,'LT-TH',5),(44,8,17,10,10,'LT',5),(45,11,10,10,10,'LT-TH',5),(46,1,14,10,10,'LT-TH',6),(47,2,4,10,10,'LT-TH',6),(48,1,3,10,10,'LT-TH',7),(49,2,4,10,10,'LT-TH',7),(50,1,6,10,10,'LT-TH',8),(51,2,5,10,10,'LT-TH',8);
/*!40000 ALTER TABLE `buoi_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chi_tiet_hoc_phi`
--

DROP TABLE IF EXISTS `chi_tiet_hoc_phi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chi_tiet_hoc_phi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hoc_phi_id` int NOT NULL,
  `mon_hoc_id` int NOT NULL,
  `chi_phi` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `hoc_phi_id` (`hoc_phi_id`,`mon_hoc_id`),
  KEY `mon_hoc_id` (`mon_hoc_id`),
  CONSTRAINT `chi_tiet_hoc_phi_ibfk_1` FOREIGN KEY (`hoc_phi_id`) REFERENCES `hoc_phi` (`id`),
  CONSTRAINT `chi_tiet_hoc_phi_ibfk_2` FOREIGN KEY (`mon_hoc_id`) REFERENCES `mon_hoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chi_tiet_hoc_phi`
--

LOCK TABLES `chi_tiet_hoc_phi` WRITE;
/*!40000 ALTER TABLE `chi_tiet_hoc_phi` DISABLE KEYS */;
INSERT INTO `chi_tiet_hoc_phi` VALUES (1,1,1,2100000.00),(2,1,2,2800000.00),(3,2,3,2100000.00),(4,2,4,2800000.00),(5,3,5,2100000.00),(6,3,6,2800000.00),(7,3,7,2800000.00),(8,4,8,2800000.00),(9,4,9,2100000.00),(10,4,10,2800000.00),(11,5,11,2800000.00),(12,5,12,2100000.00),(13,5,13,2800000.00),(14,6,14,2100000.00),(15,6,15,2100000.00),(16,7,16,2100000.00),(17,7,17,2100000.00),(18,7,18,2100000.00),(19,8,19,2100000.00),(20,8,20,2100000.00),(21,8,21,2100000.00),(22,9,32,2800000.00),(23,10,1,2100000.00),(24,10,2,2800000.00),(25,11,3,2100000.00),(26,11,4,2800000.00),(27,12,5,2100000.00),(28,12,6,2800000.00),(29,12,7,2800000.00),(30,13,8,2800000.00),(31,13,9,2100000.00),(32,13,10,2800000.00),(33,14,11,2800000.00),(34,14,12,2100000.00),(35,14,13,2800000.00),(36,15,14,2100000.00),(37,15,15,2100000.00),(38,16,16,2100000.00),(39,16,17,2100000.00),(40,16,18,2100000.00),(41,17,19,2100000.00),(42,17,20,2100000.00),(43,17,21,2100000.00),(44,18,32,2800000.00),(45,19,1,2100000.00),(46,19,2,2800000.00),(47,20,3,2100000.00),(48,20,4,2800000.00),(49,21,5,2100000.00),(50,21,6,2800000.00),(51,21,7,2800000.00),(52,22,8,2800000.00),(53,22,9,2100000.00),(54,22,10,2800000.00),(55,23,11,2800000.00),(56,23,12,2100000.00),(57,23,13,2800000.00),(58,24,14,2100000.00),(59,24,15,2100000.00),(60,25,16,2100000.00),(61,25,17,2100000.00),(62,25,18,2100000.00),(63,26,19,2100000.00),(64,26,20,2100000.00),(65,26,21,2100000.00),(66,26,3,2100000.00),(67,27,32,2800000.00),(68,28,1,2100000.00),(69,28,2,2800000.00),(70,29,3,2100000.00),(71,29,4,2800000.00),(72,30,10,2800000.00),(73,30,22,2100000.00),(74,30,23,2800000.00),(76,32,1,2100000.00),(77,32,2,2800000.00),(78,33,3,2100000.00),(79,33,4,2800000.00),(80,34,10,2800000.00),(81,34,22,2100000.00),(82,34,23,2800000.00);
/*!40000 ALTER TABLE `chi_tiet_hoc_phi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dang_ky`
--

DROP TABLE IF EXISTS `dang_ky`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dang_ky` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sinh_vien_id` int NOT NULL,
  `buoi_hoc_id` int NOT NULL,
  `hoc_ky_id` int NOT NULL,
  `trang_thai` varchar(255) DEFAULT NULL,
  `ngay_dang_ky` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sinh_vien_id` (`sinh_vien_id`,`buoi_hoc_id`),
  KEY `buoi_hoc_id` (`buoi_hoc_id`),
  KEY `hoc_ky_id` (`hoc_ky_id`),
  CONSTRAINT `dang_ky_ibfk_1` FOREIGN KEY (`sinh_vien_id`) REFERENCES `sinh_vien` (`id`),
  CONSTRAINT `dang_ky_ibfk_2` FOREIGN KEY (`buoi_hoc_id`) REFERENCES `buoi_hoc` (`id`),
  CONSTRAINT `dang_ky_ibfk_3` FOREIGN KEY (`hoc_ky_id`) REFERENCES `hoc_ky` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dang_ky`
--

LOCK TABLES `dang_ky` WRITE;
/*!40000 ALTER TABLE `dang_ky` DISABLE KEYS */;
INSERT INTO `dang_ky` VALUES (1,19,1,1,'HOAN_THANH','2025-09-07 03:02:05'),(2,19,2,1,'HOAN_THANH','2025-09-07 03:02:05'),(3,19,3,2,'HOAN_THANH','2025-09-07 03:02:05'),(4,19,4,2,'HOAN_THANH','2025-09-07 03:02:05'),(5,19,5,3,'HOAN_THANH','2025-09-07 03:02:05'),(6,19,6,3,'HOAN_THANH','2025-09-07 03:02:05'),(7,19,7,3,'HOAN_THANH','2025-09-07 03:02:05'),(8,19,8,4,'HOAN_THANH','2025-09-07 03:02:05'),(9,19,9,4,'HOAN_THANH','2025-09-07 03:02:05'),(10,19,10,4,'HOAN_THANH','2025-09-07 03:02:05'),(11,19,11,5,'HOAN_THANH','2025-09-07 03:02:05'),(12,19,12,5,'HOAN_THANH','2025-09-07 03:02:05'),(13,19,13,5,'HOAN_THANH','2025-09-07 03:02:05'),(14,19,14,6,'HOAN_THANH','2025-09-07 03:02:05'),(15,19,15,6,'HOAN_THANH','2025-09-07 03:02:05'),(16,19,16,7,'HOAN_THANH','2025-09-07 03:02:05'),(17,19,17,7,'HOAN_THANH','2025-09-07 03:02:05'),(18,19,18,7,'HOAN_THANH','2025-09-07 03:02:05'),(19,19,19,8,'HOAN_THANH','2025-09-07 03:02:05'),(20,19,20,8,'HOAN_THANH','2025-09-07 03:02:05'),(21,19,21,8,'HOAN_THANH','2025-09-07 03:02:05'),(22,19,22,9,'HOAN_THANH','2025-09-07 03:02:05'),(23,20,1,1,'HOAN_THANH','2025-09-07 03:02:05'),(24,20,2,1,'HOAN_THANH','2025-09-07 03:02:05'),(25,20,3,2,'HOAN_THANH','2025-09-07 03:02:05'),(26,20,4,2,'HOAN_THANH','2025-09-07 03:02:05'),(27,20,5,3,'HOAN_THANH','2025-09-07 03:02:05'),(28,20,6,3,'HOAN_THANH','2025-09-07 03:02:05'),(29,20,7,3,'HOAN_THANH','2025-09-07 03:02:05'),(30,20,8,4,'HOAN_THANH','2025-09-07 03:02:05'),(31,20,9,4,'HOAN_THANH','2025-09-07 03:02:05'),(32,20,10,4,'HOAN_THANH','2025-09-07 03:02:05'),(33,20,11,5,'HOAN_THANH','2025-09-07 03:02:05'),(34,20,12,5,'HOAN_THANH','2025-09-07 03:02:05'),(35,20,13,5,'HOAN_THANH','2025-09-07 03:02:05'),(36,20,14,6,'HOAN_THANH','2025-09-07 03:02:05'),(37,20,15,6,'HOAN_THANH','2025-09-07 03:02:05'),(38,20,16,7,'HOAN_THANH','2025-09-07 03:02:05'),(39,20,17,7,'HOAN_THANH','2025-09-07 03:02:05'),(40,20,18,7,'HOAN_THANH','2025-09-07 03:02:05'),(41,20,19,8,'HOAN_THANH','2025-09-07 03:02:05'),(42,20,20,8,'HOAN_THANH','2025-09-07 03:02:05'),(43,20,21,8,'HOAN_THANH','2025-09-07 03:02:05'),(44,20,22,9,'HOAN_THANH','2025-09-07 03:02:05'),(45,21,1,1,'HOAN_THANH','2025-09-07 03:02:05'),(46,21,2,1,'TRUOT','2025-09-07 03:02:05'),(47,21,3,2,'TRUOT','2025-09-07 03:02:05'),(48,21,4,2,'HOAN_THANH','2025-09-07 03:02:05'),(49,21,5,3,'HOAN_THANH','2025-09-07 03:02:05'),(50,21,6,3,'HOAN_THANH','2025-09-07 03:02:05'),(51,21,7,3,'HOAN_THANH','2025-09-07 03:02:05'),(52,21,8,4,'HOAN_THANH','2025-09-07 03:02:05'),(53,21,9,4,'HOAN_THANH','2025-09-07 03:02:05'),(54,21,10,4,'HOAN_THANH','2025-09-07 03:02:05'),(55,21,11,5,'TRUOT','2025-09-07 03:02:05'),(56,21,12,5,'HOAN_THANH','2025-09-07 03:02:05'),(57,21,13,5,'HOAN_THANH','2025-09-07 03:02:05'),(58,21,14,6,'TRUOT','2025-09-07 03:02:05'),(59,21,15,6,'HOAN_THANH','2025-09-07 03:02:05'),(60,21,16,7,'HOAN_THANH','2025-09-07 03:02:05'),(61,21,17,7,'TRUOT','2025-09-07 03:02:05'),(62,21,18,7,'HOAN_THANH','2025-09-07 03:02:05'),(63,21,19,8,'HOAN_THANH','2025-09-07 03:02:05'),(64,21,20,8,'HOAN_THANH','2025-09-07 03:02:05'),(65,21,21,8,'HOAN_THANH','2025-09-07 03:02:05'),(66,21,22,9,'HOAN_THANH','2025-09-07 03:02:05'),(67,21,26,8,'HOAN_THANH','2025-09-07 03:02:05'),(68,22,24,7,'HOAN_THANH','2025-09-07 03:02:05'),(69,22,25,7,'HOAN_THANH','2025-09-07 03:02:05'),(70,22,26,8,'HOAN_THANH','2025-09-07 03:02:05'),(71,22,27,8,'TRUOT','2025-09-07 03:02:05'),(72,22,28,9,'HOAN_THANH','2025-09-07 03:02:05'),(73,22,29,9,'HOAN_THANH','2025-09-07 03:02:05'),(74,22,30,9,'HOAN_THANH','2025-09-07 03:02:05'),(76,23,31,7,'HOAN_THANH','2025-09-07 03:02:05'),(77,23,32,7,'HOAN_THANH','2025-09-07 03:02:05'),(78,23,33,8,'HOAN_THANH','2025-09-07 03:02:05'),(79,23,34,8,'HOAN_THANH','2025-09-07 03:02:05'),(80,23,35,9,'HOAN_THANH','2025-09-07 03:02:05'),(81,23,36,9,'HOAN_THANH','2025-09-07 03:02:05'),(82,23,37,9,'HOAN_THANH','2025-09-07 03:02:05');
/*!40000 ALTER TABLE `dang_ky` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diem`
--

DROP TABLE IF EXISTS `diem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sinh_vien_id` int NOT NULL,
  `giang_vien_id` int NOT NULL,
  `mon_hoc_id` int NOT NULL,
  `hoc_ky_id` int NOT NULL,
  `lan_hoc` int NOT NULL DEFAULT '1',
  `loai` varchar(255) NOT NULL,
  `diem` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sinh_vien_id` (`sinh_vien_id`,`mon_hoc_id`,`lan_hoc`,`loai`),
  KEY `giang_vien_id` (`giang_vien_id`),
  KEY `mon_hoc_id` (`mon_hoc_id`),
  KEY `hoc_ky_id` (`hoc_ky_id`),
  CONSTRAINT `diem_ibfk_1` FOREIGN KEY (`sinh_vien_id`) REFERENCES `sinh_vien` (`id`),
  CONSTRAINT `diem_ibfk_2` FOREIGN KEY (`giang_vien_id`) REFERENCES `giang_vien` (`id`),
  CONSTRAINT `diem_ibfk_3` FOREIGN KEY (`mon_hoc_id`) REFERENCES `mon_hoc` (`id`),
  CONSTRAINT `diem_ibfk_4` FOREIGN KEY (`hoc_ky_id`) REFERENCES `hoc_ky` (`id`),
  CONSTRAINT `diem_chk_1` CHECK ((`diem` between 0 and 10))
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diem`
--

LOCK TABLES `diem` WRITE;
/*!40000 ALTER TABLE `diem` DISABLE KEYS */;
INSERT INTO `diem` VALUES (1,19,14,1,1,1,'GIUA_KY',9.00),(2,19,14,1,1,1,'CUOI_KY',7.60),(3,19,14,1,1,1,'TONG_KET',8.20),(4,19,4,2,1,1,'GIUA_KY',6.00),(5,19,4,2,1,1,'CUOI_KY',8.00),(6,19,4,2,1,1,'TONG_KET',7.20),(7,19,6,3,2,1,'GIUA_KY',7.60),(8,19,6,3,2,1,'CUOI_KY',4.70),(9,19,6,3,2,1,'TONG_KET',4.70),(10,19,2,4,2,1,'GIUA_KY',4.20),(11,19,2,4,2,1,'CUOI_KY',6.50),(12,19,2,4,2,1,'TONG_KET',5.60),(13,19,15,5,3,1,'GIUA_KY',5.50),(14,19,15,5,3,1,'CUOI_KY',4.00),(15,19,15,5,3,1,'TONG_KET',4.80),(16,19,2,6,3,1,'GIUA_KY',7.20),(17,19,2,6,3,1,'CUOI_KY',7.00),(18,19,2,6,3,1,'TONG_KET',7.10),(19,19,7,7,3,1,'GIUA_KY',7.70),(20,19,7,7,3,1,'CUOI_KY',9.00),(21,19,7,7,3,1,'TONG_KET',8.50),(22,19,17,8,4,1,'GIUA_KY',9.60),(23,19,17,8,4,1,'CUOI_KY',9.50),(24,19,17,8,4,1,'TONG_KET',9.50),(25,19,3,9,4,1,'GIUA_KY',9.00),(26,19,3,9,4,1,'CUOI_KY',8.00),(27,19,3,9,4,1,'TONG_KET',8.40),(28,19,15,10,4,1,'GIUA_KY',10.00),(29,19,15,10,4,1,'CUOI_KY',4.30),(30,19,15,10,4,1,'TONG_KET',6.60),(31,19,10,11,5,1,'GIUA_KY',5.50),(32,19,10,11,5,1,'CUOI_KY',8.00),(33,19,10,11,5,1,'TONG_KET',7.00),(34,19,4,12,5,1,'GIUA_KY',8.70),(35,19,4,12,5,1,'CUOI_KY',8.00),(36,19,4,12,5,1,'TONG_KET',8.30),(37,19,9,13,5,1,'GIUA_KY',7.50),(38,19,9,13,5,1,'CUOI_KY',9.00),(39,19,9,13,5,1,'TONG_KET',8.30),(40,19,10,14,6,1,'GIUA_KY',5.50),(41,19,10,14,6,1,'CUOI_KY',8.00),(42,19,10,14,6,1,'TONG_KET',7.00),(43,19,12,15,6,1,'GIUA_KY',7.90),(44,19,12,15,6,1,'CUOI_KY',8.00),(45,19,12,15,6,1,'TONG_KET',8.00),(46,19,11,16,7,1,'GIUA_KY',10.00),(47,19,11,16,7,1,'CUOI_KY',9.80),(48,19,11,16,7,1,'TONG_KET',9.90),(49,19,10,17,7,1,'GIUA_KY',7.50),(50,19,10,17,7,1,'CUOI_KY',6.50),(51,19,10,17,7,1,'TONG_KET',6.90),(52,19,10,18,7,1,'GIUA_KY',6.50),(53,19,10,18,7,1,'CUOI_KY',7.00),(54,19,10,18,7,1,'TONG_KET',6.80),(55,19,12,19,8,1,'GIUA_KY',10.00),(56,19,12,19,8,1,'CUOI_KY',4.40),(57,19,12,19,8,1,'TONG_KET',7.20),(58,19,13,20,8,1,'GIUA_KY',7.00),(59,19,13,20,8,1,'CUOI_KY',6.00),(60,19,13,20,8,1,'TONG_KET',6.40),(61,19,10,21,8,1,'GIUA_KY',6.50),(62,19,10,21,8,1,'CUOI_KY',7.00),(63,19,10,21,8,1,'TONG_KET',6.80),(64,19,12,32,9,1,'GIUA_KY',7.00),(65,19,12,32,9,1,'CUOI_KY',7.00),(66,19,12,32,9,1,'TONG_KET',7.00),(67,20,14,1,1,1,'GIUA_KY',10.00),(68,20,14,1,1,1,'CUOI_KY',7.40),(69,20,14,1,1,1,'TONG_KET',8.40),(70,20,4,2,1,1,'GIUA_KY',10.00),(71,20,4,2,1,1,'CUOI_KY',10.00),(72,20,4,2,1,1,'TONG_KET',10.00),(73,20,6,3,2,1,'GIUA_KY',7.60),(74,20,6,3,2,1,'CUOI_KY',6.80),(75,20,6,3,2,1,'TONG_KET',7.00),(76,20,2,4,2,1,'GIUA_KY',5.10),(77,20,2,4,2,1,'CUOI_KY',7.00),(78,20,2,4,2,1,'TONG_KET',6.20),(79,20,15,5,3,1,'GIUA_KY',7.60),(80,20,15,5,3,1,'CUOI_KY',4.00),(81,20,15,5,3,1,'TONG_KET',5.80),(82,20,2,6,3,1,'GIUA_KY',8.30),(83,20,2,6,3,1,'CUOI_KY',6.50),(84,20,2,6,3,1,'TONG_KET',7.20),(85,20,7,7,3,1,'GIUA_KY',8.30),(86,20,7,7,3,1,'CUOI_KY',9.00),(87,20,7,7,3,1,'TONG_KET',8.70),(88,20,17,8,4,1,'GIUA_KY',9.60),(89,20,17,8,4,1,'CUOI_KY',10.00),(90,20,17,8,4,1,'TONG_KET',9.80),(91,20,3,9,4,1,'GIUA_KY',8.40),(92,20,3,9,4,1,'CUOI_KY',9.00),(93,20,3,9,4,1,'TONG_KET',8.80),(94,20,15,10,4,1,'GIUA_KY',5.00),(95,20,15,10,4,1,'CUOI_KY',5.00),(96,20,15,10,4,1,'TONG_KET',5.00),(97,20,10,11,5,1,'GIUA_KY',6.00),(98,20,10,11,5,1,'CUOI_KY',8.00),(99,20,10,11,5,1,'TONG_KET',7.20),(100,20,4,12,5,1,'GIUA_KY',8.70),(101,20,4,12,5,1,'CUOI_KY',8.20),(102,20,4,12,5,1,'TONG_KET',8.40),(103,20,9,13,5,1,'GIUA_KY',7.50),(104,20,9,13,5,1,'CUOI_KY',7.50),(105,20,9,13,5,1,'TONG_KET',7.50),(106,20,10,14,6,1,'GIUA_KY',6.00),(107,20,10,14,6,1,'CUOI_KY',8.00),(108,20,10,14,6,1,'TONG_KET',7.20),(109,20,12,15,6,1,'GIUA_KY',10.00),(110,20,12,15,6,1,'CUOI_KY',6.40),(111,20,12,15,6,1,'TONG_KET',8.20),(112,20,11,16,7,1,'GIUA_KY',9.00),(113,20,11,16,7,1,'CUOI_KY',8.80),(114,20,11,16,7,1,'TONG_KET',8.90),(115,20,10,17,7,1,'GIUA_KY',6.50),(116,20,10,17,7,1,'CUOI_KY',6.00),(117,20,10,17,7,1,'TONG_KET',6.20),(118,20,10,18,7,1,'GIUA_KY',7.00),(119,20,10,18,7,1,'CUOI_KY',7.00),(120,20,10,18,7,1,'TONG_KET',7.00),(121,20,12,19,8,1,'GIUA_KY',10.00),(122,20,12,19,8,1,'CUOI_KY',6.40),(123,20,12,19,8,1,'TONG_KET',8.20),(124,20,13,20,8,1,'GIUA_KY',7.00),(125,20,13,20,8,1,'CUOI_KY',5.50),(126,20,13,20,8,1,'TONG_KET',6.10),(127,20,10,21,8,1,'GIUA_KY',5.50),(128,20,10,21,8,1,'CUOI_KY',7.00),(129,20,10,21,8,1,'TONG_KET',6.40),(130,20,18,32,9,1,'GIUA_KY',8.00),(131,20,18,32,9,1,'CUOI_KY',8.00),(132,20,18,32,9,1,'TONG_KET',8.00),(133,21,14,1,1,1,'GIUA_KY',8.00),(134,21,14,1,1,1,'CUOI_KY',3.90),(135,21,14,1,1,1,'TONG_KET',5.50),(136,21,4,2,1,1,'GIUA_KY',5.00),(137,21,4,2,1,1,'CUOI_KY',2.00),(138,21,4,2,1,1,'TONG_KET',3.20),(139,21,6,3,2,1,'GIUA_KY',6.00),(140,21,6,3,2,1,'CUOI_KY',0.50),(141,21,6,3,2,1,'TONG_KET',2.70),(142,21,2,4,2,1,'GIUA_KY',4.50),(143,21,2,4,2,1,'CUOI_KY',6.00),(144,21,2,4,2,1,'TONG_KET',5.40),(145,21,15,5,3,1,'GIUA_KY',5.10),(146,21,15,5,3,1,'CUOI_KY',3.30),(147,21,15,5,3,1,'TONG_KET',4.20),(148,21,2,6,3,1,'GIUA_KY',6.50),(149,21,2,6,3,1,'CUOI_KY',4.80),(150,21,2,6,3,1,'TONG_KET',5.50),(151,21,7,7,3,1,'GIUA_KY',8.20),(152,21,7,7,3,1,'CUOI_KY',6.00),(153,21,7,7,3,1,'TONG_KET',6.90),(154,21,17,8,4,1,'GIUA_KY',9.60),(155,21,17,8,4,1,'CUOI_KY',8.00),(156,21,17,8,4,1,'TONG_KET',8.60),(157,21,3,9,4,1,'GIUA_KY',6.00),(158,21,3,9,4,1,'CUOI_KY',4.10),(159,21,3,9,4,1,'TONG_KET',4.90),(160,21,15,10,4,1,'GIUA_KY',9.00),(161,21,15,10,4,1,'CUOI_KY',2.00),(162,21,15,10,4,1,'TONG_KET',4.80),(163,21,10,11,5,1,'GIUA_KY',2.50),(164,21,10,11,5,1,'CUOI_KY',1.50),(165,21,10,11,5,1,'TONG_KET',1.90),(166,21,4,12,5,1,'GIUA_KY',8.50),(167,21,4,12,5,1,'CUOI_KY',8.00),(168,21,4,12,5,1,'TONG_KET',8.20),(169,21,9,13,5,1,'GIUA_KY',7.50),(170,21,9,13,5,1,'CUOI_KY',3.00),(171,21,9,13,5,1,'TONG_KET',5.30),(172,21,10,14,6,1,'GIUA_KY',2.50),(173,21,10,14,6,1,'CUOI_KY',1.50),(174,21,10,14,6,1,'TONG_KET',1.90),(175,21,12,15,6,1,'GIUA_KY',10.00),(176,21,12,15,6,1,'CUOI_KY',4.40),(177,21,12,15,6,1,'TONG_KET',7.20),(178,21,11,16,7,1,'GIUA_KY',8.50),(179,21,11,16,7,1,'CUOI_KY',8.80),(180,21,11,16,7,1,'TONG_KET',8.70),(181,21,10,17,7,1,'GIUA_KY',6.50),(182,21,10,17,7,1,'CUOI_KY',2.00),(183,21,10,17,7,1,'TONG_KET',3.80),(184,21,10,18,7,1,'GIUA_KY',3.00),(185,21,10,18,7,1,'CUOI_KY',5.00),(186,21,10,18,7,1,'TONG_KET',4.20),(187,21,12,19,8,1,'GIUA_KY',8.50),(188,21,12,19,8,1,'CUOI_KY',4.80),(189,21,12,19,8,1,'TONG_KET',6.30),(190,21,13,20,8,1,'GIUA_KY',6.00),(191,21,13,20,8,1,'CUOI_KY',4.80),(192,21,13,20,8,1,'TONG_KET',5.30),(193,21,10,21,8,1,'GIUA_KY',3.00),(194,21,10,21,8,1,'CUOI_KY',5.50),(195,21,10,21,8,1,'TONG_KET',4.50),(196,21,11,32,9,1,'GIUA_KY',7.00),(197,21,11,32,9,1,'CUOI_KY',7.00),(198,21,11,32,9,1,'TONG_KET',7.00),(199,22,13,1,7,1,'GIUA_KY',6.00),(200,22,13,1,7,1,'CUOI_KY',9.00),(201,22,13,1,7,1,'TONG_KET',7.80),(202,22,18,2,7,1,'GIUA_KY',9.00),(203,22,18,2,7,1,'CUOI_KY',9.50),(204,22,18,2,7,1,'TONG_KET',9.30),(205,22,6,3,8,1,'GIUA_KY',9.90),(206,22,6,3,8,1,'CUOI_KY',7.60),(207,22,6,3,8,1,'TONG_KET',8.30),(208,22,4,4,8,1,'GIUA_KY',3.00),(209,22,4,4,8,1,'CUOI_KY',2.00),(210,22,4,4,8,1,'TONG_KET',2.40),(211,22,15,10,9,1,'GIUA_KY',10.00),(212,22,15,10,9,1,'CUOI_KY',3.30),(213,22,15,10,9,1,'TONG_KET',6.00),(214,22,15,22,9,1,'GIUA_KY',6.50),(215,22,15,22,9,1,'CUOI_KY',4.50),(216,22,15,22,9,1,'TONG_KET',5.30),(217,22,4,23,9,1,'GIUA_KY',9.00),(218,22,4,23,9,1,'CUOI_KY',5.50),(219,22,4,23,9,1,'TONG_KET',6.90),(220,23,13,1,7,1,'GIUA_KY',6.00),(221,23,13,1,7,1,'CUOI_KY',9.00),(222,23,13,1,7,1,'TONG_KET',7.80),(223,23,18,2,7,1,'GIUA_KY',9.00),(224,23,18,2,7,1,'CUOI_KY',9.50),(225,23,18,2,7,1,'TONG_KET',9.30),(226,23,6,3,8,1,'GIUA_KY',9.90),(227,23,6,3,8,1,'CUOI_KY',7.60),(228,23,6,3,8,1,'TONG_KET',8.30),(229,23,4,4,8,1,'GIUA_KY',8.00),(230,23,4,4,8,1,'CUOI_KY',6.00),(231,23,4,4,8,1,'TONG_KET',6.80),(232,23,15,10,9,1,'GIUA_KY',10.00),(233,23,15,10,9,1,'CUOI_KY',3.30),(234,23,15,10,9,1,'TONG_KET',6.00),(235,23,15,22,9,1,'GIUA_KY',6.50),(236,23,15,22,9,1,'CUOI_KY',4.50),(237,23,15,22,9,1,'TONG_KET',5.30),(238,23,4,23,9,1,'GIUA_KY',9.00),(239,23,4,23,9,1,'CUOI_KY',5.50),(240,23,4,23,9,1,'TONG_KET',6.90),(241,21,6,3,8,2,'GIUA_KY',3.00),(242,21,6,3,8,2,'CUOI_KY',5.80),(243,21,6,3,8,2,'TONG_KET',4.70);
/*!40000 ALTER TABLE `diem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giang_vien`
--

DROP TABLE IF EXISTS `giang_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giang_vien` (
  `id` int NOT NULL,
  `hoc_vi` varchar(255) DEFAULT NULL,
  `khoa_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `khoa_id` (`khoa_id`),
  CONSTRAINT `giang_vien_ibfk_1` FOREIGN KEY (`id`) REFERENCES `nguoi_dung` (`id`) ON DELETE CASCADE,
  CONSTRAINT `giang_vien_ibfk_2` FOREIGN KEY (`khoa_id`) REFERENCES `khoa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giang_vien`
--

LOCK TABLES `giang_vien` WRITE;
/*!40000 ALTER TABLE `giang_vien` DISABLE KEYS */;
INSERT INTO `giang_vien` VALUES (1,'Thạc sĩ',1),(2,'Tiến sĩ',1),(3,'Thạc sĩ',1),(4,'Tiến sĩ',1),(5,'Tiến sĩ',1),(6,'Thạc sĩ',1),(7,'Thạc sĩ',1),(8,'Thạc sĩ',1),(9,'Thạc sĩ',1),(10,'Thạc sĩ',1),(11,'Tiến sĩ',1),(12,'Tiến sĩ',1),(13,'Thạc sĩ',1),(14,'Tiến sĩ',1),(15,'Thạc sĩ',1),(16,'Thạc sĩ',1),(17,'Thạc sĩ',1),(18,'Thạc sĩ',1);
/*!40000 ALTER TABLE `giang_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoc_ky`
--

DROP TABLE IF EXISTS `hoc_ky`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoc_ky` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ky` varchar(255) NOT NULL,
  `nam_hoc` varchar(255) NOT NULL,
  `ngay_bat_dau` date NOT NULL,
  `ngay_ket_thuc` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ky` (`ky`,`nam_hoc`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoc_ky`
--

LOCK TABLES `hoc_ky` WRITE;
/*!40000 ALTER TABLE `hoc_ky` DISABLE KEYS */;
INSERT INTO `hoc_ky` VALUES (1,'Học kỳ 1','2022-2023','2022-10-03','2023-01-15'),(2,'Học kỳ 2','2022-2023','2023-02-06','2023-05-21'),(3,'Học kỳ 3','2022-2023','2023-06-05','2023-09-17'),(4,'Học kỳ 1','2023-2024','2023-10-16','2024-01-28'),(5,'Học kỳ 2','2023-2024','2024-02-26','2024-06-09'),(6,'Học kỳ 3','2023-2024','2024-06-17','2024-09-29'),(7,'Học kỳ 1','2024-2025','2024-10-07','2025-01-19'),(8,'Học kỳ 2','2024-2025','2025-02-10','2025-05-25'),(9,'Học kỳ 3','2024-2025','2025-06-09','2025-09-21'),(10,'Học kỳ 1','2025-2026','2025-10-06','2026-01-18');
/*!40000 ALTER TABLE `hoc_ky` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoc_phi`
--

DROP TABLE IF EXISTS `hoc_phi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoc_phi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sinh_vien_id` int NOT NULL,
  `hoc_ky_id` int NOT NULL,
  `tong_tien` decimal(38,2) DEFAULT NULL,
  `trang_thai` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sinh_vien_id` (`sinh_vien_id`,`hoc_ky_id`),
  KEY `hoc_ky_id` (`hoc_ky_id`),
  CONSTRAINT `hoc_phi_ibfk_1` FOREIGN KEY (`sinh_vien_id`) REFERENCES `sinh_vien` (`id`),
  CONSTRAINT `hoc_phi_ibfk_2` FOREIGN KEY (`hoc_ky_id`) REFERENCES `hoc_ky` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoc_phi`
--

LOCK TABLES `hoc_phi` WRITE;
/*!40000 ALTER TABLE `hoc_phi` DISABLE KEYS */;
INSERT INTO `hoc_phi` VALUES (1,19,1,4900000.00,'DA_THANH_TOAN'),(2,19,2,4900000.00,'DA_THANH_TOAN'),(3,19,3,7700000.00,'DA_THANH_TOAN'),(4,19,4,7700000.00,'DA_THANH_TOAN'),(5,19,5,7700000.00,'DA_THANH_TOAN'),(6,19,6,4200000.00,'DA_THANH_TOAN'),(7,19,7,6300000.00,'DA_THANH_TOAN'),(8,19,8,6300000.00,'DA_THANH_TOAN'),(9,19,9,2800000.00,'DA_THANH_TOAN'),(10,20,1,4900000.00,'DA_THANH_TOAN'),(11,20,2,4900000.00,'DA_THANH_TOAN'),(12,20,3,7700000.00,'DA_THANH_TOAN'),(13,20,4,7700000.00,'DA_THANH_TOAN'),(14,20,5,7700000.00,'DA_THANH_TOAN'),(15,20,6,4200000.00,'DA_THANH_TOAN'),(16,20,7,6300000.00,'DA_THANH_TOAN'),(17,20,8,6300000.00,'DA_THANH_TOAN'),(18,20,9,2800000.00,'DA_THANH_TOAN'),(19,21,1,4900000.00,'DA_THANH_TOAN'),(20,21,2,4900000.00,'DA_THANH_TOAN'),(21,21,3,7700000.00,'DA_THANH_TOAN'),(22,21,4,7700000.00,'DA_THANH_TOAN'),(23,21,5,7700000.00,'DA_THANH_TOAN'),(24,21,6,4200000.00,'DA_THANH_TOAN'),(25,21,7,6300000.00,'DA_THANH_TOAN'),(26,21,8,8400000.00,'DA_THANH_TOAN'),(27,21,9,2800000.00,'DA_THANH_TOAN'),(28,22,7,4900000.00,'DA_THANH_TOAN'),(29,22,8,4900000.00,'DA_THANH_TOAN'),(30,22,9,7700000.00,'DA_THANH_TOAN'),(32,23,7,4900000.00,'DA_THANH_TOAN'),(33,23,8,4900000.00,'DA_THANH_TOAN'),(34,23,9,7700000.00,'DA_THANH_TOAN');
/*!40000 ALTER TABLE `hoc_phi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khoa`
--

DROP TABLE IF EXISTS `khoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khoa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_khoa` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ten_khoa` (`ten_khoa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khoa`
--

LOCK TABLES `khoa` WRITE;
/*!40000 ALTER TABLE `khoa` DISABLE KEYS */;
INSERT INTO `khoa` VALUES (1,'Công nghệ thông tin');
/*!40000 ALTER TABLE `khoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lich_hoc`
--

DROP TABLE IF EXISTS `lich_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lich_hoc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `buoi_hoc_id` int NOT NULL,
  `thu` varchar(255) DEFAULT NULL,
  `tiet_hoc_id` int NOT NULL,
  `ngay_bat_dau` date NOT NULL,
  `ngay_ket_thuc` date NOT NULL,
  `phong_hoc_id` int NOT NULL,
  `loai` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `thu` (`thu`,`tiet_hoc_id`,`ngay_bat_dau`,`phong_hoc_id`),
  KEY `buoi_hoc_id` (`buoi_hoc_id`),
  KEY `tiet_hoc_id` (`tiet_hoc_id`),
  KEY `phong_hoc_id` (`phong_hoc_id`),
  CONSTRAINT `lich_hoc_ibfk_1` FOREIGN KEY (`buoi_hoc_id`) REFERENCES `buoi_hoc` (`id`),
  CONSTRAINT `lich_hoc_ibfk_2` FOREIGN KEY (`tiet_hoc_id`) REFERENCES `tiet_hoc` (`id`),
  CONSTRAINT `lich_hoc_ibfk_3` FOREIGN KEY (`phong_hoc_id`) REFERENCES `phong_hoc` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lich_hoc`
--

LOCK TABLES `lich_hoc` WRITE;
/*!40000 ALTER TABLE `lich_hoc` DISABLE KEYS */;
INSERT INTO `lich_hoc` VALUES (1,1,'Thứ 2',1,'2022-10-10','2022-11-14',8,'LyThuyet'),(2,1,'Thứ 2',2,'2022-10-10','2022-11-07',15,'ThucHanh'),(3,2,'Thứ 4',1,'2022-10-12','2022-12-07',5,'LyThuyet'),(4,2,'Thứ 4',2,'2022-10-12','2022-11-09',17,'ThucHanh'),(5,3,'Thứ 2',1,'2023-02-06','2023-04-03',4,'LyThuyet'),(6,4,'Thứ 5',1,'2023-02-09','2023-04-06',6,'LyThuyet'),(7,4,'Thứ 5',2,'2023-02-09','2023-03-09',13,'ThucHanh'),(8,5,'Thứ 4',1,'2023-06-07','2023-07-12',7,'LyThuyet'),(9,5,'Thứ 4',2,'2023-06-07','2023-07-05',15,'ThucHanh'),(10,6,'Thứ 2',1,'2023-06-05','2023-07-31',5,'LyThuyet'),(11,6,'Thứ 2',2,'2023-06-05','2023-07-03',17,'ThucHanh'),(12,7,'Thứ 7',1,'2023-06-10','2023-08-05',1,'LyThuyet'),(13,7,'Thứ 7',2,'2023-06-10','2023-07-08',16,'ThucHanh'),(14,8,'Thứ 4',1,'2023-10-04','2023-12-20',8,'LyThuyet'),(15,9,'Thứ 3',1,'2023-10-03','2023-11-07',9,'LyThuyet'),(16,9,'Thứ 3',2,'2023-10-03','2023-10-31',21,'ThucHanh'),(17,10,'Thứ 6',1,'2023-10-06','2023-12-01',10,'LyThuyet'),(18,10,'Thứ 6',2,'2023-10-06','2023-11-03',17,'ThucHanh'),(19,11,'Thứ 6',1,'2024-02-09','2024-04-05',11,'LyThuyet'),(20,11,'Thứ 6',2,'2024-02-09','2024-03-08',18,'ThucHanh'),(21,12,'Thứ 3',1,'2024-02-06','2024-04-02',3,'LyThuyet'),(22,13,'Thứ 4',1,'2024-02-07','2024-04-24',12,'LyThuyet'),(23,14,'Thứ 5',1,'2024-06-13','2024-07-18',5,'LyThuyet'),(24,14,'Thứ 5',2,'2024-06-13','2024-07-11',17,'ThucHanh'),(25,15,'Thứ 3',1,'2024-06-11','2024-07-16',6,'LyThuyet'),(26,15,'Thứ 3',2,'2024-06-11','2024-07-09',20,'ThucHanh'),(27,16,'Thứ 2',1,'2024-10-07','2024-11-11',5,'LyThuyet'),(28,16,'Thứ 2',2,'2024-10-07','2024-11-04',19,'ThucHanh'),(29,17,'Thứ 7',1,'2024-10-12','2024-11-16',3,'LyThuyet'),(30,17,'Thứ 7',2,'2024-10-12','2024-11-09',18,'ThucHanh'),(31,18,'Chủ nhật',1,'2024-10-13','2024-11-17',7,'LyThuyet'),(32,18,'Chủ nhật',2,'2024-10-13','2024-11-10',17,'ThucHanh'),(33,19,'Thứ 3',1,'2025-02-25','2025-04-01',4,'LyThuyet'),(34,19,'Thứ 3',2,'2025-02-25','2025-03-25',14,'ThucHanh'),(35,20,'Thứ 4',1,'2025-02-26','2025-04-02',8,'LyThuyet'),(36,20,'Thứ 4',2,'2025-02-26','2025-03-26',17,'ThucHanh'),(37,21,'Thứ 6',1,'2025-02-28','2025-04-04',5,'LyThuyet'),(38,21,'Thứ 6',2,'2025-02-28','2025-03-28',17,'ThucHanh'),(39,24,'Thứ 3',1,'2024-10-08','2024-11-12',1,'LyThuyet'),(40,24,'Thứ 3',2,'2024-10-08','2024-11-05',15,'ThucHanh'),(41,25,'Thứ 5',1,'2024-10-10','2024-12-05',2,'LyThuyet'),(42,25,'Thứ 5',2,'2024-10-10','2024-11-07',17,'ThucHanh'),(43,26,'Thứ 7',1,'2025-02-15','2025-04-12',9,'LyThuyet'),(44,27,'Thứ 2',1,'2025-02-10','2025-04-07',10,'LyThuyet'),(45,27,'Thứ 2',2,'2025-02-10','2025-03-10',21,'ThucHanh'),(46,28,'Thứ 2',1,'2025-06-09','2025-08-04',12,'LyThuyet'),(47,28,'Thứ 2',2,'2025-06-09','2025-07-07',20,'ThucHanh'),(48,29,'Thứ 3',1,'2025-06-10','2025-07-15',8,'LyThuyet'),(49,29,'Thứ 3',2,'2025-06-10','2025-07-08',19,'ThucHanh'),(50,30,'Thứ 6',1,'2025-06-13','2025-08-08',7,'LyThuyet'),(51,30,'Thứ 6',2,'2025-06-13','2025-07-11',20,'ThucHanh'),(52,31,'Thứ 4',1,'2024-10-09','2024-11-13',1,'LyThuyet'),(53,31,'Thứ 4',2,'2024-10-09','2024-11-06',15,'ThucHanh'),(54,32,'Thứ 6',1,'2024-10-11','2024-12-06',2,'LyThuyet'),(55,32,'Thứ 6',2,'2024-10-11','2024-11-08',17,'ThucHanh'),(56,33,'Thứ 7',1,'2025-02-15','2025-04-12',10,'LyThuyet'),(57,34,'Thứ 3',1,'2025-02-11','2025-04-08',10,'LyThuyet'),(58,34,'Thứ 3',2,'2025-02-11','2025-03-11',21,'ThucHanh'),(59,35,'Thứ 3',1,'2025-06-10','2025-08-05',12,'LyThuyet'),(60,35,'Thứ 3',2,'2025-06-10','2025-07-08',20,'ThucHanh'),(61,36,'Thứ 4',1,'2025-06-11','2025-07-16',8,'LyThuyet'),(62,36,'Thứ 4',2,'2025-06-11','2025-07-09',19,'ThucHanh'),(63,37,'Thứ 7',1,'2025-06-14','2025-08-09',7,'LyThuyet'),(64,37,'Thứ 7',2,'2025-06-14','2025-07-12',20,'ThucHanh'),(65,38,'Thứ 2',1,'2025-10-06','2025-12-22',6,'LyThuyet'),(66,39,'Thứ 6',1,'2025-10-10','2025-12-26',5,'LyThuyet'),(67,40,'Thứ 4',1,'2025-10-08','2025-12-03',8,'LyThuyet'),(68,40,'Thứ 4',2,'2025-10-08','2025-11-05',20,'ThucHanh'),(69,41,'Thứ 3',1,'2025-10-07','2025-12-02',7,'LyThuyet'),(70,41,'Thứ 3',2,'2025-10-07','2025-11-04',20,'ThucHanh'),(71,42,'Thứ 6',1,'2025-10-10','2025-11-14',2,'LyThuyet'),(72,42,'Thứ 6',2,'2025-10-10','2025-11-07',14,'ThucHanh'),(73,43,'Thứ 7',1,'2025-10-11','2025-11-15',10,'LyThuyet'),(74,43,'Thứ 7',2,'2025-10-11','2025-11-08',17,'ThucHanh'),(75,44,'Thứ 3',1,'2025-10-07','2025-12-23',6,'LyThuyet'),(76,45,'Thứ 5',1,'2025-10-09','2025-12-04',8,'LyThuyet'),(77,45,'Thứ 5',2,'2025-10-09','2025-11-06',20,'ThucHanh'),(78,46,'Thứ 2',1,'2025-10-06','2025-11-10',9,'LyThuyet'),(79,46,'Thứ 2',2,'2025-10-06','2025-11-03',19,'ThucHanh'),(80,47,'Thứ 4',1,'2025-10-08','2025-12-03',1,'LyThuyet'),(81,47,'Thứ 4',2,'2025-10-08','2025-11-05',18,'ThucHanh'),(82,48,'Thứ 5',1,'2025-10-09','2025-11-13',3,'LyThuyet'),(83,48,'Thứ 5',2,'2025-10-09','2025-11-06',16,'ThucHanh'),(84,49,'Thứ 3',1,'2025-10-07','2025-12-02',2,'LyThuyet'),(85,49,'Thứ 3',2,'2025-10-07','2025-11-04',18,'ThucHanh'),(86,50,'Thứ 3',1,'2025-10-07','2025-11-11',4,'LyThuyet'),(87,50,'Thứ 3',2,'2025-10-07','2025-11-04',21,'ThucHanh'),(88,51,'Thứ 7',1,'2025-10-11','2025-12-06',3,'LyThuyet'),(89,51,'Thứ 7',2,'2025-10-11','2025-11-08',13,'ThucHanh');
/*!40000 ALTER TABLE `lich_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lop`
--

DROP TABLE IF EXISTS `lop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lop` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_lop` varchar(255) NOT NULL,
  `si_so` int NOT NULL,
  `nganh_id` int NOT NULL,
  `khoa_hoc` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ma_lop` (`ma_lop`,`nganh_id`,`khoa_hoc`),
  KEY `nganh_id` (`nganh_id`),
  CONSTRAINT `lop_ibfk_1` FOREIGN KEY (`nganh_id`) REFERENCES `nganh` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lop`
--

LOCK TABLES `lop` WRITE;
/*!40000 ALTER TABLE `lop` DISABLE KEYS */;
INSERT INTO `lop` VALUES (1,'DH22CS01',10,1,2022),(2,'DH24IT01',10,3,2024),(3,'DH24CS01',10,1,2024),(4,'DH24IM01',10,2,2024),(5,'DH24IT02',10,3,2024),(6,'DH25CS01',10,1,2025),(7,'DH25IT01',10,3,2025),(8,'DH25IM01',10,2,2025);
/*!40000 ALTER TABLE `lop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mon_hoc`
--

DROP TABLE IF EXISTS `mon_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mon_hoc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_mon` varchar(255) NOT NULL,
  `tin_chi_ly_thuyet` int NOT NULL,
  `tin_chi_thuc_hanh` int NOT NULL,
  `phan_tram_giua_ky` int NOT NULL DEFAULT '30',
  `phan_tram_cuoi_ky` int NOT NULL DEFAULT '70',
  `diem_qua_mon` decimal(38,2) NOT NULL,
  `de_cuong` varchar(255) DEFAULT NULL,
  `khoa_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `khoa_id` (`khoa_id`),
  CONSTRAINT `mon_hoc_ibfk_1` FOREIGN KEY (`khoa_id`) REFERENCES `khoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mon_hoc`
--

LOCK TABLES `mon_hoc` WRITE;
/*!40000 ALTER TABLE `mon_hoc` DISABLE KEYS */;
INSERT INTO `mon_hoc` VALUES (1,'Nhập môn tin học',2,1,40,60,4.00,'https://drive.google.com/file/d/198W-7oDbPwOyywOBJxV43h2r6x-t1FPX/view',1),(2,'Cơ sở lập trình',3,1,40,60,4.00,'https://drive.google.com/file/d/1u16wa2QtA9t3uNE4sNmLwzc4xcC4AaPt/view',1),(3,'Hệ điều hành và Kiến trúc máy tính',3,0,30,70,4.00,'https://drive.google.com/file/d/1Gu7Fu1TBvQCFOx4_jV7-Hw7vfyQmUX0_/view',1),(4,'Kỹ thuật lập trình',3,1,40,60,4.00,'https://drive.google.com/file/d/10HvLCq1H_wIVUEeAVjlX8dBJVDeh5OR7/view',1),(5,'Ứng dụng web',2,1,30,70,4.00,'https://drive.google.com/file/d/1u3wyjwELaaKjTZmEBq14ouZ3BowbHWTf/view',1),(6,'Cấu trúc dữ liệu và thuật giải 1',3,1,40,60,4.00,'https://drive.google.com/file/d/1MZNVPUn9AT_tDAmkZavLflIFiqurj-K5/view',1),(7,'Cơ sở dữ liệu',3,1,40,60,4.00,'https://drive.google.com/file/d/1ugGNJM5ortwEVVTnZKlLStxQQdzehLJT/view',1),(8,'Toán rời rạc',4,0,40,60,4.00,'https://drive.google.com/file/d/1kh6LUdul3RO7tCxyTUrqFjBfvAA6nGb2/view',1),(9,'Cấu trúc dữ liệu và thuật giải 2',2,1,40,60,4.00,'https://drive.google.com/file/d/1ELcy6tS4nGNauM4KK3IgIH-vm2yYiL2h/view',1),(10,'Mạng máy tính',3,1,40,60,4.00,'https://drive.google.com/file/d/1HiBtOs0_28rhAy19hs3dIqOk1TLpQJKW/view',1),(11,'Lập trình hướng đối tượng',3,1,40,60,4.00,'https://drive.google.com/file/d/1kM7_LaeLQOmm6fnhcy6w7GTbcTwKweZE/view',1),(12,'Kỹ năng nghề nghiệp',3,0,40,60,4.00,'https://drive.google.com/file/d/18oELjie0gEm2NVbOFCSzHZq410Sh6QfG/view',1),(13,'Phân tích thiết kế hệ thống',4,0,50,50,4.00,'https://drive.google.com/file/d/12O19tePNmdDFrmJw8fBvQJiWJ9jVfwz6/view',1),(14,'Mẫu thiết kế hướng đối tượng',2,1,40,60,4.00,'https://drive.google.com/file/d/1BWqRmZyZLQpPJFe3agrXnOhJME_7yWNk/view',1),(15,'Trí tuệ nhân tạo',2,1,50,50,4.00,'https://drive.google.com/file/d/119fJuQz0efJ8e420KqfELV4KXnZTrqzH/view',1),(16,'Quản trị hệ cơ sở dữ liệu',2,1,40,60,4.00,'https://drive.google.com/file/d/1U_4mOWDXdZiGO0hJB_PDD-XCjZSd2vhC/view',1),(17,'Công nghệ phần mềm',2,1,40,60,4.00,'https://drive.google.com/file/d/10r_eakrX0Ua1iPEA5v6gTbN2Tb6Fkxf9/view',1),(18,'Các công nghệ lập trình hiện đại',2,1,40,60,4.00,'https://drive.google.com/file/d/1jISbit7fuegHcsYYUc8kP8N3sdbFCfs0/view',1),(19,'Máy học',2,1,40,60,4.00,'https://drive.google.com/file/d/1nLQ5gDal-P1qQYkXp4QDlUrzWzbDhRlf/view',1),(20,'Kiểm thử phần mềm',2,1,40,60,4.00,'https://drive.google.com/file/d/1W0Q0vSBdb6DYAbnPMpq6l-bMNi-h6PwO/view',1),(21,'Phát triển hệ thống web',2,1,30,70,4.00,'https://drive.google.com/file/d/1wd9pYdZdvO9fXiI3Eis0iu5JpCrRmmbA/view',1),(22,'Thiết kế web',2,1,40,60,4.00,'https://drive.google.com/file/d/1CdjdfVoqMAQtjxYQOLgZ38XAV6rGaE31/view',1),(23,'Cấu trúc dữ liệu và thuật giải',3,1,40,60,4.00,'https://drive.google.com/file/d/1a06bnqxH7B1WbghJlPwv4ByN50kWXXvY/view',1),(24,'Công nghệ mã nguồn mở',2,1,50,50,4.00,'https://drive.google.com/file/d/1Ae2rmjuu26eGNwx7bsh4-c2pMwcGFd7R/view',1),(25,'Lập trình giao diện',2,1,50,50,4.00,'https://drive.google.com/file/d/11LFZ90i4ZnZkRAnjPb7JsOhYuouyRNW8/view',1),(26,'Quản trị mạng',2,1,40,60,4.00,'https://drive.google.com/file/d/1P83GF327WR8xe0kBuF4fMPfrjmlJQ4Qb/view',1),(27,'An toàn hệ thống thông tin',2,1,40,60,4.00,'https://drive.google.com/file/d/1EDeBU2FwN9Qut2wBsZc3Q8wL4_eb3eEg/view',1),(28,'Hệ thống quản lý nguồn lực doanh nghiệp',2,1,50,50,4.00,'https://drive.google.com/file/d/1vjV3GcC4eoW_VoN0CvKRbuSrDuHWlbd4/view',1),(29,'Hệ thống thông tin quản lý',3,0,50,50,4.00,'https://drive.google.com/file/d/1wfL9Kt7R1PSmO51L5pe9bgpkK_v5Msg-/view',1),(30,'Lập trình cơ sở dữ liệu',2,1,50,50,4.00,'https://drive.google.com/file/d/1ykLOwQRy1hJ1TQtYFdulmb-K_uIHDyJS/view',1),(31,'Phát triển hệ thống thông tin quản lý',3,0,50,50,4.00,'https://drive.google.com/file/d/14UbF2kI6-Gys-5FXEYNPxvwxy3s5VJ7V/view',1),(32,'Đồ án ngành',0,4,40,60,4.00,'https://drive.google.com/file/d/1l4o1d3awqQ7hM0KGjDrfmZKUqlYJ3BzK/view',1),(33,'Thực tập tốt nghiệp',0,4,30,70,4.00,'https://drive.google.com/file/d/1tc_cVh5YPdez6gVUxu_56ZJA9vwYCTh0/view',1),(34,'Khóa luận tốt nghiệp',0,6,0,100,4.00,'https://drive.google.com/file/d/1B0uVr9x9w3BAaE7I41rfSFUvzlk0l-DM/view',1),(35,'Đồ án ngành Hệ thống thông tin quản lý',0,4,40,60,4.00,'https://drive.google.com/file/d/1oOuMvC4fWjUIflbMrGnBB3LYpvCMPYsy/view',1),(36,'Thực tập tốt nghiệp',0,4,30,70,4.00,'https://drive.google.com/file/d/1E0Gp6-qEdRAvlfj9h1ko8ZswhtZmjrX3/view',1),(37,'Khóa luận tốt nghiệp',0,6,0,100,4.00,'https://drive.google.com/file/d/1Up69mRDQRcGvdpuD-DEJU6KIkYyYDgll/view',1);
/*!40000 ALTER TABLE `mon_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mon_hoc_lien_quan`
--

DROP TABLE IF EXISTS `mon_hoc_lien_quan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mon_hoc_lien_quan` (
  `mon_hoc_id` int NOT NULL,
  `lien_quan_id` int NOT NULL,
  `nganh_id` int NOT NULL,
  `loai` varchar(255) NOT NULL,
  PRIMARY KEY (`mon_hoc_id`,`lien_quan_id`,`nganh_id`,`loai`),
  KEY `lien_quan_id` (`lien_quan_id`),
  KEY `nganh_id` (`nganh_id`),
  CONSTRAINT `mon_hoc_lien_quan_ibfk_1` FOREIGN KEY (`mon_hoc_id`) REFERENCES `mon_hoc` (`id`),
  CONSTRAINT `mon_hoc_lien_quan_ibfk_2` FOREIGN KEY (`lien_quan_id`) REFERENCES `mon_hoc` (`id`),
  CONSTRAINT `mon_hoc_lien_quan_ibfk_3` FOREIGN KEY (`nganh_id`) REFERENCES `nganh` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mon_hoc_lien_quan`
--

LOCK TABLES `mon_hoc_lien_quan` WRITE;
/*!40000 ALTER TABLE `mon_hoc_lien_quan` DISABLE KEYS */;
INSERT INTO `mon_hoc_lien_quan` VALUES (3,1,1,'HOC_TRUOC'),(3,1,2,'HOC_TRUOC'),(3,1,3,'HOC_TRUOC'),(7,1,1,'HOC_TRUOC'),(7,1,2,'HOC_TRUOC'),(7,1,3,'HOC_TRUOC'),(22,1,3,'HOC_TRUOC'),(4,2,1,'HOC_TRUOC'),(4,2,2,'HOC_TRUOC'),(4,2,3,'HOC_TRUOC'),(5,2,1,'HOC_TRUOC'),(10,3,1,'HOC_TRUOC'),(10,3,2,'HOC_TRUOC'),(10,3,3,'HOC_TRUOC'),(24,3,3,'HOC_TRUOC'),(6,4,1,'HOC_TRUOC'),(11,4,1,'HOC_TRUOC'),(11,4,2,'HOC_TRUOC'),(11,4,3,'TIEN_QUYET'),(19,4,1,'HOC_TRUOC'),(23,4,2,'HOC_TRUOC'),(23,4,3,'HOC_TRUOC'),(25,4,2,'HOC_TRUOC'),(25,4,3,'HOC_TRUOC'),(27,4,3,'HOC_TRUOC'),(9,6,1,'HOC_TRUOC'),(32,6,1,'TIEN_QUYET'),(13,7,1,'HOC_TRUOC'),(13,7,2,'HOC_TRUOC'),(13,7,3,'HOC_TRUOC'),(16,7,1,'HOC_TRUOC'),(16,7,2,'HOC_TRUOC'),(16,7,3,'HOC_TRUOC'),(17,7,1,'HOC_TRUOC'),(17,7,3,'HOC_TRUOC'),(21,7,1,'HOC_TRUOC'),(30,7,2,'HOC_TRUOC'),(15,8,1,'HOC_TRUOC'),(15,8,3,'HOC_TRUOC'),(26,10,3,'HOC_TRUOC'),(27,10,3,'HOC_TRUOC'),(14,11,1,'HOC_TRUOC'),(14,11,3,'HOC_TRUOC'),(15,11,1,'HOC_TRUOC'),(15,11,3,'HOC_TRUOC'),(17,11,1,'HOC_TRUOC'),(17,11,3,'HOC_TRUOC'),(18,11,1,'HOC_TRUOC'),(21,11,1,'HOC_TRUOC'),(20,13,1,'HOC_TRUOC'),(20,13,3,'HOC_TRUOC'),(28,13,2,'HOC_TRUOC'),(18,22,1,'HOC_TRUOC'),(32,23,3,'TIEN_QUYET'),(35,23,2,'HOC_TRUOC'),(30,25,2,'HOC_TRUOC'),(31,29,2,'HOC_TRUOC'),(33,32,1,'HOC_TRUOC'),(33,32,3,'HOC_TRUOC'),(34,32,1,'TIEN_QUYET'),(36,35,2,'HOC_TRUOC'),(37,35,2,'HOC_TRUOC');
/*!40000 ALTER TABLE `mon_hoc_lien_quan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nganh`
--

DROP TABLE IF EXISTS `nganh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nganh` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_nganh` varchar(255) NOT NULL,
  `khoa_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ten_nganh` (`ten_nganh`,`khoa_id`),
  KEY `khoa_id` (`khoa_id`),
  CONSTRAINT `nganh_ibfk_1` FOREIGN KEY (`khoa_id`) REFERENCES `khoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nganh`
--

LOCK TABLES `nganh` WRITE;
/*!40000 ALTER TABLE `nganh` DISABLE KEYS */;
INSERT INTO `nganh` VALUES (3,'Công nghệ thông tin',1),(2,'Hệ thống thông tin quản lý',1),(1,'Khoa học máy tính',1);
/*!40000 ALTER TABLE `nganh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nganh_mon_hoc`
--

DROP TABLE IF EXISTS `nganh_mon_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nganh_mon_hoc` (
  `nganh_id` int NOT NULL,
  `mon_hoc_id` int NOT NULL,
  `ky` int NOT NULL,
  PRIMARY KEY (`nganh_id`,`mon_hoc_id`),
  KEY `mon_hoc_id` (`mon_hoc_id`),
  CONSTRAINT `nganh_mon_hoc_ibfk_1` FOREIGN KEY (`nganh_id`) REFERENCES `nganh` (`id`),
  CONSTRAINT `nganh_mon_hoc_ibfk_2` FOREIGN KEY (`mon_hoc_id`) REFERENCES `mon_hoc` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nganh_mon_hoc`
--

LOCK TABLES `nganh_mon_hoc` WRITE;
/*!40000 ALTER TABLE `nganh_mon_hoc` DISABLE KEYS */;
INSERT INTO `nganh_mon_hoc` VALUES (1,1,1),(1,2,1),(1,3,2),(1,4,2),(1,5,3),(1,6,3),(1,7,3),(1,8,4),(1,9,4),(1,10,4),(1,11,5),(1,12,5),(1,13,5),(1,14,6),(1,15,6),(1,16,7),(1,17,7),(1,18,7),(1,19,8),(1,20,8),(1,21,8),(1,32,9),(1,33,10),(1,34,11),(2,1,1),(2,2,1),(2,3,3),(2,4,2),(2,7,4),(2,8,6),(2,10,4),(2,11,4),(2,13,5),(2,16,7),(2,23,3),(2,25,5),(2,28,6),(2,29,7),(2,30,8),(2,31,8),(2,35,9),(2,36,10),(2,37,11),(3,1,1),(3,2,1),(3,3,2),(3,4,2),(3,7,5),(3,8,4),(3,10,3),(3,11,4),(3,12,5),(3,13,6),(3,14,6),(3,15,8),(3,16,7),(3,17,7),(3,20,8),(3,22,3),(3,23,3),(3,24,4),(3,25,5),(3,26,6),(3,27,7),(3,32,9),(3,33,10),(3,34,11);
/*!40000 ALTER TABLE `nganh_mon_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoi_dung`
--

DROP TABLE IF EXISTS `nguoi_dung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nguoi_dung` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ho_ten` varchar(255) NOT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `gioi_tinh` varchar(255) DEFAULT NULL,
  `so_dien_thoai` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `cccd` varchar(255) DEFAULT NULL,
  `mat_khau` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `vai_tro` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoi_dung`
--

LOCK TABLES `nguoi_dung` WRITE;
/*!40000 ALTER TABLE `nguoi_dung` DISABLE KEYS */;
INSERT INTO `nguoi_dung` VALUES (1,'Nguyễn Văn Ón','1975-10-07','nam','0123456789','onnguyenvan@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(2,'Trần Thị Tuyết','1975-10-07','nu','0123456789','tuyettranthi@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(3,'Lê Minh Bảo','1975-10-07','nam','0123456789','baoleminh@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(4,'Nguyễn Hữu Khuê','1975-10-07','nam','0123456789','khuenguyenhuu@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(5,'Phạm Minh Trang','1975-10-07','nu','0123456789','trangphamminh@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(6,'Đỗ Quang Hùng','1975-10-07','nam','0123456789','hungdoquang@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(7,'Phùng Thị Anh','1975-10-07','nu','0123456789','anhphungthi@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(8,'Võ Văn Kiệt','1975-10-07','nam','0123456789','kietvovan@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(9,'Dương Minh Công','1975-10-07','nam','0123456789','congminhduong@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(10,'Nguyễn Lộc Thành','1975-10-07','nam','0123456789','thanhnguenloc@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(11,'Trần Tuấn Khải','1975-10-07','nam','0123456789','khaitrantuan@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(12,'Đỗ Kim Tuấn','1975-10-07','nam','0123456789','tuandokim@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(13,'Trần Hà Thanh','1975-10-07','nu','0123456789','thanhtranha@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(14,'Nguyễn Quốc Thiên','1975-10-07','nam','0123456789','thiennguyenquoc@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(15,'Nguyễn Phương Trang','1975-10-07','nu','0123456789','trangnguyephuong@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(16,'Ngô Tiến Đạt','1975-10-07','nam','0123456789','datngotien@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(17,'Trương Trí Kiệt','1975-10-07','nam','0123456789','kiettruongtri@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(18,'Ngô Ngọc Hậu','1975-10-07','nam','0123456789','haungongoc@giangvien.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_GIANG_VIEN'),(19,'Tô Quốc Bình','2004-02-21','nam','0762590966','binh@student.edu.vn','079204036719','$2a$10$ovRvIJrlNrhHGxX2140Lj.0OyoPdePXeU/.tls11mdwOuWefISv0O','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_SINH_VIEN'),(20,'Trần Huỳnh Sang','2004-03-24','nam','0762514230','sang@student.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_SINH_VIEN'),(21,'Nguyễn Đăng Khôi','2004-05-27','nam','0903234139','khoi@student.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_SINH_VIEN'),(22,'Võ Quốc Bảo','2006-05-15','nam','0789764093','bao@student.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_SINH_VIEN'),(23,'Trần Quốc Phong','2006-05-15','nam','0789764094','phong@student.edu.vn','012345678912','$2a$10$PG5LlPWRAfOGgkDIy/SfOu/a6IHgsVn1s5AqnWPPYtGhPQRn7EX0S','https://res.cloudinary.com/dbhhpljbo/image/upload/v1755243635/356743267_641722777924328_3854581708681478680_n_rnpytv.jpg','ROLE_SINH_VIEN'),(24,'Admin Hệ Thống',NULL,NULL,NULL,'admin@admin.vn',NULL,'$2a$10$YJWUKd2V8ZTn/uiiz6UQGOrEmScXYjxJCCQDrLRU3iD3vaYmmjNPm',NULL,'ROLE_ADMIN');
/*!40000 ALTER TABLE `nguoi_dung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phong_hoc`
--

DROP TABLE IF EXISTS `phong_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phong_hoc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_phong` varchar(255) NOT NULL,
  `loai` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ten_phong` (`ten_phong`),
  UNIQUE KEY `ten_phong_2` (`ten_phong`,`loai`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong_hoc`
--

LOCK TABLES `phong_hoc` WRITE;
/*!40000 ALTER TABLE `phong_hoc` DISABLE KEYS */;
INSERT INTO `phong_hoc` VALUES (1,'101','LyThuyet'),(2,'102','LyThuyet'),(3,'103','LyThuyet'),(4,'104','LyThuyet'),(5,'201','LyThuyet'),(6,'202','LyThuyet'),(7,'203','LyThuyet'),(8,'204','LyThuyet'),(9,'301','LyThuyet'),(10,'302','LyThuyet'),(11,'303','LyThuyet'),(12,'304','LyThuyet'),(13,'PM.101','ThucHanh'),(14,'PM.102','ThucHanh'),(15,'PM.103','ThucHanh'),(16,'PM.201','ThucHanh'),(17,'PM.202','ThucHanh'),(18,'PM.203','ThucHanh'),(19,'PM.301','ThucHanh'),(20,'PM.302','ThucHanh'),(21,'PM.303','ThucHanh');
/*!40000 ALTER TABLE `phong_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quy_dinh`
--

DROP TABLE IF EXISTS `quy_dinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quy_dinh` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten` varchar(255) NOT NULL,
  `gia_tri` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ten` (`ten`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quy_dinh`
--

LOCK TABLES `quy_dinh` WRITE;
/*!40000 ALTER TABLE `quy_dinh` DISABLE KEYS */;
INSERT INTO `quy_dinh` VALUES (1,'Số tiền 1 tín chỉ',700000),(2,'Số tín chỉ tối đa',24),(3,'Số buổi 1 tín chỉ lý thuyết',3),(4,'Số buổi 1 tín chỉ thực hành',5),(5,'Số tuần 1 học kỳ',15);
/*!40000 ALTER TABLE `quy_dinh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sinh_vien`
--

DROP TABLE IF EXISTS `sinh_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sinh_vien` (
  `id` int NOT NULL,
  `khoa_hoc` int DEFAULT NULL,
  `so_tin_chi` int DEFAULT '0',
  `khoa_id` int NOT NULL,
  `nganh_id` int NOT NULL,
  `lop_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `khoa_id` (`khoa_id`),
  KEY `nganh_id` (`nganh_id`),
  KEY `lop_id` (`lop_id`),
  CONSTRAINT `sinh_vien_ibfk_1` FOREIGN KEY (`id`) REFERENCES `nguoi_dung` (`id`) ON DELETE CASCADE,
  CONSTRAINT `sinh_vien_ibfk_2` FOREIGN KEY (`khoa_id`) REFERENCES `khoa` (`id`),
  CONSTRAINT `sinh_vien_ibfk_3` FOREIGN KEY (`nganh_id`) REFERENCES `nganh` (`id`),
  CONSTRAINT `sinh_vien_ibfk_4` FOREIGN KEY (`lop_id`) REFERENCES `lop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sinh_vien`
--

LOCK TABLES `sinh_vien` WRITE;
/*!40000 ALTER TABLE `sinh_vien` DISABLE KEYS */;
INSERT INTO `sinh_vien` VALUES (19,2022,75,1,1,1),(20,2022,75,1,1,1),(21,2022,75,1,1,1),(22,2024,25,1,3,2),(23,2024,25,1,3,5);
/*!40000 ALTER TABLE `sinh_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thoi_khoa_bieu`
--

DROP TABLE IF EXISTS `thoi_khoa_bieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thoi_khoa_bieu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sinh_vien_id` int NOT NULL,
  `lich_hoc_id` int NOT NULL,
  `hoc_ky_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sinh_vien_id` (`sinh_vien_id`,`lich_hoc_id`),
  KEY `lich_hoc_id` (`lich_hoc_id`),
  KEY `hoc_ky_id` (`hoc_ky_id`),
  CONSTRAINT `thoi_khoa_bieu_ibfk_1` FOREIGN KEY (`sinh_vien_id`) REFERENCES `sinh_vien` (`id`),
  CONSTRAINT `thoi_khoa_bieu_ibfk_2` FOREIGN KEY (`lich_hoc_id`) REFERENCES `lich_hoc` (`id`),
  CONSTRAINT `thoi_khoa_bieu_ibfk_3` FOREIGN KEY (`hoc_ky_id`) REFERENCES `hoc_ky` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thoi_khoa_bieu`
--

LOCK TABLES `thoi_khoa_bieu` WRITE;
/*!40000 ALTER TABLE `thoi_khoa_bieu` DISABLE KEYS */;
INSERT INTO `thoi_khoa_bieu` VALUES (1,19,1,1),(2,19,2,1),(3,19,3,1),(4,19,4,1),(5,19,5,2),(6,19,6,2),(7,19,7,2),(8,19,8,3),(9,19,9,3),(10,19,10,3),(11,19,11,3),(12,19,12,3),(13,19,13,3),(14,19,14,4),(15,19,15,4),(16,19,16,4),(17,19,17,4),(18,19,18,4),(19,19,19,5),(20,19,20,5),(21,19,21,5),(22,19,22,5),(23,19,23,6),(24,19,24,6),(25,19,25,6),(26,19,26,6),(27,19,27,7),(28,19,28,7),(29,19,29,7),(30,19,30,7),(31,19,31,7),(32,19,32,7),(33,19,33,8),(34,19,34,8),(35,19,35,8),(36,19,36,8),(37,19,37,8),(38,19,38,8),(39,20,1,1),(40,20,2,1),(41,20,3,1),(42,20,4,1),(43,20,5,2),(44,20,6,2),(45,20,7,2),(46,20,8,3),(47,20,9,3),(48,20,10,3),(49,20,11,3),(50,20,12,3),(51,20,13,3),(52,20,14,4),(53,20,15,4),(54,20,16,4),(55,20,17,4),(56,20,18,4),(57,20,19,5),(58,20,20,5),(59,20,21,5),(60,20,22,5),(61,20,23,6),(62,20,24,6),(63,20,25,6),(64,20,26,6),(65,20,27,7),(66,20,28,7),(67,20,29,7),(68,20,30,7),(69,20,31,7),(70,20,32,7),(71,20,33,8),(72,20,34,8),(73,20,35,8),(74,20,36,8),(75,20,37,8),(76,20,38,8),(77,21,1,1),(78,21,2,1),(79,21,3,1),(80,21,4,1),(81,21,5,2),(82,21,6,2),(83,21,7,2),(84,21,8,3),(85,21,9,3),(86,21,10,3),(87,21,11,3),(88,21,12,3),(89,21,13,3),(90,21,14,4),(91,21,15,4),(92,21,16,4),(93,21,17,4),(94,21,18,4),(95,21,19,5),(96,21,20,5),(97,21,21,5),(98,21,22,5),(99,21,23,6),(100,21,24,6),(101,21,25,6),(102,21,26,6),(103,21,27,7),(104,21,28,7),(105,21,29,7),(106,21,30,7),(107,21,31,7),(108,21,32,7),(109,21,33,8),(110,21,34,8),(111,21,35,8),(112,21,36,8),(113,21,37,8),(114,21,38,8),(115,21,43,8),(116,22,39,7),(117,22,40,7),(118,22,41,7),(119,22,42,7),(120,22,43,8),(121,22,44,8),(122,22,45,8),(123,22,46,9),(124,22,47,9),(125,22,48,9),(126,22,49,9),(127,22,50,9),(128,22,51,9),(130,23,52,7),(131,23,53,7),(132,23,54,7),(133,23,55,7),(134,23,56,8),(135,23,57,8),(136,23,58,8),(137,23,59,9),(138,23,60,9),(139,23,61,9),(140,23,62,9),(141,23,63,9),(142,23,64,9);
/*!40000 ALTER TABLE `thoi_khoa_bieu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiet_hoc`
--

DROP TABLE IF EXISTS `tiet_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiet_hoc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tiet` varchar(255) NOT NULL,
  `gio_bat_dau` time NOT NULL,
  `gio_ket_thuc` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiet_hoc`
--

LOCK TABLES `tiet_hoc` WRITE;
/*!40000 ALTER TABLE `tiet_hoc` DISABLE KEYS */;
INSERT INTO `tiet_hoc` VALUES (1,'Tiết 1','07:30:00','11:30:00'),(2,'Tiết 2','13:00:00','17:00:00');
/*!40000 ALTER TABLE `tiet_hoc` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-07 10:59:12
