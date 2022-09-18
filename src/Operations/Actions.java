package Operations;

import Entity.Animal;
import Enumerations.Homotaxy;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Actions implements ActionsImpl {

    private final String sepComment = " |";

    //this operation show all the existing animals to our zoo.
    @Override
    public void showAllExistingAnimals() {
        ArrayList<Animal> existingAnimals = readFileAndGetAnimals();
        if (existingAnimals == null) {
            System.out.println("No data has been found");
        } else {
            existingAnimals.forEach(x -> {
                System.out.println("Animal with name: " + x.getName() + sepComment + " code: " + x.getCode() + sepComment + " homotaxy: " + x.getHomotaxy() + sepComment + " weight: " + x.getWeight() + sepComment + " age: " + x.getAge());
            });
        }

    }

    //this operation add an animal to our list
    @Override
    public void addAnimal() {
        ArrayList<Animal> listOfExistingAnimals = readFileAndGetAnimals();
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter Animal Name: ");
        String name = userInput.nextLine();
        System.out.println("Enter Animal Code: ");
        //same logic apply for the fields, I will let exception handling only for the code
        int code = rightFormatCode(userInput);
        homotaxyOptions();
        System.out.println("Enter Animal Homotaxy from the above menu: ");
        int categoryOfHomotaxy = userInput.nextInt();
        Homotaxy homotaxy = homotaxyMenu(categoryOfHomotaxy);
        while (homotaxy == null) {
            homotaxyOptions();
            System.out.println("Give me a valid option from the above menu: ");
            categoryOfHomotaxy = userInput.nextInt();
            homotaxy = homotaxyMenu(categoryOfHomotaxy);
        }
        System.out.println("Enter Animal Weight: ");
        double weight = userInput.nextDouble();
        System.out.println("Enter Animal Age: ");
        int age = userInput.nextInt();

        //check if the anima has the same code or same name animal.

        boolean stored = isCapableForSave(listOfExistingAnimals, name, code);
        if (stored) {
            //create the obj for stored to our java serialization
            Animal animal = new Animal();
            animal.setName(name);
            animal.setCode(code);
            animal.setHomotaxy(homotaxy);
            animal.setWeight(weight);
            animal.setAge(age);
            //for the first record need to initialize
            if (listOfExistingAnimals == null) {
                //need to initialize the variable cause will have null pointer exception
                listOfExistingAnimals = new ArrayList<>();
                listOfExistingAnimals.add(animal);
            } else {
                listOfExistingAnimals.add(animal);
            }
            writeFile(listOfExistingAnimals);

        } else {
            System.out.println("Record dont saved successfully,try again!");
        }


    }

    //this operation finds an existing animal by name
    @Override
    public boolean searchByName(String name) {
        ArrayList<Animal> existingAnimals = readFileAndGetAnimals();
        assert existingAnimals != null;
        for (Animal animal : existingAnimals) {
            if (Objects.equals(animal.getName(), name)) {
                System.out.println("Animal with name: " + animal.getName() + " is existing to our Zoo with code : " + animal.getCode() + " and homotaxy: " + animal.getHomotaxy() + " age:" + animal.getAge() + " weight:" + animal.getWeight() + "\n");
                return true;
            }
        }
        System.out.println("Sorry we dont have animal with name : " + name + " to our zoo.\n");
        return false;
    }

    //this operation finds an existing animal by code
    @Override
    public void searchByCode(int code) {
        ArrayList<Animal> existingAnimals = readFileAndGetAnimals();
        boolean isNotFound = true;
        assert existingAnimals != null;
        for (Animal animal : existingAnimals) {
            if (Objects.equals(animal.getCode(), code)) {
                System.out.println("Animal with name: " + animal.getName() + " is existing to our Zoo with code : " + animal.getCode() + " and homotaxy: " + animal.getHomotaxy() + "\n");
                isNotFound = false;
            }
        }

        if (isNotFound) {
            System.out.println("Sorry we dont have animal with name : " + code + " to our zoo.\n");
        }

    }

    //this operation "feeds" an existing animal and adds the quantity of the food to its weight.
    @Override
    public void feedAnAnimal(String food, String animalToFeed) {
        double foodQuantity = 0;
        try {
            foodQuantity = Double.parseDouble(food);
        } catch (NumberFormatException e) {
            System.out.println("Sorry you dont put a double value.");
        }

        ArrayList<Animal> animals = readFileAndGetAnimals();
        double finalFoodQuantity = foodQuantity;
        animals.forEach(x -> {
            if (x.getName().equals(animalToFeed)) {
                double newWeight = x.getWeight() + finalFoodQuantity;
                System.out.println("You feed animal with name : " + x.getName() + " and weight: " + x.getWeight() + " the quantity of food: " + food + " so our little friend gained some weight.. so the new weight is now: " + newWeight + "\n");
                x.setWeight(newWeight);
            }
        });
        writeFile(animals);
    }

    //this operation deletes an animal by code
    @Override
    public void deleteAnimal(int code) {
        ArrayList<Animal> animals = readFileAndGetAnimals();
        boolean isDeleted = false;
        if (animals != null) {
            isDeleted = animals.removeIf(x -> x.getCode() == code);
        }
        if (isDeleted) {
            System.out.println("Animal with code : " + code + " has been deleted successfully!");
        } else {
            System.out.println("Animal doesnt exist in our zoo, please try again.");
        }

        writeFile(animals);

    }

    //this is a private operation for reading a file and then get a list of animals via deserialization,
    //note that this operation could be a generic operation and living at the Utils/GenericUtils
    // because we can re-use it for another Objects-Classes, but for this exercise is not necessary.
    private ArrayList<Animal> readFileAndGetAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("listData.txt");
            if (fis.available() == 0) {
                fis.close();
                return null;
            }

            ObjectInputStream ois = new ObjectInputStream(fis);
            animals = (ArrayList<Animal>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return animals;
    }

    //this is a private operation for write a file via serialization.
    //note that this operation could be a generic operation and living at the Utils/GenericUtils
    // because we can re-use it for another Objects-Classes, but for this exercise is not necessary.
    private void writeFile(ArrayList<Animal> animals) {
        try {
            FileOutputStream fos = new FileOutputStream("listData.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(animals);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    //a private method for reduce the boilerplate code.
    private Homotaxy homotaxyMenu(int answer) {
        switch (answer) {
            case 1:
                return Homotaxy.AMFIBIA;
            case 2:
                return Homotaxy.ARAXNIDIA;
            case 3:
                return Homotaxy.DITHURA;
            case 4:
                return Homotaxy.ENTOMA;
            case 5:
                return Homotaxy.ERPETA;
            case 6:
                return Homotaxy.THILASTIKA;
            case 7:
                return Homotaxy.KEFALOPODA;
            case 8:
                return Homotaxy.MALAKOSTRAKA;
            case 9:
                return Homotaxy.PTHNA;
            case 10:
                return Homotaxy.SYNAPSIDWTA;
            case 11:
                return Homotaxy.TRILOBITES;
            case 12:
                return Homotaxy.OMOTAXIES_PSARIWN;
            default:
                System.out.println("This homotaxy doesn't exist on the menu.\n");
        }
        return null;
    }

    //this operation prints the options of the class Homotaxy , it can be stored in a different place,
    //but I assume that it's only for "one-time-use".
    public void homotaxyOptions() {
        System.out.println("\n-----------------Menu--------------------------------");
        System.out.println("1. For -> AMFIBIA ");
        System.out.println("2. For -> ARAXNIDIA");
        System.out.println("3. For -> DITHURA");
        System.out.println("4. For -> ENTOMA");
        System.out.println("5. For -> ERPETA");
        System.out.println("6. For -> THILASTIKA");
        System.out.println("7. For -> KEFALOPODA");
        System.out.println("8. For -> MALAKOSTRAKA");
        System.out.println("9. For -> PTHNA");
        System.out.println("10. For -> SYNAPSIDWTA");
        System.out.println("11. For -> TRILOBITES");
        System.out.println("12. For -> OMOTAXIES_PSARIWN");

        System.out.println("-----------------------------------------------------\n");
    }

    //checks before add a new animal if containing same name or code with the existing animal
    private boolean isCapableForSave(ArrayList<Animal> animals, String name, int code) {
        if(animals == null) return true;
        for (Animal animal : animals) {
            if (animal.getName().equals(name) || animal.getCode() == code) {
                System.out.println("You cant save a animal with the same name or code of the existing animals.");
                return false;
            }
        }
        return true;
    }

    //this is an operation checks if the given code for adding a new animal has the correct format.
    //we can use main ways but the recursion method was a "nice-to-have" for educational proposes.
    private int rightFormatCode(Scanner userInput) {
        int code = 0;
        try {
            code = userInput.nextInt();
        } catch (Exception e) {
            System.out.println("Please provide a code, not a latin character.");
            System.out.println("Enter Animal Code: ");
            //clear the cache  and ask the user for valid code.
            userInput.nextLine();
        }
        if (code == 0) {
            //exit from the recursion
            return rightFormatCode(userInput);
        }
        return code;
    }

}


