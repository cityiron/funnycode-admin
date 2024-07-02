package com.fc.sport.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fc.common.utils.model.BaseDO;
import com.fc.common.utils.model.BaseDTO;

/**
 * <p>
 * 场地规则明细表
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-28
 */
public class CourtRuleDetailDTO extends BaseDTO {

    /**
     * 场馆ID
     */
    private Long placeId;

    /**
     * 场地规则ID
     */
    private Long courtRuleId;

    /**
     * 价格
     */
    private Long price;

    /**
     * 周期范围
     */
    private String weekIndexes;

    /**
     * 起始时刻
     */
    private Byte startTime;

    /**
     * 结束时刻
     */
    private Byte endTime;

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Long getCourtRuleId() {
        return courtRuleId;
    }

    public void setCourtRuleId(Long courtRuleId) {
        this.courtRuleId = courtRuleId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getWeekIndexes() {
        return weekIndexes;
    }

    public void setWeekIndexes(String weekIndexes) {
        this.weekIndexes = weekIndexes;
    }

    public Byte getStartTime() {
        return startTime;
    }

    public void setStartTime(Byte startTime) {
        this.startTime = startTime;
    }

    public Byte getEndTime() {
        return endTime;
    }

    public void setEndTime(Byte endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "CourtRuleDetail{" +
            "placeId = " + placeId +
            ", courtRuleId = " + courtRuleId +
            ", price = " + price +
            ", weekIndexes = " + weekIndexes +
            ", startTime = " + startTime +
            ", endTime = " + endTime +
        "}";
    }
}
