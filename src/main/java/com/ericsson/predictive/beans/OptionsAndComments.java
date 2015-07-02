package com.ericsson.predictive.beans;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name ="options_comments")
public class OptionsAndComments implements Serializable {

	
	private static final long serialVersionUID = -5527566248002296042L;
	
	
public OptionsAndComments() {
		
	}






	@Id
	@Column(name = "options_comments_id")
	private Integer options_comments_id;
	
	



	@Column(name = "question_id")
	private Integer question_id;
	
	@Column(name = "options")
	private String options;
	
	@Column(name = "priority")
	private Integer priority;
	
	@Column(name = "comments")
	private String comments;
	
	
	public Integer getOptions_comments_id() {
		return options_comments_id;
	}

	public void setOptions_comments_id(Integer options_comments_id) {
		this.options_comments_id = options_comments_id;
	}

	public Integer getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "OptionsAndComments [options_comments_id=" + options_comments_id
				+ ", question_id=" + question_id + ", options=" + options
				+ ", priority=" + priority + ", comments=" + comments + "]";
	}



	
}
