package ru.courses2.Task5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.courses2.Task5.model.ProductModel;
import ru.courses2.Task5.work.Product;
import ru.courses2.Task5.work.ProductRegister;
import ru.courses2.Task5.work.ProductRegisterType;
import ru.courses2.Task5.work.RepoProductRegisterType;

import java.util.List;
import java.util.function.BiConsumer;

@Service
@Order(4)
public class CheckRegisterType implements BiConsumer<ProductModel, Product> {
    private RepoProductRegisterType repoProductRegisterType;

    @Autowired
    public CheckRegisterType(RepoProductRegisterType repoProductRegisterType) {
        this.repoProductRegisterType = repoProductRegisterType;
    }

    @Override
    public void accept(ProductModel productModel, Product product) {
        System.out.println("CheckRegisterType");
        List<ProductRegisterType> listProductRegisterType = repoProductRegisterType
                .findByClasscode_ValueAndAcctype_Value(productModel.getProductCode(), "Клиентский");
        System.out.println(listProductRegisterType);
        if (listProductRegisterType == null) throw new HttpClientErrorException(HttpStatus.NOT_FOUND
                , ": КодПродукта <" + productModel.getProductCode() + "> не найдено в Каталоге продуктов");
        product.setClassCode(listProductRegisterType.getFirst().getClasscode());
        for (ProductRegisterType registerType : listProductRegisterType) {
            ProductRegister productRegister = new ProductRegister();
            productRegister.setType(registerType);
            product.addRegistr(productRegister);
        }
    }
}
