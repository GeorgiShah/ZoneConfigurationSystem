package am.system.controller;

import am.system.dto.CoordinateDTO;
import am.system.manager.CoordinateManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user/coordinate")
public class CoordinateController {

    private final CoordinateManager coordinateManager;

    public CoordinateController(CoordinateManager coordinateManager) {
        this.coordinateManager = coordinateManager;
    }

    @PostMapping(value = "/send")
    public ResponseEntity<Boolean> send(@RequestBody CoordinateDTO coordinateDTO) {
        return coordinateManager.send(coordinateDTO);
    }
}
