package com.fc.sport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fc.common.utils.model.BaseDO;

import java.util.Date;

/**
 * <p>
 * 预定表
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
@TableName("fc_booking")
public class Booking extends BaseDO {

    /**
     * 场地ID
     */
    @TableField("court_id")
    private Long courtId;

    /**
     * 场地名称
     */
    @TableField("court_name")
    private String courtName;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 价格
     */
    @TableField("price")
    private Long price;

    /**
     * 日期
     */
    @TableField("day")
    private Date day;

    /**
     * 顺序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 起始时间
     */
    @TableField("start_time")
    private String startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private String endTime;

    /**
     * 预订人
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 状态(9-已售出,1-未售出,2-被占用,3-锁定,4-过期)
     */
    @TableField("status")
    private Byte status;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    public Long getCourtId() {
        return courtId;
    }

    public void setCourtId(Long courtId) {
        this.courtId = courtId;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Booking{" +
            "courtId = " + courtId +
            ", courtName = " + courtName +
            ", name = " + name +
            ", price = " + price +
            ", day = " + day +
            ", index = " + sort +
            ", startTime = " + startTime +
            ", endTime = " + endTime +
            ", userId = " + userId +
            ", status = " + status +
            ", remark = " + remark +
        "}";
    }
}
