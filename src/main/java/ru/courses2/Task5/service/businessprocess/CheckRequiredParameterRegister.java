package ru.courses2.Task5.service.businessprocess;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.courses2.Task5.model.request.AccountModel;
import ru.courses2.Task5.model.request.RequiredField;
import ru.courses2.Task5.model.entity.ProductRegister;
import ru.courses2.Task5.model.response.ResponseRegister;

import java.lang.reflect.Field;

@Service
@Order(1)
public class CheckRequiredParameterRegister implements AccountDI<AccountModel, ProductRegister, ResponseRegister> {
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
