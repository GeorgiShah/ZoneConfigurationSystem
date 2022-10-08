package am.system.broker;

import org.eclipse.paho.client.mqttv3.*;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public final class UserPublisher {

    private static final String PUBLISHER_ID = UUID.randomUUID().toString();

    private UserPublisher() {}

    public static void publish() {
        try {
            IMqttClient publisher = new MqttClient("tcp://broker.emqx.io:1883", PUBLISHER_ID);
            publisher.connect();

            for (int i = 0; i < 10; i++) {
                MqttMessage message = new MqttMessage(("Hello " + i).getBytes(StandardCharsets.UTF_8));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publisher.publish("coordinates", message);
            }

            publisher.disconnect();
            publisher.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
