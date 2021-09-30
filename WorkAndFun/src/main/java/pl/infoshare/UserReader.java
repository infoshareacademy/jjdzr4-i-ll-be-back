package pl.infoshare;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UserReader{

    public static String[][] reader() {
        String path ="src/Users.csv";
        String line ="";
        String[] values;
        int lines = 0;
        int valuesLength=7;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));

            while (reader.readLine() != null) lines++;
            reader.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        String[][] userData = new String[valuesLength][lines];
        try {
            int i = 0;
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                values = line.split(",");
                int d = 0;
                userData[d][i] = values[d];
                d++;
                userData[d][i] = values[d];
                d++;
                userData[d][i] = values[d];
                d++;
                userData[d][i] = values[d];
                d++;
                userData[d][i] = values[d];
                d++;
                userData[d][i] = values[d];
                d++;
                userData[d][i] = values[d];
                d++;
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


}

