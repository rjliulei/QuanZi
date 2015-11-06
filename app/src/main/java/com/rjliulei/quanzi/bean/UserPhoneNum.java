package com.rjliulei.quanzi.bean;

import cn.bmob.v3.BmobObject;

/**
 * 用户号码对照表
 * Created by Administrator on 15-10-29.
 */
public class UserPhoneNum extends BmobObject {

    /***
     * 正常状态*/
    public static final int STATE_NORMAL = 0;
    /***
     * 停用状态*/
    public static final int STATE_BLOCK = 1;

    private String userId;//用户表中的用户id
    private String phoneId;//号码表中的号码id
    private String phoneNum;//手机号码
    private int state;//手机号码状态
    private String realName;//用户真实姓名
    private String comment;//备注

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
