package pl.infoshare.announcements;

public class AnnouncementDisplayService extends AnnouncementService {

    public void displayAnnouncements() throws ReturnToMenuException {
        displayAllAnnouncements();
        chooseAndShowAnnouncementDetails();
        System.out.println("Wybierz 0 aby wrócić do menu wyszukiwania ogłoszeń lub Enter, żeby wrócić do listy ogłoszeń:");
        scanner.nextLine();
        displayAllAnnouncements();
    }
}
