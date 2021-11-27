package pl.infoshare.announcements;

public class AnnouncementDeleteService extends AnnouncementService {

    public void displayAndDeleteAnnouncement() throws ReturnToMenuException {
        displayAllAnnouncements();
        Announcement announcementToDelete = askUserForAnnouncement("Podaj Id ogłoszenia, które chcesz " +
                "usunąć, lub 0 aby wrócić do Menu");
        announcementRepository.delete(announcementToDelete);
        System.out.println("Ogłoszenie usunięto");
        displayAndDeleteAnnouncement();
    }
}
