package test;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class IOSearcher {

    public static boolean search(String is, String... fileNames) {
        for (String f : fileNames) {
            Scanner in = null;
            try {
                in = new Scanner(new BufferedReader(new FileReader(f)));
            } catch (FileNotFoundException e) {
                System.out.println("problem with Scanner IOSearch");
            }
            while (in.hasNext()) {
                if (in.next().contains(is)) {
                    in.close();
                    return true;}
            }
        in.close();
        }

        return false;
    }
}
