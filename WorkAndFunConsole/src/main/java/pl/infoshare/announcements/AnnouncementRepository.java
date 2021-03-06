package pl.infoshare.announcements;

import pl.infoshare.FileActions;
import pl.infoshare.Main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AnnouncementRepository {
    private List<Announcement> announcementList = FileActions.readAnnouncementsFromFile(Main.ANNOUNCEMENTS_FILE_PATH);

    public List<Announcement> findAll() {
        refreshAnnouncementList();
        return announcementList;
    }

    public Announcement findById(long id) {
        refreshAnnouncementList();
        for (Announcement announcement : announcementList) {
            if (announcement.getId() == id) {
                return announcement;
            }
        }
        return null;
    }

    public boolean update(Announcement announcement) {
        announcementList = FileActions.readAnnouncementsFromFile(Main.ANNOUNCEMENTS_FILE_PATH);
        if (announcement != null) {
            removeFromList(announcement.getId());
            announcementList.add(announcement);
            announcementList.sort(Comparator.comparing(Announcement::getId));
            updateFile();
            return true;
        }
        return false;
    }

    private void refreshAnnouncementList() {
        announcementList = FileActions.readAnnouncementsFromFile(Main.ANNOUNCEMENTS_FILE_PATH);
    }

    private void updateFile() {
        FileActions.writeAnnouncementsToFile((ArrayList<Announcement>) announcementList, Main.ANNOUNCEMENTS_FILE_PATH);
    }

    private boolean removeFromList(long id) {
        for (Announcement announcement : announcementList) {
            if (announcement.getId() == id) {
                announcementList.remove(announcement);
                return true;
            }
        }
        return false;
    }

    public boolean delete(Announcement announcement) {
        announcementList = FileActions.readAnnouncementsFromFile(Main.ANNOUNCEMENTS_FILE_PATH);
        if (announcement != null) {
            removeFromList(announcement.getId());
            updateFile();
            return true;
        }
        return false;
    }
}


