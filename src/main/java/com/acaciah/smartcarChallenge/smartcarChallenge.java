package com.acaciah.smartcarChallenge;

//
//	Acacia Hoisington
//	12/19/17
//	Smartcar Coding Challenge
//
//
//



import java.io.*;
import java.net.*;
import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;

import static java.lang.System.exit;

public class smartcarChallenge {

    private static final String GM_URL = "http://gmapi.azurewebsites.net/";
    private static String id;

    public static void main( String args[] ) throws Exception {

        if (args.length==0){
            System.err.println("ERROR: Please input arguemets of GET or POST request. \n"+ help());
            exit(1);
        }

        if (args[0].equals("--help")){
            System.out.println(help());
        } else if (args.length<=1){
            System.out.println("Please input GET or POST request. \n"+ help());
            exit(1);
        }

        String request = args[0]; //    GET or POST
        String httpCall = args[1]; //   /vehicles/:id
        String[] httpReq = httpCall.split("/");
//		System.out.println("args are 0 "+args[0] + " 1 "+args[1]+"length of input: "+Integer.toString(args.length) + " httpReq[0] "+ httpReq[0]+  //DEBUG
//                " httpReq[1] "+ httpReq[1]+ " httpReq[2] "+ httpReq[2]+" httpReq[3] "+ httpReq[3]);   //DEBUG

        // Http request check
        if (httpReq.length<3  || !httpReq[1].equals("vehicles")){
            System.err.println("ERROR: Please input GET or POST request with the correct syntax. \n" + help());
            exit(1);
        }

        //id check
        if (!StringUtils.isNumeric(httpReq[2])){
            System.err.println("ERROR: Vehicle id passed in was "+httpReq[2]+". Please input numeric id of the vehicle in the form of, for example: GET /vehicle/1234 ");
            exit(1);
        }
        id = httpReq[2];

        if (request.equals("GET")){
            if (httpReq.length<=3){
                String vehicleSmJson = vehicle(id);
                System.out.println("Thanks! Here is the data: \n" + vehicleSmJson);
            } else if (httpReq[3].equals("doors")){
                String securitySmJson = security(id);
                System.out.println("Thanks! Here is the data: \n" + securitySmJson);
            } else if (httpReq[3].equals("fuel")){
                String fuelSmJson = fuel(id);
                System.out.println("Thanks! Here is the data: \n" + fuelSmJson);
            } else if (httpReq[3].equals("battery")){
                String batterySmJson = battery(id);
                System.out.println("Thanks! Here is the data: \n" + batterySmJson);
            }
        } else if (request.equals("POST") && httpReq[3].equals("engine")&& (httpReq[4].equals("START") || httpReq[4].equals("STOP"))){
            String command = httpReq[4];
            String engineSmJson = engine(id,command);
            System.out.println("Thanks! Here is the data: \n" + engineSmJson);
        }
    }

