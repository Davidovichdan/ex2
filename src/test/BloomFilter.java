package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class BloomFilter {
    BitSet bitSet;
    int size;
    List<MessageDigest> MDList;
    BigInteger int1;

    public BloomFilter(int size1, String... algs) {
        bitSet = new BitSet();
        size = size1;
        MDList = new ArrayList<>();

        for (String s : algs) {
            try {
                MDList.add(MessageDigest.getInstance(s));
            } catch (NoSuchAlgorithmException e) {
                System.out.println("error with bloomF line 22");
            }
        }
    }

    public void add(String w) {
        byte[] bts;

        for (MessageDigest MD : MDList) {
            bts = MD.digest(w.getBytes());
            int1 = new BigInteger(bts);
            bitSet.set(Math.abs((int1.abs().intValue()) % size), true);
        }

    }

    public boolean contains(String s) {

        byte[] bts = new byte[0];
        for (MessageDigest MD : MDList) {
            bts = MD.digest(s.getBytes());
        }
        int1 = new BigInteger(bts);
        return bitSet.get(Math.abs((int1.abs().intValue()) % size));
    }

    @Override
    public String toString()
    {
        StringBuilder s= new StringBuilder();
        for(int i=0;i<bitSet.length();i++)
        {
            if (!bitSet.get(i))
                s.append("0");
            else
                s.append("1");
        }
        return s.toString();
    }
}