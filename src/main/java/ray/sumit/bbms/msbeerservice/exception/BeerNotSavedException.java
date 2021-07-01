package ray.sumit.bbms.msbeerservice.exception;

import javax.validation.constraints.NotBlank;

public class BeerNotSavedException extends RuntimeException {
    public BeerNotSavedException() {
        super();
    }

    public BeerNotSavedException(@NotBlank String s) {
        super(s);
    }
}
