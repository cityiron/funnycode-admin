package com.fc.sport.vo;

import com.fc.common.utils.model.BaseDTO;

/**
 * <p>
 * 场地表
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
public class CourtVO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 场馆ID
     */
    private Long placeId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型(1-普通,2-VIP)
     */
    private Byte type;

    /**
     * 营业状态(0-未营业,1-营业)
     */
    private Boolean open;

    private Integer sort;

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


    @Override
    public String toString() {
        return "Court{" +
            "placeId = " + placeId +
            ", name = " + name +
            ", type = " + type +
            ", open = " + open +
        "}";
    }

}
