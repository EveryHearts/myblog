show databases;

create database `myblog_db`;

use `myblog_db`;

show tables;

drop table if exists `user_search`;
create table `user_search`(
  `user_id` bigint not null auto_increment,
  `user_nickname` varchar(12) not null,
  `user_viaSrc` varchar(255) default null,
  `user_viaByte` blob default null,
  `is_tourist` int default 0,
  `user_status` int default 0,
  `user_ip` varchar(32) not null,
  `create_date` datetime not null,
  `modify_date` datetime default null,
  primary key(`user_id`)
)engine=InnoDB auto_increment=1000 default charset=utf8;

alter table `user_search` modify `user_viaByte` longblob;

insert into `user_search` (`user_nickname`,`user_status`,`user_ip`,`create_date`)
values('铁血丹心',1,'223.89.86.213',now());

update `user_search` set `is_tourist` = 1
where `user_id`=1000;

select * from `user_search`;

drop table if exists `user_info`;
create table `user_info`(
  `user_id` bigint not null,
  `realname` varchar(12) default null,
  `gender` int default 0,
  `age` int(2) default 0,
  `phone_num` varchar(11) default null,
  `qq_num` varchar(32) default null,
  `address` varchar(255) default null,
  `description` varchar(512) default null,
  `create_date` datetime not null,
  `modify_date` datetime default null,
  primary key(`user_id`)
)engine=InnoDB default charset=utf8;

drop table if exists `user_login`;
create table `user_login`(
  `user_id` bigint not null,
  `user_auth` int default 0,
  `user_password` varchar(32) not null,
  `user_security_code` varchar(32) not null,
  `user_account` varchar(32) not null,
  `user_status` int default 0,
  `create_date` datetime not null,
  `modify_date` datetime default null,
  primary key(`user_id`)
)engine=InnoDB default charset=utf8;

drop table if exists `article_info`;
create table `article_info`(
  `article_id` bigint not null auto_increment,
  `user_id` bigint not null,
  `title` varchar(32) not null,
  `content` text not null,
  `label_id` int not null,
  `like_num` bigint default 0,
  `article_auth` int default 0,
  `article_status` int default 0,
  `create_date` datetime not null,
  `modify_date` datetime default null,
  primary key(`article_id`),
  key `FK_article_user_id` (`user_id`),
  constraint `FK_article_user_id` foreign key (`user_id`)
  references `user_search`(`user_id`)
)engine=InnoDB auto_increment=1 default charset=utf8;

drop table if exists `article_img`;
create table `article_img`(
  `article_img_id` bigint not null auto_increment,
  `article_id` bigint not null,
  `img_src` varchar(255) not null,
  `img_byte` blob not null,
  `img_type` int not null,
  `img_status` int default 0,
  `create_date` datetime not null,
  `modify_date` datetime default null,
  primary key(`article_img_id`),
  key `FK_article_id` (`article_id`),
  constraint `FK_article_id` foreign key(`article_id`)
  references `article_info`(`article_id`)
)engine=InnoDB auto_increment=1 default charset=utf8;

alter table `article_img` modify `img_byte` longblob;

drop table if exists `article_comment`;
create table `article_comment`(
  `article_comment_id` bigint not null auto_increment,
  `article_id` bigint not null,
  `content` varchar(512) not null,
  `user_id` bigint not null,
  `client_id` bigint default 0,
  `comment_type` int default 0,
  `comment_status` int default 0,
  `create_date` datetime not null,
  `modify_date` datetime default null,
  primary key(`article_comment_id`),
  key `FK_article_comment_article_id` (`article_id`),
  constraint `FK_article_comment_article_id` foreign key(`article_id`)
  references `article_info`(`article_id`)
)engine=InnoDB auto_increment=1 default charset=utf8;

drop table if exists `link_info`;
create table `link_info`(
  `link_id` bigint not null auto_increment,
  `user_id` bigint not null,
  `link_src` varchar(512) not null,
  `title` varchar(32) not null,
  `description` varchar(256) not null,
  `label_id` int not null,
  `like_num` bigint default 0,
  `link_status` int default 0,
  `create_date` datetime not null,
  `modify_date` datetime default null,
  primary key(`link_id`),
  key `FK_link_user_id` (`user_id`),
  constraint `FK_link_user_id` foreign key(`user_id`)
  references `user_search`(`user_id`)
)engine=InnoDB auto_increment=1 default charset=utf8;

