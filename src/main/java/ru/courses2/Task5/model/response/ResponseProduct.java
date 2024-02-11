package ru.courses2.Task5.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProduct {
    private String instanceId;
    private List<Integer> registerId = new ArrayList<>();
    private List<Integer> supplementaryAgreementId = new ArrayList<>();

    public void addRegister(Integer i){
        registerId.add(i);
    }
    public void addAgreement(Integer i){
        supplementaryAgreementId.add(i);
    }
}