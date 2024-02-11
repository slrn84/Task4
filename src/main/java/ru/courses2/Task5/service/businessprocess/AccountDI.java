package ru.courses2.Task5.service.businessprocess;

public interface AccountDI<T, U, S> {
    void accept(T t, U u, S s);
}