/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: SeverityCalculatorUtil.java
 * Created by: evijaka
 * Author: evijaka
 * Date : Jul 8, 2015
 */
/**
 * 
 */
package com.ericsson.predictive.data.analytics;

import java.util.List;


/**
 * @author evijaka
 *
 */
public class SeverityCalculatorUtil {

	/**
	 * @param index
	 * @return
	 */
	public static Severity calculateSeverity(int index){
		Severity sev = null;
		switch(index){
		case 0 :
			sev = Severity.A;
			break;
		case 1 :
			sev = Severity.B;
			break;
		case 2 :
			sev = Severity.C;
			break;
		case 3 :
			sev = Severity.D;
			break;
		default :
			sev = Severity.A;
			break;
		}
	
		return sev;
	}

	/**
	 * @param severity
	 * @return
	 */
	public static SeverityLevel getSeverity(List<Severity> severity){
		SeverityLevel level = null;
		int sum=0;
		level =  SeverityLevel.NO_RISK;
		if(severity!=null && !severity.isEmpty()) {
			for(Severity sev : severity){
				sum +=sev.getSeverityValue();
			}
		
			if(sum>1 && sum<3 ){
				level =  SeverityLevel.LOW;
			}else if(sum>3 && sum<6 ){
				level =  SeverityLevel.MEDIUM;
			}else if(sum>6){
				level =  SeverityLevel.HIGH;
			}
		}
		return level;
	}

}
