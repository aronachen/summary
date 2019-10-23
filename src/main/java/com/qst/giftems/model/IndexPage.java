package com.qst.giftems.model;

import java.util.ArrayList;

/**
 * 首页资源
 */
public class IndexPage {
    /** 服务器地址 */
    static final String SERVER = "http://localhost:8080/giftems";

    /** 礼物推荐上部轮换内容 */
    public String[] recommendTop;
    /** 礼物推荐下部左侧图片 */
    public String recommendBottomLeft;
    /** 礼物推荐下部右侧图片 */
    public String recommendBottomRight;
    /** 普通礼品图片 */
    public String giftCommon;
    /** 送礼攻略图片 */
    public String strategy;

    public IndexPage(ArrayList<IndexPageResource> settings) {
        ArrayList<String> recommendTopList = new ArrayList<String>();
        for (IndexPageResource s : settings) {
            switch (s.place) {
                case 1:
                    recommendTopList.add(SERVER + s.picUrl);
                    break;
                case 2:
                    recommendBottomLeft = SERVER + s.picUrl;
                    break;
                case 3:
                    recommendBottomRight = SERVER + s.picUrl;
                    break;
                case 4:
                    giftCommon = SERVER + s.picUrl;
                    break;
                case 5:
                    strategy = SERVER + s.picUrl;
                    break;
            }
        }
        recommendTop = recommendTopList.toArray(new String[recommendTopList
                .size()]);
    }
}
