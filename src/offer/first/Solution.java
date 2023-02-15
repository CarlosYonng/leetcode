package offer.first;


import java.math.BigInteger;


class Solution {

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
     * 解题思路：
     *
     * **/
//    public boolean isMatch(String s, String p) {
//
//        return false;
//    }
}