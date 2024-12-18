package org.splitwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class SplitWiseSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SplitWiseSpringApplication.class, args);
    }
}
