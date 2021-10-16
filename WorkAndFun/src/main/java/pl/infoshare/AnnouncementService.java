package pl.infoshare;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnnouncementService {

    private static final String BREAK_AND_CLOSE = "0 - Przerwij i zamknij dodawanie ogłoszenia";
    private static final Scanner scanner = new Scanner(System.in);

    public void addAnnouncement(boolean isOffer) {


        //assigning voivodeship
        Voivodeship selectedVoivodeship = selectVoivodeship();
        if (selectedVoivodeship == null) {
            return;
        }
        //assigning city
        String selectedCity = selectCity();
        if (selectedCity == null) {
            return;
        }
        //assigning city district
        String selectedCityDistrict = selectCityDistrict();
        if (selectedCityDistrict == null) {
            return;
        }
        //assigning city unit (osiedle)
        String selectedUnit = selectUnitName();
        if (selectedUnit == null) {
            return;
        }
        //assigning phone number
        String selectedPhone = selectPhoneNumber();
        if (selectedPhone == null) {
            return;
        }
        //assigning email
        String selectedEmail = selectEmail();
        if (selectedEmail == null) {
            return;
        }
        //assigning name/nickname of advertiser
        String selectedNameOfAdvertiser = selectNameOfAdvertiser();
        if (selectedNameOfAdvertiser == null) {
            return;
        }
        //assigning service type
        ServiceTypes selectedServiceType = selectServiceType();
        if (selectedServiceType == null) {
            return;
        }
        //input description
        String inputtedDescription = inputDescription();
        if (inputtedDescription == null) {
            return;
        }
        //input price
        String inputtedPrice = selectPrice();
        if (inputtedPrice == null) {
            return;
        }
        // input is price negotiable
        String isPriceNegotiable = selectKindOfPrice();
        if (isPriceNegotiable == null) {
            return;
        }
        boolean isPriceNegotiableBoolean = convertStringAnswerToBoolean(isPriceNegotiable);
        // input additional comment to price
        String inputtedPriceAdditionalComment = selectPriceAdditionalComment();
        if (inputtedPriceAdditionalComment == null) {
            return;
        }
        // get date of creating announcement
        LocalDateTime dateOfAnnouncementCreating = LocalDateTime.now();
        //generate ID based on existing announcements
        ArrayList<String[]> baseOfAnnouncements = FileActions.makeArrayFromFile(Main.ANNOUNCEMENTS_FILE_PATH);
        long generatedID = Long.valueOf(baseOfAnnouncements.get(baseOfAnnouncements.size() - 1)[0]) + 1;

        System.out.println("--------------To już prawie koniec...-----------------");
        if (ifWantToSaveAnnouncement("Czy chcesz dodać ogłoszenie?\n[1 - Dodaj ogłoszenie]", "Wybierz 1 lub 0") == null) {
            return;
        }

        Announcement newAnnouncement = new Announcement(isOffer, generatedID, selectedServiceType, selectedCity, selectedCityDistrict, selectedUnit, inputtedPrice, selectedVoivodeship, dateOfAnnouncementCreating, selectedNameOfAdvertiser, selectedEmail, isPriceNegotiableBoolean, inputtedDescription, selectedPhone, inputtedPriceAdditionalComment);
        FileActions.writeToFile(Main.ANNOUNCEMENTS_FILE_PATH, String.valueOf(newAnnouncement.getID()), String.valueOf(newAnnouncement.getIsOffer()), String.valueOf(newAnnouncement.getServiceType()), String.valueOf(newAnnouncement.getVoivodeship()), String.valueOf(newAnnouncement.getCity()), newAnnouncement.getCityDistrict(), newAnnouncement.getUnit(), newAnnouncement.getNameOfAdvertiser(), newAnnouncement.getPhoneNumber(), newAnnouncement.getEmail(), newAnnouncement.getDescription(), newAnnouncement.getPrice(), String.valueOf(newAnnouncement.getIsPriceNegotiable()), newAnnouncement.getPriceAdditionComment(), String.valueOf(newAnnouncement.getDate()), String.valueOf(newAnnouncement.getClient()));
        System.out.println("--Ogłoszenie pomyślnie zapisane! Teraz wrócisz do głównego menu--");
        TechnicalMethods.makeDelay(1500);
    }

    //turn String answer, like "T" or "N", into boolean type
    private boolean convertStringAnswerToBoolean(String isPriceNegotiable) {
        boolean isPriceNegotiableBoolean;
        if (isPriceNegotiable.equals("T")) {
            isPriceNegotiableBoolean = true;
        } else {
            isPriceNegotiableBoolean = false;
        }
        return isPriceNegotiableBoolean;
    }

    private String getInputFromUser(String messageForUser, String regex, String errorMessage) {
        String inputFromUser;

        System.out.println(messageForUser);
        System.out.println(BREAK_AND_CLOSE);
        System.out.println("______________________________");
        inputFromUser = scanner.nextLine();
        if (inputFromUser.equals("0")) {
            return null;
        } else if (!(isStringValid(inputFromUser, regex))) {
            System.out.println(errorMessage);
            inputFromUser = getInputFromUser(messageForUser, regex, errorMessage);
        }
        return inputFromUser;
    }

    /**
     * Ask user for answer "T" or "N". Also supports lower cases "t" and "n". Ask user again, if correct type of input wasn't found, displaying warning message
     *
     * @param messageForUser message (question), displays to user
     * @param errorMessage   warning message, displays to user in case of deviating from the correct answer
     * @return Character "T" or "N"
     */
    private Character getAnswerYesOrNoFromUser(String messageForUser, String errorMessage) {
        String answer = getInputFromUser(messageForUser, "[TNtn]{1}", "Niedopuszczalna odpowiedź. Wybierz \"T\" lub \"N\"");
        if (answer == null) {
            return null;
        } else {
            return answer.toUpperCase(Locale.ROOT).charAt(0);
        }
    }

    private Integer ifWantToSaveAnnouncement(String messageForUser, String errorMessage) {
        String answer = getInputFromUser(messageForUser, "[0-1]{1}", "Niedopuszczalna odpowiedź. Wybierz \"1\" lub \"0\"");
        if (answer == null) {
            return null;
        } else {
            return Integer.valueOf(answer);
        }
    }

    private Voivodeship selectVoivodeship() {
        System.out.println("Wybierz województwo, w którym proponujesz usługę, z listy. Wpisz odpowiedni numer:");
        System.out.println("______________________________");
        for (int i = 0; i < Voivodeship.values().length; i++) {
            System.out.println(Voivodeship.values()[i].getSequentialNumber() + " - " + Voivodeship.values()[i].getVoivodeshipName());
        }
        System.out.println(BREAK_AND_CLOSE);
        System.out.println("______________________________");

        String userInput = scanner.nextLine();
        if (userInput.equals("0")) {
            System.out.println("Wybrałeś(-aś) 0 - przerwanie dodawania ogłoszenia...");
            return null;
        }
        return validateAndAssignVoivodeship(userInput);
    }

    private String selectCity() {
        return getInputFromUser("Wpisz miejscowość, w której proponujesz usługę. Na przykład: Warszawa", "\\D+", "Źle wprowadzony typ danych. Podaj prawidłową miejscowość");
    }

    private String selectCityDistrict() {
        return getInputFromUser("Wpisz dzielnicę miasta, gdzie wykonujesz usługę, lub pozostaw pole pustym:", "\\D*", "Źle wprowadzony typ danych.");
    }

    private String selectUnitName() {
        return getInputFromUser("Wpisz nazwę osiedla, gdzie wykonujesz usługę, lub pozostaw pole pustym:", "\\D*", "Źle wprowadzony typ danych.");
    }

    private String selectPhoneNumber() {
        return getInputFromUser("Wpisz polski numer telefonu, do kontaktu w sprawie ogłoszenia, w formacie: XXXXXXXXX ", "(\\+48)?\\d{9}", "Błąd w numerze telefonu. Spróbuj jeszcze raz.");
    }

    private String selectEmail() {
        return getInputFromUser("Wpisz mail do kontaktu, w sprawie ogłoszenia:", "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-]+$", "Niepoprawnie wprowadzony mail. Spróbuj jeszcze raz.");
    }

    private String selectNameOfAdvertiser() {
        return getInputFromUser("Wpisz imię lub nick, które będzie wyświetlone w ogłoszeniu. Unikaj spacji.", "\\w{2,50}", "Zbyt długie/krótkie imie, lub wpisana spacja. Spróbuj jeszcze raz.");
    }

    private ServiceTypes selectServiceType() {
        System.out.println("Wybierz typ usługi, którą proponujesz, z listy, wpisując odpowiedni numer. Jeśli nie masz odpowiedniej kategorii na liście, wybierz \"Inne\":");
        System.out.println("______________________________");
        for (int i = 0; i < ServiceTypes.values().length; i++) {
            System.out.println(ServiceTypes.values()[i].getSequentialNumber() + " - " + ServiceTypes.values()[i].getServiceTypeName());
        }
        System.out.println(BREAK_AND_CLOSE);
        System.out.println("______________________________");

        String userInput = scanner.nextLine();
        if (userInput.equals("0")) {
            System.out.println("Wybrałeś(-aś) 0 - przerwanie dodawania ogłoszenia...");
            return null;
        }
        return validateAndAssignServiceType(userInput);
    }

    private String inputDescription() {
        String inputtedDescription = getInputFromUser("Wpisz treść ogłoszenia:", ".+", "Treść nie znaleziono. Ogłoszenie musi zawierać opis.");
        return inputtedDescription;
    }

    private String selectPrice() {
        Character ifWantToSetPrice = getAnswerYesOrNoFromUser("Czy chcesz podać cenę usługi [T/N]? \n T - tak; N - nie, będzie do ustalenia prywatnie \n Wpisz odpowiedź:", "Niedopuszczalna odpowiedź. Wybierz \"T\" lub \"N\"");
        String price;
        if (ifWantToSetPrice == null) {
            return null;
        } else if (ifWantToSetPrice.equals('N')) {
            price = "do ustalenia indywidualnie";
        } else {
            price = getInputFromUser("Podaj cenę usługi.\n Wpisz \"FREE\" jeśli ma być gratis", "FREE|\\d+", "Podaj prawidłową kwotę.");
        }

        if (price == null) {
            return null;
        } else if (price.equals("FREE")) {
            price = "0";
        }
        return price;
    }

    private String selectKindOfPrice() {
        Character isWantToSetPriceNegotiable = getAnswerYesOrNoFromUser("Czy podana cena jest do negocjacji [T/N]?", "Niedopuszczalna odpowiedź. Wybierz \"T\" lub \"N\"");
        if (isWantToSetPriceNegotiable == null) {
            return null;
        }
        return isWantToSetPriceNegotiable.toString();
    }

    private String selectPriceAdditionalComment() {
        return getInputFromUser("W razie potrzeby, wpisz dodatkowy komentarz do ceny, na przykład: cena za 1 godzinę pacy. Zostaw pusty, jeśli nie chcesz dodawać komentarz", ".*", "Wpisz komentarz lub pozostaw puste pole");
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
            voivodeshipToAssign = selectVoivodeship();
        }
        return voivodeshipToAssign;
    }

    private ServiceTypes validateAndAssignServiceType(String userInput) {
        ServiceTypes serviceTypeToAssign = null;
        for (ServiceTypes i : ServiceTypes.values()) {
            if (i.getSequentialNumber().equals(userInput)) {
                serviceTypeToAssign = i;
                break;
            }
        }
        if (serviceTypeToAssign == null) {
            System.out.println("Wprowadzono niepoprawne dane. Spróbuj jeszcze raz.");
            serviceTypeToAssign = selectServiceType();
        }
        return serviceTypeToAssign;
    }

    private boolean isStringValid(String userInput, String regex) {
        Pattern ptrn = Pattern.compile(regex);
        Matcher match = ptrn.matcher(userInput);
        return match.matches();
    }

}

