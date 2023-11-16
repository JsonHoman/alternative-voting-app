DROP USER if exists 'alternative-voter'@'localhost';

CREATE USER 'alternative-voter'@'localhost' IDENTIFIED BY 'alternative-voter';

GRANT ALL PRIVILEGES ON * . * TO 'alternative-voter'@'localhost';