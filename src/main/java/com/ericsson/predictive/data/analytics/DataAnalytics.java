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
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ericsson.predictive.data.analytics.InputQuestionsAndOptions.UserInput;
import com.ericsson.predictive.model.Drinking;
import com.ericsson.predictive.model.EatingHabits;
import com.ericsson.predictive.model.HabitsResponse;
import com.ericsson.predictive.model.HealthResponse;
import com.ericsson.predictive.model.PhysicalActivity;
import com.ericsson.predictive.model.Smoking;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @author evijaka
 *
 */
public class DataAnalytics {

	public  String getmyDetails(String json) throws IOException {

		HealthResponse response = new HealthResponse();
		try{

			JSONObject jsonObject = (JSONObject)new JSONParser().parse(json);
			response.setName(getAsString(jsonObject.get("name")));
			response.setAge(getAsString(jsonObject.get("age")));
			response.setBmiResult(getBMIResult(jsonObject));
			response.setHabitsResponse(constructHabitsResponse(jsonObject));
			//sendEmailNotification("selvakumar.k@ericsson.com","Selva");

		}catch(Exception e){
			e.printStackTrace();
		}
		return getAsJsonResponse(response);

	}

	public static void main(String[] args) throws AddressException, MessagingException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object json = parser.parse(new BufferedReader(new InputStreamReader(DataAnalytics.class.getClassLoader().getResourceAsStream(
				"input.json"))));
		DataAnalytics da = new DataAnalytics();
		System.out.println(da.getmyDetails(json.toString()));
	

	}


	private static void sendEmailNotification(String emailId,String name) throws AddressException, MessagingException {
		//Get the session object  
		Properties props = new Properties();  
		props.put("mail.smtp.host", "smtp.internal.ericsson.com");
		props.put("mail.smtp.port", "25");            
		Session session = Session.getDefaultInstance(props);  
		//compose message  
		MimeMessage message = new MimeMessage(session);  
		message.setFrom(new InternetAddress("noreply@ericsson.com"));//change accordingly  
		message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailId)); 
		message.setSubject("Thanks for Using Health Predictive Analysis & Vote for us");  
		message.setText("Hi  "+name+"\n"+ "\n"+ "Thanks for using our tool. Vote for us if you like this tool"+"\n"+ "\n");  
		//send message  
		Transport.send(message);           
		System.out.println("Mail sent Successfully"); 

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
			response.setDrinking(constructDrinkingResponse(jsonObject));
			response.setEatingHabits(constructEatingHabitsResponse(jsonObject));
			response.setPhysicalActivity(constructPhysicalActivityResponse(jsonObject));

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
		Object jsonVal = habitsJSON.get("Smoking");
		if(jsonVal!=null) {
			ObjectMapper mapper  = new ObjectMapper();
			try {
				List<String> jsonRequestRequest = mapper.readValue(
						jsonVal.toString(),
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
		}

		return smokingResp;
	}

	/**
	 * @param jsonObject
	 * @return
	 */
	private static Drinking constructDrinkingResponse(JSONObject jsonObject) {
		Drinking drinkingResponse = null;
		JSONObject habitsJSON = (JSONObject) jsonObject.get("habitsInput");
		Object jsonVal = habitsJSON.get("Drinking");
		if(jsonVal!=null) {
			ObjectMapper mapper  = new ObjectMapper();
			try {
				List<String> jsonRequestRequest = mapper.readValue(
						jsonVal.toString(),
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
				Response resp = new DrinkingResponseCalculator(quesAns);
				drinkingResponse = (Drinking)resp.getResponse();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return drinkingResponse;
	}

	/**
	 * @param jsonObject
	 * @return
	 */
	private static EatingHabits constructEatingHabitsResponse(JSONObject jsonObject) {
		EatingHabits eatingResponse = null;
		JSONObject habitsJSON = (JSONObject) jsonObject.get("habitsInput");
		Object jsonVal = habitsJSON.get("Eating Habit");
		if(jsonVal!=null) {
			ObjectMapper mapper  = new ObjectMapper();
			try {
				List<String> jsonRequestRequest = mapper.readValue(
						jsonVal.toString(),
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
				Response resp = new EatingHabitsResponseCalculator(quesAns);
				eatingResponse = (EatingHabits)resp.getResponse();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		return eatingResponse;
	}

	/**
	 * @param jsonObject
	 * @return
	 */
	private static PhysicalActivity constructPhysicalActivityResponse(JSONObject jsonObject) {
		PhysicalActivity physicalActivityResp = null;
		JSONObject habitsJSON = (JSONObject) jsonObject.get("habitsInput");
		Object jsonVal = habitsJSON.get("Physical Activity");
		if(jsonVal!=null) {
			ObjectMapper mapper  = new ObjectMapper();
			try {
				List<String> jsonRequestRequest = mapper.readValue(
						jsonVal.toString(),
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
				Response resp = new PhysicalActivityResponseCalculator(quesAns);
				physicalActivityResp = (PhysicalActivity)resp.getResponse();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return physicalActivityResp;
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
