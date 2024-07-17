-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 17, 2024 at 02:37 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `trading-card-game`
--

-- --------------------------------------------------------

--
-- Table structure for table `card`
--

CREATE TABLE `card` (
  `ID` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `attack` int(11) NOT NULL,
  `damage` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `upLevel` int(11) NOT NULL,
  `upCost` int(11) NOT NULL,
  `gameChar` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `card`
--

INSERT INTO `card` (`ID`, `name`, `attack`, `damage`, `duration`, `price`, `upLevel`, `upCost`, `gameChar`) VALUES
(1, 'boom', 15, 40, 2, 100, 1, 50, 'Fire'),
(2, 'Inferno Strike', 13, 20, 3, 75, 1, 35, 'Fire'),
(3, 'Thunderclap', 20, 45, 1, 125, 3, 75, 'Ice'),
(4, 'Venomous Bite', 20, 15, 4, 100, 2, 60, 'Posion'),
(5, 'Meteor Smash', 12, 15, 5, 80, 2, 45, 'Fire'),
(6, 'Shadow Slash', 17, 30, 2, 100, 1, 40, 'Ice'),
(7, 'Dragon’s Breath', 15, 30, 3, 95, 1, 60, 'Fire'),
(8, 'Fiery Blitz', 16, 35, 1, 100, 1, 65, 'Electricity'),
(9, 'Arcane Burst', 25, 10, 3, 100, 2, 45, 'Fire'),
(10, 'Frostbite', 18, 20, 5, 85, 2, 60, 'Ice'),
(11, 'Earthquake', 50, 20, 1, 75, 2, 50, 'Posion'),
(12, 'Blade Storm', 10, 30, 2, 80, 1, 40, 'Electricity'),
(13, 'Poison Dart', 15, 15, 1, 65, 1, 30, 'Posion'),
(14, 'Solar Flare', 22, 30, 1, 85, 3, 60, 'Fire'),
(15, 'Dark Pulse', 17, 25, 4, 100, 3, 65, 'Ice'),
(16, 'Mystic Bolt', 30, 20, 1, 125, 2, 60, 'Ice'),
(17, 'Flame Wave', 19, 20, 4, 85, 1, 60, 'Fire'),
(18, 'Shockwave', 25, 10, 5, 125, 2, 60, 'Electricity'),
(19, 'Savage Claw', 20, 25, 3, 100, 2, 55, 'Electricity'),
(20, 'Ice Shard', 22, 30, 2, 80, 3, 55, 'Ice');

-- --------------------------------------------------------

--
-- Table structure for table `gamehistory`
--

CREATE TABLE `gamehistory` (
  `ID` int(11) NOT NULL,
  `rivalID` int(11) NOT NULL,
  `gameResult` varchar(32) NOT NULL,
  `gameTime` varchar(64) NOT NULL,
  `RewardsPenalties` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `gamehistory`
--

INSERT INTO `gamehistory` (`ID`, `rivalID`, `gameResult`, `gameTime`, `RewardsPenalties`) VALUES
(1, 1, 'WON', '2024-07-06T19:05:57.787787', 'earned nothing'),
(2, 2, 'WON', '2024-07-06T20:05:57.787787', 'earned banana'),
(3, 2, 'LOST', '2024-01-06T19:05:57.787787', 'earned banana'),
(4, 3, 'LOST', '2024-07-06T19:15:57.787787', 'earned '),
(5, 3, 'WON', '2024-07-08T10:07:01.536834', 'earned 100 XP, earned 0 money'),
(6, 4, 'LOST', '2024-07-08T10:07:01.538126', 'earned 25 XP'),
(7, 4, 'WON', '2024-07-11T06:00:43.865712', 'earned 100 XP, earned 80 money'),
(8, 3, 'LOST', '2024-07-11T06:00:43.865905', 'earned 25 XP'),
(9, 4, 'WON', '2024-07-11T06:00:43.874054', 'earned 100 XP, earned 80 money'),
(10, 3, 'LOST', '2024-07-11T06:00:43.874082', 'earned 25 XP'),
(11, 4, 'WON', '2024-07-11T06:00:43.879550', 'earned 100 XP, earned 80 money'),
(12, 3, 'LOST', '2024-07-11T06:00:43.879590', 'earned 25 XP'),
(13, 4, 'WON', '2024-07-11T06:00:43.885386', 'earned 100 XP, earned 43 money'),
(14, 3, 'LOST', '2024-07-11T06:00:43.885435', 'earned 25 XP'),
(15, 4, 'WON', '2024-07-11T06:00:43.892345', 'earned 100 XP, earned 43 money'),
(16, 3, 'LOST', '2024-07-11T06:00:43.892384', 'earned 50 XP'),
(17, 4, 'WON', '2024-07-11T06:00:43.897592', 'earned 100 XP, earned 43 money'),
(18, 3, 'LOST', '2024-07-11T06:00:43.897650', 'earned 50 XP'),
(19, 4, 'WON', '2024-07-11T06:00:43.901744', 'earned 100 XP, earned 6 money'),
(20, 3, 'LOST', '2024-07-11T06:00:43.901780', 'earned 50 XP'),
(21, 4, 'WON', '2024-07-11T06:00:43.907214', 'earned 100 XP, earned 6 money'),
(22, 3, 'LOST', '2024-07-11T06:00:43.907251', 'earned 50 XP'),
(23, 4, 'WON', '2024-07-11T06:00:43.913789', 'earned 100 XP, earned 0 money'),
(24, 3, 'LOST', '2024-07-11T06:00:43.913819', 'earned 50 XP'),
(25, 4, 'LOST', '2024-07-11T06:00:43.920741', 'earned 50 XP'),
(26, 3, 'WON', '2024-07-11T06:00:43.920859', 'earned 200 XP, earned 0 money'),
(27, 4, 'WON', '2024-07-11T06:00:43.929031', 'earned 200 XP, earned 0 money'),
(28, 3, 'LOST', '2024-07-11T06:00:43.929073', 'earned 50 XP'),
(29, 4, 'LOST', '2024-07-11T06:00:43.935324', 'earned 50 XP'),
(30, 3, 'WON', '2024-07-11T06:00:43.935371', 'earned 200 XP, earned 0 money'),
(31, 4, 'WON', '2024-07-11T06:00:43.943017', 'earned 200 XP, earned 0 money'),
(32, 3, 'LOST', '2024-07-11T06:00:43.943059', 'earned 50 XP'),
(33, 4, 'LOST', '2024-07-11T06:00:43.949343', 'earned 50 XP'),
(34, 3, 'WON', '2024-07-11T06:00:43.949395', 'earned 200 XP, earned 0 money'),
(35, 4, 'WON', '2024-07-11T06:00:43.958785', 'earned 200 XP, earned 0 money'),
(36, 3, 'LOST', '2024-07-11T06:00:43.958849', 'earned 100 XP'),
(37, 4, 'LOST', '2024-07-11T06:00:43.964751', 'earned 100 XP'),
(38, 3, 'WON', '2024-07-11T06:00:43.964798', 'earned 400 XP, earned 0 money'),
(39, 4, 'WON', '2024-07-11T16:53:23.367607', 'earned 400 XP, earned 10 money'),
(40, 3, 'LOST', '2024-07-11T16:53:23.367763', 'earned 100 XP, lost 10 money'),
(41, 4, 'WON', '2024-07-11T16:53:23.474247', 'earned 400 XP, earned 246 money'),
(42, 3, 'LOST', '2024-07-11T16:53:23.474483', 'earned 100 XP'),
(43, 4, 'WON', '2024-07-11T16:53:23.581360', 'earned 400 XP, earned 246 money'),
(44, 3, 'LOST', '2024-07-11T16:53:23.581405', 'earned 100 XP'),
(45, 4, 'WON', '2024-07-11T16:53:23.688244', 'earned 400 XP, earned 246 money'),
(46, 3, 'LOST', '2024-07-11T16:53:23.688303', 'earned 200 XP'),
(47, 4, 'WON', '2024-07-11T16:53:23.795360', 'earned 400 XP, earned 246 money'),
(48, 3, 'LOST', '2024-07-11T16:53:23.795413', 'earned 200 XP'),
(49, 4, 'WON', '2024-07-11T16:53:23.901699', 'earned 400 XP, earned 246 money'),
(50, 3, 'LOST', '2024-07-11T16:53:23.901762', 'earned 200 XP'),
(51, 4, 'WON', '2024-07-11T16:53:24.007350', 'earned 400 XP, earned 246 money'),
(52, 3, 'LOST', '2024-07-11T16:53:24.007408', 'earned 200 XP'),
(53, 4, 'WON', '2024-07-11T16:53:24.118714', 'earned 400 XP, earned 216 money'),
(54, 3, 'LOST', '2024-07-11T16:53:24.118779', 'earned 200 XP'),
(55, 4, 'WON', '2024-07-11T16:53:24.224514', 'earned 800 XP, earned 216 money'),
(56, 3, 'LOST', '2024-07-11T16:53:24.224576', 'earned 400 XP'),
(57, 4, 'WON', '2024-07-11T16:53:24.329955', 'earned 800 XP, earned 216 money'),
(58, 3, 'LOST', '2024-07-11T16:53:24.330060', 'earned 400 XP'),
(59, 5, 'WON', '2024-07-12T08:46:05.400439', 'earned 100 XP, earned 50 money'),
(60, 6, 'LOST', '2024-07-12T08:46:05.400923', 'earned 25 XP, lost 50 money'),
(61, 6, 'WON', '2024-07-12T09:02:58.582660', 'earned 100 XP, earned 1580 money'),
(62, 3, 'LOST', '2024-07-12T09:02:58.582763', 'earned 400 XP'),
(63, 6, 'WON', '2024-07-12T09:02:58.586263', 'earned 200 XP, earned 1580 money'),
(64, 3, 'LOST', '2024-07-12T09:02:58.586309', 'earned 400 XP'),
(65, 6, 'WON', '2024-07-12T09:02:58.588277', 'earned 200 XP, earned 1562 money'),
(66, 3, 'LOST', '2024-07-12T09:02:58.588298', 'earned 400 XP'),
(67, 6, 'WON', '2024-07-12T09:02:58.590379', 'earned 400 XP, earned 1544 money'),
(68, 3, 'LOST', '2024-07-12T09:02:58.590396', 'earned 400 XP'),
(69, 6, 'WON', '2024-07-12T09:02:58.595821', 'earned 400 XP, earned 1526 money'),
(70, 3, 'LOST', '2024-07-12T09:02:58.595841', 'earned 400 XP'),
(71, 6, 'WON', '2024-07-12T09:02:58.598513', 'earned 400 XP, earned 1506 money'),
(72, 3, 'LOST', '2024-07-12T09:02:58.598548', 'earned 400 XP'),
(73, 6, 'WON', '2024-07-12T09:02:58.600771', 'earned 800 XP, earned 1486 money'),
(74, 3, 'LOST', '2024-07-12T09:02:58.600788', 'earned 800 XP'),
(75, 6, 'WON', '2024-07-12T09:02:58.603147', 'earned 800 XP, earned 1466 money'),
(76, 3, 'LOST', '2024-07-12T09:02:58.603163', 'earned 800 XP'),
(77, 6, 'WON', '2024-07-12T09:02:58.605406', 'earned 800 XP, earned 1466 money'),
(78, 3, 'LOST', '2024-07-12T09:02:58.605422', 'earned 800 XP'),
(79, 6, 'WON', '2024-07-12T09:02:58.608205', 'earned 1600 XP, earned 1466 money'),
(80, 3, 'LOST', '2024-07-12T09:02:58.608229', 'earned 800 XP'),
(81, 6, 'WON', '2024-07-12T09:02:58.610922', 'earned 1600 XP, earned 1441 money'),
(82, 3, 'LOST', '2024-07-12T09:02:58.610947', 'earned 1600 XP'),
(83, 6, 'WON', '2024-07-12T09:02:58.613940', 'earned 3200 XP, earned 1441 money'),
(84, 3, 'LOST', '2024-07-12T09:02:58.614010', 'earned 1600 XP'),
(85, 6, 'WON', '2024-07-12T09:02:58.616485', 'earned 6400 XP, earned 1441 money'),
(86, 3, 'LOST', '2024-07-12T09:02:58.616502', 'earned 3200 XP');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `nickname` varchar(32) NOT NULL,
  `email` varchar(64) NOT NULL,
  `passwordRecoveryQuestion` int(11) NOT NULL,
  `passwordRecoveryAns` varchar(32) NOT NULL,
  `level` int(11) NOT NULL,
  `maxHP` int(11) NOT NULL,
  `XP` int(11) NOT NULL,
  `money` int(11) NOT NULL,
  `CardLevel` varchar(4096) NOT NULL,
  `records` varchar(4096) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `username`, `password`, `nickname`, `email`, `passwordRecoveryQuestion`, `passwordRecoveryAns`, `level`, `maxHP`, `XP`, `money`, `CardLevel`, `records`) VALUES
(1, 'sina', '1234', 'sina sina', 's@s.com', 2, 'red', 1, 100, 0, 1000, '1 2', '1 2 3 4'),
(2, 'ehsan', '1111', 'eh', 'eak@eak.eak', 1, 'arab', 1, 100, 0, 0, '1 2 2 3', ''),
(3, 'sinaTest1', ')QdO8fd6iC', 'sinaaa', 'email@c.com', 3, 'cat', 8, 12800, 2700, 40920, '4 1 1000 0 3 1 1009 0 8 1 1008 0 10 1 13 1 17 1 2 1 1001 0 1007 0 1002 0 1006 0 1010 0 1003 0 18 1 6 1 1005 0 19 1', '6 7 9 11 13 15 17 19 21 23 25 27 29 31 33 35 37 39 41 43 45 47 49 51 53 55 57 61 63 65 67 69 71 73 75 77 79 81 83 85'),
(4, 'ehsanTest1', '12345Ss@', 'ehsanaki', 'a@a.a', 1, 'mammad', 4, 800, 750, 5906, '5 1 9 1 6 1 12 1 4 1 19 1 1009 0 20 1 1001 0 15 1 14 1 10 1 1003 0 18 1 13 1 1006 0 11 1 1010 0 1000 0 1007 0', '5 8 10 12 14 16 18 20 22 24 26 28 30 32 34 36 38 40 42 44 46 48 50 52 54 56 58'),
(5, 'sinaTest', '12345Ss@', 'sina', 'email@c.com', 3, 'cat', 1, 100, 25, 50, '14 1 1001 0 1009 0 8 1 9 1 18 1 1000 0 1010 0 20 1 17 1 15 1 3 1 11 1 1006 0 1008 0 1003 0 1007 0 12 1 5 1 13 1', '60'),
(6, 'ehsanTest', '12345Ss@', 'ehsn', 'a@a.a', 1, 'mammad', 7, 6400, 1600, 11123, '1000 0 14 1 11 1 2 1 10 1 1006 0 1009 0 20 1 1004 0 7 1 16 1 13 1 1008 0 1007 0 17 1 15 1 1001 0 8 1 4 1 1002 0', '59 62 64 66 68 70 72 74 76 78 80 82 84 86'),
(7, 'sina123', '$Fmd02a9nS', 'sina', 'a@a.com', 3, '', 1, 100, 0, 100, '', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `gamehistory`
--
ALTER TABLE `gamehistory`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
