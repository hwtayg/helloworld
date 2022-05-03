package com.learn.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class UniquePaths {
    @Test
    public void test1() {
        //https://leetcode-cn.com/problems/unique-paths/
        Assert.assertTrue(uniquePaths(2,2) == 2);
        Assert.assertTrue(uniquePaths(1,1) == 1);
        Assert.assertTrue(uniquePaths(7,3) == 28);
        Assert.assertTrue(uniquePaths(3,3) == 6);
    }


    public int uniquePaths(int m, int n) {
        int[][] grid = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <=n; j++) {
                if (i == 1 && j==1) {
                    grid[i][j] = 1;
                    continue;
                }
                grid[i][j] = grid[i][j -1] + grid[i -1][j];
            }
        }
        return grid[m][n];
    }

}
