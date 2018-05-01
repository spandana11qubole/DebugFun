package com.addy.learn.javafix3.rearrange;

public class SimpleRearrangement {

    private int[] numberList;
    public SimpleRearrangement(){
        numberList = new int[] {1,2,3,4,1,1,1,10,11,13};
    }

    public void showList(){
        for(int i : numberList){
            System.out.print(Integer.toString(i) + " ");
        }
        System.out.println();
    }

    public void rearrangeList(){
        for(int i =0 ; i < numberList.length; i++){
            for(int j = i; j < numberList.length; j++){
                if(numberList[j] == 1){
                    if(j != i) {
                        int backup = numberList[j - 1];
                        numberList[j - 1] = numberList[j];
                        numberList[j] = backup;
                    }
                }
            }

        }
    }

    public static void main(String[] args){
        SimpleRearrangement s = new SimpleRearrangement();
        s.showList();
        s.rearrangeList();
        s.showList();
    }
}
