/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: QuestOptions.java
 * Created by: evijaka
 * Author: evijaka
 * Date : Jul 8, 2015
 */
/**
 * 
 */
package com.ericsson.predictive.data.analytics;

import java.util.List;


/**
 * @author evijaka
 *
 */
public class QuestOptions {
	private String question ;
	private List<String> options;
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @return the options
	 */
	public List<String> getOptions() {
		return options;
	}
	/**
	 * @param options the options to set
	 */
	public void setOptions(List<String> options) {
		this.options = options;
	}
	
	
	


}
