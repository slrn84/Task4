package ru.courses2.Task5.utils;

import org.springframework.stereotype.Service;
import ru.courses2.Task5.model.entity.Product;
import ru.courses2.Task5.model.response.ResponseProduct;
import ru.courses2.Task5.service.businessprocess.ResponseProductDI;

@Service
public class PrepareResponseProduct implements ResponseProductDI<Product, ResponseProduct> {
    @Override
    public void accept(Product product, ResponseProduct responseProduct) {
        responseProduct.setInstanceId(Integer.toString(product.getId()));
        System.out.println(product);
        product.getRegisters().stream().forEach(x-> responseProduct.addRegister(x.getId()));
        product.getAgreements().stream().forEach(x-> responseProduct.addAgreement(x.getId()));
    }
}
