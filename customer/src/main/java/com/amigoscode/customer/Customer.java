package com.amigoscode.customer;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Getter @Setter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Integer id;
    private String firstName;
    private String lastName;
    @Nullable
    private String email;

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        Customer customer = (Customer) obj;
        if (id == null) {
            return false;
        }
        return id.equals(customer.id);
    }
}
