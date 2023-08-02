package com.ddungja.petmily.user.domain.user;


import com.ddungja.petmily.common.domain.BaseTimeEntity;
import com.ddungja.petmily.user.domain.certification.Certification;
import com.ddungja.petmily.user.domain.profile.ProfileImage;
import com.ddungja.petmily.user.domain.request.UserCreateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String phone;
    @Enumerated(EnumType.STRING)
    private ProviderType provider;
    private boolean isProfile;
    private boolean isCertification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_image_id")
    private ProfileImage profileImage;

    @Builder
    public User(Long id, String email, String nickname, String phone, ProviderType provider, boolean isProfile, boolean isCertification, ProfileImage profileImage) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.provider = provider;
        this.isProfile = isProfile;
        this.isCertification = isCertification;
        this.profileImage = profileImage;
    }


    public void createProfile() {
        this.isProfile = true;
    }

    public void signUp(UserCreateRequest userCreateRequest, Certification certification) {
        this.nickname = userCreateRequest.getNickname();
        this.phone = certification.getPhoneNumber();
        this.isCertification = true;
    }

    public void update(String nickname, ProfileImage profileImage) {
        this.nickname = nickname;
        this.profileImage = profileImage;

    }

    public void reset() {
        this.isCertification = false;
        this.isProfile = false;
        this.nickname = null;
        this.phone = null;
    }

    public void updateProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }
}
