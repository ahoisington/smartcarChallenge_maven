package com.acaciah.smartcarChallenge;
//
//	Acacia Hoisington	
//	12/23/17
//	Smartcar Coding Challenge
//	Engine Class for GM actionEnergyService. used for converting json to gson. reflects GM specs.
//  
//	
//



public class Engine  {
	private String service;
	private String status;
	public ActionResult actionResult;

	public String toString(){
		return "{ service: "+ this.service + ", status: "+this.status+", actionResult: "+actionResult.toString()+"\n";
	}

    public String getResultStatus (){
        return actionResult.getResultStatus();
    }

    public class ActionResult {
        private String status;

        public String getResultStatus (){
            if (this.status.equals("EXECUTED")){
                return "success";
            }
            return "error";
        }

        public String toString(){
            return this.status;
        }
    }
}

