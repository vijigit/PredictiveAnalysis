/**
 * Copyright Ericsson. This program is not to be copied or distributed
 * without the express written consent of Ericsson. No part of this program
 * may be used for purposes other than those intended by Ericsson.
 * 
 * Source: BMICalculator.java
 * Created by: evijaka
 * Author: evijaka
 * Date : Jul 7, 2015
 */
/**
 * 
 */
package com.ericsson.predictive.data.analytics;

import java.util.Scanner;


/**
 * @author evijaka
 *
 */
public class BMICalculator implements Response{
	

	public BMICalculator(double weight, double height) {
		this.weight = weight;
		this.height = height;
	}
	
	/* (non-Javadoc)
	 * @see com.ericsson.predictive.data.analytics.Response#getResponse()
	 */
	@Override
	public Object getResponse() {
		return calculateBMI();
	}
	private final double KILOGRAMS_PER_POUND = 0.453; 
	private final double METERS_PER_INCH = 0.026;
	
	private double weight;
	private double height;
	
	/**
	 * @param weight
	 * @param height
	 * @return
	 */
	private String calculateBMI(){
		StringBuffer buffer = new StringBuffer();
		double weightInKilogram = weight * KILOGRAMS_PER_POUND; 
		double heightInMeters = height * METERS_PER_INCH; 
		double bmi = weightInKilogram / (heightInMeters * heightInMeters);
		int bmiAsInt = (int) Math.round(bmi);
		
		buffer.append("Your Body Mass Index is " + bmiAsInt); 
		if (bmiAsInt < 16) {
			buffer.append(". You are seriously underweight"); 
		}else if (bmiAsInt < 18){
			buffer.append(". You are underweight");
		}else if (bmiAsInt < 24) {
			buffer.append(". You are normal weight"); 
		}else if (bmiAsInt < 29){
			buffer.append(". You are overweight"); 
		}else if (bmiAsInt < 35) {
			buffer.append(". You are seriously overweight"); 
		}else {
			buffer.append(". You are gravely overweight"); 
		}
		return buffer.toString();
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}


	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter weight in pounds: "); 
		double weight = input.nextDouble(); 
		System.out.print("Enter height in inches: "); 
		double height = input.nextDouble(); 
		input.close();
		BMICalculator calc = new BMICalculator(weight, height);
		System.out.println(calc.calculateBMI());
	}

	

}
