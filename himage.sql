create table if not exists image(
  id int(8) not null primary key,
  uid char(12),
  relpath varchar(128) not null, -- 相对url路径
  location varchar(128) not null,
  create_time timestamp default now(),
  status int(1) default 0 -- 0:正常 1:禁用
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table if not exists user(
  uid char(12) not null primary key,
  username varchar(18) not null,
  password varchar(256) not null,
  email varchar(128),
  create_time timestamp default now(),
  status int(1) default 0 -- 0:正常 1:禁用
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into user(uid,username,password,email) values('u456','akame','aa1d53c56fb827c6f3895fd88a9767c8','akame@gentlehu.com');
insert into user(uid,username,password,email) values('u457','alisa','c72549dc8c58b7e32a33f784055ef1c2','alisa@gentlehu.com');

insert into image(id,uid,relpath,location) values('a124','u456','2018/01/12/c782.jpg','2018/01/12/c782.jpg');
insert into image(id,uid,relpath,location) values('a125','u456','2018/01/12/c784.jpg','2018/01/12/c784.jpg');
insert into image(id,uid,relpath,location) values('a123','u456','2018/01/12/c789.jpg','2018/01/12/c789.jpg');
insert into image(id,uid,relpath,location) values('a126','u457','2018/01/12/c785.jpg','2018/01/12/c785.jpg');