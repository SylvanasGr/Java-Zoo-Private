package Operations;

public interface ActionsImpl {

    void showAllExistingAnimals();
    void addAnimal();
    boolean searchByName(String name);
    void searchByCode(int code);
    void feedAnAnimal(String food,String animalToFeed);
    void deleteAnimal(int code);
}
