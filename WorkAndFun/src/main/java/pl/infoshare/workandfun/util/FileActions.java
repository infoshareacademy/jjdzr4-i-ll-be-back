package pl.infoshare.workandfun.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileActions<E> {

    private E e;

    private ObjectMapper mapper;

    @Autowired
    public FileActions(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<E> readObjectListFromBase(String path, Class<E> elementClass) {
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
        try {
            return mapper.readValue(new FileReader(path), listType);
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public E readObjectFromBase(String path, Class<E> elementClass) {
        try {
            return mapper.readValue(new FileReader(path), elementClass);
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

//    public void writeAnnouncementsToFile(ArrayList<Announcement> announcements) {
//        try {
//            mapper.writeValue(new FileWriter(paths.getAnnouncementPath()), announcements);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void writeObjectToBase(E object, String path) {
        try {
            mapper.writeValue(new FileWriter(path), object);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeObjectListToBase(List<E> objectsList, String path) {
        try {
            mapper.writeValue(new FileWriter(path), objectsList);
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
