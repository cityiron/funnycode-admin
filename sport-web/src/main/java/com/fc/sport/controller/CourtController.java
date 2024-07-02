package com.fc.sport.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.common.utils.resp.Result;
import com.fc.common.utils.resp.ResultEnum;
import com.fc.sport.convert.CourtMapper;
import com.fc.sport.entity.Court;
import com.fc.sport.entity.Place;
import com.fc.sport.service.ICourtService;
import com.fc.sport.service.IPlaceService;
import com.fc.sport.vo.CourtVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 场地表 前端控制器
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
@RestController
@RequestMapping("/sport/court")
public class CourtController {

    private final ICourtService courtService;

    private final IPlaceService placeService;


    public CourtController(final ICourtService courtService, final IPlaceService placeService) {
        this.courtService = courtService;
        this.placeService = placeService;
    }

    @GetMapping(value = "/list")
    public Result<Page<CourtVO>> queryPageList(CourtVO courtVO, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Court> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(courtVO)) {
            queryWrapper.likeRight(StringUtils.isNotBlank(courtVO.getName()), Court::getName, courtVO.getName());
            queryWrapper.eq(Objects.nonNull(courtVO.getOpen()), Court::getOpen, courtVO.getOpen());
            queryWrapper.eq(Objects.nonNull(courtVO.getType()), Court::getType, courtVO.getType());
        }
        Page<Court> page = new Page<>(pageNo, pageSize);
        Page<Court> dbDates = courtService.page(page, queryWrapper);
        Page<CourtVO> result = new Page<>(dbDates.getCurrent(), dbDates.getSize(), dbDates.getTotal());
        result.setRecords(CourtMapper.INSTANCE.toCourtVOList(dbDates.getRecords()));
        return Result.ofSuccess(result);
    }

    @PostMapping(value = "/add")
    public Result<Boolean> add(@RequestBody CourtVO courtVO) {
        boolean save = courtService.save(CourtMapper.INSTANCE.toCourt(courtVO));
        return Result.ofSuccess(save);
    }

    @PostMapping(value = "/addBatch")
    public Result<Boolean> addBatch(@RequestBody List<CourtVO> courtVOs) {
        boolean save = courtService.saveBatch(CourtMapper.INSTANCE.toCourtList(courtVOs));
        return Result.ofSuccess(save);
    }

    @PostMapping(value = "/edit")
    public Result<Boolean> edit(@RequestBody CourtVO courtVO) {
        boolean save = courtService.updateById(CourtMapper.INSTANCE.toCourt(courtVO));
        return Result.ofSuccess(save);
    }

    @DeleteMapping(value = "/delete")
    public Result<Boolean> delete(@RequestParam(name = "id") Long id) {
        boolean save = courtService.removeById(id);
        return Result.ofSuccess(save);
    }

    @DeleteMapping(value = "/deleteBatch")
    public Result<Boolean> deleteBatch(@RequestParam(name = "ids") String ids) {
        boolean save = courtService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return Result.ofSuccess(save);
    }

    @GetMapping(value = "/queryById")
    public Result<CourtVO> queryById(@RequestParam(name = "id") Long id) {
        Court court = courtService.getById(id);
        return Objects.nonNull(court) ? Result.ofSuccess(CourtMapper.INSTANCE.toCourtVO(court)) : Result.ofFail(ResultEnum.NOT_FOUND);
    }

    @GetMapping(value = "/queryByPlaceId")
    public Result<List<CourtVO>> queryByPlaceId(@RequestParam(name = "id") Long id) {
        Place place = placeService.getById(id);
        if (Objects.isNull(place)) {
            return Result.ofSuccess(Collections.emptyList());
        }
        List<Court> result = courtService.listByPlaceId(id);
        return Result.ofSuccess(CourtMapper.INSTANCE.toCourtVOList(result));
    }

    @GetMapping(value = "/queryByPlaceCode")
    public Result<List<CourtVO>> queryByPlaceCode(@RequestParam(name = "code") String code) {
        Place place = placeService.getByCode(code);
        if (Objects.isNull(place)) {
            return Result.ofSuccess(Collections.emptyList());
        }
        List<Court> result = courtService.listByPlaceId(place.getId());
        return Result.ofSuccess(CourtMapper.INSTANCE.toCourtVOList(result));
    }

}
