package ru.courses2.Task5.work;

import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class PrepareResponseProduct implements BiConsumer<Product, ResponseProduct> {
    @Override
    public void accept(Product product, ResponseProduct responseProduct) {
        responseProduct.setInstanceId(Integer.toString(product.getId()));
        System.out.println(product);
        product.getRegisters().stream().forEach(x-> responseProduct.addRegister(x.getId()));
        product.getAgreements().stream().forEach(x-> responseProduct.addAgreement(x.getId()));
    }
}
