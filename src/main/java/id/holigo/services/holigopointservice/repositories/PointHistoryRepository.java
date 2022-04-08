package id.holigo.services.holigopointservice.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import id.holigo.services.holigopointservice.domain.PointHistory;

public interface PointHistoryRepository extends JpaRepository<PointHistory, UUID> {
    Page<PointHistory> findAllByUserId(Long userId, Pageable pageable);
}
