package ru.courses2.Task5.work;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoProductRegisterType extends CrudRepository<ProductRegisterType,Integer> {
    List<ProductRegisterType> findByClasscode_ValueAndAcctype_Value(String productClass, String accountType);
}
