create table usuarios (
id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
username varchar(50), 
password varchar(50));