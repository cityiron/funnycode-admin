package com.fc.sport.convert;

import com.fc.sport.domain.PlaceDO;
import com.fc.sport.entity.Place;
import com.fc.sport.vo.BookingVO;
import com.fc.sport.vo.PlaceVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author tiecheng
 * @version 1.0
 * @className BookingMapper
 * @description
 * @date 2024/06/26 16:06
 */
@Mapper
public interface PlaceMapper {

    PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);

    PlaceVO toPlaceVO(Place place);

    List<PlaceVO> toPlaceVOList(List<Place> places);

    Place toPlace(PlaceVO placeVO);

    List<Place> toPlaceList(List<PlaceVO> placeVOs);

    PlaceDO toPlaceDOFromPlace(Place place);

}
