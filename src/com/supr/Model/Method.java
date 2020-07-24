package com.supr.Model;

public class Method {
	private MethodType methodType;
	private int limit ;
	private Granularity granularity;
	
	
	public Method(MethodType methodType, int limit, Granularity granularity) {
		this.methodType = methodType;
		this.limit = limit;
		this.granularity = granularity;
	}
	
	
	public MethodType getMethodType() {
		return methodType;
	}
	public void setMethodType(MethodType methodType) {
		this.methodType = methodType;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Granularity getGranularity() {
		return granularity;
	}
	public void setGranularity(Granularity granularity) {
		this.granularity = granularity;
	} 
	
	
	
	
	
	
}
