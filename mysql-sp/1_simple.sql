delimiter $$
create procedure GetOrders()
begin
	select description, quantity from orders order by quantity;    
end$$
delimiter ;