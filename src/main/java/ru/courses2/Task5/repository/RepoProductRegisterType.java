package ru.courses2.Task5.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.courses2.Task5.model.entity.ProductRegisterType;

import java.util.List;

@Repository
public interface RepoProductRegisterType extends CrudRepository<ProductRegisterType,Integer> {
    List<ProductRegisterType> findByClasscode_ValueAndAcctype_Value(String productClass, String accountType);
}
