package com.tdtu.finalproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class FinalProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "nam";
		String encodedPassword = encoder.encode(rawPassword);

		System.out.println(encodedPassword);
	}


}
