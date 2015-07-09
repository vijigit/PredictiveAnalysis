/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: SmokingResponse.java
 * Created by: evijaka
 * Author: evijaka
 * Date : Jul 8, 2015
 */
/**
 * 
 */
package com.ericsson.predictive.data.analytics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ericsson.predictive.data.analytics.InputQuestionsAndOptions.UserInput;
import com.ericsson.predictive.model.AgeWiseResponse;
import com.ericsson.predictive.model.Drinking;
import com.ericsson.predictive.model.EatingHabits;


/**
 * @author evijaka
 *
 */
public class EatingHabitsResponseCalculator implements Response{
	private static final String EATING_HABITS_QUESTIONS_REGEX="Eating Habit.Q\\d\\.(.*\\?):\\{(.*)\\}";
	private static final String RESPONSE_REGEX="(.*?)=(.*+)";
	private static List<QuestOptions> questionsOptionsList ;
	private InputQuestionsAndOptions inputQuestOptions;
	private static HashMap<String, String> responseMap;

	EatingHabitsResponseCalculator(InputQuestionsAndOptions inputQuestOptions){
		this.inputQuestOptions = inputQuestOptions;
	}

	/* (non-Javadoc)
	 * @see com.ericsson.predictive.data.analytics.Response#getResponse()
	 */
	@Override
	public Object getResponse() {
		List<Severity> sevList =null;
		if(inputQuestOptions!=null){
			sevList = new ArrayList<Severity>(5);
			for(UserInput inputQuestOption : inputQuestOptions.getUserInput() ){
				for(QuestOptions questOption : questionsOptionsList){
					if(inputQuestOption.getQuestion().equalsIgnoreCase(questOption.getQuestion().trim())){
						List<String> optionsList = questOption.getOptions();
						for(int i =0; i<optionsList.size(); i++){
							String option = optionsList.get(i).trim();

							if(inputQuestOption.getOption().equalsIgnoreCase(option)){
								sevList.add(SeverityCalculatorUtil.calculateSeverity(i));
							}
						}
					}
				}
			}
		}
		SeverityLevel level = SeverityCalculatorUtil.getSeverity(sevList);
		return formEatingHabitsResponse(level);
	}

	/**
	 * @param level
	 */
	private EatingHabits formEatingHabitsResponse(SeverityLevel level) {
		EatingHabits eatingHabits = new EatingHabits();
		eatingHabits.setRiskLevel(level.toString());
		List<AgeWiseResponse> ageResponse = SeverityCalculatorUtil.frameReponse(responseMap, inputQuestOptions.getAge(), level);	
		eatingHabits.setAgeWiseResponse(ageResponse);
		return eatingHabits;

	}

	/**
	 * @return the questionsOptionsList
	 */
	public static List<QuestOptions> getQuestionsOptionsList() {
		return questionsOptionsList;
	}


	/**
	 * @return the inputQuestOptions
	 */
	public InputQuestionsAndOptions getInputQuestOptions() {
		return inputQuestOptions;
	}

	/**
	 * @param inputQuestOptions the inputQuestOptions to set
	 */
	public void setInputQuestOptions(InputQuestionsAndOptions inputQuestOptions) {
		this.inputQuestOptions = inputQuestOptions;
	}

	/**
	 * @param questionsOptionsList the questionsOptionsList to set
	 */
	public static void setQuestionsOptionsList(
			List<QuestOptions> questionsOptionsList) {
		EatingHabitsResponseCalculator.questionsOptionsList = questionsOptionsList;
	}

	static {
		questionsOptionsList = new ArrayList<QuestOptions>();
		BufferedReader br  = null;
		Pattern r = Pattern.compile(EATING_HABITS_QUESTIONS_REGEX);
		String fileName = "Questions";
		try {
			br = new BufferedReader(new InputStreamReader(DataAnalytics.class.getClassLoader().getResourceAsStream(
					fileName)));
			for(String line; (line = br.readLine()) != null; ) {
				Matcher m = r.matcher(line);
				if (m.find()) {
					QuestOptions questOptions = new QuestOptions();
					List<String> optionsList = new LinkedList<String>();
					String[] options = (m.group(2).split(","));
					for(String option : options){
						optionsList.add(option.trim());
					}
					questOptions.setQuestion(m.group(1));
					questOptions.setOptions(optionsList);
					questionsOptionsList.add(questOptions);
				} 
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		//Load Response
		try {
			Pattern response = Pattern.compile(RESPONSE_REGEX);
			br = new BufferedReader(new InputStreamReader(DataAnalytics.class.getClassLoader().getResourceAsStream(
					"eatingResponse")));
			responseMap = new HashMap<String, String>();
			for(String line; (line = br.readLine()) != null; ) {
				Matcher m = response.matcher(line);
				if (m.find()) {
					responseMap.put(m.group(1), m.group(2));
				} 
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
