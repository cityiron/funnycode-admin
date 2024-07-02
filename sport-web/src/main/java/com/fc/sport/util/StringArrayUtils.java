package com.fc.sport.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tiecheng
 * @version 1.0
 * @className StringArrayUtils
 * @description
 * @date 2024/07/01 14:59
 */
public final class StringArrayUtils {

    public static List<Long> strToList(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            List<String> idsStr = Arrays.asList(ids.split(","));
            return idsStr.stream().map(Long::parseLong).toList();
        }
        return Collections.emptyList();
    }

    public static String listToStr(List<Long> longList) {
        if (CollectionUtils.isEmpty(longList)) {
            return null;
        }
        return longList.stream().map(String::valueOf).collect(Collectors.joining(","));
    }


}
