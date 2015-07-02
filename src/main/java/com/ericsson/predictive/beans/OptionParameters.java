package com.ericsson.predictive.beans;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name ="option_parameters")
public class OptionParameters implements Serializable {

	
	private static final long serialVersionUID = -5527566248002296042L;
	
	
public OptionParameters() {
		
	}


	@Id
	@Column(name = "parameter_id")
	private Integer parameter_id;
	
	@Column(name = "parameter_name")
	private String parameter_name;


	public Integer getParameter_id() {
		return parameter_id;
	}

	public void setParameter_id(Integer parameter_id) {
		this.parameter_id = parameter_id;
	}

	public String getParameter_name() {
		return parameter_name;
	}

	public void setParameter_name(String parameter_name) {
		this.parameter_name = parameter_name;
	}

	@Override
	public String toString() {
		return "OptionParameters [parameter_id=" + parameter_id
				+ ", parameter_name=" + parameter_name + "]";
	}
	
	
}
