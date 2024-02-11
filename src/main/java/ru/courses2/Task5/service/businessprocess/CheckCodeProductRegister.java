package ru.courses2.Task5.service.businessprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.courses2.Task5.model.request.AccountModel;
import ru.courses2.Task5.model.response.ResponseRegister;
import ru.courses2.Task5.service.repository.ServiceRepoProduct;
import ru.courses2.Task5.model.entity.Product;
import ru.courses2.Task5.model.entity.ProductRegister;

@Service
@Order(3)
public class CheckCodeProductRegister implements AccountDI<AccountModel, ProductRegister, ResponseRegister> {
    ServiceRepoProduct repoProduct;

    @Autowired
    public CheckCodeProductRegister(ServiceRepoProduct repoProduct) {
        this.repoProduct = repoProduct;
    }

    @Override
    public void accept(AccountModel accountModel, ProductRegister productRegister, ResponseRegister responseRegister) {
        System.out.println("CheckCodeProductRegister");
        Product productBd = repoProduct.findFirstByIdAndClassCode(accountModel.getInstanceId(), accountModel.getRegistryTypeCode());
        if (productBd == null) throw new HttpClientErrorException(HttpStatus.NOT_FOUND
                , ": КодПродукта <" + accountModel.getRegistryTypeCode() + "> не найдено в Каталоге продуктов <tpp_ref_product_register_type> для данного типа Регистра");
        else productRegister.setProduct(productBd);
    }
}
