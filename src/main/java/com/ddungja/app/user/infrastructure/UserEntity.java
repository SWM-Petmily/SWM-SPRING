package com.ddungja.app.user.infrastructure;


import com.ddungja.app.common.domain.BaseEntity;
import com.ddungja.app.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String email;
    private String nickName;
    private String birth;
    private String phone;
    private String provider;
    private boolean isProfile;

    @Builder
    private UserEntity(Long id, String email, String nickName, String birth, String phone, String provider, boolean isProfile, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.birth = birth;
        this.phone = phone;
        this.provider = provider;
        this.isProfile = isProfile;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static UserEntity from(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .birth(user.getBirth())
                .phone(user.getPhone())
                .nickName(user.getNickName())
                .provider(user.getProvider())
                .isProfile(user.isProfile())
                .createDate(user.getCreateDate())
                .updateDate(user.getUpdateDate())
                .build();
    }

    public User toDomain() {
        return User.builder()
                .id(id)
                .email(email)
                .birth(birth)
                .phone(phone)
                .nickName(nickName)
                .provider(provider)
                .isProfile(isProfile)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }
}
