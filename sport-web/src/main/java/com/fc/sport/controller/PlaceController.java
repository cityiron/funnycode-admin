package com.fc.sport.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.common.utils.resp.Result;
import com.fc.common.utils.resp.ResultEnum;
import com.fc.sport.convert.PlaceMapper;
import com.fc.sport.entity.Place;
import com.fc.sport.service.IPlaceService;
import com.fc.sport.vo.PlaceVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Objects;

/**
 * <p>
 * 场馆表 前端控制器
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
@RestController
@RequestMapping("/sport/place")
public class PlaceController {

    private final IPlaceService placeService;


    public PlaceController(final IPlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping(value = "/list")
    public Result<Page<PlaceVO>> queryPageList(PlaceVO placeVO, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Place> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(placeVO)) {
            queryWrapper.likeRight(StringUtils.isNotBlank(placeVO.getName()), Place::getName, placeVO.getName());
            queryWrapper.likeRight(StringUtils.isNotBlank(placeVO.getCode()), Place::getCode, placeVO.getCode());
            queryWrapper.likeRight(StringUtils.isNotBlank(placeVO.getPhone()), Place::getPhone, placeVO.getPhone());
            queryWrapper.likeRight(StringUtils.isNotBlank(placeVO.getType()), Place::getType, placeVO.getType());
        }
        Page<Place> page = new Page<>(pageNo, pageSize);
        Page<Place> dbDates = placeService.page(page, queryWrapper);
        Page<PlaceVO> result = new Page<>(dbDates.getCurrent(), dbDates.getSize(), dbDates.getTotal());
        result.setRecords(PlaceMapper.INSTANCE.toPlaceVOList(dbDates.getRecords()));
        return Result.ofSuccess(result);
    }

    @PostMapping(value = "/add")
    public Result<Boolean> add(@RequestBody PlaceVO placeVO) {
        Place place = placeService.getByCodePro(placeVO.getCode());
        if (Objects.isNull(place)) {
            return Result.ofSuccess(placeService.save(PlaceMapper.INSTANCE.toPlace(placeVO)));
        }
        if (Boolean.FALSE.equals(place.getDeleted())) {
            return Result.ofFail(ResultEnum.NOT_ALLOW);
        }
        // 删除过，尝试恢复
        return Result.ofSuccess(placeService.recoverDeleted(place.getId()));
    }

    @PostMapping(value = "/edit")
    public Result<Boolean> edit(@RequestBody PlaceVO placeVO) {
        boolean save = placeService.updateById(PlaceMapper.INSTANCE.toPlace(placeVO));
        return Result.ofSuccess(save);
    }

    @DeleteMapping(value = "/delete")
    public Result<Boolean> delete(@RequestParam(name = "id") Long id) {
        boolean save = placeService.removeById(id);
        return Result.ofSuccess(save);
    }

    @DeleteMapping(value = "/deleteBatch")
    public Result<Boolean> deleteBatch(@RequestParam(name = "ids") String ids) {
        boolean save = placeService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return Result.ofSuccess(save);
    }

    @GetMapping(value = "/queryById")
    public Result<PlaceVO> queryById(@RequestParam(name = "id") Long id) {
        Place place = placeService.getById(id);
        return Objects.nonNull(place) ? Result.ofSuccess(PlaceMapper.INSTANCE.toPlaceVO(place)) : Result.ofFail(ResultEnum.NOT_FOUND);
    }

    @GetMapping(value = "/queryByCode")
    public Result<PlaceVO> queryByCode(@RequestParam(name = "code") String code) {
        Place place = placeService.getByCode(code);
        return Objects.nonNull(place) ? Result.ofSuccess(PlaceMapper.INSTANCE.toPlaceVO(place)) : Result.ofFail(ResultEnum.NOT_FOUND);
    }

}
