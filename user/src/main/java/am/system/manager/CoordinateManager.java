package am.system.manager;

import am.system.dto.CoordinateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CoordinateManager {

    public ResponseEntity<Boolean> send(CoordinateDTO coordinateDTO) {
        return ResponseEntity.ok(true);
    }
}
