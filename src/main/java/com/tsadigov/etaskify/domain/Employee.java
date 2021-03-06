package com.tsadigov.etaskify.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String name;

    private String surname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private AppUser userFk;

}
