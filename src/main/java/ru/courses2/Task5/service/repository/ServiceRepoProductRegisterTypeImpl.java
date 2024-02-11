package ru.courses2.Task5.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.courses2.Task5.repository.RepoProductRegisterType;
import ru.courses2.Task5.model.entity.ProductRegisterType;

import java.util.List;

@Service
@Primary
public class ServiceRepoProductRegisterTypeImpl implements ServiceRepoProductRegisterType {
    private RepoProductRegisterType repoProductRegisterType;

    @Autowired
    public ServiceRepoProductRegisterTypeImpl(RepoProductRegisterType repoProductRegisterType) {
        this.repoProductRegisterType = repoProductRegisterType;
    }


    @Override
    public List<ProductRegisterType> findByClasscode_ValueAndAcctype_Value(String productClass, String accountType) {
        return repoProductRegisterType.findByClasscode_ValueAndAcctype_Value(productClass,accountType);
    }

}
