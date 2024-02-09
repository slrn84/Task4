package ru.courses2.Task5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.courses2.Task5.model.ProductModel;
import ru.courses2.Task5.work.Product;
import ru.courses2.Task5.work.RepoProduct;

import java.util.function.BiConsumer;

@Service
@Order(2)
public class CheckDuplicationProduct implements BiConsumer<ProductModel, Product> {
    private RepoProduct productRepo;

    @Autowired
    public CheckDuplicationProduct(RepoProduct productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public void accept(ProductModel productModel, Product product) {
        System.out.println("CheckDuplicationProduct");
        Product productBd = productRepo.findFirstByNumber(productModel.getContractNumber());
        System.out.println("Проверка на дубли ЭП: ищем по номеру "+productModel.getContractNumber()+"; Нашли договор: "+productBd);
        if (productBd != null) throw new HttpClientErrorException( HttpStatus.BAD_REQUEST
                ,": Параметр ContractNumber № договора <"+productModel.getContractNumber()+"> уже существует для ЭП с ИД  <"+productBd.getId()+">");
    }

}
