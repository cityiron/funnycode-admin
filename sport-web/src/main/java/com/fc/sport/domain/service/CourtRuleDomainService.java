package com.fc.sport.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.sport.domain.CourtRuleDO;
import com.fc.sport.vo.CourtRuleQueryVO;

import java.util.List;

/**
 * @author tiecheng
 * @version 1.0
 * @className CourtRuleDomainService
 * @description
 * @date 2024/06/27 11:05
 */
public interface CourtRuleDomainService {

    boolean save(CourtRuleDO courtRuleDO);

    boolean saveBatch(List<CourtRuleDO> courtRuleDOList);

    boolean update(CourtRuleDO courtRuleDO);

    boolean addDetail(CourtRuleDO courtRuleDO);

    boolean updateDetail(CourtRuleDO courtRuleDO);

    boolean remove(Long id);

    boolean removeBatch(List<Long> ids);

    CourtRuleDO queryById(Long id);

    Page<CourtRuleQueryVO> page(Integer pageNo, Integer pageSize, CourtRuleDO courtRuleDO);

}
