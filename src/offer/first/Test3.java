package offer.first;

import java.util.Scanner;

public class Test3 {

    static int[] a = new int[]{0,0,1,-1};
    static int[] b = new int[]{1,-1,0,0};
    static int t = 0;
    static int c = 0;
    static int sa = 0;
    static int sb = 0;
    static int ta = 0;
    static int tb = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] one = scan.nextLine().trim().split(" ");
        t = Integer.parseInt(one[0]);
        c = Integer.parseInt(one[1]);
        String[] two = scan.nextLine().trim().split(" ");
        int n = Integer.parseInt(two[0]);
        int m = Integer.parseInt(two[1]);
        char[][] cc = new char[n][m];
        for (int i=0;i<n;i++) {
            char[] qq = scan.nextLine().trim().toCharArray();
            cc[i] = qq;
        }
        for(int i=0;i<cc.length;i++) {
            for (int j=0;j<cc[0].length;j++) {
                if (cc[i][j] == ('S')) {
                    sa = i;
                    sb = j;
                }
                if (cc[i][j] == ('T')) {
                    ta = i;
                    tb = j;
                }
            }
        }
        System.out.println(path(sa, sb, cc, 0, 0));
    }

    public static String path(int xa, int xb, char[][] cc, int xt, int xc) {
        String res = "NO";
        if (xa == ta && xb == tb && xt <= t && xc <= c) {
            return "YES";
        }

        for (int i=0;i<4;i++) {
            xa = xa + a[i];
            xb = xb + b[i];
            if (xa == ta && xb == tb ) {
                if (xa+a[i]>1 && xa+a[i] != xa - a[i] - a[i-1] && xb+b[i]>1 && xb+b[i] != xb - b[i] - b[i-1] ) {
                    xt++;
                }
                if (cc[i][sa+a[i]] == '*') {
                    xc++;
                }
                path(xa,xb,cc,xt,xc);
            }
                
        }
        return res;
    }
}
