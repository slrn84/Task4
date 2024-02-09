package ru.courses2.Task5.work;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.HttpClientErrorException;
import ru.courses2.Task5.model.AccountModel;
import ru.courses2.Task5.model.AgreementModel;
import ru.courses2.Task5.model.ProductModel;
import ru.courses2.Task5.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceTest {
    AccountModel accountModel;
    AccountModel accountModel2;
    AgreementModel agreementModel1;
    AgreementModel agreementModel2;
    ProductModel productModel1;
    ProductModel productModel2;
    Product product;

    @BeforeEach
    public void initData() throws ParseException {
        //подготовим данные перед выполнением тестов
        accountModel = new AccountModel(1, "03.012.002_47533_ComSoLd", "Клиентский", "800",
                "0022", "00", "15", "", "15-12345", "ABC", "123");
        accountModel2 = new AccountModel(0, "03.012.002_47533_ComSoLd", "Клиентский", "800",
                "0022", "00", "15", "", "15-12345", "ABC", "123");
        agreementModel1 = new AgreementModel("123", "456", "НСО",
                123456789, "НСО-123", "2024-01-11", "2025-01-10", "",
                "365", "", "открыт", "", 0, 0,
                "", 0, 0, "",
                0, 0, "");
        agreementModel2 = new AgreementModel("789", "456", "СМО",
                123456789, "СМО-789", "2024-01-13", "2024-01-13", "",
                "365", "", "открыт", "", 0, 0,
                "", 0, 0, "",
                0, 0, "");
        productModel1 = new ProductModel(1, "ДОГОВОР", "03.012.002", "03.012.002_47533_ComSoLd",
                "15", "2024-01-10-000001", new SimpleDateFormat("yyyy-mm-dd").parse("2024-01-10"), 2, 12.25F,
                0F, 0F, "qwerty", "0", 13F, 1000F,
                112233, "0022", "800", "00", 1234, new ArrayList<>(),
                new ArrayList<>(Arrays.asList(agreementModel1, agreementModel2)));
        productModel2 = new ProductModel(1, "", "", "",
                "", "", new SimpleDateFormat("yyyy-mm-dd").parse("2024-01-10"), 2, 12.25F,
                0F, 0F, "qwerty", "0", 13F, 1000F,
                0, "0022", "800", "00", 1234, new ArrayList<>(),
                new ArrayList<>());
        accountModel = new AccountModel(1, "03.012.002_47533_ComSoLd", "Клиентский",
                "800", "0022", "00", "15", "", "15-12345",
                "ABC", "123");

        product = new Product();

    }

    //Проверка на обязательность заполнения помеченных аннотацией @RequiredField полей
    @Test
    @DisplayName("Обязательные поля ЭП - заполнены")
    public void CheckRequiredParameterProduct_TestGood() {
        Assertions.assertDoesNotThrow(
                () -> new CheckRequiredParameterProduct().accept(productModel1, product)
        );
    }

    @Test
    @DisplayName("Обязательные поля ЭП - не заполнены")
    public void CheckRequiredParameterProduct_TestBad() {
        Assertions.assertThrows(HttpClientErrorException.class,
                () -> new CheckRequiredParameterProduct().accept(productModel2, product)
        );
    }

    @Test
    @DisplayName("Обязательные поля ПР - заполнены")
    public void CheckRequiredParameterRegister_TestGood() {
        Assertions.assertDoesNotThrow(
                () -> new CheckRequiredParameterRegister().accept(accountModel, new ProductRegister(), new ResponseRegister())
        );
    }

    @Test
    @DisplayName("Обязательные поля ПР - не заполнены")
    public void CheckRequiredParameterRegister_TestBad() {
        Assertions.assertThrows(HttpClientErrorException.class,
                () -> new CheckRequiredParameterRegister().accept(accountModel2, new ProductRegister(), new ResponseRegister())
        );
    }

    // Проверка на дубли ЭП
    @Test
    @DisplayName("Дубль ЭП - не найден")
    public void CheckDuplicationProduct_TestGood() {
        //Замокируем обращение к БД
        RepoProduct mockRepo = Mockito.mock(RepoProduct.class);
        Mockito.when(mockRepo.findFirstByNumber(Mockito.any())).thenReturn(null);
        //Выполним проверку
        Assertions.assertDoesNotThrow(
                () -> new CheckDuplicationProduct(mockRepo).accept(productModel1, product)
        );
    }

    @Test
    @DisplayName("Дубль ЭП - найден")
    public void CheckDuplicationProduct_TestBad() {
        //Замокируем обращение к БД
        RepoProduct mockRepo = Mockito.mock(RepoProduct.class);
        Mockito.when(mockRepo.findFirstByNumber(Mockito.any())).thenReturn(new Product());
        //Выполним проверку
        Assertions.assertThrows(HttpClientErrorException.class,
                () -> new CheckDuplicationProduct(mockRepo).accept(productModel1, product)
        );
    }

    // Проверка на дубли ДС
    @Test
    @DisplayName("Дубль ДС - не найден")
    public void CheckDuplicationAgreement_TestGood() {
        //Замокируем обращение к БД
        RepoAgreements mockRepo = Mockito.mock(RepoAgreements.class);
        Mockito.when(mockRepo.findFirstByNumber(Mockito.any())).thenReturn(null);
        //Выполним проверку
        Assertions.assertDoesNotThrow(
                () -> new CheckDuplicationAgreement(mockRepo).accept(productModel1, product)
        );
    }

    @Test
    @DisplayName("Дубль ДС - найден")
    public void CheckDuplicationAgreement_TestBad() {
        //Замокируем обращение к БД
        RepoAgreements mockRepo = Mockito.mock(RepoAgreements.class);
        Mockito.when(mockRepo.findFirstByNumber(Mockito.any())).thenReturn(new Agreements());
        //Выполним проверку
        Assertions.assertThrows(HttpClientErrorException.class,
                () -> new CheckDuplicationAgreement(mockRepo).accept(productModel1, product)
        );
    }

    // Проверка на дубли ПР
    @Test
    @DisplayName("Дубль ПР - не найден")
    public void CheckDuplicationRegister_TestGood() {
        //Замокируем обращение к БД
        RepoProductRegister mockRepo = Mockito.mock(RepoProductRegister.class);
        Mockito.when(mockRepo.findFirstByProduct_idAndType_value(Mockito.anyInt(), Mockito.any())).thenReturn(null);
        //Выполним проверку
        Assertions.assertDoesNotThrow(
                () -> new CheckDuplicationRegister(mockRepo).accept(accountModel, new ProductRegister(), new ResponseRegister())
        );
    }

    @Test
    @DisplayName("Дубль ПР - найден")
    public void CheckDuplicationRegister_TestBad() {
        //Замокируем обращение к БД
        RepoProductRegister mockRepo = Mockito.mock(RepoProductRegister.class);
        Mockito.when(mockRepo.findFirstByProduct_idAndType_value(Mockito.anyInt(), Mockito.any()))
                .thenReturn(new ProductRegister(1,null,null,1,"1","1","1"));
        //Выполним проверку
        Assertions.assertThrows(HttpClientErrorException.class,
                () -> new CheckDuplicationRegister(mockRepo).accept(accountModel, new ProductRegister(), new ResponseRegister())
        );
    }

    // Проверка кода продукта
    @Test
    @DisplayName("Код продукта - не найден")
    public void CheckRegisterType_TestGood() {
        //Замокируем обращение к БД
        RepoProductRegisterType mockRepo = Mockito.mock(RepoProductRegisterType.class);
        Mockito.when(mockRepo.findByClasscode_ValueAndAcctype_Value(Mockito.any(), Mockito.any())).thenReturn(null);
        //Выполним проверку
        Assertions.assertThrows(HttpClientErrorException.class,
                () -> new CheckRegisterType(mockRepo).accept(productModel1, product)
        );
    }

    @Test
    @DisplayName("Код продукта - найден")
    public void CheckRegisterType_TestBad() {
        //Замокируем обращение к БД
        RepoProductRegisterType mockRepo = Mockito.mock(RepoProductRegisterType.class);
        ProductRegisterType productRegisterType1 = new ProductRegisterType(1, "03.012.002_47533_ComSoLd", "Хранение ДМ.", null, null);
        ProductRegisterType productRegisterType2 = new ProductRegisterType(2, "02.001.005_45343_CoDowFF", "Серебро. Выкуп.", null, null);
        Mockito.when(mockRepo.findByClasscode_ValueAndAcctype_Value(Mockito.any(), Mockito.any()))
                .thenReturn(new ArrayList<>(Arrays.asList(productRegisterType1, productRegisterType2)));
        //Выполним проверки, свяжем ЭП с ПР
        new CheckRegisterType(mockRepo).accept(productModel1, product);

        //Проверим, что регистры привязаны к продукту
        Assertions.assertTrue(product.getRegisters().stream().count() == 2);
    }


    @Test
    @DisplayName("Код продукта ПР - не найден")
    public void CheckCodeProductRegister_TestBad() {
        //Замокируем обращение к БД
        RepoProduct mockRepo = Mockito.mock(RepoProduct.class);
        Mockito.when(mockRepo.findFirstByIdAndClassCode(Mockito.anyInt(), Mockito.any()))
                .thenReturn(null);
        //Выполним проверку
        Assertions.assertThrows(HttpClientErrorException.class,
                () -> new CheckCodeProductRegister(mockRepo).accept(accountModel, new ProductRegister(), new ResponseRegister())
        );
    }

    @Test
    @DisplayName("Код продукта ПР - найден")
    public void CheckCodeProductRegister_TestGood() {
        //Замокируем обращение к БД
        RepoProduct mockRepo = Mockito.mock(RepoProduct.class);
        Mockito.when(mockRepo.findFirstByIdAndClassCode(Mockito.anyInt(), Mockito.any()))
                .thenReturn(new Product());
        //Выполним проверку
        Assertions.assertDoesNotThrow(
                () -> new CheckCodeProductRegister(mockRepo).accept(accountModel, new ProductRegister(), new ResponseRegister())
        );
    }
}
