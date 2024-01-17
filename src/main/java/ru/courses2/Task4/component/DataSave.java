package ru.courses2.Task4.component;

import org.springframework.stereotype.Component;
import ru.courses2.Task4.repo.DataSave2;
import ru.courses2.Task4.work.Model;

import java.util.function.Consumer;

@Component
public class DataSave implements Consumer<Model> {
    @Override
    public void accept(Model model) {
        //стоит перевызов для разделения контекстов
        DataSave2 ds2 = new DataSave2();
        ds2.accept(model);
    }
}
