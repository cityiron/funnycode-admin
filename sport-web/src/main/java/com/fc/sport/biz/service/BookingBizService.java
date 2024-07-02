package com.fc.sport.biz.service;

import com.fc.sport.vo.BookingUpdateCommand;
import com.fc.sport.vo.BookingVO;

import java.util.List;

/**
 * @className BookingService
 * @description 
 * @author tiecheng
 * @date 2024/07/02 11:13
 * @version 1.0
 */
public interface BookingBizService {

    boolean update(BookingUpdateCommand command);

    boolean deleteByPlaceId(Long id);

    List<BookingVO> queryByPlaceId(Long id);

}
