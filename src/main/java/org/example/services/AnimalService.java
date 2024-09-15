package org.example.services;

import org.example.models.Animal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {
    private final List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(int index) {
        animals.remove(index);
    }

    public List<Animal> getAllAnimals() {
        return animals;
    }

    public Animal getAnimal(int index) {
        return animals.get(index);
    }

    public int getAnimalsCount() {
        return animals.size();
    }
}