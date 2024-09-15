package org.example.controllers;

import org.example.models.*;
import org.example.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AnimalController {

    private final AnimalService animalService;
    private static final Scanner scanner = new Scanner(System.in);

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить новый элемент");
            System.out.println("2. Удалить элемент по индексу");
            System.out.println("3. Вывести все элементы");
            System.out.println("4. Сравнить два элемента на равенство");
            System.out.println("5. Выход");

            System.out.print("Выберите пункт: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addNewElement();
                    break;
                case "2":
                    removeElement();
                    break;
                case "3":
                    displayAllElements();
                    break;
                case "4":
                    compareElements();
                    break;
                case "5":
                    exit = true;
                    System.out.println("Завершение работы приложения.");
                    break;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
    }

    private void addNewElement() {
        System.out.println("\nВыберите тип животного:");
        System.out.println("1. Животное");
        System.out.println("2. Млекопитающее");
        System.out.println("3. Парнокопытное");
        System.out.println("4. Птица");

        System.out.print("Ваш выбор: ");
        String typeChoice = scanner.nextLine();

        try {
            Animal animal = null;
            switch (typeChoice) {
                case "1":
                    animal = createAnimal();
                    break;
                case "2":
                    animal = createMammal();
                    break;
                case "3":
                    animal = createEvenToedUngulate();
                    break;
                case "4":
                    animal = createBird();
                    break;
                default:
                    System.out.println("Неверный выбор типа.");
            }
            if (animal != null) {
                animalService.addAnimal(animal);
                System.out.println("Элемент добавлен.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при добавлении элемента: " + e.getMessage());
        }
    }

    private Animal createAnimal() {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();

        int age = readIntWithValidation("Введите возраст животного (0-100): ", 0, 100);

        return new Animal(name, age);
    }

    private Mammal createMammal() {
        System.out.print("Введите имя млекопитающего: ");
        String name = scanner.nextLine();

        int age = readIntWithValidation("Введите возраст млекопитающего (0-100): ", 0, 100);

        System.out.print("Введите цвет шерсти: ");
        String furColor = scanner.nextLine();

        double weight = readDoubleWithValidation("Введите вес млекопитающего (0-10000 кг): ", 0, 10000);

        return new Mammal(name, age, furColor, weight);
    }

    private EvenToedUngulate createEvenToedUngulate() {
        System.out.print("Введите имя парнокопытного: ");
        String name = scanner.nextLine();

        int age = readIntWithValidation("Введите возраст парнокопытного (0-100): ", 0, 100);

        System.out.print("Введите цвет шерсти: ");
        String furColor = scanner.nextLine();

        double weight = readDoubleWithValidation("Введите вес парнокопытного (0-10000 кг): ", 0, 10000);

        int hoofCount = readIntWithValidation("Введите количество копыт (положительное четное число): ", 2, Integer.MAX_VALUE);
        if (hoofCount % 2 != 0) {
            throw new IllegalArgumentException("Количество копыт должно быть четным числом.");
        }

        System.out.print("Введите среду обитания: ");
        String habitat = scanner.nextLine();

        return new EvenToedUngulate(name, age, furColor, weight, hoofCount, habitat);
    }

    private Bird createBird() {
        System.out.print("Введите имя птицы: ");
        String name = scanner.nextLine();

        int age = readIntWithValidation("Введите возраст птицы (0-100): ", 0, 100);

        System.out.print("Введите цвет оперения: ");
        String featherColor = scanner.nextLine();

        double wingSpan = readDoubleWithValidation("Введите размах крыльев (0-10 метров): ", 0, 10);

        return new Bird(name, age, featherColor, wingSpan);
    }

    private void removeElement() {
        if (animalService.getAnimalsCount() == 0) {
            System.out.println("Список пуст. Удалять нечего.");
            return;
        }

        int index = readIntWithValidation("Введите индекс элемента для удаления: ", 0, animalService.getAnimalsCount() - 1);
        animalService.removeAnimal(index);
        System.out.println("Элемент удален.");
    }

    private void displayAllElements() {
        if (animalService.getAnimalsCount() == 0) {
            System.out.println("Список пуст.");
            return;
        }

        System.out.println("\nВсе элементы:");
        for (int i = 0; i < animalService.getAnimalsCount(); i++) {
            System.out.println("Индекс " + i + ": " + animalService.getAnimal(i));
        }
    }

    private void compareElements() {
        if (animalService.getAnimalsCount() < 2) {
            System.out.println("Недостаточно элементов для сравнения.");
            return;
        }

        int index1 = readIntWithValidation("Введите индекс первого элемента: ", 0, animalService.getAnimalsCount() - 1);
        int index2 = readIntWithValidation("Введите индекс второго элемента: ", 0, animalService.getAnimalsCount() - 1);

        if (animalService.getAnimal(index1).equals(animalService.getAnimal(index2))) {
            System.out.println("Элементы равны.");
        } else {
            System.out.println("Элементы не равны.");
        }
    }

    // Вспомогательные методы для чтения и валидации числовых значений
    private int readIntWithValidation(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
                if (value < min || value > max) {
                    System.out.println("Значение должно быть в диапазоне от " + min + " до " + max + ".");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Пожалуйста, введите целое число.");
            }
        }
        return value;
    }

    private double readDoubleWithValidation(String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                value = Double.parseDouble(input);
                if (value < min || value > max) {
                    System.out.println("Значение должно быть в диапазоне от " + min + " до " + max + ".");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Пожалуйста, введите число.");
            }
        }
        return value;
    }
}