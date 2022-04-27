package pl.infoshare.workandfun.announcements.announcement_repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.ServiceType;
import pl.infoshare.workandfun.users.User;

import java.util.List;

@Repository
public interface AnnouncementsRepository extends CrudRepository<Announcement, Long>, JpaSpecificationExecutor<Announcement> {
    Iterable<Announcement> findAllByOrderByDateDesc();
    Page<Announcement> findAllByOrderByDateDesc(Pageable pageable);
    Iterable<Announcement> findAllByServiceTypeEquals(ServiceType serviceType);
    List<Announcement> findAllByOwnerOrderByDateDesc(User user);
}
