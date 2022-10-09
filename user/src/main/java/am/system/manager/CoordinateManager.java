package am.system.manager;

import am.system.broker.UserClient;
import am.system.dto.CoordinateDTO;
import am.system.entity.Coordinate;
import am.system.utils.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CoordinateManager {

    private static final ObjectWriter WRITER = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public ResponseEntity<Boolean> send(CoordinateDTO coordinateDTO) {
        Coordinate coordinate = new Coordinate(coordinateDTO.getX(), coordinateDTO.getY(), coordinateDTO.getZ());

        try {
            UserClient.getUserClient().publish(Constants.COORDINATE_TOPIC, WRITER.writeValueAsString(coordinate));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(true);
    }
}
