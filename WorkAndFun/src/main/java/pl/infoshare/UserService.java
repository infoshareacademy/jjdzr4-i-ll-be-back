package pl.infoshare;

import java.io.IOException;
import java.nio.file.Path;


public class UserService {
    public static void addUser(User user, Path path) {
        FileActions.writeToFile(path,true, String.valueOf(user.getId()), user.getFirstName(), user.getLastName(),
                user.getPassword(), String.valueOf(user.getPhone()), user.getMail(), String.valueOf(user.getAge()),
                user.getLanguages(), String.valueOf(user.getRating()));
    }
}