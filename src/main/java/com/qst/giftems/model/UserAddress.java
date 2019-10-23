package com.qst.giftems.model;

/**
 * 收件人
 */
public class UserAddress {
    public int id;
    public int userId;  /** 对应用户ID */
    public String name; /** 姓名 */
    public String mobile;   /** 手机 */
    public int provinceId;  /** 省ID */
    public int cityId;  /** 市ID */
    public int areaId;  /** 区ID */
    public String address;  /** 地址 */
    public String postcode; /** 邮编 */
    public String info; /** 信息 */
    public String provinceName; /** 省名 */
    public String cityName; /** 市名 */
    public String areaName; /** 区名 */
}
