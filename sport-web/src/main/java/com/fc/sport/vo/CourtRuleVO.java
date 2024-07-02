package com.fc.sport.vo;

import com.fc.common.utils.model.BaseDTO;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 场地规则表
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
public class CourtRuleVO extends BaseDTO {

    /**
     * 场馆ID
     */
    private Long placeId;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private Long minPrice;

    /**
     * 规则细节
     */
    private List<CourtRuleItemVO> courtRuleItemVOList;

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

    /**
     * 关联场地
     */
    private List<Long> courtIds;

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

    public List<CourtRuleItemVO> getCourtRuleItemVOList() {
        return courtRuleItemVOList;
    }

    public void setCourtRuleItemVOList(List<CourtRuleItemVO> courtRuleItemVOList) {
        this.courtRuleItemVOList = courtRuleItemVOList;
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

    public List<Long> getCourtIds() {
        return courtIds;
    }

    public void setCourtIds(List<Long> courtIds) {
        this.courtIds = courtIds;
    }

}
