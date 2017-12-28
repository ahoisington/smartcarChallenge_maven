package com.acaciah.smartcarChallenge;
//
//	Acacia Hoisington
//	12/23/17
//	Smartcar Coding Challenge
//	Data Class for data collection in Vehicle class
//
//
//
//



public class VehicleData {
    private Vin vin;
    private Color color;
    private FourDoorSedan fourDoorSedan;
    private TwoDoorCoupe twoDoorCoupe;
    private DriveTrain driveTrain;

    public String getVinVal() {
        return this.vin.value;
    }

    public String getColor() {
        return this.color.value;
    }

    public  Integer getDoorCount() {
        if (this.fourDoorSedan.value.equals("True")) {
            return 4;
        } else if (this.twoDoorCoupe.value.equals("True")) {
            return 2;
        }
        return 0;
    }

    public String getDriveTrain() {
        return this.driveTrain.value;
    }

    public String toString() {
        return "vin: " + this.vin.type + ", " + this.vin.value;
    }

    public class Vin {
        public String type;
        public String value;
    }

    public class Color {
        public String type;
        public String value;
    }

    public class FourDoorSedan {
        public String type;
        public String value;
    }

    public class TwoDoorCoupe {
        public String type;
        public String value;
    }

    public class DriveTrain {
        public String type;
        public String value;
    }
}