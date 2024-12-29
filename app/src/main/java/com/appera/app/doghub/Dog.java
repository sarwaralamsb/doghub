package com.appera.app.doghub;

// Dog.java
public class Dog {
    private String breed; // Breed name
    private String imageUrl; // URL for the dog's image
    private String lifespan; // Lifespan of the breed
    private String temperament; // Temperament of the breed
    private String group; // Group of the breed

    public Dog(String breed, String imageUrl, String lifespan, String temperament, String group) {
        this.breed = breed;
        this.imageUrl = imageUrl;
        this.lifespan = lifespan;
        this.temperament = temperament;
        this.group = group;
    }

    public String getBreed() {
        return breed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLifespan() {
        return lifespan;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getGroup() {
        return group;
    }
}
