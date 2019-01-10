DROP DATABASE if exists `library`;
create schema library;

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
  `book_name_en` VARCHAR(255) NOT NULL,
  `book_name_uk` VARCHAR(255) NOT NULL,
  `section` VARCHAR(45) NOT NULL,
  `users_user_id` INT,
  `users_admin_id` INT,
  `taken_begin_date` DATETIME,
  `taken_end_date` DATETIME,
  `status` INT NOT NULL,
  PRIMARY KEY (`book_id`))
ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `library`.`author` (
  `author_id` INT NOT NULL AUTO_INCREMENT,
  `first_name_en` VARCHAR(45) NOT NULL,
  `first_name_uk` VARCHAR(45) NOT NULL,
  `second_name_en` VARCHAR(45) NOT NULL,
  `second_name_uk` VARCHAR(45) NOT NULL,
  `patronymic_name_en` VARCHAR(45) NULL,
  `patronymic_name_uk` VARCHAR(45) NULL,
  PRIMARY KEY (`author_id`))
ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `library`.`book_key_words` (
  `key_word_id` INT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`key_word_id`))
ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `library`.`authors2book` (
  `books_book_id` INT NOT NULL,
  `author_id` INT NOT NULL,
  INDEX `fk_authors_group_books1_idx` (`books_book_id` ASC),
  INDEX `fk_authors_group_Author1_idx` (`author_id` ASC),
  CONSTRAINT `fk_authors_group_books1`
    FOREIGN KEY (`books_book_id`)
    REFERENCES `library`.`books` (`book_id`)
    ON DELETE CASCADE
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
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

start transaction;
use library;
insert into `library`.`users` values(1, "johnsnow@gmail.com", "0960969090", "John", "Snow", "3cd2436f0dd5a354b7144a3361d19a10", "USER");
insert into `library`.`users` values(2, "admin@gmail.com", "0961234567", "Василь", "Пупкін", "751cb3f4aa17c36186f4856c8982bf27", "ADMIN");
insert into `library`.`users` values(3, "kornik@gmail.com", "0909876543", "Михаил", "Корников", "d59a62dc44f354b41469ac42b83ec0a5", "ADMIN");
insert into `library`.`users` values(4, "stepanJ@imail.com", "0443243434", "Степан", "Портак", "e6c86a8da44ff392dc61c529a545ac9d", "USER");
insert into `library`.`users` values(5, "kenya@gmail.com", "0503323434", "Інокентій", "Пастернак", "2ac9cb7dc02b3c0083eb70898e549b63", "USER");
insert into `library`.`users` values(6, "gregor@gmail.com", "0930302099", "Грегорій", "Лінус", "3e2a52b4b58782ea49a866cfdf46ef8b", "USER");
commit;

start transaction;
use library;
insert into `books` values(1, "Dune", "Дюна", "Fantasy-3-K",null,null,null,null,0);
insert into `books` values(2, "Data Science. Insider Information for Newbies","Наука про дані. Інсайдерська інформація для новачків", "IT-2-A",null,null,null,null,0);
insert into `books` values(3, "The witcher","Відьмак", "Fantasy-3-G",1, 2,'2018-12-01','2019-01-01',2);
insert into `books` values(4, "Blood elves","Кровні ельфи", "Fantasy-3-G",4, 2,'2018-12-08','2019-01-08',2);
insert into `books` values(5, "Hour of Contempt","Година презирства", "Fantasy-3-G",null,null,null,null,0);
insert into `books` values(6, "Java. INDUSTRIAL PROGRAMMING", "Java. ПРОМИСЛОВЕ ПРОГРАМУВАННЯ", "IT-1-A",4,null,null,null,1);
insert into `books` values(7, "I am a robot", "Я робот", "Fantasy-2-I",4,null,null,null,1);
insert into `books` values(8, "Bicentennial man", "Двохсотрічний чоловік", "Fantasy-2-I",null,null,null,null,0);
insert into `books` values(9, "Stars like dust", "Зорі як пил", "Fantasy-2-I",null,null,null,null,0);
insert into `books` values(10, "SQL - requests for mere mortals.", "SQL - запити для простих смертних.", "IT-1-A",5, 3,'2018-12-11','2019-01-11',2);
insert into `books` values(11, "Java. Effective programming.", "Java. Ефективне програмування.", "IT-1-A",null,null,null,null,0);
insert into `books` values(12, "The Hitchhiker's Guide to the Galaxy", "Автостопом по Галактиці", "Fantasy-3-B",null,null,null,null,0);
commit;

start transaction;
use library;
insert into `library`.`author` values(1, "Frank", "Френк", "Gebert", "Геберт", NULL, NULL);
insert into `library`.`author` values(2, "Ketty", "Кетті", "Nill", "Нілл", "O", "О");
insert into `library`.`author` values(3, "Rachel", "Рейчел", "Shat", "Шат",NULL,NULL);
insert into `library`.`author` values(4, " Andrzej", " Анджей", "Sapkowski","Сапковский", NULL, NULL);
insert into `library`.`author` values(5, "Nikolai", "Николай", "Blinov", "Блинов", "Ivanovich", "Иванович");
insert into `library`.`author` values(6, "Valery", "Валерий", "Romantic", "Романчик", "Stanislavovich", "Станиславович");
insert into `library`.`author` values(7, "Isaac", "Айзек", "Azimov", "Азімов", NULL, NULL);
insert into `library`.`author` values(8, "Michael", "Майкл", "Hernandez", "Хернандес", "J.", "Дж.");
insert into `library`.`author` values(9, "John", "Джон", "Viaxas", "Вьескас", NULL, NULL);
insert into `library`.`author` values(10, "Joshua", "Джошуа", "Bloody", "Блох", NULL, NULL);
insert into `library`.`author` values(11, "Douglas", "Дуглас", "Adams", "Адамс", NULL, NULL);
commit;


start transaction;
use library;
insert into `library`.`authors2book` values(1, 1);
insert into `library`.`authors2book` values(2, 2);
insert into `library`.`authors2book` values(2, 3);
insert into `library`.`authors2book` values(3, 4);
insert into `library`.`authors2book` values(4, 4);
insert into `library`.`authors2book` values(5, 4);
insert into `library`.`authors2book` values(5, 5);
insert into `library`.`authors2book` values(6, 6);
insert into `library`.`authors2book` values(7, 7);
insert into `library`.`authors2book` values(8, 7);
insert into `library`.`authors2book` values(9, 7);
insert into `library`.`authors2book` values(10, 8);
insert into `library`.`authors2book` values(10, 9);
insert into `library`.`authors2book` values(11, 10);
insert into `library`.`authors2book` values(12, 11);
commit;

start transaction;
use library;
insert into `library`.`book_key_words` values(1, "fantasy");
insert into `library`.`book_key_words` values(2, "it");
insert into `library`.`book_key_words` values(3, "java");
insert into `library`.`book_key_words` values(4, "sql");
insert into `library`.`book_key_words` values(5, "data science");
commit;
