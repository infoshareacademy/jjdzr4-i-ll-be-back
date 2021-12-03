package pl.infoshare.workandfun.announcement_repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.infoshare.workandfun.announcement_repository.entity.Announcement;

@Repository
public interface AnnouncementsRepository extends CrudRepository<Announcement, Long> {
    public Iterable<Announcement> findAllByOrderByDateDesc();
    public Iterable<Announcement> findAllByOrderByDateAsc();
}
