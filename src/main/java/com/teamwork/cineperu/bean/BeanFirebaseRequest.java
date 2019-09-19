package com.teamwork.cineperu.bean;

import java.util.List;

public class BeanFirebaseRequest {

	private List<String> registration_ids;
	private String priority;
	private BeanFirebaseBodyRequest data;

	public List<String> getRegistration_ids() {
		return registration_ids;
	}

	public void setRegistration_ids(List<String> registration_ids) {
		this.registration_ids = registration_ids;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public BeanFirebaseBodyRequest getData() {
		return data;
	}

	public void setData(BeanFirebaseBodyRequest data) {
		this.data = data;
	}

}
