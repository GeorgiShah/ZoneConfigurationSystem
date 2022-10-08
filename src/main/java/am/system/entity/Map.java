package am.system.entity;

import java.util.LinkedList;
import java.util.List;

public final class Map {
    private static final List<Zone> ZONES = new LinkedList<>();

    public static void addZone(Zone zone) {
        ZONES.add(zone);
    }

    public static List<Zone> getZones() {
        return new LinkedList<>(ZONES);
    }
}
