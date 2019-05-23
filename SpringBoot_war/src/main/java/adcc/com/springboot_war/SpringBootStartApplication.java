package adcc.com.springboot_war;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class SpringBootStartApplication {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(SpringbootWarApplication.class);
    }
}
