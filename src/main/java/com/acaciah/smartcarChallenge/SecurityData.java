package com.acaciah.smartcarChallenge;

//
//	Acacia Hoisington
//	12/23/17
//	Smartcar Coding Challenge
//	Security Data Class for data collection in Security class
//
//
//

import java.util.ArrayList;
import java.util.List;

public class SecurityData {
    private Doors doors;

    public ArrayList<Door> getDoors(){
        ArrayList<Door> doorList= new ArrayList<Door>();

        if (doors!= null){
            for (int i=0; i < doors.values.size(); i++){
                Door j = new Door(doors.getLocationForValue(i), doors.getLockedForValue(i));
                doorList.add(j);
            }
            return doorList;
        }
        return null;
    }

    public String toString(){
        if (this.getDoors().size() == 2){
            return "this is location "+ this.getDoors().get(0).getLocation() + " this is locked status "+ this.getDoors().get(0).getLocked()
                    + "this is location "+ this.getDoors().get(1).getLocation() + " this is locked status "+ this.getDoors().get(1).getLocked();
        } else if (this.getDoors().size() == 3){
            return "this is location "+ this.getDoors().get(0).getLocation() + " this is locked status "+ this.getDoors().get(0).getLocked()
                    + "this is location "+ this.getDoors().get(1).getLocation() + " this is locked status "+ this.getDoors().get(1).getLocked()
                    + "this is location "+ this.getDoors().get(2).getLocation() + " this is locked status "+ this.getDoors().get(2).getLocked();
        }else if (this.getDoors().size() == 4){
            return "this is location "+ this.getDoors().get(0).getLocation() + " this is locked status "+ this.getDoors().get(0).getLocked()
                    + "this is location "+ this.getDoors().get(1).getLocation() + " this is locked status "+ this.getDoors().get(1).getLocked()
                    + "this is location "+ this.getDoors().get(2).getLocation() + " this is locked status "+ this.getDoors().get(2).getLocked()
                    + "this is location "+ this.getDoors().get(3).getLocation() + " this is locked status "+ this.getDoors().get(3).getLocked();
        }


        return null;
    }

    //location, locked
    //location, locked
    // in an array. array of two objects

    public class Doors {
        private String type;
        public List<Value> values;

        public String getLocationForValue(int i){
            if (i< this.values.size()) {
                return this.values.get(i).getLocation(i).getValue();
            }
            return null;
        }

        public String getLockedForValue(int i){
            if (i< this.values.size()) {
                return this.values.get(i).getLocked(i).getValue();
            }
            return null;
        }
    }

    public class Value {
        private Location location;
        private Locked locked;
        public Location getLocation(int i){
            return this.location;
        }
        public Locked getLocked(int i){
            return this.locked;
        }
    }

    public class Location {
        private String type;
        private String value;

        public String getValue(){
            return this.value;
        }
    }

    public class Locked {
        private String type;
        public String value;

        private String getValue(){
            return this.value;
        }
    }


}


