package com.ddungja.app.profile.service.port;


import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
}
