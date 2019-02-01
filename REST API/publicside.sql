-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 01, 2019 at 12:39 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbtest`
--

-- --------------------------------------------------------

--
-- Table structure for table `publicside`
--

CREATE TABLE `publicside` (
  `deviceid` bigint(20) NOT NULL,
  `aqi` bigint(20) DEFAULT NULL,
  `latitude` double NOT NULL,
  `location_name` varchar(255) DEFAULT NULL,
  `longitude` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `publicside`
--

INSERT INTO `publicside` (`deviceid`, `aqi`, `latitude`, `location_name`, `longitude`) VALUES
(24, NULL, 6.1, NULL, 5.1),
(1, 150, 0, NULL, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `publicside`
--
ALTER TABLE `publicside`
  ADD PRIMARY KEY (`deviceid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
