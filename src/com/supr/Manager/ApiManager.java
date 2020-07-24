package com.supr.Manager;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.supr.Model.Api;
import com.supr.Model.Granularity;
import com.supr.Model.Method;
import com.supr.Model.MethodType;
import com.supr.Model.Service;



public class ApiManager {
	private HashMap<Service, Queue<Date>> serviceLog;
	private HashMap<Api , Queue<Date>> apiLog;
	private HashMap<String , Service> serviceObject;
	private HashMap<String , Api> apiObject;
	
	public ApiManager() {
		serviceLog = new HashMap<>();
		apiLog = new HashMap<>();
		serviceObject = new HashMap<>();
		apiObject = new HashMap<>();
	}

	
	
	
	public Service getServiceObject(String key) {
		return serviceObject.get(key);
	}

	public void addServiceObject(Service serviceObject) {
		this.serviceObject.put(serviceObject.getName(), serviceObject);
	}

	public Api getApiObject(String key) {
		return apiObject.get(key);
	}

	public void addApiObject(Api apiObject) {
		this.apiObject.put(apiObject.getName(),apiObject);
	}

	public HashMap<Service, Queue<Date>> getServiceLog() {
		return serviceLog;
	}




	public void setServiceLog(HashMap<Service, Queue<Date>> serviceLog) {
		this.serviceLog = serviceLog;
	}




	public HashMap<Api, Queue<Date>> getApiLog() {
		return apiLog;
	}




	public void setApiLog(HashMap<Api, Queue<Date>> apiLog) {
		this.apiLog = apiLog;
	}
	
	
	public void addApiLogSetup(Api api) {
		this.apiLog.put(api, new LinkedList<Date>());
	}
	
	
	public void addServiceLogSetup(Service service) {
		this.serviceLog.put(service, new LinkedList<Date>());
	}




	public void hitApi(Service service , Api api , MethodType methodType ) {
		Date d = new Date();
		Queue ser = serviceLog.get(service);
		Queue ap = apiLog.get(api);
		
		List<Method> methodLimit = service.getGlobalLimit();
		List<Method> localLimit = api.getLocalLimit();
		Method m = null;
		Method l = null;
		
		for(int i=0;i<methodLimit.size();i++) {
			if(methodLimit.get(i).getMethodType().equals(methodType)) {
				m = methodLimit.get(i);
				break;
			}
		}
		
		for(int i=0;i<localLimit.size();i++) {
			if(localLimit.get(i).getMethodType().equals(methodType)) {
				l = localLimit.get(i);
				break;
			}
		}
		
		if(m.getGranularity().equals(Granularity.SECOND)) {
			while(ser.size()>0) {
				Date pre = (Date) ser.peek();
				if((d.getTime() - pre.getTime())/1000 > 1) {
					ser.poll();
				}else {
					break;
				}
			}
		}else if(m.getGranularity().equals(Granularity.MINUTE)) {
			while(ser.size()>0) {
				Date pre = (Date) ser.peek();
				if((d.getTime() - pre.getTime())/(60 * 1000) > 1) {
					ser.poll();
				}else {
					break;
				}
			}
		}
		
		
		if(ser.size()<m.getLimit()) {
			if(l.getGranularity().equals(Granularity.SECOND)) {
				while(ap.size()>0) {
					Date preApiDate = (Date) ap.peek();
					if((d.getTime() - preApiDate.getTime())/1000 > 1) {
						ap.poll();
					}else {
						break;
					}
				}
			}else if(l.getGranularity().equals(Granularity.MINUTE)) {
				while(ap.size()>0) {
					Date preApiDate = (Date) ap.peek();
					if((d.getTime() - preApiDate.getTime())/(60 * 1000) > 1) {
						ap.poll();
					}else {
						break;
					}
				}
			}
			
			if(ap.size()<l.getLimit()) {
				System.out.println("Accepeted");
				ap.add(d);
				ser.add(d);
				serviceLog.put(service, ser);
				apiLog.put(api, ap);
			}else {
				System.out.println("Rejected");
			}
			
		}else {
			System.out.println("Rejected");
		}
		
	}
	
	
	
	
	
	
}
