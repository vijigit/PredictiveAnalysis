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

import java.util.List;


/**
 * @author evijaka
 *
 */

public class Smoking
{
	private String riskLevel;

    private List<AgeWiseResponse> AgeWiseResponse;

    public String getRiskLevel ()
    {
        return riskLevel;
    }

    public void setRiskLevel (String riskLevel)
    {
        this.riskLevel = riskLevel;
    }

    

    /**
	 * @return the ageWiseResponse
	 */
	public List<AgeWiseResponse> getAgeWiseResponse() {
		return AgeWiseResponse;
	}

	/**
	 * @param ageWiseResponse the ageWiseResponse to set
	 */
	public void setAgeWiseResponse(List<AgeWiseResponse> ageWiseResponse) {
		AgeWiseResponse = ageWiseResponse;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [riskLevel = "+riskLevel+", AgeWiseResponse = "+AgeWiseResponse+"]";
    }
	
}

