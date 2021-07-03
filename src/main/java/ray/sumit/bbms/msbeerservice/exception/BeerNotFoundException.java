package ray.sumit.bbms.msbeerservice.exception;

import javax.validation.constraints.NotBlank;

public class BeerNotFoundException extends RuntimeException {
    public BeerNotFoundException() {
        super();
    }

    public BeerNotFoundException(@NotBlank String s) {
        super(s);
    }
}
