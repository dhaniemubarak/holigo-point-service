package id.holigo.services.holigopointservice.services;

import id.holigo.services.holigopointservice.web.model.PointStatementPaginate;
import org.springframework.data.domain.PageRequest;

public interface PointStatementService {

    PointStatementPaginate listPointStatements(Long userId, PageRequest pageRequest);

    void createNewStatement(Long userId, Integer point, Integer credit, Integer debit, String indexNote, String noteValue);
}
