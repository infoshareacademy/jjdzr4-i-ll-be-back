package pl.infoshare;


//import java.time.LocalDate;


import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Announcement {
    private static final String BREAKANDCLOSE = "[0 - Przerwij i zamknij dodawanie ogłoszenia]";

    private boolean isService; //true=usługa/false=zapotrzebowanie
    private int adID;
    private String serviceType; //enum?
    private String city;
    private int postalCode;
    private String street;
    private String price;
    private User client;
    private Voivodeship voivodeship; //typ do zmiany na ENUM/WYWAŁKI
    private LocalDate date; //local date time - IDE pruje się o zmiany w pom.xml, do obadania
    private String nameOfAdvertiser;
    private String mail;
    private int lowerPriceLimit;
    private int higherPriceLimit;
    private String description;
    private String phoneNumberToContact;

    public void setPrice(int lowerPriceLimit, int higherPriceLimit) {
        this.price = lowerPriceLimit + " - " + higherPriceLimit + "zł";
    }

    public void addAnnouncementOfferService() {

        //przypis wojewodzstwa
        Voivodeship selectedVoivodeship = selectVoivodeshipForAnnouncement();
        if (selectedVoivodeship == null) {
            return;
        }

        //przypis miejscowosci
        String selectedCity = selectCityForAnnouncement();
        if (selectedCity == null) {
            return;
        }

        //przypis telefonu
        String selectedPhone = selectPhoneNumberForAnnouncement();
        if (selectedPhone == null) {
            return;
        }



    }

    private Voivodeship selectVoivodeshipForAnnouncement() {
        System.out.println("Wybierz województwo, w którym proponujesz usługę, z listy. Wpisz odpowiedni numer:");

        TechnicalMethods.makeDelay(500);
        System.out.println("______________________________");
        for (int i = 0; i < Voivodeship.values().length; i++) {
            System.out.println(Voivodeship.values()[i].getSequentialNumber() + " - " + Voivodeship.values()[i].getVoivodeshipName());
        }
        System.out.println(BREAKANDCLOSE);
        System.out.println("______________________________");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if (userInput.equals("0")) {
            System.out.println("Wybrałeś(-aś) 0 - przerwanie dodawania ogłoszenia...");
            return null;
        }

        return validateAndAssignVoivodeship(userInput);
    }

    private Voivodeship validateAndAssignVoivodeship(String userInput) {
        Voivodeship voivodeshipToAssign = null;
        for (Voivodeship i : Voivodeship.values()) {
            if (i.getSequentialNumber().equals(userInput)) {
                voivodeshipToAssign = i;
                break;
            }
        }
        if (voivodeshipToAssign == null) {
            System.out.println("Wprowadzono niepoprawne dane. Spróbuj jeszcze raz.");
            addAnnouncementOfferService();
        }
        return voivodeshipToAssign;
    }

    private String selectCityForAnnouncement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz miejscowość, w której proponujesz usługę. Na przykład: Warszawa");
        System.out.println(BREAKANDCLOSE);
        String selectedCity = scanner.nextLine();
        if (selectedCity.equals("0")) {
            return null;
        }
        return selectedCity;
    }

    private String selectPhoneNumberForAnnouncement() {
        Scanner scannerPhoneNumberAsking = new Scanner(System.in);
        System.out.println("Wpisz polski numer telefonu, do kontaktu w sprawie ogłoszenia, w formacie: XXXXXXXXX ");
        System.out.println(BREAKANDCLOSE);
        String selectedPhoneNumberToContact = scannerPhoneNumberAsking.nextLine();
        if (selectedPhoneNumberToContact.equals("0")) {
            return null;
        } else if (!validateSelectedPhoneNumber(selectedPhoneNumberToContact)) {
            System.out.println("Błąd w numerze telefonu. Spróbuj jeszcze raz.");
            selectPhoneNumberForAnnouncement();
        }
        return selectedPhoneNumberToContact;
    }

    private boolean validateSelectedPhoneNumber(String userInput) {
        Pattern ptrn = Pattern.compile("(\\+48)?\\d{9}");
        Matcher match = ptrn.matcher(userInput);
        return match.matches();
    }


}