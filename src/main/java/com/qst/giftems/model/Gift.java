package com.qst.giftems.model;

import java.util.ArrayList;

public class Gift {
    public int id;
    public String name; /** 礼品名称 */
    public String remark;   /** 礼品简介 */
    public int likes;   /** 礼品被喜欢的次数 */
    public int sales;   /** 累计销量 */
    public boolean collected;   /** 是否被当前用户收藏 */
    public ArrayList<GiftStyle> styles = new ArrayList<GiftStyle>();    /** 款式列表 */
}
