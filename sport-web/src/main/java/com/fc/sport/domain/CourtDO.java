package com.fc.sport.domain;

import com.fc.common.utils.model.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * <p>
 * 场地表
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
@Getter
@Setter
@ToString
public class CourtDO extends BaseDO {

    /**
     * 场馆ID
     */
    private Long placeId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型(1-普通,2-VIP)
     */
    private Byte type;

    /**
     * 营业状态(0-未营业,1-营业)
     */
    private Boolean open;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 规则详情
     */
    private List<CourtRuleDetailWeekDO> courtRuleDetailWeekDOList;

}
