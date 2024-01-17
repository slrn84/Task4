package ru.courses2.Task4.component;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import ru.courses2.Task4.annotation.LogTransformation;
import ru.courses2.Task4.repo.Users;
import ru.courses2.Task4.work.Model;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("DataChangeFio")
@LogTransformation("C:/Users/79586/Documents/JAVA_COURSES/2 этап обучения java/Task4/log/write/DataChangeFio.txt")
public class DataChangeFio implements Changer<Model> {
    @Override
    public Model change(Model model) {
        for (Users user: model.data){
            user.setFio(firstCharToUpperCase(user.getFio()));
        }
//        model.data.forEach(System.out::println);
        return model;
    }

    // Приведем первые буквы во всех словах к верхнему регистру
    public String firstCharToUpperCase(String line){
        return Stream.of(line.trim().split("\\s"))
                .filter(w -> w.length() > 0)
                .map(w -> w.substring(0, 1).toUpperCase() + w.substring(1))
                .collect(Collectors.joining(" "));
    }
}

