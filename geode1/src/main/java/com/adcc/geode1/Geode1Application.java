package com.adcc.geode1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Geode1Application {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Geode1Application.class, args);

       GeodeController geodecon = new GeodeController();
       geodecon.geodeControl();
    }

}
