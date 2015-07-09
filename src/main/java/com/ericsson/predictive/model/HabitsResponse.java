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

import com.google.gson.annotations.SerializedName;



/**
 * @author evijaka
 *
 */
public class HabitsResponse
{
	private Drinking Drinking;

	private Smoking Smoking;

	@SerializedName("Eating Habit")
	private EatingHabits eatingHabits;
	
	@SerializedName("Physical Activity")
	private PhysicalActivity physicalActivity;
	
	
	/**
	 * @return the eatingHabits
	 */
	public EatingHabits getEatingHabits() {
		return eatingHabits;
	}

	/**
	 * @param eatingHabits the eatingHabits to set
	 */
	public void setEatingHabits(EatingHabits eatingHabits) {
		this.eatingHabits = eatingHabits;
	}

	/**
	 * @return the physicalActivity
	 */
	public PhysicalActivity getPhysicalActivity() {
		return physicalActivity;
	}

	/**
	 * @param physicalActivity the physicalActivity to set
	 */
	public void setPhysicalActivity(PhysicalActivity physicalActivity) {
		this.physicalActivity = physicalActivity;
	}

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
