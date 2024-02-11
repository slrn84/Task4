package ru.courses2.Task5.service.businessprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import ru.courses2.Task5.model.request.ProductModel;
import ru.courses2.Task5.service.repository.ServiceRepoProduct;
import ru.courses2.Task5.model.entity.Product;

@Service
@Order(5)
public class SaveProduct implements ProductDI<ProductModel, Product> {
    private ServiceRepoProduct repoProduct;

    @Autowired
    public SaveProduct(ServiceRepoProduct repoProduct) {
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
