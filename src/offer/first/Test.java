package offer.first;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        String[][] ss = new String[n][5];
        for (int i=0;i<n;i++) {
            String a1 = scan.nextLine().trim();
            ss[i] = a1.split(",");
        }
        boolean[] flag = new boolean[n];
        for(int i=0;i<ss.length;i++) {
            if(!flag[i]) {
                dfs(flag, ss, i, ss.length);
            }
        }
        String res = "";
        for(int i=0;i<flag.length;i++) {
            StringBuilder sb = new StringBuilder();
            if (flag[i]) {
                for (String s: ss[i]) {
                    sb.append(s+",");
                }
                res = res + sb.substring(0,sb.toString().length()-1) + ";";
            } else {
                if(!ss[i][3].equals(ss[i][4])) {
                    flag[i] = true;
                    for (String s: ss[i]) {
                        sb.append(s+",");
                    }
                    res = res + sb.substring(0,sb.toString().length()-1) + ";";
                }
            }
        }
        System.out.println(res.substring(0,res.length()-1));
    }

    public static void dfs(boolean[] flag, String[][] ss, int i, int n) {
        for(int j=i+1;j<n;j++) {
            if (ss[i][0].equals(ss[j][0]) && Math.abs(Integer.parseInt(ss[i][1]) - Integer.parseInt(ss[j][1])) < 60
                    && Math.abs(Integer.parseInt(ss[i][2]) - Integer.parseInt(ss[j][2])) > 5) {
                flag[i] = true;
                flag[j] = true;
                dfs(flag, ss, j, n);
            }
        }
    }
}
