package id.holigo.services.holigopointservice.web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.holigo.services.common.model.PointDto;
import id.holigo.services.holigopointservice.services.PointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;

@Slf4j
@RestController
public class PointController {
    private PointService pointService;

    @Autowired
    public void setPointService(PointService pointService) {
        this.pointService = pointService;
    }

    @PostMapping("/api/v1/point/debit")
    public ResponseEntity<PointDto> debit(@RequestBody PointDto pointDto) throws JMSException, JsonProcessingException {
        return new ResponseEntity<>(pointService.debit(pointDto), HttpStatus.CREATED);
    }

    @PostMapping("/api/v1/point/credit")
    public ResponseEntity<PointDto> credit(@RequestBody PointDto pointDto) throws JMSException, JsonProcessingException {
        return new ResponseEntity<>(pointService.credit(pointDto), HttpStatus.CREATED);
    }
}
