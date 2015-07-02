package com.ericsson.predictive.beans;

import java.util.List;

public class QuestionsAndOptions {
	
	
	String questions;
	List<String> options;
	String category;
	
	
	
	@Override
	public String toString() {
		return "QuestionsAndOptions [questions=" + questions + ", options="
				+ options + ", category=" + category + "]";
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public List getOptions() {
		return options;
	}
	public void setOptions(List options) {
		this.options = options;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	
	
	
}
