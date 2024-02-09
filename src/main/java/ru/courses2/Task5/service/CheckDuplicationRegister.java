package ru.courses2.Task5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.courses2.Task5.model.AccountModel;
import ru.courses2.Task5.work.ProductRegister;
import ru.courses2.Task5.work.RepoProductRegister;
import ru.courses2.Task5.work.ResponseRegister;
import ru.courses2.Task5.work.TerConsumer;

@Service
@Order(2)
public class CheckDuplicationRegister implements TerConsumer<AccountModel, ProductRegister, ResponseRegister> {
    private RepoProductRegister repoProductRegister;
    @Autowired
    public CheckDuplicationRegister(RepoProductRegister repoProductRegister) {
        this.repoProductRegister = repoProductRegister;
    }

    @Override
    public void accept(AccountModel accountModel, ProductRegister productRegister, ResponseRegister responseRegister) {
        System.out.println("CheckDuplicationRegister");
        ProductRegister productRegisterBd = repoProductRegister
                .findFirstByProduct_idAndType_value(accountModel.getInstanceId(),accountModel.getRegistryTypeCode());
        System.out.println("Проверка на дубли ПР: ищем по типу регистра "+accountModel.getRegistryTypeCode()
                +" и по ИД ЭП "+accountModel.getInstanceId()+"; Нашли ПР: "+productRegisterBd);
        if (productRegisterBd != null) throw new HttpClientErrorException( HttpStatus.BAD_REQUEST
                ,": Параметр registryTypeCode тип регистра <"+ accountModel.getRegistryTypeCode()
                +"> уже существует для ЭП с ИД  <"+ accountModel.getInstanceId() +">");
    }

}
