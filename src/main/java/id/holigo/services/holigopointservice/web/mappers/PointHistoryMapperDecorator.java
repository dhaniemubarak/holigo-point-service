package id.holigo.services.holigopointservice.web.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import id.holigo.services.holigopointservice.domain.PointHistory;
import id.holigo.services.holigopointservice.web.model.PointHistoryDto;

public abstract class PointHistoryMapperDecorator implements PointHistoryMapper {

    @Autowired
    private MessageSource messageSource;

    private PointHistoryMapper pointHistoryMapper;

    @Autowired
    public void setPointHistoryMapper(PointHistoryMapper pointHistoryMapper) {
        this.pointHistoryMapper = pointHistoryMapper;
    }

    @Override
    public PointHistoryDto pointHistoryToPointHistoryDto(PointHistory pointHistory) {
        PointHistoryDto pointHistoryDto = pointHistoryMapper.pointHistoryToPointHistoryDto(pointHistory);
        pointHistoryDto.setInformation(
                messageSource.getMessage(pointHistory.getInformationIndex(), null, LocaleContextHolder.getLocale()));
        return pointHistoryDto;
    }

}
