package com.fc.sport.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.common.utils.resp.Result;
import com.fc.common.utils.resp.ResultEnum;
import com.fc.sport.convert.CourtRuleMapper;
import com.fc.sport.domain.CourtRuleDO;
import com.fc.sport.domain.service.CourtRuleDomainService;
import com.fc.sport.vo.CourtRuleDetailAddCommand;
import com.fc.sport.vo.CourtRuleDetailUpdateCommand;
import com.fc.sport.vo.CourtRuleQueryVO;
import com.fc.sport.vo.CourtRuleVO;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 场地规则表 前端控制器
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
@RestController
@RequestMapping("/sport/court-rule")
public class CourtRuleController {

    private final CourtRuleDomainService courtRuleDomainService;

    public CourtRuleController(final CourtRuleDomainService courtRuleDomainService) {
        this.courtRuleDomainService = courtRuleDomainService;
    }

    @GetMapping(value = "/list")
    public Result<Page<CourtRuleQueryVO>> queryPageList(CourtRuleVO courtRuleVO, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return Result.ofSuccess(courtRuleDomainService.page(pageNo, pageSize, CourtRuleMapper.INSTANCE.toCourtRuleDO(courtRuleVO)));
    }

    @PostMapping(value = "/add")
    public Result<Boolean> add(@RequestBody CourtRuleVO courtRuleVO) {
        return Result.ofSuccess(courtRuleDomainService.save(CourtRuleMapper.INSTANCE.toCourtRuleDO(courtRuleVO)));
    }

    @PostMapping(value = "/addBatch")
    public Result<Boolean> addBatch(@RequestBody List<CourtRuleVO> courtRuleVOList) {
        return Result.ofSuccess(courtRuleDomainService.saveBatch(CourtRuleMapper.INSTANCE.toCourtRuleDOList(courtRuleVOList)));
    }

    @PostMapping(value = "/edit")
    public Result<Boolean> edit(@RequestBody CourtRuleVO courtRuleVO) {
        return Result.ofSuccess(courtRuleDomainService.update(CourtRuleMapper.INSTANCE.toCourtRuleDO(courtRuleVO)));
    }

    @PostMapping(value = "/addDetail")
    public Result<Boolean> addDetail(@RequestBody CourtRuleDetailAddCommand courtRuleDetailAddCommand) {
        return Result.ofSuccess(courtRuleDomainService.addDetail(CourtRuleMapper.INSTANCE.convertCourtRuleDO(courtRuleDetailAddCommand)));
    }

    @PostMapping(value = "/editDetail")
    public Result<Boolean> editDetail(@RequestBody List<CourtRuleDetailUpdateCommand> courtRuleDetailUpdateCommandList) {
        return Result.ofSuccess(courtRuleDomainService.updateDetail(CourtRuleMapper.INSTANCE.convertCourtRuleDO(courtRuleDetailUpdateCommandList)));
    }

    @DeleteMapping(value = "/delete")
    public Result<Boolean> delete(@RequestParam(name = "id") Long id) {
        return Result.ofSuccess(courtRuleDomainService.remove(id));
    }

    @DeleteMapping(value = "/deleteBatch")
    public Result<Boolean> deleteBatch(@RequestParam(name = "ids") String ids) {
        List<String> idsStr = Arrays.asList(ids.split(","));
        return Result.ofSuccess(courtRuleDomainService.removeBatch(idsStr.stream().map(Long::parseLong).collect(Collectors.toList())));
    }

    @GetMapping(value = "/queryById")
    public Result<CourtRuleQueryVO> queryById(@RequestParam(name = "id") Long id) {
        CourtRuleDO courtRuleDO = courtRuleDomainService.queryById(id);
        return Objects.nonNull(courtRuleDO) ? Result.ofSuccess(CourtRuleMapper.INSTANCE.toCourtRuleQueryVO(courtRuleDO)) : Result.ofFail(ResultEnum.NOT_FOUND);
    }

}
