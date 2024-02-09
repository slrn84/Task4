package ru.courses2.Task5.work;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tpp_ref_product_register_type")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRegisterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int internal_id;
    private String value;
    @Column(name = "register_type_name")
    private String typename;
    @OneToOne
    @JoinColumn (name="product_class_code")
    private ProductClass classcode;
    @OneToOne
    @JoinColumn (name="account_type")
    private AccountType acctype;
}
