CREATE DATABASE schoolmanagementsystem;
use schoolmanagementsystem;



CREATE TABLE IF NOT EXISTS `courses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `stream` varchar(45) NOT NULL,
  `type`   varchar(45) NOT NULL,
  `startdate` DATE,
  `enddate` DATE,
  PRIMARY KEY (`id`)
); -- ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `students` (
`id` INT NOT NULL AUTO_INCREMENT,
`fname` varchar(45) NOT NULL,
`lname` varchar(45) NOT NULL,
`dob` DATE NOT NULL,
`tuitionfees` DECIMAL(6,2),
PRIMARY KEY (`id`)
); -- ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `trainers` (
`id` INT NOT NULL AUTO_INCREMENT,
`fname` varchar(45) NOT NULL,
`lname` varchar(45) NOT NULL,
`subject` varchar(45) NOT NULL,
PRIMARY KEY (`id`)
); -- ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `assignments` (
`id`  INT NOT NULL AUTO_INCREMENT,
`title` varchar(45) NOT NULL,
`description` varchar(45) NOT NULL,
`subdate` DATE NOT NULL,
`oralmark` DECIMAL(3,1) NOT NULL,
`totalmark` DECIMAL(4,1) NOT NULL,
UNIQUE (title,description),
PRIMARY KEY (`id`)
); -- ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `enrollments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sid` int DEFAULT NULL,
  `cid` int DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE (`sid`,`cid`),
  KEY `fk_enrollments_cid_courses_id_idx` (`cid`),
  KEY `fk_enrollments_sid_students_id_idx` (`sid`),
  CONSTRAINT `fk_enrollments_cid_courses_id` FOREIGN KEY (`cid`) REFERENCES `courses` (`id`),
  CONSTRAINT `fk_enrollments_sid_students_id` FOREIGN KEY (`sid`) REFERENCES `students` (`id`)
); -- ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `enrollmentassignments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `eid` int DEFAULT NULL,
  `aid` int DEFAULT NULL,
  oralmark DECIMAL(3,1) DEFAULT NULL,
  totalmark DECIMAL(4,1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE(`eid`,`aid`),
  KEY `fk_enrollmentassignments_eid_enrollments_id_idx` (`eid`),
  KEY `fk_enrollmentassignments_aid_assignments_id_idx` (`aid`),
  CONSTRAINT `fk_enrollmentassignments_aid_assignments_id` FOREIGN KEY (`aid`) REFERENCES `assignments` (`id`),
  CONSTRAINT `fk_enrollmentassignments_eid_enrollments_id_idx` FOREIGN KEY (`eid`) REFERENCES `enrollments` (`id`)
); -- ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `enrollmentstrainers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `eid` int DEFAULT NULL,
  `tid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE(`eid`,`tid`),
  KEY `fk_enrollmentstrainers_eid_courses_id_idx` (`eid`),
  KEY `fk_enrollmentstrainers_tid_trainers_id_idx` (`tid`),
  CONSTRAINT `fk_enrollmentstrainers_tid_trainers_id_idx` FOREIGN KEY (`tid`) REFERENCES `trainers` (`id`),
  CONSTRAINT `fk_enrollmentstrainers_eid_courses_id_idx` FOREIGN KEY (`eid`) REFERENCES `courses` (`id`)
); -- ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- Data for Courses
INSERT IGNORE INTO courses (id,title,stream,type,startdate,enddate)
VALUES (1,'CB12','Java','Part Time','2020-09-30','2021-04-30'),
(2,'CB12','Java','Full Time','2020-09-25','2020-12-30'),
(3,'CB12','C#','Part Time','2020-09-26','2021-04-03'),
(4,'CB12','Pyhton','Part Time','2020-10-20','2021-03-30'),
(5,'CB12','Pyhton','Full Time','2020-10-30','2020-12-15');