enum ServiceTypes {
    BUDOWA_DOMU("1", "Budowa domu"),
    ELEKTRYK("2", "Elektryk"),
    HYDRAULIK("3", "Hydraulik"),
    MALARZ("4", "Malarz"),
    MEBLE_I_ZABUDOWA("5", "Meble i zabudowa"),
    MOTORYZACJA("6", "Motoryzacja"),
    OGROD("7", "Ogród"),
    ORGANIZACJA_IMPREZ("8", "Organizacja imprez"),
    PROJEKTOWANIE("9", "Projektowanie"),
    REMONT("10", "Remont"),
    SPRZATANIE("11", "Sprzątanie"),
    SZKOLENIA_I_JEZYKIOBCE("12", "Szkolenia i języki obce"),
    TRANSPORT("13", "Transport"),
    USLUGI_DLA_BIZNESU("14", "Usługi dla biznesu"),
    MONTAZ_I_NAPRAWA("15", "Montaż i naprawa"),
    USLUGI_FINANSOWE("16", "Usługi finansowe"),
    USLUGI_PRAWNE_I_ADMINISTRACYJNE("17", "Usługi prawne i administracyjne"),
    USLUGI_ZDALNE("18", "Usługi zdalne"),
    ZDROWIE_I_URODA("19", "Zdrowie i uroda"),
    ZLOTA_RACZKA("20", "Złota rączka"),
    INNE("21", "Inne");

