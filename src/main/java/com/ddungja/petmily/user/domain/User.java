package com.ddungja.petmily.user.domain;


import com.ddungja.petmily.common.domain.BaseTimeEntity;
import com.ddungja.petmily.user.domain.request.UserCreateRequest;
import com.ddungja.petmily.user.domain.request.UserUpdateRequest;
import jakarta.persistence.*;
import lombok.*;

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
    @Enumerated(EnumType.STRING)
    private ProviderType provider;
    private boolean isProfile;
    private boolean isCertification;

    @Builder
    private User(Long id, String email, String nickname, String birth, String phone, ProviderType provider, boolean isProfile, boolean isCertification) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.birth = birth;
        this.phone = phone;
        this.provider = provider;
        this.isProfile = isProfile;
        this.isCertification = isCertification;
    }

    public void createProfile() {
        this.isProfile = true;
    }

    public void certicate(UserCreateRequest userCreateRequest) {
        this.nickname = userCreateRequest.getNickname();
        this.phone = userCreateRequest.getPhone();
        this.birth = userCreateRequest.getBirth();
        this.isCertification = true;
    }

    public void update(UserUpdateRequest userUpdateRequest) {
        this.nickname = userUpdateRequest.getNickname();

    }
}
