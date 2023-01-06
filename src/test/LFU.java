package test;


import java.util.LinkedHashMap;
import java.util.Map;

public class LFU implements CacheReplacementPolicy{
Map<String,Integer> lfuMap;

    public LFU() {
        lfuMap=new LinkedHashMap<>();
    }

    @Override
    public void add(String word) {
        if(lfuMap.containsKey(word))
            lfuMap.put(word,lfuMap.get(word)+1);
        else
            lfuMap.put(word,1);
    }

    @Override
    public String remove() {
        int minVal=Integer.MAX_VALUE;
        String key=null;
        for(Map.Entry<String,Integer> e :lfuMap.entrySet()) {
            if(e.getValue()<minVal) {
                minVal = e.getValue();
                key = e.getKey();
            }
        }
        lfuMap.remove(key);
        return key;
    }


}
