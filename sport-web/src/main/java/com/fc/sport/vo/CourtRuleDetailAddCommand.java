package com.fc.sport.vo;

import com.fc.common.utils.model.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author tiecheng
 * @version 1.0
 * @className CourtRuleDetailAddCommand
 * @description
 * @date 2024/07/01 12:19
 */
@Getter
@Setter
@ToString
public class CourtRuleDetailAddCommand extends BaseDTO {

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

}
