package am.system.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinate {
    private double x;

    private double y;

    private double z;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0 && Double.compare(that.z, z) == 0;
    }
}
