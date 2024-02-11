package ru.courses2.Task5.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "agreements")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Agreements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn (name="product_id")
    private Product product;
    private String number;
}
