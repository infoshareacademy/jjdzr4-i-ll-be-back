package pl.infoshare;

public enum ServiceTypes {
    BUDOWADOMU("1","Budowa domu"),
    ELEKTRYK("2","Elektryk"),
    HYDRAULIK("3","Hydraulik"),
    MALARZ("4","Malarz"),
    MEBLEIZABUDOWA("5","Meble i zabudowa"),
    MOTORYZACJA("6","Motoryzacja"),
    OGROD("7","Ogród"),
    ORGANIZACJAIMPREZ("8","Organizacja imprez"),
    PROJEKTOWANIE("9","Projektowanie"),
    REMONT("10","Remont"),
    SPRZATANIE("11","Sprzątanie"),
    SZKOLENIAIJEZYKIOBCE("12","Szkolenia i języki obce"),
    TRANSPORT("13","Transport"),
    USLUGIDLABIZNESU("14","Usługi dla biznesu"),
    MONTAZINAPRAWA("15","Montaż i naprawa"),
    USLUGIFINANSOWE("16","Usługi finansowe"),
    USLUGIPRAWNEIADMINISTRACYJNE("17","Usługi prawne i administracyjne"),
    USLUGIZDALNE("18","Usługi zdalne"),
    ZDROWIEIURODA("19","Zdrowie i uroda"),
    ZLOTARACZKA("20","Złota rączka"),
    INNE("21","Inne");

    private String sequentialNumber;
    private String serviceTypeName;

    ServiceTypes(String sequentialNumber, String serviceTypeName){
        this.sequentialNumber=sequentialNumber;
        this.serviceTypeName = serviceTypeName;
    }

    public String getSequentialNumber() {
        return sequentialNumber;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }
}
