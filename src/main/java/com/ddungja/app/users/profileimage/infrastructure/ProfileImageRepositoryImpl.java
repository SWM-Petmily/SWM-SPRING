package com.ddungja.app.users.profileimage.infrastructure;


import com.ddungja.app.users.profileimage.service.port.ProfileImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProfileImageRepositoryImpl {

    private final ProfileImageRepository profileImageRepository;
}
