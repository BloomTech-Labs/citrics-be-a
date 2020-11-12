package com.lambdaschool.foundation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.foundation.models.City;
import com.lambdaschool.foundation.models.CityOccs;
import com.lambdaschool.foundation.models.Occupation;
import com.lambdaschool.foundation.models.UserCities;
import com.lambdaschool.foundation.repository.CityRepository;
import com.lambdaschool.foundation.services.CityService;
import com.lambdaschool.foundation.services.OccupationService;
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

    @Autowired
    OccupationService occupationService;

    @Transactional
    @Override
    public void run(String[] args) throws Exception {
        // String requestURL1 = "http://26-citrics-a-ds.eba-tjpigfip.us-east-1.elasticbeanstalk.com/rent_city_state";
        String requestURL2 = "http://26-citrics-a-ds.eba-tjpigfip.us-east-1.elasticbeanstalk.com/static/";
        String requestURL3 = "http://26-citrics-a-ds.eba-tjpigfip.us-east-1.elasticbeanstalk.com/bls_jobs/";
        // String fetchLine = ""; // no longer needed. Used with requestURL1
        String fetchData = "";
        String fetchOccs = "";

        // List<String> fetchArray = new ArrayList<String>(); // no longer needed. Used with requestURL1
        List<String> dataArray = new ArrayList<String>();
        List<String> occsArray = new ArrayList<String>();
        List<City> cityArray = new ArrayList<City>();
        List<Occupation> jobsList = new ArrayList<Occupation>();

        // The integer below is just for output messaging
        int count = 0;

        // This SeedData had made a total of 889 API calls to get data for City and Occupation tables
        // The array below is a list of only the cities that actually returned results (133 of them)
        // I intend to use this to limit the number of API calls to 266 (one set for City, another for Occupation) to
        // make this SeedData run faster
        String[] citiesList = {"New%20York_NY", "Los%20Angeles_CA", "Chicago_IL", "Houston_TX", "Philadelphia_PA", "Phoenix_AZ", "San%20Antonio_TX", "San%20Diego_CA", "Dallas_TX", "San%20Jose_CA", "Indianapolis_IN", "Jacksonville_FL", "San%20Francisco_CA", "Austin_TX", "Charlotte_NC", "Detroit_MI", "EL%20Paso_TX", "Memphis_TN", "Baltimore_MD", "Boston_MA", "Washington_DC", "Denver_CO", "Milwaukee_WI", "Portland_OR", "Las%20Vegas_NV", "Oklahoma%20City_OK", "Albuquerque_NM", "Fresno_CA", "Sacramento_CA", "Kansas%20City_MO", "Virginia%20Beach_VA", "Atlanta_GA", "Colorado%20Springs_CO", "Omaha_NE", "Raleigh_NC", "Miami_FL", "Cleveland_OH", "Tulsa_OK", "Minneapolis_MN", "Wichita_KS", "Bakersfield_CA", "New%20Orleans_LA", "Tampa_FL", "Pittsburgh_PA", "Corpus%20Christi_TX", "Riverside_CA", "Cincinnati_OH", "Stockton_CA", "Toledo_OH", "Greensboro_NC", "Buffalo_NY", "Lincoln_NE", "Fort%20Wayne_IN", "Orlando_FL", "Laredo_TX", "Madison_WI", "Lubbock_TX", "Baton%20Rouge_LA", "Reno_NV", "Birmingham_AL", "Rochester_NY", "Spokane_WA", "Montgomery_AL", "Richmond_VA", "Des%20Moines_IA", "Fayetteville_NC", "Shreveport_LA", "Mobile_AL", "Amarillo_TX", "Grand%20Rapids_MI", "Salt%20Lake%20City_UT", "Worcester_MA", "Huntsville_AL", "Knoxville_TN", "Providence_RI", "Jackson_MS", "Chattanooga_TN", "Port%20St.%20Lucie_FL", "Eugene_OR", "Cape%20Coral_FL", "Salinas_CA", "Fort%20Collins_CO", "Dayton_OH", "Clarksville_TN", "New%20Haven_CT", "Columbia_SC", "Killeen_TX", "Topeka_KS", "Cedar%20Rapids_IA", "Waco_TX", "Abilene_TX", "Lansing_MI", "Ann%20Arbor_MI", "Manchester_NH", "Flint_MI", "Davenport_IA", "Las%20Cruces_NM", "Lakeland_FL", "Tyler_TX", "Lawton_OK", "College%20Station_TX", "Yuma_AZ", "Lawrence_KS", "Fort%20Smith_AR", "Trenton_NJ", "Allen_TX", "Kalamazoo_MI", "Muncie_IN", "Missoula_MT", "Warner%20Robins_GA", "Victoria_TX", "Santa%20Cruz_CA", "Cheyenne_WY", "Bowling%20Green_KY", "Ocala_FL", "Carson%20City_NV", "Valdosta_GA", "Corvallis_OR", "Grand%20Forks_ND", "Battle%20Creek_MI", "Manhattan_KS", "Saginaw_MI", "Harrisonburg_VA", "Olympia_WA", "Hattiesburg_MS", "Sierra%20Vista_AZ", "Charlottesville_VA", "Muskegon_MI", "Texarkana_TX", "Dover_DE", "Hinesville_GA", "Fairbanks_AK", "Naples_FL"};
        System.out.println("Parsing Data. Building Tables. Please wait...\n0%");

        for(String eachCity : citiesList) {
            // String example = "Colorado%20Springs_CO";

            String requestData = requestURL2 + eachCity;
            String requestOccs = requestURL3 + eachCity;

            URL dataURL = new URL(requestData);
            URL occsURL = new URL(requestOccs);

            HttpURLConnection cnct = (HttpURLConnection)dataURL.openConnection();
            cnct.setRequestMethod("GET");
            cnct.connect();
            int resp = cnct.getResponseCode();

            if(resp == 200) {
                dataArray = readAndSort(dataURL, fetchData);
                count++;
                if(count % 27 == 0) System.out.println((int)(count * 100 / 270) + "%");
            } else {
                // Page intentionally left blank
            }

            HttpURLConnection connOccs = (HttpURLConnection)occsURL.openConnection();
            connOccs.setRequestMethod("GET");
            connOccs.connect();
            int respOccs = connOccs.getResponseCode();

            if(respOccs == 200) {
                occsArray = readAndSort(occsURL, fetchOccs);
                count++;
                if(count % 27 == 0) System.out.println((int)(count * 100 / 270) + "%");
            } else {
                // Page intentionally left blank
            }

            for(int i = 0; i < dataArray.size(); i++) { // for(String data2 : dataArray)
                // This only loops through once, but leaving the for-loop just in case of errors

                ObjectMapper dataMap = new ObjectMapper();
                JsonNode dataNode = dataMap.readTree(dataArray.get(i));

                JsonNode cityName = dataNode.path("city");
                JsonNode stateCode = dataNode.path("state");
                JsonNode studioNode = dataNode.path("studio");
                JsonNode oneBNode = dataNode.path("onebr");
                JsonNode twoBNode = dataNode.path("twobr");
                JsonNode threeBNode = dataNode.path("threebr");
                JsonNode fourBNode = dataNode.path("fourbr");
                JsonNode walkNode = dataNode.path("walkscore");
                JsonNode popNode = dataNode.path("population");
                JsonNode occNode = dataNode.path("occ_title"); // duplicate?
                JsonNode hourlyNode = dataNode.path("hourly_wage"); // duplicate?
                JsonNode annualNode = dataNode.path("annual_wage"); // duplicate?
                JsonNode climateNode = dataNode.path("climate_zone");
                JsonNode simpleClimate = dataNode.path("simple_climate");

                City newCity = new City(cityName.textValue(),stateCode.textValue(),studioNode.intValue(),oneBNode.intValue(),twoBNode.intValue(),threeBNode.intValue(),fourBNode.intValue(),occNode.textValue(),hourlyNode.doubleValue(),annualNode.intValue(),climateNode.textValue(),simpleClimate.textValue(),walkNode.doubleValue(),popNode.intValue());

                for(int x = 0; x < occsArray.size(); x++) {
                    ObjectMapper occsMap = new ObjectMapper();
                    JsonNode occsNode = occsMap.readTree(occsArray.get(x));

                    JsonNode cityArea = occsNode.path("city"); // Not used
                    JsonNode stateArea = occsNode.path("state"); // Not used
                    JsonNode occTitle = occsNode.path("occ_title");
                    JsonNode jobsCapita = occsNode.path("jobs_1000");
                    JsonNode locQtnt = occsNode.path("loc_quotient");
                    JsonNode hourlyWage = occsNode.path("hourly_wage");
                    JsonNode annualWage = occsNode.path("annual_wage");

                    Occupation newOccupation = new Occupation(occTitle.textValue(), hourlyWage.doubleValue(), annualWage.intValue(), jobsCapita.doubleValue(), locQtnt.doubleValue());
                    occupationService.save(newOccupation);
                    newCity.getOccupations().add(new CityOccs(newCity, newOccupation));
                }
                cityService.save(newCity);
            }

            dataArray.clear();
            occsArray.clear();
        }
        System.out.println("Up and running!");
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

