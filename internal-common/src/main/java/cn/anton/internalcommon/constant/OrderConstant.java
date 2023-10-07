package cn.anton.internalcommon.constant;

/*
 * @author: Anton
 * @create_date: 2023/10/6 17:08
 */
public class OrderConstant {
 
 /**
  * 订单状态
  * 订单状态1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.订单取消'
  */
 
 /**
  * 订单开始
  */
 public static final Integer ORDER_STATUS_START = 1;
 /**
  * 司机接单
  */
 public static final Integer ORDER_STATUS_DRIVER_TAKE= 2;
 /**
  * 司机去接乘客
  */
 public static final Integer ORDER_STATUS_PICKUP = 3;
 /**
  * 司机到达乘客起点
  */
 public static final Integer ORDER_STATUS_ARRIVED_POINT = 4;
 /**
  * 乘客上车，司机行程开始
  */
 public static final Integer ORDER_STATUS_GETIN = 5;
 /**
  * 到达目的地，行程结束，未支付
  */
 public static final Integer ORDER_STATUS_ARRIVED_DESTINATION = 6;
 /**
  * 司机发起收款
  */
 public static final Integer ORDER_STATUS_COLLECTION = 7;
 /**
  * 乘客支付完成
  */
 public static final Integer ORDER_STATUS_PAY_FINISH = 8;
 /**
  * 订单取消
  */
 public static final Integer ORDER_STATUS_CANCEL = 9;
 /**
  * 没有交到车 订单取消
  */
 public static final Integer ORDER_STATUS_NOT_CAR = 0;
 
}
