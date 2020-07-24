package com.supr.Driver;

import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import com.supr.Manager.ApiManager;
import com.supr.Service.ApiService;

public class Driver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ApiManager apiManager = new ApiManager();
		ApiService apiService = new ApiService(apiManager);
		
		
		//setup
		String json = "{\n" + 
				"  \"serviceLimits\": [\n" + 
				"    {\n" + 
				"      \"service\": \"OrderService\",\n" + 
				"      \"globalLimits\": {\n" + 
				"        \"GET\": {\n" + 
				"          \"limit\": 10,\n" + 
				"          \"granularity\": \"second\"\n" + 
				"        },\n" + 
				"        \"POST\": {\n" + 
				"          \"limit\": 20,\n" + 
				"          \"granularity\": \"minute\"\n" + 
				"        }\n" + 
				"      },\n" + 
				"      \"apiLimits\": [\n" + 
				"        {\n" + 
				"          \"methods\": {\n" + 
				"            \"GET\": {\n" + 
				"              \"limit\": 15,\n" + 
				"              \"granularity\": \"second\"\n" + 
				"            },\n" + 
				"            \"POST\": {\n" + 
				"              \"limit\": 20,\n" + 
				"              \"granularity\": \"minute\"\n" + 
				"            }\n" + 
				"          },\n" + 
				"          \"api\": \"CreateOrder\"\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"methods\": {\n" + 
				"            \"GET\": {\n" + 
				"              \"limit\": 10,\n" + 
				"              \"granularity\": \"second\"\n" + 
				"            },\n" + 
				"            \"POST\": {\n" + 
				"              \"limit\": 10,\n" + 
				"              \"granularity\": \"second\"\n" + 
				"            }\n" + 
				"          },\n" + 
				"          \"api\": \"GetOrderById\"\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"service\": \"DeliveryService\",\n" + 
				"      \"globalLimits\": {\n" + 
				"        \"GET\": {\n" + 
				"          \"limit\": 10,\n" + 
				"          \"granularity\": \"second\"\n" + 
				"        },\n" + 
				"        \"POST\": {\n" + 
				"          \"limit\": 20,\n" + 
				"          \"granularity\": \"minute\"\n" + 
				"        }\n" + 
				"      },\n" + 
				"      \"apiLimits\": []\n" + 
				"    }\n" + 
				"  ]\n" + 
				"}";
		
		try {
			JSONObject jsonObject = new JSONObject(json);
			apiService.setup(jsonObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			String[] input = sc.nextLine().split(" ");
			try {
				apiService.hitApi(input[0], input[1], input[2]);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
