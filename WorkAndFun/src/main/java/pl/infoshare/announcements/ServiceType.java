package pl.infoshare.announcements;

public enum ServiceType {
    GLOWNA_KATEGORIA("0", "Głowna kategoria",0),
    BUDOWA_DOMU("1", "Budowa domu",1),
    ELEKTRYK("2", "Elektryk",1),
    HYDRAULIK("3", "Hydraulik",1),
    MALARZ("4", "Malarz",1),
    MEBLE_I_ZABUDOWA("5", "Meble i zabudowa",1),
    MOTORYZACJA("6", "Motoryzacja",1),
    OGROD("7", "Ogród",1),
    ORGANIZACJA_IMPREZ("8", "Organizacja imprez",1),
    PROJEKTOWANIE("9", "Projektowanie",1),
    REMONT("10", "Remont",1),
    SPRZATANIE("11", "Sprzątanie",1),
    SZKOLENIA_I_JEZYKI_OBCE("12", "Szkolenia i języki obce",1),
    TRANSPORT("13", "Transport",1),
    USLUGI_DLA_BIZNESU("14", "Usługi dla biznesu",1),
    MONTAZ_I_NAPRAWA("15", "Montaż i naprawa",1),
    USLUGI_FINANSOWE("16", "Usługi finansowe",1),
    USLUGI_PRAWNE_I_ADMINISTRACYJNE("17", "Usługi prawne i administracyjne",1),
    USLUGI_ZDALNE("18", "Usługi zdalne",1),
    ZDROWIE_I_URODA("19", "Zdrowie i uroda",1),
    ZLOTA_RACZKA("20", "Złota rączka",1),
    INNE("21", "Inne",1),

