package pl.infoshare;




public class Main
{
    public static void main( String[] args ) {

        System.out.println( "Hello World!" );
        String[][] userData = UserReader.reader();

        User user1 = new User(Integer.parseInt(userData[0][0]),userData[1][0],userData[2][0],userData[3][0],Integer.parseInt(userData[4][0]),userData[5][0],Float.parseFloat(userData[6][0]));
        System.out.println(user1.getFirstName());
        //////////////////////////////////////

        ////////////////////////////////////////
    }

}
