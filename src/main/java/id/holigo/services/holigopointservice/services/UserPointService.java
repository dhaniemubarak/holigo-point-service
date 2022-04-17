package id.holigo.services.holigopointservice.services;

import java.util.UUID;

public interface UserPointService {

    boolean credit(Long userId, Integer point, UUID transactionId, String indexNote, String noteValue);

    boolean debit(Long userId, Integer point, UUID transactionId, String indexNote, String noteValue);
}
