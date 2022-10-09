package am.system.controller;

import am.system.dto.CreateZoneDTO;
import am.system.manager.ZoneManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/admin/zone")
public class ZoneController {

    private final ZoneManager zoneManager;

    public ZoneController(ZoneManager zoneManager) {
        this.zoneManager = zoneManager;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Boolean> create(@RequestBody @Valid CreateZoneDTO createZoneDTO) {
        return zoneManager.create(createZoneDTO);
    }
}
