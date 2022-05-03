package com.learn.leetcode;


import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/decode-ways/
public class DecodeWays {
    int select = 0;
    Map<String, Integer> dfsCache = new HashMap<String, Integer>();

    public int numDecodingsDfs(String s) {
        if (s.startsWith("0")) {
            return 0;
        }

        if("".equals(s)) {
            return 1;
        }

        if (dfsCache.containsKey(s)) {
            return dfsCache.get(s);
        }

        int count = numDecodingsDfs(s.substring(1));
        if (s.length() > 1 && isValid(s.substring(0, 2))) {
            count += numDecodingsDfs(s.substring(2));
        }
        dfsCache.put(s, count);
        return count;
    }

    // 动态规划
    public int numDecodingsDp(String s) {

        if (s.startsWith("0")) {
            return 0;
        }
        int[] count = new int[s.length() + 1];
        count[s.length()] = 1;
        for(int i = s.length() - 1; i >= 0; i--) {
            if(s.charAt(i) == '0') {
                continue;
            }
            if (i == s.length() - 1) {
                count[i] = 1;
                continue;
            }
            count[i] += count[i+1];
            if (isValid(s.substring(i, i+2))) {
                count[i] += count[i + 2];
            }
        }
        return count[0];
    }

    private boolean isValid(String num) {
        if(num.startsWith("0")) {
            return false;
        }
        Integer val = Integer.valueOf(num);
        if (val >= 1 && val <= 26) {
            return true;
        }
        return false;
    }

    public int numDecodings(String s) {
        if (select == 0) {
            return numDecodingsDfs(s);
        } else if (select == 1) {
            return numDecodingsDp(s);
        } else {
            throw new RuntimeException();
        }
    }

    @Test
    public void test1() {
        int res = numDecodings("12");
        Assert.assertTrue(res == 2);
    }

    @Test
    public void test2() {
        int res = numDecodings("226");
        Assert.assertTrue(res == 3);
    }

    @Test
    public void test3() {
        int res = numDecodings("0");
        Assert.assertTrue(res == 0);
    }

    @Test
    public void test4() {
        int res = numDecodings("201");
        Assert.assertTrue(res == 1);
    }

    @Test
    public void test5() {
        int res = numDecodings("301");
        Assert.assertTrue(res == 0);
    }

    @Test
    public void test6() {
        int res = numDecodings("100001");
        Assert.assertTrue(res == 0);
    }

    @Test
    public void test7() {
        int res = numDecodings("0001");
        Assert.assertTrue(res == 0);
    }

    @Test
    public void test8() {
        int res = numDecodings("11106");
        Assert.assertTrue(res == 2);
    }

    @Test
    public void test9() {
        // 不用cache会超时，因为要遍历2的46次方 -1 次
        int res = numDecodings("111111111111111111111111111111111111111111111");
        Assert.assertTrue(res == 1836311903);
    }

}
