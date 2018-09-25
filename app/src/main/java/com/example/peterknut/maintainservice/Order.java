package com.example.peterknut.maintainservice;

/**
 * 订单类，包含一个订单的所有信息
 * 订单状态通过int类型的数值进行定义
 */
// TODO: 2018/9/22 根据关关的定义进行定义
import java.util.Date;



/**
 * VIEW
 *
 * @author the_sam
 * @email the_sam@foxmail.com
 * @date 2018-09-26 01:14:52
 */
public class Order  {


    //维修单号
    private String orderId;
    //报修时间
    private Date repairTime;
    //预计开始时间
    private Date estimatedStartTime;
    //预计结束时间
    private Date estimatedEndTime;
    //维修地址
    private String repairAddress;
    //省份
    private String province;
    //所在城市
    private String city;
    //所在地区
    private String district;
    //联系人姓名
    private String contactName;
    //联系人电话
    private String contactMobile;
    //状态：未派工>未签收>未签到>未完工>未评价>已完成
    private Integer status;
    //故障描述
    private String faultDescription;
    //备注
    private String remarks;
    //设备名称
    private String deviceName;
    //派工时间
    private Date sendJobTime;
    //签收时间
    private Date acceptTime;
    //签收备注
    private String acceptRemark;
    //签到时间
    private Date signinTime;
    //签到备注
    private String siginRemark;
    //完工时间
    private Date completedTime;
    //完工总结
    private String completedRemark;
    //客户评价时间
    private Date reviewsTime;
    //评价内容
    private String reviewRemark;
    //评价等级
    private Integer reviewLevel;
    //完成时间
    private Date finishTime;
    //客户公司
    private Integer clientId;
    //设备号
    private Integer deviceId;
    //维修员
    private Long repairUserId;
    //错误类型
    private Long faultTypeId;
    //管理员
    private Long dealUserId;
    //维修单号
    private String repairId;
    //
    private String repairUserName;
    //
    private String dealUserName;
    //客户公司
    private String clientName;
    //故障名称
    private String faultTypeName;

    public Order() {
    }

    public Order(String orderId, Date repairTime, Date estimatedStartTime, Date estimatedEndTime, String repairAddress, String province, String city, String district, String contactName, String contactMobile, Integer status, String faultDescription, String remarks, String deviceName, Date sendJobTime, Date acceptTime, String acceptRemark, Date signinTime, String siginRemark, Date completedTime, String completedRemark, Date reviewsTime, String reviewRemark, Integer reviewLevel, Date finishTime, Integer clientId, Integer deviceId, Long repairUserId, Long faultTypeId, Long dealUserId, String repairId, String repairUserName, String dealUserName, String clientName, String faultTypeName) {
        this.orderId = orderId;
        this.repairTime = repairTime;
        this.estimatedStartTime = estimatedStartTime;
        this.estimatedEndTime = estimatedEndTime;
        this.repairAddress = repairAddress;
        this.province = province;
        this.city = city;
        this.district = district;
        this.contactName = contactName;
        this.contactMobile = contactMobile;
        this.status = status;
        this.faultDescription = faultDescription;
        this.remarks = remarks;
        this.deviceName = deviceName;
        this.sendJobTime = sendJobTime;
        this.acceptTime = acceptTime;
        this.acceptRemark = acceptRemark;
        this.signinTime = signinTime;
        this.siginRemark = siginRemark;
        this.completedTime = completedTime;
        this.completedRemark = completedRemark;
        this.reviewsTime = reviewsTime;
        this.reviewRemark = reviewRemark;
        this.reviewLevel = reviewLevel;
        this.finishTime = finishTime;
        this.clientId = clientId;
        this.deviceId = deviceId;
        this.repairUserId = repairUserId;
        this.faultTypeId = faultTypeId;
        this.dealUserId = dealUserId;
        this.repairId = repairId;
        this.repairUserName = repairUserName;
        this.dealUserName = dealUserName;
        this.clientName = clientName;
        this.faultTypeName = faultTypeName;
    }

