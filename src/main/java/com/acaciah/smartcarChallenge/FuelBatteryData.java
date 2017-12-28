package com.acaciah.smartcarChallenge;
//
//	Acacia Hoisington
//	12/23/17
//	Smartcar Coding Challenge
//	Data Class for data collection in FuelBattery class.
//
//
//
//



public class FuelBatteryData {
    private BatteryLevel batteryLevel;
    private TankLevel tankLevel;

    public String getBatteryLevel() {
        return this.batteryLevel.value;
    }

    public String getTankLevel() {
        return this.tankLevel.value;
    }


    public class TankLevel {
        public String type;
        public String value;
    }

    public class BatteryLevel {
        public String type;
        public String value;
    }
}