//package com.lambdaschool.foundation;
//
//import com.lambdaschool.foundation.models.*;
//import com.lambdaschool.foundation.services.CityService;
//import com.lambdaschool.foundation.services.UserService;
//import io.swagger.models.auth.In;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.client.HttpServerErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.*;
//
///**
// * SeedData puts both known and random data into the database. It implements CommandLineRunner.
// * <p>
// * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
// * after the application context has been loaded.
// */
//@Transactional
//@Component
//public class SeedData
//    implements CommandLineRunner
//{
//    /**
//     * COMMENTED OUT CODE IN THIS CLASS IS ONLY NEEDED WHEN INITIALLY SEEDING DB
//     * IF DS UPDATES DATA, NEW DATA WILL NEED TO BE PULLED IN
//     */
//    //    /**
////     * Connects the user service to this process
////     */
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    CityService cityService;
//
//    /**
//     * Generates test, seed data for our application
//     * First a set of known data is seeded into our database.
//     * Second a random set of data using Java Faker is seeded into our database.
//     * Note this process does not remove data from the database. So if data exists in the database
//     * prior to running this process, that data remains in the database.
//     *
//     * @param args The parameter is required by the parent interface but is not used in this process.
//     */
//    @Transactional
//    @Override
//    public void run(String[] args) throws
//                                   Exception
//    {
//        // URL of the API we are accessing
//        String requestURL = "http://citrics-ds.eba-jvvvymfn.us-east-1.elasticbeanstalk.com/";
//        //        String requestURL = "https://labs27-c-citrics-api.herokuapp.com/cities/all";
//        /*
//         * Creates the object that is needed to do a client side Rest API call.
//         * WE are the client getting data from a remote API.
//         */
//        RestTemplate restTemplate = new RestTemplate();
//
//        // telling our RestTemplate what format to expect, in this case Json
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//        restTemplate.getMessageConverters()
//            .add(converter);
//
//        // create the responseType expected. In this case DSCity is the type
//        ParameterizedTypeReference<DSCity> responseType = new ParameterizedTypeReference<>()
//        {
//        };
//
//        // Hashtables and all code after line 110 is uses to calculate the historical averages for each historical field
//        // For example will find and save the national average of all cities temperature in January and save it to the NA city
//        Hashtable<Integer, Double> popHash = new Hashtable<>();
//        Hashtable<Integer, List<Integer>> incHash = new Hashtable<>();
//        Hashtable<Integer, Hashtable<Integer, Integer>> houseHash = new Hashtable<>();
//        Hashtable<Integer, Hashtable<Integer, Integer>> covidHash = new Hashtable<>();
//        Hashtable<String, List<Double>> weatherHash = new Hashtable<>();
//
//        int cityCount = 0;
//
//        /**
//         * Loop to fetch cities from DS API
//         * ONLY NEEDED FOR INITIAL SEED
//         */
//        for (int i = 0; i < 12; i++)
//        {
//            System.out.println(i);
//            DSCity ourCityData;
//            try
//            {
//                // create responseEntity
//                ResponseEntity<DSCity> responseEntity = restTemplate.exchange(requestURL + i,
//                    HttpMethod.GET,
//                    null,
//                    responseType);
//
//                ourCityData = responseEntity.getBody();
//            } catch (HttpServerErrorException er)
//            {
//                continue;
//            }
//
//            cityCount++;
//            City c = cityService.saveDs(ourCityData);
//
//            List<PopulationHist> popList;
//            List<HistoricalIncome> incList;
//            List<HistoricalHousing> houList;
//            List<HistoricalCovid> covList;
//            List<HistoricalWeather> weaList;
//
//            popList = c.getPopulationhist();
//            incList = c.getHistoricalincome();
//            houList = c.getHistoricalaveragehouse();
//            covList = c.getCovid();
//            weaList = c.getHistoricalweather();
//
//            for (PopulationHist p: popList)
//            {
//                Double val = popHash.get(p.getYear());
//
//                if (val == null)
//                {
//                    val = 0.0;
//                }
//
//                popHash.put(p.getYear(), val += p.getPop());
////                System.out.println(popHash.get(p.getYear()));
//            }
//
//            for (HistoricalIncome in: incList)
//            {
//                List<Integer> val = incHash.get(in.getYear());
//
//                if (val == null)
//                {
//                    val = new ArrayList<>();
//                    val.add(0);
//                    val.add(0);
//                }
//                Integer one = val.get(0);
//                Integer two = val.get(1);
//
//                one += in.getIndividualincome();
//                two += in.getHouseholdincome();
//
//                val.set(0, one);
//                val.set(1, two);
//
//                incHash.put(in.getYear(), val);
//            }
//
//            for (HistoricalHousing hh: houList)
//            {
//                if (houseHash.get(hh.getYear()) == null)
//                {
//                    Hashtable<Integer, Integer> x = new Hashtable<>();
//                    houseHash.put(hh.getYear() , x);
//                }
//
//                if (houseHash.get(hh.getYear()).get(hh.getMonth()) == null)
//                {
//                    houseHash.get(hh.getYear()).put(hh.getMonth(), 0);
//                }
//
//                Integer val = houseHash.get(hh.getYear()).get(hh.getMonth());
//
//                val += hh.getHousingcost();
//
//                houseHash.get(hh.getYear()).put(hh.getMonth(), val);
//
////                System.out.println(houseHash.get(hh.getYear()).get(hh.getMonth()));
//            }
//
//            for (HistoricalCovid cc: covList)
//            {
//                if (covidHash.get(cc.getMonth()) == null)
//                {
//                    Hashtable<Integer, Integer> x = new Hashtable<>();
//                    covidHash.put(cc.getMonth(), x);
//                }
//
//                if (covidHash.get(cc.getMonth()).get(cc.getDay()) == null)
//                {
//                    covidHash.get(cc.getMonth()).put(cc.getDay(), 0);
//                }
//
//                Integer val = covidHash.get(cc.getMonth()).get(cc.getDay());
//
//                val += cc.getCases();
//
//                covidHash.get(cc.getMonth()).put(cc.getDay(), val);
//            }
//
//            for (HistoricalWeather ww: weaList)
//            {
//                List<Double> val = weatherHash.get(ww.getMonth());
//
//                if (val == null)
//                {
//                    val = new ArrayList<>();
//                    val.add(0.0);
//                    val.add(0.0);
//                }
//
//                Double one = val.get(0);
//                Double two = val.get(1);
//
//                one += ww.getPrecipitation();
//                two += ww.getTemperature();
//
//                val.set(0, one);
//                val.set(1,two);
//
//                weatherHash.put(ww.getMonth(), val);
//            }
//
//
//        }
//
//
//        City na = cityService.findAverageCity();
//
//        Set<Integer> popKeys = popHash.keySet();
//        Set<Integer> incKeys = incHash.keySet();
//        Set<Integer> houKeys = houseHash.keySet();
//        Set<Integer> covKeys = covidHash.keySet();
//        Set<String> weaKeys = weatherHash.keySet();
//
//
//        for (Integer key: popKeys)
//        {
//            Double val = popHash.get(key);
//            na.getPopulationhist().add(new PopulationHist(key, val / cityCount, na));
//        }
//
//        for (Integer key: incKeys)
//        {
//            int ind = incHash.get(key).get(0);
//            int hou = incHash.get(key).get(1);
//
//            na.getHistoricalincome().add(new HistoricalIncome(key, ind / cityCount, hou / cityCount, na));
//        }
//
//        for (Integer key: houKeys)
//        {
//            Hashtable x = houseHash.get(key);
//
//            Set<Integer> monKey = x.keySet();
//
//            for (Integer mkey: monKey)
//            {
//                int val = (int) x.get(mkey);
//                na.getHistoricalaveragehouse().add(new HistoricalHousing(key, mkey, val / cityCount, na));
//            }
//        }
//
//        for (Integer key: covKeys)
//        {
//            Hashtable x = covidHash.get(key);
//
//            Set<Integer> dkeys = x.keySet();
//
//            for (Integer dkey: dkeys)
//            {
//                int val = (int) x.get(dkey);
//
//                na.getCovid().add(new HistoricalCovid(2020, key, dkey, val / cityCount, na));
//            }
//        }
//
//        for (String key: weaKeys)
//        {
//            Double pre = weatherHash.get(key).get(0);
//            Double tem = weatherHash.get(key).get(1);
//
//            na.getHistoricalweather().add(new HistoricalWeather(key, pre / cityCount, tem / cityCount, na));
//        }
//
//
//        cityService.save(na);
//        System.out.println("Completed");
//    }
//}