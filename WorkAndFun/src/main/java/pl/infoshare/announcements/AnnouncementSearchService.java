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
                searchAnnoucement(OfferType.SERVICE_OFFER);
                break;
            case "2":
                System.out.println("Wybrałeś wyszukiwanie zapotrzebowania usług.");
                searchAnnoucement(OfferType.SERVICE_DEMAND);
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

    public void searchAnnoucement(OfferType offerType) throws ReturnToMenuException {
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
                var announcementsByLocalisation = searchByLocalisation(announcementList, offerType);
                if (!announcementsByLocalisation.isEmpty()) {
                    for (Announcement announcement : announcementsByLocalisation) {
                        showAnnouncement(announcement);
                    }
                    chooseAndShowAnnouncementDetails();
                    System.out.println("Powrót do menu wyszukiwania");
                } else {
                    System.out.println("brak ogłoszeń");
                }
                searchAnnoucement(offerType);
                break;
            case 2:
                System.out.println("Wybierz kategorię ogłoszenia");
                var announcementsByCategory = searchByCategory(announcementList, offerType);
                if (!announcementsByCategory.isEmpty()) {
                    for (Announcement announcement : announcementsByCategory) {
                        showAnnouncement(announcement);
                    }
                    chooseAndShowAnnouncementDetails();
                    System.out.println("Powrót do menu wyszukiwania");
                } else {
                    System.out.println("brak ogłoszeń");
                }
                searchAnnoucement(offerType);
                break;
            case 3:
                System.out.println("Wyszukaj po opisie");
                var announcementsByDescription = searchByDescription(announcementList, offerType);
                if (!announcementsByDescription.isEmpty()) {
                    for (Announcement announcement : announcementsByDescription) {
                        showAnnouncement(announcement);
                    }
                    chooseAndShowAnnouncementDetails();
                    System.out.println("Powrót do menu wyszukiwania");
                } else {
                    System.out.println("brak ogłoszeń");
                }
                searchAnnoucement(offerType);
                break;
        }
    }

    private List<Announcement> searchByDescription(List<Announcement> announcementList, OfferType offerType) {
        String input = scanner.nextLine();
        return announcementList.stream().filter(announcement -> announcement.getOfferType().equals(offerType) &
                announcement.getDescription().toLowerCase(Locale.ROOT).contains(input.toLowerCase(Locale.ROOT))).collect(Collectors.toList());
    }

    private List<Announcement> searchByLocalisation(List<Announcement> announcementList, OfferType offerType) {
        String input = scanner.nextLine();
        return announcementList.stream()
                .filter(announcement -> announcement.getOfferType().equals(offerType) &
                        (announcement.getVoivodeship().toString().toLowerCase(Locale.ROOT).contains(input.toLowerCase(Locale.ROOT))
                                || announcement.getCity().toLowerCase(Locale.ROOT).contains(input.toLowerCase(Locale.ROOT))
                                || announcement.getCityDistrict().toLowerCase(Locale.ROOT).contains(input.toLowerCase(Locale.ROOT)) ||
                                announcement.getUnit().toLowerCase(Locale.ROOT).contains(input.toLowerCase(Locale.ROOT)))).collect(Collectors.toList());
    }

    private List<Announcement> searchByCategory(List<Announcement> announcementList, OfferType offerType) {
        String input = scanner.nextLine();
        return announcementList.stream().filter(announcement -> announcement.getOfferType().equals(offerType) &
                announcement.getServiceType().toString().toLowerCase(Locale.ROOT).contains(input.toLowerCase(Locale.ROOT))).collect(Collectors.toList());
    }
}

