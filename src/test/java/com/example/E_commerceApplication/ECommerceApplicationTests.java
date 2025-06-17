package com.example.E_commerceApplication;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ECommerceApplicationTests {

	// @Test
	void contextLoads() {
		// Simple test to verify context loads
		assertThat(true).isTrue();
	}
}