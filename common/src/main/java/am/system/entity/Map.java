package am.system.entity;

import java.awt.*;
import java.util.*;

public final class Map {
    private static final java.util.Map<Zone, Polygon> ZONE_POLYGON_MAP = new HashMap<>();

    public static void addZone(Zone zone) {
        Polygon newPolygon = new Polygon();
        for (Coordinate coordinate : zone.getCoordinates())
            newPolygon.addPoint((int) coordinate.getX(), (int) coordinate.getY());

        ZONE_POLYGON_MAP.put(zone, newPolygon);
    }

    public static Set<Zone> getZones() {
        return new HashSet<>(ZONE_POLYGON_MAP.keySet());
    }

    public static Polygon getPolygonForZone(Zone zone) {
        return ZONE_POLYGON_MAP.get(zone);
    }
}
