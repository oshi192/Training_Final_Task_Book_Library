create schema if not exists library;

CREATE TABLE IF NOT EXISTS `library`.`users` (
  `user_id` INT AUTO_INCREMENT,
  `email` VARCHAR(64) NOT NULL,
  `phone_number` VARCHAR(15) NOT NULL,
  `user_name` VARCHAR(255) NULL,
  `surname` VARCHAR(255) NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `library`.`books` (
  `book_id` INT NOT NULL AUTO_INCREMENT,
  `book_name` VARCHAR(255) NOT NULL,
  `section` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`book_id`))
ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `library`.`author` (
  `author_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `second_name` VARCHAR(45) NOT NULL,
  `patronymic_name` VARCHAR(45) NULL,
  PRIMARY KEY (`author_id`))
ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `library`.`book_key_words` (
  `key_word_id` INT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`key_word_id`))
ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `library`.`taken_books` (
  `users_user_id` INT NOT NULL,
  `users_admin_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  `taken_date` DATETIME NOT NULL,
  `end_date` VARCHAR(45) NOT NULL,
  INDEX `fk_taken_books_users2_idx` (`users_user_id` ASC),
  INDEX `fk_taken_books_users3_idx` (`users_admin_id` ASC),
  INDEX `fk_taken_books_books2_idx` (`book_id` ASC),
  CONSTRAINT `fk_taken_books_users2`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `library`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_taken_books_users3`
    FOREIGN KEY (`users_admin_id`)
    REFERENCES `library`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_taken_books_books2`
    FOREIGN KEY (`book_id`)
    REFERENCES `library`.`books` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `library`.`book_request` (
  `users_user_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  INDEX `fk_book_request_users1_idx` (`users_user_id` ASC),
  INDEX `fk_book_request_books1_idx` (`book_id` ASC),
  CONSTRAINT `fk_book_request_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `library`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_request_books1`
    FOREIGN KEY (`book_id`)
    REFERENCES `library`.`books` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `library`.`authors2book` (
  `books_book_id` INT NOT NULL,
  `author_id` INT NOT NULL,
  INDEX `fk_authors_group_books1_idx` (`books_book_id` ASC),
  INDEX `fk_authors_group_Author1_idx` (`author_id` ASC),
  CONSTRAINT `fk_authors_group_books1`
    FOREIGN KEY (`books_book_id`)
    REFERENCES `library`.`books` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_authors_group_Author1`
    FOREIGN KEY (`author_id`)
    REFERENCES `library`.`author` (`author_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `library`.`keywords2book` (
  `book_id` INT NOT NULL,
  `key_words_id` INT NOT NULL,
  INDEX `fk_book_keywords_books1_idx` (`book_id` ASC),
  INDEX `fk_keywords2book_book_key_words1_idx` (`key_words_id` ASC),
  CONSTRAINT `fk_book_keywords_books1`
    FOREIGN KEY (`book_id`)
    REFERENCES `library`.`books` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_keywords2book_book_key_words1`
    FOREIGN KEY (`key_words_id`)
    REFERENCES `library`.`book_key_words` (`key_word_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

