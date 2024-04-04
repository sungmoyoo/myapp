-- DDL(Data Definition Language)

drop table boards;

create table boards(
  board_no int primary key auto_increment,
  title varchar(255) not null,
  content text not null,
  writer varchar(30) not null,
  created_date datetime null default now()
);

insert into boards(board_no,title,content,writer)
  values(1, '제목1','내용1','홍길동');
insert into boards(board_no,title,content,writer)
  values(2, '제목2','내용2','임꺽정');
insert into boards(board_no,title,content,writer)
  values(3, '제목3','내용3','유성모');
insert into boards(board_no,title,content,writer)
  values(4, '제목4','내용4','기모찌');
insert into boards(board_no,title,content,writer)
  values(5, '제목5','내용5','앙기모');

전체 레코드 조회
select * from boards;

특정 번호의 레코드만 조회
select * from boards where board_no = 3;

특정 번호의 레코드 수정
update boards set
  title = 'okok',
  content = 'nono',
  writer = 'hoho',
where board_no = 3;

특정 번호의 레코드 삭제
delete from boards where board_no = 3;


create table assignments(
  assignment_no int primary key auto_increment,
  title varchar(255) not null,
  content text not null,
  deadline date not null
);

insert into assignments(title, content, deadline) values('정보처리기사',' 필기', '2024-2-23');
insert into assignments(assignment_no,title, content, deadline) values(1, '복습',' 노트', '2024-5-22');
insert into assignments(title, content, deadline) values('linux','online', '2024-6-23');
insert into assignments(title, content, deadline) values('blog','포스팅', '2024-1-31');

create table members(
  member_no int primary key auto_increment,
  email varchar(255) not null,
  name varchar(30) not null,
  password varchar(256) not null,
  created_date datetime null default now()
);

insert into members(email,name,password,created_date)
  values('user1@test.com','user1',sha2('1111',256),'2024-1-1');
insert into members(email,name,password,created_date)
  values('user2@test.com','user2',sha2('1111',256),'2024-2-2');
insert into members(email,name,password,created_date)
  values('user3@test.com','user3',sha2('1111',256),'2024-3-3');
insert into members(email,name,password,created_date)
  values('user4@test.com','user4',sha2('1111',256),'2024-4-4');
insert into members(email,name,password,created_date)
  values('user5@test.com','user5',sha2('1111',256),'2024-5-5');

기존 테이블에 column 추가
alter table boards
  add column category int not null;

기존 입력된 table의 column은 category 1로 설정
update boards set category=1;
