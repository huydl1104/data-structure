package com.yudl.sturcture.digui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yudongliang
 * create time 2021-06-01
 * leetCode 解决方案
 * describe : 理论上 应该创建一个  二位数组来 表示棋盘，但是实际上可以通过算法，用一个一位数组可以解决问题，
 *          arr[8] = {0,4,7,5,2,6,1,3} // 对应下标arr ；下标表示第几行，即表示第几个皇后，
 *          arr[i] = value, value 表示 第i+1个皇后，放在 第i+1行的，第value+1列
 */
class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> lists = solution.solveNQueens(8);
        for (List<String> list : lists) {
            for (String s : list) {
                System.out.print(s+"\t");
            }
            System.out.println();
        }
        System.out.println(lists.size());
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<Integer>();//共有几列
        Set<Integer> diagonals1 = new HashSet<Integer>();//对角线1
        Set<Integer> diagonals2 = new HashSet<Integer>();//对角线2
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);//回溯
        return solutions;
    }

    public void backtrack(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)){
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)){
                    continue;
                }
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}

