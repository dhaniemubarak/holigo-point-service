package id.holigo.services.holigopointservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {

    public static final String DEBIT_POINT = "debit-point";
    public static final String CREDIT_POINT = "credit-point";
    public static final String CREATE_ACCOUNT_BALANCE = "create-account-balance";
    public static final String CREATE_USER_POINT = "create-user-point";
    public static final String CREATE_POINT_ACCOUNT_STATEMENT = "create-point-account-statement";

    @Bean
    public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
