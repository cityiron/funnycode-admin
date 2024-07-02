package com.fc.sport.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fc.sport.biz.service.BookingBizService;
import com.fc.sport.convert.BookingMapper;
import com.fc.sport.entity.Booking;
import com.fc.sport.entity.Court;
import com.fc.sport.service.IBookingService;
import com.fc.sport.service.ICourtService;
import com.fc.sport.vo.BookingUpdateCommand;
import com.fc.sport.vo.BookingVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author tiecheng
 * @version 1.0
 * @className BookingServiceImpl
 * @description
 * @date 2024/07/02 11:13
 */
@Service
public class BookingBizServiceImpl implements BookingBizService {

    private final IBookingService bookingService;

    private final ICourtService courtService;

    public BookingBizServiceImpl(final IBookingService bookingService, final ICourtService courtService) {
        this.bookingService = bookingService;
        this.courtService = courtService;
    }

    @Override
    public boolean update(BookingUpdateCommand command) {
        Booking booking = BookingMapper.INSTANCE.toBookingFromBookingUpdateCommand(command);
        return bookingService.updateById(booking);
    }

    @Override
    public boolean deleteByPlaceId(Long id) {
        List<Court> courtList = courtService.list(new LambdaQueryWrapper<Court>().eq(Court::getPlaceId, id));
        if (CollectionUtils.isEmpty(courtList)) {
            return true;
        }
        return bookingService.remove(new LambdaQueryWrapper<Booking>().in(Booking::getCourtId, courtList.stream().map(Court::getId).toList()));
    }

    @Override
    public List<BookingVO> queryByPlaceId(Long id) {
        List<Court> courtList = courtService.list(new LambdaQueryWrapper<Court>().eq(Court::getPlaceId, id));
        if (CollectionUtils.isEmpty(courtList)) {
            return Collections.emptyList();
        }
        List<Booking> dbDates = bookingService.list(new LambdaQueryWrapper<Booking>().in(Booking::getCourtId, courtList.stream().map(Court::getId).toList()));
        return BookingMapper.INSTANCE.listToBookingVOFromBooking(dbDates);
    }

}
