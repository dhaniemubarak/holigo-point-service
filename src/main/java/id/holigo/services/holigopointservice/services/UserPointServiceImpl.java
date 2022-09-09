package id.holigo.services.holigopointservice.services;

import id.holigo.services.common.model.PointDto;
import id.holigo.services.holigopointservice.domain.PointStatement;
import id.holigo.services.holigopointservice.domain.UserPoint;
import id.holigo.services.holigopointservice.repositories.UserPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserPointServiceImpl implements UserPointService {

    private UserPointRepository userPointRepository;

    private PointStatementService pointStatementService;


    @Autowired
    public void setUserPointRepository(UserPointRepository userPointRepository) {
        this.userPointRepository = userPointRepository;
    }

    @Autowired
    public void setPointStatementService(PointStatementService pointStatementService) {
        this.pointStatementService = pointStatementService;
    }

    @Override
    public void createUserPoint(Long userId) {
        boolean isExists = userPointRepository.existsById(userId);
        if (!isExists) {
            UserPoint userDeposit = new UserPoint();
            userDeposit.setUserId(userId);
            userDeposit.setPoint(0);
            userPointRepository.save(userDeposit);
        }
        userPointRepository.getById(userId);
    }

    @Transactional
    @Override
    public PointDto credit(PointDto pointDto) {
        pointDto.setIsValid(false);
        if (pointDto.getCreditAmount() == 0) {
            return pointDto;
        }
        if (pointDto.getDebitAmount() > 0) {
            return pointDto;
        }
        boolean isExists = userPointRepository.existsById(pointDto.getUserId());
        if (!isExists) {
            createUserPoint(pointDto.getUserId());
        }
        UserPoint userPoint = userPointRepository.getById(pointDto.getUserId());
        Integer currentPoint = userPoint.getPoint();
        Integer newPoint = currentPoint + pointDto.getCreditAmount();
        updatePoint(pointDto, userPoint, currentPoint, newPoint);
        return pointDto;
    }

    private void updatePoint(PointDto pointDto, UserPoint userPoint, Integer currentPoint, Integer newPoint) {
        int isUpdate = userPointRepository.updatePoint(userPoint.getUserId(), currentPoint, newPoint);
        if (isUpdate == 0) {
            throw new RuntimeException();
        }
        PointStatement pointStatement = pointStatementService.createNewStatement(pointDto, currentPoint);
        if (pointStatement.getId() != null) {
//            pointDto.setPoint(currentPoint);
//            PointDto resultAccountStatement = accountBalanceService.createAccountStatement(depositDto);
//            if (!resultAccountStatement.getIsValid()) {
//                throw new RuntimeException("Invalid createAccountStatement");
//            }
//            depositDto.setIsValid(resultAccountStatement.getIsValid());
//            depositDto.setDeposit(resultAccountStatement.getDeposit());
        }
    }

    @Transactional
    @Override
    public PointDto debit(PointDto pointDto) {
        pointDto.setIsValid(false);
        if (pointDto.getDebitAmount() == 0) {
            return pointDto;
        }
        if (pointDto.getCreditAmount() > 0) {
            return pointDto;
        }
        boolean isExists = userPointRepository.existsById(pointDto.getUserId());
        if (!isExists) {
            createUserPoint(pointDto.getUserId());
        }
        UserPoint userPoint = userPointRepository.getById(pointDto.getUserId());
        if (userPoint.getPoint() < pointDto.getDebitAmount()) {
            pointDto.setMessage("point tidak cukup");
            return pointDto;
        }

        Integer currentPoint = userPoint.getPoint();
        Integer newPoint = currentPoint - pointDto.getDebitAmount();
        updatePoint(pointDto, userPoint, currentPoint, newPoint);
        return pointDto;
    }
}