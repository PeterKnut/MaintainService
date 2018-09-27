package com.example.peterknut.maintainservice;

//故障
public class Fault {
        //
        private Integer faultId;
        //设备类型
        private Long deviceTypeId;
        //设备型号
        private String deviceSpecification;
        //故障类型
        private int faultTypeId;
        //故障现象
        private String phenomenon;
        //故障原因
        private String reason;
        //处理方法
        private String solution;
        //备注
        private String remark;

    public Fault(int faultTypeId, String phenomenon) {
        this.faultTypeId = faultTypeId;
        this.phenomenon = phenomenon;
    }

    /**
         * 设置：
         */
        public void setFaultId(Integer faultId) {
            this.faultId = faultId;
        }
        /**
         * 获取：
         */
        public Integer getFaultId() {
            return faultId;
        }
        /**
         * 设置：设备类型
         */
        public void setDeviceTypeId(Long deviceTypeId) {
            this.deviceTypeId = deviceTypeId;
        }
        /**
         * 获取：设备类型
         */
        public Long getDeviceTypeId() {
            return deviceTypeId;
        }
        /**
         * 设置：设备型号
         */
        public void setDeviceSpecification(String deviceSpecification) {
            this.deviceSpecification = deviceSpecification;
        }
        /**
         * 获取：设备型号
         */
        public String getDeviceSpecification() {
            return deviceSpecification;
        }
        /**
         * 设置：故障类型
         */
        public void setFaultTypeId(int faultTypeId) {
            this.faultTypeId = faultTypeId;
        }
        /**
         * 获取：故障类型
         */
        public int getFaultTypeId() {
            return faultTypeId;
        }
        /**
         * 设置：故障现象
         */
        public void setPhenomenon(String phenomenon) {
            this.phenomenon = phenomenon;
        }
        /**
         * 获取：故障现象
         */
        public String getPhenomenon() {
            return phenomenon;
        }
        /**
         * 设置：故障原因
         */
        public void setReason(String reason) {
            this.reason = reason;
        }
        /**
         * 获取：故障原因
         */
        public String getReason() {
            return reason;
        }
        /**
         * 设置：处理方法
         */
        public void setSolution(String solution) {
            this.solution = solution;
        }
        /**
         * 获取：处理方法
         */
        public String getSolution() {
            return solution;
        }
        /**
         * 设置：备注
         */
        public void setRemark(String remark) {
            this.remark = remark;
        }
        /**
         * 获取：备注
         */
        public String getRemark() {
            return remark;
        }


}
