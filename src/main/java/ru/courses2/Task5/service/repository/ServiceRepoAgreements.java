package ru.courses2.Task5.service.repository;

import ru.courses2.Task5.model.entity.Agreements;

public interface ServiceRepoAgreements {
    Agreements findFirstByNumber(String number);

    void save(Agreements agreement);

    void findAll();

    void deleteAll();

    long count();
}
