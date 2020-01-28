package anagrams;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Anagrams {
  public BigInteger listPosition(String word) {
    if(word.length() <= 1) return BigInteger.valueOf(word.length());
    BigInteger rank = BigInteger.ZERO;
    for(int i = word.length() - 1; i >= 0; i --) {
      String wordTail = word.substring(i);
      BigInteger previousPerms = getPreviousPermutations(wordTail);
      rank = rank.add(previousPerms);
    }
      return rank.add(BigInteger.ONE);
  }

  private BigInteger getPreviousPermutations(String s) {
    char first = s.charAt(0);
    BigInteger previousPerms = BigInteger.ZERO;
    List<Character> charRank = getCharRank(s);
    Map<Character, BigInteger> repeatMap = getRepeatMap(s);
    int currentRank = 0;
    while(charRank.get(currentRank) != first) {
      BigInteger tailSize = BigInteger.valueOf(s.length() - 1);
      BigInteger repeatFactor = getRepeatFactor(charRank.get(currentRank), repeatMap);
      previousPerms = previousPerms.add(factorial(tailSize).divide(repeatFactor));
      currentRank++;
    }
    return previousPerms;
  }

  private BigInteger getRepeatFactor(char excluded, Map<Character, BigInteger> count) {
    BigInteger result = BigInteger.ONE;
    for(char c : count.keySet()) {
      BigInteger currentCount = count.get(c);
      if(c == excluded) currentCount = currentCount.subtract(BigInteger.ONE);
      if(currentCount.compareTo(BigInteger.ZERO) != 0) result = result.multiply(factorial(currentCount));
    }
    return result;
  }

  private Map<Character, BigInteger> getRepeatMap(String s) {
    char[] chars = s.toCharArray();
    Map<Character, BigInteger> countMap = new HashMap<>();
    for(char c: chars) {
      if(countMap.get(c) == null) {
        countMap.put(c, BigInteger.ZERO);
      }
      BigInteger currCount = countMap.get(c);
      countMap.put(c, currCount.add(BigInteger.ONE));
    }
    return countMap;
  }

  private List<Character> getCharRank(String s) {
    char[] chars = s.toCharArray();
    Arrays.sort(chars);
    Set<Character> charSet = new HashSet<>();
    List<Character> rankList = new ArrayList<>();
    for(char c : chars) {
      if(!charSet.contains(c)) {
        charSet.add(c);
        rankList.add(c);
      }
    }
    return rankList;
  }
  
  private BigInteger factorial(BigInteger n) {
    BigInteger result = BigInteger.ONE;
    for(BigInteger i = n; i.compareTo(BigInteger.ZERO) > 0; i = i.subtract(BigInteger.ONE)) {
      result = result.multiply(i);
    }
    return result;
  }
}