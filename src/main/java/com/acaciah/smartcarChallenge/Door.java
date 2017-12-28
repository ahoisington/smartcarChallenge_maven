package com.acaciah.smartcarChallenge;
//
//	Acacia Hoisington	
//	12/23/17
//	Smartcar Coding Challenge
//	Door Class for getDoors in SecurityData Class
//	
//  
//	
//

public class Door  {
	private String location;
	private String locked;

	public Door( String location, String locked ){
		this.location= location;
		this.locked =locked;
	}

	public String getLocation(){
		return this.location;
	}

	public String getLocked(){
		return this.locked;
	}
//
//	public void setLocation(String location){
//		this.location= location;
//	}
//
//	public void setLocked(String locked){
//		this.locked =locked;
//	}
}

