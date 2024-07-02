package com.fc.sport.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fc.sport.domain.service.BookingDomainService;
import com.fc.sport.entity.*;
import com.fc.sport.service.*;
import com.fc.sport.util.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingDomainServiceImplTest {

    @Mock
    private IBookingService bookingService;

    @Mock
    private IPlaceService placeService;

    @Mock
    private ICourtService courtService;

    @Mock
    private ICourtRuleService courtRuleService;

    @Mock
    private ICourtRuleDetailService courtRuleDetailService;

    @Mock
    private ICourtRuleAssociationService courtRuleAssociationService;

    private BookingDomainService bookingDomainService;

    private static Long PLACE_ID = 10086L;

    private static List<Long> COURT_RULE_IDS = new ArrayList<>();

    private static Map<Long, List<Long>> COURT_RULE_ASSOCIATION_IDS = new HashMap<>();

    static {
        COURT_RULE_IDS.add(1L);
        COURT_RULE_IDS.add(2L);

        List<Long> ids1 = new ArrayList<>();
        ids1.add(101L);
        ids1.add(102L);
        ids1.add(103L);
        COURT_RULE_ASSOCIATION_IDS.put(1L, ids1);

        List<Long> ids2 = new ArrayList<>();
        ids2.add(104L);
        COURT_RULE_ASSOCIATION_IDS.put(2L, ids2);
    }

    @Before
    public void before() {
        bookingDomainService = new BookingDomainServiceImpl(placeService, courtService, courtRuleDetailService, courtRuleAssociationService, courtRuleService, bookingService);
    }

    @Test
    public void test_Date() {
        List<Date> currentWeekDatesToNextSunday = DateUtils.getCurrentDateToNextSunday();
        System.out.println(currentWeekDatesToNextSunday.size());
        for (Date date : currentWeekDatesToNextSunday) {
            System.out.println(DateUtils.getDayOfWeek(date));
        }
        LocalDate specificDate = LocalDate.of(2024, 7, 11);
        currentWeekDatesToNextSunday = DateUtils.getTargetDateToNextSunday(specificDate);
        System.out.println(currentWeekDatesToNextSunday.size());
    }

    @Test
    public void test_booking() {
        Mockito.when(placeService.list(Mockito.any(LambdaQueryWrapper.class))).thenReturn(mockPlaceList());
//        Mockito.when(placeService.list(ArgumentMatchers.argThat())).thenReturn(mockPlaceList());

        Mockito.when(bookingService.getOne(new LambdaQueryWrapper<Booking>().eq(Booking::getCourtId, 1).orderByAsc(Booking::getDay, Booking::getSort).last("limit 1"))).thenReturn(firstBookingPlace());
//        Mockito.when(placeService.list(new LambdaQueryWrapper<Place>().eq(Place::getOpen, true))).thenReturn(mockPlaceList());
        Mockito.when(courtService.list(new LambdaQueryWrapper<Court>().eq(Court::getPlaceId, PLACE_ID))).thenReturn(mockCourtList());
        Mockito.when(courtRuleService.list(new LambdaQueryWrapper<CourtRule>().eq(CourtRule::getPlaceId, PLACE_ID))).thenReturn(mockCourtRuleList());
        Mockito.when(courtRuleAssociationService.list(new LambdaQueryWrapper<CourtRuleAssociation>().in(CourtRuleAssociation::getCourtRuleId, COURT_RULE_IDS))).thenReturn(mockCourtRuleAssociationList());
        Mockito.when(courtService.listByIds(COURT_RULE_ASSOCIATION_IDS.get(1L))).thenReturn(mockCourtList(COURT_RULE_ASSOCIATION_IDS.get(1L)));
        Mockito.when(courtService.listByIds(COURT_RULE_ASSOCIATION_IDS.get(2L))).thenReturn(mockCourtList(COURT_RULE_ASSOCIATION_IDS.get(2L)));
        Mockito.when(courtRuleDetailService.list(new LambdaQueryWrapper<CourtRuleDetail>().in(CourtRuleDetail::getCourtRuleId, COURT_RULE_IDS))).thenReturn(mockCourtRuleDetailList());
        bookingDomainService.createBookingInfo();
    }

    private Booking firstBookingPlace() {
        Booking booking = new Booking();
        booking.setCourtId(1L);
        booking.setStatus((byte) 1);
        return booking;
    }

    private List<Place> mockPlaceList() {
        List<Place> placeList = new ArrayList<>();
        Place place = new Place();
        place.setId(PLACE_ID);
        place.setOpen(true);
        placeList.add(place);
        return placeList;
    }

    private List<Court> mockCourtList() {
        List<Court> courtList = new ArrayList<>();
        courtList.add(normal("1号", 101L));
        courtList.add(normal("2号", 102L));
        courtList.add(normal("3号", 103L));
        courtList.add(normal("4号", 104L));
        return courtList;
    }

    private List<Court> mockCourtList(List<Long> ids) {
        return mockCourtList().stream().filter(c -> ids.contains(c.getId())).toList();
    }

    private Court normal(String name, Long id) {
        Court court = new Court();
        court.setPlaceId(PLACE_ID);
        court.setName(name);
        court.setId(id);
        court.setOpen(true);
        court.setType((byte) 1);
        return court;
    }

    private List<CourtRule> mockCourtRuleList() {
        List<CourtRule> courtRuleList = new ArrayList<>();
        for (Long courtRuleId : COURT_RULE_IDS) {
            CourtRule cr = new CourtRule();
            cr.setEffect(true);
            cr.setName("普通场地预定规则" + courtRuleId);
            cr.setMinPrice(5000L);
            cr.setPlaceId(PLACE_ID);
            cr.setId(courtRuleId);
            courtRuleList.add(cr);
        }
        return courtRuleList;
    }

    private List<CourtRuleAssociation> mockCourtRuleAssociationList() {
        List<CourtRuleAssociation> courtRuleAssociationList = new ArrayList<>();
        for (Map.Entry<Long, List<Long>> entry : COURT_RULE_ASSOCIATION_IDS.entrySet()) {
            for (Long l : entry.getValue()) {
                CourtRuleAssociation cra = new CourtRuleAssociation();
                cra.setCourtRuleId(entry.getKey());
                cra.setCourtId(l);
                courtRuleAssociationList.add(cra);
            }
        }
        return courtRuleAssociationList;
    }

    private List<CourtRuleDetail> mockCourtRuleDetailList() {
        String week1T5 = String.join("1,2,3,4,5", ",");
        List<CourtRuleDetail> courtRuleDetailList = new ArrayList<>();
        CourtRuleDetail crd1 = new CourtRuleDetail();
        crd1.setPlaceId(PLACE_ID);
        crd1.setStartTime((byte) 7);
        crd1.setEndTime((byte) 12);
        crd1.setCourtRuleId(1L);
        crd1.setPrice(8000L);
        crd1.setWeekIndexes(week1T5);
        courtRuleDetailList.add(crd1);
        CourtRuleDetail crd2 = new CourtRuleDetail();
        crd2.setPlaceId(PLACE_ID);
        crd2.setStartTime((byte) 12);
        crd2.setEndTime((byte) 18);
        crd2.setCourtRuleId(1L);
        crd2.setPrice(10000L);
        crd2.setWeekIndexes(week1T5);
        courtRuleDetailList.add(crd2);
        CourtRuleDetail crd3 = new CourtRuleDetail();
        crd3.setPlaceId(PLACE_ID);
        crd3.setStartTime((byte) 18);
        crd3.setEndTime((byte) 24);
        crd3.setCourtRuleId(1L);
        crd3.setPrice(11000L);
        crd3.setWeekIndexes(week1T5);
        courtRuleDetailList.add(crd3);
        String week6T7 = String.join("6,7", ",");
        CourtRuleDetail crd4 = new CourtRuleDetail();
        crd4.setPlaceId(PLACE_ID);
        crd4.setStartTime((byte) 7);
        crd4.setEndTime((byte) 12);
        crd4.setCourtRuleId(1L);
        crd4.setPrice(6000L);
        crd4.setWeekIndexes(week1T5);
        courtRuleDetailList.add(crd4);
        CourtRuleDetail crd5 = new CourtRuleDetail();
        crd5.setPlaceId(PLACE_ID);
        crd5.setStartTime((byte) 12);
        crd5.setEndTime((byte) 18);
        crd5.setCourtRuleId(1L);
        crd5.setPrice(9000L);
        crd5.setWeekIndexes(week1T5);
        courtRuleDetailList.add(crd5);
        CourtRuleDetail crd6 = new CourtRuleDetail();
        crd6.setPlaceId(PLACE_ID);
        crd6.setStartTime((byte) 18);
        crd6.setEndTime((byte) 24);
        crd6.setCourtRuleId(1L);
        crd6.setPrice(10000L);
        crd6.setWeekIndexes(week1T5);
        courtRuleDetailList.add(crd6);
        return courtRuleDetailList;
    }

}