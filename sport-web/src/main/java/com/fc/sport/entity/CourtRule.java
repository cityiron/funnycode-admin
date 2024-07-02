package com.fc.sport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fc.common.utils.model.BaseDO;

import java.util.Date;

/**
 * <p>
 * 场地规则表
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-28
 */
@TableName("fc_court_rule")
public class CourtRule extends BaseDO {

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
     * 最小价格
     */
    @TableField("min_price")
    private Long minPrice;

    /**
     * 有效起始时间
     */
    @TableField("effective_start")
    private Date effectiveStart;

    /**
     * 有效结束时间
     */
    @TableField("effective_end")
    private Date effectiveEnd;

    /**
     * 规则生效(0-未生效,1-生效)
     */
    @TableField("is_effect")
    private Boolean effect;

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

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Date getEffectiveStart() {
        return effectiveStart;
    }

    public void setEffectiveStart(Date effectiveStart) {
        this.effectiveStart = effectiveStart;
    }

    public Date getEffectiveEnd() {
        return effectiveEnd;
    }

    public void setEffectiveEnd(Date effectiveEnd) {
        this.effectiveEnd = effectiveEnd;
    }

    public Boolean getEffect() {
        return effect;
    }

    public void setEffect(Boolean effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "CourtRule{" +
            "placeId = " + placeId +
            ", name = " + name +
            ", minPrice = " + minPrice +
            ", effectiveStart = " + effectiveStart +
            ", effectiveEnd = " + effectiveEnd +
            ", effect = " + effect +
        "}";
    }
}
