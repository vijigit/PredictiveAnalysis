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
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ericsson.predictive.data.analytics.InputQuestionsAndOptions.UserInput;
import com.ericsson.predictive.model.AgeWiseResponse;
import com.ericsson.predictive.model.Smoking;


/**
 * @author evijaka
 *
 */
public class SmokingResponseCalculator implements Response{
	private static final String SMOKING_QUESTIONS_REGEX="Smoking.Q\\d\\.(.*\\?):\\{(.*)\\}";
	private static List<QuestOptions> questionsOptionsList ;
	private InputQuestionsAndOptions inputQuestOptions;

	SmokingResponseCalculator(InputQuestionsAndOptions inputQuestOptions){
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
		return formSmokingResponse(level);
	}

	/**
	 * @param level
	 */
	private Smoking formSmokingResponse(SeverityLevel level) {
		Smoking smoking = new Smoking();
		smoking.setRiskLevel(level.toString());
		List<AgeWiseResponse> ageWiseResponse = new ArrayList<AgeWiseResponse>();
		smoking.setAgeWiseResponse(ageWiseResponse);
		return smoking;
		
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
		SmokingResponseCalculator.questionsOptionsList = questionsOptionsList;
	}

	static {
		questionsOptionsList = new ArrayList<QuestOptions>();
		BufferedReader br  = null;
		Pattern r = Pattern.compile(SMOKING_QUESTIONS_REGEX);
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
				} else {
					System.out.println("NO MATCH" + line);
				}
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
