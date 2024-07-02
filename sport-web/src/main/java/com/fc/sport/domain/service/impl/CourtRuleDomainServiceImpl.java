package com.fc.sport.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.common.utils.model.BaseDO;
import com.fc.sport.convert.CourtRuleMapper;
import com.fc.sport.domain.CourtRuleDO;
import com.fc.sport.domain.service.CourtRuleDomainService;
import com.fc.sport.entity.Court;
import com.fc.sport.entity.CourtRule;
import com.fc.sport.entity.CourtRuleAssociation;
import com.fc.sport.entity.CourtRuleDetail;
import com.fc.sport.service.ICourtRuleAssociationService;
import com.fc.sport.service.ICourtRuleDetailService;
import com.fc.sport.service.ICourtRuleService;
import com.fc.sport.service.ICourtService;
import com.fc.sport.vo.CourtRuleQueryVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author tiecheng
 * @version 1.0
 * @className CourtRuleDomainServiceImpl
 * @description
 * @date 2024/06/27 11:10
 */
@Service
public class CourtRuleDomainServiceImpl implements CourtRuleDomainService {

    private final ICourtRuleService courtRuleService;

    private final ICourtRuleAssociationService courtRuleAssociationService;

    private final ICourtService courtService;

    private final ICourtRuleDetailService courtRuleDetailService;

    public CourtRuleDomainServiceImpl(final ICourtRuleService courtRuleService, final ICourtRuleAssociationService courtRuleAssociationService, final ICourtService courtService, final ICourtRuleDetailService courtRuleDetailService) {
        this.courtRuleService = courtRuleService;
        this.courtRuleAssociationService = courtRuleAssociationService;
        this.courtService = courtService;
        this.courtRuleDetailService = courtRuleDetailService;
    }

    @Override
    @Transactional
    public boolean save(CourtRuleDO courtRuleDO) {
        if (Objects.isNull(courtRuleDO)) {
            return false;
        }
        return doSave(courtRuleDO);
    }

