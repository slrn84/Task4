package ru.courses2.Task5.service.businessprocess;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.courses2.Task5.model.request.ProductModel;
import ru.courses2.Task5.model.request.RequiredField;
import ru.courses2.Task5.model.entity.Product;

import java.lang.reflect.Field;

@Service
@Order(1)
public class CheckRequiredParameterProduct implements ProductDI<ProductModel, Product> {
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
