package com.fc.sport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.sport.entity.Place;

/**
 * <p>
 * 场馆表 服务类
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
public interface IPlaceService extends IService<Place> {

    Place getByCode(String code);

    Place getByCodePro(String code);

    boolean recoverDeleted(Long id);

}
