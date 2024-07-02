package com.fc.sport.vo;

import com.fc.common.utils.model.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @className CourtRuleAddCommand
 * @description 
 * @author tiecheng
 * @date 2024/07/01 13:03
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class CourtRuleAddCommand extends BaseDTO {

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
     * 规则详细
     */
    private List<CourtRuleItemVO> courtRuleItemVOList;

    /**
     * 关联场地
     */
    private List<Long> courtIds;

}
