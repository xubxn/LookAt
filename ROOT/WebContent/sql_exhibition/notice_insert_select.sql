insert into notice (member_id, n_title, n_details, n_date) values ('admin', '제목02', '테스트', now());

insert into notice (member_id, n_title, n_details) 
values ('admin', '제목03', '테스트'),
('admin', '제목04', '테스트'),
('admin', '제목05', '테스트'),
('admin', '제목06', '테스트'),
('admin', '제목07', '테스트'),
('admin', '제목08', '테스트'),
('admin', '제목09', '테스트'),
('admin', '제목10', '테스트'),
('admin', '제목11', '테스트'),
('admin', '제목12', '테스트'),
('admin', '제목13', '테스트'),
('admin', '제목14', '테스트'),
('admin', '제목15', '테스트'),
('admin', '제목16', '테스트'),
('admin', '제목17', '테스트'),
('admin', '제목18', '테스트'),
('admin', '제목19', '테스트'),
('admin', '제목20', '테스트');

select * from notice;

select count(*) from notice;

select notice_no, member_id, n_title, n_details, n_date
from notice
order by notice_no desc limit 0,5;

select notice_no, member_id, n_title, n_details, n_date
from notice
where notice_no = 2;

update notice 
set n_title='수정 03', n_details='수정 테스트', n_date=DATE_FORMAT(now(),'%Y%m%d')
where notice_no = 3;

delete from notice
where notice_no = 28;

insert into notice (notice_no, member_id, n_title, n_details) 
values (1, 'admin', '제목01', '테스트');

commit;