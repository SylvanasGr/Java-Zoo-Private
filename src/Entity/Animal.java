package Entity;

import Enumerations.Homotaxy;

import java.io.Serializable;

public class Animal implements  Serializable{

    private int code;
    private String name;
    private Homotaxy homotaxy;
    private double weight;
    private int age;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Homotaxy getHomotaxy() {
        return homotaxy;
    }

    public void setHomotaxy(Homotaxy homotaxy) {
        this.homotaxy = homotaxy;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Animal() {
    }
}
