/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: InputQuestionsAndOptions.java
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
public class InputQuestionsAndOptions {
	
	
	private int age;
	private List<UserInput> userInput;
	
	/**
	 * @return the userInput
	 */
	public List<UserInput> getUserInput() {
		return userInput;
	}
	/**
	 * @param userInput the userInput to set
	 */
	public void setUserInput(List<UserInput> userInput) {
		this.userInput = userInput;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	public class UserInput{
		private String question;
		private String option;
		
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
		 * @return the option
		 */
		public String getOption() {
			return option;
		}
		/**
		 * @param option the option to set
		 */
		public void setOption(String option) {
			this.option = option;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "UserInput [question=" + question + ", option=" + option
					+ "]";
		}
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InputQuestionsAndOptions [age=" + age + ", userInput="
				+ userInput + "]";
	}
	
	
	

}
