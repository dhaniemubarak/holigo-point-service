package id.holigo.services.holigopointservice.web.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import id.holigo.services.holigopointservice.domain.PointStatement;
import id.holigo.services.holigopointservice.web.model.PointStatementDto;

public abstract class PointStatementMapperDecorator implements PointStatementMapper {

    @Autowired
    private MessageSource messageSource;

    private PointStatementMapper pointStatementMapper;

    @Autowired
    public void setPointStatementMapper(PointStatementMapper pointStatementMapper) {
        this.pointStatementMapper = pointStatementMapper;
    }

    @Override
    public PointStatementDto pointStatementToPointStatementDto(PointStatement pointStatement) {
        PointStatementDto pointStatementDto = pointStatementMapper.pointStatementToPointStatementDto(pointStatement);
        pointStatementDto.setInformation(
                messageSource.getMessage(pointStatement.getInformationIndex(), null, LocaleContextHolder.getLocale()));
        return pointStatementDto;
    }

}
