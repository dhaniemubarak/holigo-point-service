package id.holigo.services.holigopointservice.services;

import id.holigo.services.common.model.PointDto;
import id.holigo.services.holigopointservice.domain.PointStatement;
import id.holigo.services.holigopointservice.web.model.PointStatementPaginate;
import org.springframework.data.domain.PageRequest;

public interface PointStatementService {

    PointStatement createNewStatement(PointDto pointDto, Integer currentPoint);
}
