package id.holigo.services.holigopointservice.services.AccountBalance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.holigo.services.common.model.PointDto;
import id.holigo.services.holigopointservice.config.JmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;

@Slf4j
@Service
public class AccountBalanceServiceImpl implements AccountBalanceService {
    private JmsTemplate jmsTemplate;

    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public PointDto createAccountStatement(PointDto pointDto) throws JMSException, JsonProcessingException {
        Message received = jmsTemplate.sendAndReceive(JmsConfig.CREATE_POINT_ACCOUNT_STATEMENT, session -> {
            Message message;
            try {
                message = session.createTextMessage(objectMapper.writeValueAsString(pointDto));
                message.setStringProperty("_type", "id.holigo.services.common.model.PointDto");
            } catch (JsonProcessingException e) {
                throw new JMSException(e.getMessage());
            }
            return message;
        });
        if (received == null) {
            return pointDto;
        }
        return objectMapper.readValue(received.getBody(String.class),
                PointDto.class);
    }
}
