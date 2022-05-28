delimiter $$
create procedure get_country_list(inout country_list varchar(255))
begin
  declare current varchar(2) default '';
  declare finished integer default 0;

  declare cur_countries cursor for select country from orders;

  declare continue handler for not found set finished = 1;

  open cur_countries;

  get_countries: loop
    fetch cur_countries into current;
    if finished = 1 then 
      leave get_countries;
    end if;
    set country_list = concat(country_list,'|', current);
  end loop get_countries;

  close cur_countries;
end$$
delimiter ;


set @countries = 'ro';
call get_country_list(@countries);
select @countries;