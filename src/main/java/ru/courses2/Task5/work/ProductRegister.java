package ru.courses2.Task5.work;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tpp_product_register")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn (name="product_id")
    private Product product;
    @OneToOne
    @JoinColumn (name="type")
    private ProductRegisterType type;
    private int account_id;
    private String currency_code;
    private String state;
    private String account_number;
}
