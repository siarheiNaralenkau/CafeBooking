create table venues(id integer primary key not null AUTO_INCREMENT, 
	unique_id varchar(35) UNIQUE, 
	name varchar(100),
	phone varchar(25),
	address varchar(100),
	city varchar(40),
	country varchar(60),
	latitude float,
	longitude float,
	category varchar(60),
	has_free_seats BOOL);
	
	