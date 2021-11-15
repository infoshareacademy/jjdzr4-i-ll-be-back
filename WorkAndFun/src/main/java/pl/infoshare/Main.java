package pl.infoshare;

import pl.infoshare.announcements.Announcement;
import pl.infoshare.announcements.AnnouncementService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static final Path USERS_FILE_PATH = Paths.get("src", "main", "resources", "Users.csv");
    public static final Path ANNOUNCEMENTS_FILE_PATH = Paths.get("src", "main", "resources", "Announcements.csv");
    public static final Path ANNOUNCEMENTS_FILE_PATH_V2 = Paths.get("src", "main", "resources", "Announcements.json");
    public static final String ANNOUNCEMENTS_BASE_PATH = Paths.get("src", "main", "resources", "AnnouncementsBase").toString();

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.display();
    }


}