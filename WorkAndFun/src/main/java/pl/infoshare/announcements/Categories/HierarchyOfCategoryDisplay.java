package pl.infoshare.announcements.Categories;

import static pl.infoshare.announcements.AnnouncementService.BREAK_AND_CLOSE;
import static pl.infoshare.announcements.AnnouncementService.scanner;

public class HierarchyOfCategoryDisplay {

    /**
     * Recursive method, which display level-downs of inputted hierarchy, until they are
     * @param hierarchyOfCategory
     * @return print subcategories and return the "lowest"
     */
    public static ServiceType hierarchyDisplay(HierarchyOfCategory hierarchyOfCategory) {
        ServiceType serviceTypeToAssign = null;
        if (hierarchyOfCategory.getLevelDown().size() > 0) {
            //print the header of category
            System.out.println("Wybierz typ usługi z listy wpisując odpowiedni numer:");
            System.out.println("______________________________");
            System.out.println(hierarchyOfCategory.getServiceType().getServiceTypeName());
            for (HierarchyOfCategory category: hierarchyOfCategory.getLevelDown()) {
                System.out.println(category.getId() + " - " + category.getServiceType().getServiceTypeName());
            }

            boolean infinityLoop = true;
            do {
                try {
                    System.out.println(BREAK_AND_CLOSE);
                    System.out.println("______________________________");
                    System.out.println("Podaj numer kategorii:");
                    String userInput = scanner.nextLine();
                    if (userInput.equals("0")){
                        return null;
                    }
                    serviceTypeToAssign = hierarchyDisplay(hierarchyOfCategory.getLevelDown().get(Integer.parseInt(userInput) - 1));
                    infinityLoop = false;
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Źle wprowadzony numer!");
                }
            } while (infinityLoop);

        } else {
            serviceTypeToAssign = hierarchyOfCategory.getServiceType();
        }
        return serviceTypeToAssign;
    }
}
