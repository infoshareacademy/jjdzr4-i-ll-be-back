package pl.infoshare;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import pl.infoshare.announcements.Announcement;
import pl.infoshare.announcements.Gson.LocalDateTimeDeserializer;
import pl.infoshare.announcements.Gson.LocalDateTimeSerializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileActions {
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    public static void writeAnnouncementsToFile(ArrayList<Announcement> announcements, Path path) {
        try {
            FileWriter fw = new FileWriter(path.toString());
            GSON.toJson(announcements, fw);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addAnnouncementsToFile(Announcement announcement, Path path) {
        ArrayList<Announcement> announcements = loadAnnouncementsFromFile(path);
        announcements.add(announcement);
        try {
            FileWriter fw = new FileWriter(path.toString());
            GSON.toJson(announcements, fw);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Announcement> loadAnnouncementsFromFile(Path path) {
        try {
            return GSON.fromJson(new FileReader(path.toString()), new TypeToken<ArrayList<Announcement>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Announcement>();
    }


}