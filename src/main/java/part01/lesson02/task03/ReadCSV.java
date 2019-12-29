package part01.lesson02.task03;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 * Read the CSV file into an array
 *
 * @author Oleg_Chapurin
 */
public class ReadCSV {

    public ArrayList<String> read(String path) {
        ArrayList<String> arrayList = new ArrayList<>();
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String listPath = properties.getProperty("FillPath");
        try(InputStreamReader in = new InputStreamReader(getClass().getResourceAsStream(listPath));
            BufferedReader reader = new BufferedReader(in)) {
            String line1;
            Scanner scanner = null;
            while ((line1 = reader.readLine()) != null) {
                arrayList.add(line1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
