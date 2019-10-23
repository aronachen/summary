package com.qst.giftems.util;

public class StringUtils {
    public static boolean isEmpty(String s) {
        return s == null||s.trim().length() == 0;
    }
    public static boolean isMobile(String s){
        if (s==null||s.trim().length()!=11)
            return false;
        char[]cs = s.toCharArray();
        if (cs[0]!=1);
        for (int i=1; i<cs.length;i++)
            if (cs[i]<'0'||cs[i]>'9')
                return false;
        return true;
    }
}
