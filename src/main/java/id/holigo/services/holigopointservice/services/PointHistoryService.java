package id.holigo.services.holigopointservice.services;

import id.holigo.services.holigopointservice.web.model.PointHistoryPaginate;
import org.springframework.data.domain.PageRequest;

public interface PointHistoryService {

    PointHistoryPaginate listPointHistories(Long userId, PageRequest pageRequest);
}
