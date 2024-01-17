package ru.courses2.Task4.component;

import org.springframework.stereotype.Component;
import ru.courses2.Task4.annotation.LogTransformation;
import ru.courses2.Task4.logging.Logging;
import ru.courses2.Task4.repo.Logins;
import ru.courses2.Task4.repo.Users;
import ru.courses2.Task4.work.Model;

import java.util.Iterator;

@Component("DataCheckDate")
@LogTransformation("C:/Users/79586/Documents/JAVA_COURSES/2 этап обучения java/Task4/log/write/DataCheckDate.txt")
public class DataCheckDate implements Changer<Model> {
    @Override
    public Model change(Model model) {
        Iterator<Users> userIterator = model.data.iterator();
        while (userIterator.hasNext()) {
            Users user = userIterator.next();
            Iterator<Logins> loginIterator = user.getLogins().iterator();
            while (loginIterator.hasNext()) {
                Logins login = loginIterator.next();
                if (login.getDate() == null) {
                    //сохраним лог
                    saveLog("Ошибка в файле '" + login.getFilename() + "': не задана дата у юзера: " + user + "\n");
                    //удалим запись с пустой датой
                    loginIterator.remove();
                }
            }
            //если у юзера записей нет, то он в БД не нужен
            if (user.getLogins().stream().count() == 0) {
                //удалим юзера
                userIterator.remove();
            }
        }
//        model.data.forEach(System.out::println);
        return model;
    }

    public void saveLog(String text) {
        String fileForSave = "C:\\Users\\79586\\Documents\\JAVA_COURSES\\2 этап обучения java\\Task4\\log\\write\\Error.txt";
        new Logging(fileForSave, text).saveLog();
    }
}