-- Data for Students
INSERT IGNORE INTO students (id,fname,lname,dob,tuitionfees)
VALUES (1,'Zebulen', 'Purdom', '1985-05-10', 1600.50),
(2,'Haily', 'Picton', '1987-08-20', 1700.00),
(3,'Carma', 'Pollington', '1997-08-10', 2100.00),
(4,'Nigel', 'Verrill', '1996-04-01', 2000.00),
(5,'Brear', 'Reston', '1987-01-29', 1300.00),
(6,'Cirstoforo', 'Donkersley', '1989-02-11', 1250.00),
(7,'Daphna', 'Mycroft', '1990-10-19', 1200.00),
(8,'Stacy', 'MacCarter', '1983-04-05', 1230.00),
(9,'Sarina', 'Mechem', '1990-01-31', 2200.00),
(10,'Heywood', 'Lomasny', '1992-04-26', 2050.50),
(11,'Celie', 'Caspell', '1994-07-18', 1700.00),
(12,'Knox', 'Vernham', '1997-03-27', 2300.00),
(13,'Thibaud', 'Moyler', '1992-07-09', 1000.00),
(14,'Theo', 'Chorlton', '1996-08-11', 2500.00),
(15,'Karena', 'Gertz', '1986-01-02', 1750.50),
(16,'Karon', 'Langland', '1983-03-21', 1270.00),
(17,'Brett', 'Camillo', '2000-02-26', 2400.00),
(18,'Sally', 'Pixton', '1988-12-20', 1600.00),
(19,'Lorrin', 'Bremner', '1985-07-07', 1900.00),
(20,'Dasia', 'O''Dennehy', '1983-03-06', 1950.00);

-- Data for Trainers
INSERT IGNORE INTO trainers (id,fname,lname,subject)
VALUES (1,'Di', 'Kett','MySql'),
(2,'Dinah', 'Madle','Front-end'),
(3,'Jeremie', 'Tunder','Back-end'),
(4,'Petty', 'Pordall','Front-end'),
(5,'Mitzi', 'Van Arsdall','Back-end'),
(6,'Lyell', 'Stapele','Back-end'),
(7,'Konrad', 'Philliskirk','MySql'),
(8,'Kassi', 'Baskwell','MySql'),
(9,'Filippo', 'Drawmer','Front-end'),
(10,'Gabe', 'Shafier','Front-end'),
(11,'Gaylord', 'Riguard','Back-end');

-- Data for Assignments
INSERT IGNORE INTO assignments (id,title,description,subdate,oralmark,totalmark)
VALUES (1,'Individual Project Part A','School Management System','2020-10-10',20,100),
(2,'Individual Project Part B','Database for School Management System','2020-11-10',20,100),
(3,'Individual Project Part C','Web App - School Management System','2020-11-10',20,100),
(4,'Team Project Part A','CRM for small Business','2020-12-10',20,100),
(5,'Team Project Part B','Web App - CRM for small Business','2020-10-10',20,100);

