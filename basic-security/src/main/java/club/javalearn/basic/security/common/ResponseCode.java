package club.javalearn.basic.security.common;

/**
 *
 * @author king-pan
 * @date 2018-04-03
 */
public enum ResponseCode {
    /**
     *
     */
    SUCCESS(200, "SUCCESS"),
    NOT_FOUND(404,"NOT_FOUND"),
    UN_AUTHORIZED(403,"UN_AUTHORIZED"),
    ERROR(500, "ERROR"),
    NEED_LOGIN(0, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(1, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;


    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
