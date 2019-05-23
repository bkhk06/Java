package adcc.com.springboot_war;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringbootWarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWarApplication.class, args);
    }

}
