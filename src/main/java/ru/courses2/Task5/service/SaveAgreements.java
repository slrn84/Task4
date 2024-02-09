package ru.courses2.Task5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.courses2.Task5.model.AgreementModel;
import ru.courses2.Task5.model.ProductModel;
import ru.courses2.Task5.work.Agreements;
import ru.courses2.Task5.work.Product;
import ru.courses2.Task5.work.RepoAgreements;
import ru.courses2.Task5.work.RepoProduct;

import java.util.function.Function;

@Service
public class SaveAgreements implements Function<ProductModel, Product> {
    private RepoProduct repoProduct;
    private RepoAgreements repoAgreements;
    @Autowired
    public SaveAgreements(RepoProduct repoProduct, RepoAgreements repoAgreements) {
        this.repoProduct = repoProduct;
        this.repoAgreements = repoAgreements;
    }


    @Override
    public Product apply(ProductModel productModel) {
        System.out.println("SaveAgreements");
        Product product = null;
        for (AgreementModel agreementModel : productModel.getArrangements()) {

            Agreements agreement = new Agreements();
            agreement.setNumber(agreementModel.getNumber());

            product = repoProduct.findFirstById(productModel.getInstanceId());

            if (product == null)
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND
                        , ": ID ЭП <" + productModel.getInstanceId() + "> не найдено в БД");
            else product.addAgreement(agreement);
            System.out.println("00" + agreement);
            System.out.println("00" + product);
            repoAgreements.save(agreement);

        }
        return product;
    }

}
