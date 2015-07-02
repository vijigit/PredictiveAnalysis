package com.ericsson.predictive.dao;


import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.http.HttpHeaders;

import com.ericsson.predictive.beans.OptionParameters;
import com.ericsson.predictive.beans.OptionsAndComments;
import com.ericsson.predictive.beans.Questions;
import com.ericsson.predictive.beans.QuestionsAndOptions;



public class PredictiveAnalysisDAO {

	private static SessionFactory factory;

	static {

		factory = new AnnotationConfiguration()
				.configure()
				
				// addPackage("com.xyz") //add package if used.
				// addAnnotatedClass(Employee.class).
				.addAnnotatedClass(Questions.class)
				
				.addAnnotatedClass(OptionParameters.class)
				
				.addAnnotatedClass(OptionsAndComments.class)
				
				
				.buildSessionFactory();

	}

	
	
	public static void main(String[] args) {
		//getAllQuestions();

	getOptions(1);
	
	}

	public static ArrayList<QuestionsAndOptions> getAllQuestions() {

		ArrayList<Questions> questionsList = null;
		
		ArrayList<QuestionsAndOptions> questionOptionsList= new ArrayList<QuestionsAndOptions>();

		Session session = factory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			questionsList = (ArrayList<Questions>) session.createQuery(
					"FROM Questions").list();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		for (Questions question : questionsList) {
			
			//System.out.println(question.toString());
			QuestionsAndOptions qa = new QuestionsAndOptions();
			
			//System.out.println(question);
			
			qa.setQuestions(question.getQuestion());
			
		//	System.out.println(question.getQuestion_id());
			
			qa.setOptions(getOptions(question.getQuestion_id()));
			
			qa.setCategory(question.getCategory());
			//System.out.println(qa.toString());
			
			questionOptionsList.add(qa);
			
			
		}
		return questionOptionsList;

		//System.out.println(serviceList.toString());
		//return serviceList;
		
		

	}
	
	
	
	
	public static List getOptions(Integer questionID) {

		ArrayList<OptionsAndComments> options = new ArrayList<OptionsAndComments>();
		
		List optionsList = new ArrayList<String>();

		Session session = factory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			/*options = (ArrayList<OptionsAndComments>) session.createQuery(
					"FROM OptionsAndComments").list();*/
			
			Query query = session
					.createQuery("from OptionsAndComments where question_id= :question_id");
			query.setInteger("question_id", questionID);
			
			options = (ArrayList<OptionsAndComments>) query.list();
			
			
			for (OptionsAndComments option : options) {
				
				optionsList.add(option.getOptions());
				
			}
			
			
			

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
	//	System.out.println(optionsList);
		
		return optionsList;
		
		
		
		


	
	
	}

	public static ArrayList<String> getCategoryNames() {
		
		
		
ArrayList<OptionParameters> optionParamter = null;
		
		ArrayList<String> categoryList= new ArrayList<String>();

		Session session = factory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			optionParamter = (ArrayList<OptionParameters>) session.createQuery(
					"FROM OptionParameters").list();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		for (OptionParameters op : optionParamter) {
			
			//System.out.println(question.toString());
			
			categoryList.add(op.getParameter_name());
			
		}
		return categoryList;
		
		
		// TODO Auto-generated method stub
		
	}

	public static ArrayList<QuestionsAndOptions> getAllQuestionsbyCategory(
			String category) {
		// TODO Auto-generated method stub
ArrayList<Questions> questionsList = null;
		
		ArrayList<QuestionsAndOptions> questionOptionsList= new ArrayList<QuestionsAndOptions>();

		Session session = factory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			
			
			
			
			Query query = session
					.createQuery("from Questions where category= :category_name");
			query.setString("category_name", category);
			
			questionsList = (ArrayList<Questions>) query.list();
			
			
			
			

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		for (Questions question : questionsList) {
			
			//System.out.println(question.toString());
			QuestionsAndOptions qa = new QuestionsAndOptions();
			
			//System.out.println(question);
			
			qa.setQuestions(question.getQuestion());
			
		//	System.out.println(question.getQuestion_id());
			
			qa.setOptions(getOptions(question.getQuestion_id()));
			
			qa.setCategory(question.getCategory());
			//System.out.println(qa.toString());
			
			questionOptionsList.add(qa);
			
			
		}
		return questionOptionsList;

		//System.out.println(serviceList.toString());
		//return serviceList;
		
		
	}
	


}
