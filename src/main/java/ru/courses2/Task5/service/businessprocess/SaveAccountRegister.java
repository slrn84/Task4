package ru.courses2.Task5.service.businessprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import ru.courses2.Task5.model.entity.ProductRegister;
import ru.courses2.Task5.model.request.AccountModel;
import ru.courses2.Task5.model.response.ResponseRegister;
import ru.courses2.Task5.service.repository.ServiceRepoProductRegister;

@Service
@Order(4)
public class SaveAccountRegister implements AccountDI<AccountModel, ProductRegister, ResponseRegister> {
    private ServiceRepoProductRegister repoProductRegister;
    private SetAccountDI<ProductRegister> setAccount;

    @Autowired
    public SaveAccountRegister(ServiceRepoProductRegister repoProductRegister
            , SetAccountDI<ProductRegister> setAccount) {
        this.repoProductRegister = repoProductRegister;
        this.setAccount = setAccount;
    }

    @Override
    public void accept(AccountModel accountModel, ProductRegister productRegister, ResponseRegister responseRegister) {
        System.out.println("SaveRegister");
        setAccount.accept(productRegister);
        repoProductRegister.save(productRegister);
        responseRegister.setAccountId(Integer.toString(productRegister.getId()));
    }
}