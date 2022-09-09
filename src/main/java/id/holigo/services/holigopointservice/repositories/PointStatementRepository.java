package id.holigo.services.holigopointservice.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import id.holigo.services.holigopointservice.domain.PointStatement;

public interface PointStatementRepository extends JpaRepository<PointStatement, UUID> {
    Page<PointStatement> findAllByUserId(Long userId, Pageable pageable);
}
