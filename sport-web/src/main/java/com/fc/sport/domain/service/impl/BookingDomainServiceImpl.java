package com.fc.sport.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fc.common.utils.model.BaseDO;
import com.fc.sport.convert.CourtMapper;
import com.fc.sport.convert.CourtRuleMapper;
import com.fc.sport.convert.PlaceMapper;
import com.fc.sport.domain.*;
import com.fc.sport.domain.context.PlaceBookingRunContext;
import com.fc.sport.domain.service.BookingDomainService;
import com.fc.sport.entity.*;
import com.fc.sport.service.*;
import com.fc.sport.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author tiecheng
 * @version 1.0
 * @className BookingDomainServiceImpl
 * @description
 * @date 2024/06/27 15:52
 */
@Slf4j
@Service
public class BookingDomainServiceImpl implements BookingDomainService {

    private final IPlaceService placeService;

    private final ICourtService courtService;

    private final ICourtRuleDetailService courtRuleDetailService;

    private final ICourtRuleAssociationService courtRuleAssociationService;

    private final ICourtRuleService courtRuleService;

    private final IBookingService bookingService;

    public BookingDomainServiceImpl(final IPlaceService placeService, final ICourtService courtService,
                                    final ICourtRuleDetailService courtRuleDetailService, final ICourtRuleAssociationService courtRuleAssociationService,
                                    final ICourtRuleService courtRuleService, final IBookingService bookingService) {
        this.placeService = placeService;
        this.courtService = courtService;
        this.courtRuleDetailService = courtRuleDetailService;
        this.courtRuleAssociationService = courtRuleAssociationService;
        this.courtRuleService = courtRuleService;
        this.bookingService = bookingService;
    }

    @Override
    public boolean createBookingInfo() {
        // 1. 只处理营业中的场馆
        List<Place> placeList =
                placeService.list(new LambdaQueryWrapper<Place>().eq(Place::getOpen, true));
        if (CollectionUtils.isEmpty(placeList)) {
            return false;
        }
        // 2. 循环一个场馆一个场馆构建数据
        for (Place place : placeList) {
            PlaceBookingRunContext runContext = PlaceBookingRunContext.build(buildPlaceBooking(place));
            if (!innerCreateBookingInfo(runContext)) {
                // todo error log
            }
        }
        return true;
    }

    private boolean innerCreateBookingInfo(PlaceBookingRunContext context) {
        Objects.requireNonNull(context, "context");
        Objects.requireNonNull(context.getPlaceDO(), "context.placeDO");

        if (context.getPlaceDO().equals(PlaceDO.EMPTY)) {
            return false;
        }

        List<Booking> bookings = new ArrayList<>();
        for (CourtDO courtDO : context.getPlaceDO().getCourtDOList()) {
            boolean open = !Objects.isNull(courtDO.getOpen()) && courtDO.getOpen();
            if (!open) {
                continue;
            }
            Booking latsBooking = bookingService.getOne(new LambdaQueryWrapper<Booking>().eq(Booking::getCourtId, courtDO.getId()).orderByAsc(Booking::getDay, Booking::getSort).last("limit 1"));
            List<Date> scheduleWeek = calcDate(latsBooking);
            for (Date date : scheduleWeek) {
                int dayOfWeek = DateUtils.getDayOfWeek(date);
                for (CourtRuleDetailWeekDO courtRuleDetailWeekDO : courtDO.getCourtRuleDetailWeekDOList()) {
                    if (courtRuleDetailWeekDO.getWeekIndex() == dayOfWeek) {
                        List<CourtRuleDetailDO> courtRuleDetailDOList = courtRuleDetailWeekDO.getCourtRuleDetailDOList();
                        courtRuleDetailDOList.sort(Comparator.comparingInt(CourtRuleDetailDO::getStartTime));
                        int i = 0;
                        int prevEndTime = 0;
                        for (CourtRuleDetailDO courtRuleDetailDO : courtRuleDetailDOList) {
                            int s = courtRuleDetailDO.getStartTime();
                            int e = courtRuleDetailDO.getEndTime();
                            if (s < prevEndTime) {
                                s = prevEndTime; // 时间数据不正确，起始时间会基于上一次的结束时间
                            } else if (s > prevEndTime) {
                                // 缺失，用场地基础价格，并且默认锁上
                                long price = 10000;
                                for (int j = prevEndTime; j < s; j++) {
                                    Booking booking = new Booking();
                                    booking.setStartTime(String.valueOf(j));
                                    booking.setEndTime(String.valueOf(j + 1));
                                    booking.setDay(date);
                                    booking.setCourtId(courtDO.getId());
                                    booking.setCourtName(courtDO.getName());
                                    booking.setPrice(price);
                                    booking.setName("¥ " + price / 100);
                                    booking.setSort(i);
                                    booking.setStatus((byte) 3);
                                    bookings.add(booking);
                                    prevEndTime = j + 1;
                                }
                            }
                            for (int j = s; j < e; j++) {
                                Booking booking = new Booking();
                                booking.setStartTime(String.valueOf(j));
                                booking.setEndTime(String.valueOf(j + 1));
                                booking.setDay(date);
                                booking.setCourtId(courtDO.getId());
                                booking.setCourtName(courtDO.getName());
                                booking.setPrice(courtRuleDetailDO.getPrice());
                                booking.setName("¥ " + courtRuleDetailDO.getPrice() / 100);
                                booking.setSort(i);
                                booking.setStatus((byte) 1);
                                bookings.add(booking);
                                i++;
                                prevEndTime = j + 1;
                            }
                        }
                    }
                }
            }
        }
        return bookingService.saveBatch(bookings, 200);
    }

