package ru.courses2.Task5.work;

import org.springframework.data.repository.CrudRepository;
import ru.courses2.Task5.work.Agreements;

public interface RepoAgreements extends CrudRepository<Agreements,Integer> {
    Agreements findFirstByNumber(String number);
}
