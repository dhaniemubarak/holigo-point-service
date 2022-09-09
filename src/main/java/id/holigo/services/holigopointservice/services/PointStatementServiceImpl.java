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
public class PointStatementServiceImpl implements PointStatementService {

    @Autowired
    private PointStatementRepository pointStatementRepository;

    @Autowired
    private PointStatementMapper pointStatementMapper;

    @Override
    public PointStatementPaginate listPointStatements(Long userId, PageRequest pageRequest) {

        PointStatementPaginate pointStatementPaginate;
        Page<PointStatement> pointStatementPage;

        pointStatementPage = pointStatementRepository.findAllByUserId(userId, pageRequest);

        pointStatementPaginate = new PointStatementPaginate(
                pointStatementPage.getContent().stream().map(pointStatementMapper::pointStatementToPointStatementDto)
                        .collect(Collectors.toList()),
                PageRequest.of(pointStatementPage.getPageable().getPageNumber(),
                        pointStatementPage.getPageable().getPageSize()),
                pointStatementPage.getTotalElements());

        return pointStatementPaginate;
    }

    @Override
    public void createNewStatement(Long userId, Integer point, Integer credit, Integer debit, String indexNote, String noteValue) {
        PointStatement pointStatement = new PointStatement();
        pointStatement.setPoint(point);
        pointStatement.setCredit(credit);
        pointStatement.setDebit(debit);
        pointStatement.setUserId(userId);
        pointStatementRepository.save(pointStatement);
    }

}
