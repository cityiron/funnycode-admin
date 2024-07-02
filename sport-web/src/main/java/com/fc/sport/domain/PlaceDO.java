package com.fc.sport.domain;

import com.fc.common.utils.model.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 场馆领域模型
 *
 * @author tiecheng
 * @version 1.0
 * @className PlaceDO
 * @description
 * @date 2024/07/02 09:00
 */
@Getter
@Setter
@ToString
public class PlaceDO extends BaseDO {

    public static final PlaceDO EMPTY = new PlaceDO();

    /**
     * 场地规则
     */
    private List<CourtRuleDO> courtRuleDOList;

    /**
     * 场地
     */
    private List<CourtDO> courtDOList;

    /**
     * 场地加工，给场地关联的预定规则信息补充
     */
    public void courtProcess() {
        if (CollectionUtils.isEmpty(courtRuleDOList) || CollectionUtils.isEmpty(courtRuleDOList)) {
            return;
        }

        for (CourtDO courtDO : courtDOList) {
            Long courtId = courtDO.getId();
            Map<Long, List<CourtRuleDetailDO>> weekCourtRuleDetailMap = new HashMap<>();
            for (CourtRuleDO courtRuleDO : courtRuleDOList) {
                if (courtRuleDO.getCourtIds().contains(courtId)) {
                    for (CourtRuleDetailDO courtRuleDetailDO : courtRuleDO.getCourtRuleDetailDOList()) {
                        List<Long> weekIndexes = courtRuleDetailDO.getWeekIndexes();
                        for (Long weekIndex : weekIndexes) {
                            if (weekCourtRuleDetailMap.containsKey(weekIndex)) {
                                weekCourtRuleDetailMap.get(weekIndex).add(courtRuleDetailDO);
                            } else {
                                List<CourtRuleDetailDO> temp = new ArrayList<>();
                                temp.add(courtRuleDetailDO);
                                weekCourtRuleDetailMap.put(weekIndex, temp);
                            }
                        }
                    }
                }
            }
            List<CourtRuleDetailWeekDO> crdwList = new ArrayList<>();
            for (Map.Entry<Long, List<CourtRuleDetailDO>> entry : weekCourtRuleDetailMap.entrySet()) {
                CourtRuleDetailWeekDO courtRuleDetailWeekDO = new CourtRuleDetailWeekDO();
                courtRuleDetailWeekDO.setWeekIndex(entry.getKey());
                courtRuleDetailWeekDO.setCourtRuleDetailDOList(entry.getValue());
                crdwList.add(courtRuleDetailWeekDO);
            }
            courtDO.setCourtRuleDetailWeekDOList(crdwList);
        }
    }

}
