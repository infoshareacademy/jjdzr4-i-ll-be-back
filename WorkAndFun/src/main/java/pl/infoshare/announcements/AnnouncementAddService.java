package pl.infoshare.announcements;

import pl.infoshare.FileActions;
import pl.infoshare.Main;
import pl.infoshare.TechnicalMethods;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AnnouncementAddService extends AnnouncementService {

    public void addAnnouncement(Type type) {
        try {
            addAnnouncementService(type);
        } catch (ReturnToMenuException returnToMenuException) {
            System.out.println(returnToMenuException.getMessage());
        }
    }

    private void addAnnouncementService(Type type) throws ReturnToMenuException {
        //assigning voivodeship
        Voivodeship selectedVoivodeship = (Voivodeship) userInputCheck(selectVoivodeship());
        //assigning city
        String selectedCity = (String) userInputCheck(selectCity());
        //assigning city district
        String selectedCityDistrict = (String) userInputCheck(selectCityDistrict());
        //assigning city unit (osiedle)
        String selectedUnit = (String) userInputCheck(selectUnitName());
        //assigning phone number
        String selectedPhone = (String) userInputCheck(selectPhoneNumber());
        //assigning email
        String selectedEmail = (String) userInputCheck(selectEmail());
        //assigning name/nickname of advertiser
        String selectedNameOfAdvertiser = (String) userInputCheck(selectNameOfAdvertiser());
        //assigning service type
        ServiceType selectedServiceType = (ServiceType) userInputCheck(selectServiceType());
        //input header
        String inputtedHeader = (String) userInputCheck(inputHeader());
        //input description
        String inputtedDescription = (String) userInputCheck(inputDescription());
        //input price
        String inputtedPrice = (String) userInputCheck(selectPrice());
        // input is price negotiable
        Boolean isPriceNegotiableBoolean;
        if (inputtedPrice.equals("do ustalenia indywidualnie") || inputtedPrice.equals("0")) {
            isPriceNegotiableBoolean = null;
        } else {
            isPriceNegotiableBoolean = (Boolean) userInputCheck(selectKindOfPrice());
        }
        // input additional comment to price
        String inputtedPriceAdditionalComment = (String) userInputCheck(selectPriceAdditionalComment());
        // get date of creating announcement
        LocalDateTime dateOfAnnouncementCreating = LocalDateTime.now();
        //generate ID based on existing announcements
        ArrayList<String[]> baseOfAnnouncementsStrings = FileActions.makeArrayFromFile(Main.ANNOUNCEMENTS_FILE_PATH);
        long generatedID = Long.parseLong(baseOfAnnouncementsStrings.get(baseOfAnnouncementsStrings.size() - 1)[0]) + 1;
        //TODO: add clientId assigning functionality to announcement; change code below
        Integer selectedClientId = null;

        System.out.println("--------------To już prawie koniec...-----------------");

        Announcement newAnnouncement = new Announcement(type, inputtedHeader, generatedID, selectedServiceType, selectedCity,
                selectedCityDistrict, selectedUnit, inputtedPrice, selectedVoivodeship, dateOfAnnouncementCreating,
                selectedNameOfAdvertiser, selectedEmail, isPriceNegotiableBoolean, inputtedDescription, selectedPhone,
                inputtedPriceAdditionalComment, selectedClientId);

        showAnnouncementDetails(newAnnouncement);
        userInputCheck(ifWantToSaveAnnouncement());

        FileActions.writeToFile(Main.ANNOUNCEMENTS_FILE_PATH, true, String.valueOf(newAnnouncement.getId()),
                String.valueOf(newAnnouncement.getType()), String.valueOf(newAnnouncement.getServiceType()),
                String.valueOf(newAnnouncement.getVoivodeship()), String.valueOf(newAnnouncement.getCity()),
                newAnnouncement.getCityDistrict(), newAnnouncement.getUnit(), newAnnouncement.getNameOfAdvertiser(),
                newAnnouncement.getPhoneNumber(), newAnnouncement.getEmail(), newAnnouncement.getDescription(),
                newAnnouncement.getPrice(), String.valueOf(newAnnouncement.getIsPriceNegotiable()),
                newAnnouncement.getPriceAdditionComment(), String.valueOf(newAnnouncement.getDate()),
                String.valueOf(newAnnouncement.getClientId()), String.valueOf(newAnnouncement.getHeader()));
        System.out.println("--Ogłoszenie pomyślnie zapisane! Teraz wrócisz do menu głównego--");
        TechnicalMethods.makeDelay(1500);
    }
}