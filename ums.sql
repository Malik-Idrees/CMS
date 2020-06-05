-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2020 at 10:43 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.1.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ums`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `studentid` int(11) NOT NULL,
  `courseid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `comment` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`studentid`, `courseid`, `comment`) VALUES
(1, 'CS-212', 'AWESOME COURSE !'),
(1, 'CS-220', 'WELL PLANNED COURSE !'),
(1, 'CS-250', 'Hahaha'),
(1, 'EE-212', 'hhh'),
(1, 'HU-107', 'good'),
(1, 'HU-210', 'worst course ever, in the history of all courses'),
(1, 'MATH-361', 'bad course'),
(1, 'SE-200', 'Good course'),
(2, 'CS-212', 'My FAVOURITE !'),
(2, 'CS-220', 'FOLLOWING MARKET TRENDS! !'),
(3, 'CS-220', 'RELATED TO REAL WORLD !'),
(100, 'CS-212', 'Bad course'),
(1, 'EE-215', 'Good course');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `id` varchar(30) NOT NULL,
  `location` varchar(100) DEFAULT NULL,
  `start_at` time DEFAULT NULL,
  `end_at` time DEFAULT NULL,
  `semester` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `location`, `start_at`, `end_at`, `semester`) VALUES
('CS-100', 'Cambridge Room 9', '08:00:00', '13:00:00', 8),
('CS-114', 'C Block room 2', '08:00:00', '14:00:00', 6),
('CS-212', 'James Hall Room 5', '11:00:00', '13:00:00', 2),
('CS-220', 'Venice Hall Room 7', '09:00:00', '12:00:00', 8),
('CS-250', 'Auditorium Conference Room', '09:00:00', '13:00:00', 5),
('ECO-100', 'B Block room 5', '11:00:00', '12:00:00', 4),
('EE-212', 'Tower Block G.08', '09:00:00', '12:00:00', 6),
('EE-215', 'B Block room 1', '10:00:00', '14:00:00', 2),
('HU-107', 'David Complex Room G.02', '09:00:00', '12:00:00', 3),
('HU-109', 'C Block room 9', '09:00:00', '14:00:00', 3),
('HU-21', 'Mellor Seminor Room 2', '10:00:00', '14:00:00', 1),
('MATH-232', 'Tower Block G.08', '10:00:00', '12:00:00', 6),
('MATH-361', 'Dental Blue', '10:00:00', '12:00:00', 7),
('MGT-271', 'B Block room 7', '11:00:00', '12:00:00', 8),
('OTM-455', 'C Block room 2', '09:00:00', '12:00:00', 6),
('PHY-102', 'Venice Hall Room 7', '10:00:00', '14:00:00', 1),
('SE-200', 'James Hall Room 5', '11:00:00', '14:00:00', 4);

-- --------------------------------------------------------

--
-- Table structure for table `coursetype`
--

