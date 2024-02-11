package ru.courses2.Task5.service.businessprocess;


public interface AgreementDI<T, R> {
    R apply(T t);
}
