package com.ddungja.petmily.redis;


import lombok.Data;

import java.io.Serializable;

@Data

public class NoticeDto implements Serializable{

    private  Long id;
    private  String notice;

    public NoticeDto(Long id, String notice) {
        this.id = id;
        this.notice = notice;
    }
}
