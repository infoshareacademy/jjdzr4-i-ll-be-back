package pl.infoshare.workandfun.announcements.announcement_repo.entity.additionals;

public enum Voivodeship {
    DOLNOSLASKIE("Dolnośląskie"),
    KUJAWSKO_POMORSKIE( "Kujawsko-pomorskie"),
    LODZKIE("Łódzkie"),
    LUBELSKIE(  "Lubuskie"),
    MALOPOLSKIE( "Małopolskie"),
    MAZOWIECKIE( "Mazowieckie"),
    OPOLSKIE("Opolskie"),
    PODKARPACKIE( "Podkarpackie"),
    PODLASKIE( "Podlaskie"),
    POMORSKIE( "Pomorskie"),
    SLASKIE( "Śląskie"),
    SWIETOKRZYSKIE( "Świętokrzyskie"),
    WARMINSKO_MAZURSKIE( "Warmińsko-mazurskie"),
    WIELKOPOLSKIE( "Wielkopolskie"),
    ZACHODNIOPOMORSKIE( "Zachodniopomorskie");

    private final String voivodeshipName;

    Voivodeship(String voivodeshipName) {
        this.voivodeshipName = voivodeshipName;
    }

    public String getVoivodeshipName() {
        return voivodeshipName;
    }
}
