package am.system.broker;

import org.eclipse.paho.client.mqttv3.*;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

public final class AdminClient implements Publisher, Subscriber {

    private static AdminClient adminClient;

    private static final String ADMIN_ID = UUID.randomUUID().toString();

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
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
                System.out.println("Admin lost connection to server: " + throwable.getCause());
            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) {
                System.out.println("Admin received: " + new String(mqttMessage.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                System.out.println("Delivery is completed: " + iMqttDeliveryToken.isComplete());
            }
        });

        try {
            client.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private static void connectToServer() {
        try {
            client = new MqttClient("tcp://broker.emqx.io:1883", ADMIN_ID);
            client.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
