package pl.infoshare;

import pl.infoshare.announcements.Announcement;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
        for (String particularLine : particularLines) {
            String[] newString = particularLine.split("~");
            arrayFromFile.add(particularLine.split("~"));
        }
        return arrayFromFile;
    }

    public static void writeToFile(Path path, boolean append, String... parts) {
        try {
            FileWriter fstream = new FileWriter(String.valueOf(path), append);
            BufferedWriter out = new BufferedWriter(fstream);
            if (append) {
                out.newLine();
            }
            String lineToFill = String.join("~", parts);

            out.write(lineToFill);
            //close buffer writer
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono pliku do zapisu pod wskazaną ścieżką!");
        }
    }

    public static void writeToFileObjectList(List<Announcement> announcementList) {
        ArrayList<String[]> arrayFromObjectList = new ArrayList<>();
        writeToFile(Main.ANNOUNCEMENTS_FILE_PATH, false, Announcement.ANNOUNCEMENT_HEADER);

        for (Announcement announcement : announcementList) {
            arrayFromObjectList.add(announcement.mapToStringArray());
            writeToFile(Main.ANNOUNCEMENTS_FILE_PATH, true, String.valueOf(announcement.getId()),
                    String.valueOf(announcement.getOfferType()), String.valueOf(announcement.getServiceType()),
                    String.valueOf(announcement.getVoivodeship()), String.valueOf(announcement.getCity()),
                    announcement.getCityDistrict(), announcement.getUnit(), announcement.getNameOfAdvertiser(),
                    announcement.getPhoneNumber(), announcement.getEmail(), announcement.getDescription(),
                    announcement.getPrice(), String.valueOf(announcement.getIsPriceNegotiable()),
                    announcement.getPriceAdditionComment(), String.valueOf(announcement.getDate()),
                    String.valueOf(announcement.getClientId()), String.valueOf(announcement.getHeader()));
        }
    }

    public static void clearCsvFile(Path filepath) {
        File csvFile = new File(String.valueOf(filepath));
        csvFile.delete();
    }
}