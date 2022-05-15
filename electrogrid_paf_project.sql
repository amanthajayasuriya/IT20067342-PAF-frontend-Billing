-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 03:19 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid_paf_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `billing_tb`
--

CREATE TABLE `billing_tb` (
  `billID` int(8) NOT NULL,
  `AccountNumber` int(8) NOT NULL,
  `name` varchar(50) NOT NULL,
  `UnitCount` float NOT NULL,
  `month` varchar(20) NOT NULL,
  `billAmount` float(10,2) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `billing_tb`
--

INSERT INTO `billing_tb` (`billID`, `AccountNumber`, `name`, `UnitCount`, `month`, `billAmount`, `date`) VALUES
(2, 10001, 'ajith', 156, 'jaaa', 3459.15, '2022-05-13'),
(31, 10002, 'shehan', 27, 'january', 211.95, '2022-05-13'),
(34, 10003, 'sarath', 122, 'january', 2371.15, '2022-05-13');

-- --------------------------------------------------------

--
-- Table structure for table `usertb`
--

CREATE TABLE `usertb` (
  `UID` int(9) NOT NULL,
  `userName` varchar(10) NOT NULL,
  `password` varchar(9) NOT NULL,
  `email` varchar(30) NOT NULL,
  `accountNo` int(9) NOT NULL,
  `createdDate` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `usertb`
--

INSERT INTO `usertb` (`UID`, `userName`, `password`, `email`, `accountNo`, `createdDate`) VALUES
(1, 'amantha', 'amajay000', 'amantha@gmail.com', 123456789, '2022-04-25 22:25:11'),
(2, 'shehanFdo', 'shehanFdo', 'shehan@gmail.com', 10002, '2022-04-26 05:21:37'),
(3, 'AjithJay', 'ajithJay9', 'Ajith@gmail.com', 10001, '2022-04-26 05:22:20'),
(4, 'sarathsil', 'sarath000', 'sarath@gmail.com', 10003, '2022-04-26 05:23:49'),
(5, 'yehangune', 'yehan12', 'yehan@gmail.com', 10008, '2022-04-29 18:00:00'),
(9, 'sumuduFdo', 'sumudu99', 'sumudu99@gmail.com', 10019, '2022-05-03 10:11:57');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `billing_tb`
--
ALTER TABLE `billing_tb`
  ADD PRIMARY KEY (`billID`);

--
-- Indexes for table `usertb`
--
ALTER TABLE `usertb`
  ADD PRIMARY KEY (`UID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `billing_tb`
--
ALTER TABLE `billing_tb`
  MODIFY `billID` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `usertb`
--
ALTER TABLE `usertb`
  MODIFY `UID` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
