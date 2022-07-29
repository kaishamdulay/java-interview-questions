
use powerplant;
create table powerplant(
   powerplant_id INT NOT NULL AUTO_INCREMENT,
   powerplant_name VARCHAR(100) NOT NULL,
   powerplant_code VARCHAR(40) NOT NULL,
   powerplant_year VARCHAR(40) NOT NULL,
   PRIMARY KEY ( powerplant_id )
);

use powerplant;
create table generator(

   id INT NOT NULL AUTO_INCREMENT,
   generator_id VARCHAR(100) NOT NULL,
   powerplant_id INT,
   generator_status VARCHAR(100) NOT NULL,
   generator_annual_net VARCHAR(40) NOT NULL,
   PRIMARY KEY ( id )
);


insert into powerplant values(1,'7-Mile Ridge Wind Project','AK',2019);
insert into powerplant values(2,'Agrium Kenai Nitrogen Operations','AK',2019);
insert into powerplant values(3,'Alakanuk','AK',2019);
insert into powerplant values(4,'Allison Creek Hydro','AK',2019);
insert into powerplant values(5,'Ambler','AK',2019);
insert into powerplant values(6,'Anchorage 1','AK',2019);


insert into generator values(1,'WT1',(select powerplant_id from powerplant where powerplant_name='7-Mile Ridge Wind Project'),'CN','');
insert into generator values(2,'744A',(select powerplant_id from powerplant where powerplant_name='Agrium Kenai Nitrogen Operations'),'OS','');
insert into generator values(3,'744B',(select powerplant_id from powerplant where powerplant_name='Agrium Kenai Nitrogen Operations'),'OS','');
insert into generator values(4,'744C',(select powerplant_id from powerplant where powerplant_name='Agrium Kenai Nitrogen Operations'),'OS','');
insert into generator values(5,'744D',(select powerplant_id from powerplant where powerplant_name='Agrium Kenai Nitrogen Operations'),'OS','');
insert into generator values(6,'744E',(select powerplant_id from powerplant where powerplant_name='Agrium Kenai Nitrogen Operations'),'OS','');
insert into generator values(7,'644A',(select powerplant_id from powerplant where powerplant_name='Agrium Kenai Nitrogen Operations'),'RE','');
insert into generator values(8,'644B',(select powerplant_id from powerplant where powerplant_name='Agrium Kenai Nitrogen Operations'),'RE','');
insert into generator values(9,'644C',(select powerplant_id from powerplant where powerplant_name='Agrium Kenai Nitrogen Operations'),'RE','');
insert into generator values(10,'644D',(select powerplant_id from powerplant where powerplant_name='Agrium Kenai Nitrogen Operations'),'RE','');
insert into generator values(11,'644E',(select powerplant_id from powerplant where powerplant_name='Agrium Kenai Nitrogen Operations'),'RE','');
insert into generator values(12,'644F',(select powerplant_id from powerplant where powerplant_name='Agrium Kenai Nitrogen Operations'),'RE','');
insert into generator values(13,'644G',(select powerplant_id from powerplant where powerplant_name='Agrium Kenai Nitrogen Operations'),'RE','');
insert into generator values(14,'UNIT4',(select powerplant_id from powerplant where powerplant_name='Alakanuk'),'SB','(14)');
insert into generator values(15,'G309',(select powerplant_id from powerplant where powerplant_name='Alakanuk'),'CN','');
insert into generator values(16,'UNIT1',(select powerplant_id from powerplant where powerplant_name='Alakanuk'),'RE','');
insert into generator values(17,'UNIT2',(select powerplant_id from powerplant where powerplant_name='Alakanuk'),'RE','');
insert into generator values(18,'UNIT3',(select powerplant_id from powerplant where powerplant_name='Alakanuk'),'RE','');
insert into generator values(19,'GEN1',(select powerplant_id from powerplant where powerplant_name='Allison Creek Hydro'),'OP','19135');
insert into generator values(20,'1',(select powerplant_id from powerplant where powerplant_name='Ambler'),'OP','428');
insert into generator values(21,'2',(select powerplant_id from powerplant where powerplant_name='Ambler'),'OP','321');
insert into generator values(22,'3',(select powerplant_id from powerplant where powerplant_name='Ambler'),'OP','428');
insert into generator values(24,'3R',(select powerplant_id from powerplant where powerplant_name='Anchorage 1'),'OP','9491');
insert into generator values(25,'4',(select powerplant_id from powerplant where powerplant_name='Anchorage 1'),'OP','5241');
insert into generator values(26,'P1 BS',(select powerplant_id from powerplant where powerplant_name='Anchorage 1'),'OP','5');
insert into generator values(27,'1',(select powerplant_id from powerplant where powerplant_name='Anchorage 1'),'RE','');
insert into generator values(28,'2',(select powerplant_id from powerplant where powerplant_name='Anchorage 1'),'RE','');
insert into generator values(29,'3',(select powerplant_id from powerplant where powerplant_name='Anchorage 1'),'RE','');
insert into generator values(30,'D1',(select powerplant_id from powerplant where powerplant_name='Anchorage 1'),'RE','');
insert into generator values(31,'D2',(select powerplant_id from powerplant where powerplant_name='Anchorage 1'),'RE','');



