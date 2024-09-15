package org.example.models;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EvenToedUngulate extends Mammal {
    private int hoofCount;
    private String habitat;

    public EvenToedUngulate() {
        super();
        this.hoofCount = 4;
        this.habitat = "Неизвестно";
    }

    public EvenToedUngulate(String name, int age, String furColor, double weight, int hoofCount, String habitat) {
        super(name, age, furColor, weight);
        if (hoofCount <= 0 || hoofCount % 2 != 0) {
            throw new IllegalArgumentException("Количество копыт должно быть положительным четным числом.");
        }
        this.hoofCount = hoofCount;
        this.habitat = habitat;
    }


    public int getHoofCount() {
        return hoofCount;
    }

    public void setHoofCount(int hoofCount) {
        if (hoofCount <= 0 || hoofCount % 2 != 0) {
            throw new IllegalArgumentException("Количество копыт должно быть положительным четным числом.");
        }
        this.hoofCount = hoofCount;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        EvenToedUngulate that = (EvenToedUngulate) o;
        return hoofCount == that.hoofCount &&
                Objects.equals(habitat, that.habitat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hoofCount, habitat);
    }

    @Override
    public String toString() {
        return "Парнокопытное {" +
                "Имя='" + getName() + '\'' +
                ", Возраст=" + getAge() +
                ", Цвет шерсти='" + getFurColor() + '\'' +
                ", Вес=" + getWeight() +
                ", Количество копыт=" + hoofCount +
                ", Среда обитания='" + habitat + '\'' +
                '}';
    }
}