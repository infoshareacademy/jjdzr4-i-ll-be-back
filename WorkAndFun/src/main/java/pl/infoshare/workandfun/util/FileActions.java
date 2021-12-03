package pl.infoshare.workandfun.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.infoshare.workandfun.config.Paths;
import pl.infoshare.workandfun.domain.Announcement;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileActions<E> {

    E e;

    private final ObjectMapper mapper;
    private final Paths paths;

    @Autowired
    public FileActions(ObjectMapper mapper, Paths paths) {
        this.mapper = mapper;
        this.paths = paths;
    }

    public List<E> readObjectListFromBase() {
        List<E> objectList = new ArrayList<>();
        try {
            objectList = mapper.readValue(new FileReader(paths.getAnnouncementPath()), new TypeReference<List<E>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objectList;
    }

    public E readObjectFromBase() {
        try {
            return (E) mapper.readValue(new FileReader(paths.getAnnouncementPath()), new TypeReference<E>() {
            });
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

//    public ArrayList<Announcement> readAnnouncementsFromFile() {
//        ArrayList<Announcement> announcementList = new ArrayList<>();
//        try {
//            announcementList = mapper.readValue(new FileReader(paths.getAnnouncementPath()), new TypeReference<ArrayList<Announcement>>() {
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return announcementList;
//    }

    public void writeAnnouncementsToFile(ArrayList<Announcement> announcements) {
        try {
            mapper.writeValue(new FileWriter(paths.getAnnouncementPath()), announcements);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeObjectToBase(E object) {
        try {
            mapper.writeValue(new FileWriter(paths), object);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

//    public static void addAnnouncementsToFile(Announcement announcement, Path path) {
//        ArrayList<Announcement> announcements = readAnnouncementsFromFile(path);
//        announcements.add(announcement);
//        try {
//            FileWriter fw = new FileWriter(path.toString());
//            GSON.toJson(announcements, fw);
//            fw.flush();
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
