package com.fc.sport.convert;

import com.fc.sport.domain.CourtDO;
import com.fc.sport.entity.Court;
import com.fc.sport.vo.CourtVO;
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
public interface CourtMapper {

    CourtMapper INSTANCE = Mappers.getMapper(CourtMapper.class);

    CourtVO toCourtVO(Court court);

    List<CourtVO> toCourtVOList(List<Court> courts);

    Court toCourt(CourtVO courtVO);

    List<Court> toCourtList(List<CourtVO> courtVOs);

    CourtVO toCourtVO(CourtDO courtDO);

    CourtDO toCourtDOFromCourt(Court court);

    List<CourtDO> toCourtDOListFromCourtList(List<Court> courtList);

}
