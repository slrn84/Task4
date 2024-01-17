package ru.courses2.Task4.work;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.courses2.Task4.component.OperationMaker;
import ru.courses2.Task4.repo.DataSave2;
import ru.courses2.Task4.repo.Logins;
import ru.courses2.Task4.repo.Users;
import ru.courses2.Task4.repo.UsersRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EndToEndTest {
    @Test @DisplayName("End-to-end test")
    public void applicationTest(){
        //Определим контекст для взаимодествия с БД
        ApplicationContext ctx = SpringApplication.run(DataSave2.class);
        UsersRepo usersRepo = ctx.getBean(UsersRepo.class);
        //Почистим таблицу
        usersRepo.deleteAll();

        //!!!Исполним весь цикл приложения
        new AnnotationConfigApplicationContext("ru.courses2.Task4.annotation","ru.courses2.Task4.component")
                .getBean("operationMaker", OperationMaker.class).make();


        //Поищем новые записи
        usersRepo.findAll();
//        System.out.println(usersRepo.count());

        Assertions.assertTrue(usersRepo.count()>0);
    }
}
