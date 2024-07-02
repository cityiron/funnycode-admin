package com.fc.sport.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fc.common.utils.model.BaseDTO;

import java.util.List;

/**
 * <p>
 * 场地规则明细表
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-28
 */
public class CourtRuleDetailVO extends BaseDTO {

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
    private List<Long> weekIndexes;

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

    public List<Long> getWeekIndexes() {
        return weekIndexes;
    }

    public void setWeekIndexes(List<Long> weekIndexes) {
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
