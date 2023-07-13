package com.ddungja.petmily.registration.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class RegistrationApiItem {
    String rfidCd;
    String dogNm;
    String sexNm;
    String kindNm;
    String neuterYn;
    String orgNm;
    String officeTel;
    String aprGbNm;
    String dogRegNo;

    @Builder
    public RegistrationApiItem(String dogRegNo, String rfidCd, String dogNm, String sexNm, String kindNm, String neuterYn, String orgNm, String officeTel, String aprGbNm) {
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
