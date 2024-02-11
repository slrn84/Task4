package ru.courses2.Task5.service.businessprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.courses2.Task5.model.entity.ProductRegister;
import ru.courses2.Task5.model.request.AccountModel;
import ru.courses2.Task5.model.response.ResponseRegister;
import ru.courses2.Task5.service.repository.ServiceRepoProductRegister;

@Service
@Order(2)
public class CheckDuplicationRegister implements AccountDI<AccountModel, ProductRegister, ResponseRegister> {
    private ServiceRepoProductRegister repoProductRegister;
    @Autowired
    public CheckDuplicationRegister(ServiceRepoProductRegister repoProductRegister) {
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
