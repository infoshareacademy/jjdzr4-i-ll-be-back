package pl.infoshare;

import java.util.Scanner;

public class Menu {
    public void menu() {
        System.out.println("----------------------------------------------------");
        System.out.println("&&&&&&&&&&&--->> Work & Fun Service <<---&&&&&&&&&&&");
        System.out.println("----------------------------------------------------");
        System.out.println("Wybierz jedna z opcji wprowadzajac odpowiednia cyfre");
        System.out.println("1 - Wyszukaj ogloszenie z oferowana usluga");
        System.out.println("2 - Wyszukaj ogloszenie z poszukiwana usluga");
        System.out.println("3 - Dodaj ogloszenie z oferowana usluga");
        System.out.println("4 - Dodaj ogloszenie z poszukiwana usluga");
        System.out.println("5 - Edytuj ogloszenie");
        System.out.println("6 - Usun ogloszenie");
        System.out.println("7 - Zakoncz program");
        System.out.println("----------------------------------------------------");
        getMenuChoice();
    }

    public void getMenuChoice() {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        switch (string) {
            case "1":
                System.out.println("Wybrales 1 - Wyszukaj ogloszenie z oferowana usluga");
                break;
            case "2":
                System.out.println("Wybrales 2 - Wyszukaj ogloszenie z poszukiwana usluga");
                break;
            case "3":
                System.out.println("Wybrales 3 - Dodaj ogloszenie z oferowana usluga");
                break;
            case "4":
                System.out.println("Wybrales 4 - Dodaj ogloszenie z poszukiwana usluga");
                break;
            case "5":
                System.out.println("Wybrales 5 - Edytuj ogloszenie");
                break;
            case "6":
                System.out.println("Wybrales 6 - Usun ogloszenie");
                break;
            case "7":
                System.out.println("Wybrales 7 - Zakoncz program <<--- I'll Be Back !");
            default: {
                System.out.println("Ej Ty! Ogarnij sie i wprowadz poprawny parametr !");
                getMenuChoice();
            }
        }
    }
}
