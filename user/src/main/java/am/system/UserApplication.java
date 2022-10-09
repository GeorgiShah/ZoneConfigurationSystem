package am.system;

import am.system.broker.UserClient;
import am.system.utils.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        UserClient.getUserClient().subscribe(Constants.VIOLATIONS_TOPIC);
        SpringApplication.run(UserApplication.class, args);
    }
}
