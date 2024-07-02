package com.fc.sport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.sport.entity.Court;

import java.util.List;

/**
 * <p>
 * 场地表 服务类
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
public interface ICourtService extends IService<Court> {

    List<Court> listByPlaceId(Long id);

}
