delimiter $$
create procedure check_limit_by_country(in c varchar(2), in level int, out reached boolean)
begin
  declare total int default 0;
	select sum(quantity) into total from orders where country = c;
  if total > level then
    set reached = true;
  end if;
end$$
delimiter ;

call check_limit_by_country('us', 200, @reached);
select @reached;