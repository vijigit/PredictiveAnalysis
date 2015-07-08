/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: Smoking.java
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

public class PhysicalActivity
{
	private String interval1;

	public String getInterval1 ()
	{
		return interval1;
	}

	public void setInterval1 (String interval1)
	{
		this.interval1 = interval1;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [interval1 = "+interval1+"]";
	}
}

