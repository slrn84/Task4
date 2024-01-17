package ru.courses2.Task4.component;

@FunctionalInterface
public interface MySupplier<T,S> {
    T get(S s);
}