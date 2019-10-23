package com.qst.giftems.model;

public class GiftStyle {
    public int id;
    public String name; /** 款式名称 */
    public double price; /** 原价 */
    public double discount; /** 折后价 */
    public String pic1;  /** 图片1 */
    public String pic2; /** 图片2 */
    public String pic3; /** 图片3 */
    public String pic4; /** 图片4 */
    public String pic5; /** 图片5 */
    public String remark;   /** 介绍 */
    public int orderNumber; /** 顺序号，显示同一礼品的多个款式时排序用 */
    public Gift gift;   /** 对应礼品 */
}
