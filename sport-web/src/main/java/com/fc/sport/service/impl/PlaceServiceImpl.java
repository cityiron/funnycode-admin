package com.fc.sport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fc.sport.entity.Place;
import com.fc.sport.mapper.PlaceMapper;
import com.fc.sport.service.IPlaceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 场馆表 服务实现类
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
@Service
public class PlaceServiceImpl extends ServiceImpl<PlaceMapper, Place> implements IPlaceService {

    @Override
    public Place getByCode(String code) {
        return this.getOne(new LambdaQueryWrapper<Place>().eq(Place::getCode, code));
    }

    @Override
    public Place getByCodePro(String code) {
        return this.getBaseMapper().getByCodePro(code);
    }

    @Override
    public boolean recoverDeleted(Long id) {
        return this.getBaseMapper().recoverDeleted(id);
    }

}
