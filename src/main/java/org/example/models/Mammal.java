package org.example.models;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Mammal extends Animal {
    private String furColor;
    private double weight;

    public Mammal() {
        super();
        this.furColor = "Неизвестно";
        this.weight = 0.0;
    }

    public Mammal(String name, int age, String furColor, double weight) {
        super(name, age);
        if (weight < 0 || weight > 10000) {
            throw new IllegalArgumentException("Вес должен быть от 0 до 10000 кг.");
        }
        this.furColor = furColor;
        this.weight = weight;
    }


    public String getFurColor() {
        return furColor;
    }

    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight < 0 || weight > 10000) {
            throw new IllegalArgumentException("Вес должен быть от 0 до 10000 кг.");
        }
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Mammal mammal = (Mammal) o;
        return Double.compare(mammal.weight, weight) == 0 &&
                Objects.equals(furColor, mammal.furColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), furColor, weight);
    }

    @Override
    public String toString() {
        return "Млекопитающее {" +
                "Имя='" + getName() + '\'' +
                ", Возраст=" + getAge() +
                ", Цвет шерсти='" + furColor + '\'' +
                ", Вес=" + weight +
                '}';
    }
}