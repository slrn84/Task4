package ru.courses2.Task5.service.repository;

import ru.courses2.Task5.model.entity.Product;

public interface ServiceRepoProduct {
    Product findFirstByIdAndClassCode(int instanceId, String registryTypeCode);

    Product findFirstById(Integer instanceId);

    Product findFirstByNumber(String contractNumber);

    void save(Product product);

    void deleteAll();

    void findAll();

    long count();
}
