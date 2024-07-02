package com.fc.sport.dto;

import com.fc.common.utils.model.BaseDTO;

/**
 * <p>
 * 场地规则关联表
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
public class CourtRuleAssociationDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 场地ID
     */
    private Long courtId;

    /**
     * 场地规则ID
     */
    private Long courtRuleId;

    public Long getCourtId() {
        return courtId;
    }

    public void setCourtId(Long courtId) {
        this.courtId = courtId;
    }

    public Long getCourtRuleId() {
        return courtRuleId;
    }

    public void setCourtRuleId(Long courtRuleId) {
        this.courtRuleId = courtRuleId;
    }

    @Override
    public String toString() {
        return "CourtRuleAssociation{" +
            "courtId = " + courtId +
            ", courtRuleId = " + courtRuleId +
        "}";
    }
}
