package ui;
import java.util.*;
import model.InfrastructureDepartment;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static Scanner txt;
    static InfrastructureDepartment inDep;
    public static void main(String[] args) {
        txt = new Scanner(System.in);
        inDep = new InfrastructureDepartment();
        menu();
    }

    static void menu() {
        int optMenu = -1;
        System.out.printf("Welcome to Billboard Management");
        while (optMenu != 0) {
            System.out.print("\nPlease select an option:\n1. Add billboard\n2. Show billboards\n3. Export danger report\n" +
                    "4. Exit\n");
            optMenu = Main.txt.nextInt();
            Main.txt.nextLine();
            switch (optMenu) {
                case 1:
                    System.out.println("Please, enter the details for new billboard separated by (++)");
                    String billboardDetails=Main.txt.nextLine();
                    inDep.addBillboard(billboardDetails);
                    break;
                case 2:
                    inDep.loadBillboards();
                    break;
                case 3:
                    inDep.exportDangerousBillboardReport();
                    break;
                case 4:
                    System.out.print("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.print("Please enter a valid option");
                    break;
            }
        }
    }

}