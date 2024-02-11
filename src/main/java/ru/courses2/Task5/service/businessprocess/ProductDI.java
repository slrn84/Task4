package ru.courses2.Task5.service.businessprocess;

public interface ProductDI<T, U> {
    void accept(T t, U u);
}