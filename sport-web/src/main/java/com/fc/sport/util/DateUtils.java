package com.fc.sport.util;

import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static java.time.temporal.ChronoField.DAY_OF_WEEK;

/**
 * @author tiecheng
 * @version 1.0
 * @className DateUtils
 * @description
 * @date 2024/07/01 14:26
 */
public final class DateUtils {

    public static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai"); // 设置东八区时区

    /**
     * 获取周几
     *
     * @param date 日期
     * @return
     */
    public static int getDayOfWeek(Date date) {
        LocalDate localDate = convertDateToLocalDate(date);

        return localDate.getDayOfWeek().get(DAY_OF_WEEK);
    }

    /**
     * 日期 {@link Date} 转 {@link LocalDate}
     *
     * @param date
     * @return
     */
    public static LocalDate convertDateToLocalDate(Date date) {
        Objects.requireNonNull(date, "date");

        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZONE_ID);

        return zonedDateTime.toLocalDate();
    }

    /**
     * 获取当前日期到下一个周天的日期集合
     *
     * @return
     */
    public static List<Date> getCurrentDateToNextSunday() {
        return getTargetDateToNextSunday(LocalDate.now());
    }

    /**
     * 获取目标日期到下一个周天的日期集合
     *
     * @param date 日期
     * @return
     */
    public static List<Date> getTargetDateToNextSunday(Date date) {
        return Objects.isNull(date) ? getTargetDateToNextSunday(LocalDate.now()) : getTargetDateToNextSunday(convertDateToLocalDate(date));
    }

    /**
     * 获取目标日期到下一个周天的日期集合
     *
     * @param currentDate 日期
     * @return
     */
    public static List<Date> getTargetDateToNextSunday(LocalDate currentDate) {
        if (Objects.isNull(currentDate)) {
            // 1. 获取当前日期
            currentDate = LocalDate.now();
        }

        // 2. 计算下一个周日
        LocalDate nextSunday = getNextSunday(currentDate);

        // 3. 创建日期集合
        List<Date> dates = new ArrayList<>();

        for (LocalDate date = currentDate; !date.isAfter(nextSunday); date = date.plusDays(1)) {
            // 使用atStartOfDay并指定时区
            LocalDateTime dateTime = date.atStartOfDay(ZONE_ID).toLocalDateTime();
            // 将LocalDateTime转换为Instant
            Instant instant = dateTime.toInstant(ZoneOffset.ofHours(8)); // 注意：这里我们使用ZoneOffset而不是ZoneId，因为我们只关心偏移量
            // 将Instant转换为Date
            dates.add(Date.from(instant));
        }

        return dates;
    }

    private static LocalDate getNextSunday(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int daysToAdd = 7 - dayOfWeek.getValue() % 7; // 计算需要添加的天数以到达下一个周日
        return date.plusDays(daysToAdd);
    }

}
