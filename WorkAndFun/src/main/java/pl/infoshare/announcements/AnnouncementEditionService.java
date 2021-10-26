package pl.infoshare.announcements;

import java.util.function.Consumer;

public class AnnouncementEditionService extends AnnouncementService {

    public void editAnnouncement() {
        System.out.println("podaj id ogloszenia do edycji");
        long id = Long.parseLong(scanner.nextLine());
        Announcement announcementToEdit = announcementRepository.findById(id);
        Announcement announcementAfterEdit = editAnnouncement(announcementToEdit);
        announcementRepository.update(announcementAfterEdit);
    }

    private Announcement editAnnouncement(Announcement announcementToEdit) {
        System.out.println("podaj który element chcesz edytowac");
        printAnnouncementFields(announcementToEdit);
        System.out.println("chcesz zapisac zmiany wprowadz 0");
        int userInput = Integer.parseInt(scanner.nextLine());

        if (userInput == 0) {
            return announcementToEdit;
        }
        if (userInput == 1) {
            setValueIfNotNull(selectCity(), announcementToEdit::setCity);
        }
        if (userInput == 2) {
            setValueIfNotNull(selectEmail(), announcementToEdit::setEmail);
        }
        if (userInput == 3) {
            setValueIfNotNull(inputDescription(), announcementToEdit::setDescription);
        }
        if (userInput == 4) {
            setValueIfNotNull(selectKindOfPrice(), announcementToEdit::setPriceNegotiable);
        }
        editAnnouncement(announcementToEdit);
        return announcementToEdit;
    }

    private void printAnnouncementFields(Announcement announcement) {
        System.out.println("1 - " + announcement.getCity());
        System.out.println("2 - " + announcement.getEmail());
        System.out.println("3 - " + announcement.getDescription());
        System.out.println("4 - " + announcement.getIsPriceNegotiable());
    }

    private <T> void setValueIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}