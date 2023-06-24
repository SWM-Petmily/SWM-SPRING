package com.ddungja.app.users.user.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;


@Getter
public class User implements UserDetails {

    private final Long id;
    private final String email;
    private final String nickName;
    private final String birth;
    private final String phone;
    private final String provider;
    private final boolean isProfile;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    @Builder
    private User(Long id, String email, String nickName, String birth, String phone, String provider, boolean isProfile, LocalDateTime createDate, LocalDateTime updateDate) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
