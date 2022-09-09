package id.holigo.services.holigopointservice.services;

import java.util.stream.Collectors;

import id.holigo.services.holigopointservice.domain.PointStatement;
import id.holigo.services.holigopointservice.web.mappers.PointStatementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import id.holigo.services.holigopointservice.repositories.PointStatementRepository;
import id.holigo.services.holigopointservice.web.model.PointStatementPaginate;

@Service
public class PointHistoryServiceImpl implements PointHistoryService {

    @Autowired
    private PointStatementRepository pointStatementRepository;

    @Autowired
    private PointStatementMapper pointStatementMapper;

    @Override
    public PointStatementPaginate listPointHistories(Long userId, PageRequest pageRequest) {

        PointStatementPaginate pointStatementPaginate;
        Page<PointStatement> pointHistoryPage;

        pointHistoryPage = pointStatementRepository.findAllByUserId(userId, pageRequest);

        pointStatementPaginate = new PointStatementPaginate(
                pointHistoryPage.getContent().stream().map(pointStatementMapper::pointStatementToPointStatementDto)
                        .collect(Collectors.toList()),
                PageRequest.of(pointHistoryPage.getPageable().getPageNumber(),
                        pointHistoryPage.getPageable().getPageSize()),
                pointHistoryPage.getTotalElements());

        return pointStatementPaginate;
    }

    @Override
    public void createNewHistory(Long userId, Integer point, Integer credit, Integer debit, String indexNote, String noteValue) {
        PointStatement pointStatement = new PointStatement();
        pointStatement.setPoint(point);
        pointStatement.setCredit(credit);
        pointStatement.setDebit(debit);
        pointStatement.setUserId(userId);
        pointStatementRepository.save(pointStatement);
    }

}
