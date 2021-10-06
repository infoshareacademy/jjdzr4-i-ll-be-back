package pl.infoshare;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);

    public void display() {
        System.out.println("----------------------------------------------------");
        System.out.println("&&&&&&&&&&&--->> Work & Fun Service <<---&&&&&&&&&&&");
        System.out.println("----------------------------------------------------");
        System.out.println("Wybierz jedną z opcji wprowadzając odpowiednią cyfrę");
        System.out.println("1 - Wyszukaj ogłoszenie z oferowana uslugą");
        System.out.println("2 - Wyszukaj ogłoszenie z poszukiwaną uslugą");
        System.out.println("3 - Dodaj ogłoszenie z oferowaną uslugą");
        System.out.println("4 - Dodaj ogłoszenie z poszukiwaną uslugą");
        System.out.println("5 - Edytuj ogłoszenie");
        System.out.println("6 - Usun ogłoszenie");
        System.out.println("0 - Zakończ program");
        System.out.println("----------------------------------------------------");
        getMenuChoice();
    }

    private void getMenuChoice() {
        switch (getUserInput()) {
            case 1:
                System.out.println("Wybrałes 1 - Wyszukaj ogłoszenie z oferowaną uslugą");
                break;
            case 2:
                System.out.println("Wybrałes 2 - Wyszukaj ogłoszenie z poszukiwaną uslugą");
                break;
            case 3:
                System.out.println("Wybrałes 3 - Dodaj ogłoszenie z oferowaną uslugą");
                break;
            case 4:
                System.out.println("Wybrałes 4 - Dodaj ogłoszenie z poszukiwaną uslugą");
                break;
            case 5:
                System.out.println("Wybrałes 5 - Edytuj ogłoszenie");
                break;
            case 6:
                System.out.println("Wybrałes 6 - Usun ogłoszenie");
                break;
            case 0:
                System.out.println("Wybrałes 0 - Zakończ program <<--- I'll Be Back !");
                break;
            default: {
                System.out.println("Podałeś zły parametr");
                getMenuChoice();
            }
        }
    }

    private byte getUserInput() {
        byte menuChoice;
        try {
            menuChoice = scanner.nextByte();
        } catch (Exception ignored) {
            scanner.next();
            return -1;
        }
        return menuChoice;
    }
}
