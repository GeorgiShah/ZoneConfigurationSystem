package am.system;

import am.system.broker.AdminConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        AdminConsumer.consume();
        SpringApplication.run(AdminApplication.class, args);
    }
}
