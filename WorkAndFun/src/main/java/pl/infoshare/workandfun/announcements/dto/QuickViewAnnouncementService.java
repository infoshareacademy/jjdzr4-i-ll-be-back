package pl.infoshare.workandfun.announcements.dto;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class QuickViewAnnouncementService {

    public String formatAnnouncementCreateDate(QuickViewAnnouncementDto dto) {
        LocalDateTime now = LocalDateTime.now();
        if (now.getYear() != dto.getDate().getYear()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.forLanguageTag("PL"));
            return dto.getDate().format(formatter);
        } else if (now.getMonthValue() != dto.getDate().getMonthValue() || now.getDayOfMonth() - dto.getDate().getDayOfMonth() > 1) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM", Locale.forLanguageTag("PL"));
            return dto.getDate().format(formatter);
        } else if ((now.getDayOfMonth() - dto.getDate().getDayOfMonth()) > 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.forLanguageTag("PL"));
            return "wczoraj " + dto.getDate().format(formatter);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.forLanguageTag("PL"));
            return "dzisiaj " + dto.getDate().format(formatter);
        }
    }
}
