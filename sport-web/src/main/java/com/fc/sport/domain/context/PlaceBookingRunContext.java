package com.fc.sport.domain.context;

import com.fc.sport.domain.PlaceDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * @author tiecheng
 * @version 1.0
 * @className PlaceBookingRunContext
 * @description
 * @date 2024/07/02 09:07
 */
@Getter
@Setter
@ToString
public class PlaceBookingRunContext {

    private PlaceDO placeDO;

    public static PlaceBookingRunContext build(PlaceDO placeDO) {
        Objects.requireNonNull(placeDO, "placeDO");
        Objects.requireNonNull(placeDO.getCourtRuleDOList(), "courtRuleDOList");
        PlaceBookingRunContext placeBookingRunContext = new PlaceBookingRunContext();
        placeBookingRunContext.setPlaceDO(placeDO);

        return placeBookingRunContext;
    }

}
