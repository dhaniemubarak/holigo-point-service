package id.holigo.services.holigopointservice.web.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import id.holigo.services.holigopointservice.domain.UserPoint;
import id.holigo.services.holigopointservice.repositories.UserPointRepository;
import id.holigo.services.holigopointservice.web.mappers.UserPointMapper;
import id.holigo.services.holigopointservice.web.model.UserPointDto;

@RestController
public class UserPointController {

    @Autowired
    private UserPointRepository userPointRepository;

    @Autowired
    private UserPointMapper userPointMapper;

    @GetMapping("/api/v1/userPoint")
    public ResponseEntity<UserPointDto> getUserPoint(@RequestHeader("User-Id") Long userId) {

        Optional<UserPoint> fetchUserPoint = userPointRepository.findByUserId(userId);
        if (fetchUserPoint.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userPointMapper.userPointToUserPointDto(fetchUserPoint.get()), HttpStatus.OK);
    }
}
