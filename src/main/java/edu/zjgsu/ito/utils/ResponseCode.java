package edu.zjgsu.ito.utils;

/**
 * Created by sjjok00 on 2017/10/27.
 */
public enum ResponseCode {
    SUCCESS(200,"SUCCESS"),
    ERROR(400,"ERROR"),
    FIRSE_LOGIN(600,"FIRSE_LOGIN"),
    NEED_LOGIN(300,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(500,"ILLEGAL_ARGUMENT");

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
