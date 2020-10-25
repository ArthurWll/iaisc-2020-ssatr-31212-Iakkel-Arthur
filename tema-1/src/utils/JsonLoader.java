package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import simulator.PetriNet;

import java.io.FileReader;

public class JsonLoader {
    public boolean loadJson(String file){
        boolean loadStatus=true;
        PetriNet petriNet = PetriNet.getInstance();
        JSONParser parser = new JSONParser();

        try{
            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject jsonObjectPetri = (JSONObject) jsonObject.get("Petri");
            if(jsonObjectPetri != null){
                JSONArray jsonArray=(JSONArray) jsonObjectPetri.get("Locations");
                if(jsonArray == null || jsonArray.size() == 0){
                    throw new Exception ("JSON error => locations empty");
                } else {
                    for(int i=0; i<jsonArray.size();i++){
                        jsonObject=(JSONObject) jsonArray.get(i);
                        petriNet.addLocation(MappingHandler.jsonObjToLocation(jsonObject));
                    }
                }
                jsonArray = (JSONArray) jsonObjectPetri.get("Transitions");
                if(jsonArray == null || jsonArray.size() == 0){
                    throw new Exception("JSON error => transitions empty");
                } else{
                    for(int i=0;i<jsonArray.size();i++){
                        jsonObject = (JSONObject) jsonArray.get(i);
                        petriNet.addTransition(MappingHandler.jsonObjToTransition(jsonObject));
                    }
                }
            } else {
                throw new Exception ("JSON error => PetriNet empty");
            }
        } catch (Exception e){
            loadStatus = false;
            e.printStackTrace();
        }
        return loadStatus;
    }
}
