package com.supr.Service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.supr.Manager.ApiManager;
import com.supr.Model.Api;
import com.supr.Model.Granularity;
import com.supr.Model.Method;
import com.supr.Model.MethodType;
import com.supr.Model.Service;

public class ApiService {
	
	private ApiManager apiManager;
	
	public ApiService(ApiManager apiManager) {
		this.apiManager = new ApiManager();
	}
	
	
	
	public void hitApi(String serviceName , String apiName , String methodType) {
		Service service = apiManager.getServiceObject(serviceName);
		Api api = apiManager.getApiObject(apiName);
		MethodType method = MethodType.valueOf(methodType.toUpperCase());
		this.apiManager.hitApi(service, api, method);
	}
	
	public void addService(Service serviceObject) {
		apiManager.addServiceObject(serviceObject);
		apiManager.addServiceLogSetup(serviceObject);
	}
	
	public void addApi(Api apiObject) {
		apiManager.addApiObject(apiObject);
		apiManager.addApiLogSetup(apiObject);
	}



	public void setup(JSONObject jsonObject) {
		try {
			JSONArray services = jsonObject.getJSONArray("serviceLimits");
			for(int i=0;i<services.length();i++) {
				
				JSONObject serviceObject = services.getJSONObject(i);
				String serviceName = serviceObject.getString("service");
				JSONObject globalLimit = serviceObject.getJSONObject("globalLimits");
				Iterator<String> keys = globalLimit.keys();
				List<Method> methods = new ArrayList<Method>();
				while(keys.hasNext()) {
				    String key = keys.next();
				    if (globalLimit.get(key) instanceof JSONObject) {
				    	JSONObject method = globalLimit.getJSONObject(key);
				    	Method methodObject = new Method(MethodType.valueOf(key),Integer.parseInt(method.getString("limit")) , Granularity.valueOf(method.getString("granularity").toUpperCase()));
				    	methods.add(methodObject);
				    }
				}
				List<Api> apiList = new ArrayList<>();
				JSONArray apiLimits = serviceObject.getJSONArray("apiLimits");
				
				for(int j=0;j<apiLimits.length();j++) {
					JSONObject apiObject = apiLimits.getJSONObject(j);
					String apiName = apiObject.getString("api");
					JSONObject apiMethod = apiObject.getJSONObject("methods");
					Iterator<String> apiKeys = apiMethod.keys();
					List<Method> apiMethods = new ArrayList<Method>();
					while(apiKeys.hasNext()) {
					    String key = apiKeys.next();
					    if (apiMethod.get(key) instanceof JSONObject) {
					    	JSONObject method = apiMethod.getJSONObject(key);
					    	Method methodObject = new Method(MethodType.valueOf(key),Integer.parseInt(method.getString("limit")) , Granularity.valueOf(method.getString("granularity").toUpperCase()));
					    	apiMethods.add(methodObject);
					    }
					}
					
					Api api = new Api(apiName,apiMethods);
					
					//save
					addApi(api);
					apiList.add(api);
					
					
				}
				
				Service service = new Service(serviceName , methods ,apiList);
				addService(service);
			
				
			}
			
			System.out.println("setup done");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}
