package am.system;

import am.system.broker.AdminClient;
import am.system.utils.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        AdminClient.getAdminClient().subscribe(Constants.COORDINATE_TOPIC);
        SpringApplication.run(AdminApplication.class, args);
    }
}
