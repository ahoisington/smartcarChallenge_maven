package com.acaciah.smartcarChallenge;
//
//	Acacia Hoisington
//	12/23/17
//	Smartcar Coding Challenge
//	Security Class for GM getSecurityStatusService. used for converting json to gson. reflects GM specs.
//
//
import java.util.ArrayList;



public class Security  {
    private String service;
    private String status;
    public SecurityData data;

    public String getService() {
        return this.service;
    }

    public String getStatus() {
        return this.status;
    }

    public SecurityData getData(){ return this.data;}

    public ArrayList<Door> getDoors() { return this.data.getDoors();}

    public String toString(){
        return "{ service: "+ this.service + ", status: "+this.status+", data: "+ data.toString()+"}\n";
    }
}







