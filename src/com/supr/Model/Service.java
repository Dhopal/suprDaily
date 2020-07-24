package com.supr.Model;

import java.util.List;

public class Service {
	private String name;
	private List<Method> globalLimit;
	private List<Api> apiList;
	
	public Service(String name, List<Method> globalLimit,List<Api> apiList) {
		this.name = name;
		this.globalLimit = globalLimit;
		this.apiList = apiList; 
	}
	
	
	public List<Api> getApiList() {
		return apiList;
	}


	public void setApiList(List<Api> apiList) {
		this.apiList = apiList;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Method> getGlobalLimit() {
		return globalLimit;
	}
	public void setGlobalLimit(List<Method> globalLimit) {
		this.globalLimit = globalLimit;
	}
		
}
