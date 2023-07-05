package com.ddungja.petmily;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootTest
class AppApplicationTests {

	@Test
	void contextLoads() {
		HikariDataSource hikariDataSource = new HikariDataSource();

		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

	}

}
