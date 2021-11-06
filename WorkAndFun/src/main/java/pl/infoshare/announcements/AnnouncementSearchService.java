package pl.infoshare.announcements;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AnnouncementSearchService extends AnnouncementService {

    public void displaySearchMenu() throws ReturnToMenuException {
        System.out.println("1. Wyszukaj oferty na wykonanie usług.");
        System.out.println("2. Wyszukaj oferty na zapotrzebowanie usług.");
        System.out.println("0. Powrót do menu");
        String userInput = scanner.nextLine();
        switch (userInput) {
            case "1":
                System.out.println("Wybrałeś wyszukiwanie wykonywania usług.");
                searchAnnouncement(Type.SERVICE_OFFER);
                break;
            case "2":
                System.out.println("Wybrałeś wyszukiwanie zapotrzebowania usług.");
                searchAnnouncement(Type.SERVICE_DEMAND);
                break;
            case "0":
                break;
            default: {
                System.out.println("Podałeś zły parametr!");
                displaySearchMenu();
                break;
            }
        }
    }

    public void searchAnnouncement(Type type) throws ReturnToMenuException {
        var announcementList = announcementRepository.findAll();
        String userInput = getInputFromUser("Wpisz co chcesz wyszukać " +
                        "1 - Lokalizacja 2 - Kategoria 3 - Opis :"
                , "[1-3]{1}", "Nie ma pola o takim numerze, wprowadz poprawny numer pola do edycji");

        if (userInput == null) {
            throw new ReturnToMenuException("Wybrałes powrót do menu");
        }
        switch (Integer.parseInt(userInput)) {
            case 1:
                System.out.println("Podaj lokalizację ogłoszenia");
                var announcementsByLocation = searchByLocation(announcementList, type);
                if (!announcementsByLocation.isEmpty()) {
                    for (Announcement announcement : announcementsByLocation) {
                        showAnnouncement(announcement);
                    }
                    chooseAndShowAnnouncementDetails();
                    System.out.println("Powrót do menu wyszukiwania");
                } else {
                    System.out.println("brak ogłoszeń");
                }
                searchAnnouncement(type);
                break;
            case 2:
                System.out.println("Wybierz kategorię ogłoszenia");
                var announcementsByCategory = searchByCategory(announcementList, type);
                if (!announcementsByCategory.isEmpty()) {
                    for (Announcement announcement : announcementsByCategory) {
                        showAnnouncement(announcement);
                    }
                    chooseAndShowAnnouncementDetails();
                    System.out.println("Powrót do menu wyszukiwania");
                } else {
                    System.out.println("brak ogłoszeń");
                }
                searchAnnouncement(type);
                break;
            case 3:
                System.out.println("Wyszukaj po opisie");
                var announcementsByDescription = searchByDescription(announcementList, type);
                if (!announcementsByDescription.isEmpty()) {
                    for (Announcement announcement : announcementsByDescription) {
                        showAnnouncement(announcement);
                    }
                    chooseAndShowAnnouncementDetails();
                    System.out.println("Powrót do menu wyszukiwania");
                } else {
                    System.out.println("brak ogłoszeń");
                }
                searchAnnouncement(type);
                break;
        }
    }

    private List<Announcement> searchByDescription(List<Announcement> announcementList, Type type) {
        String input = scanner.nextLine();
        return announcementList.stream().filter(announcement -> announcement.getType().equals(type) &
                announcement.getDescription().toLowerCase(Locale.ROOT).contains(input.toLowerCase(Locale.ROOT))).collect(Collectors.toList());
    }

    private List<Announcement> searchByLocation(List<Announcement> announcementList, Type type) {
        String input = scanner.nextLine();
        return announcementList.stream()
                .filter(announcement -> announcement.getType().equals(type) &
                        (announcement.getVoivodeship().toString().toLowerCase(Locale.ROOT).contains(input.toLowerCase(Locale.ROOT))
                                || announcement.getCity().toLowerCase(Locale.ROOT).contains(input.toLowerCase(Locale.ROOT))
                                || announcement.getCityDistrict().toLowerCase(Locale.ROOT).contains(input.toLowerCase(Locale.ROOT)) ||
                                announcement.getUnit().toLowerCase(Locale.ROOT).contains(input.toLowerCase(Locale.ROOT)))).collect(Collectors.toList());
    }

    private List<Announcement> searchByCategory(List<Announcement> announcementList, Type type) {
        String input = scanner.nextLine();
        return announcementList.stream().filter(announcement -> announcement.getType().equals(type) &
                announcement.getServiceType().getServiceTypeName().toLowerCase(Locale.ROOT).contains(input.toLowerCase(Locale.ROOT))).collect(Collectors.toList());
    }
}