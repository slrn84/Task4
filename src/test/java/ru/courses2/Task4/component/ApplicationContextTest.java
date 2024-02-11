package ru.courses2.Task4.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import ru.courses2.Task4.work.Model;

import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {DataReader.class, DataSave.class, DataChangeFio.class, DataCheckDate.class, DataTypeRequired.class})
public class ApplicationContextTest {
    MySupplier<Model,String> dataReaderTest;
    List<Changer<Model>> changerTest;
    Consumer<Model> dataSaveTest;
    @Autowired
    public ApplicationContextTest(MySupplier<Model, String> datareader, List<Changer<Model>> changer, Consumer<Model> datasave) {
        this.dataReaderTest = datareader;
        this.changerTest = changer;
        this.dataSaveTest = datasave;
    }

    //проверим, что бины созданы и внедрены
    @Test
    public void contextLoads() {
        System.out.println(changerTest.toString());
        assertThat(dataReaderTest).isNotNull();
        assertThat(changerTest).isNotNull();
        assertThat(dataSaveTest).isNotNull();
    }
}