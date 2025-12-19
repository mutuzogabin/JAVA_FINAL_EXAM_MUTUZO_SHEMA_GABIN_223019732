-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 19, 2025 at 07:47 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `securitydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `accesscontrol`
--

CREATE TABLE `accesscontrol` (
  `ID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `Attribute1` varchar(50) DEFAULT NULL,
  `Attribute2` varchar(50) DEFAULT NULL,
  `Attribute3` varchar(50) DEFAULT NULL,
  `CreatedAt` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accesscontrol`
--

INSERT INTO `accesscontrol` (`ID`, `UserID`, `Attribute1`, `Attribute2`, `Attribute3`, `CreatedAt`) VALUES
(4, 2, '34', 'fds', 'sd', '2025-10-22 12:24:28');

-- --------------------------------------------------------

--
-- Table structure for table `alert`
--

CREATE TABLE `alert` (
  `AlertID` int(11) NOT NULL,
  `Description` text DEFAULT NULL,
  `Date` timestamp NOT NULL DEFAULT current_timestamp(),
  `Status` varchar(50) DEFAULT NULL,
  `Remarks` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `alert`
--

INSERT INTO `alert` (`AlertID`, `Description`, `Date`, `Status`, `Remarks`) VALUES
(1, 'ghdh', '2025-10-22 10:03:41', 'yes', 'dhdhd'),
(2, 'hdhd', '2025-10-22 10:04:33', 'no', 'dhdjd');

-- --------------------------------------------------------

--
-- Table structure for table `camera`
--

CREATE TABLE `camera` (
  `ID` int(11) NOT NULL,
  `AccessControlID` int(11) DEFAULT NULL,
  `Attribute1` varchar(50) DEFAULT NULL,
  `Attribute2` varchar(50) DEFAULT NULL,
  `Attribute3` varchar(50) DEFAULT NULL,
  `CreatedAt` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cameras`
--

CREATE TABLE `cameras` (
  `CameraID` int(11) NOT NULL,
  `Location` varchar(100) DEFAULT NULL,
  `IPAddress` varchar(50) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cameras`
--

INSERT INTO `cameras` (`CameraID`, `Location`, `IPAddress`, `Status`) VALUES
(1, 'Main Entrance', '192.168.0.101', 'Active'),
(2, 'Parking Lot', '192.168.0.102', 'Active'),
(3, 'Server Room', '192.168.0.103', 'Inactive');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `EventID` int(11) NOT NULL,
  `CameraID` int(11) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  `Remarks` varchar(255) DEFAULT NULL,
  `EventDate` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`EventID`, `CameraID`, `Description`, `Status`, `Remarks`, `EventDate`) VALUES
(1, 1, 'Unauthorized access detected', 'closed', 'resloved', '2025-11-10 10:17:01'),
(2, 2, 'Motion detected at night', 'Closed', 'Resolved by guard', '2025-11-10 10:17:01'),
(3, 3, 'Camera offline', 'Open', 'Maintenance required', '2025-11-10 10:17:01');

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `EventID` int(11) NOT NULL,
  `CameraID` int(11) DEFAULT NULL,
  `Description` text DEFAULT NULL,
  `Date` timestamp NOT NULL DEFAULT current_timestamp(),
  `Status` varchar(50) DEFAULT NULL,
  `Remarks` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `event_alert`
--

CREATE TABLE `event_alert` (
  `EventID` int(11) NOT NULL,
  `AlertID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `LogID` int(11) NOT NULL,
  `AlertID` int(11) DEFAULT NULL,
  `Attribute1` varchar(50) DEFAULT NULL,
  `Attribute2` varchar(50) DEFAULT NULL,
  `Attribute3` varchar(50) DEFAULT NULL,
  `CreatedAt` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `PasswordHash` varchar(255) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `FullName` varchar(100) DEFAULT NULL,
  `Role` varchar(50) DEFAULT NULL,
  `CreatedAt` timestamp NOT NULL DEFAULT current_timestamp(),
  `LastLogin` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserID`, `Username`, `PasswordHash`, `Email`, `FullName`, `Role`, `CreatedAt`, `LastLogin`) VALUES
(1, 'admin', 'admin123', 'admin@example.com', 'System Administrator', 'Admin', '2025-10-22 10:17:15', NULL),
(2, 'security1', 'pass123', 'security1@example.com', 'John Doe', 'Security', '2025-10-22 10:17:15', NULL),
(3, 'security2', 'pass456', 'security2@example.com', 'Jane Smith', 'Security', '2025-10-22 10:17:15', NULL),
(4, 'viewer', 'viewer123', 'viewer@example.com', 'Guest User', 'Viewer', '2025-10-22 10:17:15', NULL),
(5, 'GIANT', '12121', 'olaa@gmail.com', 'AFRICAN GIANT', 'Security', '2025-11-10 08:25:07', NULL),
(6, 'DIORR', '12', 'd@gmail.com', 'Didros D', 'Viewer', '2025-11-10 09:20:25', NULL),
(7, 'shema', 'shema', NULL, NULL, 'Security', '2025-11-11 15:17:49', NULL),
(9, 'shemaa', '11', 'shema@gmail.com', 'Shema Kethan', 'Viewer', '2025-11-11 15:22:15', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accesscontrol`
--
ALTER TABLE `accesscontrol`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indexes for table `alert`
--
ALTER TABLE `alert`
  ADD PRIMARY KEY (`AlertID`);

--
-- Indexes for table `camera`
--
ALTER TABLE `camera`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `AccessControlID` (`AccessControlID`);

--
-- Indexes for table `cameras`
--
ALTER TABLE `cameras`
  ADD PRIMARY KEY (`CameraID`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`EventID`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`EventID`),
  ADD KEY `CameraID` (`CameraID`);

--
-- Indexes for table `event_alert`
--
ALTER TABLE `event_alert`
  ADD PRIMARY KEY (`EventID`,`AlertID`),
  ADD KEY `AlertID` (`AlertID`);

--
-- Indexes for table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`LogID`),
  ADD KEY `AlertID` (`AlertID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accesscontrol`
--
ALTER TABLE `accesscontrol`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `alert`
--
ALTER TABLE `alert`
  MODIFY `AlertID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `camera`
--
ALTER TABLE `camera`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `cameras`
--
ALTER TABLE `cameras`
  MODIFY `CameraID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `EventID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
  MODIFY `EventID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `log`
--
ALTER TABLE `log`
  MODIFY `LogID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accesscontrol`
--
ALTER TABLE `accesscontrol`
  ADD CONSTRAINT `accesscontrol_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`);

--
-- Constraints for table `camera`
--
ALTER TABLE `camera`
  ADD CONSTRAINT `camera_ibfk_1` FOREIGN KEY (`AccessControlID`) REFERENCES `accesscontrol` (`ID`);

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `events_ibfk_1` FOREIGN KEY (`CameraID`) REFERENCES `camera` (`ID`);

--
-- Constraints for table `event_alert`
--
ALTER TABLE `event_alert`
  ADD CONSTRAINT `event_alert_ibfk_1` FOREIGN KEY (`EventID`) REFERENCES `events` (`EventID`),
  ADD CONSTRAINT `event_alert_ibfk_2` FOREIGN KEY (`AlertID`) REFERENCES `alert` (`AlertID`);

--
-- Constraints for table `log`
--
ALTER TABLE `log`
  ADD CONSTRAINT `log_ibfk_1` FOREIGN KEY (`AlertID`) REFERENCES `alert` (`AlertID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
