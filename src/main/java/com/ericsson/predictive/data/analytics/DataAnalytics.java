/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: DataAnalytics.java
 * Created by: evijaka
 * Author: evijaka
 * Date : Jul 7, 2015
 */
/**
 * 
 */
package com.ericsson.predictive.data.analytics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ericsson.predictive.data.analytics.InputQuestionsAndOptions.UserInput;
import com.ericsson.predictive.model.HabitsResponse;
import com.ericsson.predictive.model.HealthResponse;
import com.ericsson.predictive.model.Smoking;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @author evijaka
 *
 */
public class DataAnalytics {

	public static void main(String[] args) throws IOException {
		try{
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new BufferedReader(new InputStreamReader(DataAnalytics.class.getClassLoader().getResourceAsStream(
					"input.json"))));
			JSONObject jsonObject = (JSONObject) obj;
			HealthResponse response = new HealthResponse();
			System.out.println(jsonObject);
			response.setName(getAsString(jsonObject.get("name")));
			response.setAge(getAsString(jsonObject.get("age")));
			response.setBmiResult(getBMIResult(jsonObject));
			response.setHabitsResponse(constructHabitsResponse(jsonObject));
			System.out.println(getAsJsonResponse(response));
			System.out.println(response.toString());
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	
	private static String getAsJsonResponse (Object responseObj){
		Gson gson = new GsonBuilder().create();
		String s1 = gson.toJson(responseObj);
		return s1;
	}
	
	/**
	 * @param object
	 * @return
	 */
	private static String getAsString(Object object) {
		return (null!=object) ? object.toString() : null;
	}

	/**
	 * @param object
	 * @return
	 */
	private static double getAsDouble(Object object) {
		String objAsString =  (null!=object) ? object.toString() : "";
		double value = Double.parseDouble(objAsString);
		return value;
	}

	/**
	 * @param jsonObject
	 * @return
	 */
	private static String getBMIResult(JSONObject jsonObject){
		Response resp = new BMICalculator(getAsDouble(jsonObject.get("weight")) , getAsDouble(jsonObject.get("height")));
		return resp.getResponse().toString();
	}

	/**
	 * @param jsonObject
	 * @return
	 */
	private static HabitsResponse constructHabitsResponse(JSONObject jsonObject) {
		HabitsResponse response = null;
		try {
			response = new HabitsResponse();
			response.setSmoking(constructSmokingResponse(jsonObject));
		}catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * @param jsonObject
	 * @return
	 */
	private static Smoking constructSmokingResponse(JSONObject jsonObject) {
		Smoking smokingResp = null;
		JSONObject habitsJSON = (JSONObject) jsonObject.get("habitsInput");
		ObjectMapper mapper  = new ObjectMapper();
		try {
			List<String> jsonRequestRequest = mapper.readValue(
					habitsJSON.get("Smoking").toString(),
					new TypeReference<List<String>>() {
					});
			InputQuestionsAndOptions quesAns = new InputQuestionsAndOptions();
			List<UserInput> userInput = new ArrayList<InputQuestionsAndOptions.UserInput>();
			quesAns.setAge(getAsInt(jsonObject.get("age")));
			System.out.println(jsonRequestRequest);
			for(int i=0; i<jsonRequestRequest.size(); ++i){
				String val = jsonRequestRequest.get(i);
				if(val.equals("{") || val.equals("}")){

				}else {
					InputQuestionsAndOptions.UserInput input = quesAns.new UserInput();
					input.setQuestion(val);
					input.setOption(jsonRequestRequest.get(++i));
					userInput.add(input);

				}
			}
			quesAns.setUserInput(userInput);
			System.out.println(quesAns.toString());
			Response resp = new SmokingResponseCalculator(quesAns);
			smokingResp = (Smoking)resp.getResponse();
		}catch(Exception e){
			e.printStackTrace();
		}

		return smokingResp;
	}

	/**
	 * @param object
	 * @return
	 */
	private static int getAsInt(Object object) {
		String objAsString =  (null!=object) ? object.toString() : "0";
		int value = Integer.parseInt(objAsString);
		return value;
	}

}
