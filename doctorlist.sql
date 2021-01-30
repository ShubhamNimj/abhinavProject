-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 30, 2021 at 10:53 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `abhinav`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctorlist`
--

CREATE TABLE `doctorlist` (
  `id` int(11) NOT NULL,
  `created` date DEFAULT NULL,
  `docName` varchar(255) DEFAULT NULL,
  `doc_avail_days` varchar(255) DEFAULT NULL,
  `modified` date DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctorlist`
--

INSERT INTO `doctorlist` (`id`, `created`, `docName`, `doc_avail_days`, `modified`, `status`) VALUES
(1, '2021-01-29', 'Shubham Nimje', 'Mon,Tue,Wed', '2021-01-29', 1),
(2, '2021-01-30', 'Rajesh Desai', 'Thu,Fei,Sat', '2021-01-30', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctorlist`
--
ALTER TABLE `doctorlist`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doctorlist`
--
ALTER TABLE `doctorlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
