-- user
insert into users (user_id, email, nickname, phone, provider, birth, create_date, update_date, is_profile, is_certification) values
(1, 'kwon@naver.com', '권기오', '01063169037', 'KAKAO', '0713', now(), now(), true, false),
(2, 'aran@naver.com', '김아란', '01012345678', 'KAKAO', '0613', now(), now(), false, false),
(3, 'seongi@naver.com', '오승기', '01011112222', 'KAKAO', '0402', now(), now(), false, false);

-- profile image
insert into profile_images (profile_image_id, url, create_date, update_date) values
(1, 'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk', now(), now()),
(2, 'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk', now(), now());

-- profile
insert into profiles(profile_id, user_id, profile_image_id, job, environment, people, comment,  open_talk, region, is_experience, create_date, update_date) values
(1, 1 ,1, '학생', '아파트', 4, '잘 키울 자신 있음', 'wwww.opentalk.com', '서울', TRUE, now(), now()),
(3, 3 ,2, '주부', '주택', 1, '잘 키울게', 'wwww.opentalk.com', '광주', FALSE, now(), now());

-- experience
insert into experiences (experience_id, profile_id, species, period, create_date, update_date) values
(1, 1, '불독', 12, now(), now()),
(2, 1, '리트리버', 23, now(), now()),
(3, 1, '불독', 5, now(), now());

-- main_categories
insert into main_categories(main_category_id, name) values
(1, '강아지'),
(2, '고양이');

-- sub_categories
insert into sub_categories(sub_category_id, main_category_id, name)values
(1, 1, '불독'),
(2, 1, '골든 리트리버'),
(3, 1, '닥스훈트'),
(4, 2, '믹스묘'),
(5, 2, '러시안블루'),
(6, 2, '페르시안');

-- posts
insert into posts (adopter,advantage,average_cost,birth,create_date,disadvantage,gender,main_category_id,money,name,neutered,reason,region,reports,status,sub_category_id,thumbnail_image,update_date,user_id,views,post_id) values
('입양자', '장점','평균비용','2022-03',now(),'단점','MALE',1,'100000','테스트','YES','이유','서울',1,'SAVE',1,'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk',now(),1,1,1),
('입양자', '장점','평균비용','2022-03',now(),'단점','MALE',1,'100000','테스트','NO','이유','서울',1,'SAVE',1,'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk',now(),1,1,2),
('입양자', '장점','평균비용','2022-03',now(),'단점','MALE',1,'100000','테스트','NO','이유','서울',1,'SAVE',1,'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk',now(),2,1,3);

-- post_images
insert into images (create_date,image_type,post_id,update_date,url,image_id) values
(now(),'POST',1,now(),'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk',default),
(now(),'POST',1,now(),'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk',default);

-- diseases
insert into diseases (create_date,name,post_id,update_date,disease_id) values
(now(),'질병1',1,now(),default),
(now(),'질병2',1,now(),default);

-- applys
insert into applys (apply_id, user_id, post_id, seller_id, approval, create_date, update_date, job, environment, people, open_talk,region, is_experience, url ,comment) values
(1, 2, 1, 1, 'WAITING', now(), now(), '직장인', '집', 3, 'www.naver.com', '서울', true,  'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk', '잘 키울 수 있음'),
(2, 1, 2, 2, 'WAITING', now(), now(), '직장인', '집', 3, 'www.naver.com', '서울', false,  'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk', '잘 키울 수 있음'),
(3, 3, 1, 1, 'WAITING', now(), now(), '직장인', '집', 3, 'www.naver.com', '서울', true,  'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk', '잘 키울 수 있음');

-- apply_experiences
insert into apply_experiences(apply_experience_id, apply_id, species, period, create_date, update_date) values
(1, 1, '불독', 12, now(), now()),
(2, 1, '말티즈', 15, now(), now()),
(3, 1, '리트리버', 6, now(), now()),
(4, 3, '불독', 12, now(), now()),
(5, 3, '말티즈', 15, now(), now()),
(6, 3, '리트리버', 6, now(), now());


