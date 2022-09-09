package id.holigo.services.holigopointservice.services;

import id.holigo.services.common.model.PointDto;

import java.util.UUID;

public interface UserPointService {

    void createUserPoint(Long userId);

    PointDto credit(PointDto depositDto) throws Exception;

    PointDto debit(PointDto depositDto) throws Exception;
}
