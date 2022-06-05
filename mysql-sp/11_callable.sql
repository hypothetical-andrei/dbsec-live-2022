delimiter $$
create procedure test_proc(in a int, inout b int, out c int)
begin
	declare tempa int default 0;
  declare tempb int default 1;
  select tempa + a into b;
  select tempb + a into c;
end$$
delimiter ;

call test_proc(1, @b, @c);
select @b;
select @c;