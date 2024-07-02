package com.fc.sport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fc.sport.entity.Court;
import com.fc.sport.mapper.CourtMapper;
import com.fc.sport.service.ICourtService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 场地表 服务实现类
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
@Service
public class CourtServiceImpl extends ServiceImpl<CourtMapper, Court> implements ICourtService {

    @Override
    public List<Court> listByPlaceId(Long id) {
        return this.list(new LambdaQueryWrapper<Court>().eq(Court::getPlaceId, id));
    }

}
