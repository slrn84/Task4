package ru.courses2.Task5.service.businessprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import ru.courses2.Task5.model.entity.Product;
import ru.courses2.Task5.model.entity.ProductRegister;
import ru.courses2.Task5.model.request.ProductModel;
import ru.courses2.Task5.service.repository.ServiceRepoProductRegister;

@Service
@Order(6)
public class SaveProductRegister implements ProductDI<ProductModel, Product> {
    private ServiceRepoProductRegister repoProductRegister;
    private SetAccountDI<ProductRegister> setAccount;
    @Autowired
     public SaveProductRegister(ServiceRepoProductRegister repoProductRegister, SetAccountDI<ProductRegister> setAccount) {
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
