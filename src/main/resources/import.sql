insert into student(name, sex, birth_date, self_Description) values ('David', 'Male', {ts '2012-09-17 18:47:52.69'}, 'blablabla...');
insert into student(name, sex, nickName) values ('Jimmy', 'Male', 'nickname');
insert into student(name, sex) values ('Angel', 'Male'); 

insert into address(location, student_id, is_Default) values ('Griffen Street #1', select id from student where id=1, true); 