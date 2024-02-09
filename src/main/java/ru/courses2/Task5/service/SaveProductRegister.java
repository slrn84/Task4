package ru.courses2.Task5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import ru.courses2.Task5.model.AccountModel;
import ru.courses2.Task5.model.ProductModel;
import ru.courses2.Task5.work.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Service
@Order(6)
public class SaveProductRegister implements BiConsumer<ProductModel, Product> {
    private RepoProductRegister repoProductRegister;
    private Consumer<ProductRegister> setAccount;
    @Autowired
     public SaveProductRegister(RepoProductRegister repoProductRegister, Consumer<ProductRegister> setAccount) {
        this.repoProductRegister = repoProductRegister;
        this.setAccount = setAccount;
    }

    @Override
    public void accept(ProductModel productModel, Product product) {
        System.out.println("SaveRegister");
        for(ProductRegister productRegister: product.getRegisters()){
            setAccount.accept(productRegister);
            repoProductRegister.save(productRegister);
        }
    }
}
