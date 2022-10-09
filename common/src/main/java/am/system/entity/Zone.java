package am.system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Zone {
    private List<Coordinate> coordinates;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zone zone = (Zone) o;
        return Objects.equals(coordinates, zone.coordinates) && Objects.equals(name, zone.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, name);
    }

    public boolean containsCoordinate(Coordinate coordinate) {
        Polygon polygon = Map.getPolygonForZone(this);

        return polygon.contains(coordinate.getX(), coordinate.getY());
    }
}
