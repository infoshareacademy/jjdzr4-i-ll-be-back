package pl.infoshare;

import pl.infoshare.announcements.AnnouncementEditionService;
import pl.infoshare.announcements.AnnouncementService;
import pl.infoshare.announcements.ReturnToMenuException;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final AnnouncementService myAnnouncementService = new AnnouncementService();
    private final AnnouncementService.Displaying displaying = myAnnouncementService.new Displaying();
    private final AnnouncementService.Adding adding = myAnnouncementService.new Adding();
    private final AnnouncementEditionService announcementEditionService = new AnnouncementEditionService();

    public void display() {
        System.out.println("----------------------------------------------------");
        System.out.println("&&&&&&&&&&&--->> Work & Fun Service <<---&&&&&&&&&&&");
        System.out.println("----------------------------------------------------");
        System.out.println("Wybierz jedną z opcji wprowadzając odpowiednią cyfrę");
        System.out.println("1 - Wyszukaj ogłoszenie");
        System.out.println("2 - Wyświetl ogłoszenia");
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
                System.out.println("Wybrałes 1 - Wyszukaj ogłoszenie");
                break;
            case 2:
                System.out.println("Wybrałes 2 - Wyświetl ogłoszenia");
                try {
                    displaying.displayAllAnnouncements();
                } catch (ReturnToMenuException returnToMenuException) {
                    System.out.println(returnToMenuException.getMessage());
                }
                break;
            case 3:
                System.out.println("Wybrałes 3 - Dodaj ogłoszenie z oferowaną uslugą");
                adding.addAnnouncement(true);
                break;
            case 4:
                System.out.println("Wybrałes 4 - Dodaj ogłoszenie z poszukiwaną uslugą");
                adding.addAnnouncement(false);
                break;
            case 5:
                System.out.println("Wybrałes 5 - Edytuj ogłoszenie");
                try {
                    announcementEditionService.editAnnouncement();
                } catch (ReturnToMenuException returnToMenuException) {
                    System.out.println(returnToMenuException.getMessage());
                }
                break;
            case 6:
                System.out.println("Wybrałes 6 - Usun ogłoszenie");
                try {
                    displaying.displayAndDeleteAnnouncement();
                } catch (ReturnToMenuException returnToMenuException) {
                    System.out.println(returnToMenuException.getMessage());
                }
                break;
            case 0:
                System.out.println("Wybrałes 0 - Zakończ program <<--- I'll Be Back !");
                System.exit(0);
            default: {
                System.out.println("Podałeś zły parametr. Spróbuj jeszcze raz:");
                getMenuChoice();
            }
        }
        display();
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
