package id.holigo.services.holigopointservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.holigo.services.holigopointservice.domain.UserPoint;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserPointRepository extends JpaRepository<UserPoint, Long> {

    @Modifying(flushAutomatically = true)
    @Query("UPDATE UserPoint u SET u.point=:newPoint WHERE u.userId=:userId AND u.point=:currentPoint")
    int updatePoint(@Param("userId") Long userId,
                    @Param("currentDeposit") Integer currentPoint,
                    @Param("newPoint") Integer newPoint);
}
