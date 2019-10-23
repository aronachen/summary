package com.qst.giftems.model;

public class OrderItem {
    public Integer id;
    public Integer orderId;  /** 所属订单Id **/
    public Integer giftId;  /** 礼品编号 **/
    public String giftName; /** 礼品名称 **/
    public Integer giftCount;   /** 购买礼品数量 **/
    public Integer styleId; /** 款式Id **/
    public String styleName;    /** 款式名称 **/
    public float discount;  /** 款式折扣价格 **/
    public float stylePrice;    /** 款式价格 **/
    public String remark;   /** 备注 **/
    public String stylePic1;    /** 对应礼品款式的图片1 **/
    public Order order; /** 对应订单 **/
}
