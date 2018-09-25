package com.example.peterknut.maintainservice;

import java.util.LinkedList;

import okhttp3.MediaType;

public class GlobalVariablies {
    //传送的JSON格式
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    //登陆地址
    public static final String LOGIN_URL = "http://192.168.2.100/login";
    //获取用户详细信息地址
    public static final String GET_USER_DETAIL_URL = "http://192.168.2.100/sys/user/getUserDetail";
    //获取用户所有订单
    public static final String GET_ORDER_URL = "http://192.168.2.100/as/orderView/list?limit=10&offset=0";
    //存储当前用户所有工单
    public static LinkedList<Order> allWorkOrder = new LinkedList<>();
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

    //保存登陆之后的用户信息
    public static User user;

}
