CREATE TABLE `admin` (
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `bike` (
  `bikeId` varchar(10) NOT NULL,
  `model` varchar(20) NOT NULL,
  `build` varchar(20) NOT NULL,
  `color` varchar(10) NOT NULL,
  `available` varchar(5) NOT NULL,
  `type` varchar(10) NOT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`bikeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `rental` (
  `rental_id` int(11) NOT NULL AUTO_INCREMENT,
  `bikeId` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `rentDate` varchar(50) DEFAULT NULL,
  `duration` double DEFAULT NULL,
  `returned` tinyint(1) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`rental_id`),
  KEY `bikeId` (`bikeId`),
  KEY `username` (`username`),
  CONSTRAINT `rental_ibfk_1` FOREIGN KEY (`bikeId`) REFERENCES `bike` (`bikeId`),
  CONSTRAINT `rental_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `user` (
  `userName` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `emailId` varchar(50) NOT NULL,
  `phoneNumber` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
