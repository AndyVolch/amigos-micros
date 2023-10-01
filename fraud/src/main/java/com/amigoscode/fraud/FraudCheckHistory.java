package com.amigoscode.fraud;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
@Entity
public class FraudCheckHistory {
    @Id
    @SequenceGenerator(
            name = "fraud_id_sequence",
            sequenceName = "fraud_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fraud_id_sequence"
    )
    private Integer id;
    private Integer customerId;
    private Boolean isFraudster;
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FraudCheckHistory that = (FraudCheckHistory) o;
        if (id == null) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
