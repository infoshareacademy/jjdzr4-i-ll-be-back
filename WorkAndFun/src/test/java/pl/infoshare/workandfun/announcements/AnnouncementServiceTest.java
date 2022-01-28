package pl.infoshare.workandfun.announcements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementSpec;
import pl.infoshare.workandfun.announcements.announcement_repo.AnnouncementsRepository;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.Announcement;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.ServiceType;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Type;
import pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals.Voivodeship;
import pl.infoshare.workandfun.announcements.dto.AddAndEditAnnouncementDto;
import pl.infoshare.workandfun.announcements.dto.QuickViewAnnouncementDto;
import pl.infoshare.workandfun.announcements.mappers.AddAndEditMapper;
import pl.infoshare.workandfun.announcements.mappers.QuickViewAnnouncementMapper;
import pl.infoshare.workandfun.exception.AnnouncementNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnnouncementServiceTest {

    @Mock
    private AnnouncementsRepository announcementsRepository;
    @Spy
    private AddAndEditMapper addAndEditMapper;
    @Spy
    private QuickViewAnnouncementMapper quickViewAnnouncementMapper;
    @Captor
    private ArgumentCaptor<Announcement> captor;
    @InjectMocks
    @Spy
    private AnnouncementService announcementService;

    private Announcement announcement;
    private Long id;

    @BeforeEach
    void init() {
        announcement = new Announcement(1L, Type.SERVICE_OFFER, "Wyprowadzam psy, koty, myszy, konie, słonie",
                ServiceType.INNE, "Warszawa", "dzielnica", "osiedle", "200", null, Voivodeship.MAZOWIECKIE, LocalDateTime.of(2020, 10, 10, 10, 10), "Andrzej",
                "andrzej@aa.pl", false, "Andrzej, czyli ja to miłośnik zwierząt chętnie spędzający z nimi czas, nie masz" +
                " co zrobić ze swoim zwierzakiem, zadzwoń do Andrzeja", "+48666666666", "z FV będzie drożej");
        id = 1L;
    }

    @Test
    void shouldFindAllSortedByCreateDateDescConvertToDto() {
        //given
        List<Announcement> announcements = List.of(this.announcement);
        when(announcementsRepository.findAllByOrderByDateDesc()).thenReturn(announcements);
        //when
        final var result = announcementService.findAllSortedByCreateDateDescConvertToDto();
        final var firstElement = result.iterator().next();
        //then
        assertThat(((Collection<?>) result))
                .hasSize(1)
                .hasExactlyElementsOfTypes(QuickViewAnnouncementDto.class);
        assertThat(firstElement).usingRecursiveComparison().ignoringFields("isIndividualPrice", "fullLocalization").isEqualTo(this.announcement);
        assertThat(firstElement.getFullLocalization()).isEqualTo("Warszawa, dzielnica, osiedle");
        assertThat(firstElement.isIndividualPrice()).isFalse();
        verify(quickViewAnnouncementMapper, times(announcements.size())).toDto(this.announcement);
    }

    @Test
    void shouldFindAllByQuerySpec() {
        announcementService.findAllByQuerySpec(mock(AnnouncementSpec.class));
        verify(announcementsRepository).findAll(any(AnnouncementSpec.class));
    }

    @Test
    void shouldThrowExceptionWhenFindByIdNotFoundAnnouncement() {
        //given
        when(announcementsRepository.findById(id)).thenReturn(Optional.empty());
        //when
        Throwable thrown = catchThrowable(() -> announcementService.findById(id));
        //then
        assertThat(thrown)
                .isExactlyInstanceOf(AnnouncementNotFoundException.class)
                .hasMessage("Brak ogłoszenia o numerze ID = " + id);
        verify(announcementsRepository).findById(id);
    }

    @Test
    void shouldFindById() {
        //given
        when(announcementsRepository.findById(id)).thenReturn(Optional.of(announcement));
        //when
        //then
        assertThat(announcement).isEqualTo(announcementService.findById(id));
        verify(announcementsRepository).findById(id);
    }

    @Test
    void shouldFindByIdAndConvertToDto() {
        //given
        when(announcementsRepository.findById(id)).thenReturn(Optional.of(announcement));
        //when
        final var result = announcementService.findByIdConvertToDto(id);
        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(announcement);
        verify(addAndEditMapper).toDto(announcement);
        verify(announcementsRepository).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenFindByIdConvertToDtoNotFoundAnnouncement() {
        //given
        when(announcementsRepository.findById(id)).thenReturn(Optional.empty());
        //when
        Throwable thrown = catchThrowable(() -> announcementService.findByIdConvertToDto(id));
        //then
        assertThat(thrown)
                .isExactlyInstanceOf(AnnouncementNotFoundException.class)
                .hasMessage("Brak ogłoszenia o numerze ID = " + id);
        verify(announcementsRepository).findById(id);
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

    @Test
    void shouldThrowExceptionWhenNotFoundAnnouncementToDeleteById() {
        //given
        when(announcementsRepository.findById(id)).thenReturn(Optional.empty());
        //when
        Throwable thrown = catchThrowable(() -> announcementService.deleteById(id));
        //then
        assertThat(thrown)
                .isExactlyInstanceOf(AnnouncementNotFoundException.class)
                .hasMessage("Brak ogłoszenia o numerze ID = " + id);
        verify(announcementsRepository).findById(id);
    }

    @Test
    void shouldSave() {
        //given
        AddAndEditAnnouncementDto dto = new AddAndEditAnnouncementDto(1L, Type.SERVICE_OFFER, "Wyprowadzam psy, koty, myszy, konie, słonie",
                ServiceType.INNE, "Warszawa", "dzielnica", "osiedle", "200", Voivodeship.MAZOWIECKIE, "Andrzej", "andrzej@aa.pl",
                false, "Andrzej, czyli ja to miłośnik zwierząt chętnie spędzający z nimi czas, nie masz co zrobić ze swoim zwierzakiem, zadzwoń do Andrzeja",
                "+48666666666", "z FV będzie drożej");
        given(addAndEditMapper.toEntity(dto)).willCallRealMethod();
        given(announcementsRepository.save(captor.capture())).willReturn(announcement);
        //when
        announcementService.save(dto);
        //then
        verify(addAndEditMapper).toEntity(dto);
        verify(announcementsRepository).save(any(Announcement.class));
    }

    @Test
    void shouldUpdate() {
        //given
        AddAndEditAnnouncementDto dto = new AddAndEditAnnouncementDto(1L, Type.SERVICE_OFFER, "Wyprowadzam psy, koty, myszy, konie, słonie",
                ServiceType.INNE, "Warszawa", "dzielnica", "osiedle", "200", Voivodeship.MAZOWIECKIE, "Andrzej", "andrzej@aa.pl",
                false, "Andrzej, czyli ja to miłośnik zwierząt chętnie spędzający z nimi czas, nie masz co zrobić ze swoim zwierzakiem, zadzwoń do Andrzeja",
                "+48666666666", "z FV będzie drożej");
        given(announcementsRepository.findById(id)).willReturn(Optional.of(announcement));
        given(announcementsRepository.save(announcement)).willReturn(announcement);
        //when
        Announcement actualResult = announcementService.update(id, dto);
        //then
        assertThat(actualResult).isSameAs(announcement);
        verify(announcementsRepository).findById(id);
        verify(announcementsRepository).save(same(announcement));
    }

    @Test
    void shouldSearchAllByParameterNonBlank() {
        //given
        List<QuickViewAnnouncementDto> quickViewAnnouncementDtoList = new ArrayList<>(List.of(
                new QuickViewAnnouncementDto(1L, "Wyprowadzam psy, koty, myszy, konie, słonie",
                        "200", false, LocalDateTime.of(2021, 12, 20, 10, 15), false, "Wrocław, Bielany",
                        "Andrzej, czyli ja to miłośnik zwierząt chętnie spędzający z nimi czas, nie masz co zrobić ze swoim zwierzakiem, zadzwoń do Andrzeja"),
                new QuickViewAnnouncementDto(2L, "Mechanik samochodowy",
                        "do ustalenia indywidualnie", true, LocalDateTime.of(2020, 10, 10, 10, 10), true, "Warszawa, Wola",
                        "Diagnostyka, wymiana filtrów/oleju, pompowanie kół"),
                new QuickViewAnnouncementDto(3L, "Przeprowadzki",
                        "150", false, LocalDateTime.of(2022, 1, 10, 11, 25), false, "Kraków",
                        "Mam bus, pomogę z przeprowadzką")));
        doReturn(quickViewAnnouncementDtoList).when(announcementService).findAllSortedByCreateDateDescConvertToDto();
//        given(announcementService.findAllSortedByCreateDateDescConvertToDto()).willReturn(quickViewAnnouncementDtoList);
        String parameter = "przeprowadz";
        //when
        var actualResult = announcementService.searchAllByParameter(parameter);
        //then
        assertThat(actualResult)
                .hasSize(1)
                .hasExactlyElementsOfTypes(QuickViewAnnouncementDto.class)
                .element(0).usingRecursiveComparison().isEqualTo(quickViewAnnouncementDtoList.get(2));
    }

    @Test
    void shouldSearchAllByParameterBlank() {
        //given
        List<QuickViewAnnouncementDto> quickViewAnnouncementDtoList = new ArrayList<>(List.of(
                new QuickViewAnnouncementDto(1L, "Wyprowadzam psy, koty, myszy, konie, słonie",
                        "200", false, LocalDateTime.of(2021, 12, 20, 10, 15), false, "Wrocław, Bielany",
                        "Andrzej, czyli ja to miłośnik zwierząt chętnie spędzający z nimi czas, nie masz co zrobić ze swoim zwierzakiem, zadzwoń do Andrzeja"),
                new QuickViewAnnouncementDto(2L, "Mechanik samochodowy",
                        "do ustalenia indywidualnie", true, LocalDateTime.of(2020, 10, 10, 10, 10), true, "Warszawa, Wola",
                        "Diagnostyka, wymiana filtrów/oleju, pompowanie kół"),
                new QuickViewAnnouncementDto(3L, "Przeprowadzki",
                        "150", false, LocalDateTime.of(2022, 1, 10, 11, 25), false, "Kraków",
                        "Mam bus, pomogę z przeprowadzką")));
        doReturn(quickViewAnnouncementDtoList).when(announcementService).findAllSortedByCreateDateDescConvertToDto();
        String parameter = "";
        //when
        var actualResult = announcementService.searchAllByParameter(parameter);
        //then
        assertThat(actualResult)
                .hasSize(quickViewAnnouncementDtoList.size())
                .hasOnlyElementsOfType(QuickViewAnnouncementDto.class)
                .usingRecursiveComparison().isEqualTo(quickViewAnnouncementDtoList);
    }


    @Test
    void shouldFillDB() {
        //given
        Announcement usedAnnouncement = new Announcement(
                5L, Type.SERVICE_OFFER, "Diagnostyka i serwis aut elektrycznych", ServiceType.MOTORYZACJA, "Legnica",
                "", "", "do ustalenia indywidualnie", null, Voivodeship.DOLNOSLASKIE, null,
                "Leszek", "rexoAuto@gmail.com", false,
                "Diagnostyka i serwis aut elektrycznych. Pomagam przy zakupie auta elektrycznego",
                "+48741741741", "");
        given(announcementsRepository.save(captor.capture())).willReturn(this.announcement);
        //when
        announcementService.fillDB();
        //then
        assertThat(captor.getValue()).usingRecursiveComparison().isEqualTo(usedAnnouncement);
        verify(announcementsRepository, times(captor.getAllValues().size())).save(any(Announcement.class));
        List<Announcement> announcements = captor.getAllValues();
        for (Announcement announcement : announcements) {
            verify(announcementsRepository).save(announcement);
        }
    }
}