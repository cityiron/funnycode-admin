package com.fc.sport.convert;

import com.fc.sport.domain.CourtDO;
import com.fc.sport.domain.CourtRuleDO;
import com.fc.sport.domain.CourtRuleDetailDO;
import com.fc.sport.entity.Court;
import com.fc.sport.entity.CourtRule;
import com.fc.sport.entity.CourtRuleAssociation;
import com.fc.sport.entity.CourtRuleDetail;
import com.fc.sport.vo.*;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tiecheng
 * @version 1.0
 * @className BookingMapper
 * @description
 * @date 2024/06/26 16:06
 */
@Mapper(uses = {CommonMapStructUtil.class})
public interface CourtRuleMapper {

    CourtRuleMapper INSTANCE = Mappers.getMapper(CourtRuleMapper.class);

    // 领域对象 == 数据库对象

    List<CourtRule> toCourtRuleList(List<CourtRuleDO> courtRuleDOList);

    /**
     * 场地规则领域对象转场地规则数据库对象
     *
     * @param courtRuleDO 场地规则领域对象
     * @return
     */
    CourtRule toCourtRule(CourtRuleDO courtRuleDO);

    /**
     * 场地规则领域对象{@link CourtRuleDO#getCourtDOList()} ()}转场地规则关联数据库对象
     *
     * @param courtRuleDO 场地规则领域对象
     * @return
     */
    default List<CourtRuleAssociation> toCourtRuleAssociationList(CourtRuleDO courtRuleDO) {
        List<CourtRuleAssociation> courtRuleAssociationList = INSTANCE.toCourtRuleAssociationList(courtRuleDO.getCourtDOList());
        courtRuleAssociationList.stream().forEach(c -> {
            c.setCourtRuleId(courtRuleDO.getId());
        });
        return courtRuleAssociationList;
    }

