package salmo.SalmoWebsite;

import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class WeatherJSONParser {


    private JSONObject json;
    private Map current;

    
    public WeatherJSONParser(String json ) throws Exception{
        JSONParser parser = new JSONParser();
        //Parsers the JSON
        this.json= (JSONObject) parser.parse(json);
        //Get all Objects within "current"
        this.current =(Map) this.json.get("current");
    }
    
    public String getTemp() {
        return "" +  current.get("temp");
    }
    
    
    public String getWeather() {
        //weather is an array nested within current 
        JSONArray weather  = (JSONArray) current.get("weather");
        Iterator itr = weather.iterator();
        //Can use an iterator to get elements of the array
        JSONObject  weatherDetails = ( JSONObject) itr.next();
        return "" + weatherDetails.get("description");
    }
    
    public String getIcon() {
        //weather is an array nested within current 
        JSONArray weather  = (JSONArray) current.get("weather");
        Iterator itr = weather.iterator();
        //Can use an iterator to get elements of the array
        JSONObject  weatherDetails = ( JSONObject) itr.next();
        return "" + weatherDetails.get("icon");
    }
    
    
    public static void main(String[] args) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
            "https://api.openweathermap.org/data/2.5/onecall?lat=43.0731&lon=-89.4012&\r\n" + 
            "exclude=hourly&units=imperial&appid="
            + "0a00760f62994b5d333193505902466f",
           String.class);
        WeatherJSONParser weather = new WeatherJSONParser(response.getBody().toString());
        System.out.println(weather.getIcon());
    }

}
