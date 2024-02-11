package ru.courses2.Task5.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tpp_ref_product_class")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int internal_id;
    private String value;
    private String gbl_code;
    private String gbl_name;
    private String product_row_code;
    private String product_row_name;
    private String subclass_code;
    private String subclass_name;
}
