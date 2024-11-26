
DROP TABLE SOCIAL_MEDIA_API.INTERACTIONS;
DROP TABLE SOCIAL_MEDIA_API.POSTS;
DROP TABLE SOCIAL_MEDIA_API.ACCOUNTS;
DROP SCHEMA SOCIAL_MEDIA_API;

CREATE SCHEMA SOCIAL_MEDIA_API;

CREATE TABLE `SOCIAL_MEDIA_API`.`ACCOUNTS` (
                                               `userId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
                                               `username` VARCHAR(15) NOT NULL UNIQUE,
                                               `password` VARCHAR(15) NOT NULL,
                                               `email` VARCHAR(30) NOT NULL UNIQUE,
                                               `phone` VARCHAR(11) NOT NULL UNIQUE,
                                               `status` INT NOT NULL DEFAULT 1,
                                               `createDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP());

CREATE TABLE `SOCIAL_MEDIA_API`.`POSTS` (
                                            `postId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
                                            `userId` INT NOT NULL,
                                            `context` VARCHAR(255) NOT NULL,
                                            `status` INT NOT NULL DEFAULT 1,
                                            `createDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
                                            CONSTRAINT `post_userId`
                                                FOREIGN KEY (`userId`)
                                                    REFERENCES `SOCIAL_MEDIA_API`.`ACCOUNTS` (`userId`)
                                                    ON DELETE CASCADE
                                                    ON UPDATE CASCADE);

CREATE TABLE `SOCIAL_MEDIA_API`.`INTERACTIONS` (
                                                   `interactionId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE,
                                                   `userId` INT NOT NULL,
                                                   `postId` INT NOT NULL,
                                                   `context` VARCHAR(255),
                                                   `type` INT,
                                                   `status` INT NOT NULL DEFAULT 1,
                                                   `createDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
                                                   CONSTRAINT `interaction_userId`
                                                       FOREIGN KEY (`userId`)
                                                           REFERENCES `SOCIAL_MEDIA_API`.`ACCOUNTS` (`userId`)
                                                           ON DELETE CASCADE
                                                           ON UPDATE CASCADE,
                                                   CONSTRAINT `interaction_postId`
                                                       FOREIGN KEY (`postId`)
                                                           REFERENCES `SOCIAL_MEDIA_API`.`POSTS` (`postId`)
                                                           ON DELETE CASCADE);
