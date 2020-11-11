package com.lambdaschool.foundation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.foundation.models.City;
import com.lambdaschool.foundation.models.CityOccs;
import com.lambdaschool.foundation.models.UserCities;
import com.lambdaschool.foundation.repository.CityRepository;
import com.lambdaschool.foundation.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    CityService cityService;

    @Transactional
    @Override
    public void run(String[] args) throws Exception {
        String requestURL1 = "http://26-citrics-a-ds.eba-tjpigfip.us-east-1.elasticbeanstalk.com/rent_city_state";
        String requestURL2 = "http://26-citrics-a-ds.eba-tjpigfip.us-east-1.elasticbeanstalk.com/static/";
        String fetchLine = "";
        String fetchData = "";

        List<String> fetchArray = new ArrayList<String>();
        List<String> dataArray = new ArrayList<String>();

        URL locURL = new URL(requestURL1);


        HttpURLConnection connection = (HttpURLConnection)locURL.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int response = connection.getResponseCode();

        if(response == 200) {
            // Connection to endpoint is successful

            // I will try using BufferedReader's read() method instead and pre-parse data into an array
            fetchArray = readAndSort(locURL, fetchLine);

            // End of BufferedReader, check size
            System.out.println("Size of fetchArray: " + fetchArray.size());
        }

        for(String data : fetchArray) {
            // String example = "{\"city\":\"New York\",\"state\":\"NY\"}";

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(data);

            JsonNode cityNode = node.path("city");
            JsonNode stateNode = node.path("state");

            // Uncomment next line to display output of all cities and states
            // System.out.println("City: " + cityNode.textValue() + ", State: " + stateNode.textValue());

            String requestData = "";
            String place = cityNode.textValue();
            if(place.contains(" ")) {
                // parse and re-connect
                String[] words = place.split(" ");
                requestData = requestURL2 + words[0];

                for(int j = 1; j < words.length; j++) {
                    requestData += "%" + "20" + words[j];
                }

                requestData += "_" + stateNode.textValue();
            } else {
                requestData = requestURL2 + cityNode.textValue() + "_" + stateNode.textValue();
            }

            URL dataURL = new URL(requestData);

            HttpURLConnection cnct = (HttpURLConnection)dataURL.openConnection();
            cnct.setRequestMethod("GET");
            cnct.connect();
            int resp = cnct.getResponseCode();

            if(resp == 200) {
                dataArray = readAndSort(dataURL, fetchData);

                // End of BufferedReader, check size
                // System.out.println(cityNode.textValue() + ", " + stateNode.textValue() + " data: " + dataArray.size());
            } else if(resp == 404) {
                // System.out.println(cityNode.textValue() + ", " + stateNode.textValue() + " returned a 404");
            } else {
                // System.out.println(cityNode.textValue() + ", " + stateNode.textValue() + " returned an unknown error");
            }

            for(String data2 : dataArray) {
                ObjectMapper dataMap = new ObjectMapper();
                JsonNode dataNode = dataMap.readTree(data2);

                JsonNode cityName = dataNode.path("city");
                JsonNode stateCode = dataNode.path("state");
                JsonNode studioPrice = dataNode.path("studio");
                JsonNode oneBPrice = dataNode.path("onebr");
                JsonNode twoBPrice = dataNode.path("twobr");
                JsonNode threeBPrice = dataNode.path("threebr");
                JsonNode fourBPrice = dataNode.path("fourbr");
                JsonNode walkScore = dataNode.path("walkscore");
                JsonNode popTotal = dataNode.path("population");
                JsonNode occTitle = dataNode.path("occ_title");
                JsonNode hourlyWage = dataNode.path("hourly_wage");
                JsonNode annualWage = dataNode.path("annual_wage");
                JsonNode climateZone = dataNode.path("climate_zone");
                JsonNode simpleClimate = dataNode.path("simple_climate");
//                       public City(String name, String state, int studio, int onebr, int twobr, int threebr
//                       , int fourbr, String occ_title, Double hourly_wage, int annual_wage, String climate_zone, String simple_climate, float walkscore, int population,
//                       List<CityOccs> occupations, Set<UserCities> users) {

                 City newCity = new City(cityName.textValue(),stateCode.textValue(),studioNode.intValue(),oneBNode.intValue(),twoBNode.intValue(),threeBNode.intValue(),fourBNode.intValue(),occNode.textValue(),hourlyNode.doubleValue(),annualNode.intValue(),climateNode.textValue(),simpleClimate.textValue(),walkNode.floatValue(),popNode.intValue());
//                System.out.println(newCity.getName());
                 cityService.save(newCity);


                    // Insert code on how you'd want to store this data to table. For now, I will use println
                // Uncomment next line to display data for specified cities/states (Note: Apparently, not all cities in API have data)
                System.out.println(cityName.textValue() + ", " + stateCode.textValue() + ", Studio: " + studioPrice.intValue() + ", Walkability: " + walkScore.floatValue() + ", Population: " + popTotal.intValue());
            }
            dataArray.clear();
        }
    }

    public List<String> readAndSort(URL theURL, String fetched) {
        List<String> listArray = new ArrayList<String>();
        BufferedReader rdr = null;

        try {
            rdr = new BufferedReader(new InputStreamReader(theURL.openStream()));
            int num = 0;
            char ch;
            boolean isReading = false;

            while((num = rdr.read()) != -1) {
                ch = (char)num;

                if(ch == '{') isReading = true;

                if(isReading && ch != '\\') fetched += ch;

                if(ch == '}') {
                    isReading = false;
                    listArray.add(fetched);
                    fetched = "";
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rdr != null) rdr.close();
            } catch(IOException f) {
                f.printStackTrace();
            }
        }

        return listArray;
    }
}