CREATE TABLE `coursetype` (
  `dept` varchar(50) NOT NULL,
  `courses` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `coursetype`
--

INSERT INTO `coursetype` (`dept`, `courses`) VALUES
('CS', 'CS-100'),
('CS', 'CS-114'),
('CS', 'CS-212'),
('CS', 'CS-220'),
('CS', 'CS-250'),
('CS', 'ECO-100'),
('CS', 'EE-212'),
('CS', 'EE-215'),
('CS', 'HU-107'),
('CS', 'HU-109'),
('CS', 'HU-21'),
('CS', 'MATH-232'),
('CS', 'MATH-361'),
('CS', 'MGT-271'),
('CS', 'OTM-455'),
('CS', 'PHY-102'),
('CS', 'SE-200'),
('EE', 'CS-100'),
('EE', 'CS-114'),
('EE', 'CS-212'),
('EE', 'CS-220'),
('EE', 'CS-250'),
('EE', 'ECO-100'),
('EE', 'EE-212'),
('EE', 'EE-215'),
('EE', 'HU-107'),
('EE', 'HU-109'),
('EE', 'HU-21'),
('EE', 'MATH-361'),
('EE', 'MATH?232'),
('EE', 'MGT-271'),
('EE', 'OTM?455'),
('EE', 'PHY?102'),
('EE', 'SE-200'),
('SE', 'CS-100'),
('SE', 'CS-212'),
('SE', 'CS-220'),
('SE', 'CS-250'),
('SE', 'CS?114'),
('SE', 'ECO?130'),
('SE', 'EE-212'),
('SE', 'EE?215'),
('SE', 'HU-107'),
('SE', 'HU-109'),
('SE', 'HU-21'),
('SE', 'MATH-361'),
('SE', 'MATH?232'),
('SE', 'MGT-271'),
('SE', 'OTM?455'),
('SE', 'PHY?102'),
('SE', 'SE-200'),
('SS', 'CS-100'),
('SS', 'CS-212'),
('SS', 'CS-220'),
('SS', 'CS-250'),
('SS', 'CS?114'),
('SS', 'ECO?130'),
('SS', 'EE-212'),
('SS', 'EE?215'),
('SS', 'HU-107'),
('SS', 'HU-109'),
('SS', 'HU-21'),
('SS', 'MATH-361'),
('SS', 'MATH?232'),
('SS', 'MGT-271'),
('SS', 'OTM?455'),
('SS', 'PHY?102'),
('SS', 'SE-200');

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE `friends` (
  `studentid1` int(11) NOT NULL,
  `studentid2` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `friends`
--

INSERT INTO `friends` (`studentid1`, `studentid2`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(2, 6),
(2, 7),
(2, 8),
(2, 9),
(2, 10),
(3, 11),
(3, 12),
(3, 13),
(3, 14),
(3, 15),
(4, 16),
(4, 17),
(4, 18),
(4, 19),
(4, 20),
(5, 2),
(5, 21),
(5, 22),
(5, 23),
(5, 24),
(5, 25),
(6, 26),
(6, 27),
(6, 28),
(6, 29),
(6, 30),
(7, 1),
(7, 2),
(7, 3),
(7, 4),
(7, 5),
(8, 6),
(8, 7),
(8, 8),
(8, 9),
(8, 10),
(9, 11),
(9, 12),
(9, 13),
(9, 14),
(9, 15),
(10, 16),
(10, 17),
(10, 18),
(10, 19),
(10, 20),
(11, 21),
(11, 22),
(11, 23),
(11, 24),
(11, 25),
(12, 26),
(12, 27),
(12, 28),
(12, 29),
(12, 30),
(13, 1),
(13, 2),
(13, 3),
(13, 4),
(13, 5),
(14, 6),
(14, 7),
(14, 8),
(14, 9),
(14, 10),
(15, 11),
(15, 12),
(15, 13),
(15, 14),
(15, 15),
(16, 16),
(16, 17),
(16, 18),
(16, 19),
(16, 20),
(17, 21),
(17, 22),
(17, 23),
(17, 24),
(17, 25),
(18, 26),
(18, 27),
(18, 28),
(18, 29),
(18, 30),
(19, 1),
(19, 2),
(19, 3),
(19, 4),
(19, 5),
(20, 6),
(20, 7),
(20, 8),
(20, 9),
(20, 10);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  `password` int(11) DEFAULT NULL,
  `major` varchar(50) DEFAULT NULL,
  `minor` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `fname`, `lname`, `age`, `semester`, `password`, `major`, `minor`, `email`) VALUES
(1, 'Jamal', 'Irma', 22, 2, 1595477, 'CS', 'EE', 'quis.pede@DuisgravidaPraesent.edu'),
(2, 'Jasmine', 'Amanda', 23, 7, 895682, 'CS', 'EE', 'semper@etnunc.co.uk'),
(3, 'Leigh', 'Aladdin', 24, 3, 4111924, 'CS', 'EE', 'In.lorem.Donec@Nullatincidunt.edu'),
(4, 'Finn', 'Gay', 22, 1, 440125, 'CS', 'SS', 'iaculis@vitaesodalesnisi.org'),
(5, 'Erasmus', 'Mia', 30, 6, 7889041, 'CS', 'SS', 'et.netus@ligulaeuenim.ca'),
(6, 'Mark', 'Austin', 24, 2, 6226889, 'SE', 'SS', 'magnis.dis.parturient@pede.com'),
(7, 'Indigo', 'Merritt', 24, 1, 1869009, 'SE', 'EE', 'velit.dui@lectusNullam.co.uk'),
(8, 'Kevyn', 'Mark', 30, 8, 3164620, 'SE', 'EE', 'ipsum@idante.co.uk'),
(9, 'Minerva', 'Cyrus', 29, 8, 585630, 'SE', 'EE', 'arcu.Sed@montesnasceturridiculus.com'),
(10, 'Jelani', 'Chase', 30, 1, 7378976, 'SE', 'SS', 'In.tincidunt@in.com'),
(11, 'Leigh', 'Jolie', 24, 5, 7172926, 'CS', 'SS', 'pretium@dui.net'),
(12, 'Jael', 'Jasper', 21, 4, 240949, 'CS', 'SS', 'felis.Donec.tempor@maurisaliquam.co.uk'),
(13, 'Fay', 'Ross', 26, 5, 7403041, 'CS', 'EE', 'odio.Phasellus.at@sedestNunc.org'),
(14, 'Deacon', 'Kameko', 20, 5, 5373457, 'CS', 'EE', 'natoque.penatibus.et@porttitor.com'),
(15, 'Fletcher', 'Flavia', 21, 1, 7451979, 'CS', 'EE', 'pede.nec@Fuscefermentumfermentum.edu'),
(16, 'Kiara', 'Doris', 20, 5, 5133607, 'SE', 'SS', 'Praesent.luctus@loremauctor.ca'),
(17, 'Eugenia', 'Ryder', 21, 5, 6295528, 'SE', 'SS', 'quis.pede@sodalesMaurisblandit.org'),
(18, 'Keith', 'Haviva', 26, 6, 669283, 'SE', 'SS', 'fringilla@necenim.edu'),
(19, 'Cairo', 'Dolan', 27, 8, 3965972, 'SE', 'EE', 'enim@velarcu.co.uk'),
(20, 'Bree', 'Azalia', 21, 8, 5813082, 'SE', 'EE', 'lorem.vitae.odio@NullamenimSed.edu'),
(21, 'Madeson', 'Ronan', 26, 4, 5200520, 'CS', 'EE', 'Pellentesque.ultricies@dolor.ca'),
(22, 'Claudia', 'Dominic', 29, 1, 7902270, 'CS', 'SS', 'nonummy.ultricies@tinciduntvehicularisus.net'),
(23, 'Ulric', 'Basia', 26, 7, 4987889, 'CS', 'SS', 'Cum@leoinlobortis.ca'),
(24, 'Chaney', 'Glenna', 27, 1, 7169509, 'CS', 'SS', 'egestas@sodalesnisimagna.co.uk'),
(25, 'Isaac', 'Jacqueline', 21, 7, 3676345, 'CS', 'EE', 'in.magna@Nunccommodo.ca'),
(26, 'Leslie', 'Maxine', 25, 1, 6408136, 'SE', 'EE', 'conubia.nostra.per@Integeraliquamadipiscing.edu'),
(27, 'Emma', 'Inga', 29, 6, 127981, 'SE', 'EE', 'sem@nec.ca'),
(28, 'Hedy', 'Tana', 28, 4, 4936418, 'SE', 'SS', 'amet@egetmagna.ca'),
(29, 'Amanda', 'Callum', 23, 2, 2553457, 'SE', 'SS', 'natoque@massaQuisque.co.uk'),
(30, 'Robin', 'Gloria', 20, 2, 3808529, 'SE', 'SS', 'eu@aceleifendvitae.net'),
(31, 'Wylie', 'Orlando', 21, 4, 2950217, 'CS', 'EE', 'nulla@mattisCraseget.co.uk'),
(32, 'Keith', 'Conan', 22, 3, 7705755, 'CS', 'EE', 'consequat@malesuadafamesac.com'),
(33, 'Connor', 'Joan', 21, 4, 5236191, 'CS', 'EE', 'ornare@duinectempus.ca'),
(34, 'Kennedy', 'Frances', 23, 3, 5389811, 'CS', 'SS', 'ipsum@elitfermentumrisus.co.uk'),
(35, 'Maile', 'Ainsley', 21, 4, 4813812, 'CS', 'SS', 'eget@ut.ca'),
(36, 'Alexander', 'Aladdin', 21, 6, 4427531, 'SE', 'SS', 'facilisis@et.com'),
(37, 'Lavinia', 'Hillary', 24, 3, 5682202, 'SE', 'EE', 'sed.dictum.eleifend@arcuvel.ca'),
(38, 'Donna', 'Chadwick', 30, 5, 3256316, 'SE', 'EE', 'Ut@metus.co.uk'),
(39, 'Eaton', 'Blake', 25, 3, 3248330, 'SE', 'EE', 'eget.dictum@Namporttitor.org'),
(40, 'Jillian', 'Nasim', 20, 3, 2952918, 'SE', 'SS', 'nunc@feugiattelluslorem.ca'),
(41, 'Eagan', 'Rooney', 26, 8, 5724183, 'CS', 'SS', 'porttitor@Nuncquisarcu.ca'),
(42, 'Dominic', 'Hayfa', 29, 4, 4350870, 'CS', 'SS', 'nisi.nibh.lacinia@mauris.net'),
(43, 'Hedwig', 'Farrah', 30, 1, 3592051, 'CS', 'EE', 'non@enimdiam.co.uk'),
(44, 'Yuli', 'Dahlia', 25, 8, 7374196, 'CS', 'EE', 'magna@ametultricies.co.uk'),
(45, 'Brennan', 'Skyler', 30, 2, 6219211, 'CS', 'EE', 'eu.eleifend@loremsitamet.ca'),
(46, 'Whoopi', 'Lysandra', 24, 2, 87596, 'SE', 'SS', 'amet.orci.Ut@maurisIntegersem.org'),
(47, 'Palmer', 'Scarlet', 22, 5, 4884781, 'SE', 'SS', 'ipsum@aliquetodio.com'),
(48, 'Wyatt', 'Jael', 26, 8, 3544164, 'SE', 'SS', 'diam@euarcu.co.uk'),
(49, 'Caleb', 'Penelope', 21, 3, 3165579, 'SE', 'EE', 'dolor.dapibus@mauris.edu'),
(50, 'Demetrius', 'Michael', 26, 5, 2278094, 'SE', 'EE', 'velit.eget@magnisdisparturient.ca'),
(51, 'Ila', 'Julian', 26, 3, 4251081, 'CS', 'EE', 'quis@facilisisvitaeorci.ca'),
(52, 'Drew', 'Myra', 28, 8, 2368745, 'CS', 'SS', 'penatibus@eleifend.com'),
(53, 'Breanna', 'Charissa', 30, 4, 7269436, 'CS', 'SS', 'ornare.elit@augueutlacus.edu'),
(54, 'Zane', 'Jonah', 22, 5, 2570621, 'CS', 'SS', 'accumsan.interdum@idsapienCras.com'),
(55, 'Molly', 'Jane', 27, 2, 7740898, 'CS', 'EE', 'Nulla.eu@sitametrisus.edu'),
(56, 'Kelsie', 'Lewis', 25, 8, 3623943, 'SE', 'EE', 'in@dolornonummyac.edu'),
(57, 'Jared', 'Alfonso', 29, 5, 2473249, 'SE', 'EE', 'ut.eros.non@pretiumaliquetmetus.net'),
(58, 'Edan', 'Ignatius', 26, 1, 6869498, 'SE', 'SS', 'vulputate@sitamet.net'),
(59, 'Plato', 'Kelly', 25, 3, 7676545, 'SE', 'SS', 'fringilla.mi@consectetuermaurisid.org'),
(60, 'Clementine', 'Samson', 27, 3, 7151391, 'SE', 'SS', 'nunc@eudui.edu'),
(61, 'Karleigh', 'Lionel', 24, 6, 6269974, 'CS', 'EE', 'Vivamus@anteblandit.org'),
(62, 'Indigo', 'Bert', 20, 8, 4921790, 'CS', 'EE', 'Duis@loremfringilla.com'),
(63, 'Xena', 'Blaze', 25, 1, 4395565, 'CS', 'EE', 'enim.diam.vel@condimentumDonec.com'),
(64, 'Hadley', 'Vielka', 23, 4, 1191002, 'CS', 'SS', 'imperdiet.nec@vulputateduinec.com'),
(65, 'Ray', 'Rhiannon', 23, 5, 6603650, 'CS', 'SS', 'convallis.dolor@Namtempor.co.uk'),
(66, 'Joshua', 'Denton', 26, 1, 7315266, 'SE', 'SS', 'nulla@veliteget.org'),
(67, 'Adria', 'Dorian', 28, 4, 1626680, 'SE', 'EE', 'egestas@sit.com'),
(68, 'Shaeleigh', 'Macon', 24, 3, 3330678, 'SE', 'EE', 'rutrum.magna.Cras@ultricessit.co.uk'),
(69, 'Uma', 'Quinlan', 25, 8, 736092, 'SE', 'EE', 'velit.dui.semper@lorem.ca'),
(70, 'Yoshi', 'Gavin', 21, 5, 428171, 'SE', 'SS', 'Nunc.quis@Duisatlacus.co.uk'),
(71, 'Emily', 'Ulysses', 29, 5, 3910495, 'CS', 'SS', 'elit.pharetra.ut@ipsum.ca'),
(72, 'Aladdin', 'Sybil', 23, 3, 3440028, 'CS', 'SS', 'eget@venenatisamagna.edu'),
(73, 'Avram', 'Dawn', 25, 2, 1002218, 'CS', 'EE', 'purus.ac.tellus@duilectusrutrum.co.uk'),
(74, 'Kasper', 'Ethan', 22, 1, 1267938, 'CS', 'EE', 'non@Quisque.org'),
(75, 'Joelle', 'Danielle', 26, 3, 1724067, 'CS', 'EE', 'mus.Aenean@anteNunc.org'),
(76, 'Adrian', 'Pandora', 24, 2, 301298, 'SE', 'SS', 'montes@ataugueid.ca'),
(77, 'Kennan', 'Britanni', 29, 1, 3638686, 'SE', 'SS', 'sem.mollis.dui@sedtortorInteger.co.uk'),
(78, 'Sandra', 'Grace', 21, 3, 2915063, 'SE', 'SS', 'nunc.nulla.vulputate@liberoProinsed.org'),
(79, 'Ali', 'Susan', 27, 4, 5319352, 'SE', 'EE', 'bibendum.ullamcorper.Duis@Fusce.edu'),
(80, 'Flynn', 'Alea', 21, 7, 6395565, 'SE', 'EE', 'lacus.Mauris.non@nuncrisus.edu'),
(81, 'Shelley', 'Brynn', 20, 6, 6704915, 'CS', 'EE', 'massa@etipsumcursus.net'),
(82, 'Imelda', 'Laura', 20, 8, 5945058, 'CS', 'SS', 'Proin.vel.nisl@ligula.org'),
(83, 'Ian', 'Orla', 21, 1, 2201537, 'CS', 'SS', 'Lorem.ipsum@nonhendrerit.co.uk'),
(84, 'Kitra', 'Keane', 28, 2, 4064565, 'CS', 'SS', 'nec.cursus@fames.org'),
(85, 'Hermione', 'Magee', 20, 7, 4859003, 'CS', 'EE', 'faucibus.orci.luctus@facilisisSuspendisse.org'),
(86, 'Alice', 'Chester', 20, 8, 4762236, 'SE', 'EE', 'sem@purusactellus.net'),
(87, 'Chloe', 'Maisie', 22, 3, 5669853, 'SE', 'EE', 'ornare.egestas@erat.ca'),
(88, 'Kay', 'Kelly', 26, 5, 1342542, 'SE', 'SS', 'morbi@etpede.net'),
(89, 'Bevis', 'Mallory', 23, 4, 3662315, 'SE', 'SS', 'tincidunt.tempus.risus@semper.net'),
(90, 'Brielle', 'Uta', 30, 5, 2318992, 'SE', 'SS', 'Sed.molestie@sem.edu'),
(91, 'Clementine', 'Quinlan', 26, 5, 2819038, 'CS', 'EE', 'rutrum@Sed.ca'),
(92, 'Blake', 'Whitney', 22, 5, 4639527, 'CS', 'EE', 'blandit.Nam.nulla@hendreritid.org'),
(93, 'Kyla', 'Wyatt', 30, 8, 3464878, 'CS', 'EE', 'mauris.Morbi@eratsemper.net'),
(94, 'Leo', 'Salvador', 20, 3, 3983522, 'CS', 'SS', 'sapien.cursus@rhoncusid.co.uk'),
(95, 'Steven', 'Medge', 26, 1, 4389916, 'CS', 'SS', 'odio.vel@sed.org'),
(96, 'Gloria', 'Myles', 28, 1, 604397, 'SE', 'SS', 'turpis.egestas.Fusce@facilisis.ca'),
(97, 'Akeem', 'Shelby', 28, 2, 7630278, 'SE', 'EE', 'ridiculus.mus@disparturientmontes.org'),
(98, 'Demetria', 'Lydia', 30, 8, 4044422, 'SE', 'EE', 'ligula@idmollis.org'),
(99, 'Aquila', 'Xaviera', 28, 7, 4297377, 'SE', 'EE', 'Pellentesque.tincidunt.tempus@et.co.uk'),
(100, 'Remedios', 'Priscilla', 24, 7, 5022861, 'SE', 'SS', 'Morbi.sit@etpedeNunc.net');

-- --------------------------------------------------------

--
-- Table structure for table `studenthastaken`
--

CREATE TABLE `studenthastaken` (
  `studentid` int(11) NOT NULL,
  `courseid` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `studenthastaken`
--

INSERT INTO `studenthastaken` (`studentid`, `courseid`) VALUES
(1, 'CS-212'),
(1, 'CS-220'),
(1, 'CS-250'),
(1, 'EE-215'),
(2, 'HU-107'),
(2, 'HU-21'),
(2, 'MATH-361'),
(2, 'PHY-102'),
(2, 'SE-200'),
(3, 'ECO-130'),
(3, 'EE-215'),
(3, 'MATH-232'),
(3, 'MGT-271'),
(3, 'OTM455'),
(4, 'CS-100'),
(4, 'CS-114'),
(4, 'CS-212'),
(4, 'CS-220'),
(4, 'HU-109'),
(5, 'CS-114'),
(5, 'EE-212'),
(5, 'MATH-232'),
(5, 'OTM-455'),
(6, 'ECO130'),
(6, 'EE215'),
(6, 'MATH232'),
(6, 'PHY102'),
(6, 'SE-200'),
(7, 'CS-100'),
(7, 'CS114'),
(7, 'HU-109'),
(7, 'MGT-271'),
(7, 'OTM455'),
(8, 'CS-212'),
(8, 'CS-220'),
(8, 'CS-250'),
(8, 'EE-212'),
(8, 'HU-107'),
(9, 'HU-21'),
(9, 'MATH-361'),
(9, 'MATH232'),
(9, 'PHY102'),
(9, 'SE-200'),
(10, 'CS114'),
(10, 'ECO130'),
(10, 'EE215'),
(10, 'MGT-271'),
(10, 'OTM455'),
(11, 'CS-100'),
(11, 'CS-212'),
(11, 'CS-220'),
(11, 'CS-250'),
(11, 'HU-109'),
(12, 'EE-212'),
(12, 'HU-107'),
(12, 'HU-21'),
(12, 'MATH-361'),
(12, 'SE-200'),
(13, 'ECO130'),
(13, 'EE215'),
(13, 'MATH232'),
(13, 'MGT-271'),
(13, 'PHY102'),
(14, 'CS-100'),
(14, 'CS-212'),
(14, 'CS114'),
(14, 'HU-109'),
(14, 'OTM455'),
(15, 'CS-220'),
(15, 'CS-250'),
(15, 'EE-212'),
(15, 'HU-107'),
(15, 'HU-21'),
(16, 'EE215'),
(16, 'MATH-361'),
(16, 'MATH232'),
(16, 'PHY102'),
(16, 'SE-200'),
(17, 'CS114'),
(17, 'ECO130'),
(17, 'HU-109'),
(17, 'MGT-271'),
(17, 'OTM455'),
(18, 'CS-100'),
(18, 'CS-212'),
(18, 'CS-220'),
(18, 'CS-250'),
(18, 'EE-212'),
(19, 'HU-107'),
(19, 'HU-21'),
(19, 'MATH-361'),
(19, 'PHY102'),
(19, 'SE-200'),
(20, 'ECO130'),
(20, 'EE215'),
(20, 'MATH232'),
(20, 'MGT-271'),
(20, 'OTM455');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `coursetype`
--
ALTER TABLE `coursetype`
  ADD PRIMARY KEY (`dept`,`courses`);

--
-- Indexes for table `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`studentid1`,`studentid2`),
  ADD KEY `studentid2` (`studentid2`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `studenthastaken`
--
ALTER TABLE `studenthastaken`
  ADD PRIMARY KEY (`studentid`,`courseid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`studentid1`) REFERENCES `student` (`id`),
  ADD CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`studentid2`) REFERENCES `student` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