-- Data for enrollments
INSERT IGNORE INTO enrollments (id,sid,cid)
VALUES (1,(SELECT id FROM students WHERE id=1),(SELECT id FROM courses WHERE id=1)),
(2,(SELECT id FROM students WHERE id=2),(SELECT id FROM courses WHERE id=4)),
(3,(SELECT id FROM students WHERE id=3),(SELECT id FROM courses WHERE id=5)),
(4,(SELECT id FROM students WHERE id=4),(SELECT id FROM courses WHERE id=5)),
(5,(SELECT id FROM students WHERE id=5),(SELECT id FROM courses WHERE id=2)),
(6,(SELECT id FROM students WHERE id=6),(SELECT id FROM courses WHERE id=3)),
(7,(SELECT id FROM students WHERE id=7),(SELECT id FROM courses WHERE id=3)),
(8,(SELECT id FROM students WHERE id=8),(SELECT id FROM courses WHERE id=1)),
(9,(SELECT id FROM students WHERE id=9),(SELECT id FROM courses WHERE id=4)),
(10,(SELECT id FROM students WHERE id=10),(SELECT id FROM courses WHERE id=5)),
(11,(SELECT id FROM students WHERE id=10),(SELECT id FROM courses WHERE id=1)),
(12,(SELECT id FROM students WHERE id=11),(SELECT id FROM courses WHERE id=1)),
(13,(SELECT id FROM students WHERE id=12),(SELECT id FROM courses WHERE id=2)),
(14,(SELECT id FROM students WHERE id=12),(SELECT id FROM courses WHERE id=3)),
(15,(SELECT id FROM students WHERE id=1),(SELECT id FROM courses WHERE id=4)),
(16,(SELECT id FROM students WHERE id=13),(SELECT id FROM courses WHERE id=1)),
(17,(SELECT id FROM students WHERE id=14),(SELECT id FROM courses WHERE id=1)),
(18,(SELECT id FROM students WHERE id=15),(SELECT id FROM courses WHERE id=2)),
(19,(SELECT id FROM students WHERE id=16),(SELECT id FROM courses WHERE id=2)),
(20,(SELECT id FROM students WHERE id=17),(SELECT id FROM courses WHERE id=3)),
(21,(SELECT id FROM students WHERE id=18),(SELECT id FROM courses WHERE id=2)),
(22,(SELECT id FROM students WHERE id=18),(SELECT id FROM courses WHERE id=4)),
(23,(SELECT id FROM students WHERE id=19),(SELECT id FROM courses WHERE id=1)),
(24,(SELECT id FROM students WHERE id=9),(SELECT id FROM courses WHERE id=5)),
(25,(SELECT id FROM students WHERE id=1),(SELECT id FROM courses WHERE id=1));


-- Data for enrollmentassignments
INSERT IGNORE INTO enrollmentassignments (id,eid,aid,oralmark,totalmark)
VALUES (1,(SELECT id from enrollments WHERE id =1),(SELECT id FROM assignments WHERE id =1),15,80),
(2,(SELECT id from enrollments WHERE id =1),(SELECT id FROM assignments WHERE id =2),10,70),
(3,(SELECT id from enrollments WHERE id =1),(SELECT id FROM assignments WHERE id =3),20,80),
(4,(SELECT id from enrollments WHERE id =2),(SELECT id FROM assignments WHERE id =1),10,65),
(5,(SELECT id from enrollments WHERE id =2),(SELECT id FROM assignments WHERE id =2),12,55),
(6,(SELECT id from enrollments WHERE id =2),(SELECT id FROM assignments WHERE id =4),13,90),
(7,(SELECT id from enrollments WHERE id =3),(SELECT id FROM assignments WHERE id =1),17,45),
(8,(SELECT id from enrollments WHERE id =3),(SELECT id FROM assignments WHERE id =2),20,100),
(9,(SELECT id from enrollments WHERE id =3),(SELECT id FROM assignments WHERE id =3),15,95),
(10,(SELECT id from enrollments WHERE id =3),(SELECT id FROM assignments WHERE id =4),18,88),
(11,(SELECT id from enrollments WHERE id =3),(SELECT id FROM assignments WHERE id =5),19,90);


INSERT IGNORE INTO enrollmentassignments (id,eid,aid)
VALUES
(12,(SELECT id from enrollments WHERE id =4),(SELECT id FROM assignments WHERE id =1)),
(13,(SELECT id from enrollments WHERE id =4),(SELECT id FROM assignments WHERE id =2)),
(14,(SELECT id from enrollments WHERE id =5),(SELECT id FROM assignments WHERE id =1)),
(15,(SELECT id from enrollments WHERE id =5),(SELECT id FROM assignments WHERE id =2)),
(16,(SELECT id from enrollments WHERE id =1),(SELECT id FROM assignments WHERE id =1));


