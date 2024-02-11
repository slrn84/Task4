package ru.courses2.Task5.service.businessprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.courses2.Task5.model.request.ProductModel;
import ru.courses2.Task5.service.repository.ServiceRepoProduct;
import ru.courses2.Task5.model.entity.Product;

@Service
@Order(2)
public class CheckDuplicationProduct implements ProductDI<ProductModel, Product> {
    private ServiceRepoProduct repoProduct;

    @Autowired
    public CheckDuplicationProduct(ServiceRepoProduct productRepo) {
        this.repoProduct = productRepo;
    }

    @Override
    public void accept(ProductModel productModel, Product product) {
        System.out.println("CheckDuplicationProduct");
        Product productBd = repoProduct.findFirstByNumber(productModel.getContractNumber());
        System.out.println("Проверка на дубли ЭП: ищем по номеру "+productModel.getContractNumber()+"; Нашли договор: "+productBd);
        if (productBd != null) throw new HttpClientErrorException( HttpStatus.BAD_REQUEST
                ,": Параметр ContractNumber № договора <"+productModel.getContractNumber()+"> уже существует для ЭП с ИД  <"+productBd.getId()+">");
    }

}
