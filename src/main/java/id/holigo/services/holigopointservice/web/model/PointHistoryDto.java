package id.holigo.services.holigopointservice.web.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointHistoryDto implements Serializable {

    private UUID id;

    private Integer point;

    private Integer debit;

    private Integer credit;

    private String information;

    private String transactionId;

    private String paymentId;

    private Timestamp createdAt;
}
