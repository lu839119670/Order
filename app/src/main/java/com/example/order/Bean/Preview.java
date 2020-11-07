package com.example.order.Bean;

public class Preview {
    private String name;
    private double money;
    private int number;
    private String weight;
    private String spicy;

    public Preview(String name, double money, int number, String weight, String spicy) {
        this.name = name;
        this.money = money;
        this.number = number;
        this.weight = weight;
        this.spicy = spicy;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSpicy() {
        return spicy;
    }

    public void setSpicy(String spicy) {
        this.spicy = spicy;
    }
}
