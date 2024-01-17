package ru.courses2.Task4.work;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.courses2.Task4.component.OperationMaker;

public class Application {
    public static void main(String[] args){
        new AnnotationConfigApplicationContext("ru.courses2.Task4.annotation","ru.courses2.Task4.component")
                .getBean("operationMaker", OperationMaker.class).make();
    }
}