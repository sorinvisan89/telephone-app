package com.solidsoft.telephone.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "contacts")
@Entity
@Data
@NoArgsConstructor
public class Contact {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "email", nullable = false)
    private String email;
}
