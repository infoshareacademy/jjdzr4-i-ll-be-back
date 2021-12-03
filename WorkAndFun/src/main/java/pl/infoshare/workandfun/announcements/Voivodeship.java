package pl.infoshare.workandfun.announcements;

public enum Voivodeship {
    DOLNOSLASKIE("1", "Dolnośląskie"),
    KUJAWSKO_POMORSKIE("2", "Kujawsko-pomorskie"),
    LODZKIE("3", "Łódzkie"),
    LUBELSKIE("4", "Lubelskie"),
    LUBUSKIE("5", "Lubuskie"),
    MALOPOLSKIE("6", "Małopolskie"),
    MAZOWIECKIE("7", "Mazowieckie"),
    OPOLSKIE("8", "Opolskie"),
    PODKARPACKIE("9", "Podkarpackie"),
    PODLASKIE("10", "Podlaskie"),
    POMORSKIE("11", "Pomorskie"),
    SLASKIE("12", "Śląskie"),
    SWIETOKRZYSKIE("13", "Świętokrzyskie"),
    WARMINSKO_MAZURSKIE("14", "Warmińsko-mazurskie"),
    WIELKOPOLSKIE("15", "Wielkopolskie"),
    ZACHODNIOPOMORSKIE("16", "Zachodniopomorskie");

    private final String sequentialNumber;
    private final String voivodeshipName;

    Voivodeship(String sequentialNumber, String voivodeshipName) {
        this.sequentialNumber = sequentialNumber;
        this.voivodeshipName = voivodeshipName;
    }

    public String getSequentialNumber() {
        return sequentialNumber;
    }

    public String getVoivodeshipName() {
        return voivodeshipName;
    }
}
