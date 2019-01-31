package com.bergsgustavs.todotrainingproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class TodoTrainingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoTrainingProjectApplication.class, args);
    }

}

