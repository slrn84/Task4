package ru.courses2.Task5.work;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ResponseError {
    private HttpStatusCode statusCode;
    private String message;

    public ResponseError(HttpStatusCode statusCode, String message) {
        this.statusCode = statusCode;
        if (statusCode != HttpStatus.OK)
            this.message = message
                    + " Стек вызова: "
                    + Arrays.stream(Thread.currentThread().getStackTrace())
                    .map(s -> s.toString()).collect(Collectors.joining(" \n "))
                    ;
    }
}