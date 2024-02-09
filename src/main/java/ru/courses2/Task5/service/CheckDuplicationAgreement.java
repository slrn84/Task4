package ru.courses2.Task5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.courses2.Task5.model.AgreementModel;
import ru.courses2.Task5.model.ProductModel;
import ru.courses2.Task5.work.Agreements;
import ru.courses2.Task5.work.Product;
import ru.courses2.Task5.work.RepoAgreements;

import java.util.function.BiConsumer;

@Service
@Order(3)
public class CheckDuplicationAgreement implements BiConsumer<ProductModel, Product> {
    private RepoAgreements agreementsRepo;

    @Autowired
    public CheckDuplicationAgreement(RepoAgreements agreementsRepo) {
        this.agreementsRepo = agreementsRepo;
    }

    @Override
    public void accept(ProductModel productModel, Product product) {
        System.out.println("CheckDuplicationAgreement");
        for (AgreementModel agreementModel : productModel.getArrangements()) {
            Agreements agreement = agreementsRepo.findFirstByNumber(agreementModel.getNumber());
            System.out.println("Проверка на дубли ДС: ищем по номеру " + agreementModel.getNumber()
                    + "; Нашли договор: " + agreement);
            if (agreement != null) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST
                    , ": Параметр № Дополнительного соглашения (сделки) Number <" + agreementModel.getNumber()
                    + "> уже существует для ЭП с ИД  <" + productModel.getInstanceId() + ">");
        }
    }
}
