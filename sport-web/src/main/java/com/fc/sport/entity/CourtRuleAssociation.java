package com.fc.sport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fc.common.utils.model.BaseDO;

/**
 * <p>
 * 场地规则关联表
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
@TableName("fc_court_rule_association")
public class CourtRuleAssociation extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 场地ID
     */
    @TableField("court_id")
    private Long courtId;

    /**
     * 场地规则ID
     */
    @TableField("court_rule_id")
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
