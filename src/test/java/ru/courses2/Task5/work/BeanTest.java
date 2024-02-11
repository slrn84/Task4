package ru.courses2.Task5.work;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import ru.courses2.Task5.model.entity.Product;
import ru.courses2.Task5.model.entity.ProductRegister;
import ru.courses2.Task5.model.request.AccountModel;
import ru.courses2.Task5.model.request.ProductModel;
import ru.courses2.Task5.model.response.ResponseProduct;
import ru.courses2.Task5.model.response.ResponseRegister;
import ru.courses2.Task5.service.repository.ServiceRepoProduct;
import ru.courses2.Task5.service.businessprocess.AccountDI;
import ru.courses2.Task5.service.businessprocess.AgreementDI;
import ru.courses2.Task5.service.businessprocess.ProductDI;
import ru.courses2.Task5.service.businessprocess.ResponseProductDI;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ComponentScan("ru.courses2.Task5.*")
public class BeanTest {

    List<ProductDI<ProductModel, Product>> checkAndExecProduct;
    AgreementDI<ProductModel, Product> execAgreement;
    List<AccountDI<AccountModel, ProductRegister, ResponseRegister>> checkAndExecAccount;
    ResponseProductDI<Product, ResponseProduct> prepareResponseProduct;
    ServiceRepoProduct repoProduct;

    @Autowired
    public BeanTest(
            List<ProductDI<ProductModel, Product>> checkAndExecProduct
            , AgreementDI<ProductModel, Product> execAgreement
            , List<AccountDI<AccountModel, ProductRegister, ResponseRegister>> checkAndExecAccount
            , ResponseProductDI<Product, ResponseProduct> prepareResponseProduct
            , ServiceRepoProduct repoProduct
    ) {
        this.checkAndExecProduct = checkAndExecProduct;
        this.execAgreement = execAgreement;
        this.checkAndExecAccount = checkAndExecAccount;
        this.prepareResponseProduct = prepareResponseProduct;
        this.repoProduct = repoProduct;
    }

    //проверим, что бины не пусты
    @Test
    public void contextLoads() {
        System.out.println(checkAndExecProduct.stream().count());
        System.out.println(execAgreement);
        System.out.println(checkAndExecAccount.stream().count());
        System.out.println(prepareResponseProduct);
        assertThat(checkAndExecProduct).isNotNull();
        assertThat(execAgreement).isNotNull();
        assertThat(checkAndExecAccount).isNotNull();
        assertThat(prepareResponseProduct).isNotNull();

    }

    //Проверим, что все бины созданы
    @Test
    public void count(){
        Assertions.assertTrue(checkAndExecProduct.stream().count()>=6 && checkAndExecAccount.stream().count()>=4);
    }

}