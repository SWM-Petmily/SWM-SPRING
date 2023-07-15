package com.ddungja.petmily;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class AppApplicationTests {

    
    @Test
    public void test(){
        LocalDate start = LocalDate.of(2022, 7, 1);

        LocalDate end = LocalDate.now();
        System.out.println(ChronoUnit.MONTHS.between(start, end));
    }
}