    private PlaceDO buildPlaceBooking(Place place) {
        Long placeId = place.getId();

        // 1. 查询场地数据
        List<Court> courtList = courtService.list(new LambdaQueryWrapper<Court>().eq(Court::getPlaceId, placeId));
        List<CourtDO> courtDOList = CourtMapper.INSTANCE.toCourtDOListFromCourtList(courtList);
        if (CollectionUtils.isEmpty(courtList)) {
            return PlaceDO.EMPTY;
        }

        // 2. 查询场地规则数据
        List<CourtRuleDO> courtRuleDOS = queryByPlaceId(place.getId());

        // 3. 创建场地模型并丰富
        PlaceDO placeDO = PlaceMapper.INSTANCE.toPlaceDOFromPlace(place);
        placeDO.setCourtDOList(courtDOList);
        placeDO.setCourtRuleDOList(courtRuleDOS);

        // 4. 加工场地信息
        placeDO.courtProcess();

        return placeDO;
    }

    private List<CourtRuleDO> queryByPlaceId(Long id) {
        // 1. 查询场地规则数据
        List<CourtRule> courtRuleList = courtRuleService.list(new LambdaQueryWrapper<CourtRule>().eq(CourtRule::getPlaceId, id));

        // 2. 查询场地和场地规则关联数据
        List<Long> courtRuleIds = courtRuleList.stream().map(BaseDO::getId).toList();
        List<CourtRuleAssociation> courtRuleAssociations = courtRuleAssociationService.list(new LambdaQueryWrapper<CourtRuleAssociation>().in(CourtRuleAssociation::getCourtRuleId, courtRuleIds));

        // 3. 查询场地数据
        List<Long> courtIds = courtRuleAssociations.stream().map(CourtRuleAssociation::getCourtId).toList();
        List<Court> courtList = courtService.listByIds(courtIds);

        // 4. 查询场地规则详情数据
        List<CourtRuleDetail> courtRuleDetailList = courtRuleDetailService.list(new LambdaQueryWrapper<CourtRuleDetail>().in(CourtRuleDetail::getCourtRuleId, courtRuleIds));

        // 5. 把场地和场地规则关联数据和场地规则详情数据根据场地规则ID分组
        Map<Long, List<CourtRuleAssociation>> courtRuleIdMapForCourtRuleAssociation = courtRuleAssociations.stream().collect(Collectors.groupingBy(CourtRuleAssociation::getCourtRuleId, Collectors.mapping(Function.identity(), Collectors.toList())));
        Map<Long, List<CourtRuleDetail>> courtRuleIdMapForCourtRuleDetail = courtRuleDetailList.stream().collect(Collectors.groupingBy(CourtRuleDetail::getCourtRuleId, Collectors.mapping(Function.identity(), Collectors.toList())));

        // 6. 转换成模型对象
        return CourtRuleMapper.INSTANCE.toCourtRuleDOListFromCourtRuleListPro(courtRuleList, courtList, courtRuleIdMapForCourtRuleAssociation, courtRuleIdMapForCourtRuleDetail);
    }

    private List<Date> calcDate(Booking booking) {
        if (Objects.isNull(booking) || Objects.isNull(booking.getId())) {
            return DateUtils.getTargetDateToNextSunday(new Date());
        } else {
            return DateUtils.getTargetDateToNextSunday(booking.getDay());
        }
    }

}
