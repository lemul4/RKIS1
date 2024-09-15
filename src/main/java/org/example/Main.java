package org.example;

import org.example.config.AppConfig;
import org.example.controllers.AnimalController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        AnimalController controller = context.getBean(AnimalController.class);
        controller.start();
    }
}