package com.fc.sport.domain;

import com.fc.common.utils.model.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @className CourtRuleDetailWeekDO
 * @description 规则详情周信息
 * @author tiecheng
 * @date 2024/07/02 10:08
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class CourtRuleDetailWeekDO extends BaseDO {

    /**
     * 周几，1-7
     */
    private Long weekIndex;

    /**
     *  价格和起始时间，要根据时间排序
     */
    private List<CourtRuleDetailDO> courtRuleDetailDOList;

}
