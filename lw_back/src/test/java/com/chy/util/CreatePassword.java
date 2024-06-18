package com.chy.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class CreatePassword {
    
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();;
    
    @Test
    public void create1Password() {
        String password = "123456";
        password = passwordEncoder.encode(password);
        System.out.println(password);
    }
}
