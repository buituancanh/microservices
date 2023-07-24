package vn.elca.tech.microservices.order.impl.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_order")
@Getter @Setter
public class OrderEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "user_number")
    private String userNumber;

    @Column(name = "timestamp", columnDefinition = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "email")
    private String email;

    @Column(name = "amount")
    private long amount;
}
