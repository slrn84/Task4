package ru.courses2.Task5.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.courses2.Task5.model.entity.Product;
import ru.courses2.Task5.model.entity.ProductRegister;
import ru.courses2.Task5.model.request.AccountModel;
import ru.courses2.Task5.model.request.ProductModel;
import ru.courses2.Task5.model.response.ResponseProduct;
import ru.courses2.Task5.model.response.ResponseRegister;
import ru.courses2.Task5.service.businessprocess.AccountDI;
import ru.courses2.Task5.service.businessprocess.AgreementDI;
import ru.courses2.Task5.service.businessprocess.ProductDI;
import ru.courses2.Task5.service.businessprocess.ResponseProductDI;

import java.util.List;

@RestController
public class ProductController {
    List<ProductDI<ProductModel, Product>> checkAndExecProduct;
    AgreementDI<ProductModel, Product> execAgreement;
    List<AccountDI<AccountModel, ProductRegister, ResponseRegister>> checkAndExecAccount;
    ResponseProductDI<Product, ResponseProduct> prepareResponseProduct;

    @Autowired
    public ProductController(List<ProductDI<ProductModel, Product>> checkAndExecProduct
            , AgreementDI<ProductModel, Product> execAgreement
            , List<AccountDI<AccountModel, ProductRegister, ResponseRegister>> checkAndExecAccount
            , ResponseProductDI<Product, ResponseProduct> prepareResponseProduct
    ) {
        this.checkAndExecProduct = checkAndExecProduct;
        this.execAgreement = execAgreement;
        this.checkAndExecAccount = checkAndExecAccount;
        this.prepareResponseProduct = prepareResponseProduct;
    }

    // Предназначен для создания нового объекта ЭКЗЕМПЛЯР ПРОДУКТА (ЭП)
    @PostMapping("/corporate-settlement-instance/create")
    @Transactional
    public ResponseEntity<?> handle1(@RequestBody ProductModel productModel) {
        System.out.println("/corporate-settlement-instance/create");
        ResponseProduct responseProduct = new ResponseProduct();
        Product product;
        if (productModel.getInstanceId() == null) {
            product = new Product();
            // Ветка - Если ИД ЭП == NULL/Пусто
            checkAndExecProduct.stream().forEach(x -> x.accept(productModel, product));
        } else {
            // Ветка - Если ИД ЭП != NULL, то изменяется состав ДС
            product = execAgreement.apply(productModel);
        }
        System.out.println("11"+product);
        prepareResponseProduct.accept(product, responseProduct);
        System.out.println("11"+responseProduct);
        return new ResponseEntity<>(responseProduct, HttpStatus.OK);
    }

    // Предназначен для создания нового объекта ПРОДУКТОВЫЙ РЕГИСТР (ПР)
    @PostMapping("/corporate-settlement-account/create")
    @Transactional
    public ResponseEntity<?> handle2(@RequestBody AccountModel accountModel) {
        System.out.println("/corporate-settlement-account/create");
        ProductRegister productRegister = new ProductRegister();
        ResponseRegister responseRegister = new ResponseRegister();
        checkAndExecAccount.stream().forEach(x -> x.accept(accountModel, productRegister, responseRegister));
//        System.out.println(productRegister);

        return new ResponseEntity<>(responseRegister, HttpStatus.OK);
    }
}
