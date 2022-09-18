import Operations.Actions;
import Operations.Service;

import java.util.Scanner;

public class ZooMain {

    public static void main(String[] args) {
//      We use scanner for a user input.
        Scanner input = new Scanner(System.in);
        Actions actions = new Actions();
        Service service = new Service();
        boolean loop = true;
        //todo:
        // 1.add corresponding try-catch for weight,feed and etc at the feature..
        // 2. try to implement an "nice-to-have" ui
        service.mainExecutableBusinessLogic(actions, loop, input);
        input.close();
    }
}
