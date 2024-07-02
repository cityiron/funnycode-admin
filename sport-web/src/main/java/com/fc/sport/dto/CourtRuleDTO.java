package com.fc.sport.dto;

import com.fc.common.utils.model.BaseDTO;

import java.util.Date;

/**
 * <p>
 * 场地规则表
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
public class CourtRuleDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 场馆ID
     */
    private Long placeId;

    /**
     * 名称
     */
    private String name;

    private Long minPrice;

    /**
     * 起始时间
     */
    private Date effectiveStart;

    /**
     * 结束时间
     */
    private Date effectiveEnd;

    /**
     * 规则生效(0-未生效,1-生效)
     */
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

}
