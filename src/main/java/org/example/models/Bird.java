package org.example.models;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Bird extends Animal {
    private String featherColor;
    private double wingSpan;

    public Bird() {
        super();
        this.featherColor = "Неизвестно";
        this.wingSpan = 0.0;
    }

    public Bird(String name, int age, String featherColor, double wingSpan) {
        super(name, age);
        if (wingSpan < 0 || wingSpan > 10) {
            throw new IllegalArgumentException("Размах крыльев должен быть от 0 до 10 метров.");
        }
        this.featherColor = featherColor;
        this.wingSpan = wingSpan;
    }


    public String getFeatherColor() {
        return featherColor;
    }

    public void setFeatherColor(String featherColor) {
        this.featherColor = featherColor;
    }

    public double getWingSpan() {
        return wingSpan;
    }

    public void setWingSpan(double wingSpan) {
        if (wingSpan < 0 || wingSpan > 10) {
            throw new IllegalArgumentException("Размах крыльев должен быть от 0 до 10 метров.");
        }
        this.wingSpan = wingSpan;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Bird bird = (Bird) o;
        return Double.compare(bird.wingSpan, wingSpan) == 0 &&
                Objects.equals(featherColor, bird.featherColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), featherColor, wingSpan);
    }

    @Override
    public String toString() {
        return "Птица {" +
                "Имя='" + getName() + '\'' +
                ", Возраст=" + getAge() +
                ", Цвет оперения='" + featherColor + '\'' +
                ", Размах крыльев=" + wingSpan +
                '}';
    }
}