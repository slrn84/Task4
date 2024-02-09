package ru.courses2.Task5.service;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.courses2.Task5.model.AccountModel;
import ru.courses2.Task5.model.RequiredField;
import ru.courses2.Task5.work.ProductRegister;
import ru.courses2.Task5.work.ResponseRegister;
import ru.courses2.Task5.work.TerConsumer;

import java.lang.reflect.Field;

@Service
@Order(1)
public class CheckRequiredParameterRegister implements TerConsumer<AccountModel, ProductRegister, ResponseRegister> {
    @Override
    public void accept(AccountModel accountModel, ProductRegister productRegister, ResponseRegister responseRegister) {
        System.out.println("CheckRequiredParameterAccount");
        for (Field field : accountModel.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(RequiredField.class)) {
                field.setAccessible(true);

                try {
                    if (field.get(accountModel) == null || field.get(accountModel).toString().equals("")
                            || field.get(accountModel).toString().equals("0"))
                        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST
                                , ": Имя обязательного параметра <" + field.getName() + "> не заполнено.");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
