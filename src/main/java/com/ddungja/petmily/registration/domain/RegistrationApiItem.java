package com.ddungja.petmily.registration.domain;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
public class RegistrationApiItem {
    private String rfidCd;
    private String dogNm;
    private String sexNm;
    private String kindNm;
    private String neuterYn;
    private String orgNm;
    private String officeTel;
    private String aprGbNm;
    private String dogRegNo;

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
