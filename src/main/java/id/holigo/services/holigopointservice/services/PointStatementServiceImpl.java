package id.holigo.services.holigopointservice.services;

import java.util.stream.Collectors;

import id.holigo.services.common.model.PointDto;
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

    private PointStatementRepository pointStatementRepository;

    @Autowired
    public void setPointStatementRepository(PointStatementRepository pointStatementRepository) {
        this.pointStatementRepository = pointStatementRepository;
    }

    @Override
    public PointStatement createNewStatement(PointDto pointDto, Integer currentPoint) {
        PointStatement pointStatement = new PointStatement();
        pointStatement.setPoint(currentPoint);
        pointStatement.setCredit(pointDto.getCreditAmount());
        pointStatement.setDebit(pointDto.getDebitAmount());
        pointStatement.setUserId(pointDto.getUserId());
        pointStatement.setTransactionId(pointDto.getTransactionId());
        pointStatement.setPaymentId(pointDto.getPaymentId());
        pointStatement.setInformationIndex(pointDto.getInformationIndex());
        pointStatement.setInformationValue(pointDto.getInformationValue());
        pointStatement.setTransactionType(pointDto.getTransactionType());
        pointStatement.setInvoiceNumber(pointDto.getInvoiceNumber());
        return pointStatementRepository.save(pointStatement);
    }

}
