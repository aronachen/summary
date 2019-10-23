package com.qst.giftems.model;

import android.support.annotation.NonNull;

public class GiftType implements Comparable<GiftType>{

    public int id;
    public String name; /** 名称 */
    public String pic;   /** 图片 */
    public int orderNum;    /** 顺序号 */

    @Override
    public int compareTo(@NonNull GiftType another) {
        return orderNum < another.orderNum ? -1 : (orderNum == another.orderNum ? 0 : 1);
    }
}
