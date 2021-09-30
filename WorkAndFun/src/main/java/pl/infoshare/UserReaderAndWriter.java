package pl.infoshare;

import java.io.*;
import java.util.Iterator;

public class UserReaderAndWriter {

    public static String[][] reader() {
        String path ="src/Users.csv";
        String line ="";
        String[] values;
        int lines = 0;
        int valuesLength=9; //declerate num of columns
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));

            while (reader.readLine() != null) lines++;  //counting lines of file
            reader.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        String[][] userData = new String[lines][valuesLength];
        try {
            int i = 0;
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                values = line.split(",");
                for(int j=0;j<valuesLength;j++){
                    userData[i][j]=values[j];
                }
                i++;
            }

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return userData;
    }
    public static void addUser(User user) throws IOException{
        String path = "src/Users.csv";
        FileWriter fstream = new FileWriter(path, true);
        BufferedWriter out = new BufferedWriter(fstream);
        out.newLine();
        out.write(user.getId() + "," + user.getFirstName() + "," + user.getLastName() + "," + user.getPassword() + "," + user.getPhone() + "," + user.getMail() + "," +user.getAge() + "," +user.getLanguages()+ "," +user.getRating());
        //close buffer writer
        out.close();
    }
}



