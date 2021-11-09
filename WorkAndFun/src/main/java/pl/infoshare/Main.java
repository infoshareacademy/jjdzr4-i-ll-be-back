package pl.infoshare;

import pl.infoshare.announcements.Hierarchy;
import pl.infoshare.announcements.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static final Path USERS_FILE_PATH = Paths.get("src", "main", "resources", "Users.csv");
    public static final Path ANNOUNCEMENTS_FILE_PATH = Paths.get("src", "main", "resources", "Announcements.csv");

    public static void main(String[] args) {

        Hierarchy hierarchy = new Hierarchy().actualCategory();

        CategoryHierarchyDisplay display = new CategoryHierarchyDisplay();
        ServiceType s = display.hierarchyDisplay(hierarchy);

        for (Hierarchy particularHierarchy : hierarchy.getChildren()) {
            System.out.println(particularHierarchy.getServiceType().getSequentialNumber() + " - " + particularHierarchy.getServiceType().getServiceTypeName());
            //System.out.println(hierarchy1.getServiceType().getServiceTypeName())
//            System.out.println(ServiceType.values()[i].getSequentialNumber() + " - " +
//                    ServiceType.values()[i].getServiceTypeName());
        }





        Menu menu = new Menu();
        menu.display();
    }

}