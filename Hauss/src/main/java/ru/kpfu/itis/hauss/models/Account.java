package ru.kpfu.itis.hauss.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"favoritesIdea"})
public class Account {

    public enum State {
        CONFIRMED, NOT_CONFIRMED, DELETED, BANNED
    }

    public enum Role {
        USER, ADMIN
    }

//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    private UUID uuid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "hash_password")
    private String hashPassword;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "photo_name")
    private String photoName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @Column(name = "confirm_code")
    private String confirmCode;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "idea_id", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"idea_id", "author_id"})})
    private Set<Idea> favoritesIdea;
}
