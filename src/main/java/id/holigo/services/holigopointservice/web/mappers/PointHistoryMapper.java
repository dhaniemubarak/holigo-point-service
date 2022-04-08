package id.holigo.services.holigopointservice.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import id.holigo.services.holigopointservice.domain.PointHistory;
import id.holigo.services.holigopointservice.web.model.PointHistoryDto;

@DecoratedWith(PointHistoryMapperDecorator.class)
@Mapper
public interface PointHistoryMapper {
    PointHistoryDto pointHistoryToPointHistoryDto(PointHistory pointHistory);
}
