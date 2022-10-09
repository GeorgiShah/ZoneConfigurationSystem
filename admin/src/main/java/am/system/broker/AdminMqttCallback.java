package am.system.broker;

import am.system.entity.Coordinate;
import am.system.entity.Zone;
import am.system.service.CoordinateService;
import am.system.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.IOException;
import java.util.Objects;

public class AdminMqttCallback implements MqttCallback {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final CoordinateService coordinateService;

    public AdminMqttCallback() {
        coordinateService = new CoordinateService();
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Admin lost connection to server: " + throwable.getCause());
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {
        System.out.println("Admin received: " + new String(mqttMessage.getPayload()));

        Coordinate coordinate = null;
        try {
            coordinate = OBJECT_MAPPER.readValue(mqttMessage.getPayload(), Coordinate.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Objects.nonNull(coordinate)) {
            Zone zone = coordinateService.isInExistingZone(coordinate);
            if (Objects.nonNull(zone))
                AdminClient.getAdminClient().publish(Constants.VIOLATIONS_TOPIC, "Zone " + zone.getName() + " violated");
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("Delivery is completed: " + iMqttDeliveryToken.isComplete());
    }
}
