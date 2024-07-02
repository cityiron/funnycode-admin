package com.fc.sport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fc.common.utils.model.BaseDO;

/**
 * <p>
 * 场地规则明细表
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-28
 */
@TableName("fc_court_rule_detail")
public class CourtRuleDetail extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 场馆ID
     */
    @TableField("place_id")
    private Long placeId;

    /**
     * 场地规则ID
     */
    @TableField("court_rule_id")
    private Long courtRuleId;

    /**
     * 价格
     */
    @TableField("price")
    private Long price;

    /**
     * 周期范围
     */
    @TableField("week_indexes")
    private String weekIndexes;

    /**
     * 起始时刻
     */
    @TableField("start_time")
    private Byte startTime;

    /**
     * 结束时刻
     */
    @TableField("end_time")
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
