package ray.sumit.bbms.msbeerservice.enumeration;

public enum ReturnCodeEnum {
    INVALID_REQUEST_PARAMS, DEFAULT_ERROR;

    public static ReturnCodeEnum fromValue(String key) {
        return ReturnCodeEnum.valueOf(key);
    }
}
