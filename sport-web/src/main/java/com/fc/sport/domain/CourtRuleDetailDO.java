package com.fc.sport.domain;

import com.fc.common.utils.model.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author tiecheng
 * @version 1.0
 * @className CourtRuleDetailDO
 * @description
 * @date 2024/06/28 10:55
 */
@Getter
@Setter
@ToString
public class CourtRuleDetailDO extends BaseDO {

    /**
     * 价格
     */
    private Long price;

    /**
     * 场地规则ID
     */
    private Long courtRuleId;

    /**
     * 周期范围
     */
    private List<Long> weekIndexes;

    /**
     * 周信息
     */
    private String weekIndexesStr;

    /**
     * 起始时间
     */
    private Byte startTime;

    /**
     * 结束时间
     */
    private Byte endTime;

}