drop table if exists `link_comment`;
create table `link_comment`(
  `link_comment_id` bigint not null auto_increment,
  `link_id` bigint not null,
  `content` varchar(512) not null,
  `user_id` bigint not null,
  `client_id` bigint default 0,
  `comment_type` int default 0,
  `comment_status` int default 0,
  `create_date` datetime not null,
  `modify_date` datetime default null,
  primary key(`link_comment_id`),
  key `FK_link_comment_link_id` (`link_id`),
  constraint `FK_link_comment_link_id` foreign key(`link_id`)
  references `link_info` (`link_id`)
)engine=InnoDB auto_increment=1 default charset=utf8;

drop table if exists `file_info`;
create table `file_info`(
  `file_id` bigint not null auto_increment,
  `user_id` bigint not null,
  `file_name` varchar(256) not null,
  `file_auth` int default 1,
  `like_num` bigint default 0,
  `file_type` int not null,
  `file_path` varchar(512) not null,
  `file_status` int default 0,
  `create_date` datetime not null,
  `modify_date` datetime default null,
  primary key(`file_id`),
  key `FK_file_user_id` (`user_id`),
  constraint `FK_file_user_id` foreign key (`user_id`)
  references `user_search`(`user_id`)
)engine=InnoDB auto_increment=1 default charset=utf8;

drop table if exists `file_byte`;
create table `file_byte`(
  `file_id` bigint not null,
  `byte` blob not null,
  primary key(`file_id`)
)engine=InnoDB default charset=utf8;

alter table `file_byte` modify `byte` longblob;

drop table if exists `file_comment`;
create table `file_comment`(
  `file_comment_id` bigint not null auto_increment,
  `file_id` bigint not null,
  `content` varchar(512) not null,
  `user_id` bigint not null,
  `client_id` bigint default 0,
  `comment_type` int default 0,
  `comment_status` int default 0,
  `create_date` datetime not null,
  `modify_date` datetime default null,
  primary key(`file_comment_id`),
  key `FK_file_comment_file_id` (`file_id`),
  constraint `FK_file_comment_file_id` foreign key(`file_id`)
  references `file_info` (`file_id`)
)engine=InnoDB auto_increment=1 default charset=utf8;

drop table if exists `message`;
create table `message`(
  `message_id` bigint not null auto_increment,
  `user_id` bigint not null,
  `visitor_id` bigint not null,
  `content` varchar(512) not null,
  `message_status` int default 0,
  `message_type` int default 0,
  `create_date` datetime not null,
  `modify_date` datetime default null,
  primary key(`message_id`)
)engine=InnoDB auto_increment=1 default charset=utf8;

drop table if exists `notify_info`;
create table `notify_info`(
  `notify_id` bigint not null auto_increment,
  `user_id` bigint default 0,
  `origin_user_id` bigint default 0,
  `origin_id` bigint default 0,
  `content` varchar(512) not null,
  `notify_type` int not null,
  `notify_auth` int not null,
  `notify_status` int default 0,
  `create_date` datetime not null,
  `modify_date` datetime default null,
  primary key(`notify_id`)
)engine=InnoDB auto_increment=1 default charset=utf8;

drop table if exists `label_info`;
create table `label_info`(
  `label_id` bigint not null auto_increment,
  `label_value` varchar(32) not null,
  `label_status` int default 0,
  `use_num` bigint default 0,
  `create_date` datetime not null,
  `modify_date` datetime default null,
  primary key(`label_id`)
)engine=InnoDB auto_increment=1 default charset=utf8;

drop table if exists `manage_record`;
create table `manage_record`(
  `record_id` bigint not null auto_increment,
  `manager_id` bigint not null,
  `record_status` int default 0,
  `process_result` int default 0,
  `origin_id` bigint not null,
  `origin_type` int not null,
  `origin_user_id` bigint not null,
  `content` varchar(256) default null,
  `create_date` datetime not null,
  `modify_date` datetime default null,
  primary key(`record_id`)
)engine=InnoDB auto_increment=1 default charset=utf8;

show tables;

select * from `user_search`;
select * from `user_login`;
select * from `user_info`;

update user_search set user_nickname = 'Sweet' where user_id = 1005;

insert into `label_info` (
  `label_value`,
  `use_num`,
  `create_date`
)values(
  "JAVA",
  0,
  now()
);

select * from manage_record where record_id = 1;

select * from `label_info`;

update `label_info` set `use_num` = `use_num`+1 , `modify_date` = now() where `label_id` = 1;

select count(*) from `notify_info`;
