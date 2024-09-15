package org.example.models;

import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class Animal {
    private String name;
    private int age;

    public Animal() {
        this.name = "Неизвестно";
        this.age = 0;
    }

    public Animal(String name, int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Возраст должен быть от 0 до 100.");
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Возраст должен быть от 0 до 100.");
        }
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        return age == animal.age && Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Животное {" +
                "Имя='" + name + '\'' +
                ", Возраст=" + age +
                '}';
    }
}