package com.ddungja.petmily.registration.service;

import com.ddungja.petmily.common.exception.CustomException;
import com.ddungja.petmily.post.domain.MainCategory;
import com.ddungja.petmily.post.domain.SubCategory;
import com.ddungja.petmily.post.repository.MainCategoryRepository;
import com.ddungja.petmily.post.repository.SubCategoryRepository;
import com.ddungja.petmily.registration.controller.response.RegistrationApiResponse;
import com.ddungja.petmily.registration.domain.Registration;
import com.ddungja.petmily.registration.domain.request.RegistrationCreateRequest;
import com.ddungja.petmily.registration.repository.RegistrationApiClient;
import com.ddungja.petmily.registration.repository.RegistrationRepository;
import com.ddungja.petmily.user.domain.user.User;
import com.ddungja.petmily.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ddungja.petmily.common.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RegistrationService {

    @Value("${api.serviceKey}")
    private String serviceKey;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final RegistrationApiClient registrationApiClient;

    @Transactional
    public Registration register(Long userId, RegistrationCreateRequest registrationCreateRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        MainCategory mainCategory = mainCategoryRepository.findByName("강아지").orElseThrow(() -> new CustomException(MAIN_CATEGORY_NOT_FOUND));
        if (registrationRepository.findByRegistrationNumber(registrationCreateRequest.getDogRegistrationNumber()).isPresent()) {
            throw new CustomException(REGISTER_ALREADY_EXISTS);
        }
        RegistrationApiResponse.RegistrationApiItem registerInfo = getRegistrationInfo(registrationCreateRequest);
        SubCategory petSubcategory = subCategoryRepository.findByName(registerInfo.getKindNm()).orElseGet(() ->
             subCategoryRepository.save(SubCategory.builder()
                    .name(registerInfo.getKindNm())
                    .mainCategory(mainCategory).build())
        );
        return registrationRepository.save(Registration.from(registerInfo, user, petSubcategory));
    }


    private RegistrationApiResponse.RegistrationApiItem getRegistrationInfo(RegistrationCreateRequest registrationCreateRequest) {
        RegistrationApiResponse registerInfo = registrationApiClient.getAnimalInfo(registrationCreateRequest.getOwnerName(), registrationCreateRequest.getDogRegistrationNumber(), serviceKey, "json");
        if (registerInfo.getResponse().getBody() == null || registerInfo.getResponse().getBody().getItem() == null) {
            throw new CustomException(REGISTER_NOT_FOUND);
        }
        return registerInfo.getResponse().getBody().getItem();
    }

    public List<Registration> getMyRegister(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        return registrationRepository.findByUserId(user.getId());
    }

    @Transactional
    public void delete(Long userId, Long registrationId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Registration registration = registrationRepository.findById(registrationId).orElseThrow(() -> new CustomException(REGISTER_NOT_FOUND));
        if(!registration.getUser().getId().equals(user.getId())){
            throw new CustomException(REGISTER_NOT_FOUND);
        }
        registrationRepository.delete(registration);
    }

    @Transactional
    public Registration select(Long userId, Long registrationId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Registration registration = registrationRepository.findByIdAndUserId(registrationId,userId).orElseThrow(() -> new CustomException(REGISTER_NOT_FOUND));
        return registration;
    }
}
