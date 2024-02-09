package ru.courses2.Task5.work;

import org.springframework.data.repository.CrudRepository;

public interface RepoProductRegister extends CrudRepository<ProductRegister,Integer> {
    ProductRegister findFirstByProduct_idAndType_value(int product, String type);
}
