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
     * 11. 盛最多水的容器
     * 解题思路：
     * 设置开始和结尾双向指针
     * 该题主要就是找两个权重最大并且他们和步长的乘积最大的值；
     * 从左右同时向中心缩减找到最大值
     * **/
    public int maxArea(int[] height) {
        int n = height.length;
        int l = 0;
        int r = n-1;
        int total = 0;
        while(l < r) {
            total = Math.max((r-l)*Math.min(height[r],height[l]),total);
            if(height[l] <= height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return total;
    }


    /**
     * 42. 接雨水
     * 解题思路：
     * 设置开始和结尾双向指针
     * 该题就是在左指针或右指针位置找左右的最大权重值,左右最大权重值较小的-当前位置指针的权值就是当前指针能储水的量；
     * 左指针当前位置的权值小于当前右指针位置的权值那么，左指针对应左边的最大值就小于右指针对应得右边的最大值
     *
     *
     *
     * 我的解法：
     * 遍历每一个指针找每一个指针的左右最大并计算，不如双向指针来的快
     * public int trap(int[] height) {
     *         int n = height.length;
     *         int total = 0;
     *         for(int i=1;i<n-1;i++) {
     *             int left = 0;
     *             int right = n-1;
     *             for(int j=0;j<i;j++) {
     *                 if(height[j] >= height[i]) {
     *                     left = height[left] < height[j] ? j : left;
     *                 }
     *             }
     *             for(int k=n-1;k>i;k--) {
     *                 if(height[k] >= height[i]) {
     *                     right = height[right] < height[k] ? k : right;
     *                 }
     *             }
     *             if(height[left] >= height[i] && height[right] >= height[i]) {
     *                 total += Math.min(height[left],height[right])-height[i];
     *             }
     *         }
     *         return total;
     *     }
     *
     * **/
    public static int trap(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }

    /**
     * 88. 合并两个有序数组
     * 解题思路：
     * System中提供了一个native静态方法arraycopy(),可以使用这个方法来实现数组之间的复制。对于一维数组来说，这种复制属性值传递，修改副本不会影响原来的值。
     * 对于二维或者一维数组中存放的是对象时，复制结果是一维的引用变量传递给副本的一维数组，修改副本时，会影响原来的数组。
     * public static void arraycopy(Object src,
     *                              int srcPos,
     *                              Object dest,
     *                              int destPos,
     *                              int length)
     * 其中：src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
     *
     * 先将num1做备份，然后看两个数组谁更短，最短的那个会被全部合并到num1中，剩下的那个更长的数组再将没拼接完的剩余部分接在p1+p2后面
     * （p1+p2也就是如果数组1更短就是2p1,如果数组2更短就是2p2）
     *
     * **/
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0;
        int p2 = 0;
        int p = 0;
        int[] num1__copy = new int[m];
        System.arraycopy(nums1,0,num1__copy,0,m);
        while(p1<m && p2<n){
            nums1[p++] = (num1__copy[p1]<nums2[p2])?num1__copy[p1++]:nums2[p2++];
        }
        if(p1<m){
            System.arraycopy(num1__copy,p1,nums1,p2+p1,m-p1);
        }
        if(p2<n){
            System.arraycopy(nums2,p2,nums1,p2+p1,n-p2);
        }
    }

    /**
     * 209. 长度最小的子数组
     * 滑动窗口：
     *
     * **/
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        if(n == 0) {
            return 0;
        }
        //方便取Math.min最小值的初始化
        int len = Integer.MAX_VALUE;
        int sum = 0;
        int begin = 0;
        int end = 0;
        while(end < n) {
            sum += nums[end];
            while(sum >= target) {
                //窗口长度值满足，将start进位并sum减去start原位数值，进行窗口缩减
                len = Math.min(end-begin+1,len);
                sum -= nums[begin];
                begin++;

            }
            end++;
        }
        return len == Integer.MAX_VALUE ? 0 : len;
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