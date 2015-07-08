/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: Drinking.java
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

public class Drinking
{
	private String riskLevel;

	private AgeWiseResponse[] AgeWiseResponse;

	public String getRiskLevel ()
	{
		return riskLevel;
	}

	public void setRiskLevel (String riskLevel)
	{
		this.riskLevel = riskLevel;
	}

	public AgeWiseResponse[] getAgeWiseResponse ()
	{
		return AgeWiseResponse;
	}

	public void setAgeWiseResponse (AgeWiseResponse[] AgeWiseResponse)
	{
		this.AgeWiseResponse = AgeWiseResponse;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [riskLevel = "+riskLevel+", AgeWiseResponse = "+AgeWiseResponse+"]";
	}	
}