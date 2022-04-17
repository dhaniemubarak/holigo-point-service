package id.holigo.services.holigopointservice.services;

import id.holigo.services.holigopointservice.domain.UserPoint;
import id.holigo.services.holigopointservice.repositories.UserPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserPointServiceImpl implements UserPointService {

    private UserPointRepository userPointRepository;

    private PointHistoryService pointHistoryService;


    @Autowired
    public void setUserPointRepository(UserPointRepository userPointRepository) {
        this.userPointRepository = userPointRepository;
    }

    @Autowired
    public void setPointHistoryService(PointHistoryService pointHistoryService) {
        this.pointHistoryService = pointHistoryService;
    }

    @Transactional
    @Override
    public boolean credit(Long userId, Integer point, UUID transactionId, String indexNote, String noteValue) {
        boolean isCredit = false;
        Optional<UserPoint> fetchUserPoint = userPointRepository.findByUserId(userId);
        if (fetchUserPoint.isPresent()) {
            UserPoint userPoint = fetchUserPoint.get();
            Integer currentPoint = userPoint.getPoint();
            Integer newPoint = currentPoint + point;
            userPoint.setPoint(newPoint);
            UserPoint savedUserPoint = userPointRepository.save(userPoint);
            pointHistoryService.createNewHistory(userId, currentPoint, point, 0, indexNote, noteValue);
            isCredit = true;
        }
        return isCredit;
    }

    @Transactional
    @Override
    public boolean debit(Long userId, Integer point, UUID transactionId, String indexNote, String noteValue) {
        boolean isDebit = false;
        Optional<UserPoint> fetchUserPoint = userPointRepository.findByUserId(userId);
        if (fetchUserPoint.isPresent()) {
            UserPoint userPoint = fetchUserPoint.get();
            if (userPoint.getPoint() >= point) {
                Integer currentPoint = userPoint.getPoint();
                Integer newPoint = currentPoint - point;
                userPoint.setPoint(newPoint);
                UserPoint savedUserPoint = userPointRepository.save(userPoint);
                pointHistoryService.createNewHistory(userId, currentPoint, point, 0, indexNote, noteValue);
                isDebit = true;
            }
        }
        return isDebit;
    }
}
