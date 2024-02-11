package ru.courses2.Task5.model.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountModel {
    @RequiredField
    int instanceId;
    String registryTypeCode;
    String accountType;
    String currencyCode;
    String branchCode;
    String priorityCode;
    String mdmCode;
    String salesCode;
    String clientCode;
    String trainRegion;
    String counter;
}
