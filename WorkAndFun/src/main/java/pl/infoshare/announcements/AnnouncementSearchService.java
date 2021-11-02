package pl.infoshare.announcements;

import java.util.List;
import java.util.stream.Collectors;

public class AnnouncementSearchService extends AnnouncementService {

    public void displaySearchMenu() throws ReturnToMenuException {
        System.out.println("1. Wyszukaj oferty na wykonanie usług.");
        System.out.println("2. Wyszukaj oferty na zapotrzebowanie usług.");
        String userInput = scanner.nextLine();
        switch (userInput) {
            case "1":
                System.out.println("Wybrałeś wyszukiwanie wykonywania usług.");
                searchAnnoucement(true);
                break;
            case "2":
                System.out.println("Wybrałeś wyszukiwanie zapotrzebowania usług.");
                searchAnnoucement(false);
                break;
            default: {
                System.out.println("Podałeś zły parametr!");
                displaySearchMenu();
                break;
            }
        }
    }

    public void searchAnnoucement(boolean isOffer) throws ReturnToMenuException {
        var announcementList = announcementRepository.findAll();
        String userInput = getInputFromUser("Wpisz co chcesz wyszukać " +
                        "1 - Lokalizacja 2 - kategoria 3 - opis:"
                , "[1-3]{1}", "Nie ma pola o takim numerze, wprowadz poprawny numer pola do edycji");

        if (userInput == null) {
            throw new ReturnToMenuException("Wybrałes powrót do menu");
        }
        switch (Integer.parseInt(userInput)) {
            case 1:
                System.out.println("Podaj lokalizację ogłoszenia");
                var announcementsByLocalisation = searchByLocalisation(announcementList, isOffer);
                if (!announcementsByLocalisation.isEmpty()) {
                    for (Announcement announcement : announcementsByLocalisation) {
                        showAnnouncement(announcement);
                    }
                    chooseAndShowAnnouncementDetails();
                    System.out.println("Powrót do menu wyszukiwania");
                } else {
                    System.out.println("brak ogłoszeń");
                }
                searchAnnoucement(isOffer);
                break;
            case 2:
                System.out.println("Wybierz kategorię ogłoszenia");
                var announcementsByCategory = searchByCategory(announcementList, isOffer);
                if (!announcementsByCategory.isEmpty()) {
                    for (Announcement announcement : announcementsByCategory) {
                        showAnnouncement(announcement);
                    }
                    chooseAndShowAnnouncementDetails();
                    System.out.println("Powrót do menu wyszukiwania");
                } else {
                    System.out.println("brak ogłoszeń");
                }
                searchAnnoucement(isOffer);
                break;
            case 3:
                System.out.println("Wyszukaj po opisie");
                var announcementsByDescription = searchByDescription(announcementList, isOffer);
                if (!announcementsByDescription.isEmpty()) {
                    for (Announcement announcement : announcementsByDescription) {
                        showAnnouncement(announcement);
                    }
                    chooseAndShowAnnouncementDetails();
                    System.out.println("Powrót do menu wyszukiwania");
                } else {
                    System.out.println("brak ogłoszeń");
                }
                searchAnnoucement(isOffer);
                break;
        }
    }

    private List<Announcement> searchByDescription(List<Announcement> announcementList, boolean isOffer) {
        String input = scanner.nextLine();
        return announcementList.stream().filter(announcement -> announcement.getIsOffer() == isOffer &
                announcement.getDescription().equalsIgnoreCase(input)).collect(Collectors.toList());
    }

    private List<Announcement> searchByLocalisation(List<Announcement> announcementList, boolean isOffer) {
        String input = scanner.nextLine();
        return announcementList.stream()
                .filter(announcement -> announcement.getIsOffer() == isOffer &
                        (announcement.getVoivodeship().toString().equalsIgnoreCase(input) || announcement.getCity().equalsIgnoreCase(input)
                                || announcement.getCityDistrict().equalsIgnoreCase(input) ||
                                announcement.getUnit().equalsIgnoreCase(input))).collect(Collectors.toList());
    }

    private List<Announcement> searchByCategory(List<Announcement> announcementList, boolean isOffer) {
        String input = scanner.nextLine();
        return announcementList.stream().filter(announcement -> announcement.getIsOffer() == isOffer &
                announcement.getServiceType().toString().equals(input)).collect(Collectors.toList());
    }
}

