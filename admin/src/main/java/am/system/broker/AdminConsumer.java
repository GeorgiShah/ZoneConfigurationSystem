package am.system.broker;

import org.eclipse.paho.client.mqttv3.*;

import java.util.UUID;

public final class AdminConsumer {

    private static final String CONSUMER_ID = UUID.randomUUID().toString();

    private AdminConsumer() {}

    public static void consume() {
        try {
            IMqttClient consumer = new MqttClient("tcp://broker.emqx.io:1883", CONSUMER_ID);

            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setAutomaticReconnect(true);
            consumer.connect(connectOptions);

            consumer.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    System.out.println("Connection to server lost: " + throwable.getCause());
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) {
                    System.out.println("Received: " + new String(mqttMessage.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("Delivery is completed: " + iMqttDeliveryToken.isComplete());
                }
            });

            consumer.subscribe("coordinates");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
