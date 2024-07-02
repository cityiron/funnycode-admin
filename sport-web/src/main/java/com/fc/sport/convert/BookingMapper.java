package com.fc.sport.convert;

import com.fc.sport.domain.BookingDO;
import com.fc.sport.domain.BookingStatusEnum;
import com.fc.sport.entity.Booking;
import com.fc.sport.vo.BookingUpdateCommand;
import com.fc.sport.vo.BookingVO;
import org.mapstruct.EnumMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

/**
 * @author tiecheng
 * @version 1.0
 * @className BookingMapper
 * @description
 * @date 2024/06/26 16:06
 */
@Mapper
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    BookingVO toBookingVOFromBooking(Booking booking);

    List<BookingVO> listToBookingVOFromBooking(List<Booking> bookingList);

    Booking toBooking(BookingVO bookingVO);

    @Mapping(target = "status", source = "status", qualifiedByName = "setEnumValue")
    BookingDO toBookingDO(Booking booking);

    @Mapping(target = "status", source = "status.value")
    Booking toBooking(BookingDO bookingDO);

    @Named("setEnumValue")
    static BookingStatusEnum setEnumValue(Byte status) {
        if (Objects.isNull(status)) {
            return null;
        }
        return BookingStatusEnum.findByValue(status);
    }

    @Mapping(target = "status", source = "lock", qualifiedByName = "lockStatus")
    Booking toBookingFromBookingUpdateCommand(BookingUpdateCommand command);

    @Named("lockStatus")
    static Byte lockStatus(boolean lock) {
        if (lock) {
            return 3;
        }
        return null;
    }

}
