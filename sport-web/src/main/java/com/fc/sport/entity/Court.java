package com.fc.sport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fc.common.utils.model.BaseDO;

/**
 * <p>
 * 场地表
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
@TableName("fc_court")
public class Court extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 场馆ID
     */
    @TableField("place_id")
    private Long placeId;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 类型(1-普通,2-VIP)
     */
    @TableField("type")
    private Byte type;

    /**
     * 营业状态(0-未营业,1-营业)
     */
    @TableField("is_open")
    private Boolean open;

    /**
     * 顺序
     */
    @TableField("sort")
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
