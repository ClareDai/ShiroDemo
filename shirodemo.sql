create table shirodemo;
use shirodemo;

create table role(
rid int primary key auto_increment,
rname varchar(50) not null
);

insert into role(rname) values('admin');
insert into role(rname) values('manager');
insert into role(rname) values('normal');

create table user(
uid int primary key auto_increment,
uname varchar(50) not null,
upwd varchar(50) not null,
rid int,
foreign key (rid) references role(rid)
);

insert into user(uname,upwd,rid) values('tom','93c3ef4bc1b01b865028192eeda1a41d',1);
insert into user(uname,upwd,rid) values('jack','d71c5a8dac1256f42a90a78227e40cfa',2);
insert into user(uname,upwd,rid) values('rose','d083c028d8ac0ba5ba89605ed390de74',3);


create table permission(
pid int primary key auto_increment,
pname varchar(50) not null,
rid int,
foreign key (rid) references role(rid)
);

insert into permission(pname,rid) values('add',2);
insert into permission(pname,rid) values('del',1);
insert into permission(pname,rid) values('update',2);
insert into permission(pname,rid) values('query',3);