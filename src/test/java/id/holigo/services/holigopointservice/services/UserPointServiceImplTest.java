package id.holigo.services.holigopointservice.services;

import id.holigo.services.holigopointservice.domain.UserPoint;
import id.holigo.services.holigopointservice.repositories.UserPointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserPointServiceImplTest {

    @Autowired
    UserPointService userPointService;

    @Autowired
    UserPointRepository userPointRepository;

    UserPoint userPoint;

    @BeforeEach
    void setUp() {
        userPoint = new UserPoint();
        userPoint.setPoint(300);
        userPoint.setUserId(5L);
    }

    @Test
    void credit() {
        UserPoint savedUserPoint = userPointRepository.save(userPoint);
        Integer credit = 200;
        boolean isCredit = userPointService.credit(5L, credit, UUID.randomUUID(), null, null);

        assertTrue(isCredit);

        Optional<UserPoint> fetchUserPoint = userPointRepository.findByUserId(5L);
        if (fetchUserPoint.isPresent()) {
            UserPoint getUserPoint = fetchUserPoint.get();
            assertEquals(500, getUserPoint.getPoint());
        }


    }

    @Test
    void debit() {
        Integer debit = 200;
        boolean isDebit = userPointService.debit(5L, debit, UUID.randomUUID(), null, null);
        assertTrue(isDebit);

        Optional<UserPoint> fetchUserPoint = userPointRepository.findByUserId(5L);
        if (fetchUserPoint.isPresent()) {
            UserPoint getUserPoint = fetchUserPoint.get();
            assertEquals(300, getUserPoint.getPoint());
        }
    }
}