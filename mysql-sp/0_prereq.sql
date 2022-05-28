create table orders (
  id int primary key auto_increment,
  description varchar(20),
  quantity int
);

insert into orders (description, quantity) values ('a', 100), ('b', 40), ('c', 200);