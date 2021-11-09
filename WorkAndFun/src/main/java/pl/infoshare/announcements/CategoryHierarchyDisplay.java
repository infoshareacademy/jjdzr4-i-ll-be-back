package pl.infoshare.announcements;

import static pl.infoshare.announcements.AnnouncementService.BREAK_AND_CLOSE;
import static pl.infoshare.announcements.AnnouncementService.scanner;

public class CategoryHierarchyDisplay {

    public ServiceType hierarchyDisplay(Hierarchy hierarchy) {
        ServiceType serviceTypeToAssign = null;
        if (hierarchy.getChildren().size() > 0) {
            for (Hierarchy h: hierarchy.getChildren()) {
                System.out.println(h.getId() + " - " + h.getServiceType().getServiceTypeName());
            }


            //Integer.parseInt(userInput)
//            for (Hierarchy hh: hierarchy.getChildren()) {
//                if (hh.getServiceType().getSequentialNumber().equals(userInput)){
//                    hierarchyDisplay(hh);
//                    break;
//                }
//            }

            boolean infinityLoop = true;
            do {
                try {
                    System.out.println(BREAK_AND_CLOSE);
                    System.out.println("Podaj numer kategorii:");
                    String userInput = scanner.nextLine();
                    if (userInput.equals("0")){
                        return null;
                    }
                    serviceTypeToAssign = hierarchyDisplay(hierarchy.getChildren().get(Integer.parseInt(userInput) - 1));
                    infinityLoop = false;
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Źle wprowadzony numer! :(");
                }
            } while (infinityLoop);

        } else {
            serviceTypeToAssign = hierarchy.getServiceType();
        }
        return serviceTypeToAssign;

    }

}
