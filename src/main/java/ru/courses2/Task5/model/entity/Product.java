package ru.courses2.Task5.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tpp_product")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name="product_code_id")
    private ProductClass classCode;
    private int client_id;
    private String type;
    private String number;
    private int priority;
    private Date date_of_conclusion;
    private LocalDateTime start_date_time;
    private LocalDateTime end_date_time;
    private int days;
    private float penalty_rate;
    private float nso;
    private float threshold_amount;
    private String requisite_type;
    private String interest_rate_type;
    private float tax_rate;
    private String reason_close;
    private String state;
    @Transient
    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductRegister> registers = new ArrayList<>();
    @Transient
    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agreements> agreements = new ArrayList<>();

    public void addAgreement(Agreements agreement){
        this.agreements.add(agreement);
        agreement.setProduct(this);
    }
    public void addRegistr(ProductRegister register){
        this.registers.add(register);
        register.setProduct(this);
    }

}
