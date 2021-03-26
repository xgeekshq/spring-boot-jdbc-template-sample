CREATE TABLE IF NOT EXISTS car (
   id SERIAL NOT NULL PRIMARY KEY,
   name varchar(50),
   city varchar(100),
   model varchar(150),
   color varchar(50)
);