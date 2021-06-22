package ray.sumit.bbms.msbeerservice.exception.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BreweriesError {
    private String errorCode;
    private String errorMessage;
    private List<String> validationResult;

    public BreweriesError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BreweriesError(String errorCode, String errorMessage, List<String> validationResult) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.validationResult = validationResult;
    }
}
