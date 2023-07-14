package com.ddungja.petmily.registration.service;

import com.ddungja.petmily.common.domain.exception.CustomException;
import com.ddungja.petmily.post.domain.SubCategory;
import com.ddungja.petmily.post.repository.SubCategoryRepository;
import com.ddungja.petmily.registration.client.RegistrationApiClient;
import com.ddungja.petmily.registration.domain.Registration;
import com.ddungja.petmily.registration.domain.RegistrationApiItem;
import com.ddungja.petmily.registration.domain.request.RegistrationCreateRequest;
import com.ddungja.petmily.registration.repository.RegistrationRepository;
import com.ddungja.petmily.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.ddungja.petmily.common.domain.exception.ExceptionCode.REGISTER_ALREADY_EXISTS;
import static com.ddungja.petmily.common.domain.exception.ExceptionCode.REGISTER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    private final SubCategoryRepository subCategoryRepository;

    private final RegistrationRepository registrationRepository;
    @Autowired
    private RegistrationApiClient registrationApiClient;

    public Registration getApiResult(User user, RegistrationCreateRequest registrationCreateRequest) {

        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("owner_nm", registrationCreateRequest.getOwner_nm());
        requestParam.set("dog_reg_no", registrationCreateRequest.getDog_reg_no());
        requestParam.set("serviceKey", registrationCreateRequest.getServiceKey());
        requestParam.set("_type", "json");

        RegistrationApiItem item = registrationApiClient.getAnimalInfo(requestParam).getResponse().getBody().getItem();

        if(item == null) throw new CustomException(REGISTER_NOT_FOUND);

        if(registrationRepository.findByDogRegNo(item.getDogRegNo()).isPresent()) throw new CustomException(REGISTER_ALREADY_EXISTS);

        SubCategory petSubcategory = subCategoryRepository.findByName(item.getKindNm()).orElseGet(() -> {
            SubCategory subCategory = SubCategory.builder()
                    .name(item.getKindNm())
                    .build();
            return subCategoryRepository.save(subCategory);
        });

        Registration registration = Registration.from(item, user, petSubcategory);
        log.debug("반려동물 등록 결과 = {}", registration);

        return registration;
    }

    public Registration register(User user, RegistrationCreateRequest registrationCreateRequest) {
        Registration registration = getApiResult(user, registrationCreateRequest);
         registrationRepository.save(registration);
         return registration;
    }
}
