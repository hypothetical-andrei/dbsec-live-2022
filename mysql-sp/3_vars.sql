delimiter $$
create procedure get_total()
begin
  declare total int default 0;
	select sum(quantity) into total from orders;
  select total;
end$$
delimiter ;

