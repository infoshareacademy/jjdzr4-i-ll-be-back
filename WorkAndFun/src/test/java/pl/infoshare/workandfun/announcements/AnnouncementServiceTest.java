package pl.infoshare.workandfun.announcements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementSpec;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementsRepository;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.ServiceType;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Type;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Voivodeship;
import pl.infoshare.workandfun.announcements.mappers.AddAndEditMapper;
import pl.infoshare.workandfun.announcements.mappers.QuickViewAnnouncementMapper;
import pl.infoshare.workandfun.exception.AnnouncementNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnnouncementServiceTest {

    @Mock
    private AnnouncementsRepository announcementsRepository;
    @Spy
    private AddAndEditMapper addAndEditMapper;
    @Spy
    private QuickViewAnnouncementMapper quickViewAnnouncementMapper;

    @InjectMocks
    private AnnouncementService announcementService;

    private Announcement announcement = new Announcement();
    private Long id;

    @BeforeEach
    void init() {
        announcement = new Announcement(1L, Type.SERVICE_OFFER, "Wyprowadzam psy, koty, myszy, konie, słonie",
                ServiceType.INNE, "Warszawa", "dzielnica", "osiedle", "200", null, Voivodeship.MAZOWIECKIE, LocalDateTime.of(LocalDate.of(2020, 10, 10), LocalTime.of(10, 10)), "Andrzej",
                "andrzej@aa.pl", false, "Andrzej, czyli ja to miłośnik zwierząt chętnie spędzający z nimi czas, nie masz" +
                " co zrobić ze swoim zwierzakiem, zadzwoń do Andrzeja", "+48666666666", "z FV będzie drożej");
        id = 1L;
    }

    @Test
    void shouldFindAllSortedByCreateDateDescConvertToDto() {
        //given
        when(announcementsRepository.findAllByOrderByDateDesc()).thenReturn(List.of(announcement));
        //when
        final var result = announcementService.findAllSortedByCreateDateDescConvertToDto();
        final var firstElement = result.iterator().next();
        //then
        assertThat(((Collection<?>) result)).hasSize(1);
        assertThat(firstElement.getDate()).isEqualTo(LocalDateTime.of(LocalDate.of(2020, 10, 10), LocalTime.of(10, 10)));
        assertThat(firstElement.getHeader()).isEqualTo("Wyprowadzam psy, koty, myszy, konie, słonie");
        assertThat(firstElement.getId()).isEqualTo(1L);
        assertThat(firstElement.getPrice()).isEqualTo("200");
        assertThat(firstElement.getIsPriceNegotiable()).isFalse();
        assertThat(firstElement.getFullLocalization()).isEqualTo("Warszawa, dzielnica, osiedle");
        assertThat(firstElement.isIndividualPrice()).isFalse();
    }

    @Test
    void shouldFindAllByQuerySpec() {
        announcementService.findAllByQuerySpec(mock(AnnouncementSpec.class));
        verify(announcementsRepository).findAll(any(AnnouncementSpec.class));
    }

    @Test
    void shouldThrowExceptionWhenFindByIdNotFoundAnnouncement() {
        //given
        Long id3 = 3L;
        when(announcementsRepository.findById(id3)).thenReturn(Optional.empty());
        //when
        //then
        Assertions.assertThrows(AnnouncementNotFoundException.class, () -> announcementService.findById(id3));
    }

    @Test
    void shouldFindById() {
        //given
        when(announcementsRepository.findById(id)).thenReturn(Optional.of(announcement));
        //when
        //then
        assertThat(announcement).isEqualTo(announcementService.findById(id));
    }

    @Test
    void shouldFindByIdAndMapToDto() {
        //given
        when(announcementsRepository.findById(id)).thenReturn(Optional.of(announcement));
        //when
        final var result = announcementService.findByIdConvertToDto(id);
        //then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getType()).isEqualTo(Type.SERVICE_OFFER);
        assertThat(result.getHeader()).isEqualTo("Wyprowadzam psy, koty, myszy, konie, słonie");
        assertThat(result.getServiceType()).isEqualTo(ServiceType.INNE);
        assertThat(result.getCity()).isEqualTo("Warszawa");
        assertThat(result.getCityDistrict()).isEqualTo("dzielnica");
        assertThat(result.getUnit()).isEqualTo("osiedle");
        assertThat(result.getPrice()).isEqualTo("200");
        assertThat(result.getVoivodeship()).isEqualTo(Voivodeship.MAZOWIECKIE);
        assertThat(result.getEmail()).isEqualTo("andrzej@aa.pl");
        assertThat(result.getIsPriceNegotiable()).isFalse();
        assertThat(result.getDescription()).isEqualTo("Andrzej, czyli ja to miłośnik zwierząt chętnie spędzający z nimi czas, nie masz co zrobić ze swoim zwierzakiem, zadzwoń do Andrzeja");
        assertThat(result.getPhoneNumber()).isEqualTo("+48666666666");
        assertThat(result.getPriceAdditionComment()).isEqualTo("z FV będzie drożej");
    }

    @Test
    void shouldThrowExceptionWhenFindByIdConvertToDtoNotFoundAnnouncement() {
        //given
        when(announcementsRepository.findById(id)).thenReturn(Optional.empty());
        //when
        //then
        Assertions.assertThrows(AnnouncementNotFoundException.class, () -> announcementService.findById(id));
    }

    @Test
    void shouldDeleteAnnouncementByIdIfExist() {
        //given
        when(announcementsRepository.findById(id)).thenReturn(Optional.of(announcement));
        //when
        announcementService.deleteById(id);
        //then
        verify(announcementsRepository).findById(id);
        verify(announcementsRepository).delete(announcement);
    }
}
