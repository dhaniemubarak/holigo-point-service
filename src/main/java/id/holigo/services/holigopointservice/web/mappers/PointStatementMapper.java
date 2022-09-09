package id.holigo.services.holigopointservice.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import id.holigo.services.holigopointservice.domain.PointStatement;
import id.holigo.services.holigopointservice.web.model.PointStatementDto;

@DecoratedWith(PointStatementMapperDecorator.class)
@Mapper
public interface PointStatementMapper {
    PointStatementDto pointStatementToPointStatementDto(PointStatement pointStatement);
}
