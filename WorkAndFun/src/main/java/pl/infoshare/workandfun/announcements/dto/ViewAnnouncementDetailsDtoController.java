package pl.infoshare.workandfun.announcements.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.infoshare.workandfun.announcements.AnnouncementService;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Service
public class ViewAnnouncementDetailsDtoController {

    private AnnouncementService announcementService;

    public List<AnnouncementService> viewAllDetails(@PathVariable Long id) {
        List<AnnouncementService> allAnnouncementData = new ArrayList<>();
        announcementService.findByIdConvertToDto(id).getCity();
        announcementService.findByIdConvertToDto(id).getCityDistrict();
        announcementService.findByIdConvertToDto(id).getDescription();
        announcementService.findByIdConvertToDto(id).getEmail();
        announcementService.findByIdConvertToDto(id).getCityDistrict();
        announcementService.findByIdConvertToDto(id).getHeader();
        announcementService.findByIdConvertToDto(id).getServiceType();
        announcementService.findByIdConvertToDto(id).getId();
        announcementService.findByIdConvertToDto(id).getIsPriceNegotiable();
        announcementService.findByIdConvertToDto(id).getNameOfAdvertiser();
        announcementService.findByIdConvertToDto(id).getPhoneNumber();
        announcementService.findByIdConvertToDto(id).getPhoneNumber();
        announcementService.findByIdConvertToDto(id).getPriceAdditionComment();
        announcementService.findByIdConvertToDto(id).getType();
        announcementService.findByIdConvertToDto(id).getUnit();
        announcementService.findByIdConvertToDto(id).getVoivodeship();

        return allAnnouncementData;


    }
}
