CREATE TABLE IF NOT EXISTS `t_prof` (
    `account` varchar(10),
    `name` varchar(10) not null,
    `password` varchar(12) not null,
    primary key(`account`)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- insert into t_prof values("123456","张老师", "123456");

CREATE TABLE IF NOT EXISTS `t_stu` (
    `account` varchar(10),
    `name` varchar(10) not null,
    `password` varchar(12) not null,
    primary key(`account`)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- insert into t_stu values("123456","张三", "123456");

CREATE TABLE IF NOT EXISTS `t_project` (
    `id` int unsigned auto_increment,
    `name` varchar(50) not null,
    `cooperation` varchar(50) not null,
    `prop` varchar(50) not null,
    `state` varchar(1) not null,
    `start` date not null,
    `end` date,
    `prof_id` varchar(10) not null,
    `remark` varchar(1000),
    `code` varchar(100),
    `code_copy` varchar(100),
    `code_state` varchar(1),
    primary key(`id`)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_manager` (
    `id` int unsigned auto_increment,
    `stu_id` varchar(10) not null,
    `proj_id` varchar(10) not null,
    primary key(`id`)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_doc` (
    `id` int unsigned auto_increment,
    `name` varchar(50) not null,
    `state` varchar(1) not null,
    `template_id` varchar(10),
    `format` varchar(1) not null,
    `doc_path` varchar(200) not null,
    `worker_id` varchar(10),
    `manager_id` varchar(10),
    `proj_id` varchar(10) not null,
    primary key(`id`)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `t_doc_template` (
    `id` int unsigned auto_increment,
    `type` varchar(10) not null,
    `expire` date not null,
    `prof_id` varchar(10) not null,
    `doc_path` varchar(200) not null,
    primary key(`id`)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_code_manage_history` (
    `id` int unsigned auto_increment,
    `doc_id` varchar(10) not null,
    `manager_id` varchar(10) not null,
    `start` date not null,
    `end` date not null,
    primary key(`id`)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_doc_manage_history` (
    `id` int unsigned auto_increment,
    `doc_id` varchar(10) not null,
    `manager_id` varchar(10) not null,
    `start` date not null,
    `end` date not null,
    primary key(`id`)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_message` (
    `id` int unsigned auto_increment,
    `type` varchar(1) not null,
    `account` varchar(10) not null,
    `message` varchar(1000) not null,
    `end` date not null,
    primary key(`id`)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- security
CREATE TABLE IF NOT EXISTS `t_user` (
    `id` bigint unsigned auto_increment,
    `username` varchar(100) not null,
    `password` varchar(100) not null,
    primary key(`id`)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- insert into t_user values("1","123456", "123456");

CREATE TABLE IF NOT EXISTS `t_role` (
    `id` bigint unsigned auto_increment,
    `username` varchar(100) not null,
    `name` varchar(10) not null,
    primary key(`id`)
    )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- insert into t_role values("1","123456", "USER");