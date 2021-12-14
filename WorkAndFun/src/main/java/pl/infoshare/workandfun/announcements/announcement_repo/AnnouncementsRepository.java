package pl.infoshare.workandfun.announcements.announcement_repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;

@Repository
public interface AnnouncementsRepository extends CrudRepository<Announcement, Long>, JpaSpecificationExecutor<Announcement> {
    Iterable<Announcement> findAllByOrderByDateDesc();
}
