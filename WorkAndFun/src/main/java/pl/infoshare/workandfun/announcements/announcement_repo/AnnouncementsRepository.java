package pl.infoshare.workandfun.announcements.announcement_repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;

@Repository
public interface AnnouncementsRepository extends CrudRepository<Announcement, Long> {
    Iterable<Announcement> findAllByOrderByDateDesc();
    Iterable<Announcement> findAllByOrderByDateAsc();
}
