package id.holigo.services.holigopointservice.listeners;

import id.holigo.services.common.model.PointDto;
import id.holigo.services.holigopointservice.config.JmsConfig;
import id.holigo.services.holigopointservice.services.UserPointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Slf4j
@Component
public class PointListener {

    private JmsTemplate jmsTemplate;

    private UserPointService userPointService;

    @Autowired
    public void setUserPointService(UserPointService userPointService) {
        this.userPointService = userPointService;
    }

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = JmsConfig.CREDIT_POINT)
    public void listenForCredit(@Payload PointDto pointDto, @Headers MessageHeaders headers, Message message) throws JMSException {
        try {
            PointDto credit = userPointService.credit(pointDto);
            jmsTemplate.convertAndSend(message.getJMSReplyTo(), credit);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), pointDto);
    }

    @JmsListener(destination = JmsConfig.DEBIT_POINT)
    public void listenForDebit(@Payload PointDto pointDto, @Headers MessageHeaders headers, Message message) throws JMSException {
        try {
            PointDto debit = userPointService.debit(pointDto);
            jmsTemplate.convertAndSend(message.getJMSReplyTo(), debit);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), pointDto);
    }
}
