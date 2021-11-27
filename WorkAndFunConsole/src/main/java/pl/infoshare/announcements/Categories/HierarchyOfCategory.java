package pl.infoshare.announcements.Categories;

import java.util.ArrayList;
import java.util.List;

public class HierarchyOfCategory {

    private long id;
    private ServiceType serviceType;
    private HierarchyOfCategory levelUp = null; //jeśli null to jesteś root -> wyżej nie wejdziesz
    private List<HierarchyOfCategory> levelDown = new ArrayList<>(); //jeśłi puste -> niżej nie zejdziesz

    public HierarchyOfCategory() {

    }

    private HierarchyOfCategory(long id, ServiceType serviceType, List<HierarchyOfCategory> levelDown) {
        this.id = id;
        this.serviceType = serviceType;
        setLevels(levelDown);
    }

    private HierarchyOfCategory(long id, ServiceType serviceType) {
        this.id = id;
        this.serviceType = serviceType;
    }

    public long getId() {
        return id;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public List<HierarchyOfCategory> getLevelDown() {
        return levelDown;
    }

    /**
     * Set both levels to category; Firstly to each child assign "this" category as a parent. Next, each child assign as a level-down category for parent
     * @param children list of level-down categories
     */
    private void setLevels(List<HierarchyOfCategory> children) {
        for (HierarchyOfCategory child : children) {
            child.levelUp = this;
        }
        this.levelDown = children;
    }

    public HierarchyOfCategory initializeCategories() {
        return new HierarchyOfCategory(0, ServiceType.GLOWNA_KATEGORIA, List.of(
                new HierarchyOfCategory(1, ServiceType.BUDOWA_DOMU, List.of(
                        new HierarchyOfCategory(1, ServiceType.BUDOWA_DOMU_BUDOWA_OD_PODSTAW),
                        new HierarchyOfCategory(2, ServiceType.BUDOWA_DOMU_DACH_I_ELEWACJA),
                        new HierarchyOfCategory(3, ServiceType.BUDOWA_DOMU_GARAZE_I_BRAMY),
                        new HierarchyOfCategory(4, ServiceType.BUDOWA_DOMU_INSTALACJE),
                        new HierarchyOfCategory(5, ServiceType.BUDOWA_DOMU_NA_ZEWNATRZ),
                        new HierarchyOfCategory(6, ServiceType.BUDOWA_DOMU_PODLOGI),
                        new HierarchyOfCategory(7, ServiceType.BUDOWA_DOMU_SCIANY_I_SUFIT),
                        new HierarchyOfCategory(8, ServiceType.BUDOWA_DOMU_POZOSTALE)
                )),
                new HierarchyOfCategory(2, ServiceType.ELEKTRYK),
                new HierarchyOfCategory(3, ServiceType.HYDRAULIK, List.of(
                        new HierarchyOfCategory(1, ServiceType.HYDRAULIK_INSTALACJA_CENTRALNEGO_OGRZEWANIA),
                        new HierarchyOfCategory(2, ServiceType.HYDRAULIK_INSTALACJA_GAZOWA),
                        new HierarchyOfCategory(3, ServiceType.HYDRAULIK_MONTAZ_WANNY),
                        new HierarchyOfCategory(4, ServiceType.HYDRAULIK_MONTAZ_WC),
                        new HierarchyOfCategory(5, ServiceType.HYDRAULIK_OGRZEWANIE_PODLOGOWE_ELEKTRYCZNE),
                        new HierarchyOfCategory(6, ServiceType.HYDRAULIK_UDRAZNIANIE_RUR),
                        new HierarchyOfCategory(7, ServiceType.HYDRAULIK_POZOSTALE_USLUGI_HYDRAULICZNE),
                        new HierarchyOfCategory(8, ServiceType.HYDRAULIK_POZOSTALE)
                )),
                new HierarchyOfCategory(4, ServiceType.MALARZ),
                new HierarchyOfCategory(5, ServiceType.MEBLE_I_ZABUDOWA, List.of(
                        new HierarchyOfCategory(1, ServiceType.MEBLE_I_ZABUDOWA_NAPRAWA_MEBLI),
                        new HierarchyOfCategory(2, ServiceType.MEBLE_I_ZABUDOWA_LUSTRA_NA_WYMIAR),
                        new HierarchyOfCategory(3, ServiceType.MEBLE_I_ZABUDOWA_MALOWANIE_MEBLI),
                        new HierarchyOfCategory(4, ServiceType.MEBLE_I_ZABUDOWA_MEBLE_KUCHENNE_NA_WYMIAR),
                        new HierarchyOfCategory(5, ServiceType.MEBLE_I_ZABUDOWA_MEBLE_NA_WYMIAR),
                        new HierarchyOfCategory(6, ServiceType.MEBLE_I_ZABUDOWA_MEBLE_LAZIENKOWE_NA_WYMIAR),
                        new HierarchyOfCategory(7, ServiceType.MEBLE_I_ZABUDOWA_OKLEJANIE_MEBLI),
                        new HierarchyOfCategory(8, ServiceType.MEBLE_I_ZABUDOWA_STOLARZ),
                        new HierarchyOfCategory(9, ServiceType.MEBLE_I_ZABUDOWA_SZKLARZ),
                        new HierarchyOfCategory(10, ServiceType.MEBLE_I_ZABUDOWA_POZOSTALE)
                )),
                new HierarchyOfCategory(6, ServiceType.MOTORYZACJA, List.of(
                        new HierarchyOfCategory(1, ServiceType.MOTORYZACJA_BLACHARZ_SAMOCHODOWY),
                        new HierarchyOfCategory(2, ServiceType.MOTORYZACJA_GEOMETRIA_KOL),
                        new HierarchyOfCategory(3, ServiceType.MOTORYZACJA_INSTALACJA_LPG),
                        new HierarchyOfCategory(4, ServiceType.MOTORYZACJA_LAKIERNIK),
                        new HierarchyOfCategory(5, ServiceType.MOTORYZACJA_MECHANIK_MOTOCYKLOWY),
                        new HierarchyOfCategory(6, ServiceType.MOTORYZACJA_NAPRAWA_SZYB_SAMOCHODOWYCH),
                        new HierarchyOfCategory(7, ServiceType.MOTORYZACJA_NAUKA_JAZDY),
                        new HierarchyOfCategory(8, ServiceType.MOTORYZACJA_PRZYCIEMNIANIE_SZYB),
                        new HierarchyOfCategory(9, ServiceType.MOTORYZACJA_SERWIS_INSTALACJI_LPG),
                        new HierarchyOfCategory(10, ServiceType.MOTORYZACJA_SPAWACZ),
                        new HierarchyOfCategory(11, ServiceType.MOTORYZACJA_TAPICER_SAMOCHODOWY),
                        new HierarchyOfCategory(12, ServiceType.MOTORYZACJA_TUNING_SAMOCHODOW),
                        new HierarchyOfCategory(13, ServiceType.MOTORYZACJA_WULKANIZACJA_OPON),
                        new HierarchyOfCategory(14, ServiceType.MOTORYZACJA_WYMIANA_OLEJU),
                        new HierarchyOfCategory(15, ServiceType.MOTORYZACJA_WYMIANA_SPRZEGLA),
                        new HierarchyOfCategory(16, ServiceType.MOTORYZACJA_POZOSTALE)
                )),
                new HierarchyOfCategory(7, ServiceType.OGROD),
                new HierarchyOfCategory(8, ServiceType.ORGANIZACJA_IMPREZ),
                new HierarchyOfCategory(9, ServiceType.PROJEKTOWANIE),
                new HierarchyOfCategory(10, ServiceType.REMONT, List.of(
                        new HierarchyOfCategory(1, ServiceType.REMONT_BALUSTRADY_I_PORECZE),
                        new HierarchyOfCategory(2, ServiceType.REMONT_DACH_I_ELEWACJA),
                        new HierarchyOfCategory(3, ServiceType.REMONT_INSTALACJE),
                        new HierarchyOfCategory(4, ServiceType.REMONT_INTELIGENTNY_DOM),
                        new HierarchyOfCategory(5, ServiceType.REMONT_PODLOGI),
                        new HierarchyOfCategory(6, ServiceType.REMONT_REMONTY_BUDYNKOW),
                        new HierarchyOfCategory(7, ServiceType.REMONT_USLUGI_MALARSKIE),
                        new HierarchyOfCategory(8, ServiceType.REMONT_WYKONCZENIE_WNETRZ),
                        new HierarchyOfCategory(9, ServiceType.REMONT_SCIANY_I_SUFIT),
                        new HierarchyOfCategory(10, ServiceType.REMONT_POZOSTALE)
                )),
                new HierarchyOfCategory(11, ServiceType.SPRZATANIE),
                new HierarchyOfCategory(12, ServiceType.SZKOLENIA_I_JEZYKI_OBCE, List.of(
                        new HierarchyOfCategory(1, ServiceType.SZKOLENIA_I_JEZYKI_OBCE_NAUKA_JAZDY),
                        new HierarchyOfCategory(2, ServiceType.SZKOLENIA_I_JEZYKI_OBCE_NAUKA_JEZYKOW),
                        new HierarchyOfCategory(3, ServiceType.SZKOLENIA_I_JEZYKI_OBCE_SZKOLENIA_PRZECIWPOZAROWE),
                        new HierarchyOfCategory(4, ServiceType.SZKOLENIA_I_JEZYKI_OBCE_SZKOLENIA_Z_PIERWSZEJ_POMOCY),
                        new HierarchyOfCategory(5, ServiceType.SZKOLENIA_I_JEZYKI_OBCE_SZKOLA_JEZYKOWA),
                        new HierarchyOfCategory(6, ServiceType.SZKOLENIA_I_JEZYKI_OBCE_TLUMACZENIE_PRZYSIEGLE),
                        new HierarchyOfCategory(7, ServiceType.SZKOLENIA_I_JEZYKI_OBCE_TLUMACZENIE_STRON_INTERNETOWYCH)
                )),
                new HierarchyOfCategory(13, ServiceType.TRANSPORT, List.of(
                        new HierarchyOfCategory(1, ServiceType.TRANSPORT_AUTO_DO_SLUBU),
                        new HierarchyOfCategory(2, ServiceType.TRANSPORT_PRZEPROWADZKI),
                        new HierarchyOfCategory(3, ServiceType.TRANSPORT_SPEDYCJA),
                        new HierarchyOfCategory(4, ServiceType.TRANSPORT_TRANSPORT_SAMOCHODOW_KRAJOWY),
                        new HierarchyOfCategory(5, ServiceType.TRANSPORT_TRANSPORT_SAMOCHODOW_ZAGRANICZNY),
                        new HierarchyOfCategory(6, ServiceType.TRANSPORT_TRANSPORT_ZAGRANICZNY),
                        new HierarchyOfCategory(7, ServiceType.TRANSPORT_WYNAJEM_AUTOKAROW),
                        new HierarchyOfCategory(8, ServiceType.TRANSPORT_WYNAJEM_BUSOW),
                        new HierarchyOfCategory(9, ServiceType.TRANSPORT_WYNAJEM_LIMUZYNY),
                        new HierarchyOfCategory(10, ServiceType.TRANSPORT_ZARZADZANIE_FLOTA)
                )),
                new HierarchyOfCategory(14, ServiceType.USLUGI_DLA_BIZNESU),
                new HierarchyOfCategory(15, ServiceType.MONTAZ_I_NAPRAWA),
                new HierarchyOfCategory(16, ServiceType.USLUGI_FINANSOWE),
                new HierarchyOfCategory(17, ServiceType.USLUGI_PRAWNE_I_ADMINISTRACYJNE),
                new HierarchyOfCategory(18, ServiceType.USLUGI_ZDALNE),
                new HierarchyOfCategory(19, ServiceType.ZDROWIE_I_URODA),
                new HierarchyOfCategory(20, ServiceType.ZLOTA_RACZKA),
                new HierarchyOfCategory(21, ServiceType.INNE)
        ));
    }
}

