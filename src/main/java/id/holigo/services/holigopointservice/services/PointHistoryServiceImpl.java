package id.holigo.services.holigopointservice.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import id.holigo.services.holigopointservice.domain.PointHistory;
import id.holigo.services.holigopointservice.repositories.PointHistoryRepository;
import id.holigo.services.holigopointservice.web.mappers.PointHistoryMapper;
import id.holigo.services.holigopointservice.web.model.PointHistoryPaginate;

@Service
public class PointHistoryServiceImpl implements PointHistoryService {

    @Autowired
    private PointHistoryRepository pointHistoryRepository;

    @Autowired
    private PointHistoryMapper pointHistoryMapper;

    @Override
    public PointHistoryPaginate listPointHistories(Long userId, PageRequest pageRequest) {

        PointHistoryPaginate pointHistoryPaginate;
        Page<PointHistory> pointHistoryPage;

        pointHistoryPage = pointHistoryRepository.findAllByUserId(userId, pageRequest);

        pointHistoryPaginate = new PointHistoryPaginate(
                pointHistoryPage.getContent().stream().map(pointHistoryMapper::pointHistoryToPointHistoryDto)
                        .collect(Collectors.toList()),
                PageRequest.of(pointHistoryPage.getPageable().getPageNumber(),
                        pointHistoryPage.getPageable().getPageSize()),
                pointHistoryPage.getTotalElements());

        return pointHistoryPaginate;
    }

    @Override
    public void createNewHistory(Long userId, Integer point, Integer credit, Integer debit, String indexNote, String noteValue) {
        PointHistory pointHistory = new PointHistory();
        pointHistory.setPoint(point);
        pointHistory.setCredit(credit);
        pointHistory.setDebit(debit);
        pointHistory.setUserId(userId);
        pointHistoryRepository.save(pointHistory);
    }

}