    private String sequentialNumber;
    private String serviceTypeName;

    ServiceTypes(String sequentialNumber, String serviceTypeName) {
        this.sequentialNumber = sequentialNumber;
        this.serviceTypeName = serviceTypeName;
    }

    public String getSequentialNumber() {
        return sequentialNumber;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }
}

enum Voivodeship {
    DOLNOSLASKIE("1", "Dolnośląskie"),
    KUJAWSKO_POMORSKIE("2", "Kujawsko-pomorskie"),
    LODZKIE("3", "Łódzkie"),
    LUBELSKIE("4", "Lubelskie"),
    LUBUSKIE("5", "Lubuskie"),
    MALOPOLSKIE("6", "Małopolskie"),
    MAZOWIECKIE("7", "Mazowieckie"),
    OPOLSKIE("8", "Opolskie"),
    PODKARPACKIE("9", "Podkarpackie"),
    PODLASKIE("10", "Podlaskie"),
    POMORSKIE("11", "Pomorskie"),
    SLASKIE("12", "Śląskie"),
    SWIETOKRZYSKIE("13", "Świętokrzyskie"),
    WARMINSKO_MAZURSKIE("14", "Warmińsko-mazurskie"),
    WIELKOPOLSKIE("15", "Wielkopolskie"),
    ZACHODNIOPOMORSKIE("16", "Zachodniopomorskie");

    private String sequentialNumber;
    private String voivodeshipName;

    Voivodeship(String sequentialNumber, String voivodeshipName) {
        this.sequentialNumber = sequentialNumber;
        this.voivodeshipName = voivodeshipName;
    }

    public String getSequentialNumber() {
        return sequentialNumber;
    }

    public String getVoivodeshipName() {
        return voivodeshipName;
    }
}