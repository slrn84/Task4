package ru.courses2.Task5.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.courses2.Task5.model.entity.ProductRegister;

@Repository
public interface RepoProductRegister extends CrudRepository<ProductRegister,Integer> {
    ProductRegister findFirstByProduct_idAndType_value(int product, String type);
}
