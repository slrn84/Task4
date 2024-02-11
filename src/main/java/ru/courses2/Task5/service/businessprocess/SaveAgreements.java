package ru.courses2.Task5.service.businessprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.courses2.Task5.model.entity.Agreements;
import ru.courses2.Task5.model.entity.Product;
import ru.courses2.Task5.model.request.AgreementModel;
import ru.courses2.Task5.model.request.ProductModel;
import ru.courses2.Task5.service.repository.ServiceRepoProduct;
import ru.courses2.Task5.service.repository.ServiceRepoAgreements;

@Service
public class SaveAgreements implements AgreementDI<ProductModel, Product> {
    private ServiceRepoProduct repoProduct;
    private ServiceRepoAgreements repoAgreements;
    @Autowired
    public SaveAgreements(ServiceRepoProduct repoProduct, ServiceRepoAgreements repoAgreements) {
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
