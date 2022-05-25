package ru.kpfu.itis.hauss.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
