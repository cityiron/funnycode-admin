package com.fc.sport.vo;

import com.fc.common.utils.model.BaseDTO;

import java.util.List;

/**
 * @author tiecheng
 * @version 1.0
 * @className CourtRuleItemVO
 * @description
 * @date 2024/06/28 15:03
 */
public class CourtRuleItemVO extends BaseDTO {

    private List<Long> weekIndexes;

    private List<CourtRuleDetailVO> courtRuleDetailVOList;

    public List<Long> getWeekIndexes() {
        return weekIndexes;
    }

    public void setWeekIndexes(List<Long> weekIndexes) {
        this.weekIndexes = weekIndexes;
    }

    public List<CourtRuleDetailVO> getCourtRuleDetailVOList() {
        return courtRuleDetailVOList;
    }

    public void setCourtRuleDetailVOList(List<CourtRuleDetailVO> courtRuleDetailVOList) {
        this.courtRuleDetailVOList = courtRuleDetailVOList;
    }
}
