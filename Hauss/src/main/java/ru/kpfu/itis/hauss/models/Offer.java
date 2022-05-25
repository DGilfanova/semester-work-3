package ru.kpfu.itis.hauss.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "offer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(name = "execution_time", nullable = false)
    private Integer executionTime;

    @Column(name = "photo_name")
    private String photoName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private OfferCategory category;

    @OneToMany(mappedBy = "offer")
    private List<Order> orders;
}
