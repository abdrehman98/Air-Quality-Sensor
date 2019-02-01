-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 01, 2019 at 02:06 PM
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
-- Table structure for table `codeversion`
--

CREATE TABLE `codeversion` (
  `id` bigint(20) NOT NULL,
  `firmware_url` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `codeversion`
--

INSERT INTO `codeversion` (`id`, `firmware_url`) VALUES
(1, 'URL');

-- --------------------------------------------------------

--
-- Table structure for table `datarecord`
--

CREATE TABLE `datarecord` (
  `id` bigint(20) NOT NULL,
  `aqi` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL,
  `humidity` double NOT NULL,
  `pm1` double NOT NULL,
  `pm10` double NOT NULL,
  `pm25` double NOT NULL,
  `temperature` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `datarecord`
--

INSERT INTO `datarecord` (`id`, `aqi`, `device_id`, `humidity`, `pm1`, `pm10`, `pm25`, `temperature`) VALUES
(11, 150, 1, 20, 15, 20, 10, 50),
(12, 150, 1, 20, 15, 20, 10, 50),
(13, 150, 1, 20, 15, 20, 10, 50),
(14, 150, 1, 20, 15, 20, 10, 50),
(15, 150, 1, 20, 15, 20, 10, 50),
(16, 150, 1, 20, 15, 20, 10, 50),
(17, 150, 1, 20, 15, 20, 10, 50),
(18, 150, 2, 20, 15, 20, 10, 50),
(20, 150, 1, 20, 15, 20, 10, 50),
(21, 150, 1, 20, 15, 20, 10, 50),
(24, 150, 1, 20, 15, 20, 10, 50),
(27, 150, 1, 20, 15, 20, 10, 50),
(30, 150, 1, 20, 15, 20, 10, 50),
(33, 150, 1, 20, 15, 20, 10, 50),
(36, 150, 1, 20, 15, 20, 10, 50);

-- --------------------------------------------------------

--
-- Table structure for table `device`
--

CREATE TABLE `device` (
  `id` bigint(20) NOT NULL,
  `code_version_id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `device`
--

INSERT INTO `device` (`id`, `code_version_id`, `created_at`) VALUES
(1, 1, '2019-01-27 20:50:08'),
(2, 1, '2019-02-01 11:33:49'),
(3, 1, '2019-02-01 11:33:58');

-- --------------------------------------------------------

--
-- Table structure for table `error`
--

CREATE TABLE `error` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `error`
--

INSERT INTO `error` (`id`, `description`) VALUES
(2, 'sed'),
(1, 'sed');

-- --------------------------------------------------------

--
-- Table structure for table `errorlog`
--

CREATE TABLE `errorlog` (
  `id` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL,
  `error_id` bigint(20) NOT NULL,
  `occured_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `errorlog`
--

INSERT INTO `errorlog` (`id`, `device_id`, `error_id`, `occured_at`) VALUES
(3, 1, 1, '2019-01-27 20:56:56'),
(4, 1, 1, '2019-01-27 20:56:59'),
(5, 1, 1, '2019-01-27 20:57:01'),
(6, 1, 5, '2019-01-27 20:57:37'),
(7, 1, 5, '2019-01-27 20:57:39'),
(8, 1, 5, '2019-01-27 20:57:41'),
(9, 1, 50, '2019-01-27 20:57:46'),
(10, 1, 1, '2019-01-27 21:00:56');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(43),
(43),
(43),
(43),
(43),
(43),
(43),
(43);

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `id` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL,
  `latitude` double NOT NULL,
  `locationtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `longitude` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`id`, `device_id`, `latitude`, `locationtime`, `longitude`) VALUES
(1, 1, 6.1, '2019-01-27 20:51:13', 5.1);

-- --------------------------------------------------------

--
-- Table structure for table `partner`
--

CREATE TABLE `partner` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `partner`
--

INSERT INTO `partner` (`id`, `email`, `password`) VALUES
(39, 'mf@gmail.com', '123456'),
(41, 'mf1@gmail.com', '123456'),
(42, 'mf2@gmail.com', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `partnerpreviousaqi`
--

CREATE TABLE `partnerpreviousaqi` (
  `id` bigint(20) NOT NULL,
  `aqi` bigint(20) NOT NULL,
  `deviceid` bigint(20) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `partnerpreviousaqi`
--

INSERT INTO `partnerpreviousaqi` (`id`, `aqi`, `deviceid`, `timestamp`) VALUES
(22, 150, 1, '2019-01-27 21:10:19'),
(25, 150, 1, '2019-01-27 21:12:22'),
(28, 150, 1, '2019-01-27 21:12:36'),
(31, 150, 1, '2019-01-27 21:17:25'),
(34, 150, 1, '2019-01-27 21:18:38'),
(37, 150, 1, '2019-01-27 21:18:50');

-- --------------------------------------------------------

--
-- Table structure for table `partnerpreviouspm25`
--

CREATE TABLE `partnerpreviouspm25` (
  `id` bigint(20) NOT NULL,
  `pm25` double NOT NULL,
  `deviceid` bigint(20) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `partnerpreviouspm25`
--

INSERT INTO `partnerpreviouspm25` (`id`, `pm25`, `deviceid`, `timestamp`) VALUES
(23, 150, 1, '2019-01-27 21:10:19'),
(26, 150, 1, '2019-01-27 21:12:22'),
(29, 150, 1, '2019-01-27 21:12:36'),
(32, 150, 1, '2019-01-27 21:17:25'),
(35, 150, 1, '2019-01-27 21:18:38'),
(38, 150, 1, '2019-01-27 21:18:50');

-- --------------------------------------------------------

--
-- Table structure for table `partnersignin`
--

CREATE TABLE `partnersignin` (
  `id` bigint(20) NOT NULL,
  `end` datetime DEFAULT NULL,
  `partner_id` bigint(20) DEFAULT NULL,
  `start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `partnersignin`
--

INSERT INTO `partnersignin` (`id`, `end`, `partner_id`, `start`) VALUES
(40, NULL, NULL, '2019-02-01 11:28:11');

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
-- Indexes for table `codeversion`
--
ALTER TABLE `codeversion`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `datarecord`
--
ALTER TABLE `datarecord`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `error`
--
ALTER TABLE `error`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `errorlog`
--
ALTER TABLE `errorlog`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `partner`
--
ALTER TABLE `partner`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_kjhx5hhn0xq0h5lcn987ugv2o` (`email`);

--
-- Indexes for table `partnerpreviousaqi`
--
ALTER TABLE `partnerpreviousaqi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `partnerpreviouspm25`
--
ALTER TABLE `partnerpreviouspm25`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `partnersignin`
--
ALTER TABLE `partnersignin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `publicside`
--
ALTER TABLE `publicside`
  ADD PRIMARY KEY (`deviceid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
