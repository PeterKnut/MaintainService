package com.example.peterknut.maintainservice;

import java.util.Date;

public class User {


        //用户名
        private String username;
        //用户姓名
        private String name;
        //密码
        private String password;
        //电话号码
        private String mobile;
        //状态 0:禁用，1:正常
        private Integer status;
        //性别
        private Long sex;
        //现居住地
        private String liveAddress;
        //省份
        private String province;
        //所在城市
        private String city;
        //所在地区
        private String district;

        public User(String username, String name, String password, String mobile, Integer status, Long sex, String liveAddress, String province, String city, String district){
            this.username = username;
            this.name = name;
            this.password = password;
            this.mobile = mobile;
            this.status = status;
            this.sex = sex;
            this.liveAddress = liveAddress;
            this.province = province;
            this.city = city;
            this.district = district;
        }

        /**
         * 设置：用户名
         */
        public void setUsername(String username) {
            this.username = username;
        }
        /**
         * 获取：用户名
         */
        public String getUsername() {
            return username;
        }
        /**
         * 设置：
         */
        public void setName(String name) {
            this.name = name;
        }
        /**
         * 获取：
         */
        public String getName() {
            return name;
        }
        /**
         * 设置：密码
         */
        public void setPassword(String password) {
            this.password = password;
        }
        /**
         * 获取：密码
         */
        public String getPassword() {
            return password;
        }

        /**
         * 设置：电话号码
         */
        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
        /**
         * 获取：电话号码
         */
        public String getMobile() {
            return mobile;
        }
        /**
         * 设置：状态 0:禁用，1:正常
         */
        public void setStatus(Integer status) {
            this.status = status;
        }
        /**
         * 获取：状态 0:禁用，1:正常
         */
        public Integer getStatus() {
            return status;
        }


        /**
         * 设置：性别
         */
        public void setSex(Long sex) {
            this.sex = sex;
        }
        /**
         * 获取：性别
         */
        public Long getSex() {
            return sex;
        }

        /**
         * 设置：现居住地
         */
        public void setLiveAddress(String liveAddress) {
            this.liveAddress = liveAddress;
        }
        /**
         * 获取：现居住地
         */
        public String getLiveAddress() {
            return liveAddress;
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


}
