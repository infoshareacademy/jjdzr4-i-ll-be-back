package pl.infoshare;


import java.io.IOException;

public class Main
{
    public static void main( String[] args ) {
        String[][] userData = UserReaderAndWriter.reader(); //Getting users from csv file into table

        User user1 = new User(Integer.parseInt(userData[0][0]),userData[0][1],userData[0][2],userData[0][3],Integer.parseInt(userData[0][4]),userData[0][5],Integer.parseInt(userData[0][6]),userData[0][7],Float.parseFloat(userData[0][8])); //Creating user from exsisting users
        System.out.println(user1.getFirstName());
        User user2 = new User(5,"Kamila", "Strzelecka","tomojehaslo",554332221,"kamstrzel@gmail.com",18,"Polski",  4.3);  //Constructing new user
        ////////////////////////////////////
        try {
            UserReaderAndWriter.addUser(user2); //saving user
        }
        catch (IOException e){
            e.printStackTrace();
        }
        //////////////
        for(int i=0;i<userData.length;i++){
            for(int j = 0; j<userData[i].length;j++){
                System.out.print(userData[i][j]+ " ");
            }
            System.out.println();
        }
    }

}
