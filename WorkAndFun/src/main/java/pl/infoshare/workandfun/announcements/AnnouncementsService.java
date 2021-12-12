package pl.infoshare.workandfun.announcements;

import org.springframework.stereotype.Service;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementSpec;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementsRepository;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;
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
        return announcementsRepository.findById(id).orElseThrow(() -> new RuntimeException("Brak ogłoszenia z podanym ID"));
    }

    public void deleteById(Long id) {
        announcementsRepository.deleteById(id);
    }

    public Announcement save(Announcement announcement) {
        return announcementsRepository.save(announcement);
    }

    public Announcement update(Long id, AnnouncementEditRequest editRequest) {
        var announcementToUpdate = announcementsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brak ogłoszenia z podanym ID"));
        announcementToUpdate.setType(editRequest.getType());
        announcementToUpdate.setHeader(editRequest.getHeader());
        announcementToUpdate.setServiceType(editRequest.getServiceType());
        announcementToUpdate.setCity(editRequest.getCity());
        announcementToUpdate.setCityDistrict(editRequest.getCityDistrict());
        announcementToUpdate.setUnit(editRequest.getUnit());
        announcementToUpdate.setPrice(editRequest.getPrice());
        announcementToUpdate.setVoivodeship(editRequest.getVoivodeship());
        announcementToUpdate.setEmail(editRequest.getEmail());
        announcementToUpdate.setIsPriceNegotiable(editRequest.getIsPriceNegotiable());
        announcementToUpdate.setDescription(editRequest.getDescription());
        announcementToUpdate.setPhoneNumber(editRequest.getPhoneNumber());
        announcementToUpdate.setPriceAdditionComment(editRequest.getPriceAdditionComment());
        return announcementsRepository.save(announcementToUpdate);
    }
}
