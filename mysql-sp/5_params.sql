delimiter $$
create procedure get_total_by_country(in c varchar(2))
begin
  declare total int default 0;
	select sum(quantity) into total from orders where country = c;
  select total;
end$$
delimiter ;

call get_total_by_country('us');