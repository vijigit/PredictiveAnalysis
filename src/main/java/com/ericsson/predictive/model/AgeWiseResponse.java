/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: AgeWiseResponse.java
 * Created by: evijaka
 * Author: evijaka
 * Date : Jul 8, 2015
 */
/**
 * 
 */
package com.ericsson.predictive.model;


/**
 * @author evijaka
 *
 */
public class AgeWiseResponse {
	private String interval;

    public String getInterval ()
    {
        return interval;
    }

    public void setInterval (String interval)
    {
        this.interval = interval;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [interval1 = "+interval+"]";
    }
}
