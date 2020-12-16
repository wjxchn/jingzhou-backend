create table authuser(
authuserid int primary key auto_increment,
userid int,
username varchar(200),
institutionid int,
achievements varchar(2000),
researchfield varchar(500),
realname varchar(100),
authorid varchar(50));

create table follow(
followid int primary key auto_increment,
followerid int ,
researcherid int);

create table institution(
institutionid int primary key auto_increment,
institutionname varchar(200));

create table institutionrank(
rankid int primary key auto_increment,
institutionit int,
type varchar(100),
amount int);


create table massage(
massageid int primary key auto_increment,
senderid int,
receiverid int,
content varchar(2000));

create table paperrank(
rankid int primary key auto_increment,
citation int,
paperid varchar(100),
authorid varchar(100),
papername varchar(200),
field varchar(100));

create table patent(
patentid int primary key auto_increment,
inventor varchar(100),
pubdate date,
patentname varchar(200),
pubnumber varchar(100),
patentnumber varchar(100),
applicant varchar(100),
address varchar(100),
classification varchar(100),
abstractcontent varchar(1000),
appdate date);

create table project(
projectid int primary key auto_increment,
journal varchar(100),
introduction varchar(200),
typr varchar(100),
projectname varchar(100),
institution varchar(100),
researcher varchar(100),
time date);

create table user (
userid int primary key auto_increment,
username varchar(100),
password varchar(100),
isauth int,
downloadauth int,
email varchar(300),
time date,
phone varchar(30));