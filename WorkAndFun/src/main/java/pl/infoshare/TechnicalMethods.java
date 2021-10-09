package pl.infoshare;

public class TechnicalMethods {
    public static void makeDelay(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e){
            System.out.println("Przerwałeś(-aś) działanie programu... :(");
        }
    }
}
