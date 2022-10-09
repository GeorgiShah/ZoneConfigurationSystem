package am.system.broker;

import am.system.utils.Constants;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

public final class AdminClient implements Publisher, Subscriber {

    private static final String ADMIN_ID = UUID.randomUUID().toString();

    private static AdminClient adminClient;

    private static IMqttClient client;

    private AdminClient() {}

    public static AdminClient getAdminClient() {
        if (Objects.isNull(adminClient)) {
            adminClient = new AdminClient();
            connectToServer();
        }

        return adminClient;
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
        client.setCallback(new AdminMqttCallback());

        try {
            client.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private static void connectToServer() {
        try {
            client = new MqttClient(Constants.BROKER, ADMIN_ID);
            client.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
