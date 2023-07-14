package com.ddungja.petmily.registration.service;

import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.post.domain.MainCategory;
import com.ddungja.petmily.post.domain.SubCategory;
import com.ddungja.petmily.post.repository.MainCategoryRepository;
import com.ddungja.petmily.post.repository.SubCategoryRepository;
import com.ddungja.petmily.registration.client.RegistrationApiClient;
import com.ddungja.petmily.registration.domain.Registration;
import com.ddungja.petmily.registration.domain.RegistrationApiItem;
import com.ddungja.petmily.registration.domain.request.RegistrationCreateRequest;
import com.ddungja.petmily.registration.repository.RegistrationRepository;
import com.ddungja.petmily.user.domain.User;
import com.ddungja.petmily.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final RegistrationApiClient registrationApiClient;

    public Registration register(Long userId, RegistrationCreateRequest registrationCreateRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Registration registration = getApiResult(user, registrationCreateRequest);
        return registrationRepository.save(registration);
    }

    private Registration getApiResult(User user, RegistrationCreateRequest registrationCreateRequest) {
        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("owner_nm", registrationCreateRequest.getOwner_nm());
        requestParam.set("dog_reg_no", registrationCreateRequest.getDog_reg_no());
        requestParam.set("serviceKey", registrationCreateRequest.getServiceKey());
        requestParam.set("_type", "json");

        RegistrationApiItem item = registrationApiClient.getAnimalInfo(requestParam).getResponse().getBody().getItem();
        if(item == null) throw new CustomException(REGISTER_NOT_FOUND);

        if(registrationRepository.findByDogRegNo(item.getDogRegNo()).isPresent()) throw new CustomException(REGISTER_ALREADY_EXISTS);

        MainCategory mainCategory = mainCategoryRepository.findByName("강아지");

        SubCategory petSubcategory = subCategoryRepository.findByName(item.getKindNm()).orElseGet(() -> {
            SubCategory subCategory = SubCategory.builder()
                    .name(item.getKindNm())
                    .mainCategory(mainCategory)
                    .build();
            return subCategoryRepository.save(subCategory);
        });
        Registration registration = Registration.from(item, user, petSubcategory);
        log.debug("반려동물 등록 결과 = {}", registration);
        return registration;
    }
}
