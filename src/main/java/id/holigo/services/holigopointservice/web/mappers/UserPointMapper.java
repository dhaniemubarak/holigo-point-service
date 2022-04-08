package id.holigo.services.holigopointservice.web.mappers;

import org.mapstruct.Mapper;

import id.holigo.services.holigopointservice.domain.UserPoint;
import id.holigo.services.holigopointservice.web.model.UserPointDto;

@Mapper
public interface UserPointMapper {
    UserPointDto userPointToUserPointDto(UserPoint userPoint);
}
