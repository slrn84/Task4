package ru.courses2.Task5.work;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import ru.courses2.Task5.model.AccountModel;
import ru.courses2.Task5.model.ProductModel;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ComponentScan("ru.courses2.Task5.*")
public class BeanTest {
    List<BiConsumer<ProductModel, Product>> checkAndExecProduct;
    Function<ProductModel, Product> execAgreement;
    List<TerConsumer<AccountModel, ProductRegister, ResponseRegister>> checkAndExecAccount;
    BiConsumer<Product, ResponseProduct> prepareResponseProduct;
    RepoProduct repoProduct;

    @Autowired
    public BeanTest(
            List<BiConsumer<ProductModel, Product>> checkAndExecProduct
            , Function<ProductModel, Product> execAgreement
            , List<TerConsumer<AccountModel, ProductRegister, ResponseRegister>> checkAndExecAccount
            , BiConsumer<Product, ResponseProduct> prepareResponseProduct
            , RepoProduct repoProduct
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