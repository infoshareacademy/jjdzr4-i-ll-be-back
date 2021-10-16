package pl.infoshare;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileActions {

    public static ArrayList<String[]> makeArrayFromFile(Path path) {
        //initialize targeted array to fill and list of strings contains particular lines
        ArrayList<String[]> arrayFromFile = new ArrayList<>();
        String[] particularLines = new String[0];

        //get particular lines from file by separating them by sign of new line (\n)
        try {
            particularLines = Files.readString(path).split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //fill array line by line, and separate each line into another list, by "," sign
        for (int i = 0; i < particularLines.length; i++) {
            String[] newString = particularLines[i].split("~");
            arrayFromFile.add(particularLines[i].split("~"));
        }
        return arrayFromFile;
    }

    public static void writeToFile(Path path, String... parts) {
        try {
            FileWriter fstream = new FileWriter(String.valueOf(path), true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.newLine();
            String lineToFill = String.join("~",parts);

            out.write(lineToFill);
            //close buffer writer
            out.close();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Nie znaleziono pliku do zapisu pod wskazaną ścieżką!");
        }

    }
}