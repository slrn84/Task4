package ru.courses2.Task5.service.businessprocess;

import ru.courses2.Task5.model.entity.ProductRegister;

public interface SetAccountDI<T> {
    void accept(ProductRegister register);
}
