package pl.infoshare.workandfun.announcements;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import pl.infoshare.workandfun.users.User;
import pl.infoshare.workandfun.users.UserRepository;

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
    @Mock
    private UserRepository userRepository;
    @Spy
    private AddAndEditMapper addAndEditMapper;
    @Spy
    private QuickViewAnnouncementMapper quickViewAnnouncementMapper;
    @Captor
    private ArgumentCaptor<Announcement> captor;
    @Captor
    private ArgumentCaptor<String> captorUsername;
    @InjectMocks
    @Spy
    private AnnouncementService announcementService;

    private Announcement announcement;
    private Announcement comparableAnnouncement;
    private Long id;

    @BeforeEach
    void init() throws JsonProcessingException {
        announcement = new Announcement(1L, Type.SERVICE_OFFER, "Wyprowadzam psy, koty, myszy, konie, słonie",
                ServiceType.INNE, "Warszawa", "dzielnica", "osiedle", "200", null, Voivodeship.MAZOWIECKIE, LocalDateTime.of(2020, 10, 10, 10, 10), "Andrzej",
                "andrzej@aa.pl", false, "Andrzej, czyli ja to miłośnik zwierząt chętnie spędzający z nimi czas, nie masz" +
                " co zrobić ze swoim zwierzakiem, zadzwoń do Andrzeja", "+48666666666", "z FV będzie drożej");
        id = 1L;
        ObjectMapper om = new ObjectMapper().registerModule(new JavaTimeModule());
        comparableAnnouncement = om.readValue(om.writeValueAsString(announcement), Announcement.class);
    }

    @Test
    void shouldFindAllSortedByCreateDateDescConvertToDto() {
        //given
        List<Announcement> announcements = List.of(announcement);
        when(announcementsRepository.findAllByOrderByDateDesc()).thenReturn(announcements);
        //when
        final var result = announcementService.findAllSortedByCreateDateDescConvertToDto();
        final var firstElement = result.iterator().next();
        //then
        assertThat(((Collection<?>) result))
                .hasSize(1)
                .hasExactlyElementsOfTypes(QuickViewAnnouncementDto.class);
        assertThat(firstElement).usingRecursiveComparison().ignoringFields("isIndividualPrice", "fullLocalization").isEqualTo(comparableAnnouncement);
        assertThat(firstElement.getFullLocalization()).isEqualTo("Warszawa, dzielnica, osiedle");
        assertThat(firstElement.isIndividualPrice()).isFalse();
        verify(quickViewAnnouncementMapper, times(announcements.size())).toDto(announcement);
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
        assertThat(result).usingRecursiveComparison().isEqualTo(comparableAnnouncement);
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
        String usernameSample = "testUsername";
        AddAndEditAnnouncementDto dto = new AddAndEditAnnouncementDto(1L, Type.SERVICE_OFFER, "Wyprowadzam psy, koty, myszy, konie, słonie",
                ServiceType.INNE, "Warszawa", "dzielnica", "osiedle", "200", Voivodeship.MAZOWIECKIE, "Andrzej", "andrzej@aa.pl",
                false, "Andrzej, czyli ja to miłośnik zwierząt chętnie spędzający z nimi czas, nie masz co zrobić ze swoim zwierzakiem, zadzwoń do Andrzeja",
                "+48666666666", "z FV będzie drożej");
        User testUser = new User();
        testUser.setUsername(usernameSample);
        given(userRepository.findByUsername(captorUsername.capture())).willReturn(Optional.of(testUser));
        given(addAndEditMapper.toEntity(dto)).willCallRealMethod();
        given(announcementsRepository.save(captor.capture())).willReturn(announcement);
        //when
        announcementService.save(dto, usernameSample);
        //then
        verify(addAndEditMapper).toEntity(dto);
        verify(announcementsRepository).save(any(Announcement.class));
        verify(userRepository).findByUsername(usernameSample);
        assertThat(captor.getValue().getOwner()).isSameAs(testUser);
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
        String parameter = "przeprowadz";
        //when
        Iterable<QuickViewAnnouncementDto> actualResult = announcementService.searchAllByParameter(parameter);
        //then
        assertThat(actualResult)
                .hasSize(1)
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
        Iterable<QuickViewAnnouncementDto> actualResult = announcementService.searchAllByParameter(parameter);
        //then
        assertThat(actualResult)
                .hasSize(quickViewAnnouncementDtoList.size())
                .usingRecursiveComparison().isEqualTo(quickViewAnnouncementDtoList);
    }
}