    @Override
    @Transactional
    public boolean saveBatch(List<CourtRuleDO> courtRuleDOList) {
        if (CollectionUtils.isEmpty(courtRuleDOList)) {
            return false;
        }
        for (CourtRuleDO courtRuleDO : courtRuleDOList) {
            if (!doSave(courtRuleDO)) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional
    public boolean update(CourtRuleDO courtRuleDO) {
        if (Objects.isNull(courtRuleDO) || CollectionUtils.isEmpty(courtRuleDO.getCourtIds())) {
            return false;
        }
        CourtRule courtRule = CourtRuleMapper.INSTANCE.toCourtRule(courtRuleDO);
        boolean save = courtRuleService.updateById(courtRule);
        if (save) {
            return doUpdateCourtRuleDetail(courtRuleDO, false) && doUpdateCourtAssociation(courtRuleDO);
        }
        return true;
    }

    @Override
    public boolean addDetail(CourtRuleDO courtRuleDO) {
        return false;
    }

    @Override
    public boolean updateDetail(CourtRuleDO courtRuleDO) {
        return doUpdateCourtRuleDetail(courtRuleDO, true);
    }

    @Override
    @Transactional
    public boolean remove(Long id) {
        return doRemove(id);
    }

    @Override
    @Transactional
    public boolean removeBatch(List<Long> ids) {
        return ids.stream().anyMatch(this::doRemove);
    }

    @Override
    public CourtRuleDO queryById(Long id) {
        CourtRule courtRule = courtRuleService.getById(id);
        List<CourtRuleAssociation> courtRuleAssociations = courtRuleAssociationService.list(new LambdaQueryWrapper<CourtRuleAssociation>().eq(CourtRuleAssociation::getCourtRuleId, id));
        List<Long> courtIds = courtRuleAssociations.stream().map(CourtRuleAssociation::getCourtId).toList();
        List<Court> courtList = courtService.listByIds(courtIds);
        List<CourtRuleDetail> courtRuleDetailList = courtRuleDetailService.list(new LambdaQueryWrapper<CourtRuleDetail>().eq(CourtRuleDetail::getCourtRuleId, id));
        return CourtRuleMapper.INSTANCE.toCourtRuleDO(courtRule, courtList, courtRuleDetailList);
    }

    @Override
    public Page<CourtRuleQueryVO> page(Integer pageNo, Integer pageSize, CourtRuleDO courtRuleDO) {
        LambdaQueryWrapper<CourtRule> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(courtRuleDO)) {
            queryWrapper.likeRight(StringUtils.isNotBlank(courtRuleDO.getName()), CourtRule::getName, courtRuleDO.getName());
            queryWrapper.eq(Objects.nonNull(courtRuleDO.getPlaceId()), CourtRule::getPlaceId, courtRuleDO.getPlaceId());
            queryWrapper.ge(Objects.nonNull(courtRuleDO.getEffectiveStart()), CourtRule::getEffectiveStart, courtRuleDO.getEffectiveStart());
            queryWrapper.le(Objects.nonNull(courtRuleDO.getEffectiveEnd()), CourtRule::getEffectiveEnd, courtRuleDO.getEffectiveEnd());
        }
        Page<CourtRule> page = new Page<>(pageNo, pageSize);
        Page<CourtRule> dbDates = courtRuleService.page(page, queryWrapper);
        Page<CourtRuleQueryVO> result = new Page<>(dbDates.getCurrent(), dbDates.getSize(), dbDates.getTotal());
        if (CollectionUtils.isEmpty(dbDates.getRecords())) {
            return result;
        }

        // 获取所有关联的场馆信息
        List<Long> courtRuleIds = dbDates.getRecords().stream().map(BaseDO::getId).collect(Collectors.toList());
        List<CourtRuleAssociation> courtRuleAssociations = courtRuleAssociationService.list(new LambdaQueryWrapper<CourtRuleAssociation>().in(CourtRuleAssociation::getCourtRuleId, courtRuleIds));
        List<Long> courtIds = courtRuleAssociations.stream().map(CourtRuleAssociation::getCourtId).collect(Collectors.toList());
        List<Court> courtList = courtService.listByIds(courtIds);
        // 场馆信息根据规则ID分组
        Map<Long, List<CourtRuleAssociation>> courtRuleIdMapForCourtRuleAssociation = courtRuleAssociations.stream().collect(Collectors.groupingBy(CourtRuleAssociation::getCourtRuleId, Collectors.mapping(Function.identity(), Collectors.toList())));
        List<CourtRuleDetail> courtRuleDetailList = courtRuleDetailService.list(new LambdaQueryWrapper<CourtRuleDetail>().in(CourtRuleDetail::getCourtRuleId, courtRuleIds));
        Map<Long, List<CourtRuleDetail>> courtRuleIdMapForCourtRuleDetail = courtRuleDetailList.stream().collect(Collectors.groupingBy(CourtRuleDetail::getCourtRuleId, Collectors.mapping(Function.identity(), Collectors.toList())));
        List<CourtRuleQueryVO> courtRuleQueryVOList = new ArrayList<>();
        for (CourtRule record : dbDates.getRecords()) {
            List<Long> currentRuleCourtIds = courtRuleIdMapForCourtRuleAssociation.get(record.getId()).stream().map(CourtRuleAssociation::getCourtId).collect(Collectors.toList());
            CourtRuleDO currentCourtRuleDO = CourtRuleMapper.INSTANCE.toCourtRuleDO(record, courtList.stream().filter(c -> currentRuleCourtIds.contains(c.getId())).collect(Collectors.toList()), courtRuleIdMapForCourtRuleDetail.get(record.getId()));
            CourtRuleQueryVO courtRuleQueryVO = CourtRuleMapper.INSTANCE.toCourtRuleQueryVO(currentCourtRuleDO);
            courtRuleQueryVOList.add(courtRuleQueryVO);
        }
        result.setRecords(courtRuleQueryVOList);
        return result;
    }

    private boolean doSave(CourtRuleDO courtRuleDO) {
        ruleCheck(courtRuleDO);
        CourtRule courtRule = CourtRuleMapper.INSTANCE.toCourtRule(courtRuleDO);
        boolean save = courtRuleService.save(courtRule);
        if (save) {
            courtRuleDO.setId(courtRule.getId());
            List<CourtRuleDetail> courtRuleDetailList = CourtRuleMapper.INSTANCE.toCourtRuleDetailList(courtRuleDO);
            List<CourtRuleAssociation> courtRuleAssociationList = CourtRuleMapper.INSTANCE.toCourtRuleAssociationList(courtRuleDO);
            return courtRuleAssociationService.saveBatch(courtRuleAssociationList) && courtRuleDetailService.saveBatch(courtRuleDetailList);
        }
        return false;
    }

    private boolean doUpdateCourtRuleDetail(CourtRuleDO courtRuleDO, boolean onlyUpdate) {
        if (onlyUpdate) {
            List<CourtRuleDetail> toUpdateCourtRuleDetailList = CourtRuleMapper.INSTANCE.toCourtRuleDetailList(courtRuleDO);
            return courtRuleDetailService.updateBatchById(toUpdateCourtRuleDetailList);
        } else {
            Long id = courtRuleDO.getId();
            // 1. 删除旧数据
            List<CourtRuleDetail> courtRuleDetailList = courtRuleDetailService.list(new LambdaQueryWrapper<CourtRuleDetail>().eq(CourtRuleDetail::getCourtRuleId, id));
            List<Long> oldIds = courtRuleDetailList.stream().map(CourtRuleDetail::getId).toList();
            boolean remove = courtRuleDetailService.removeBatchByIds(oldIds);
            if (remove) {
                // 2. 插入新数据
                List<CourtRuleDetail> toSaveCourtRuleDetailList = CourtRuleMapper.INSTANCE.toCourtRuleDetailList(courtRuleDO);
                return courtRuleDetailService.saveBatch(toSaveCourtRuleDetailList);
            }
        }

        return false;
    }

    private boolean doUpdateCourtAssociation(CourtRuleDO courtRuleDO) {
        List<Long> courtIds = courtRuleDO.getCourtIds();
        Long id = courtRuleDO.getId();
        List<CourtRuleAssociation> courtRuleAssociations = courtRuleAssociationService.list(new LambdaQueryWrapper<CourtRuleAssociation>().eq(CourtRuleAssociation::getCourtRuleId, id));
        List<Long> addList = new ArrayList<>();
        List<Long> removeList = new ArrayList<>();
        for (CourtRuleAssociation courtRuleAssociation : courtRuleAssociations) {
            if (!courtIds.contains(courtRuleAssociation.getCourtId())) {
                removeList.add(courtRuleAssociation.getCourtId());
            }
        }
        for (Long courtId : courtIds) {
            if (courtRuleAssociations.stream().noneMatch(c -> c.getCourtId().equals(courtId))) {
                addList.add(courtId);
            }
        }
        if (CollectionUtils.isNotEmpty(addList)) {
            List<CourtRuleAssociation> saveDates = new ArrayList<>();
            for (Long addId : addList) {
                CourtRuleAssociation courtRuleAssociation = new CourtRuleAssociation();
                courtRuleAssociation.setCourtRuleId(id);
                courtRuleAssociation.setCourtId(addId);
                saveDates.add(courtRuleAssociation);
            }
            if (!courtRuleAssociationService.saveBatch(saveDates)) {
                return false;
            }
        }

        if (CollectionUtils.isNotEmpty(removeList) && (!courtRuleAssociationService.removeBatchByIds(removeList))) {
            return false;

        }
        return true;
    }

    private boolean doRemove(Long id) {
        return courtRuleService.removeById(id) && courtRuleAssociationService.remove(new LambdaQueryWrapper<CourtRuleAssociation>().eq(CourtRuleAssociation::getCourtRuleId, id)) &&
                courtRuleDetailService.remove(new LambdaQueryWrapper<CourtRuleDetail>().eq(CourtRuleDetail::getCourtRuleId, id));
    }

    private void ruleCheck(CourtRuleDO courtRuleDO) {
        // TODO 规则校验，业务诉求不清晰
    }

}
