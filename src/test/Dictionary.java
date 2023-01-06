package test;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dictionary {
    BloomFilter bf;
    CacheManager cm_NOT_Exist;
    CacheManager cm_Exist;
    List<String> filesList = new ArrayList<>();


    public Dictionary(String... FileNames) {
        cm_Exist = new CacheManager(400, new LRU());
        cm_NOT_Exist = new CacheManager(100, new LFU());
        bf = new BloomFilter(256, "MD5", "SHA1");

        for (String f : FileNames) {
            filesList.add(f);
            try {
                Scanner in = new Scanner(new BufferedReader(new FileReader(f)));
                while (in.hasNext()) {
                    bf.add(in.next());
                }
                in.close();
            } catch (FileNotFoundException e) {
                System.out.println("problem with Dictionary Scanner");
            }
        }
    }

    public boolean query(String is) {
        if (cm_Exist.query(is))
            return true;
        if (cm_NOT_Exist.query(is))
            return false;
        if (bf.contains(is)) {
            cm_Exist.add(is);
        } else
            cm_NOT_Exist.add(is);
        return (bf.contains(is));
    }

    public boolean challenge(String lazy) {
        if (IOSearcher.search(lazy, filesList.get(0), filesList.get(1)))
            cm_Exist.add(lazy);
        else
            cm_NOT_Exist.add(lazy);
        return IOSearcher.search(lazy, filesList.get(0), filesList.get(1));

    }
}

