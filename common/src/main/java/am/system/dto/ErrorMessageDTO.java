package am.system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessageDTO {
    private String fieldName;

    private String message;

    public ErrorMessageDTO(String message) {
        this.message = message;
    }
}
