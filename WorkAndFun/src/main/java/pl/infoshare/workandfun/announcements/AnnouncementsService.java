package pl.infoshare.workandfun.announcements;

import org.springframework.stereotype.Service;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementSpec;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementsRepository;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;
import pl.infoshare.workandfun.exception.AnnouncementNotFoundException;

import java.util.List;

@Service
public class AnnouncementsService {

    private final AnnouncementsRepository announcementsRepository;

    public AnnouncementsService(AnnouncementsRepository announcementsRepository) {
        this.announcementsRepository = announcementsRepository;
    }

    public Iterable<Announcement> findAllSortedByCreateDateDesc() {
        return announcementsRepository.findAllByOrderByDateDesc();
    }

    public List<Announcement> findAllByQuerySpec(AnnouncementSpec announcementSpec){
        return announcementsRepository.findAll(announcementSpec);
    }

    public Announcement findById(Long id) {
        return announcementsRepository.findById(id).orElseThrow(() -> new AnnouncementNotFoundException(id));
    }

    public void deleteById(Long id) {
        announcementsRepository.findById(id)
                .ifPresentOrElse(announcementsRepository::delete, () -> { throw new AnnouncementNotFoundException(id); });
    }

    public Announcement save(Announcement announcement) {
        return announcementsRepository.save(announcement);
    }

    public Announcement update(Long id, AnnouncementEditRequest editRequest) {

        return announcementsRepository.findById(id).map(element -> {
            element.setType(editRequest.getType());
            element.setHeader(editRequest.getHeader());
            element.setServiceType(editRequest.getServiceType());
            element.setCity(editRequest.getCity());
            element.setCityDistrict(editRequest.getCityDistrict());
            element.setUnit(editRequest.getUnit());
            element.setPrice(editRequest.getPrice());
            element.setVoivodeship(editRequest.getVoivodeship());
            element.setEmail(editRequest.getEmail());
            element.setIsPriceNegotiable(editRequest.getIsPriceNegotiable());
            element.setDescription(editRequest.getDescription());
            element.setPhoneNumber(editRequest.getPhoneNumber());
            element.setPriceAdditionComment(editRequest.getPriceAdditionComment());
            return announcementsRepository.save(element);
        }).orElseThrow(() -> new AnnouncementNotFoundException(id));
    }
}
