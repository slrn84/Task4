package ru.courses2.Task5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import ru.courses2.Task5.model.ProductModel;
import ru.courses2.Task5.work.Product;
import ru.courses2.Task5.work.RepoProduct;

import java.util.function.BiConsumer;

@Service
@Order(5)
public class SaveProduct implements BiConsumer<ProductModel, Product> {
    private RepoProduct repoProduct;

    @Autowired
    public SaveProduct(RepoProduct repoProduct) {
        this.repoProduct = repoProduct;
    }

    @Override
    public void accept(ProductModel productModel, Product product) {
        System.out.println("SaveProduct");
        product.setType(productModel.getProductType());
        product.setNumber(productModel.getContractNumber());
        product.setDate_of_conclusion(productModel.getContractDate());
        product.setPenalty_rate(productModel.getInterestRatePenalty());
        product.setNso(productModel.getMinimalBalance());
        product.setThreshold_amount(productModel.getThresholdAmount());
        product.setInterest_rate_type(productModel.getRateType());
        product.setTax_rate(productModel.getTaxPercentageRate());

        repoProduct.save(product);
    }
}
