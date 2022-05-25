package ru.kpfu.itis.hauss.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity()
@Table(name = "idea")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"account"})
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Column(name = "photo_name")
    private String photoName;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private IdeaCategory category;

    @ManyToOne
    @JoinColumn(name = "style_id", nullable = false)
    private Style style;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
