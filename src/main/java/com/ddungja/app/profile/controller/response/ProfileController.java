package com.ddungja.app.profile.controller.response;

import com.ddungja.app.profile.service.port.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
}
