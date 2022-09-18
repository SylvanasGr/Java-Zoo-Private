package Operations;

import utils.GenericUtils;

import java.util.Scanner;

public class Service implements ServiceImpl {


    @Override
    public void mainExecutableBusinessLogic(Actions actions, boolean loop, Scanner input) {
        while (loop) {
            System.out.println("Choose a valid options from the menu:");
            // There a static method of the GenericUtil class who is showing us the menu.
            GenericUtils.menuOptions();
            //  We hold the user input to a String variable.
            String answer = input.nextLine();
            System.out.println("Choice " + answer + " has been selected!");
            switch (answer) {
                case "1":
                    actions.showAllExistingAnimals();
//                    loop = false;
                    break;
                case "2":
                    actions.addAnimal();
                    break;
                case "3":
                    try {
                        System.out.println("Give me the name of the animal to look if we have it to our zoo: ");
                        actions.searchByName(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("Try again something went wrong");
                    }
                    break;
                case "4":
                    try {

                        System.out.println("Give me the code of the animal to look if we have it to our zoo: ");
                        actions.searchByCode(Integer.parseInt(input.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input try again :) ...");
                    }
                    break;
                case "5":
                    try {
                        System.out.println("Give me the code of the animal to remove it from our zoo: ");
                        actions.deleteAnimal(Integer.parseInt(input.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input try again :) ...");
                    }
                    break;
                case "6":
                    try {
                        System.out.println("Which animal do you want to feed? Give me the name: ");
                        String animalToFeed = input.nextLine();
                        boolean isExisting = actions.searchByName(animalToFeed);
                        if (isExisting) {
                            System.out.println("Give me the real quantity of the food in Kilos (example 0.200 equals 200grams) to feed our little friend at the zoo!");
                            actions.feedAnAnimal(input.nextLine(), animalToFeed);
                        }
                    } catch (Exception e) {
                        System.out.println("Animal is still hungry .. maybe try again with correct data? :D !");
                    }
                    break;
                case "7":
                    System.out.println("Good Bye");
                    loop = false;
                    break;
                default:
                    System.out.println("This options is invalid, please try again.");
            }
        }
    }
}
