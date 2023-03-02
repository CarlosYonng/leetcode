package offer.first;


import java.math.BigInteger;
import java.util.*;


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
     * 15. 三数之和
     * 解题思路：
     * 先排序，排序后若相邻的两个数相同，那么只走一次计算逻辑，防止结果重复；
     * 循环取一个值a然后就变成了->  a+ b+ c = 0  ->  b+c = -a,三数之和其实就是两数之和
     * **/
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for(int first=0;first<n;first++) {
            if(first > 0 && nums[first] == nums[first-1]) {
                continue;
            }
            int third = n-1;
            int target = -nums[first];
            for(int second=first+1;second<n;second++) {
                if(second>first+1 && nums[second] == nums[second-1]) {
                    continue;
                }
                while(second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                if(second == third) {
                    break;
                }
                if(nums[second] + nums[third] == target) {
                    List<Integer> tt = new ArrayList<>();
                    tt.add(nums[first]);
                    tt.add(nums[second]);
                    tt.add(nums[third]);
                    res.add(tt);
                }
            }
        }
        return res;
    }

    /**
     * 20. 有效的括号
     * 解题思路：
     * 栈，下一个元素如果是]}) 那上一个一定是对应得[{(，依次入栈，配对出栈最后看栈情况就可以
     * **/
    public boolean isValid(String s) {
        int n = s.length();
        if(n%2 != 0 || n == 0) {
            return false;
        }
        Map<Character,Character>  map = new HashMap<>();
        map.put(']','[');
        map.put('}','{');
        map.put(')','(');
        Deque<Character> deque = new ArrayDeque<>();
        for(int i=0;i<n;i++) {

            Character cc = s.charAt(i);
            if(map.containsKey(cc)) {
                if(deque.isEmpty() || deque.peek() != map.get(cc)) {
                    return false;
                } else {
                    deque.pop();
                }
            } else {
                deque.push(cc);
            }
        }
        return deque.isEmpty();
    }


    /**
     * 28. 找出字符串中第一个匹配项的下标
     * 解题思路：
     * 从左向右匹配，第一个字符匹配成功就依次字符匹配，否则从下一个字符开始重新匹配第一个字符，直到第一次匹配字符串长度和匹配项相同
     * **/
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        char[] hc = haystack.toCharArray();
        char[] nc = needle.toCharArray();

        for(int i=0;i<=n-m;i++) {
            int a = i;
            int b = 0;
            while(b < m && hc[a] == nc[b]) {
                a++;
                b++;
            }
            if(b == m) {
                return i;
            }
        }
        return -1;
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
     * 394. 字符串解码
     * 解题思路：
     * 两个辅助栈
     * stack_multi  用于存放系数
     * stack_res    用于存放外部字符
     * 一个变量
     * res  存储内部字符
     *
     * 当有新的内部字符时，就将此时的内部字符转为外部字符存入栈中，
     * 当发现第一个]时，就将当前的内部字符与系数栈顶的系数相乘做新的内部字符
     *
     * 我的解法： 一坨答辩（递归）
     * public String decodeString(String s) {
     *         if (!Pattern.matches(".*[A-Za-z]+.*", s)) {
     *             return "";
     *         }
     *         return getString(s);
     *     }
     *
     *     public String getString(String s) {
     *         if (!s.contains("[")) {
     *             return s;
     *         }
     *         char[] cc = s.toCharArray();
     *         int begin = 0;
     *         int end = 0;
     *         int count = 0;
     *         StringBuilder sb = new StringBuilder();
     *         for(int i=0;i<cc.length;i++) {
     *             if(cc[i] == '[') {
     *                 begin = i;
     *                 if(i>0) {
     *                     StringBuilder ss = new StringBuilder();
     *                     int k = i;
     *                     while (k>0 && Character.isDigit(cc[k-1])) {
     *                         ss.append(cc[k-1]);
     *                         k--;
     *                     }
     *                     ss.reverse();
     *                     count =Integer.parseInt(ss+"");
     *                 }
     *                 continue;
     *             }
     *             if(end == 0 && cc[i] == ']') {
     *                 end = i;
     *                 break;
     *             }
     *         }
     *         for(int i=0;i<count;i++) {
     *             sb = sb.append(s.substring(begin+1,end));
     *         }
     *         if (s.contains("[")) {
     *             int ccc = count;
     *             int aa = 0;
     *             while (ccc > 0) {
     *                 aa++;
     *                 ccc /= 10;
     *             }
     *             s = s.substring(0,begin-aa) + sb + s.substring(end+1,s.length());
     *
     *         }
     *         return getString(s);
     *     }
     *
     *
     * **/
    public static String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for(Character c : s.toCharArray()) {
            if(c == '[') {
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            }
            else if(c == ']') {
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for(int i = 0; i < cur_multi; i++) tmp.append(res);
                res = new StringBuilder(stack_res.removeLast() + tmp);
            }
            else if(c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
            else res.append(c);
        }
        return res.toString();
    }


    /**
     * 406. 根据身高重建队列
     * 解题思路：
     * 排n个坑然后按顺序往坑里填值，从左往右填，相同位置按顺序排序后如果坑重复了，那先入坑的后移（因为事先做了排序所以可以后移）
     * Arrays.sort(people, new Comparator<int[]>() {
     *             public int compare(int[] person1, int[] person2) {
     *                 if (person1[0] != person2[0]) {
     *                 如果第一位不相等要按身高降序排序，这样在arrayList里按index插入的时候才保证小的高位在前，不会影响次位index的数量
     *                     return person2[0] - person1[0];
     *                 } else {
     *                 首位相等次位递增是因为  如果大数相同那次位一定使比首位更大的因为
     *                     return person1[1] - person2[1];
     *                 }
     *             }
     *         });
     * **/
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        List<int[]> ans = new ArrayList<int[]>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }

    /**
     * 556. 下一个更大元素 III
     * 解题思路：
     * 该题就是从右向左找第一个 顺序的 i < i+1 那此时将这个第i个数和从右向左第一个比这第i个数大的数和这第i个数交换位置即得到下一个更大元素的最小高位值变动值，
     * 再将后面的进行从小到大排序即可
     *  例如：2535421的 下一个更大元素就是找到  从右向左数第一个i < i+1 就是 3<5  那就将3换为从右向左的第一个比3大的数为：2545321，
     *  此时下一个更大元素的最小高位值变动值 就由  3->4，然后因为从右向左的数都是降序的，找最小值 进行升序排序就好：
     *  即： 25 35 421   第一次 i<i+1  为   3<5
     *      -> 25 4 5321    交换 3和第一次比3大的数   为  3 和 4交换
     *      ->25 4  1235    进行尾串 升序排序取下一个更大元素的最小值
     * **/
    public int nextGreaterElement(int n) {
        char[] nums = Integer.toString(n).toCharArray();
        int i = nums.length - 2;
        //循环结束后i的值为第一次从右数i<i+1的下标
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }

        int j = nums.length - 1;
        //循环结束后j的值为第一次从右数i<j的下标
        while (j >= 0 && nums[i] >= nums[j]) {
            j--;
        }
        swap(nums, i, j);

        //因为尾串时降序排序 所以首位依次换位置就可以变为升序排序
        reverse(nums, i + 1);
        long ans = Long.parseLong(new String(nums));
        //排序后最大值不能超过int范围
        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }
    public void reverse(char[] nums, int begin) {
        int i = begin, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
    public void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * 646. 最长数对链
     * 解题思路：
     *  要挑选最长数对链的第一个数对时，最优的选择是挑选第二个数字最小的，
     *  这样能给挑选后续的数对留下更多的空间。
     *  挑完第一个数对后，要挑第二个数对时，也是按照相同的思路，
     *
     * **/
    public int findLongestChain(int[][] pairs) {
        int curr = Integer.MIN_VALUE;
        int res = 0;
        Arrays.sort(pairs, (a,b) -> a[1] - b[1]);
        for(int[] i:pairs) {
            if(curr < i[0]) {
                curr = i[1];
                res++;
            }
        }
        return res;
    }

    /**
     * 720. 词典中最长的单词
     * 字符串：
     *  Arrays.sort(words, (a, b) ->  {
     *             if (a.length() != b.length()) {
     *                 return a.length() - b.length();  表示短的字符串排序在前
     *             } else {
     *                 return b.compareTo(a);       表示降序排序（为了最后得到字典序升序的字符串）
     *             }
     *
     * 先在set里放入""，是为了在集合放入第一个字母
     * **/
    public static String longestWord(String[] words) {
        Arrays.sort(words, (a, b) ->  {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            } else {
                return b.compareTo(a);
            }
        });
        String longest = "";
        Set<String> candidates = new HashSet<String>();
        candidates.add("");
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String word = words[i];
            if (candidates.contains(word.substring(0, word.length() - 1))) {
                candidates.add(word);
                longest = word;
            }
        }
        return longest;
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