package com.ne.customers;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

}
