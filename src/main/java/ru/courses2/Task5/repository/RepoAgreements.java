package ru.courses2.Task5.repository;

import org.springframework.data.repository.CrudRepository;
import ru.courses2.Task5.model.entity.Agreements;

public interface RepoAgreements extends CrudRepository<Agreements,Integer> {
    Agreements findFirstByNumber(String number);
}
