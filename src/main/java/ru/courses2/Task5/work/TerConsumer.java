package ru.courses2.Task5.work;

@FunctionalInterface
public interface TerConsumer<T, U, S> {
    void accept(T t, U u, S s);
}