select curdate();

select curtime();

select SYSDATE();

select now();


select Country , COUNT(Medal) cnt  
from summermedals 
group by Medal, Country  
having Medal='Gold'
order BY cnt desc
limit 10;

-- member : 아이디, 비번, 이름, 이메일
create table member(
	mno 	int          primary key	auto_increment,
	userid  varchar(18)  not null,
	passwd  varchar(18)  not null,
	name    varchar(20)  not null,
	email   varchar(100) not null,
	regdate datetime 	 default current_timestamp
);

select * from member;
drop table member;
	
-- board : 글번호,제목, 작성자, 작성일, 조회수, 본문
create table board(
	bno 		int primary key 	auto_increment,
	title 		varchar(100) 		not null,
	userid 		varchar(50) 		not null,
	regdate 	datetime			default current_timestamp,
	views 		int					default 0,
	contents 	text				
);

create procedure loop_insert(in cnt int)
begin
	declare i int default 1;
	while (i <= cnt) do
	insert into board (title, userid, contents)
	values('aaaa', '11', '가');
	insert into board (title, userid, contents)
	values('bbbb', '22', '나');
	insert into board (title, userid, contents)
	values('ccccc', '33', '다');
	set i = i + 1;
	end while;
end;

call loop_insert(200000);

select * from board;
select count(bno) sum_board from board
drop table board;
	

