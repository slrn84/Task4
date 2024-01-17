package ru.courses2.Task4.repo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "logins")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
public class Logins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "access_date")
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Users user;
    private String application;
    @Transient
    private String filename;

}
