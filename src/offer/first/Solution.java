package offer.first;


import java.math.BigInteger;


class Solution {

    /**
     * 5. 最长回文子串
     * **/
    public String longestPalindrome(String s) {
        int len = s.length();
        if(len < 2) {
            return s;
        }
        boolean[][] dp = new boolean[len][len];
        int maxlen = 1;
        int begin = 0;
        char[] arr = s.toCharArray();
        for(int i=0;i<len;i++) {
            dp[i][i] = true;
        }
        for(int L=2;L<=len;L++) {

            for(int i=0;i<len;i++) {
                //L = j-i+1, j = L+i-1;
                int j = L+i-1;

                if(j>=len) {
                    break;
                }
                if(arr[i] != arr[j]) {
                    dp[i][j] = false;
                } else {
                    if(j-i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                if(dp[i][j] && j-i+1 > maxlen) {
                    maxlen = j-i+1;
                    begin = i;
                }
            }
        }
        return s.substring(begin,begin+maxlen);
    }

    /**
     * 6. N 字形变换
     * **/
    public String convert(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        int t = r * 2 - 2;
        int c = (n + t - 1) / t * (r - 1);
        char[][] mat = new char[r][c];
        for (int i = 0, x = 0, y = 0; i < n; ++i) {
            mat[x][y] = s.charAt(i);
            if (i % t < r - 1) {
                ++x; // 向下移动
            } else {
                --x;
                ++y; // 向右上移动
            }
        }
        StringBuffer ans = new StringBuffer();
        for (char[] row : mat) {
            for (char ch : row) {
                if (ch != 0) {
                    ans.append(ch);
                }
            }
        }
        return ans.toString();
    }

    /**
     * 7. 整数反转
     * **/
    public int reverse(int x) {
        int res = 0;
        while(x != 0) {
            int tmp = x % 10;
            if(res > 214748364 || ((res == 214748364) && tmp>7)) {
                return 0;
            }
            if(res < -214748364 || ((res == -214748364) && tmp<-8)) {
                return 0;
            }
            res = res*10 +tmp;
            x /= 10;
        }
        return res;
    }


    /**
     * 剑指 Offer 17. 打印从1到最大的n位数
     * 解题思路：
     * 将输入值转换为二进制位数。再将二进制位数转为String位数并全部转为9遍历
     * **/
    public int[] printNumbers(int n) {
        int a = 1;
        int b = a << n-1;
        String str = Integer.toBinaryString(b);
        str = str.replace("1","9");
        str = str.replaceAll("0","9");
        BigInteger bb = new BigInteger(str);
        int[] aa = new int[bb.intValue()];
        for(int i=0; i<bb.intValue(); i++) {
            aa[i] = i+1;
        }
        return aa;
    }

    /**
     * 剑指 Offer 18. 删除链表的节点
     * 解题思路：
     * 通过制造链表中节点指针来处理数据
     * **/
    public ListNode deleteNode(ListNode head, int val) {
        if(head.val == val) {
            return head.next;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while(cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        if(cur != null) {
            pre.next = cur.next;
        }
        return head;
    }

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 剑指 Offer 19. 正则表达式匹配
     * 解题思路：状态转移方程
     *  dp思路
     *  f[i][j] 为s的前i个和p的前j个匹配
     *  只看最后一位的情况分三种情况
     * **/
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] f = new boolean[n+1][m+1];
        for(int i=0;i<=n;i++) {
            for(int j=0;j<=m;j++) {
                //为空
                if(j == 0) {
                    f[i][j] = (i == 0);
                } else {
                    //没匹配到*的情况
                    if(p.charAt(j-1) != '*') {
                        if(i > 0  && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')){
                            f[i][j] = f[i-1][j-1];
                        }
                    } else {
                        //匹配到*，但不用的情况
                        if(j >= 2) {
                            f[i][j] |= f[i][j-2];
                        }
                        //匹配到*，用的情况
                        if(i>=1 && j>=2 && (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.')) {
                            f[i][j] |= f[i-1][j];
                        }
                    }
                }
            }
        }
        return f[n][m];
    }

    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     * 解题思路：
     *  看一下链表长度l，再减去k，再重新遍历到l-k时就是res
     * **/
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode res = head;
        ListNode tmp = head;
        int i=0;
        while(tmp != null) {
            tmp = tmp.next;
            i++;
        }
        int n = i-k;
        while(res != null && n-- > 0) {
            res = res.next;
        }
        return res;
    }
}