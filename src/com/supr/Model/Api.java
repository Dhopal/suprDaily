package com.supr.Model;

import java.util.List;

public class Api {
	private String name;
	private List<Method> localLimit;
	
	public Api(String name, List<Method> localLimit) {
		this.name = name;
		this.localLimit = localLimit;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Method> getLocalLimit() {
		return localLimit;
	}
	public void setLocalLimit(List<Method> localLimit) {
		this.localLimit = localLimit;
	}
}
