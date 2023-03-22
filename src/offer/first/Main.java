package offer.first;

import java.util.*;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        String a = "0";
        String b = "0";
        System.out.println(3%2);
    }

    public static String addBinary(String a, String b) {
        int l = a.length() >= b.length() ? a.length() : b.length();
        int s = a.length() <= b.length() ? a.length() : b.length();
        String ll =  a.length() >= b.length() ? a : b;
        String res = "";
        int carry = 0;
        for(int j=s-1;j>=0;j--) {
            if((a.charAt(j) == b.charAt(j) && a.charAt(j) == '1' && carry == 0)
                    ||(a.charAt(j) != b.charAt(j) && carry == 1)) {
                res = 0 + res;
                carry = 1;
            } else if (a.charAt(j) == b.charAt(j) && a.charAt(j) == '1' && carry == 1) {
                res = 1 + res;
                carry = 1;
            }  else if (a.charAt(j) == b.charAt(j) && a.charAt(j) == '0' && carry == 0) {
                res = 0 + res;
                carry = 0;
            } else {
                res = 1 + res;
                carry = 0;
            }
        }
        for(int j=l-s-1;j>=0;j--) {
            if(a.charAt(j) == '1' && carry == 1) {
                res = 0 + res;
                carry = 1;
            } else if(a.charAt(j) == '0' && carry == 0) {
                res = 0 + res;
                res = a.substring(0,j) + res;
                carry = 0;
                break;
            } else {
                res = 1 + res;
                res = a.substring(0,j) + res;
                carry = 0;
                break;
            }
        }
        if (carry == 1) {
            res = 1 + res;
        }
        return res;
    }
}

