package pl.infoshare.announcements;

import java.util.ArrayList;
import java.util.List;

public class Hierarchy {

    private long id;
    private ServiceType serviceType;
    private Hierarchy parent = null; //jeśli null to jesteś root -> wyżej nie wejdziesz
    private List<Hierarchy> children = new ArrayList<>(); //jeśłi puste -> niżej nie zejdziesz

    private Hierarchy(long id, ServiceType serviceType, List<Hierarchy> children) {
        this.id = id;
        this.serviceType = serviceType;
        setChildren(children);
    }

    private Hierarchy(long id, ServiceType serviceType) {
        this.id = id;
        this.serviceType = serviceType;
    }

    public Hierarchy() {

    }

    public long getId() {
        return id;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public Hierarchy getParent() {
        return parent;
    }

    public List<Hierarchy> getChildren() {
        return children;
    }

    private void setChildren(List<Hierarchy> children) {
        for (Hierarchy particularHierarchy : children) {
            particularHierarchy.parent = this;
        }
        this.children = children;
    }

    public Hierarchy actualCategory() {
        return new Hierarchy(0, ServiceType.GLOWNA_KATEGORIA, List.of(
                new Hierarchy(1, ServiceType.BUDOWA_DOMU, List.of(
                        new Hierarchy(1, ServiceType.BUDOWA_DOMU_BUDOWA_OD_PODSTAW),
                        new Hierarchy(2, ServiceType.BUDOWA_DOMU_DACH_I_ELEWACJA),
                        new Hierarchy(3, ServiceType.BUDOWA_DOMU_GARAZE_I_BRAMY),
                        new Hierarchy(4, ServiceType.BUDOWA_DOMU_INSTALACJE),
                        new Hierarchy(5, ServiceType.BUDOWA_DOMU_NA_ZEWNATRZ),
                        new Hierarchy(6, ServiceType.BUDOWA_DOMU_PODLOGI),
                        new Hierarchy(7, ServiceType.BUDOWA_DOMU_SCIANY_I_SUFIT),
                        new Hierarchy(8, ServiceType.BUDOWA_DOMU_POZOSTALE)
                )),
                new Hierarchy(2, ServiceType.ELEKTRYK),
                new Hierarchy(3, ServiceType.HYDRAULIK, List.of(
                        new Hierarchy(1, ServiceType.HYDRAULIK_INSTALACJA_CENTRALNEGO_OGRZEWANIA),
                        new Hierarchy(2, ServiceType.HYDRAULIK_INSTALACJA_GAZOWA),
                        new Hierarchy(3, ServiceType.HYDRAULIK_MONTAZ_WANNY),
                        new Hierarchy(4, ServiceType.HYDRAULIK_MONTAZ_WC),
                        new Hierarchy(5, ServiceType.HYDRAULIK_OGRZEWANIE_PODLOGOWE_ELEKTRYCZNE),
                        new Hierarchy(6, ServiceType.HYDRAULIK_UDRAZNIANIE_RUR),
                        new Hierarchy(7, ServiceType.HYDRAULIK_POZOSTALE_USLUGI_HYDRAULICZNE),
                        new Hierarchy(8, ServiceType.HYDRAULIK_POZOSTALE)
                )),
                new Hierarchy(4, ServiceType.MALARZ),
                new Hierarchy(5, ServiceType.MEBLE_I_ZABUDOWA, List.of(
                        new Hierarchy(1, ServiceType.MEBLE_I_ZABUDOWA_NAPRAWA_MEBLI),
                        new Hierarchy(2, ServiceType.MEBLE_I_ZABUDOWA_LUSTRA_NA_WYMIAR),
                        new Hierarchy(3, ServiceType.MEBLE_I_ZABUDOWA_MALOWANIE_MEBLI),
                        new Hierarchy(4, ServiceType.MEBLE_I_ZABUDOWA_MEBLE_KUCHENNE_NA_WYMIAR),
                        new Hierarchy(5, ServiceType.MEBLE_I_ZABUDOWA_MEBLE_NA_WYMIAR),
                        new Hierarchy(6, ServiceType.MEBLE_I_ZABUDOWA_MEBLE_LAZIENKOWE_NA_WYMIAR),
                        new Hierarchy(7, ServiceType.MEBLE_I_ZABUDOWA_OKLEJANIE_MEBLI),
                        new Hierarchy(8, ServiceType.MEBLE_I_ZABUDOWA_STOLARZ),
                        new Hierarchy(9, ServiceType.MEBLE_I_ZABUDOWA_SZKLARZ),
                        new Hierarchy(10, ServiceType.MEBLE_I_ZABUDOWA_POZOSTALE)
                )),
                new Hierarchy(6, ServiceType.MOTORYZACJA, List.of(
                        new Hierarchy(1, ServiceType.MOTORYZACJA_BLACHARZ_SAMOCHODOWY),
                        new Hierarchy(2, ServiceType.MOTORYZACJA_GEOMETRIA_KOL),
                        new Hierarchy(3, ServiceType.MOTORYZACJA_INSTALACJA_LPG),
                        new Hierarchy(4, ServiceType.MOTORYZACJA_LAKIERNIK),
                        new Hierarchy(5, ServiceType.MOTORYZACJA_MECHANIK_MOTOCYKLOWY),
                        new Hierarchy(6, ServiceType.MOTORYZACJA_NAPRAWA_SZYB_SAMOCHODOWYCH),
                        new Hierarchy(7, ServiceType.MOTORYZACJA_NAUKA_JAZDY),
                        new Hierarchy(8, ServiceType.MOTORYZACJA_PRZYCIEMNIANIE_SZYB),
                        new Hierarchy(9, ServiceType.MOTORYZACJA_SERWIS_INSTALACJI_LPG),
                        new Hierarchy(10, ServiceType.MOTORYZACJA_SPAWACZ),
                        new Hierarchy(11, ServiceType.MOTORYZACJA_TAPICER_SAMOCHODOWY),
                        new Hierarchy(12, ServiceType.MOTORYZACJA_TUNING_SAMOCHODOW),
                        new Hierarchy(13, ServiceType.MOTORYZACJA_WULKANIZACJA_OPON),
                        new Hierarchy(14, ServiceType.MOTORYZACJA_WYMIANA_OLEJU),
                        new Hierarchy(15, ServiceType.MOTORYZACJA_WYMIANA_SPRZEGLA),
                        new Hierarchy(16, ServiceType.MOTORYZACJA_POZOSTALE)
                )),
                new Hierarchy(7, ServiceType.OGROD),
                new Hierarchy(8, ServiceType.ORGANIZACJA_IMPREZ),
                new Hierarchy(9, ServiceType.PROJEKTOWANIE),
                new Hierarchy(10, ServiceType.REMONT, List.of(
                        new Hierarchy(1, ServiceType.REMONT_BALUSTRADY_I_PORECZE),
                        new Hierarchy(2, ServiceType.REMONT_DACH_I_ELEWACJA),
                        new Hierarchy(3, ServiceType.REMONT_INSTALACJE),
                        new Hierarchy(4, ServiceType.REMONT_INTELIGENTNY_DOM),
                        new Hierarchy(5, ServiceType.REMONT_PODLOGI),
                        new Hierarchy(6, ServiceType.REMONT_REMONTY_BUDYNKOW),
                        new Hierarchy(7, ServiceType.REMONT_USLUGI_MALARSKIE),
                        new Hierarchy(8, ServiceType.REMONT_WYKONCZENIE_WNETRZ),
                        new Hierarchy(9, ServiceType.REMONT_SCIANY_I_SUFIT),
                        new Hierarchy(10, ServiceType.REMONT_POZOSTALE)
                )),
                new Hierarchy(11, ServiceType.SPRZATANIE),
                new Hierarchy(12, ServiceType.SZKOLENIA_I_JEZYKI_OBCE, List.of(
                        new Hierarchy(1, ServiceType.SZKOLENIA_I_JEZYKI_OBCE_NAUKA_JAZDY),
                        new Hierarchy(2, ServiceType.SZKOLENIA_I_JEZYKI_OBCE_NAUKA_JEZYKOW),
                        new Hierarchy(3, ServiceType.SZKOLENIA_I_JEZYKI_OBCE_SZKOLENIA_PRZECIWPOZAROWE),
                        new Hierarchy(4, ServiceType.SZKOLENIA_I_JEZYKI_OBCE_SZKOLENIA_Z_PIERWSZEJ_POMOCY),
                        new Hierarchy(5, ServiceType.SZKOLENIA_I_JEZYKI_OBCE_SZKOLA_JEZYKOWA),
                        new Hierarchy(6, ServiceType.SZKOLENIA_I_JEZYKI_OBCE_TLUMACZENIE_PRZYSIEGLE),
                        new Hierarchy(7, ServiceType.SZKOLENIA_I_JEZYKI_OBCE_TLUMACZENIE_STRON_INTERNETOWYCH)
                )),
                new Hierarchy(13, ServiceType.TRANSPORT, List.of(
                        new Hierarchy(1, ServiceType.TRANSPORT_AUTO_DO_SLUBU),
                        new Hierarchy(2, ServiceType.TRANSPORT_PRZEPROWADZKI),
                        new Hierarchy(3, ServiceType.TRANSPORT_SPEDYCJA),
                        new Hierarchy(4, ServiceType.TRANSPORT_TRANSPORT_SAMOCHODOW_KRAJOWY),
                        new Hierarchy(5, ServiceType.TRANSPORT_TRANSPORT_SAMOCHODOW_ZAGRANICZNY),
                        new Hierarchy(6, ServiceType.TRANSPORT_TRANSPORT_ZAGRANICZNY),
                        new Hierarchy(7, ServiceType.TRANSPORT_WYNAJEM_AUTOKAROW),
                        new Hierarchy(8, ServiceType.TRANSPORT_WYNAJEM_BUSOW),
                        new Hierarchy(9, ServiceType.TRANSPORT_WYNAJEM_LIMUZYNY),
                        new Hierarchy(10, ServiceType.TRANSPORT_ZARZADZANIE_FLOTA)
                )),
                new Hierarchy(14, ServiceType.USLUGI_DLA_BIZNESU),
                new Hierarchy(15, ServiceType.MONTAZ_I_NAPRAWA),
                new Hierarchy(16, ServiceType.USLUGI_FINANSOWE),
                new Hierarchy(17, ServiceType.USLUGI_PRAWNE_I_ADMINISTRACYJNE),
                new Hierarchy(18, ServiceType.USLUGI_ZDALNE),
                new Hierarchy(19, ServiceType.ZDROWIE_I_URODA),
                new Hierarchy(20, ServiceType.ZLOTA_RACZKA),
                new Hierarchy(21, ServiceType.INNE)
        ));
    }

    @Override
    public String toString() {
        String s = "{" + serviceType.getServiceTypeName();
        if (parent != null) {
            s = s + " | parent = " + parent.serviceType.getServiceTypeName();
        } else {
            s = s + " | parent = null";
        }
        s = s + " | \nchildren = " + children +
                "}";
        return s;
    }
}

