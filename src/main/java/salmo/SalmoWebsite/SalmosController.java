package salmo.SalmoWebsite;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@RestController

public class SalmosController {
    @Autowired // This
    private UserRepository userRepository;
    @Autowired
    private EventRepo er;


    @RequestMapping("/Salmo")
    public User greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        List<User> pleaseWork = (List<User>) userRepository.findAll();
        for (User u : pleaseWork) {
            System.out.println(u.getName());
        }
        String str = pleaseWork.get(0).getEmail();
        return pleaseWork.get(1);

    }


    @RequestMapping("/days")
    public String[] sendDaysOfWeek(@RequestParam(value = "days") String days) {
        Days day = new Days(); 
        return day.getDaysOfTheWeek();


    }

    @RequestMapping("/weather")
   String[] getWeather(@RequestParam(value = "weather") String weather) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
            "https://api.openweathermap.org/data/2.5/onecall?lat=43.0731&lon=-89.4012&\r\n" + 
                "exclude=hourly&units=imperial&appid="
                + "0a00760f62994b5d333193505902466f",
            String.class);
        WeatherJSONParser weather1 = 
            new WeatherJSONParser(response.getBody().toString());
        String[] today = {"" + weather1.getTemp(), "" + weather1.getWeather()};
        for(String s: today) {
            System.out.println();
        }

        return today;

    }


    @RequestMapping(method = RequestMethod.POST, value = "/Salmo")
    public void login(@RequestBody User user) {
        System.out.println("Users Name: " + user.getName());
        System.out.println("Users ID: " + user.getId());
        System.out.println("Users Email: " + user.getEmail());
        userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/SalmoEvent")
    public void addEvent(@RequestBody Event event) {
        System.out.println("Users Name: " + event.getEvent() + " " + event.getId());
        er.save(event);

    }

    @RequestMapping("/SalmoEvent")
    public Event getEvent(@RequestParam(value = "event") String name) {
        List<Event> pleaseWork = (List<Event>) er.findAll();
        for (Event u : pleaseWork) {
            System.out.println(u.getEvent());
        }

        return pleaseWork.get(2);

    }

}
