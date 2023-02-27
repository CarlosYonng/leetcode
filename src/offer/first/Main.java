package offer.first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(exchange(new int[]{1,2,3,4}));
    }

    public static int[] exchange(int[] nums) {
        int[] a1 = new int[nums.length];
        int[] a2 = new int[nums.length];
        int j=0;
        int k=0;
        for(int i : nums) {
            if(i%2 != 0){
                a1[j] = i;
                j++;
            } else {
                a2[k] = i;
                k++;
            }
        }
        for(int i=0 ;i<a2.length;i++) {
            a1[a1.length+i] = a2[i];
        }
        return a1;
    }
}
