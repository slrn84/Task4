package ru.courses2.Task5.service.businessprocess;

public interface ResponseProductDI<T, U> {
    void accept(T t, U u);
}
