-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.0.17-nt - MySQL Community Edition (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for judicialblockchain
CREATE DATABASE IF NOT EXISTS `judicialblockchain` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `judicialblockchain`;

-- Dumping structure for table judicialblockchain.judge
CREATE TABLE IF NOT EXISTS `judge` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `jname` varchar(50) default NULL,
  `age` varchar(50) default NULL,
  `address` varchar(150) default NULL,
  `contact` varchar(15) default NULL,
  `email` varchar(50) default NULL,
  `uname` varchar(50) default NULL,
  `pass` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table judicialblockchain.judge: ~1 rows (approximately)
/*!40000 ALTER TABLE `judge` DISABLE KEYS */;
INSERT INTO `judge` (`id`, `jname`, `age`, `address`, `contact`, `email`, `uname`, `pass`) VALUES
	(1, 'judge1', '56', 'vijay nagar\r\nbengaluru', '4356789099', 'codebigtechnologies@gmail.com', 'judge1', 'judge1');
/*!40000 ALTER TABLE `judge` ENABLE KEYS */;

-- Dumping structure for table judicialblockchain.lawyer
CREATE TABLE IF NOT EXISTS `lawyer` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `lname` varchar(50) default NULL,
  `age` varchar(50) default NULL,
  `address` varchar(150) default NULL,
  `contact` varchar(15) default NULL,
  `email` varchar(50) default NULL,
  `uname` varchar(50) default NULL,
  `pass` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table judicialblockchain.lawyer: ~1 rows (approximately)
/*!40000 ALTER TABLE `lawyer` DISABLE KEYS */;
INSERT INTO `lawyer` (`id`, `lname`, `age`, `address`, `contact`, `email`, `uname`, `pass`) VALUES
	(1, 'lawyer1', '39', 'vijay nagar\r\nbengaluru', '5467890987', 'codebigtechnologies@gmail.com', 'lawyer1', 'lawyer1');
/*!40000 ALTER TABLE `lawyer` ENABLE KEYS */;

-- Dumping structure for table judicialblockchain.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `cname` varchar(50) default NULL,
  `age` varchar(50) default NULL,
  `address` varchar(150) default NULL,
  `contact` varchar(15) default NULL,
  `email` varchar(50) default NULL,
  `uname` varchar(50) default NULL,
  `pass` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table judicialblockchain.user: ~1 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `cname`, `age`, `address`, `contact`, `email`, `uname`, `pass`) VALUES
	(1, 'aaa', '29', 'vijay nagar\r\nbengaluru', '9876789765', 'aaa@gmail.com', 'aaa', 'aaa');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
