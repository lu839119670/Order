package com.example.order.Bean;

public class Menu {
    private String category1;
    private DishEnum category;
    private String name;
    private double money;
    private String weight;
    private int number;
    private String spicy;
    private int image;

    public Menu(DishEnum category, String name, double money, String weight, int number, String spicy, int image) {
        this.category = category;
        this.name = name;
        this.money = money;
        this.weight = weight;
        this.number = number;
        this.spicy = spicy;
        this.image = image;
    }

    public Menu(String name, double money, int number, int image) {
        this.name = name;
        this.money = money;
        this.number = number;
        this.image = image;
    }

    public Menu() {
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public DishEnum getCategory() {
        return category;
    }

    public void setCategory(DishEnum category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSpicy() {
        return spicy;
    }

    public void setSpicy(String spicy) {
        this.spicy = spicy;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
