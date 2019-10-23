package com.qst.giftems.model;

/**
 * 首页资源
 */
public class IndexPageResource {
    public int id;
    /** 图片地址 **/
    public String picUrl;
    /** 配置类型 1:pc 2:android 3:ios **/
    public int type;
    /** 每种类型图片的顺序 **/
    public int orderNum;
    /** 0:未发布 1：已发布 **/
    public int status;
    /** 位置 1:礼物推荐上部 2.礼物推荐下部左侧 3.礼物推荐下部右侧 4:礼品中心 5:礼品攻略 **/
    public int place;
}