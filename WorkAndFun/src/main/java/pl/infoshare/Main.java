package pl.infoshare;

import com.google.gson.*;
import pl.infoshare.announcements.Announcement;
import pl.infoshare.announcements.AnnouncementService;
import pl.infoshare.announcements.Gson.LocalDateDeserializer;
import pl.infoshare.announcements.Gson.LocalDateSerializer;
import pl.infoshare.announcements.Gson.LocalDateTimeDeserializer;
import pl.infoshare.announcements.Gson.LocalDateTimeSerializer;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.util.ArrayList;

public class Main {
    public static final Path USERS_FILE_PATH = Paths.get("src", "main", "resources", "Users.csv");
    public static final Path ANNOUNCEMENTS_FILE_PATH = Paths.get("src", "main", "resources", "Announcements.csv");

    public static void main(String[] args) throws IOException {
        ArrayList<Announcement> announcements = AnnouncementService.makeAnnouncementArrayFromFile(ANNOUNCEMENTS_FILE_PATH);
        Announcement announcement = announcements.get(0);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());

        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());

        Gson gson = gsonBuilder.setPrettyPrinting().create();

        FileWriter fw = new FileWriter("src/main/resources/1.json");
        gson.toJson(announcement,fw);
        fw.flush();
        fw.close();






        Menu menu = new Menu();
        menu.display();
    }
}