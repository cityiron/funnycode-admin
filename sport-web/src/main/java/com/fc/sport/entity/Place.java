package com.fc.sport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fc.common.utils.model.BaseDO;

/**
 * <p>
 * 场馆表
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-28
 */
@TableName("fc_place")
public class Place extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一编码
     */
    @TableField("code")
    private String code;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 位置说明
     */
    @TableField("position")
    private String position;

    /**
     * 类型(ymq羽毛球,ppq乒乓球,fp飞盘,lq篮球,zq足球,yy游泳)
     */
    @TableField("type")
    private String type;

    /**
     * 平面图
     */
    @TableField("plan_pic")
    private String planPic;

    /**
     * 简介
     */
    @TableField("brief")
    private String brief;

    /**
     * 设施
     */
    @TableField("facs_dict")
    private String facsDict;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 座机
     */
    @TableField("tel")
    private String tel;

    /**
     * 营业状态(0-未营业,1-营业)
     */
    @TableField("is_open")
    private Boolean open;

    /**
     * 生成预定信息截止日期
     */
    @TableField("max_day")
    private Integer maxDay;

    /**
     * 预定信息起始时间
     */
    @TableField("start_time")
    private String startTime;

    /**
     * 预定信息结束时间
     */
    @TableField("end_time")
    private String endTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlanPic() {
        return planPic;
    }

    public void setPlanPic(String planPic) {
        this.planPic = planPic;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getFacsDict() {
        return facsDict;
    }

    public void setFacsDict(String facsDict) {
        this.facsDict = facsDict;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Integer getMaxDay() {
        return maxDay;
    }

    public void setMaxDay(Integer maxDay) {
        this.maxDay = maxDay;
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

    @Override
    public String toString() {
        return "Place{" +
            "code = " + code +
            ", name = " + name +
            ", position = " + position +
            ", type = " + type +
            ", planPic = " + planPic +
            ", brief = " + brief +
            ", facsDict = " + facsDict +
            ", phone = " + phone +
            ", tel = " + tel +
            ", open = " + open +
            ", maxDay = " + maxDay +
            ", startTime = " + startTime +
            ", endTime = " + endTime +
        "}";
    }
}
