package test;


import java.util.LinkedHashSet;

public class LRU implements CacheReplacementPolicy {
    LinkedHashSet<String> set;

    public LRU() {
        set = new LinkedHashSet<>();
    }

    @Override
    public void add(String word) {
        if (set.contains(word))
            set.remove(word);
        set.add(word);
    }

    @Override
    public String remove() {
        String wordToRemove= set.iterator().next();
        set.remove(set.iterator().next());
        return wordToRemove;
    }
}
