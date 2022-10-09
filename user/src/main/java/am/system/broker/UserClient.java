package am.system.broker;

import am.system.utils.Constants;
import org.eclipse.paho.client.mqttv3.*;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

public final class UserClient implements Publisher, Subscriber {

    private static UserClient userClient;

    private static final String USER_ID = UUID.randomUUID().toString();

    private static IMqttClient client;

    private UserClient() {}

    public static UserClient getUserClient() {
        if (Objects.isNull(userClient)) {
            userClient = new UserClient();
            connectToServer();
        }

        return userClient;
    }

    @Override
    public void publish(String topic, String message) {
        MqttMessage msg = new MqttMessage(message.getBytes(StandardCharsets.UTF_8));

        try {
            client.publish(topic, msg);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe(String topic) {
        client.setCallback(new UserMqttCallback());

        try {
            client.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private static void connectToServer() {
        try {
            client = new MqttClient(Constants.BROKER, USER_ID);
            client.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
