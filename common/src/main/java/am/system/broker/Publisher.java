package am.system.broker;

public interface Publisher {

    void publish(String topic, String message);
}
