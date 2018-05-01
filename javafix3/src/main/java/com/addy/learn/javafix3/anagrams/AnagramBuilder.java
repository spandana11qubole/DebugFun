package com.addy.learn.javafix3.anagrams;

import java.util.*;

public class AnagramBuilder {

    private String str1;
    private String str2;
    private int deletionsFromStr1;
    private int deletionsFromStr2;
    private int totalDeletions;

    public int getDeletionsFromStr1() {
        return deletionsFromStr1;
    }

    public int getDeletionsFromStr2() {
        return deletionsFromStr2;
    }

    public int getTotalDeletions() {
        return totalDeletions;
    }

    public AnagramBuilder(String s1, String s2){
        str1 = s1;
        str2 = s2;
        findDeletions();
    }

    public void findDeletions(){

        Map<Character, Integer> charMapStr1 = new HashMap<>();
        Map<Character, Integer> charMapStr2 = new HashMap<>();

        for(char c : str1.toCharArray()){
            if(charMapStr1.containsKey(c)){
                Integer oldVal = charMapStr1.get(c);
                charMapStr1.replace(c,++oldVal);

            }else{
                charMapStr1.put(c,1);
            }
        }

        for(char c : str2.toCharArray()){
            if(charMapStr2.containsKey(c)){
                Integer oldVal = charMapStr2.get(c);
                charMapStr2.replace(c, ++oldVal);
            }else{
                charMapStr2.put(c,1);
            }
        }

        int uncommonCharsInStr1 = 0;
        int uncommonCharsInStr2 = 0;
//h1
        Set<Character> charSet1 = charMapStr1.keySet();
        Set<Character> charSet2 = charMapStr2.keySet();

        for(Character c : charSet1){
            if (!charSet2.contains(c)){
                uncommonCharsInStr1++;
            }
        }

        for(Character c : charSet2){
            if (!charSet1.contains(c)){
                uncommonCharsInStr2++;
            }
        }

        Set<Character> allChars = new HashSet<>(charSet1);
        allChars.addAll(charSet2);
        for(Character c : allChars){
            if(charSet1.contains(c) && charSet2.contains(c)){
                if(charMapStr1.get(c) > charMapStr2.get(c)){
                    deletionsFromStr1 = charMapStr1.get(c) - charMapStr2.get(c);
                }else if(charMapStr2.get(c) > charMapStr1.get(c)){
                    deletionsFromStr2 = charMapStr2.get(c) - charMapStr2.get(c);
                }else{
                    // no deletions
                }
            }
        }

        deletionsFromStr2 += uncommonCharsInStr2;
        deletionsFromStr1 += uncommonCharsInStr1;
        totalDeletions = deletionsFromStr1 + deletionsFromStr2;
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter String 1\t>");
        String str1 = s.nextLine();
        System.out.print("\nEnter String 2\t>");
        String str2 = s.nextLine();
        AnagramBuilder a = new AnagramBuilder(str1,str2);
        System.out.println("Number of deletions in String 1 " + Integer.toString(a.getDeletionsFromStr1()));
        System.out.println("Number Of Deletions in String 2 " + Integer.toString(a.getDeletionsFromStr2()));
        System.out.println("Number Of Deletions in total " + Integer.toString(a.getTotalDeletions()));
    }


}
