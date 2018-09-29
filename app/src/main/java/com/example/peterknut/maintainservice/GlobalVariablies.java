package com.example.peterknut.maintainservice;

import java.text.SimpleDateFormat;
import java.util.LinkedList;

import okhttp3.MediaType;

public class GlobalVariablies {

    //登陆地址
    public static final String LOGIN_URL = "http://192.168.2.100/login";
    //获取用户详细信息地址
    public static final String GET_USER_DETAIL_URL = "http://192.168.2.100/sys/user/getUserDetail";
    //获取用户所有订单
    public static final String GET_ORDER_URL = "http://192.168.2.100/as/orderView/mylist?limit=10&offset=0";
    //获取故障列表
    public static final String GET_FAULT_URL = "http://192.168.2.100/as/fault/list";
    //获取文献列表
    public static final String GET_DOCUMENT_RUL = "http://192.168.2.100/as/document/list";

    //对订单进行更新
    public static final String UPDATE_ORDER_URL = "http://192.168.2.100/as/orderView/update";
    //获取订单描述图片
    public static final String GET_IMAGE_URL = "http://192.168.2.100/as/orderView/listFile";
    //获取订单描述图片
    public static final String GET_IMAGE_URL1 = "http://192.168.2.100/files/4dcaf852-9e86-4fbf-b51a-81423b5705eb.jpg";
    //获取订单描述图片
    public static final String GET_IMAGE_URL2 = "http://192.168.2.100/files/a94bc1d8-cb67-4793-8b23-b488ba572104.png";
    //获取视频描述
    public static final String GET_Video_URL1 = "http://192.168.2.100/files/5403a555-7e4d-42a1-801f-09e851b54e5a.mp4";
    // 取消
    public static final String NOTIFY_SAVE = "http://192.168.2.100/oa/notify/save";
    //存储当前用户所有工单
    public static LinkedList<Order> allWorkOrder = new LinkedList<>() ;
    //存储当前用户待签收工单
    public static LinkedList<Order> unSignedInOrder = new LinkedList<>();
    //存储当前用户待签到工单
    public static LinkedList<Order> unCheckInOrder = new LinkedList<>();
    //存储当前用户未完工工单
    public static LinkedList<Order> unFinishedOrder = new LinkedList<>();
    //存储当前用户待评价工单
    public static LinkedList<Order> unCommentOrder = new LinkedList<>();
    //存储当前用户已完成工单
    public static LinkedList<Order> finishedOrder = new LinkedList<>();
    //存储故障库
    public static LinkedList<Fault> faultLinkedList = new LinkedList<>();
    //存储文献库
    public static LinkedList<Document> documentLinkedList = new LinkedList<>();

    //时间格式
    public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //用户取消操作时返回的备注
    public static String remarks = "";



    //记录当前查看的工单在列表中的编号
    public static int orderPosition;
    //记录当前查看的工单的状态(1, 待签收；2， 待签到； 3， 待完成； 4， 未评价； 5， 已完成)
    public static int orderStatus;
    //当前故障位置
    public static int faultPosition;
    //当前文献位置
    public static int documentPosition;


    //保存登陆之后的用户信息
    public static User user;

}
