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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ericsson.predictive.model.AgeWiseResponse;


/**
 * @author evijaka
 *
 */
public class SeverityCalculatorUtil {
	
	private static final String RESPONSE_TEXT="At the age of {0} you will suffer from {1}";

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
	/**
	 * @param severity
	 * @return
	 */
	public static List<AgeWiseResponse>  frameReponse(HashMap<String,String> responseMap, int age){
		List<AgeWiseResponse> ageWiseResponse = new ArrayList<AgeWiseResponse>();
		AgeWiseResponse response1 = new AgeWiseResponse();
		AgeWiseResponse response2 = new AgeWiseResponse();
		AgeWiseResponse response3 = new AgeWiseResponse();
		AgeWiseResponse response4 = new AgeWiseResponse();
	
		if(age>15 && age<20){
			response1.setInterval("At the age of 20-25 You will suffer from " + responseMap.get("20-25"));
			response2.setInterval("At the age of 25-35 You will suffer from " + responseMap.get("25-35"));
			response3.setInterval("At the age of 35-45 You will suffer from " + responseMap.get("35-45"));
			response4.setInterval("At the age of 45-50 You will suffer from " + responseMap.get("45-50"));
			ageWiseResponse.add(response1);
			ageWiseResponse.add(response2);
			ageWiseResponse.add(response3);
			ageWiseResponse.add(response4);
			
		}else if(age>20 && age<25){
			response1.setInterval("At the age of 25-35 You will suffer from " + responseMap.get("25-35"));
			response2.setInterval("At the age of 35-45 You will suffer from " + responseMap.get("35-45"));
			response3.setInterval("At the age of 45-50 You will suffer from " + responseMap.get("45-50"));
			response4.setInterval("At the age of 50-55 You will suffer from " + responseMap.get("50-55"));
			ageWiseResponse.add(response1);
			ageWiseResponse.add(response2);
			ageWiseResponse.add(response3);
			ageWiseResponse.add(response4);
			
		}else if(age>25 && age<35){
			response1.setInterval("At the age of 35-45 You will suffer from " + responseMap.get("35-45"));
			response2.setInterval("At the age of 45-50 You will suffer from " + responseMap.get("45-50"));
			response3.setInterval("At the age of 50-55 You will suffer from " + responseMap.get("50-55"));
			response4.setInterval("Above 55 You will suffer from " + responseMap.get("Above 55"));
			ageWiseResponse.add(response1);
			ageWiseResponse.add(response2);
			ageWiseResponse.add(response3);
			ageWiseResponse.add(response4);
		}else if(age>35 && age<45){
			response1.setInterval("At the age of 35-45 You will suffer from " + responseMap.get("35-45"));
			response2.setInterval("At the age of 45-50 You will suffer from " + responseMap.get("45-50"));
			response3.setInterval("At the age of 50-55 You will suffer from " + responseMap.get("50-55"));
			response4.setInterval("Above 55 You will suffer from " + responseMap.get("Above 55"));
			ageWiseResponse.add(response1);
			ageWiseResponse.add(response2);
			ageWiseResponse.add(response3);
			ageWiseResponse.add(response4);
		}else if(age>45 && age<50){
			response1.setInterval("At the age of 45-50 You will suffer from " + responseMap.get("45-50"));
			response2.setInterval("At the age of 50-55 You will suffer from " + responseMap.get("50-55"));
			response3.setInterval("Above 55 You will suffer from " + responseMap.get("Above 55"));
			ageWiseResponse.add(response1);
			ageWiseResponse.add(response2);
			ageWiseResponse.add(response3);
		}else if(age>50 && age<55){
			
			response1.setInterval("At the age of 50-55 You will suffer from " + responseMap.get("50-55"));
			response2.setInterval("Above 55 You will suffer from " + responseMap.get("Above 55"));
			ageWiseResponse.add(response1);
			ageWiseResponse.add(response2);
		}else{
			response1.setInterval("Above 55 You will suffer from " + responseMap.get("Above 55"));
			ageWiseResponse.add(response1);
		}
		return ageWiseResponse;
	}

}
