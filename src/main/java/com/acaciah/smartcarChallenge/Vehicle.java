package com.acaciah.smartcarChallenge;
//
//	Acacia Hoisington	
//	12/23/17
//	Smartcar Coding Challenge
//	Vehicle Class for GM vehicle Info. used for converting json to gson. reflects GM specs.
//	
//  
//	
//

import java.util.List;



public class Vehicle  {
	private String service;
	private String status;
	public VehicleData data;

	public String getService() {
		return this.service;
	}

	public String getStatus() {
		return this.status;
	}

	public String getVinVal() {
		return this.data.getVinVal();
	}

	public String getColor() {
		return this.data.getColor();
	}

	public  Integer getDoorCount() {
		return this.data.getDoorCount();
	}

	public String getDriveTrain() {
		return this.data.getDriveTrain();
	}

	public String toString(){
		return "{ service: "+ this.service + ", status: "+this.status+", data: "+ data.toString()+"}\n";
	}
}






 
