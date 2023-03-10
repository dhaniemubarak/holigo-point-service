package id.holigo.services.holigopointservice.domain;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PointStatement {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private Long userId;

    private Integer point;

    private Integer debit;

    private Integer credit;

    private String informationIndex;

    private String informationValue;

    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID transactionId;

    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID paymentId;

    private String transactionType;

    private String invoiceNumber;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