    List<CourtRuleAssociation> toCourtRuleAssociationList(List<CourtDO> courtDOList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courtId", source = "id")
    CourtRuleAssociation toCourtRuleAssociation(CourtDO courtDO);

    /**
     * 场地规则领域对象{@link CourtRuleDO#getCourtRuleDetailDOList()}转场地规则详情数据库对象
     *
     * @param courtRuleDO 场地规则领域对象
     * @return
     */
    default List<CourtRuleDetail> toCourtRuleDetailList(CourtRuleDO courtRuleDO) {
        List<CourtRuleDetail> courtRuleDetailList = INSTANCE.toCourtRuleDetailList(courtRuleDO.getCourtRuleDetailDOList());
        courtRuleDetailList.stream().forEach(c -> {
            c.setPlaceId(courtRuleDO.getPlaceId());
            c.setCourtRuleId(courtRuleDO.getId());
        });
        return courtRuleDetailList;
    }

    List<CourtRuleDetail> toCourtRuleDetailList(List<CourtRuleDetailDO> courtRuleDetailDOList);

    /**
     * 场地规则详情领域对象转场地规则详情数据库对象
     *
     * @param courtRuleDetailDO 场地规则详情领域对象
     * @return
     */
    @Mapping(target = "weekIndexes", expression = "java(CommonMapStructUtil.listToStr(courtRuleDetailDO.getWeekIndexes()))")
    CourtRuleDetail toCourtRuleDetail(CourtRuleDetailDO courtRuleDetailDO);

    // 数据库对象 == 领域对象

    @Mapping(target = "id", source = "courtRule.id")
    @Mapping(target = "creator", source = "courtRule.creator")
    @Mapping(target = "modifier", source = "courtRule.modifier")
    @Mapping(target = "createTime", source = "courtRule.createTime")
    @Mapping(target = "modifyTime", source = "courtRule.modifyTime")
    @Mapping(target = "placeId", source = "courtRule.placeId")
    @Mapping(target = "name", source = "courtRule.name")
    @Mapping(target = "minPrice", source = "courtRule.minPrice")
    @Mapping(target = "effectiveStart", source = "courtRule.effectiveStart")
    @Mapping(target = "effectiveEnd", source = "courtRule.effectiveEnd")
    @Mapping(target = "effect", source = "courtRule.effect")
    @Mapping(target = "courtIds", source = "courtList", qualifiedByName = "courtIdList")
    @Mapping(target = "courtDOList", source = "courtList")
    @Mapping(target = "courtRuleDetailDOList", source = "courtRuleDetailList")
    CourtRuleDO toCourtRuleDO(CourtRule courtRule, List<Court> courtList, List<CourtRuleDetail> courtRuleDetailList);

    /**
     * 从数据库数据组合到场地规则模型
     *
     * @param courtRuleList
     * @param courtList
     * @param craListByCourtRuleIdMap
     * @param crdListByCourtRuleIdMap
     * @return
     */
    default List<CourtRuleDO> toCourtRuleDOListFromCourtRuleListPro(List<CourtRule> courtRuleList, List<Court> courtList, Map<Long, List<CourtRuleAssociation>> craListByCourtRuleIdMap, Map<Long, List<CourtRuleDetail>> crdListByCourtRuleIdMap) {
        List<CourtRuleDO> result = new ArrayList<>();
        for (CourtRule courtRule : courtRuleList) {
            Long courtRuleId = courtRule.getId();
            List<Long> currentRuleCourtIds = craListByCourtRuleIdMap.get(courtRuleId).stream().map(CourtRuleAssociation::getCourtId).toList();
            CourtRuleDO currentCourtRuleDO = this.toCourtRuleDO(courtRule, courtList.stream().filter(c -> currentRuleCourtIds.contains(c.getId())).toList(), crdListByCourtRuleIdMap.get(courtRuleId));
            result.add(currentCourtRuleDO);
        }
        return result;
    }

    @Mapping(target = "weekIndexes", expression = "java(CommonMapStructUtil.strToList(courtRuleDetail.getWeekIndexes()))")
    CourtRuleDetailDO toCourtRuleDetailDO(CourtRuleDetail courtRuleDetail);

    CourtDO toCourtDO(Court court);

    // 视图对象 == 领域对象

    List<CourtRuleDO> toCourtRuleDOList(List<CourtRuleVO> courtRuleVOList);

    @Mapping(target = "courtRuleDetailDOList", source = "courtRuleItemVOList", qualifiedByName = "itemToDetail")
    @Mapping(target = "courtDOList", source = "courtIds", qualifiedByName = "fillCourtDOList")
    CourtRuleDO toCourtRuleDO(CourtRuleVO courtRuleVO);

    default CourtRuleDO convertCourtRuleDO(CourtRuleDetailAddCommand courtRuleDetailAddCommand) {
        if (Objects.isNull(courtRuleDetailAddCommand)) {
            return CourtRuleDO.EMPTY;
        }
        CourtRuleDO courtRuleDO = new CourtRuleDO();
        courtRuleDO.setCourtRuleDetailDOList(List.of(this.toCourtRuleDetailDOFromCourtRuleDetailAddCommand(courtRuleDetailAddCommand)));
        return courtRuleDO;
    }

    CourtRuleDetailDO toCourtRuleDetailDOFromCourtRuleDetailAddCommand(CourtRuleDetailAddCommand courtRuleDetailAddCommand);

    /**
     * 场地规则详情更新命令对象转场地规则模型
     *
     * @param courtRuleDetailUpdateCommandList
     * @return
     */
    default CourtRuleDO convertCourtRuleDO(List<CourtRuleDetailUpdateCommand> courtRuleDetailUpdateCommandList) {
        if (CollectionUtils.isEmpty(courtRuleDetailUpdateCommandList)) {
            return CourtRuleDO.EMPTY;
        }
        List<CourtRuleDetailDO> courtRuleDetailDOList = this.toCourtRuleDetailDOListFromCourtRuleDetailUpdateCommandList(courtRuleDetailUpdateCommandList);
        CourtRuleDO courtRuleDO = new CourtRuleDO();
        courtRuleDO.setCourtRuleDetailDOList(courtRuleDetailDOList);
        return courtRuleDO;
    }

    List<CourtRuleDetailDO> toCourtRuleDetailDOListFromCourtRuleDetailUpdateCommandList(List<CourtRuleDetailUpdateCommand> courtRuleDetailUpdateCommandList);

    CourtRuleDetailDO toCourtRuleDetailDOFromCourtRuleDetailUpdateCommand(CourtRuleDetailUpdateCommand courtRuleDetailUpdateCommand);

    List<CourtRuleDetailDO> toCourtRuleDetailDOList(List<CourtRuleDetailVO> courtRuleDetailVOList);

    @Mapping(target = "weekIndexesStr", expression = "java(CommonMapStructUtil.listToStr(courtRuleDetailVO.getWeekIndexes()))")
    CourtRuleDetailDO toCourtRuleDetailDO(CourtRuleDetailVO courtRuleDetailVO);

    // 领域对象 == 视图对象

    @Mapping(target = "courtVOList", source = "courtDOList")
    @Mapping(target = "courtRuleItemVOList", source = "courtRuleDetailDOList", qualifiedByName = "detailToItem")
    CourtRuleQueryVO toCourtRuleQueryVO(CourtRuleDO courtRuleDO);

    List<CourtRuleVO> toCourtRuleVOList(List<CourtRuleDO> courtRuleDOList);

    @Mapping(target = "courtRuleItemVOList", source = "courtRuleDetailDOList", qualifiedByName = "detailToItem")
    CourtRuleVO toCourtRuleVO(CourtRuleDO courtRuleDO);

    List<CourtRuleDetailVO> toCourtRuleDetailVOList(List<CourtRuleDetailDO> courtRuleDetailDOList);

    CourtRuleDetailVO toCourtRuleDetailVO(CourtRuleDetailDO courtRuleDetailDO);

    @AfterMapping
    default void afterMapping(CourtRuleDetail courtRuleDetail, CourtRuleDO courtRuleDO) {
        courtRuleDetail.setCourtRuleId(courtRuleDO.getId());
    }

    @AfterMapping
    default void afterMapping(CourtRuleAssociation courtRuleAssociation, CourtRuleDO courtRuleDO) {
        courtRuleAssociation.setCourtRuleId(courtRuleDO.getId());
    }

    @Named("courtIdList")
    static List<Long> courtIdList(List<Court> courtList) {
        if (CollectionUtils.isEmpty(courtList)) {
            return Collections.emptyList();
        }
        return courtList.stream().map(Court::getId).collect(Collectors.toList());
    }

    @Named("detailToItem")
    static List<CourtRuleItemVO> detailToItem(List<CourtRuleDetailDO> courtRuleDetailDOList) {
        if (CollectionUtils.isEmpty(courtRuleDetailDOList)) {
            return Collections.emptyList();
        }
        List<CourtRuleItemVO> result = new ArrayList<>();
        Map<String, List<CourtRuleDetailDO>> weekIndexesMap = courtRuleDetailDOList.stream().collect(Collectors.groupingBy(CourtRuleDetailDO::getWeekIndexesStr));
        for (Map.Entry<String, List<CourtRuleDetailDO>> entry : weekIndexesMap.entrySet()) {
            CourtRuleItemVO courtRuleItemVO = new CourtRuleItemVO();
            courtRuleItemVO.setCourtRuleDetailVOList(INSTANCE.toCourtRuleDetailVOList(entry.getValue()));
            courtRuleItemVO.setWeekIndexes(entry.getValue().getFirst().getWeekIndexes());
            result.add(courtRuleItemVO);
        }
        return result;
    }

    @Named("itemToDetail")
    static List<CourtRuleDetailDO> itemToDetail(List<CourtRuleItemVO> courtRuleItemVOList) {
        if (CollectionUtils.isEmpty(courtRuleItemVOList)) {
            return Collections.emptyList();
        }
        List<CourtRuleDetailVO> courtRuleDetailVOS = new ArrayList<>();
        for (CourtRuleItemVO courtRuleItemVO : courtRuleItemVOList) {
            for (CourtRuleDetailVO courtRuleDetailVO : courtRuleItemVO.getCourtRuleDetailVOList()) {
                courtRuleDetailVO.setWeekIndexes(courtRuleItemVO.getWeekIndexes());
                courtRuleDetailVOS.add(courtRuleDetailVO);
            }
        }

        return INSTANCE.toCourtRuleDetailDOList(courtRuleDetailVOS);
    }

    @Named("fillCourtDOList")
    static List<CourtDO> fillCourtDOList(List<Long> courtIds) {
        if (CollectionUtils.isEmpty(courtIds)) {
            return Collections.emptyList();
        }
        List<CourtDO> result = new ArrayList<>();
        for (Long courtId : courtIds) {
            CourtDO courtDO = new CourtDO();
            courtDO.setId(courtId);
            result.add(courtDO);
        }
        return result;
    }


}
