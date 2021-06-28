package com.yudl.sturcture.dynamic_programming;

import java.util.Scanner;

/**
 * @Author: ydl
 * @Description: 动态规划
 * @CreateDate: 2021-06-26
 */
public class DynamicTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long max = 0;
        int[][] dp = new int[n][n];
        dp[0][0] = in.nextInt();
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int num = in.nextInt();
                if (j == 0)
                    dp[i][j] = dp[i - 1][j] + num;
                else
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + num;
                max = Math.max(dp[i][j], max);
            }
        }
        System.out.println(max);
    }

}
