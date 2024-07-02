package com.fc.sport.service.impl;

import com.fc.sport.entity.Booking;
import com.fc.sport.mapper.BookingMapper;
import com.fc.sport.service.IBookingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 预定表 服务实现类
 * </p>
 *
 * @author tiecheng
 * @since 2024-06-26
 */
@Service
public class BookingServiceImpl extends ServiceImpl<BookingMapper, Booking> implements IBookingService {

}
