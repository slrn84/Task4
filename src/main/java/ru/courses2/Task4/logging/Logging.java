package ru.courses2.Task4.logging;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor
public class Logging {
    private String file;
    private String text;

    public void saveLog() {
        if (file == "") System.out.println(text);
        else {
            try (FileWriter fw = new FileWriter(file, true);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
