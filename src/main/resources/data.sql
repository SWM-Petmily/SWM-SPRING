
-- user
insert into users (user_id, email, nickname, phone, provider, birth, create_date, update_date, is_profile, is_certification)
values (1, 'rldh11111@naver.com', '권기오', '01063169037', 'kakao', '0713', now(), now(), true, false);

insert into users (user_id, email, nickname, phone, provider, birth, create_date, update_date, is_profile, is_certification)
values (2, 'rldh9037@naver.com', '김아란', '01012345678', 'kakao', '0613', now(), now(), false, false);

insert into users (user_id, email, nickname, phone, provider, birth, create_date, update_date, is_profile, is_certification)
values (3, 'rldh6316@naver.com', '오승기', '01011112222', 'kakao', '0402', now(), now(), false, false);


-- profile image
insert into profile_images (profile_image_id, url, create_date, update_date)
values (1, 'data:image/jpegbase64,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', now(), now());


insert into profile_images (profile_image_id, url, create_date, update_date)
values (2, 'data:image/jpeg;base64,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', now(), now());



-- profile

insert into profiles(profile_id, user_id, profile_image_id, job, environment, people, comment,  open_talk, region, is_experience, create_date, update_date)
values (1, 1 ,1, '학생', '아파트', 4, '잘 키울 자신 있음', 'wwww.opentalk.com', '서울', TRUE, now(), now());

insert into profiles(profile_id, user_id, profile_image_id, job, environment, people, comment,  open_talk, region, is_experience, create_date, update_date)
values (3, 3 ,2, '주부', '주택', 1, '잘 키울게', 'wwww.opentalk.com', '광주', FALSE, now(), now());


--- experience
insert into experiences (experience_id, profile_id, species, period, create_date, update_date)
values (1, 1, '불독', 12, now(), now());

insert into experiences (experience_id, profile_id, species, period, create_date, update_date)
values (2, 1, '리트리버', 23, now(), now());

insert into experiences (experience_id, profile_id, species, period, create_date, update_date)
values (3, 1, '불독', 5, now(), now());




-- main_categories
insert into main_categories(main_category_id, name)
values (1, '강아지');


insert into main_categories(main_category_id, name)
values (2, '고양이');


-- sub_categories
insert into sub_categories(sub_category_id, main_category_id, name)
values (1, 1, '불독');

insert into sub_categories(sub_category_id,main_category_id, name)
values (2, 1, '골든 리트리버');

insert into sub_categories(sub_category_id,main_category_id, name)
values (3, 1, '닥스훈트');

insert into sub_categories(sub_category_id, main_category_id, name)
values (4, 2, '믹스묘');

insert into sub_categories(sub_category_id,main_category_id, name)
values (5, 2, '러시안블루');

insert into sub_categories(sub_category_id,main_category_id, name)
values (6, 2, '페르시안');

-- posts
insert
into posts (adopter,advantage,average_cost,birth,create_date,disadvantage,gender,main_category_id,money,name,neutered,reason,region,reports,status,sub_category_id,thumbnail_image,update_date,user_id,views,post_id)
values ('입양자', '장점','평균비용','2022-03',now(),'단점','MALE',1,'100000','테스트','YES','이유','서울',1,'SAVE',1,'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk',now(),1,1,1);

insert
into posts (adopter,advantage,average_cost,birth,create_date,disadvantage,gender,main_category_id,money,name,neutered,reason,region,reports,status,sub_category_id,thumbnail_image,update_date,user_id,views,post_id)
values ('입양자', '장점','평균비용','2022-03',now(),'단점','MALE',1,'100000','테스트','NO','이유','서울',1,'SAVE',1,'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk',now(),1,1,2);

insert
into posts (adopter,advantage,average_cost,birth,create_date,disadvantage,gender,main_category_id,money,name,neutered,reason,region,reports,status,sub_category_id,thumbnail_image,update_date,user_id,views,post_id)
values ('입양자', '장점','평균비용','2022-03',now(),'단점','MALE',1,'100000','테스트','NO','이유','서울',1,'SAVE',1,'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk',now(),2,1,3);


-- post_images
insert
into images (create_date,image_type,post_id,update_date,url,image_id)
values (now(),'POST',1,now(),'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk',default);

insert
into images (create_date,image_type,post_id,update_date,url,image_id)
values (now(),'POST',1,now(),'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk',default);


-- diseases
insert
into diseases (create_date,name,post_id,update_date,disease_id)
values (now(),'질병1',1,now(),default);

insert
into diseases (create_date,name,post_id,update_date,disease_id)
values (now(),'질병2',1,now(),default);

-- applys
insert into applys (apply_id, user_id, post_id, seller_id, approval, create_date, update_date, job, environment, people, open_talk,region, is_experience, url ,comment)
values(1, 2, 1, 1, 'WAITING', now(), now(), '직장인', '집', 3, 'www.naver.com', '서울', true,  'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMSEBUQEBIVEhAPFQ8PEBAQFRAPFQ8PFRUWFhUVFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMsNygtLisBCgoKDg0OGhAQGi0dHR0tKy0tLS0rLS0tKystLS0tKystLSstLS0tLS0tLS0rLS0tLS0tLSstLS0tLS0rLS0tLf', '잘 키울 수 있음');

insert into applys (apply_id, user_id, post_id, seller_id, approval, create_date, update_date, job, environment, people, open_talk,region, is_experience, url ,comment)
values(2, 1, 2, 2, 'WAITING', now(), now(), '직장인', '집', 3, 'www.naver.com', '서울', false,  'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMSEBUQEBIVEhAPFQ8PEBAQFRAPFQ8PFRUWFhUVFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMsNygtLisBCgoKDg0OGhAQGi0dHR0tKy0tLS0rLS0tKystLS0tKystLSstLS0tLS0tLS0rLS0tLS0tLSstLS0tLS0rLS0tLf', '잘 키울 수 있음');

insert into applys (apply_id, user_id, post_id, seller_id, approval, create_date, update_date, job, environment, people, open_talk,region, is_experience, url ,comment)
values(3, 3, 1, 1, 'WAITING', now(), now(), '직장인', '집', 3, 'www.naver.com', '서울', true,  'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMSEBUQEBIVEhAPFQ8PEBAQFRAPFQ8PFRUWFhUVFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMsNygtLisBCgoKDg0OGhAQGi0dHR0tKy0tLS0rLS0tKystLS0tKystLSstLS0tLS0tLS0rLS0tLS0tLSstLS0tLS0rLS0tLf', '잘 키울 수 있음');

-- apply_experiences
insert into apply_experiences(apply_experience_id, apply_id, species, period, create_date, update_date)
values (1, 1, '불독', 12, now(), now());

insert into apply_experiences(apply_experience_id, apply_id, species, period, create_date, update_date)
values (2, 1, '말티즈', 15, now(), now());

insert into apply_experiences(apply_experience_id, apply_id, species, period, create_date, update_date)
values (3, 1, '리트리버', 6, now(), now());

insert into apply_experiences(apply_experience_id, apply_id, species, period, create_date, update_date)
values (4, 3, '불독', 12, now(), now());

insert into apply_experiences(apply_experience_id, apply_id, species, period, create_date, update_date)
values (5, 3, '말티즈', 15, now(), now());

insert into apply_experiences(apply_experience_id, apply_id, species, period, create_date, update_date)
values (6, 3, '리트리버', 6, now(), now());


