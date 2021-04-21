DROP TABLE Assignment;
DROP TABLE Section;
DROP TABLE Class;
DROP TABLE Person;


CREATE TABLE Person ( 
	PersonID INTEGER PRIMARY KEY,
    FirstName VARCHAR(25),
    LastName VARCHAR(25),
    UserName VARCHAR(25),
    Pass VARCHAR(25),
    Email VARCHAR(40),
    PhoneNumber VARCHAR(25),
    IsStudent BOOLEAN
);

CREATE TABLE Class(
	ClassID INTEGER PRIMARY KEY,
    ClassName VARCHAR(50),
    TutorID INTEGER NULL,
    ClassSubject VARCHAR(50),
    ClassLevel VARCHAR(50),
    ClassDescription VARCHAR(500),
    IsOnline BOOLEAN,
    City VARCHAR(50),
    State VARCHAR(2),
    StartDate DATE,
    EndDate DATE
);

CREATE TABLE Section(
	SectionID INTEGER PRIMARY KEY,
    ClassID INTEGER,
    Capacity INTEGER,
    SectionDate DATE,
    SectionTime TIME,
	Length INTEGER,
    FOREIGN KEY (ClassID) REFERENCES Class(ClassID)
);

CREATE TABLE Assignment (
	PersonID INTEGER,
    SectionID INTEGER,
    PRIMARY KEY(PersonID, SectionID),
    FOREIGN KEY (PersonID) REFERENCES Person(PersonID),
    FOREIGN KEY (SectionID) REFERENCES Section(SectionID)
);