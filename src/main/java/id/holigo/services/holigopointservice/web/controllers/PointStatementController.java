package id.holigo.services.holigopointservice.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.holigo.services.holigopointservice.services.PointStatementService;
import id.holigo.services.holigopointservice.web.model.PointStatementPaginate;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PointStatementController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private PointStatementService pointStatementService;

    @GetMapping("/api/v1/pointHistories")
    public ResponseEntity<PointStatementPaginate> getPointHistories(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestHeader("user-id") Long userId) {

        log.info("USER ID -> {}", userId);
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        PointStatementPaginate pointHistories = pointStatementService.listPointStatements(userId,
                PageRequest.of(pageNumber, pageSize, Sort.by("createdAt").descending()));
        return new ResponseEntity<>(pointHistories, HttpStatus.OK);
    }
}
