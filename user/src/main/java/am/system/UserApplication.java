package am.system;

import am.system.broker.UserPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        UserPublisher.publish();
        SpringApplication.run(UserApplication.class, args);
    }
}