-- Data for enrollmentstrainers
INSERT IGNORE INTO enrollmentstrainers(id,eid,tid)
VALUES (1,(SELECT id from courses WHERE id =1),(SELECT id FROM trainers WHERE id =3)),
(2,(SELECT id from courses WHERE id =1),(SELECT id FROM trainers WHERE id =2)),
(3,(SELECT id from courses WHERE id =1),(SELECT id FROM trainers WHERE id =10)),
(4,(SELECT id from courses WHERE id =2),(SELECT id FROM trainers WHERE id =3)),
(5,(SELECT id from courses WHERE id =2),(SELECT id FROM trainers WHERE id =4)),
(6,(SELECT id from courses WHERE id =3),(SELECT id FROM trainers WHERE id =3)),
(7,(SELECT id from courses WHERE id =3),(SELECT id FROM trainers WHERE id =7)),
(8,(SELECT id from courses WHERE id =3),(SELECT id FROM trainers WHERE id =8)),
(9,(SELECT id from courses WHERE id =4),(SELECT id FROM trainers WHERE id =9)),
(10,(SELECT id from courses WHERE id =4),(SELECT id FROM trainers WHERE id =1)),
(11,(SELECT id from courses WHERE id =4),(SELECT id FROM trainers WHERE id =5)),
(12,(SELECT id from courses WHERE id =5),(SELECT id FROM trainers WHERE id =5)),
(13,(SELECT id from courses WHERE id =4),(SELECT id FROM trainers WHERE id =6)),
(14,(SELECT id from courses WHERE id =1),(SELECT id FROM trainers WHERE id =3));


-- List with Students
DELIMITER //
CREATE PROCEDURE getAllStudents()
BEGIN
SELECT students.id, students.fname as `First Name`,students.lname as `Last Name`,students.dob as `Date Of Birth`,
students.tuitionfees as `Tuition Fees`
FROM students;
END //
DELIMITER ;

CALL getAllStudents();

-- List with Courses
DELIMITER //
CREATE PROCEDURE getAllCourses()
BEGIN
SELECT courses.id, courses.title as `Title`, courses.stream as `Stream`, courses.type ,
courses.startdate as `Start Date`, courses.enddate as `End Date`
FROM courses;
END //
DELIMITER ;

CALL getAllCourses();

-- List with Assignments
DELIMITER //
CREATE PROCEDURE getAllAssignments()
BEGIN
SELECT assignments.id, assignments.title as `Title`, assignments.description as `Description`, assignments.subdate as `Submission Date` ,
assignments.oralmark as `Oral Mark`, assignments.totalmark as `Total Mark`
FROM assignments;
END //
DELIMITER ;

CALL getAllAssignments();



-- List with Trainers
DELIMITER //
CREATE PROCEDURE getAllTrainers()
BEGIN
SELECT trainers.id, trainers.fname as `First Name`, trainers.lname as `Last Name`, trainers.subject as `Subject`
FROM trainers;
END //
DELIMITER ;

CALL getAllTrainers();

-- List of Studens per Course
DELIMITER //
CREATE PROCEDURE getStudentsPerCourse()
BEGIN
SELECT	enrollments.id, courses.title AS `Title`,courses.stream AS `Stream`,courses.type,students.fname AS `First Name`,
students.lname AS `Last Name`
FROM students
INNER JOIN enrollments
ON enrollments.sid = students.id
INNER JOIN courses
ON courses.id = enrollments.cid
group by enrollments.id
order by courses.id;
END //
DELIMITER ;

CALL getStudentsPerCourse();


DELIMITER //
CREATE PROCEDURE getStudentsPerCoursebyId()
BEGIN
SELECT	enrollments.id, courses.title AS `Title`,courses.stream AS `Stream`,courses.type,students.fname AS `First Name`,
students.lname AS `Last Name`
FROM students
INNER JOIN enrollments
ON enrollments.sid = students.id
INNER JOIN courses
ON courses.id = enrollments.cid
group by enrollments.id
order by enrollments.id;
END //
DELIMITER ;

