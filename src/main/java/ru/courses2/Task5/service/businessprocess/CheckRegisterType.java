package ru.courses2.Task5.service.businessprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.courses2.Task5.model.entity.Product;
import ru.courses2.Task5.model.entity.ProductRegister;
import ru.courses2.Task5.model.entity.ProductRegisterType;
import ru.courses2.Task5.model.request.ProductModel;
import ru.courses2.Task5.service.repository.ServiceRepoProductRegisterType;

import java.util.List;

@Service
@Order(4)
public class CheckRegisterType implements ProductDI<ProductModel, Product> {
    private ServiceRepoProductRegisterType repoProductRegisterType;

    @Autowired
    public CheckRegisterType(ServiceRepoProductRegisterType repoProductRegisterType) {
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
