package com.ericsson.predictive.beans;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Questions implements Serializable {

	private static final long serialVersionUID = -5527566248002296042L;

	public Questions() {

	}
	
	@Id
	@Column(name = "question_id")
	private Integer question_id;

	@Column(name = "parameter_id")
	private Integer parameter_id;

	@Column(name = "question")
	private String question;

	@Column(name = "category")
	private String category;
	

	@Override
	public String toString() {
		return "Questions [question_id=" + question_id + ", parameter_id="
				+ parameter_id + ", question=" + question + ", category="
				+ category + "]";
	}

	public Integer getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}

	public Integer getParameter_id() {
		return parameter_id;
	}

	public void setParameter_id(Integer parameter_id) {
		this.parameter_id = parameter_id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

}
