package com.ddungja.petmily;

import com.ddungja.petmily.apply.domain.ApprovalType;
import com.ddungja.petmily.post.domain.type.PostStatusType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AppApplicationTests {


    @DisplayName("apply 값 생성")
    @Test
    public void apply() {
        int num = 1;
        System.out.println("insert into applys (apply_id, user_id, post_id, seller_id, approval, create_date, update_date, job, environment, people, open_talk,region, is_experience, url ,comment) values");

        for (ApprovalType value : ApprovalType.values()) {
            for (int i = 0; i < 50; i++) {
                System.out.println("(" + num + ", 2, 1, 1, '" + value + "', now(), now(), '직장인', '집', 3, 'www.naver.com', '서울', true,  'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk', '잘 키울 수 있음'),");
                num++;
            }
        }

        for (ApprovalType value : ApprovalType.values()) {
            for (int i = 0; i < 50; i++) {
                System.out.println("(" + num + ", 1, 201, 2, '" + value + "', now(), now(), '직장인', '집', 3, 'www.naver.com', '서울', true,  'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk', '잘 키울 수 있음'),");
                num++;
            }
        }

        System.out.println("insert into posts (age, adopter,advantage,average_cost,birth,create_date,disadvantage,gender,main_category_id,money,name,neutered,reason,region,reports,status,sub_category_id,thumbnail_image,update_date,user_id,views,post_id) values");
        num = 1;
        for (PostStatusType value : PostStatusType.values()) {
            for (int i = 0; i < 50; i++) {
                System.out.println("(15, '입양자', '장점','평균비용','2022-03',now(),'단점','MALE',1,'100000','테스트','YES','이유','서울',1,'"+value+"',1,'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk',now(),1,1,"+num+"),");
                num++;
            }

        }
        for (PostStatusType value : PostStatusType.values()) {
            for (int i = 0; i < 50; i++) {
                System.out.println("(15, '입양자', '장점','평균비용','2022-03',now(),'단점','MALE',1,'100000','테스트','YES','이유','서울',1,'"+value+"',1,'https://encrypted-tbn1.gstatic.com/licensed-image?q=tbn:ANd9GcREj22c-wMNL5IDmU99v8G7voUl17Yxm0JJqMLqttdPT4DnaB99zqVK7HWiNzjP3aZnzCEf-ikAqb2yiDk',now(),2,1,"+num+"),");
                num++;
            }

        }


    }
}
