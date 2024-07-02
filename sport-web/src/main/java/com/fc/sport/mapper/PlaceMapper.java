package com.fc.sport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.sport.entity.Place;

/**
 * <p>
 * 场馆表 Mapper 接口
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
public interface PlaceMapper extends BaseMapper<Place> {

    Place getByCodePro(String code);

    boolean recoverDeleted(Long id);

}
