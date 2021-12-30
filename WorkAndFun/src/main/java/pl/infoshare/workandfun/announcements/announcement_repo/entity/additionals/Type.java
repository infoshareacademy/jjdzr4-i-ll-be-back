package pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals;

public enum Type {
    SERVICE_OFFER("Oferuję usługę"),
    SERVICE_DEMAND("Szukam usługi");

    private final String value;

    Type(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
