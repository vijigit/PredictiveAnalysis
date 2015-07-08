/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: HabitsResponse.java
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
public class HabitsResponse
{
	private Drinking Drinking;

	private Smoking Smoking;

	public Drinking getDrinking ()
	{
		return Drinking;
	}

	public void setDrinking (Drinking Drinking)
	{
		this.Drinking = Drinking;
	}

	public Smoking getSmoking ()
	{
		return Smoking;
	}

	public void setSmoking (Smoking Smoking)
	{
		this.Smoking = Smoking;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [Drinking = "+Drinking+", Smoking = "+Smoking+"]";
	}	
}
