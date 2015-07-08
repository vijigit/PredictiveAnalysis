/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: HealthResponse.java
 * Created by: evijaka
 * Author: evijaka
 * Date : Jul 7, 2015
 */
/**
 * 
 */
package com.ericsson.predictive.model;


/**
 * @author evijaka
 *
 */
public class HealthResponse
{
	private HabitsResponse habitsResponse;

	private String age;

	private String name;

	private String bmiResult;

	public HabitsResponse getHabitsResponse ()
	{
		return habitsResponse;
	}

	public void setHabitsResponse (HabitsResponse habitsResponse)
	{
		this.habitsResponse = habitsResponse;
	}

	public String getAge ()
	{
		return age;
	}

	public void setAge (String age)
	{
		this.age = age;
	}

	public String getName ()
	{
		return name;
	}

	public void setName (String name)
	{
		this.name = name;
	}

	public String getBmiResult ()
	{
		return bmiResult;
	}

	public void setBmiResult (String bmiResult)
	{
		this.bmiResult = bmiResult;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [habitsResponse = "+habitsResponse+", age = "+age+", name = "+name+", bmiResult = "+bmiResult+"]";
	}	
}
