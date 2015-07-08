/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: Severity.java
 * Created by: evijaka
 * Author: evijaka
 * Date : Jul 8, 2015
 */
/**
 * 
 */
package com.ericsson.predictive.data.analytics;


/**
 * @author evijaka
 *
 */
public enum Severity {
	A(1), B(2), C(3), D(4);
	private final  int severityValue;
	
	private Severity(int severityValue ) {
		this.severityValue = severityValue;
	}

	/**
	 * @return the severityValue
	 */
	public int getSeverityValue() {
		return severityValue;
	}
	

}
