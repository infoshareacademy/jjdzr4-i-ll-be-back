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
    private String phoneNumber;

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
        //przypis mailu
        String selectedEmail = selectEmailForAnnouncement();
        if (selectedEmail == null) {
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
        System.out.println("______________________________");
        String selectedCity = scanner.nextLine();
        if (selectedCity.equals("0")) {
            return null;
        }
        return selectedCity;
    }

    private String selectPhoneNumberForAnnouncement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz polski numer telefonu, do kontaktu w sprawie ogłoszenia, w formacie: XXXXXXXXX ");
        System.out.println(BREAKANDCLOSE);
        System.out.println("______________________________");
        String selectedPhoneNumber = scanner.nextLine();
        if (selectedPhoneNumber.equals("0")) {
            return null;
        } else if (!validateSelectedPhoneNumber(selectedPhoneNumber)) {
            System.out.println("Błąd w numerze telefonu. Spróbuj jeszcze raz.");
            selectPhoneNumberForAnnouncement();
        }
        return selectedPhoneNumber;
    }

    private boolean validateSelectedPhoneNumber(String userInputPhoneNumber) {
        Pattern ptrn = Pattern.compile("(\\+48)?\\d{9}");
        Matcher match = ptrn.matcher(userInputPhoneNumber);
        return match.matches();
    }

    private String selectEmailForAnnouncement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz mail do kontaktu, w sprawie ogłoszenia:");
        System.out.println(BREAKANDCLOSE);
        System.out.println("______________________________");
        String selectedEmail = scanner.nextLine();
        if (selectedEmail.equals("0")) {
            return null;
        } else if (!validateSelectedEmail(selectedEmail)) {
            System.out.println("Niepoprawnie wprowadzony mail. Spróbuj jeszcze raz.");
            selectEmailForAnnouncement();
        }
        return selectedEmail;
    }

    private boolean validateSelectedEmail(String userInputEmail) {
        Pattern ptrn = Pattern.compile(".+@.+\\..+");
        Matcher match = ptrn.matcher(userInputEmail);
        return match.matches();
    }

}