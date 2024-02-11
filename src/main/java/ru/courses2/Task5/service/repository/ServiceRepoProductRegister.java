package ru.courses2.Task5.service.repository;

import ru.courses2.Task5.model.entity.ProductRegister;

public interface ServiceRepoProductRegister {
    ProductRegister findFirstByProduct_idAndType_value(int instanceId, String registryTypeCode);

    void save(ProductRegister productRegister);

    void deleteAll();

    void findAll();

    long count();
}
