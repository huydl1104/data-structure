package com.yudl.sturcture.kmp;

/**
 * @Author: ydl
 * @Description: kmp算法
 * @CreateDate: 2021-06-26
 */
public class KmpJava {

    public static void main(String[] args) {
        String s = "abcacabcaabcabcac";
        String p = "abcab";
        int kmp = KMP(s, p);
        System.out.println(kmp);

    }
    public static int[] getNext(String s){
        int[] next = new int[s.length()];
        next[0]=-1;
        int i = 0;
        int j = -1;
        while(i<s.length()-1){
            if(j==-1 || s.charAt(i)==s.charAt(j)){
                i++;
                j++;
                next[i]=j;
            }else{
                j=next[j];
            }
        }
        return next;
    }

    public static int KMP(String s,String p){
        int[] next = getNext(p);
        int i=0,j=0;
        while(i<s.length() && j<p.length()){
            if(j==-1 || s.charAt(i) == p.charAt(j)){
                i++;
                j++;
            }else{
                //避免会退到0
                j=next[j];
            }
        }
        if(j==p.length()){
            return i-(p.length());
        }else{
            return -1;
        }
    }
}
