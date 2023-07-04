package com.ddungja.app.users.user.domain;


import com.ddungja.app.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String email;
    private String nickname;
    private String birth;
    private String phone;
    private Provider provider;
    private boolean isProfile;

    @Builder
    private User(Long id, String email, String nickName, String birth, String phone, Provider provider, boolean isProfile, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.email = email;
        this.nickname = nickName;
        this.birth = birth;
        this.phone = phone;
        this.provider = provider;
        this.isProfile = isProfile;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }


    public void createProfile() {
        this.isProfile = true;
    }
}
