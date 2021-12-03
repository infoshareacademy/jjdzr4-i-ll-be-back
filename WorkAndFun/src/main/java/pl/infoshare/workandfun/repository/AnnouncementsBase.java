package pl.infoshare.workandfun.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.infoshare.workandfun.config.Paths;
import pl.infoshare.workandfun.domain.Announcement;
import pl.infoshare.workandfun.util.FileActions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class AnnouncementsBase {

    private final FileActions<ArrayList<Announcement>> fileActions;
    private List<Announcement> announcementList;
    private final Paths paths;

    @Autowired
    public AnnouncementsBase(FileActions<ArrayList<Announcement>> fileActions, Paths paths) {
        this.fileActions = fileActions;
        this.paths = paths;
        this.announcementList = this.fileActions.readObjectFromBase(paths.getAnnouncementPath());
    }

    public List<Announcement> findAll() {
        refreshAnnouncementBase();
        return announcementList;
    }

    public Announcement findById(long id) {
        refreshAnnouncementBase();
        for (Announcement announcement : announcementList) {
            if (announcement.getId() == id) {
                return announcement;
            }
        }
        return null;
    }

    public boolean updateAnnouncement(Announcement announcement) {
        announcementList = findAll();
        if (announcement != null) {
            removeFromList(announcement.getId());
            announcementList.add(announcement);
            announcementList.sort(Comparator.comparing(Announcement::getId));
            updateFile();
            return true;
        }
        return false;
    }

    private void refreshAnnouncementBase() {
        announcementList = fileActions.readObjectFromBase(paths.getAnnouncementPath());
    }

    private void updateFile() {
        fileActions.writeObjectToBase((ArrayList<Announcement>) announcementList, paths.getAnnouncementPath());
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
        announcementList = findAll();
        if (announcement != null) {
            removeFromList(announcement.getId());
            updateFile();
            return true;
        }
        return false;
    }
}