    /**
     * help() function that tells user various input possibilities for running this program.
     *
     * @return      inputs for running this program
     */
    private static String help(){
        return "For Vehicle info, pass in: GET /vehicles/:id \n" +
                "For Security info, pass in: GET /vehicles/:id/doors \n" +
                "For Fuel Range, pass in: GET /vehicles/:id/fuel \n" +
                "For Battery, pass in: GET /vehicles/:id/battery\n" +
                "To START or STOP the engine, pass in: POST /vehicles/:id/engine\n";
    }
    /**
     * retrieves GM data from sendVehiclePost and transforms the data so its structure
     *      follows the SmartCar API Specification.
     * @param id    vehicle id
     * @return      vehicle info in json following SmartCar API spec
     *
     */
    public static String vehicle(String id)  {
        try{
            String gmData = sendVehiclePost(id);

            Gson gson = new Gson();
            Vehicle veh = gson.fromJson(gmData, Vehicle.class);

            //construct SmartCarAPI specs of data
            JsonObject smVehicle = new JsonObject();
            smVehicle.addProperty("vin", veh.getVinVal());
            smVehicle.addProperty("color", veh.getColor());
            smVehicle.addProperty("doorCount", veh.getDoorCount());
            smVehicle.addProperty("driveTrain", veh.getDriveTrain());
            gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

            return gson.toJson(smVehicle);
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * retrieves GM data from sendSecurityPost and transforms the data so its structure
     *      follows the SmartCar API Specification.
     * @ param id   vehicle id
     * @return      security info (doors) in json following SmartCar API spec
     *
     */
    private static String security(String id) throws IOException {
        try {
            String gmData = sendSecurityPost(id);

            Gson gson = new Gson();
            Security s = gson.fromJson(gmData, Security.class);

            //construct SmartCarAPI specs of data  // GETTERDONE
            JsonArray smSecArray = new JsonArray();
            JsonObject smSecurity = new JsonObject();
            smSecurity.addProperty("location", s.getDoors().get(0).getLocation());
            smSecurity.addProperty("locked", s.getDoors().get(0).getLocked());
            smSecurity.addProperty("location", s.getDoors().get(1).getLocation());
            smSecurity.addProperty("locked", s.getDoors().get(1).getLocked());
            gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            System.out.println("yo "+ gson.toJson(smSecurity)); //DEBUG
            System.out.println(s.toString()); //DEBUG
            return gson.toJson(smSecurity);
        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * retrieves GM data from sendFuelBatPost and transforms the data so its structure
     *      follows the SmartCar API Specification.
     * @param id    vehicle id
     * @return      fuel range (percent fuel) in json following SmartCar API spec
     *
     */
    public static String fuel(String id)  {
        try {
            String gmData = sendFuelBatPost(id);
            Gson gson = new Gson();
            FuelBattery fb = gson.fromJson(gmData, FuelBattery.class);

            //construct SmartCarAPI specs of data
            JsonObject smFuel = new JsonObject();
            smFuel.addProperty("percent", fb.getTankLevel());
            gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            System.out.println("yo "+ gson.toJson(smFuel)); //DEBUG
            System.out.println(fb.toString()); //DEBUG
            return gson.toJson(smFuel);
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * retrieves GM data from sendFuelBatPost and transforms the data so its structure
     *      follows the SmartCar API Specification.
     * @param id vehicle id
     * @return   battery range (percent of battery remaining) in json following SmartCar API spec
     *
     */
    private static String battery(String id)  {
        try {
            String gmData = sendFuelBatPost(id);

            Gson gson = new Gson();
            FuelBattery fb = gson.fromJson(gmData, FuelBattery.class);

            //construct SmartCarAPI specs of data
            JsonObject smBattery = new JsonObject();
            smBattery.addProperty("percent", fb.getBatteryLevel());
            gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            System.out.println("yo "+ gson.toJson(smBattery)); //DEBUG
            System.out.println(fb.toString());//DEBUg
            return gson.toJson(smBattery);
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * sends post request to GM database to start or stop the vehicle engine using sendEnginePost().
     *      transforms the response (EXECUTED or FAILED) so its structure follows the SmartCar API Specification.
     * @param id        vehicle id
     * @param command   command ("START" or "STOP") to start or stop vehicle engine
     * @return          status ("success" or "error") in json following SmartCar API spec
     *
     */
    private static String engine(String id, String command) {	//need the COMMAND sent in {START,STOP}
        try {
            String gmData = sendEnginePost(id, command);

            Gson gson = new Gson();
            Engine e = gson.fromJson(gmData, Engine.class);

            //construct SmartCarAPI specs of data
            JsonObject smEngine = new JsonObject();
            smEngine.addProperty("status", e.getResultStatus());
            gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            System.out.println("yo "+ gson.toJson(smEngine));  //DEBUG
            System.out.println(e.toString());  //DEBUG
            return gson.toJson(smEngine);
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  sends Post request /getVehicleInfoService to Gm database for given vehicle id.
     * @param id    vehicle id
     * @return      vehicle Info in json following the GM specifications
     * @throws IOException
     */
    private static String sendVehiclePost(String id) throws IOException {
        URL url = new URL(GM_URL+ "getVehicleInfoService/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // Setting post request
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type","application/json");
        String postJsonData = "{\"id\":\""+id+"\",\"responseType\":\"JSON\"}";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postJsonData);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        System.out.println("nSending 'POST' request to URL : " + url);
        System.out.println("Post Data : " + postJsonData);
        System.out.println("Response Code : " + responseCode);

        //read response
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        //printing result from response
        System.out.println("Response is "+response.toString()); //DEBUG DO WE NEED THIS?
        return response.toString();
    }

    /**
     *  sends post request /getSecurityStatusService to Gm database for given vehicle id.
     * @param id    vehicle id
     * @return      security status of doors in json following the GM specifications
     * @throws IOException
     */
    private static String sendSecurityPost(String id) throws IOException {
        URL url = new URL(GM_URL+"getSecurityStatusService/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // Setting post request
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type","application/json");
        String postJsonData = "{\"id\":\""+id+"\",\"responseType\":\"JSON\"}";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postJsonData);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        System.out.println("nSending 'POST' request to URL : " + url);
        System.out.println("Post Data : " + postJsonData);
        System.out.println("Response Code : " + responseCode);

        //read response
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        System.out.println("Response is "+response.toString()); //DEBUG DO WE NEED THIS?
        return response.toString();
    }

    /**
     *  sends post request /getEnergyService to Gm database for given vehicle id.
     * @param id    vehicle id
     * @return      fuel and battery level in json following the GM specifications
     * @throws IOException
     */
    private static String sendFuelBatPost(String id) throws IOException {
        URL url = new URL(GM_URL+"getEnergyService/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // Setting basic post request
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type","application/json");
        String postJsonData = "{\"id\":\""+id+"\",\"responseType\":\"JSON\"}";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postJsonData);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        System.out.println("nSending 'POST' request to URL : " + url);
        System.out.println("Post Data : " + postJsonData);
        System.out.println("Response Code : " + responseCode);

        //read response
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        //printing result from response
        System.out.println("Response is "+response.toString()); //DEBUG DO WE NEED THIS?
        return response.toString();
    }

    /**
     *  sends post request /actionEngineService to Gm database to start or stop the vehicle.  The database
     *      returns "EXECUTED" or "FAILED" depending on whether the action occurred or not.
     * @param id        vehicle id
     * @param command   command "START" or "STOP"
     * @return          status of the action "EXECUTED" or "FAILED" in json following the GM specifications
     * @throws IOException
     */
    private static String sendEnginePost(String id, String command) throws IOException {
        URL url = new URL(GM_URL+"actionEngineService/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        command = "START";

        // Setting basic post request
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type","application/json");

        String postJsonData = null;
        if (command.equals("START")){
            postJsonData = "{\"id\":\""+id+"\",\"command\": \"START_VEHICLE\",\"responseType\":\"JSON\"}";
        } else if(command.equals("STOP")){
            postJsonData = "{\"id\":\""+id+"\",\"command\": \"STOP_VEHICLE\",\"responseType\":\"JSON\"}";
        } else{
            System.err.println("ERROR: Please pass engine command in. For example, POST /vehicles/1234/engine/START ");
            exit(1);
        }

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postJsonData);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        System.out.println("nSending 'POST' request to URL : " + url);
        System.out.println("Post Data : " + postJsonData);
        System.out.println("Response Code : " + responseCode);

        //read response
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        //printing result from response
        System.out.println("Response is "+response.toString()); //DEBUG DO WE NEED THIS?
        return response.toString();
    }

}







