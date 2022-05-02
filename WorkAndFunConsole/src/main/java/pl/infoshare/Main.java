package pl.infoshare;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static final Path USERS_FILE_PATH = Paths.get("WorkAndFunConsole","src", "main", "resources", "Users.json");
    public static final Path ANNOUNCEMENTS_FILE_PATH = Paths.get("WorkAndFunConsole","src", "main", "resources", "Announcements.json");

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.display();
    }
}