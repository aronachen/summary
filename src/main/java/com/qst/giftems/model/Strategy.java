package com.qst.giftems.model;

public class Strategy implements Comparable<Strategy> {
    public int id;
    /** 攻略名称 */
    public String title;
    /** 说明 */
    public String remark;
    /** 图片1 */
    public String pic1;
    /** 图片2 */
    public String pic2;
    /** 图片3 */
    public String pic3;
    /** 图片4 */
    public String pic4;
    /** 图片5 */
    public String pic5;
    /** 内容 */
    public String content;
    /** 创建时间 */
    public String createdTime;

    @Override
    public int compareTo(Strategy another) {
        return createdTime.compareTo(another.createdTime);
    }
}
