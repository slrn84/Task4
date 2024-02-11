package ru.courses2.Task5.repository;

import org.springframework.data.repository.CrudRepository;
import ru.courses2.Task5.model.entity.AccountType;

public interface RepoAccountType extends CrudRepository<AccountType,Integer> {
}
