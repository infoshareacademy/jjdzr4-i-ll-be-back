package pl.infoshare;


//import java.time.LocalDate;


import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Announcement {
    private static final String BREAKANDCLOSE = "[0 - Przerwij i zamknij dodawanie ogłoszenia]";

    private boolean isOffer; //true=usługa/false=zapotrzebowanie
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
    private Integer lowerPriceLimit;
    private Integer higherPriceLimit;
    private String description;
    private String phoneNumber;
    private String priceAdditionComment = "";
    private boolean isPriceToBeDetermined = false;

    private void setPrice(Integer lowerPriceLimit, Integer higherPriceLimit, String priceAdditionComment) throws Exception {
        if (higherPriceLimit > lowerPriceLimit){
            throw new Exception("Górny próg cenowy nie może być mniejszy od dolnego progu");
        } else if (higherPriceLimit == lowerPriceLimit){
            this.price = higherPriceLimit + " zł";
        } else {
            this.price = lowerPriceLimit + " - " + higherPriceLimit + " zł";
        }
        if (!(priceAdditionComment.equals(""))){
            this.priceAdditionComment = priceAdditionComment;
        }
    }

    public void addAnnouncementOfferService() {

        //assigning voivodeship
        Voivodeship selectedVoivodeship = selectVoivodeship();
        if (selectedVoivodeship == null) {return;}
        //assigning city
        String selectedCity = selectCity();
        if (selectedCity == null) {return;}
        //assigning phone number
        String selectedPhone = selectPhoneNumber();
        if (selectedPhone == null) {return;}
        //assigning email
        String selectedEmail = selectEmail();
        if (selectedEmail == null) {return;}
        //assigning name/nickname of advertiser
        String selectedNameOfAdvertiser = selectNameOfAdvertiser();
        if (selectedNameOfAdvertiser == null) {return;}
        //assigning service type
        ServiceTypes selectedServiceType = selectServiceType();
        if (selectedServiceType == null) {return;}
        //input description
        String inputtedDescription = inputDescription();
        if (inputtedDescription == null) {return;}




    }

    private void cena(){


        Character answerIsWannaSetPrice = getAnswerYesOrNoFromUser("Czy chcesz podać cenę usługi [T/N]? \n T - tak; N - nie, będzie do ustalenia prywatnie \n Wpisz odpowiedź:");
        if (answerIsWannaSetPrice == null) {return;}
        if (answerIsWannaSetPrice.equals("T")){


        }
    }

    public void lowestPrice(){
        String lowestPrice;
        lowestPrice = getInputFromUser("Podaj dolną granicę ceny.\n Wpisz \"FREE\" jeśli ma być gratis");
        if(lowestPrice.equals(0)){
            return;
        } else if(!(validateString(lowestPrice, "[(FREE){1}\\d+]"))){

        }
    }

    private Character getAnswerYesOrNoFromUser(String messageForUser){
        String isPriceToBeDeterminedAnswer = getInputFromUser(messageForUser);
        if (isPriceToBeDeterminedAnswer.equals("0")) {
            return null;
        } else if (!(validateString(isPriceToBeDeterminedAnswer.toUpperCase(Locale.ROOT), "([TN]{1}"))){
            System.out.println("Niedopuszczalna odpowiedź. Wybierz \"T\" lub \"N\"");
            getAnswerYesOrNoFromUser(messageForUser);
        }
        return isPriceToBeDeterminedAnswer.toUpperCase(Locale.ROOT).charAt(0);
    }

    private String getInputFromUser(String messageForUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(messageForUser);
        System.out.println(BREAKANDCLOSE);
        System.out.println("______________________________");
        return scanner.nextLine();
    }

    private Voivodeship selectVoivodeship() {
        System.out.println("Wybierz województwo, w którym proponujesz usługę, z listy. Wpisz odpowiedni numer:");
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

    private String selectCity() {
        String selectedCity = getInputFromUser("Wpisz miejscowość, w której proponujesz usługę. Na przykład: Warszawa");
        if (selectedCity.equals("0")) {
            return null;
        }
        return selectedCity;
    }

    private String selectPhoneNumber() {
        String selectedPhoneNumber = getInputFromUser("Wpisz polski numer telefonu, do kontaktu w sprawie ogłoszenia, w formacie: XXXXXXXXX ");
        if (selectedPhoneNumber.equals("0")) {
            return null;
        } else if (!validateString(selectedPhoneNumber,"(\\+48)?\\d{9}")) {
            System.out.println("Błąd w numerze telefonu. Spróbuj jeszcze raz.");
            selectPhoneNumber();
        }
        return selectedPhoneNumber;
    }

    private String selectEmail() {
        String selectedEmail = getInputFromUser("Wpisz mail do kontaktu, w sprawie ogłoszenia:");
        if (selectedEmail.equals("0")) {
            return null;
        } else if (!validateString(selectedEmail,".+@.+\\..+")) {
            System.out.println("Niepoprawnie wprowadzony mail. Spróbuj jeszcze raz.");
            selectEmail();
        }
        return selectedEmail;
    }

    private String selectNameOfAdvertiser() {
        String selectedNameOfAdvertiser = getInputFromUser("Wpisz imię/nick, które będzie wyświetlone w ogłoszeniu. Unikaj spacji.");
        if (selectedNameOfAdvertiser.equals("0")) {
            return null;
        } else if (!validateString(selectedNameOfAdvertiser,"\\w{2,50}")) {
            System.out.println("Zbyt długie/krótkie imie, lub wpisana spacja. Spróbuj jeszcze raz.");
            selectNameOfAdvertiser();
        }
        return selectedNameOfAdvertiser;
    }

    private ServiceTypes selectServiceType() {
        System.out.println("Wybierz typ usługi, którą proponujesz, z listy, wpisując odpowiedni numer. Jeśli nie masz odpowiedniej kategorii na liście, wybierz \"Inne\":");
        System.out.println("______________________________");
        for (int i = 0; i < ServiceTypes.values().length; i++) {
            System.out.println(ServiceTypes.values()[i].getSequentialNumber() + " - " + ServiceTypes.values()[i].getServiceTypeName());
        }
        System.out.println(BREAKANDCLOSE);
        System.out.println("______________________________");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if (userInput.equals("0")) {
            System.out.println("Wybrałeś(-aś) 0 - przerwanie dodawania ogłoszenia...");
            return null;
        }
        return validateAndAssignServiceType(userInput);
    }

    private String inputDescription() {
        String inputtedDescription = getInputFromUser("Wpisz treść ogłoszenia:");
        if (inputtedDescription.equals("0")) {
            return null;
        }
        return inputtedDescription;
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
            selectVoivodeship();
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
            selectServiceType();
        }
        return serviceTypeToAssign;
    }

    private boolean validateString(String userInput, String rules) {
        Pattern ptrn = Pattern.compile(rules);
        Matcher match = ptrn.matcher(userInput);
        return match.matches();
    }

}