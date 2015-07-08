/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: LoadQuestionsAndOptions.java
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


/**
 * @author evijaka
 *
 */
public class LoadQuestionsAndOptions {

	private static final String SMOKING_QUESTIONS_REGEX="Smoking.Q\\d\\.(.*\\?):\\{(.*)\\}";
	private static List<QuestOptions> questionsOptionsList ;

	/**
	 * @return the questionsOptionsList
	 */
	public static List<QuestOptions> getQuestionsOptionsList() {
		return questionsOptionsList;
	}

	/**
	 * @param questionsOptionsList the questionsOptionsList to set
	 */
	public static void setQuestionsOptionsList(
			List<QuestOptions> questionsOptionsList) {
		LoadQuestionsAndOptions.questionsOptionsList = questionsOptionsList;
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
						optionsList.add(option);
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
