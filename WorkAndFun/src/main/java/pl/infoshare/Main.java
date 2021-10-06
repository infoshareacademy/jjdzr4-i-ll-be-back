package pl.infoshare;



import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static final Path path = Paths.get("src","main","resources","Users.csv");
    public static void main(String[] args) {
        User user1 = new User(1,"Andrzej", "Kowal","passsssword", 123344411,"coolemail@wp.pl",54,"Polski",(float)4.3);
        ArrayList<String[]> array= FileActions.makeArrayFromFile(path);
        try {
            UserService.addUser(user1, path);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        Menu menu = new Menu();
        menu.display();
    }

}