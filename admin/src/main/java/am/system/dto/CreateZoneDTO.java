package am.system.dto;

import am.system.entity.Coordinate;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class CreateZoneDTO {

    @Size(min = 3, message = "Zone must contain at least 3 coordinates")
    private List<Coordinate> coordinates;

    @Size(min = 4, message = "Characters length doesn't match")
    private String name;
}
