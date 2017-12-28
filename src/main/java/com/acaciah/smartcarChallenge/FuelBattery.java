package com.acaciah.smartcarChallenge;
//
//	Acacia Hoisington	
//	12/23/17
//	Smartcar Coding Challenge
//	Fuel Battery Class for GM getEnergyService. used for converting json to gson. reflects GM specs.
//	
//  
//	




public class FuelBattery {
	private String service;
	private String status;
	public FuelBatteryData data;

	public String getService() {
		return this.service;
	}

	public String getStatus() {
		return this.status;
	}

	public String getBatteryLevel() {
		return this.data.getBatteryLevel();
	}

	public String getTankLevel() {
		return this.data.getTankLevel();
	}

	public String toString(){
		return "{ service: "+ this.service + ", status: "+this.status+", data: "+ data.toString()+"}\n";
	}
}






 
