package ru.courses2.Task5.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.courses2.Task5.repository.RepoAgreements;
import ru.courses2.Task5.model.entity.Agreements;

@Service
@Primary
public class ServiceRepoAgreementsImpl implements ServiceRepoAgreements {
    private RepoAgreements repoAgreements;

    @Autowired
    public ServiceRepoAgreementsImpl(RepoAgreements repoAgreements) {
        this.repoAgreements = repoAgreements;
    }

    @Override
    public Agreements findFirstByNumber(String number) {
        return repoAgreements.findFirstByNumber(number);
    }

    @Override
    public void save(Agreements entity) {
        repoAgreements.save(entity);
    }

    @Override
    public void findAll() {
        repoAgreements.findAll();
    }

    @Override
    public void deleteAll() {
        repoAgreements.deleteAll();
    }

    @Override
    public long count() {
        return repoAgreements.count();
    }

}
