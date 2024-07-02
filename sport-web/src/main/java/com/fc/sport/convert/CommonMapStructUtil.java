package com.fc.sport.convert;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tiecheng
 * @version 1.0
 * @className MapStructUtil
 * @description
 * @date 2024/06/27 13:27
 */

@Named("CommonMapStructUtil")
public class CommonMapStructUtil {

    @Named("strToList")
    public static List<Long> strToList(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            List<String> idsStr = Arrays.asList(ids.split(","));
            return idsStr.stream().map(Long::parseLong).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Named("listToStr")
    public static String listToStr(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return null;
        }
        return ids.stream().map(String::valueOf).collect(Collectors.joining(","));
    }


}
