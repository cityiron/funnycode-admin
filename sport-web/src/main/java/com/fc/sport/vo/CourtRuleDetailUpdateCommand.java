package com.fc.sport.vo;

import com.fc.common.utils.model.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author tiecheng
 * @version 1.0
 * @className CourtRuleDetailUpdateCommand
 * @description 更新规则详情
 * @date 2024/07/01 11:21
 */
@Getter
@Setter
@ToString
public class CourtRuleDetailUpdateCommand extends BaseDTO {

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
