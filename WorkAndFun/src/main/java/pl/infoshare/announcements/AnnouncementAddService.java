package pl.infoshare.announcements;

import pl.infoshare.FileActions;
import pl.infoshare.Main;
import pl.infoshare.TechnicalMethods;
import pl.infoshare.announcements.Categories.HierarchyOfCategory;
import pl.infoshare.announcements.Categories.HierarchyOfCategoryDisplay;
import pl.infoshare.announcements.Categories.ServiceType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class AnnouncementAddService extends AnnouncementService {

    public static final HierarchyOfCategory hierarchyOfCategory = new HierarchyOfCategory().initializeCategories();

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
        ServiceType selectedServiceType = (ServiceType) userInputCheck(HierarchyOfCategoryDisplay.chooseAndAssignHierarchy(hierarchyOfCategory));
        //input header
        String inputtedHeader = (String) userInputCheck(inputHeader());
        //input description
        String inputtedDescription = (String) userInputCheck(inputDescription());
        //input price
        String inputtedPrice = (String) userInputCheck(selectPrice());
        // input is price negotiable
        Boolean isPriceNegotiableBoolean;
        if (inputtedPrice.equals("do ustalenia indywidualnie") || inputtedPrice.equals("0")) {
            isPriceNegotiableBoolean = false;
        } else {
            isPriceNegotiableBoolean = (Boolean) userInputCheck(selectKindOfPrice());
        }
        // input additional comment to price
        String inputtedPriceAdditionalComment = (String) userInputCheck(selectPriceAdditionalComment());
        // get date of creating announcement
        LocalDateTime dateOfAnnouncementCreating = LocalDateTime.now();
        //generate ID based on existing announcements
        ArrayList<Announcement> baseOfAnnouncements = FileActions.loadAnnouncementsFromFile(Main.ANNOUNCEMENTS_FILE_PATH);
        Collections.sort(baseOfAnnouncements);
        long generatedID = baseOfAnnouncements.get(baseOfAnnouncements.size() - 1).getId() + 1;
        //TODO: add clientId assigning functionality to announcement; change code below
        Integer selectedClientId = null;

        System.out.println("--------------To już prawie koniec...-----------------");

        Announcement newAnnouncement = new Announcement(type, inputtedHeader, generatedID, selectedServiceType, selectedCity,
                selectedCityDistrict, selectedUnit, inputtedPrice, selectedVoivodeship, dateOfAnnouncementCreating,
                selectedNameOfAdvertiser, selectedEmail, isPriceNegotiableBoolean, inputtedDescription, selectedPhone,
                inputtedPriceAdditionalComment, selectedClientId);

        showAnnouncementDetails(newAnnouncement);
        userInputCheck(ifWantToSaveAnnouncement());

        FileActions.addAnnouncementsToFile(newAnnouncement, Main.ANNOUNCEMENTS_FILE_PATH);
        System.out.println("--Ogłoszenie pomyślnie zapisane! Teraz wrócisz do menu głównego--");
        TechnicalMethods.makeDelay(1500);
    }
}