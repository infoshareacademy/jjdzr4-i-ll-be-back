package pl.infoshare;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static final Path USERS_FILE_PATH = Paths.get("src", "main", "resources", "Users.csv");
    public static final Path ANNOUNCEMENTS_FILE_PATH = Paths.get("src", "main", "resources", "Announcements.csv");

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.display();
    }
}