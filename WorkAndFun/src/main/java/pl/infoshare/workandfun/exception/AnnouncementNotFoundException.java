package pl.infoshare.workandfun.exception;

public class AnnouncementNotFoundException extends RuntimeException{

    public AnnouncementNotFoundException(Long id) {
        super("Brak ogłoszenia o numerze ID = " + id);
    }
}
