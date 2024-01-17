package ru.courses2.Task4.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import ru.courses2.Task4.work.Model;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {DataReader.class, DataSave.class, DataChangeFio.class, DataCheckDate.class, DataTypeRequired.class})
public class ApplicationContextTest {
    @Autowired
    private MySupplier<Model,String> dataReaderTest;
    @Autowired @Qualifier("DataChangeFio")
    private Changer<Model> modelChangeFioTest;
    @Autowired @Qualifier("DataTypeRequired")
    private Changer<Model> modelChangeTypeTest;
    @Autowired @Qualifier("DataCheckDate")
    private Changer<Model> modelCheckDateTest;
    @Autowired
    private Consumer<Model> dataSaveTest;

    //проверим, что бины созданы и внедрены
    @Test
    public void contextLoads() throws Exception {

        assertThat(dataReaderTest).isNotNull();
        assertThat(modelChangeFioTest).isNotNull();
        assertThat(modelChangeTypeTest).isNotNull();
        assertThat(modelCheckDateTest).isNotNull();
        assertThat(dataSaveTest).isNotNull();
    }
}