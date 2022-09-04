import Operations.Actions;

import java.util.Scanner;

public class ZooMain {

    public static void main(String[] args) {

//            Actions actions = new Actions();
//            ArrayList<Animal> list =  actions.getAllTheAnimalsInformation();

//            for(Animal animal:list){
//                if(animal.getName().equals("5")){
//                    System.out.println("i found you");
//                }
//            }

//      We use scanner for a user input.
        Scanner input = new Scanner(System.in);
        Actions actions = new Actions();
        boolean loop = true;
        while (loop) {
            System.out.println("Choose a valid options from the menu:");
//      There a static method of the GenericUtil class who is showing us the menu.
            GenericUtils.menuOptions();
            //      We hold the user input to a String variable.
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
                    System.out.println("Give me the name of the animal to look if we have it to our zoo: ");
                    actions.searchByName(input.nextLine());
                    break;
                case "4":
                    System.out.println("Give me the code of the animal to look if we have it to our zoo: ");
                    actions.searchByCode(Integer.parseInt(input.nextLine()));
                    break;
                case "5":
                    System.out.println("Give me the code of the animal to remove it from our zoo: ");
                    actions.deleteAnimal(Integer.parseInt(input.nextLine()));
                    break;
                case "6":
                    System.out.println("Which animal do you want to feed? Give me the name: ");
                    String animalToFeed = input.nextLine();
                    boolean isExisting = actions.searchByName(animalToFeed);
                    if(isExisting){
                        System.out.println("Give me the real quantity of the food in grams (example 125.50) to feed our little friend at the zoo!");
                        actions.feedAnAnimal(input.nextLine(),animalToFeed);
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
            input.close();


    }


}
