package com.example.demo.enums;

/**
 * @author zrx
 * @since 2023/12/24 1:40
 */
public enum SexEnums {
    MAN(0, "男"),
    WOMAN(1, "女"),
    OTHER(2, "未填");

    private final int code;
    private final String desc;

    SexEnums(int code, String desc) {
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
