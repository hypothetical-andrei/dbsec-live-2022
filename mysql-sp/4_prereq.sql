drop table orders;

create table orders (
  id int primary key auto_increment,
  description varchar(20),
  quantity int,
  country varchar(2)
);

insert into orders (description, quantity, country) values ('a', 100,'us'), ('b', 40,'ca'), ('c', 200,'us');