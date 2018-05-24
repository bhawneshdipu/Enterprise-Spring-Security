CREATE TABLE lecturer (
	lid INT(7) AUTO_INCREMENT PRIMARY KEY,
	gname VARCHAR(100) NOT NULL,
	surname VARCHAR(200) NOT NULL,
	email VARCHAR(200),
	campus VARCHAR(200),
	password VARCHAR(200)
);
CREATE TABLE course (
	cid INT(7) AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(50) NOT NULL,
	prerequisites VARCHAR(200) NOT NULL
);


CREATE TABLE course_conduction (
	ccid INT(10) AUTO_INCREMENT PRIMARY KEY,
	lid INT(10) REFERENCES lecturer(lid),
	cid INT(10) REFERENCES course(cid),
	semester VARCHAR(10) NOT NULL,
	capacity VARCHAR(10) NOT NULL
);
CREATE TABLE student (
	sid INT(8) AUTO_INCREMENT PRIMARY KEY,
	fname VARCHAR(100),
	surname VARCHAR(200),
	email VARCHAR(200),
	major VARCHAR(200),
	password VARCHAR(200) NOT NULL
);


CREATE TABLE enrollment (
	eid INT(8) AUTO_INCREMENT PRIMARY KEY,
	sid INT(10) REFERENCES student(sid),
	cid INT(10) REFERENCES course(cid),
	semester VARCHAR(10) NOT NULL
);
CREATE TABLE assessment (
	aid INT(10) AUTO_INCREMENT PRIMARY KEY,
	sid INT(10) REFERENCES student(sid),
	eid INT(10)  REFERENCES enrollment(eid),
	A1 INT(5) NOT NULL,
	A2 INT(5) NOT NULL
);

CREATE TABLE course_assessment (
	caid INT(10) AUTO_INCREMENT PRIMARY KEY,
	cid INT(10) REFERENCES course(cid),
	aid INT(10) REFERENCES assessment(aid),
	semester VARCHAR(10) NOT NULL
);




