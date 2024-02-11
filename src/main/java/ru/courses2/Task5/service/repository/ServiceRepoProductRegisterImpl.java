package ru.courses2.Task5.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.courses2.Task5.repository.RepoProductRegister;
import ru.courses2.Task5.model.entity.ProductRegister;

@Service
public class ServiceRepoProductRegisterImpl implements ServiceRepoProductRegister {
    private RepoProductRegister repoProductRegister;

    @Autowired
    public ServiceRepoProductRegisterImpl(RepoProductRegister repoProductRegister) {
        this.repoProductRegister = repoProductRegister;
    }


    @Override
    public ProductRegister findFirstByProduct_idAndType_value(int product, String type) {
        return repoProductRegister.findFirstByProduct_idAndType_value(product,type);
    }

    @Override
    public void save(ProductRegister entity) {
        repoProductRegister.save(entity);
    }

    @Override
    public void findAll() {
        repoProductRegister.findAll();
    }

    @Override
    public long count() {
        return repoProductRegister.count();
    }

    @Override
    public void deleteAll() {
        repoProductRegister.deleteAll();
    }
}
