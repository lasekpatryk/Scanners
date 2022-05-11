DROP TABLE IF EXISTS `SCANNER`;
CREATE TABLE `SCANNER` (
  `ID` INTEGER PRIMARY KEY NOT NULL,
  `IN_SERVICE` BOOLEAN NOT NULL,
  `LOGGED_USER` VARCHAR(255) NOT NULL,
  `SCANNER_NUMBER` INTEGER NOT NULL,
  `TIME_OF_LOGIN` DATETIME,
  PRIMARY KEY (`ID`)
);