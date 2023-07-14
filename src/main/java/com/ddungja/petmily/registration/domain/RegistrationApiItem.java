package com.ddungja.petmily.registration.domain;

import lombok.*;

@Getter
public class RegistrationApiItem {
    private final String rfidCd;
    private final String dogNm;
    private final String sexNm;
    private final String kindNm;
    private final String neuterYn;
    private final String orgNm;
    private final String officeTel;
    private final String aprGbNm;
    private final String dogRegNo;

    @Builder
    private RegistrationApiItem(String dogRegNo, String rfidCd, String dogNm, String sexNm, String kindNm, String neuterYn, String orgNm, String officeTel, String aprGbNm) {
        this.dogRegNo = dogRegNo;
        this.rfidCd = rfidCd;
        this.dogNm = dogNm;
        this.sexNm = sexNm;
        this.kindNm = kindNm;
        this.neuterYn = neuterYn;
        this.orgNm = orgNm;
        this.officeTel = officeTel;
        this.aprGbNm = aprGbNm;
    }


}
