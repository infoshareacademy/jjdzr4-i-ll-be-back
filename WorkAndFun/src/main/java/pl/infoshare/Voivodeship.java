package pl.infoshare;

public enum Voivodeship {
    Dolnoslaskie("1", "Dolnośląskie"),
    KujawskoPomorskie("2", "Kujawsko-pomorskie"),
    Lodzkie("3","Łódzkie"),
    Lubelskie("4","Lubelskie"),
    Lubuskie("5","Lubuskie"),
    Malopolskie("6","Małopolskie"),
    Mazowieckie("7","Mazowieckie"),
    Opolskie("8","Opolskie"),
    Podkarpackie("9","Podkarpackie"),
    Podlaskie("10","Podlaskie"),
    Pomorskie("11","Pomorskie"),
    Slaskie("12","Śląskie"),
    Swietokrzyskie("13","Świętokrzyskie"),
    WarminskoMazurskie("14","Warmińsko-mazurskie"),
    Wielkopolskie("15","Wielkopolskie"),
    Zachodniopomorskie("16","Zachodniopomorskie");

    private String sequentialNumber;
    private String voivodeshipName;

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