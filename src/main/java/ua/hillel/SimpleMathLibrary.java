package ua.hillel;

import java.util.Arrays;

public class SimpleMathLibrary {
    static double add(double arg1,double arg2){
        return arg1 +arg2;
    }
    static double mines(double arg1,double arg2){
        return arg1  - arg2;
    }

    static int[] chunk(int[] array, int needle){
    if(array.length<1 ){
        throw new RuntimeException("Nullable length");
    }
    int index = search(array, needle);
    if(index==-1){
        throw new RuntimeException("Array content not any needle numbers");
    }
        if(index == array.length-1){
            throw new RuntimeException("Needle number have last position in array");
        }
        return Arrays.copyOfRange(array,index+1,array.length);
    }
    static int search(int[] array,int needle){
        int index = -1;
        for(int i=0;i<array.length;i++){
            if( array[i]==needle){
                index = i;
            }
        }
        return index;
    }

    static boolean check(int[] array){
        Arrays.sort(array);
        return Arrays.binarySearch(array,1)>=0 && Arrays.binarySearch(array,4)>=0;

    }
}