    public Order(String orderId, Date repairTime, String clientName, String contactName, String devices, int status) {
        this.orderId = orderId;
        this.repairTime = repairTime;
        this.clientName = clientName;
        this.contactName = contactName;
        this.deviceName = devices;
        this.status = status;
    }

    /**
     * 设置：维修单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    /**
     * 获取：维修单号
     */
    public String getOrderId() {
        return orderId;
    }
    /**
     * 设置：报修时间
     */
    public void setRepairTime(Date repairTime) {
        this.repairTime = repairTime;
    }
    /**
     * 获取：报修时间
     */
    public Date getRepairTime() {
        return repairTime;
    }
    /**
     * 设置：预计开始时间
     */
    public void setEstimatedStartTime(Date estimatedStartTime) {
        this.estimatedStartTime = estimatedStartTime;
    }
    /**
     * 获取：预计开始时间
     */
    public Date getEstimatedStartTime() {
        return estimatedStartTime;
    }
    /**
     * 设置：预计结束时间
     */
    public void setEstimatedEndTime(Date estimatedEndTime) {
        this.estimatedEndTime = estimatedEndTime;
    }
    /**
     * 获取：预计结束时间
     */
    public Date getEstimatedEndTime() {
        return estimatedEndTime;
    }
    /**
     * 设置：维修地址
     */
    public void setRepairAddress(String repairAddress) {
        this.repairAddress = repairAddress;
    }
    /**
     * 获取：维修地址
     */
    public String getRepairAddress() {
        return repairAddress;
    }
    /**
     * 设置：省份
     */
    public void setProvince(String province) {
        this.province = province;
    }
    /**
     * 获取：省份
     */
    public String getProvince() {
        return province;
    }
    /**
     * 设置：所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * 获取：所在城市
     */
    public String getCity() {
        return city;
    }
    /**
     * 设置：所在地区
     */
    public void setDistrict(String district) {
        this.district = district;
    }
    /**
     * 获取：所在地区
     */
    public String getDistrict() {
        return district;
    }
    /**
     * 设置：联系人姓名
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /**
     * 获取：联系人姓名
     */
    public String getContactName() {
        return contactName;
    }
    /**
     * 设置：联系人电话
     */
    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }
    /**
     * 获取：联系人电话
     */
    public String getContactMobile() {
        return contactMobile;
    }
    /**
     * 设置：状态：未派工>未签收>未签到>未完工>未评价>已完成
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * 获取：状态：未派工>未签收>未签到>未完工>未评价>已完成
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * 设置：故障描述
     */
    public void setFaultDescription(String faultDescription) {
        this.faultDescription = faultDescription;
    }
    /**
     * 获取：故障描述
     */
    public String getFaultDescription() {
        return faultDescription;
    }
    /**
     * 设置：备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    /**
     * 获取：备注
     */
    public String getRemarks() {
        return remarks;
    }
    /**
     * 设置：设备名称
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    /**
     * 获取：设备名称
     */
    public String getDeviceName() {
        return deviceName;
    }
    /**
     * 设置：派工时间
     */
    public void setSendJobTime(Date sendJobTime) {
        this.sendJobTime = sendJobTime;
    }
    /**
     * 获取：派工时间
     */
    public Date getSendJobTime() {
        return sendJobTime;
    }
    /**
     * 设置：签收时间
     */
    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }
    /**
     * 获取：签收时间
     */
    public Date getAcceptTime() {
        return acceptTime;
    }
    /**
     * 设置：签收备注
     */
    public void setAcceptRemark(String acceptRemark) {
        this.acceptRemark = acceptRemark;
    }
    /**
     * 获取：签收备注
     */
    public String getAcceptRemark() {
        return acceptRemark;
    }
    /**
     * 设置：签到时间
     */
    public void setSigninTime(Date signinTime) {
        this.signinTime = signinTime;
    }
    /**
     * 获取：签到时间
     */
    public Date getSigninTime() {
        return signinTime;
    }
    /**
     * 设置：签到备注
     */
    public void setSiginRemark(String siginRemark) {
        this.siginRemark = siginRemark;
    }
    /**
     * 获取：签到备注
     */
    public String getSiginRemark() {
        return siginRemark;
    }
    /**
     * 设置：完工时间
     */
    public void setCompletedTime(Date completedTime) {
        this.completedTime = completedTime;
    }
    /**
     * 获取：完工时间
     */
    public Date getCompletedTime() {
        return completedTime;
    }
    /**
     * 设置：完工总结
     */
    public void setCompletedRemark(String completedRemark) {
        this.completedRemark = completedRemark;
    }
    /**
     * 获取：完工总结
     */
    public String getCompletedRemark() {
        return completedRemark;
    }
    /**
     * 设置：客户评价时间
     */
    public void setReviewsTime(Date reviewsTime) {
        this.reviewsTime = reviewsTime;
    }
    /**
     * 获取：客户评价时间
     */
    public Date getReviewsTime() {
        return reviewsTime;
    }
    /**
     * 设置：评价内容
     */
    public void setReviewRemark(String reviewRemark) {
        this.reviewRemark = reviewRemark;
    }
    /**
     * 获取：评价内容
     */
    public String getReviewRemark() {
        return reviewRemark;
    }
    /**
     * 设置：评价等级
     */
    public void setReviewLevel(Integer reviewLevel) {
        this.reviewLevel = reviewLevel;
    }
    /**
     * 获取：评价等级
     */
    public Integer getReviewLevel() {
        return reviewLevel;
    }
    /**
     * 设置：完成时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
    /**
     * 获取：完成时间
     */
    public Date getFinishTime() {
        return finishTime;
    }
    /**
     * 设置：客户公司
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
    /**
     * 获取：客户公司
     */
    public Integer getClientId() {
        return clientId;
    }
    /**
     * 设置：设备号
     */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
    /**
     * 获取：设备号
     */
    public Integer getDeviceId() {
        return deviceId;
    }
    /**
     * 设置：维修员
     */
    public void setRepairUserId(Long repairUserId) {
        this.repairUserId = repairUserId;
    }
    /**
     * 获取：维修员
     */
    public Long getRepairUserId() {
        return repairUserId;
    }
    /**
     * 设置：错误类型
     */
    public void setFaultTypeId(Long faultTypeId) {
        this.faultTypeId = faultTypeId;
    }
    /**
     * 获取：错误类型
     */
    public Long getFaultTypeId() {
        return faultTypeId;
    }
    /**
     * 设置：管理员
     */
    public void setDealUserId(Long dealUserId) {
        this.dealUserId = dealUserId;
    }
    /**
     * 获取：管理员
     */
    public Long getDealUserId() {
        return dealUserId;
    }
    /**
     * 设置：维修单号
     */
    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }
    /**
     * 获取：维修单号
     */
    public String getRepairId() {
        return repairId;
    }
    /**
     * 设置：
     */
    public void setRepairUserName(String repairUserName) {
        this.repairUserName = repairUserName;
    }
    /**
     * 获取：
     */
    public String getRepairUserName() {
        return repairUserName;
    }
    /**
     * 设置：
     */
    public void setDealUserName(String dealUserName) {
        this.dealUserName = dealUserName;
    }
    /**
     * 获取：
     */
    public String getDealUserName() {
        return dealUserName;
    }
    /**
     * 设置：客户公司
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    /**
     * 获取：客户公司
     */
    public String getClientName() {
        return clientName;
    }
    /**
     * 设置：故障名称
     */
    public void setFaultTypeName(String faultTypeName) {
        this.faultTypeName = faultTypeName;
    }
    /**
     * 获取：故障名称
     */
    public String getFaultTypeName() {
        return faultTypeName;
    }
}
