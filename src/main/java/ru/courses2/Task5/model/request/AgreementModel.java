package ru.courses2.Task5.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgreementModel {
    String generalAgreementId;
    String supplementaryAgreementId;
    String arrangementType;
    Integer schedulerJobId;
    @RequiredField
    String number;
    @RequiredField String openingDate;
    String closingDate;
    String cancelDate;
    String validityDuration;
    String cancellationReason;
    String status;
    String interestCalculationDate;
    int interestRate;
    int coefficient;
    String coefficientAction;
    int minimumInterestRate;
    int minimumInterestRateCoefficient;
    String minimumInterestRateCoefficientAction;
    int maximalInterestRate;
    int maximalInterestRateCoefficient;
    String maximalInterestRateCoefficientAction;
}
