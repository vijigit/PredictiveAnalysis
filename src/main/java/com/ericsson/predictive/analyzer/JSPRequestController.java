package com.ericsson.predictive.analyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.predictive.beans.QuestionsAndOptions;
import com.ericsson.predictive.beans.Shop;
import com.ericsson.predictive.dao.PredictiveAnalysisDAO;
import com.ericsson.predictive.data.analytics.DataAnalytics;

@Controller
@RequestMapping("/get")
public class JSPRequestController {
 
	@RequestMapping(value="getAllQuestions", method = RequestMethod.GET)
	public @ResponseBody ArrayList<QuestionsAndOptions> getAllQuestions() {
 
	return	PredictiveAnalysisDAO.getAllQuestions();
 
	}
	
	
	@RequestMapping(value="processMyRequest", method = RequestMethod.POST)
	public @ResponseBody String processMyRequest(@RequestBody String json) {
		
		System.out.println(json);
 
		
		DataAnalytics da = new DataAnalytics();
		
		
		
	try {
		return	da.getmyDetails();
	} catch (IOException e) {
		return "failed";
	}
 
	}
	
	
	
	
	@RequestMapping(value="/{category}", method = RequestMethod.GET)
	public @ResponseBody ArrayList<QuestionsAndOptions> getAllQuestionsByCategory(@PathVariable String category) {
 
	return	PredictiveAnalysisDAO.getAllQuestionsbyCategory(category);
 
	}
	
	
	
	
	@RequestMapping(value="getCategoryNames", method = RequestMethod.GET)
	public @ResponseBody ArrayList<String> getCategoryNames() {
 
	return	PredictiveAnalysisDAO.getCategoryNames();
 
	}
 
}