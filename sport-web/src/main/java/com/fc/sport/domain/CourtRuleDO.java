package com.fc.sport.domain;

import com.fc.common.utils.model.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 场地规则领域模型
 *
 * @author tiecheng
 * @version 1.0
 * @className CourtRuleDO
 * @description
 * @date 2024/06/27 11:11
 */
@Getter
@Setter
@ToString
public class CourtRuleDO extends BaseDO {

    public static final CourtRuleDO EMPTY = new CourtRuleDO();

    /**
     * 场馆ID
     */
    private Long placeId;

    /**
     * 名称
     */
    private String name;

    /**
     * 最小价格
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
     * 关联场地
     */
    private List<Long> courtIds;

    /**
     * 关联场地
     */
    private List<CourtDO> courtDOList;

    /**
     * 规则详情
     */
    private List<CourtRuleDetailDO> courtRuleDetailDOList;

}
