package com.rjliulei.quanzi.bean;

import cn.bmob.v3.BmobObject;

public class Feedback extends BmobObject {
	// ��������
	private String content;
	// ��ϵ��ʽ
	private String userId;
	private String userName;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
