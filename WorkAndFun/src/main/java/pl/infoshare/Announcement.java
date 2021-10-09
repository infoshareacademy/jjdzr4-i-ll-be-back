package pl.infoshare;


//import java.time.LocalDate;


import java.time.LocalDate;
import java.util.Scanner;

public class Announcement {
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

    public void setPrice(int lowerPriceLimit, int higherPriceLimit) {
        this.price = lowerPriceLimit + " - " + higherPriceLimit + "zł";
    }

    public void addAnnouncementOfferService() {


        Voivodeship selectedVoivodeship = selectVoivodeshipForAnnouncement();
        if (selectedVoivodeship == null){
            return;
        }



    }

    private Voivodeship selectVoivodeshipForAnnouncement() {
        System.out.println("Wybierz województwo z listy. Wpisz odpowiedni numer:");

        TechnicalMethods.makeDelay(500);

        for (int i = 0; i < Voivodeship.values().length; i++) {
            System.out.println(Voivodeship.values()[i].getSequentialNumber() + " - " + Voivodeship.values()[i].getVoivodeshipName());
            TechnicalMethods.makeDelay(100);
        }
        System.out.println("0 - Przerwij i zamknij dodawanie ogłoszenia");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if (userInput.equals("0")){
            System.out.println("Wybrałeś(-aś) 0 - przerwanie dodawania ogłoszenia...");
            return null;
        }

        return checkVoivodeship(userInput);
    }

    public Voivodeship checkVoivodeship(String userInput){
        try {
            for (Voivodeship i : Voivodeship.values()) {
                if (i.getSequentialNumber().equals(userInput)) {
                    return i;
                }
            }
            throw new Exception("Wprowadzono niepoprawne dane. Spróbuj jeszcze raz.");
        } catch (Exception e){
            addAnnouncementOfferService();
        }
        return null;
    }
}