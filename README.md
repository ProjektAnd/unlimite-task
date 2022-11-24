# unlimite-task
Final recruitment task

## Requirements
1. Java 8
2. MySQL 10+ ver.
3. Spring 2.7.5

## Configuration
1. sql-script -> files to set up Mysql DB
2. resoursec/aplication.properties -> config file

## Student endpoint -> api/student
###Method: GET -> Students
Path: api/student 


###Method: GET -> Student
Path: api/student/ pramas = { studentId , firstName , lastName} 


###Method: GET -> StudentS
Path: api/student/{studentId}


###Method: GET -> Instructors
Path: api/student/{studentId}/instructor


###Method: POST <- Student
Path: api/student/ 


###Method: PUT <- Student
Path: api/student/{studentId}


###Method: PATCH 
Path: api/student/{studentId}/addInstructor/{instructorID}


###Method: DELETE 
Path: api/student/{studentId}


###Method: DELETE 
Path: api/student/{studentId}/deleteInstructor/{instructorId}


## Instructor endpoint -> api/instructor
Method: GET -> Instructors
Path: api/instructor 


###Method: GET -> Instructor
Path: api/instructor/ pramas = { studentId , firstName , lastName} 


###Method: GET -> Instructors
Path: api/instructor/{studentId}


###Method: GET -> Students
Path: api/instructor/{studentId}/student


###Method: POST <- Instructor
Path: api/instructor/ 


###Method: PUT <- Instructor
Path: api/instructor/{instructorId}


###Method: PATCH 
Path: api/instructor/{studentId}/addInstructor/{instructorId}


###Method: DELETE 
Path: api/instructor/{instructorId}


###Method: DELETE 
Path: api/student/{studentId}/deleteInstructor/{instructorId}
