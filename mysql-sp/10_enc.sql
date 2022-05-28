create table ism_passwords (
	id int not null,
	pass varchar(50),
	encpass varbinary(50)
)engine=MyISAM default charset=utf8;

delimiter $$
drop function if exists encpassword $$
create function encpassword (s varchar(50)) returns varbinary(50) deterministic
begin
return aes_encrypt(s,'somekey');
end$$
delimiter ;

delimiter $$
drop function if exists decpassword $$
create function decpassword (s varbinary(50)) returns varchar(50) deterministic
begin
return aes_decrypt(s,'somekey');
end$$
delimiter ;

SHOW function STATUS where Db='ism_sp';

insert into ism_passwords (id, pass, encpass) values (1,'somepass',encpassword('somepass'));

create trigger ins_enc before insert on ism_passwords for each row set new.encpass = encpassword(new.encpass);

insert into ism_passwords (id, pass, encpass) values (1,'somepass','somepass');

