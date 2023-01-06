package test;


import java.util.HashSet;


public class CacheManager {
    HashSet<String> set;
    int freeSize;
    CacheReplacementPolicy crp;

    public CacheManager(int sizeCache, CacheReplacementPolicy crpGiven) {
        set = new HashSet<>();
        crp = crpGiven;
        freeSize = sizeCache;
    }

    public boolean query(String b) {
        return set.contains(b);
    }

    public void add(String a) {
        crp.add(a);
        String stringToRmv;
        if (freeSize > 0) {
            freeSize--;
        } else {
            stringToRmv = crp.remove();
            set.remove(stringToRmv);
        }
        set.add(a);
    }
}
