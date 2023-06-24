package com.ddungja.app.users.user.domain;

import lombok.Data;

@Data
public class KakaoProfile {

    public long id;
    public String connected_at;
    public Properties properties;
    public KakaoAccount kakao_account;

    @Data
    public static class KakaoAccount {
        public boolean profile_nickname_needs_agreement;
        public Profile profile;
        public boolean has_email;
        public boolean email_needs_agreement;
        public boolean is_email_valid;
        public boolean is_email_verified;
        public String email;
        public boolean has_age_range;
        public boolean age_range_needs_agreement;
        public String age_range;
        public boolean has_birthday;
        public boolean birthday_needs_agreement;
        public String birthday;
        public String birthday_type;
        public boolean has_gender;
        public boolean gender_needs_agreement;
        public String gender;
    }

    @Data
    public static class Profile {
        public String nickname;
    }

    @Data
    public static class Properties {
        public String nickname;
    }

    public String getEmail() {
        return kakao_account.getEmail();
    }

    public String getNickName() {
        return properties.getNickname();
    }

    public String getGender() {
        return kakao_account.getGender();
    }

}

