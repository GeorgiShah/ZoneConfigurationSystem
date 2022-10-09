package am.system.service;

import am.system.entity.Coordinate;
import am.system.entity.Map;
import am.system.entity.Zone;
import org.springframework.stereotype.Service;

@Service
public class CoordinateService {

    public Zone isInExistingZone(Coordinate coordinate) {
        for (Zone zone : Map.getZones()) {
            if (zone.containsCoordinate(coordinate))
                return zone;
        }

        return null;
    }
}
