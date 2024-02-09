package ru.courses2.Task5.service;

import org.springframework.stereotype.Service;
import ru.courses2.Task5.work.ProductRegister;

import java.util.function.Consumer;

@Service
public class SetAccount implements Consumer<ProductRegister> {
    //заглушка получения номера счета по параметрам
    @Override
    public void accept(ProductRegister register) {
        System.out.println("SetAccount");
        String account_number = "";
        String account_id = "";

        if(register.getType()==null) {
            //Найти значение номера счета по параметрам branchCode, currencyCode, mdmCode, priorityCode, registryTypeCode в таблице Пулов счетов

        } else {
            //Найти значение номера счета по registryTypeCode в таблице Пулов счетов
        }

        for (int i = 0; i < 20; i++) {
            int n = (int) ((Math.random() * 9));
            account_number = account_number + n;
        }
        for (int i = 0; i < 8; i++) {
            int n = (int) ((Math.random() * 9));
            account_id = account_id + n;
        }
        register.setAccount_number(account_number);
        register.setAccount_id(Integer.parseInt(account_id.trim()));
    }
}
