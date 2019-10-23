package com.qst.giftems.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    public static final int PAY_SUCCESS = 1;    //状态:交易成功 前台：确认收货后,状态改变
    /**
     * 状态:交易关闭 后台:退货后，交易关闭 或 前台订单超时后，交易关闭 或 前台申请退货，后台操作“退货”按钮
     * 订单待支付时,用户也可以选择管理该订单进行关闭
     */
    public static final int PAY_CLOSED = 2;
    public static final int PAY_DAIZHIFU = 3;   //状态：买家待支付 前台：用户提交订单后，修改至买家待支付状态
    public static final int PAY_YIZHIFU = 4;    //状态：买家已支付 前台：支付成功后，显示买家已支付 后台：根据买家已支付的状态,修改卖家代发货状态
    public static final int PAY_DENGDAIDAIFU = 5;   //状态：等待代付 前台：用户操作：代付"
    public static final int PAY_DAIFUWANCHENG = 6;  //状态：代付完成 前台：代付成功后,显示代付完成 后台：根据买家支付情况,修改卖家代发货状态
    public static final int PAY_DAIFAHUO = 7;   //状态：卖家待发货 后台：卖家发货后,点击"发货"按钮后状态变为已发货
    public static final int PAY_YIFAHUO = 8;    //状态：卖家已发货 前台：用户点击"确认收货"按钮后,修改PAY_SUCCESS状态
    public static final int PAY_TUIHUO = 9; //状态 ：退货中 前台：点击 退款按钮，并修改订单状态为 PAY_TUIHUO状态 后台:受理退货,查询待退款状态的订单，修改状态为交易关闭

    public Integer id;  /** 订单ID **/
    public String orderNo;  //订单编号(订单编号(yyyyMMddHHmmssSSS+1000内随机数) 随机数默认为3位，不够的话前面补0，这样确定为20位)
    public String deliveryNo;   /** 快递单号:后台操作发货时填写的快递单号 **/
    public String deliveryName; /** 快递公司名称 **/
    public String transNo;  /** 支付交易号 **/
    public Integer userId;  /** 付款人ID（user） **/
    public String userName; /** 付款人用户名(user) **/
    public String receiverName; /** 收礼人姓名 **/
    public String receiverPhone;    /** 收礼人电话 **/
    public String receiverAddress;  /** 收礼人地址 **/
    public String arriveDate;   /** 指定送达日期 **/
    public Float price; /** 订单金额(人民币) **/
    public String payTime;  /** 付款时间 **/
    public String createTime;   /** 订单生成时间 **/
    public String expiredTime;  /** 订单过期时间(订单生成之后7天为缓冲付款时间，过期不可在付款) */
    public Integer status;  /** 订单状态 */
    public String orderInfo;    /** 订单状态信息（对应订单状态，为何未付款，付款失败的原因等） */

    public String attachInfo;   /** 订单附加信息 **/
    /**
     * 订单删除 0:正常 1:已删除(用户回收站) 2:彻底删除(用户看不到) 3:永久隐藏(除非数据库恢复)
     * 对于订单的删除是用户行为,后台对用户彻底删除的订单进行清理(建议不删除)
     */
    public Integer deleted;
    public Integer couponItemId;    /** 优惠券Id **/
    public List<OrderItem> items = new ArrayList<OrderItem>();  /** 订单Item集合 */
    public String specifyTime;  /** 指定日期送达时间 */
    public String specifyFlag;  /** 指定日期送达标志位 **/
    public String leaveWords;   /** 文字留言 **/
    public String leaveSound;   /** 留言录音 **/
    private String statusName;  /** 订单状态名称 **/

    /** 订单状态名称 **/
    public String getStatusName() {
        if (statusName == null || statusName.length() == 0) {
            switch (status) {
                case PAY_SUCCESS:
                    statusName = "交易成功";
                    break;
                case PAY_CLOSED:
                    statusName = "交易关闭";
                    break;
                case PAY_DAIZHIFU:
                    statusName = "等待支付";
                    break;
                case PAY_YIZHIFU:
                    statusName = "已支付";
                    break;
                case PAY_DENGDAIDAIFU:
                    statusName = "等待代付";
                    break;
                case PAY_DAIFUWANCHENG:
                    statusName = "代付完成";
                    break;
                case PAY_DAIFAHUO:
                    statusName = "等待发货";
                    break;
                case PAY_YIFAHUO:
                    statusName = "已发货";
                    break;
                case PAY_TUIHUO:
                    statusName = "退货中";
                    break;
            }
        }
        return statusName;
    }

}
