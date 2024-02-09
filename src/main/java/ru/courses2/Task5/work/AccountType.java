package ru.courses2.Task5.work;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tpp_ref_account_type")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int internal_id;
    private String value;
}
