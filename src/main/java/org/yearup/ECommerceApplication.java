package org.yearup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceApplication
{

    public static void main(String[] args) {
        String dbName = System.getenv("DB_NAME");
        System.out.println("DB_NAME = " + dbName);
        if (dbName != null && !dbName.isBlank()) {
            String bannerResource = "classpath:banner-" + dbName.toLowerCase() + ".txt";
            System.setProperty("spring.banner.location", bannerResource);
        }
        SpringApplication.run(ECommerceApplication.class, args);
    }

}