    BUDOWA_DOMU_BUDOWA_OD_PODSTAW("22", "Budowa od podstaw",2),
    BUDOWA_DOMU_DACH_I_ELEWACJA("23", "Dach i elewacja",2),
    BUDOWA_DOMU_GARAZE_I_BRAMY("24", "Garaze i bramy",2),
    BUDOWA_DOMU_INSTALACJE("25", "Instalacje",2),
    BUDOWA_DOMU_NA_ZEWNATRZ("26", "Na zewnatrz",2),
    BUDOWA_DOMU_PODLOGI("27", "Podlogi",2),
    BUDOWA_DOMU_SCIANY_I_SUFIT("28", "Sciany i sufit",2),
    BUDOWA_DOMU_POZOSTALE("29", "Pozostale",2),
    HYDRAULIK_INSTALACJA_CENTRALNEGO_OGRZEWANIA("30", "Instalacja centralnego ogrzewania",2),
    HYDRAULIK_INSTALACJA_GAZOWA("31", "Instalacja gazowa",2),
    HYDRAULIK_MONTAZ_WANNY("32", "Montaz wanny",2),
    HYDRAULIK_MONTAZ_WC("33", "Montaz wc",2),
    HYDRAULIK_OGRZEWANIE_PODLOGOWE_ELEKTRYCZNE("34", "Ogrzewanie podlogowe elektryczne",2),
    HYDRAULIK_UDRAZNIANIE_RUR("35", "Udraznianie rur",2),
    HYDRAULIK_POZOSTALE_USLUGI_HYDRAULICZNE("36", "Pozostale uslugi hydrauliczne",2),
    HYDRAULIK_POZOSTALE("37", "Pozostale",2),
    MEBLE_I_ZABUDOWA_NAPRAWA_MEBLI("38", "Naprawa mebli", 2),
    MEBLE_I_ZABUDOWA_LUSTRA_NA_WYMIAR("39", "Lustra na wymiar", 2),
    MEBLE_I_ZABUDOWA_MALOWANIE_MEBLI("40", "Malowanie mebli", 2),
    MEBLE_I_ZABUDOWA_MEBLE_KUCHENNE_NA_WYMIAR("41", "Meble kuchenne na wymiar", 2),
    MEBLE_I_ZABUDOWA_MEBLE_NA_WYMIAR("42", "Meble na wymiar", 2),
    MEBLE_I_ZABUDOWA_MEBLE_LAZIENKOWE_NA_WYMIAR("43", "Meble lazienkowe na wymiar", 2),
    MEBLE_I_ZABUDOWA_OKLEJANIE_MEBLI("44", "Oklejanie mebli", 2),
    MEBLE_I_ZABUDOWA_STOLARZ("45", "Stolarz", 2),
    MEBLE_I_ZABUDOWA_SZKLARZ("46", "Szklarz", 2),
    MEBLE_I_ZABUDOWA_POZOSTALE("47", "Pozostale", 2),
    MOTORYZACJA_BLACHARZ_SAMOCHODOWY("48", "Blacharz samochodowy", 2),
    MOTORYZACJA_GEOMETRIA_KOL("49", "Geometria kol", 2),
    MOTORYZACJA_INSTALACJA_LPG("50", "Instalacja lpg", 2),
    MOTORYZACJA_LAKIERNIK("51", "Lakiernik", 2),
    MOTORYZACJA_MECHANIK_MOTOCYKLOWY("52", "Mechanik motocyklowy", 2),
    MOTORYZACJA_NAPRAWA_SZYB_SAMOCHODOWYCH("53", "Naprawa szyb samochodowych", 2),
    MOTORYZACJA_NAUKA_JAZDY("54", "Nauka jazdy", 2),
    MOTORYZACJA_PRZYCIEMNIANIE_SZYB("55", "Przyciemnianie szyb", 2),
    MOTORYZACJA_SERWIS_INSTALACJI_LPG("56", "Serwis instalacji lpg", 2),
    MOTORYZACJA_SPAWACZ("57", "Spawacz", 2),
    MOTORYZACJA_TAPICER_SAMOCHODOWY("58", "Tapicer samochodowy", 2),
    MOTORYZACJA_TUNING_SAMOCHODOW("59", "Tuning samochodow", 2),
    MOTORYZACJA_WULKANIZACJA_OPON("60", "Wulkanizacja opon", 2),
    MOTORYZACJA_WYMIANA_OLEJU("61", "Wymiana oleju", 2),
    MOTORYZACJA_WYMIANA_SPRZEGLA("62", "Wymiana sprzegla", 2),
    MOTORYZACJA_POZOSTALE("63", "Pozostale", 2),
    SZKOLENIA_I_JEZYKI_OBCE_NAUKA_JAZDY("64", "Nauka jazdy", 2),
    SZKOLENIA_I_JEZYKI_OBCE_NAUKA_JEZYKOW("65", "Nauka jezykow", 2),
    SZKOLENIA_I_JEZYKI_OBCE_SZKOLENIA_PRZECIWPOZAROWE("66", "Szkolenia przeciwpozarowe", 2),
    SZKOLENIA_I_JEZYKI_OBCE_SZKOLENIA_Z_PIERWSZEJ_POMOCY("67", "Szkolenia z pierwszej pomocy", 2),
    SZKOLENIA_I_JEZYKI_OBCE_SZKOLA_JEZYKOWA("68", "Szkola jezykowa", 2),
    SZKOLENIA_I_JEZYKI_OBCE_TLUMACZENIE_PRZYSIEGLE("69", "Tlumaczenie przysiegle", 2),
    SZKOLENIA_I_JEZYKI_OBCE_TLUMACZENIE_STRON_INTERNETOWYCH("70", "Tlumaczenie stron internetowych", 2),
    TRANSPORT_AUTO_DO_SLUBU("71", "Auto do slubu", 2),
    TRANSPORT_PRZEPROWADZKI("72", "Przeprowadzki", 2),
    TRANSPORT_SPEDYCJA("73", "Spedycja", 2),
    TRANSPORT_TRANSPORT_SAMOCHODOW_KRAJOWY("74", "Samochodow krajowy", 2),
    TRANSPORT_TRANSPORT_SAMOCHODOW_ZAGRANICZNY("75", "Samochodow zagraniczny", 2),
    TRANSPORT_TRANSPORT_ZAGRANICZNY("76", "Zagraniczny", 2),
    TRANSPORT_WYNAJEM_AUTOKAROW("77", "Wynajem autokarow", 2),
    TRANSPORT_WYNAJEM_BUSOW("78", "Wynajem busow", 2),
    TRANSPORT_WYNAJEM_LIMUZYNY("79", "Wynajem limuzyny", 2),
    TRANSPORT_ZARZADZANIE_FLOTA("80", "Zarzadzanie flota", 2),
    REMONT_BALUSTRADY_I_PORECZE("81", "Balustrady i porecze", 2),
    REMONT_DACH_I_ELEWACJA("82", "Dach i elewacja", 2),
    REMONT_INSTALACJE("83", "Instalacje", 2),
    REMONT_INTELIGENTNY_DOM("84", "Inteligentny dom", 2),
    REMONT_PODLOGI("85", "Podlogi", 2),
    REMONT_REMONTY_BUDYNKOW("86", "Remonty budynkow", 2),
    REMONT_USLUGI_MALARSKIE("87", "Uslugi malarskie", 2),
    REMONT_WYKONCZENIE_WNETRZ("88", "Wykonczenie wnetrz", 2),
    REMONT_SCIANY_I_SUFIT("89", "Sciany i sufit", 2),
    REMONT_POZOSTALE("90", "Pozostale", 2);


    private final String sequentialNumber;
    private final String serviceTypeName;
    private final int level;

    ServiceType(String sequentialNumber, String serviceTypeName, int level) {
        this.sequentialNumber = sequentialNumber;
        this.serviceTypeName = serviceTypeName;
        this.level = level;
    }

    public String getSequentialNumber() {
        return sequentialNumber;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public int getLevel() {
        return level;
    }

}
