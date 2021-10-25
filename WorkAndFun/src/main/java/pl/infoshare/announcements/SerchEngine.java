package pl.infoshare.announcements;

import pl.infoshare.FileActions;
import pl.infoshare.Main;
import pl.infoshare.Menu;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class SerchEngine {

    private static final Scanner scanner = new Scanner(System.in);


    public static void searchAnnoucement(Boolean isOffer) {
        Path path = Paths.get("./src/main/resources/Announcements.csv");
        ArrayList<String[]> arrayFromFile = FileActions.makeArrayFromFile(path);
        boolean isEmpty = true;
        System.out.print("Wpisz co chcesz wyszukać (Lokalizacja lub kategoria lub opis):");
        String serachInput = scanner.nextLine();
        int numOfColumns = 16;
        for (int i = 0; i < arrayFromFile.size(); i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (isOffer.toString().equals(arrayFromFile.get(i)[1])) {
                    if (serachInput.toLowerCase(Locale.ROOT).equals(arrayFromFile.get(i)[j].toLowerCase(Locale.ROOT))) {
                        System.out.print(arrayFromFile.get(i)[2] + " " + arrayFromFile.get(i)[3] + " " + arrayFromFile.get(i)[4] + " " + arrayFromFile.get(i)[5] + " " + arrayFromFile.get(i)[6]
                                + " " + arrayFromFile.get(i)[7] + " " + arrayFromFile.get(i)[8] + " " + arrayFromFile.get(i)[9] + " " + arrayFromFile.get(i)[10] + " " + arrayFromFile.get(i)[11] + " "
                                + arrayFromFile.get(i)[12] + " " + arrayFromFile.get(i)[13] + " " + arrayFromFile.get(i)[14]);
                        isEmpty = false;
                        System.out.println();
                    }
                }
            }
        }
        if (isEmpty) {
            System.out.println("Przykro mi nic nie znaleziono :/");
        }
        while(true) {
            System.out.println("Czy chcesz wrócić do menu? Y/N");
            String yesOrNo = scanner.nextLine();
            if (yesOrNo.toLowerCase(Locale.ROOT).equals("y")) {
                Menu menu = new Menu();
                menu.display();
                break;
            }
            else if(yesOrNo.toLowerCase(Locale.ROOT).equals("n")){
                System.out.println("Zakonczyłeś działanie programu");
                break;
            }
            else{
                System.out.println("Podałeś zły parametr!");
            }
        }
    }
}
