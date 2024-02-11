package ru.courses2.Task5.service.repository;

import ru.courses2.Task5.model.entity.ProductRegisterType;

import java.util.List;

public interface ServiceRepoProductRegisterType {
    List<ProductRegisterType> findByClasscode_ValueAndAcctype_Value(String productCode, String type);
}
