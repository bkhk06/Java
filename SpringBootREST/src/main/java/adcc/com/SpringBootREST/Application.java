package adcc.com.SpringBootREST;

@SpringBootApplication
@RestController
public class Application<RestController> {
    public String index() {
        return "Hello Spring Boot!";
    }
}
