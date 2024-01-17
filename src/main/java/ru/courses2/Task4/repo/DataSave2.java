package ru.courses2.Task4.repo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.courses2.Task4.work.Model;

@SpringBootApplication(scanBasePackages = "ru.courses2.Task4.repo")
public class DataSave2 {
    public void accept(Model model) {
        //Определим контекст
        ApplicationContext ctx = SpringApplication.run(DataSave2.class);
        UsersRepo usersRepo = ctx.getBean(ru.courses2.Task4.repo.UsersRepo.class);

        //Запишим данные в таблицы
        for (Users user : model.data) {
            usersRepo.save(user);
        }
    }
}
