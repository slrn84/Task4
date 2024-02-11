package ru.courses2.Task5.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.courses2.Task5.repository.RepoProduct;
import ru.courses2.Task5.model.entity.Product;

@Service
public class ServiceRepoProductImpl implements ServiceRepoProduct {
    private RepoProduct repoProduct;

    @Autowired
    public ServiceRepoProductImpl(RepoProduct repoProduct) {
        this.repoProduct = repoProduct;
    }

    @Override
    public Product findFirstByNumber(String number) {
        return repoProduct.findFirstByNumber(number);
    }

    @Override
    public Product findFirstById(Integer instanceId) {
        return repoProduct.findFirstById(instanceId);
    }

    @Override
    public Product findFirstByIdAndClassCode(int id, String classCode) {
        return repoProduct.findFirstByIdAndClassCode(id, classCode);
    }

    @Override
    public void save(Product entity) {
        repoProduct.save(entity);
    }

    @Override
    public void findAll() {
        repoProduct.findAll();
    }

    @Override
    public long count() {
        return repoProduct.count();
    }

    @Override
    public void deleteAll() {
        repoProduct.deleteAll();
    }
}
