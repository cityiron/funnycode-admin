package com.fc.sport.domain;

import com.fc.common.utils.model.BaseDO;

import java.util.Date;

/**
 * @className BookingDO
 * @description 
 * @author tiecheng
 * @date 2024/06/27 15:42
 * @version 1.0
 */
public class BookingDO extends BaseDO {

    /**
     * 场地ID
     */
    private Long courtId;

    /**
     * 场地名称
     */
    private String courtName;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private Long price;

    /**
     * 日期
     */
    private Date day;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 预订人
     */
    private Long userId;

    /**
     * 状态(9-已售出,1-未售出,2-被占用,3-锁定,4-过期)
     */
    private BookingStatusEnum status;

    /**
     * 备注
     */
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

    public BookingStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BookingStatusEnum status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
