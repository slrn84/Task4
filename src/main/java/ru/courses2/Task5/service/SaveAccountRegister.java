package ru.courses2.Task5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import ru.courses2.Task5.model.AccountModel;
import ru.courses2.Task5.work.ProductRegister;
import ru.courses2.Task5.work.RepoProductRegister;
import ru.courses2.Task5.work.ResponseRegister;
import ru.courses2.Task5.work.TerConsumer;

import java.util.function.Consumer;

@Service
@Order(4)
public class SaveAccountRegister implements TerConsumer<AccountModel, ProductRegister, ResponseRegister> {
    private RepoProductRegister repoProductRegister;
    private Consumer<ProductRegister> setAccount;

    @Autowired
    public SaveAccountRegister(RepoProductRegister repoProductRegister, Consumer<ProductRegister> setAccount) {
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