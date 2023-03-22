package offer.first;


/**
 *
 * 时间限制：C/C++ 1秒，其他语言 2秒
 * 空间限制：C/C++131072K，其他语言262144K
 * 64bit IO Format：%lld
 *
 * 语言限定：
 * C（clang11）, C++（clang++1）, Pascal（fpc 3.0.2）, Java（javac 1.8）, Python2（2.7.3）,
 * PHP(7.4.7), C#(mcs5.4), ObjC(gcc 5.4), Pythen3(3.9), JavaScript Node(12.18.2), JavaScript V8(6.0.0),
 * Sqlite(3.7.9), R(4.0.3), Go(1.14.4), Ruby(2.7.1), Swift(5.3), matlab(Octave 5.2), Pypy2(pypy2.7.13),
 * Pypy3(pypy3.6.1), Rust(1.44), Scala(2.11.12), Kotlin(1.4.10), Groovy(3.0.6), TypeScript(4.1.2), Mysql(8.0)
 * 本题可使用本地IDE编码，不能使用本地已有代码，无跳出限制，
 * 编码后请点击”保存并调试“按钮进行代码提交。
 *
 * ■ 题目描述
 * 【最多提取子串数目 | 挑选字符串】
 * 给定a-z，26个英文字母小写字符串组成的字符串A和B，
 * 其中A可能存在重复字母，B不会存在重复字母，
 * 现从字符串A中按规则挑选一些字母可以组成字符串B。
 *
 * 挑选规则如下：
 * 同一个位置的字母只能挑选一次，
 * 被挑选字母的相对先后顺序不能被改变，
 * 求最多可以同时从A中挑选多少组能组成B的字符串。
 *
 * 输入描述
 * 输入为2行，第一行输入字符串a，第二行输入字符串b，行首行尾没有多余空格
 * 输出描述
 * 输出一行，包含一个数字，表示最多可以同时从a中挑选多少组能组成b的字符串，行末没有多余空格
 *
 * 示例1
 * 输入
 * badc
 * bac
 * 输出
 * 1
 * 说明
 * 从a中可以挑选出一组能组成b的字符串
 *
 * 示例2
 * 输入
 * bbadcac
 * bac
 * 输出
 * 2
 * 说明
 * 从a中可以挑选出2组能组成b的字符串
 *
 * 解题思路
 * 遍历b中的字母，查找a中是否包含该字母，如果找到，则用其他字符替换，如果没有找到则退出，输出匹配到的组数，如果找到继续新一轮，直到找不到为止。

 * **/

import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String aa = scan.nextLine();
        String bb = scan.nextLine();
        System.out.println(count(aa,bb));
    }

    public static int count(String a, String b) {
        int res = 0;
        if(a.length() < b.length()) {
            return res;
        }
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();
        boolean[] used = new boolean[aa.length];

        int p = 0;
        while (p==0) {
            int t = 0;
            for (int i=0;i<bb.length;i++) {
                for (int j=t;j<aa.length;j++) {
                    if(!used[j] && bb[i] == aa[j]) {
                        used[j] = true;
                        p++;
                        t = j+1;
                        break;
                    }
                    if (p < i) {
                        break;
                    }
                }
            }
            if (p == bb.length) {
                res++;
                p = 0;
            } else {
                p = -1;
            }
        }
        return res;
    }


}
