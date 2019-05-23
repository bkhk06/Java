package adcc.com.springboot_war;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {
    @RequestMapping("/app")
    public String app(){
        return "Application started";
    }
}
