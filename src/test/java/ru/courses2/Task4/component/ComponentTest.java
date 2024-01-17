package ru.courses2.Task4.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import ru.courses2.Task4.repo.DataSave2;
import ru.courses2.Task4.repo.Logins;
import ru.courses2.Task4.repo.Users;
import ru.courses2.Task4.repo.UsersRepo;
import ru.courses2.Task4.work.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class ComponentTest{
    private Model model;
    private Users user1;
    private Users user2;
    private Logins logins1;
    private Logins logins2;
    private Logins logins3;
    private Logins logins4;


    @BeforeEach
    public void initData() {
        //подготовим данные перед выполнением тестов
        model = new Model();
        model.data = new ArrayList<>();

        user1 = new Users(1, "petya","гном гномыч",new ArrayList<Logins>());
        user2 = new Users(2, "vasya","сидоров сидр сидорович с",new ArrayList<Logins>());

        logins1 = new Logins(1, LocalDateTime.parse("14-02-2021 23:05:01", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), null, "web", "");
        logins2 = new Logins(2, LocalDateTime.parse("01-10-2022 14:17:26", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), null, "mobile", "");
        logins3 = new Logins(3, LocalDateTime.parse("06-07-2023 01:36:45", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), null, "http", "");
        logins4 = new Logins(4, null, user2, "web", "");

        user1.addLogin(logins1);
        user1.addLogin(logins2);
        user2.addLogin(logins3);
        user2.addLogin(logins4);

        model.data.add(user1);
        model.data.add(user2);
    }

    //компонент проверки данных - исправляет ФИО так, чтобы каждый его компонент начинался с большой буквы
    @Test
    @DisplayName("ФИО")
    public void DataChangeFioTest(){
        //внесем изменения в модель
        new DataChangeFio().change(model);

        //посмотрим визуально
        System.out.println(model);

        //выполним проверку
        for (Users user: model.data){
            for(String word : user.getFio().split(" ")){
                Assertions.assertTrue(Character.isUpperCase(word.charAt(0)));
            }
        }
    }

    //компонента проверяет, что тип приложения соответствует одному из: “web”, “mobile”. Если там записано что-либо иное, то оно преобразуется к виду “other:”+значение.
    @Test
    @DisplayName("Тип приложения")
    public void DataTypeRequiredTest() {
        //внесем изменения в модель
        new DataTypeRequired().change(model);

        //посмотрим визуально
        System.out.println(model);

        //выполним проверку
        for (Users user: model.data){
            for(Logins login : user.getLogins()){
                String app = login.getApplication();
                if (!app.matches("web|mobile")) Assertions.assertTrue(app.startsWith("other:"));
            }
        }
    }

    //компонента проверки даты проверяет её наличие. Если дата не задана, то человек не вносится в базу, а сведения о имени файла и значении человека заносятся в отдельный лог.
    @Test
    @DisplayName("Время и дата")
    public void DataCheckDateTest(){
        //внесем изменения в модель с помощью заглушек
        new DataCheckDateStub().change(model);

        //посмотрим визуально
        System.out.println(model);

        //проверим, что записи с пустой датой отсутствуют в нашей модели
        for (Users user: model.data){
            for(Logins login : user.getLogins()){
                Assertions.assertTrue(login.getDate() != null);
            }
        }
    }

    //проверим, что компонента читает записи из файлов
    @Test()
    @DisplayName("Чтение данных")
    public void DataReaderTest(){
        //пример записи в файлах - admin;администратор ку;11-02-2023 23:05:01;web
        String path = "C:/Users/79586/Documents/JAVA_COURSES/2 этап обучения java/Task4/log/read";
        Model model = new DataReader().get(path);
        Assertions.assertTrue(model.data.stream().count()>0);
    }

    @Test
    @DisplayName("Запись в БД")
    public void DataSaveTest(){
        //Определим контекст
        ApplicationContext ctx = SpringApplication.run(DataSave2.class);
        UsersRepo usersRepo = ctx.getBean(UsersRepo.class);
        //Почистим таблицу
        usersRepo.deleteAll();
        //Запишим данные в таблицу
        for (Users user : model.data) {
            usersRepo.save(user);
        }
        //Поищем новые записи
        usersRepo.findAll();

        Assertions.assertTrue(usersRepo.count()>0);
    }
}
