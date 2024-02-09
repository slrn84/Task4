package ru.courses2.Task5.service;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.courses2.Task5.model.ProductModel;
import ru.courses2.Task5.model.RequiredField;
import ru.courses2.Task5.work.Product;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;

@Service
@Order(1)
public class CheckRequiredParameterProduct implements BiConsumer<ProductModel, Product> {
    @Override
    public void accept(ProductModel productModel, Product product) {
        System.out.println("CheckRequiredParameterProduct");
        for (Field field : productModel.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(RequiredField.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(productModel) == null || field.get(productModel).toString().equals("")
                            || field.get(productModel).toString().equals("0"))
                        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST
                                , ": Имя обязательного параметра <" + field.getName() + "> не заполнено.");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
