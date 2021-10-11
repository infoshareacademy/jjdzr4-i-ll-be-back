package pl.infoshare;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static final Path USERS_FILE_PATH = Paths.get("src","main","resources","Users.csv");
    public static final Path ANNOUNCEMENTS_FILE_PATH = Paths.get("src","main","resources","Announcements.csv");
    public static void main(String[] args) {

//        User user1 = new User(1,"Andrzej", "Kowal","passsssword", 123344411,"coolemail@wp.pl",54,"Polski",(float)4.3);
//        ArrayList<String[]> array = FileActions.makeArrayFromFile(USERS_FILE_PATH);
//
//        UserService.addUser(user1, USERS_FILE_PATH);

        Menu menu = new Menu();
        menu.display();



    }
}