package ru.courses2.Task4.component;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.courses2.Task4.annotation.LogTransformation;
import ru.courses2.Task4.repo.Logins;
import ru.courses2.Task4.repo.Users;
import ru.courses2.Task4.work.Model;

@Component
@Order(3)
@LogTransformation("C:/Users/79586/Documents/JAVA_COURSES/2 этап обучения java/Task4/log/write/DataTypeRequired.txt")
public class DataTypeRequired implements Changer<Model> {
    @Override
    public Model change(Model model) {
        for (Users user : model.data) {
            for (Logins login : user.getLogins())
                if (!login.getApplication().matches("web|mobile"))
                    login.setApplication("other:" + login.getApplication());
        }
//        model.data.forEach(System.out::println);
        return model;
    }
}
