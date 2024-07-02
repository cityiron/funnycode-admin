package com.fc.sport.domain;


/**
 * @author tiecheng
 * @version 1.0
 * @className BookingStatusEnum
 * @description
 * @date 2024/06/27 15:45
 */
public enum BookingStatusEnum {

    SALE((byte) 9, "已售出"),

    NOT_SALE((byte) 1, "未售出"),

    OCCUPY((byte) 2, "占用"),

    LOCK((byte) 3, "锁定"),

    EXPIRE((byte) 4, "过期");

    private byte value;

    private String remark;

    BookingStatusEnum(byte value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public byte getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public static BookingStatusEnum findByValue(byte value) {
        for (BookingStatusEnum bookingStatusEnum : BookingStatusEnum.values()) {
            if (bookingStatusEnum.getValue() == value) {
                return bookingStatusEnum;
            }
        }
        return NOT_SALE;
    }

}
