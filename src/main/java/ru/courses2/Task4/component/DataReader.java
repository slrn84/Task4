package ru.courses2.Task4.component;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
import ru.courses2.Task4.repo.Logins;
import ru.courses2.Task4.repo.Users;
import ru.courses2.Task4.work.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//TODO: Разбить функционал на более мелкие классы или функции
@Component
public class DataReader implements MySupplier<Model, String> {
    @AllArgsConstructor
    @ToString
    class Data {
        String line;
        String filename;
    }

    @Override
    public Model get(String path) {
        // выходим, если путь некорректный
        if (path.isEmpty() || path == "") return null;

        Model model = new Model();
        model.data = new ArrayList<>();

        List<File> files;

        // читаем все файлы из указанной папки в files
        try {
            files = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Data> list = new ArrayList<>();
        // читаем все строки из каждого файла в массив list
        for (File file : files) {
            try {
                Files.lines(file.toPath())
                        .forEachOrdered(s -> list.add(new Data(s, file.toPath().toString())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
//        list.forEach(System.out::println);

        // парсим строки и пишем в таблицу
        for (Data line : list) {
            String[] words = line.line.split(";");
            Logins login = new Logins();
            Users user = searchUser(model, words[0]);

            if (words[2].isEmpty()) login.setDate(null);
            else login.setDate(LocalDateTime.parse(words[2], DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

            login.setApplication(words[3]);
            login.setFilename(line.filename);

            if (user == null) {
                user = new Users();
                user.setUsername(words[0]);
                user.setFio(words[1]);
                user.addLogin(login);
                model.data.add(user);
            } else user.addLogin(login);
        }

//        model.data.forEach(System.out::println);
        return model;
    }

    public Users searchUser(Model model, String name) {
        for (Users user : model.data) {
            if (user.getUsername().equals(name)) return user;
        }
        return null;
    }

}
