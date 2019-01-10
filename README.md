## Final project
### 16. System Library.

#### The task:
 Create a directory by which you can search by:
    • The author (one of the group).
    • The name of the book or its fragment.
    • One of the keywords in the book (book attribute).
The catalog is filled by the Administrator by adding and modifying / deleting them. Each book must have an address (place on the shelf) or a reader. The reader, in order to take the book, registers, leaving the e-mail and telephone number. The book can be taken from the Administrator in the library for a period of no more than a month,
only if the book is available in the library. The administrator must have a page where the books and readers who use the book are reflected.

###Library system
####This system allows you to:

all users: browse books and use them to search
 - Registered user - invite a book, and take it and the term - no more than a month from the moment the request is confirmed
 - Administrator - Create new entries (Books), edit or delete those books that nobody has taken yet, and also confirm or reject book requests


      to login:
   - Admin: login [admin@gmail.com] password [Admin1234]
   - User: login [stepanJ@imail.com] password [12345678Qq]


#### Used technologies:
 - JSP + JSTL;
 - Servlets;
 - Log4J;
 - Junit 4
 - Maven
 - JDBC;
 - MySql
 - Tomcat
 - Bootstrap
 - Md5 hasing for passwords

#### Architecture
 - MVC
    - Model - Entities vs services & Dao
    - Controller - Servlet, Command classes, Filters, Listeners
    - View - jsp pages
 - Webapp

 #### How to launch:
 __Need instaled:__
  - maven
  - MySql
  sequense:
  - run sql script from sql/data.sql file
  - clone or download
  - if folder with pom file : ```mvn clean compile tomkat7:run```
  - go to [http://localhost:8888/library/](http://localhost:8888/library/)


