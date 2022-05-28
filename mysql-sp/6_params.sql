delimiter $$
create procedure get_total_by_country_v2(in c varchar(2), out total int)
begin
	select sum(quantity) into total from orders where country = c;
end$$
delimiter ;

call get_total_by_country_v2('us', @total);
select @total;