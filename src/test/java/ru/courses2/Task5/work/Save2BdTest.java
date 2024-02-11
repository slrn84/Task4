package ru.courses2.Task5.work;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.courses2.Task5.model.entity.Agreements;
import ru.courses2.Task5.model.entity.Product;
import ru.courses2.Task5.model.entity.ProductRegister;
import ru.courses2.Task5.model.request.AgreementModel;
import ru.courses2.Task5.model.request.ProductModel;
import ru.courses2.Task5.service.businessprocess.SetAccountDI;
import ru.courses2.Task5.service.repository.ServiceRepoProduct;
import ru.courses2.Task5.service.businessprocess.SaveAgreements;
import ru.courses2.Task5.service.businessprocess.SaveProduct;
import ru.courses2.Task5.service.businessprocess.SaveProductRegister;
import ru.courses2.Task5.service.repository.ServiceRepoAgreements;
import ru.courses2.Task5.service.repository.ServiceRepoProductRegister;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class Save2BdTest {
    ServiceRepoProduct repoProduct;
    ServiceRepoProductRegister repoProductRegister;
    ServiceRepoAgreements repoAgreements;
    SetAccountDI<ProductRegister> setAccount;
    ProductModel productModel;
    Product product;
    @Autowired
    public Save2BdTest(ServiceRepoProduct repoProduct, ServiceRepoProductRegister repoProductRegister
            , ServiceRepoAgreements repoAgreements, SetAccountDI<ProductRegister> setAccount) throws ParseException {
        this.repoProduct = repoProduct;
        this.repoProductRegister = repoProductRegister;
        this.repoAgreements = repoAgreements;
        this.setAccount = setAccount;

        productModel = new ProductModel(
                2, "ДОГОВОР", "03.012.002", "03.012.002_47533_ComSoLd",
                "15", "2024-01-10-000001", new SimpleDateFormat("yyyy-mm-dd").parse("2024-01-10"), 2, 12.25F,
                0F, 0F, "qwerty", "0", 13F, 1000F,
                112233, "0022", "800", "00", 1234, new ArrayList<>(),
                new ArrayList<>(List.of(new AgreementModel())));

        product = new Product(1,null,1,"","123",1,null,null,null,1,11,1,2,"1","1",1,"1","2"
                ,new ArrayList<>(List.of(new ProductRegister())),new ArrayList<>(List.of(new Agreements())));
    }


    //Проверим запись в БД
    @Test
    @DisplayName("Запись в БД ЭП")
    public void SaveProduct_Test() {
        //Почистим таблицу
        repoProduct.deleteAll();
        //Запишем данные в таблицу
        new SaveProduct(repoProduct).accept(productModel, new Product());
        //Поищем новые записи
        repoProduct.findAll();
        //Проверим, что наши записи в БД есть
        Assertions.assertTrue(repoProduct.count() > 0);
    }

    @Test
    @DisplayName("Запись в БД ПР")
    public void SaveProductRegister_Test() {
        //Почистим таблицу
        repoProductRegister.deleteAll();
        //Запишем данные в таблицу
        new SaveProductRegister(repoProductRegister,setAccount).accept(productModel, product);
        //Поищем новые записи
        repoProductRegister.findAll();
        //Проверим, что наши записи в БД есть
        Assertions.assertTrue(repoProductRegister.count() > 0);
    }

    @Test
    @DisplayName("Запись в БД ДС")
    public void SaveAgreements_Test() {
        //Подготовим связанный ЭП в БД
        new SaveProduct(repoProduct).accept(productModel, product);
        //Замокируем обращение к БД
        ServiceRepoProduct mockRepo = Mockito.mock(ServiceRepoProduct.class);
        Mockito.when(mockRepo.findFirstById(Mockito.any())).thenReturn(product);
        //Почистим таблицу
        repoAgreements.deleteAll();
        //Запишем данные в таблицу
        new SaveAgreements(mockRepo, repoAgreements).apply(productModel);
        //Поищем новые записи
        repoAgreements.findAll();
        //Проверим, что наши записи в БД есть
        Assertions.assertTrue(repoAgreements.count() > 0);
    }
}
