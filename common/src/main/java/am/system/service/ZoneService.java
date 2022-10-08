package am.system.service;

import am.system.entity.Map;
import am.system.entity.Zone;
import org.springframework.stereotype.Service;

@Service
public class ZoneService {

    public void saveOnMap(Zone zone) {
        Map.addZone(zone);
    }

    public boolean existsOnMap(Zone zone) {
        return Map.getZones().contains(zone);
    }
}
