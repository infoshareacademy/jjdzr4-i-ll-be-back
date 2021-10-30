package pl.infoshare.announcements;

import java.util.function.Consumer;

public class AnnouncementEditionService extends AnnouncementService {

    public void editAnnouncement() throws ReturnToMenuException {
        System.out.println("Podaj Id ogłoszenia do edycji lub 0 aby wrócić do Menu");
        long id = -1;
        while (id <= -1) {
            try {
                id = Long.parseLong(scanner.nextLine());
                if (id == 0) {
                    break;
                }
                Announcement announcementToEdit = announcementRepository.findById(id);
                if (announcementToEdit == null) {
                    System.out.printf("Nie ma ogłoszenia o id = { %s }. Podaj poprawny numer Id ogłoszenia lub 0 aby " +
                            "wrócić do Menu%n", id);
                    id = -1;
                } else {
                    Announcement announcementAfterEdit = editAnnouncement(announcementToEdit);
                    announcementRepository.update(announcementAfterEdit);
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Podaj poprawny numer Id ogłoszenia lub 0 aby wrócić do Menu");
            }
        }
    }

    private Announcement editAnnouncement(Announcement announcementToEdit) throws ReturnToMenuException {
        System.out.println("Podaj który element chcesz edytowac");
        printAnnouncementFields(announcementToEdit);
        String userInput = getInputFromUser("1 - Zapisz zmiany", "\\b([1-9]|1[0-4])\\b",
                "Nie ma pola o takim numerze, wprowadz poprawny numer pola do edycji");

        if (userInput == null) {
            throw new ReturnToMenuException("Wybrałes powrót do menu");
        }
        switch (Integer.parseInt(userInput)) {
            case 1:
                return announcementToEdit;
            case 2:
                setValueIfNotNull(selectServiceType(), announcementToEdit::setServiceType);
                editAnnouncement(announcementToEdit);
                break;
            case 3:
                setValueIfNotNull(selectVoivodeship(), announcementToEdit::setVoivodeship);
                editAnnouncement(announcementToEdit);
                break;
            case 4:
                setValueIfNotNull(selectCity(), announcementToEdit::setCity);
                editAnnouncement(announcementToEdit);
                break;
            case 5:
                setValueIfNotNull(selectCityDistrict(), announcementToEdit::setCityDistrict);
                editAnnouncement(announcementToEdit);
                break;
            case 6:
                setValueIfNotNull(selectUnitName(), announcementToEdit::setUnit);
                editAnnouncement(announcementToEdit);
                break;
            case 7:
                setValueIfNotNull(selectNameOfAdvertiser(), announcementToEdit::setNameOfAdvertiser);
                editAnnouncement(announcementToEdit);
                break;
            case 8:
                setValueIfNotNull(selectPhoneNumber(), announcementToEdit::setPhoneNumber);
                editAnnouncement(announcementToEdit);
                break;
            case 9:
                setValueIfNotNull(selectEmail(), announcementToEdit::setEmail);
                editAnnouncement(announcementToEdit);
                break;
            case 10:
                setValueIfNotNull(inputDescription(), announcementToEdit::setDescription);
                editAnnouncement(announcementToEdit);
                break;
            case 11:
                setValueIfNotNull(selectPrice(), announcementToEdit::setPrice);
                editAnnouncement(announcementToEdit);
                break;
            case 12:
                setValueIfNotNull(selectKindOfPrice(), announcementToEdit::setPriceNegotiable);
                editAnnouncement(announcementToEdit);
                break;
            case 13:
                setValueIfNotNull(selectPriceAdditionalComment(), announcementToEdit::setPriceAdditionComment);
                editAnnouncement(announcementToEdit);
                break;
            case 14:
                setValueIfNotNull(inputHeader(), announcementToEdit::setHeader);
                editAnnouncement(announcementToEdit);
                break;
        }
        return announcementToEdit;
    }

    private void printAnnouncementFields(Announcement announcement) {
        System.out.println("2 -  Typ usługi ----------------------> " + announcement.getServiceType());
        System.out.println("3 -  Województwo ---------------------> " + announcement.getVoivodeship());
        System.out.println("4 -  Miasto --------------------------> " + announcement.getCity());
        System.out.println("5 -  Dzielnica -----------------------> " + announcement.getCityDistrict());
        System.out.println("6 -  Osiedle -------------------------> " + announcement.getUnit());
        System.out.println("7 -  Imię / Nick ---------------------> " + announcement.getNameOfAdvertiser());
        System.out.println("8 -  Numer telefonu ------------------> " + announcement.getPhoneNumber());
        System.out.println("9 -  Email ---------------------------> " + announcement.getEmail());
        System.out.println("10 - Opis ogłoszenia -----------------> " + announcement.getDescription());
        System.out.println("11 - Cena ----------------------------> " + announcement.getPrice());
        System.out.println("12 - Czy cena do negocjacji ----------> " + announcement.getIsPriceNegotiable());
        System.out.println("13 - Dodatkowy komentarz do ceny -----> " + announcement.getPriceAdditionComment());
        System.out.println("14 - Tytuł ogłoszenia ----------------> " + announcement.getHeader());
    }

    private <T> void setValueIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}