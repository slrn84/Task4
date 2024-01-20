package ru.courses2.Task4.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.courses2.Task4.work.Model;

import java.util.List;
import java.util.function.Consumer;

@Component
public class OperationMaker implements Operationable{
    MySupplier<Model,String> datareader;
    List<Changer<Model>> changer;
    Consumer<Model> datasave;
    @Autowired
    public OperationMaker(MySupplier<Model, String> datareader, List<Changer<Model>> changer, Consumer<Model> datasave) {
        this.datareader = datareader;
        this.changer = changer;
        this.datasave = datasave;
    }

    public void make() {
        final String path = "C:/Users/79586/Documents/JAVA_COURSES/2 этап обучения java/Task4/log/read"; // формат данных в файлах - soma;петр петрович петров;27-03-2025 09:21:11;mobile

        //Компонент чтения данных
        Model model = datareader.get(path);
        System.out.println(changer.toString());

        //Промежуточные компоненты
        changer.stream().forEach(x->x.change(model));

        //Посмотрим результат
//        model.data.forEach(System.out::println);

        //Компонента записи дынных
        datasave.accept(model);
    }
}
