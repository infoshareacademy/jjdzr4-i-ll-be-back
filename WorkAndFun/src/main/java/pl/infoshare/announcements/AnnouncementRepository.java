package pl.infoshare.announcements;

import pl.infoshare.FileActions;
import pl.infoshare.Main;
import java.util.Comparator;
import java.util.List;

public class AnnouncementRepository {
    public List<Announcement> announcementList = AnnouncementService.makeAnnouncementArrayFromFile(Main.ANNOUNCEMENTS_FILE_PATH);

    public Announcement findById(long id) {
        announcementList = AnnouncementService.makeAnnouncementArrayFromFile(Main.ANNOUNCEMENTS_FILE_PATH);
        for (Announcement announcement : announcementList) {
            if (announcement.getId() == id) {
                return announcement;
            }
        }
        return null;
    }

    public boolean update(Announcement announcement) {
        if (announcement != null) {
            removeFromList(announcement.getId());
            announcementList.add(announcement);
            announcementList.sort(Comparator.comparing(Announcement::getId));
            updateFile();
            return true;
        }
        return false;
    }

    private void updateFile() {
        FileActions.clearCsvFile(Main.ANNOUNCEMENTS_FILE_PATH);
        FileActions.writeToFileObjectList(announcementList);
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
        if (announcement != null) {
            removeFromList(announcement.getId());
            updateFile();
            return true;
        }
        return false;
    }
}

