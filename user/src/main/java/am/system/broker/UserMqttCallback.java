package am.system.broker;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class UserMqttCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("User lost connection to server: " + throwable.getCause());
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {
        System.out.println("User received: " + new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("Delivery is completed: " + iMqttDeliveryToken.isComplete());
    }
}
