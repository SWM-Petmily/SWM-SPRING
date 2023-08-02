package com.ddungja.petmily.user.repository;

import com.ddungja.petmily.user.domain.profile.Profile;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    EntityManager entityManager;
    
    @Test
    public void test1() {
        //given

        //when
        //then
        List<Profile> resultList1 = entityManager.createQuery("select p from Profile p  join fetch p.experiences e ", Profile.class).getResultList();
        System.out.println("resultList = " + resultList1.size());
    }

}