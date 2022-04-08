package id.holigo.services.holigopointservice.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import id.holigo.services.holigopointservice.domain.UserPoint;

public interface UserPointRepository extends JpaRepository<UserPoint, UUID> {

    Optional<UserPoint> findByUserId(Long userId);
}
