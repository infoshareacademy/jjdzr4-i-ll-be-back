package pl.infoshare;

import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import static pl.infoshare.FileActions.GSON;

public class UserService {
    public static void writeUsersToFile(ArrayList<User> users, Path path) {
        try {
            FileWriter fw = new FileWriter(path.toString());
            GSON.toJson(users, fw);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addAnnouncementsToFile(User user, Path path) {
        ArrayList<User> users = loadUsersFromFile(path);
        users.add(user);
        try {
            FileWriter fw = new FileWriter(path.toString());
            GSON.toJson(users, fw);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> loadUsersFromFile(Path path) {
        try {
            return GSON.fromJson(new FileReader(path.toString()), new TypeToken<ArrayList<User>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<User>();
    }
}