CALL getStudentsPerCoursebyId();






-- All the trainers per course
DELIMITER //
CREATE PROCEDURE getTrainersPerCourse()
BEGIN
SELECT enrollmentstrainers.id,courses.title AS `Title`,courses.stream AS `Stream`,courses.type,trainers.fname AS `First Name`,
trainers.lname AS `Last Name`, trainers.subject as `Subject`
FROM trainers
INNER JOIN enrollmentstrainers
ON enrollmentstrainers.tid = trainers.id
INNER JOIN courses
ON courses.id = enrollmentstrainers.eid
group by enrollmentstrainers.id
order by courses.id;
END //
DELIMITER ;


CALL getTrainersPerCourse();




-- All the assignments per course
DELIMITER //
CREATE PROCEDURE getAssignmentsPerCourse()
BEGIN
SELECT DISTINCT courses.title AS `Title`,courses.stream AS `Stream`,courses.type,assignments.title AS `Assignment Title`,
assignments.description as `Description`
FROM enrollmentassignments
INNER JOIN enrollments
ON enrollments.id = enrollmentassignments.eid
INNER JOIN assignments 
ON enrollmentassignments.aid = assignments.id
INNER JOIN courses
ON courses.id = enrollments.cid
ORDER BY courses.id;
END //
DELIMITER ;

CALL getAssignmentsPerCourse();


-- All the assignments per course per student
DELIMITER //
CREATE PROCEDURE getAssignmentsPerCoursePerStudent()
BEGIN
SELECT	enrollmentassignments.id,students.fname AS `First Name` ,students.lname AS `Last Name`,courses.title AS `Course Title`,
courses.stream AS `Course Stream`,courses.type AS `Course Type`,assignments.title AS `Assignment Title`,
enrollmentassignments.oralmark AS `Oral Mark`,enrollmentassignments.totalmark AS `Total Mark`
FROM students
INNER JOIN enrollments
ON students.id = enrollments.sid
INNER JOIN enrollmentassignments
ON enrollmentassignments.eid = enrollments.id
inner join courses
on courses.id = enrollments.cid
inner join assignments on enrollmentassignments.aid = assignments.id
-- group by enrollmentassignments.id
order by students.id;
END //
DELIMITER ;

CALL getAssignmentsPerCoursePerStudent();



-- All the assignments per course per student without grades
DELIMITER //
CREATE PROCEDURE getAssignmentsPerCoursePerStudentNullGrades()
BEGIN
SELECT	enrollmentassignments.id,students.fname AS `First Name` ,students.lname AS `Last Name`,courses.title AS `Course Title`,
courses.stream AS `Course Stream`,courses.type AS `Course Type`,assignments.title AS `Assignment Title`,
enrollmentassignments.oralmark AS `Oral Mark`,enrollmentassignments.totalmark AS `Total Mark`
FROM students
INNER JOIN enrollments
ON students.id = enrollments.sid
INNER JOIN enrollmentassignments
ON enrollmentassignments.eid = enrollments.id
inner join courses
on courses.id = enrollments.cid
inner join assignments on enrollmentassignments.aid = assignments.id
where enrollmentassignments.oralmark IS NULL and enrollmentassignments.totalmark IS NULL
order by students.id;
END //
DELIMITER ;

CALL getAssignmentsPerCoursePerStudentNullGrades();


-- A list of students that belong to more than one courses
DELIMITER //
CREATE PROCEDURE getStudentsWithMultipleCourses()
BEGIN
SELECT students.fname as `First Name`,students.lname as `Last Name`,count(enrollments.sid) as ` Enrollments` 
FROM students
INNER JOIN enrollments
ON enrollments.sid = students.id
group by students.id
having count(enrollments.sid)>1;
END //
DELIMITER ;

CALL getStudentsWithMultipleCourses();

