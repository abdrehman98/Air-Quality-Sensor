-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2019 at 01:29 PM
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
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`email`, `password`) VALUES
('mf@gmail.com', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `aqi`
--

CREATE TABLE `aqi` (
  `id` bigint(20) NOT NULL,
  `aqi` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `aqi`
--

INSERT INTO `aqi` (`id`, `aqi`, `device_id`, `timestamp`) VALUES
(66, 149, 1, '2019-03-20 20:11:35'),
(75, 149, 1, '2019-03-20 20:12:17'),
(84, 149, 1, '2019-03-20 20:12:18'),
(93, 149, 1, '2019-03-20 20:12:19'),
(102, 149, 1, '2019-03-20 20:12:20'),
(111, 149, 1, '2019-03-20 20:12:20'),
(120, 149, 1, '2019-03-22 03:54:18'),
(129, 149, 1, '2019-03-22 04:00:57'),
(138, 149, 1, '2019-03-22 04:07:23'),
(149, 148, 1, '2019-03-22 06:33:54'),
(160, 148, 1, '2019-05-12 16:26:48'),
(169, 148, 1, '2019-05-12 19:43:28'),
(178, 148, 1, '2019-06-03 01:19:45'),
(187, 148, 2, '2019-06-03 01:19:51'),
(196, 148, 3, '2019-06-03 01:19:57'),
(205, 148, 4, '2019-06-03 01:20:04'),
(214, 148, 5, '2019-06-03 01:20:11'),
(223, 148, 6, '2019-06-03 01:20:16'),
(232, 148, 6, '2019-06-03 01:23:41'),
(246, 148, 1, '2019-06-03 01:59:25'),
(255, 148, 1, '2019-06-04 12:17:17'),
(264, 148, 1, '2019-06-06 02:12:56'),
(276, 148, 1, '2019-06-06 02:23:33'),
(294, 148, 1, '2019-06-06 02:28:07'),
(303, 148, 1, '2019-06-06 02:28:08'),
(313, 148, 1, '2019-06-07 03:47:36');

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
(1, 'URL'),
(2, 'URL2');

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
(310, 148, 1, 31, 100, 100, 100, 31),
(300, 148, 1, 31, 100, 100, 100, 1),
(291, 148, 1, 31, 100, 100, 100, 1),
(288, 148, 2, 31, 100, 100, 100, 1),
(285, 148, 2, 31, 100, 100, 100, 1),
(282, 148, 2, 31, 100, 100, 100, 1),
(273, 148, 1, 31, 100, 100, 100, 1),
(270, 148, 2, 31, 100, 100, 100, 1),
(261, 148, 1, 31, 100, 100, 100, 1),
(252, 148, 1, 31, 100, 100, 100, 1),
(243, 148, 1, 31, 100, 100, 100, 1),
(238, 148, 1, 31, 100, 100, 100, 1),
(229, 148, 6, 31, 100, 100, 100, 1),
(220, 148, 6, 31, 100, 100, 100, 1),
(211, 148, 5, 31, 100, 100, 100, 1),
(202, 148, 4, 31, 100, 100, 100, 1),
(193, 148, 3, 31, 100, 100, 100, 1),
(184, 148, 2, 31, 100, 100, 100, 1),
(175, 148, 1, 31, 100, 100, 100, 1),
(166, 148, 1, 31, 100, 100, 100, 1),
(157, 148, 1, 31, 100, 100, 100, 1),
(117, 149, 1, 31, 100, 100, 100, 31),
(126, 149, 1, 31, 100, 100, 100, 31),
(135, 149, 1, 31, 100, 100, 100, 31),
(146, 148, 1, 31, 100, 100, 100, 31);

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
(3, 1, '2019-02-01 11:33:58'),
(4, 1, '2019-06-08 04:18:22');

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
(1, 'sed'),
(3, 'Thsi is a etst error.');

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
(10, 1, 1, '2019-01-27 21:00:56'),
(323, 3, 1, '2019-06-07 04:29:11');

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
(325),
(325),
(325),
(325),
(325),
(325),
(325),
(325);

-- --------------------------------------------------------

--
-- Table structure for table `humidity`
--

CREATE TABLE `humidity` (
  `id` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL,
  `humidity` double NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `humidity`
--

INSERT INTO `humidity` (`id`, `device_id`, `humidity`, `timestamp`) VALUES
(71, 1, 31, '2019-03-20 20:11:36'),
(80, 1, 31, '2019-03-20 20:12:17'),
(89, 1, 31, '2019-03-20 20:12:18'),
(98, 1, 31, '2019-03-20 20:12:19'),
(107, 1, 31, '2019-03-20 20:12:20'),
(116, 1, 31, '2019-03-20 20:12:20'),
(125, 1, 31, '2019-03-22 03:54:18'),
(134, 1, 31, '2019-03-22 04:00:57'),
(143, 1, 31, '2019-03-22 04:07:24'),
(154, 1, 31, '2019-03-22 06:33:55'),
(165, 1, 31, '2019-05-12 16:26:48'),
(174, 1, 31, '2019-05-12 19:43:29'),
(183, 1, 31, '2019-06-03 01:19:45'),
(192, 2, 31, '2019-06-03 01:19:51'),
(201, 3, 31, '2019-06-03 01:19:57'),
(210, 4, 31, '2019-06-03 01:20:04'),
(219, 5, 31, '2019-06-03 01:20:11'),
(228, 6, 31, '2019-06-03 01:20:16'),
(237, 6, 31, '2019-06-03 01:23:41'),
(251, 1, 31, '2019-06-03 01:59:25'),
(260, 1, 31, '2019-06-04 12:17:17'),
(269, 1, 31, '2019-06-06 02:12:56'),
(281, 1, 31, '2019-06-06 02:23:34'),
(299, 1, 31, '2019-06-06 02:28:07'),
(308, 1, 31, '2019-06-06 02:28:08'),
(318, 1, 31, '2019-06-07 03:47:36');

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `id` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL,
  `latitude` double NOT NULL,
  `location_name` varchar(255) NOT NULL,
  `locationtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `longitude` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`id`, `device_id`, `latitude`, `location_name`, `locationtime`, `longitude`) VALUES
(241, 1, 38.59, 'Lahore', '2019-06-03 01:58:41', 25.38),
(321, 2, 360, 'ArfaTower', '2019-06-07 04:17:45', 300);

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
(42, 'mf2@gmail.com', '123456'),
(309, 'mf3@gmail.com', '123456'),
(324, 'mf4@gmail.com', '123456');

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
(37, 150, 1, '2019-01-27 21:18:50'),
(53, 148, 1, '2019-03-20 19:57:27'),
(54, 148, 1, '2019-03-20 19:57:27'),
(58, 149, 1, '2019-03-20 20:00:11'),
(61, 149, 1, '2019-03-20 20:03:03'),
(64, 149, 1, '2019-03-20 20:11:35'),
(73, 149, 1, '2019-03-20 20:12:17'),
(82, 149, 1, '2019-03-20 20:12:18'),
(91, 149, 1, '2019-03-20 20:12:19'),
(100, 149, 1, '2019-03-20 20:12:20'),
(109, 149, 1, '2019-03-20 20:12:20'),
(118, 149, 1, '2019-03-22 03:54:18'),
(127, 149, 1, '2019-03-22 04:00:57'),
(136, 149, 1, '2019-03-22 04:07:23'),
(147, 148, 1, '2019-03-22 06:33:54'),
(158, 148, 1, '2019-05-12 16:26:48'),
(167, 148, 1, '2019-05-12 19:43:27'),
(176, 148, 1, '2019-06-03 01:19:45'),
(185, 148, 2, '2019-06-03 01:19:51'),
(194, 148, 3, '2019-06-03 01:19:57'),
(203, 148, 4, '2019-06-03 01:20:04'),
(212, 148, 5, '2019-06-03 01:20:11'),
(221, 148, 6, '2019-06-03 01:20:16'),
(230, 148, 6, '2019-06-03 01:23:41'),
(239, 148, 1, '2019-06-03 01:42:09'),
(244, 148, 1, '2019-06-03 01:59:25'),
(253, 148, 1, '2019-06-04 12:17:17'),
(262, 148, 1, '2019-06-06 02:12:55'),
(271, 148, 2, '2019-06-06 02:15:11'),
(274, 148, 1, '2019-06-06 02:23:33'),
(283, 148, 2, '2019-06-06 02:23:44'),
(286, 148, 2, '2019-06-06 02:24:11'),
(289, 148, 2, '2019-06-06 02:24:12'),
(292, 148, 1, '2019-06-06 02:28:07'),
(301, 148, 1, '2019-06-06 02:28:08'),
(311, 148, 1, '2019-06-07 03:47:36');

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
(38, 150, 1, '2019-01-27 21:18:50'),
(56, 148, 1, '2019-03-20 19:57:28'),
(55, 148, 1, '2019-03-20 19:57:28'),
(59, 149, 1, '2019-03-20 20:00:11'),
(62, 149, 1, '2019-03-20 20:03:03'),
(65, 149, 1, '2019-03-20 20:11:35'),
(74, 149, 1, '2019-03-20 20:12:17'),
(83, 149, 1, '2019-03-20 20:12:18'),
(92, 149, 1, '2019-03-20 20:12:19'),
(101, 149, 1, '2019-03-20 20:12:20'),
(110, 149, 1, '2019-03-20 20:12:20'),
(119, 149, 1, '2019-03-22 03:54:18'),
(128, 149, 1, '2019-03-22 04:00:57'),
(137, 149, 1, '2019-03-22 04:07:23'),
(148, 148, 1, '2019-03-22 06:33:54'),
(159, 148, 1, '2019-05-12 16:26:48'),
(168, 148, 1, '2019-05-12 19:43:27'),
(177, 100, 1, '2019-06-03 01:19:45'),
(186, 100, 2, '2019-06-03 01:19:51'),
(195, 100, 3, '2019-06-03 01:19:57'),
(204, 100, 4, '2019-06-03 01:20:04'),
(213, 100, 5, '2019-06-03 01:20:11'),
(222, 100, 6, '2019-06-03 01:20:16'),
(231, 100, 6, '2019-06-03 01:23:41'),
(240, 100, 1, '2019-06-03 01:42:10'),
(245, 100, 1, '2019-06-03 01:59:25'),
(254, 100, 1, '2019-06-04 12:17:17'),
(263, 100, 1, '2019-06-06 02:12:56'),
(272, 100, 2, '2019-06-06 02:15:11'),
(275, 100, 1, '2019-06-06 02:23:33'),
(284, 100, 2, '2019-06-06 02:23:44'),
(287, 100, 2, '2019-06-06 02:24:11'),
(290, 100, 2, '2019-06-06 02:24:12'),
(293, 100, 1, '2019-06-06 02:28:07'),
(302, 100, 1, '2019-06-06 02:28:08'),
(312, 100, 1, '2019-06-07 03:47:36');

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
-- Table structure for table `pm1`
--

CREATE TABLE `pm1` (
  `id` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL,
  `pm1` double NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pm1`
--

INSERT INTO `pm1` (`id`, `device_id`, `pm1`, `timestamp`) VALUES
(67, 1, 100, '2019-03-20 20:11:35'),
(76, 1, 100, '2019-03-20 20:12:17'),
(85, 1, 100, '2019-03-20 20:12:18'),
(94, 1, 100, '2019-03-20 20:12:19'),
(103, 1, 100, '2019-03-20 20:12:20'),
(112, 1, 100, '2019-03-20 20:12:20'),
(121, 1, 100, '2019-03-22 03:54:18'),
(130, 1, 100, '2019-03-22 04:00:57'),
(139, 1, 100, '2019-03-22 04:07:24'),
(150, 1, 100, '2019-03-22 06:33:54'),
(161, 1, 100, '2019-05-12 16:26:48'),
(170, 1, 100, '2019-05-12 19:43:29'),
(179, 1, 100, '2019-06-03 01:19:45'),
(188, 2, 100, '2019-06-03 01:19:51'),
(197, 3, 100, '2019-06-03 01:19:57'),
(206, 4, 100, '2019-06-03 01:20:04'),
(215, 5, 100, '2019-06-03 01:20:11'),
(224, 6, 100, '2019-06-03 01:20:16'),
(233, 6, 100, '2019-06-03 01:23:41'),
(247, 1, 100, '2019-06-03 01:59:25'),
(256, 1, 100, '2019-06-04 12:17:17'),
(265, 1, 100, '2019-06-06 02:12:56'),
(277, 1, 100, '2019-06-06 02:23:33'),
(295, 1, 100, '2019-06-06 02:28:07'),
(304, 1, 100, '2019-06-06 02:28:08'),
(314, 1, 100, '2019-06-07 03:47:36');

-- --------------------------------------------------------

--
-- Table structure for table `pm10`
--

CREATE TABLE `pm10` (
  `id` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL,
  `pm10` double NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pm10`
--

INSERT INTO `pm10` (`id`, `device_id`, `pm10`, `timestamp`) VALUES
(68, 1, 100, '2019-03-20 20:11:36'),
(77, 1, 100, '2019-03-20 20:12:17'),
(86, 1, 100, '2019-03-20 20:12:18'),
(95, 1, 100, '2019-03-20 20:12:19'),
(104, 1, 100, '2019-03-20 20:12:20'),
(113, 1, 100, '2019-03-20 20:12:20'),
(122, 1, 100, '2019-03-22 03:54:18'),
(131, 1, 100, '2019-03-22 04:00:57'),
(140, 1, 100, '2019-03-22 04:07:24'),
(151, 1, 100, '2019-03-22 06:33:54'),
(162, 1, 100, '2019-05-12 16:26:48'),
(171, 1, 100, '2019-05-12 19:43:29'),
(180, 1, 100, '2019-06-03 01:19:45'),
(189, 2, 100, '2019-06-03 01:19:51'),
(198, 3, 100, '2019-06-03 01:19:57'),
(207, 4, 100, '2019-06-03 01:20:04'),
(216, 5, 100, '2019-06-03 01:20:11'),
(225, 6, 100, '2019-06-03 01:20:16'),
(234, 6, 100, '2019-06-03 01:23:41'),
(248, 1, 100, '2019-06-03 01:59:25'),
(257, 1, 100, '2019-06-04 12:17:17'),
(266, 1, 100, '2019-06-06 02:12:56'),
(278, 1, 100, '2019-06-06 02:23:33'),
(296, 1, 100, '2019-06-06 02:28:07'),
(305, 1, 100, '2019-06-06 02:28:08'),
(315, 1, 100, '2019-06-07 03:47:36');

-- --------------------------------------------------------

--
-- Table structure for table `pm25`
--

CREATE TABLE `pm25` (
  `id` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL,
  `pm25` double NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pm25`
--

INSERT INTO `pm25` (`id`, `device_id`, `pm25`, `timestamp`) VALUES
(69, 1, 100, '2019-03-20 20:11:36'),
(78, 1, 100, '2019-03-20 20:12:17'),
(87, 1, 100, '2019-03-20 20:12:18'),
(96, 1, 100, '2019-03-20 20:12:19'),
(105, 1, 100, '2019-03-20 20:12:20'),
(114, 1, 100, '2019-03-20 20:12:20'),
(123, 1, 100, '2019-03-22 03:54:18'),
(132, 1, 100, '2019-03-22 04:00:57'),
(141, 1, 100, '2019-03-22 04:07:24'),
(152, 1, 100, '2019-03-22 06:33:54'),
(163, 1, 100, '2019-05-12 16:26:48'),
(172, 1, 100, '2019-05-12 19:43:29'),
(181, 1, 100, '2019-06-03 01:19:45'),
(190, 2, 100, '2019-06-03 01:19:51'),
(199, 3, 100, '2019-06-03 01:19:57'),
(208, 4, 100, '2019-06-03 01:20:04'),
(217, 5, 100, '2019-06-03 01:20:11'),
(226, 6, 100, '2019-06-03 01:20:16'),
(235, 6, 100, '2019-06-03 01:23:41'),
(249, 1, 100, '2019-06-03 01:59:25'),
(258, 1, 100, '2019-06-04 12:17:17'),
(267, 1, 100, '2019-06-06 02:12:56'),
(279, 1, 100, '2019-06-06 02:23:34'),
(297, 1, 100, '2019-06-06 02:28:07'),
(306, 1, 100, '2019-06-06 02:28:08'),
(316, 1, 100, '2019-06-07 03:47:36');

-- --------------------------------------------------------

--
-- Table structure for table `previouslocation`
--

CREATE TABLE `previouslocation` (
  `id` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL,
  `latitude` double NOT NULL,
  `locationtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `longitude` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `previouslocation`
--

INSERT INTO `previouslocation` (`id`, `device_id`, `latitude`, `locationtime`, `longitude`) VALUES
(43, 1, 6.1, '2019-01-27 20:51:13', 5.1),
(45, 1, 35.56, '2019-03-19 16:55:09', 35.56),
(47, 1, 3556, '2019-03-19 16:55:31', 3556),
(49, 1, 3556, '2019-03-19 16:55:33', 3556),
(144, 1, 35.56, '2019-03-20 07:08:46', 35.56),
(155, 1, 360, '2019-03-22 04:31:13', 300),
(242, 1, 38.59, '2019-06-03 01:58:41', 25.38),
(322, 2, 360, '2019-06-07 04:17:45', 300);

-- --------------------------------------------------------

--
-- Table structure for table `publicside`
--

CREATE TABLE `publicside` (
  `deviceid` bigint(20) NOT NULL,
  `aqi` bigint(20) DEFAULT NULL,
  `latitude` double NOT NULL,
  `location_name` varchar(255) DEFAULT NULL,
  `longitude` double NOT NULL,
  `pm25` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `publicside`
--

INSERT INTO `publicside` (`deviceid`, `aqi`, `latitude`, `location_name`, `longitude`, `pm25`) VALUES
(1, 148, 38.59, 'Lahore', 25.38, 100);

-- --------------------------------------------------------

--
-- Table structure for table `temperature`
--

CREATE TABLE `temperature` (
  `id` bigint(20) NOT NULL,
  `device_id` bigint(20) NOT NULL,
  `temperature` double NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `temperature`
--

INSERT INTO `temperature` (`id`, `device_id`, `temperature`, `timestamp`) VALUES
(70, 1, 31, '2019-03-20 20:11:36'),
(79, 1, 31, '2019-03-20 20:12:17'),
(88, 1, 31, '2019-03-20 20:12:18'),
(97, 1, 31, '2019-03-20 20:12:19'),
(106, 1, 31, '2019-03-20 20:12:20'),
(115, 1, 31, '2019-03-20 20:12:20'),
(124, 1, 31, '2019-03-22 03:54:18'),
(133, 1, 31, '2019-03-22 04:00:57'),
(142, 1, 31, '2019-03-22 04:07:24'),
(153, 1, 31, '2019-03-22 06:33:54'),
(164, 1, 1, '2019-05-12 16:26:48'),
(173, 1, 1, '2019-05-12 19:43:29'),
(182, 1, 1, '2019-06-03 01:19:45'),
(191, 2, 1, '2019-06-03 01:19:51'),
(200, 3, 1, '2019-06-03 01:19:57'),
(209, 4, 1, '2019-06-03 01:20:04'),
(218, 5, 1, '2019-06-03 01:20:11'),
(227, 6, 1, '2019-06-03 01:20:16'),
(236, 6, 1, '2019-06-03 01:23:41'),
(250, 1, 1, '2019-06-03 01:59:25'),
(259, 1, 1, '2019-06-04 12:17:17'),
(268, 1, 1, '2019-06-06 02:12:56'),
(280, 1, 1, '2019-06-06 02:23:34'),
(298, 1, 1, '2019-06-06 02:28:07'),
(307, 1, 1, '2019-06-06 02:28:08'),
(317, 1, 31, '2019-06-07 03:47:36');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`email`);

--
-- Indexes for table `aqi`
--
ALTER TABLE `aqi`
  ADD PRIMARY KEY (`id`);

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
-- Indexes for table `humidity`
--
ALTER TABLE `humidity`
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
-- Indexes for table `pm1`
--
ALTER TABLE `pm1`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pm10`
--
ALTER TABLE `pm10`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pm25`
--
ALTER TABLE `pm25`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `previouslocation`
--
ALTER TABLE `previouslocation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `publicside`
--
ALTER TABLE `publicside`
  ADD PRIMARY KEY (`deviceid`);

--
-- Indexes for table `temperature`
--
ALTER TABLE `temperature`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
