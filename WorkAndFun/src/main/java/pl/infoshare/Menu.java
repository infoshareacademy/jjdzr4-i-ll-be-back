package pl.infoshare;

import pl.infoshare.announcements.*;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    protected final AnnouncementDisplayService announcementDisplayService = new AnnouncementDisplayService();
    private final AnnouncementEditionService announcementEditionService = new AnnouncementEditionService();
    private final AnnouncementSearchService announcementSearchService = new AnnouncementSearchService();
    private final AnnouncementAddService announcementAddService = new AnnouncementAddService();
    private final AnnouncementDeleteService announcementDeleteService = new AnnouncementDeleteService();

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
                try {
                    announcementSearchService.displaySearchMenu();
                } catch (ReturnToMenuException returnToMenuException) {
                    System.out.println(returnToMenuException.getMessage());
                }
                break;
            case 2:
                System.out.println("Wybrałes 2 - Wyświetl ogłoszenia");
                try {
                    announcementDisplayService.displayAnnouncements();
                } catch (ReturnToMenuException returnToMenuException) {
                    System.out.println(returnToMenuException.getMessage());
                }
                break;
            case 3:
                System.out.println("Wybrałes 3 - Dodaj ogłoszenie z oferowaną uslugą");
                announcementAddService.addAnnouncement(Type.SERVICE_OFFER);
                break;
            case 4:
                System.out.println("Wybrałes 4 - Dodaj ogłoszenie z poszukiwaną uslugą");
                announcementAddService.addAnnouncement(Type.SERVICE_DEMAND);
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
                    announcementDeleteService.displayAndDeleteAnnouncement();
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
