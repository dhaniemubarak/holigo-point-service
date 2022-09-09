package id.holigo.services.holigopointservice.services.AccountBalance;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.holigo.services.common.model.PointDto;

import javax.jms.JMSException;

public interface AccountBalanceService {
    PointDto createAccountStatement(PointDto pointDto) throws JMSException, JsonProcessingException;
}
