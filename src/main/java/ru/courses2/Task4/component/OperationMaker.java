package ru.courses2.Task4.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.courses2.Task4.work.Model;

import java.util.function.Consumer;

@Component
public class OperationMaker {
    @Autowired
    MySupplier<Model,String> datareader;
    @Autowired @Qualifier("DataChangeFio")
    Changer<Model> modelChangeFio;
    @Autowired @Qualifier("DataTypeRequired")
    Changer<Model> modelChangeType;
    @Autowired @Qualifier("DataCheckDate")
    Changer<Model> modelCheckDate;
    @Autowired Consumer<Model> datasave;

    public void make() {
        final String path = "C:/Users/79586/Documents/JAVA_COURSES/2 этап обучения java/Task4/log/read"; // формат данных в файлах - soma;петр петрович петров;27-03-2025 09:21:11;mobile
        //Компонент чтения данных
        Model model = datareader.get(path);

        //Промежуточные компоненты
        modelChangeFio.change(model);
        modelChangeType.change(model);
        modelCheckDate.change(model);

        //Посмотрим результат
//        model.data.forEach(System.out::println);

        //Компонента записи дынных
        datasave.accept(model);
    }
}
