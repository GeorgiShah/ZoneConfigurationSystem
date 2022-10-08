package am.system.manager;

import am.system.dto.CreateZoneDTO;
import am.system.entity.Zone;
import am.system.handler.ZoneExistException;
import am.system.service.ZoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ZoneManager {

    private final ZoneService zoneService;

    public ZoneManager(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    public ResponseEntity<Boolean> create(CreateZoneDTO createZoneDTO) {
        Zone newZone = new Zone(createZoneDTO.getCoordinates(), createZoneDTO.getName());
        if (zoneService.existsOnMap(newZone))
            throw new ZoneExistException("Zone is already exist in map", HttpStatus.CONFLICT);

        zoneService.saveOnMap(newZone);

        return ResponseEntity.ok(true);
    }
}
