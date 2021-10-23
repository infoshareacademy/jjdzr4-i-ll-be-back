package pl.infoshare.announcements;

import pl.infoshare.FileActions;
import pl.infoshare.Main;
import pl.infoshare.TechnicalMethods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnnouncementService {

    private static final String BREAK_AND_CLOSE = "0 - Przerwij i zamknij bez zapisu";
    private static final Scanner scanner = new Scanner(System.in);

    public class Displaying {
        public void displayAllAnnouncements() {
            //ask user about type of displayed announcement; exit if selected 0
            Boolean typeOfAnnouncementIsOffer = OfferOrDemandAnswer();
            if (typeOfAnnouncementIsOffer == null) {
                return;
            }

            ArrayList<Announcement> baseOfAnnouncements = makeAnnouncementArrayFromFile();
            // sort desc
            Collections.sort(baseOfAnnouncements, Collections.reverseOrder());

            System.out.println("=======================LISTA OGŁOSZEŃ=======================");
            for (Announcement i : baseOfAnnouncements) {
                //typeOfAnnouncementToShow true = offer announcement; false = demand announcement
                if (i.getIsOffer() == typeOfAnnouncementIsOffer) {
                    showAnnouncement(i);
                }
            }
            System.out.println("===========================KONIEC===========================");

            System.out.println("Wciśnij Enter, żeby wrócić do głównego menu:");
            scanner.nextLine();
        }

        private void showAnnouncement(Announcement announcement) {
            LocalDateTime now = java.time.LocalDateTime.now();

            System.out.println("------------------------------------------------------------");
            System.out.println("|--" + announcement.getHeader());
            if ("do ustalenia indywidualnie".equals(announcement.getPrice())) {
                System.out.println("|                                 " + announcement.getPrice());
            } else {
                System.out.println("|                                                      " + announcement.getPrice() + " zł");
            }
            System.out.println("|");
            System.out.println("|--" + announcement.getNameOfAdvertiser() + ", " + announcement.getEmail());
            System.out.println("|--" + announcement.getCity() + ", " + prepareDateToDisplayFormat(now, announcement.getDate()));
            System.out.println("|--id:" + announcement.getId());
            System.out.println("------------------------------------------------------------\n");
        }

        private String prepareDateToDisplayFormat(LocalDateTime now, LocalDateTime comparable) {
            if (now.getYear() != comparable.getYear()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return comparable.format(formatter);
            } else if ((now.getMonthValue() != comparable.getMonthValue()) || ((now.getMonthValue() == comparable.getMonthValue()) && ((now.getDayOfMonth() - comparable.getDayOfMonth()) > 1))) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM");
                return comparable.format(formatter);
            } else if ((now.getDayOfMonth() - comparable.getDayOfMonth()) > 0) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                return "wczoraj " + comparable.format(formatter);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                return "dzisiaj " + comparable.format(formatter);
            }
        }

        private Boolean OfferOrDemandAnswer() {
            String answer = getInputFromUser("Jaki rodzaj ogłoszeń chcesz wyświetlić?\n" +
                            "1 - Ogłoszenia z oferowanymi usługami;\n2 - Ogłoszenia z zapotrzebowaniem na usługę",
                    "[1-2]{1}", "Nie znaleziono takiej opcji. Wpisz jedną z podanych wartości");
            if ("1".equals(answer)) {
                return true;
            } else if ("2".equals(answer)) {
                return false;
            } else {
                return null;
            }
        }
    }


    public class Adding {
        public void addAnnouncement(boolean isOffer) {
            try {
                addAnnouncementOfferService(isOffer);
            } catch (ReturnToMenuException returnToMenuException) {
                System.out.println(returnToMenuException.getMessage());
            }
        }

        private void addAnnouncementOfferService(boolean isOffer) throws ReturnToMenuException {

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
            ifWantToSaveAnnouncement();

            Announcement newAnnouncement = new Announcement(isOffer, inputtedHeader, generatedID, selectedServiceType, selectedCity,
                    selectedCityDistrict, selectedUnit, inputtedPrice, selectedVoivodeship, dateOfAnnouncementCreating,
                    selectedNameOfAdvertiser, selectedEmail, isPriceNegotiableBoolean, inputtedDescription, selectedPhone,
                    inputtedPriceAdditionalComment, selectedClientId);
            FileActions.writeToFile(Main.ANNOUNCEMENTS_FILE_PATH, String.valueOf(newAnnouncement.getId()),
                    String.valueOf(newAnnouncement.getIsOffer()), String.valueOf(newAnnouncement.getServiceType()),
                    String.valueOf(newAnnouncement.getVoivodeship()), String.valueOf(newAnnouncement.getCity()),
                    newAnnouncement.getCityDistrict(), newAnnouncement.getUnit(), newAnnouncement.getNameOfAdvertiser(),
                    newAnnouncement.getPhoneNumber(), newAnnouncement.getEmail(), newAnnouncement.getDescription(),
                    newAnnouncement.getPrice(), String.valueOf(newAnnouncement.getIsPriceNegotiable()),
                    newAnnouncement.getPriceAdditionComment(), String.valueOf(newAnnouncement.getDate()),
                    String.valueOf(newAnnouncement.getClientId()), String.valueOf(newAnnouncement.getHeader()));
            System.out.println("--Ogłoszenie pomyślnie zapisane! Teraz wrócisz do menu głównego--");
            TechnicalMethods.makeDelay(1500);
        }

        private String ifWantToSaveAnnouncement() {
            return getInputFromUser("Czy chcesz dodać ogłoszenie?\n1 - Dodaj ogłoszenie", "[0-1]{1}",
                    "Niedopuszczalna odpowiedź. Wybierz \"1\" lub \"0\"");
        }


        private Object userInputCheck(Object object) throws ReturnToMenuException {
            if (object == null) {
                throw new ReturnToMenuException("Wybrałeś powrót do menu głównego");
            }
            return object;
        }

        private Voivodeship selectVoivodeship() {
            System.out.println("Wybierz województwo, w którym proponujesz/poszukujesz usługę, z listy. Wpisz odpowiedni numer:");
            System.out.println("______________________________");
            for (int i = 0; i < Voivodeship.values().length; i++) {
                System.out.println(Voivodeship.values()[i].getSequentialNumber() + " - " +
                        Voivodeship.values()[i].getVoivodeshipName());
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
            return getInputFromUser("Wpisz miejscowość wykonania usługi. Na przykład: Warszawa",
                    "\\D+", "Źle wprowadzony typ danych. Podaj prawidłową miejscowość");
        }

        private String selectCityDistrict() {
            return getInputFromUser("Wpisz dzielnicę miasta lub pozostaw puste pole:", "\\D*",
                    "Źle wprowadzony typ danych.");
        }

        private String selectUnitName() {
            return getInputFromUser("Wpisz nazwę osiedla lub pozostaw puste pole:", "\\D*",
                    "Źle wprowadzony typ danych.");
        }

        private String selectPhoneNumber() {
            return getInputFromUser("Wpisz polski numer telefonu do kontaktu w sprawie ogłoszenia w " +
                    "formacie: XXXXXXXXX ", "(\\+48)?\\d{9}", "Błąd w numerze telefonu. Spróbuj jeszcze raz.");
        }

        private String selectEmail() {
            return getInputFromUser("Wpisz mail do kontaktu w sprawie ogłoszenia:",
                    "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-]+$", "Niepoprawnie wprowadzony mail. " +
                            "Spróbuj jeszcze raz.");
        }

        private String selectNameOfAdvertiser() {
            return getInputFromUser("Wpisz imię lub nick, które będzie wyświetlone w ogłoszeniu. Unikaj spacji.",
                    "\\w{2,50}", "Zbyt długie/krótkie imie lub wpisana spacja. Spróbuj jeszcze raz.");
        }

        private ServiceType selectServiceType() {
            System.out.println("Wybierz typ usługi z listy wpisując odpowiedni numer. Jeśli nie masz odpowiedniej " +
                    "kategorii na liście, wybierz \"Inne\":");
            System.out.println("______________________________");
            for (int i = 0; i < ServiceType.values().length; i++) {
                System.out.println(ServiceType.values()[i].getSequentialNumber() + " - " +
                        ServiceType.values()[i].getServiceTypeName());
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

        private String inputHeader() {
            return getInputFromUser("Wpisz tytuł ogłoszenia, który będzie widoczny przy wyszukiwaniu:", ".+", "Treści nie " +
                    "znaleziono. Ogłoszenie musi zawierać tytuł.");
        }

        private String inputDescription() {
            return getInputFromUser("Wpisz treść ogłoszenia:", ".+", "Treści nie " +
                    "znaleziono. Ogłoszenie musi zawierać opis.");
        }

        private String selectPrice() {
            String ifWantToSetPrice = getInputFromUser("Czy chcesz podać cenę usługi [T/N/F]? " +
                            "\n T - tak; N - nie, będzie do ustalenia prywatnie; F - gratis \n Wpisz odpowiedź:", "[TtNnFf]{1}",
                    "Niedopuszczalna odpowiedź. Wybierz \"T\", \"N\" lub \"F\":");
            String price;
            if (ifWantToSetPrice == null) {
                return null;
            } else if (ifWantToSetPrice.toUpperCase(Locale.ROOT).equals("T")) {
                price = getInputFromUser("Podaj cenę usługi:",
                        "\\d+", "Podaj prawidłową kwotę. Dopuszcza się wprowadzenie tylko cyfr:");
            } else if (ifWantToSetPrice.toUpperCase(Locale.ROOT).equals("N")) {
                price = "do ustalenia indywidualnie";
            } else {
                price = "0";
            }

            if (price == null) {
                return null;
            }
            return price;
        }

        private Boolean selectKindOfPrice() {
            String isWantToSetPriceNegotiable = getInputFromUser("Czy podana cena jest do negocjacji [T/N]?",
                    "[TNtn]{1}", "Niedopuszczalna odpowiedź. Wybierz \"T\" lub \"N\"");
            if (isWantToSetPriceNegotiable == null) {
                return null;
            }
            if (isWantToSetPriceNegotiable.toUpperCase(Locale.ROOT).equals("T")) {
                return true;
            } else {
                return false;
            }

        }

        private String selectPriceAdditionalComment() {
            return getInputFromUser("W razie potrzeby, wpisz dodatkowy komentarz do ceny, na przykład: " +
                            "cena za 1 godzinę pacy. Zostaw pusty, jeśli nie chcesz dodawać komentarz", ".*",
                    "Wpisz komentarz lub pozostaw puste pole");
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

        private ServiceType validateAndAssignServiceType(String userInput) {
            ServiceType serviceTypeToAssign = null;
            for (ServiceType i : ServiceType.values()) {
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

    }

    public ArrayList<Announcement> makeAnnouncementArrayFromFile() {
        ArrayList<String[]> baseOfAnnouncementsStrings = FileActions.makeArrayFromFile(Main.ANNOUNCEMENTS_FILE_PATH);
        ArrayList<Announcement> baseOfAnnouncements = new ArrayList<>();
        //delete headers
        baseOfAnnouncementsStrings.remove(0);

        for (String[] line : baseOfAnnouncementsStrings) {
            boolean isOffer = Boolean.parseBoolean(line[1]);
            long ID = Long.parseLong(line[0]);
            ServiceType serviceType = ServiceType.valueOf(line[2]);
            String city = line[4];
            String cityDistrict = line[5];
            String unit = line[6]; //osiedle
            String price = line[11];

            //TODO: change code below after adding clientId assigning functionality
            Integer clientId;
            if (line[15].equals("null")) {
                clientId = null;
            } else clientId = Integer.valueOf(line[15]);

            Voivodeship voivodeship = Voivodeship.valueOf(line[3]);
            LocalDateTime dateOfCreating = LocalDateTime.parse(line[14]);
            String nameOfAdvertiser = line[7];
            String email = line[9];
            boolean isPriceNegotiable = Boolean.parseBoolean(line[12]);
            String description = line[10];
            String phoneNumber = line[8];
            String priceAdditionComment = line[13];
            String header = line[16];

            Announcement newAnnouncement = new Announcement(isOffer,
                    header,
                    ID,
                    serviceType,
                    city,
                    cityDistrict,
                    unit,
                    price,
                    voivodeship,
                    dateOfCreating,
                    nameOfAdvertiser,
                    email,
                    isPriceNegotiable,
                    description,
                    phoneNumber,
                    priceAdditionComment,
                    clientId);

            baseOfAnnouncements.add(newAnnouncement);
        }
        return baseOfAnnouncements;
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

    private boolean isStringValid(String userInput, String regex) {
        Pattern ptrn = Pattern.compile(regex);
        Matcher match = ptrn.matcher(userInput);
        return match.matches();
    }

}

enum ServiceType {
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

    ServiceType(String sequentialNumber, String serviceTypeName) {
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