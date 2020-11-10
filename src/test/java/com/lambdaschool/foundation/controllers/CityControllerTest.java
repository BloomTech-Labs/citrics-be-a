//package com.lambdaschool.foundation.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.lambdaschool.foundation.models.City;
//import com.lambdaschool.foundation.models.CityIdName;
//import com.lambdaschool.foundation.repository.UserRepository;
//import com.lambdaschool.foundation.services.CityService;
//import com.lambdaschool.foundation.services.HelperFunctions;
//import com.lambdaschool.foundation.services.UserService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(CityController.class)
//class CityControllerTest
//{
//    /**
//     * MVC - MODEL VIEW CONTROLLER
//     * this is Spring's built in class to mock up a MVC
//     */
//    @Autowired
//    private MockMvc mockMvc;
//
//    /**
//     * Mocks up the cityservice
//     */
//    @MockBean
//    private CityService service;
//
//    /**
//     * Mocks up the Custom Error Details class
//     * Needed due to custom error messages turned on? Clairfy further with JM.
//     */
//    @MockBean
//    private HelperFunctions helperFunctions;
//
//    /**
//     * Mocks User Repository
//     * Needed due to JWT Authentication Config
//     */
//    @MockBean
//    private UserRepository userRepository;
//
//    /**
//     * Mocks User Service
//     * Needed due to JWT Authentication Config
//     */
//    @MockBean
//    private UserService userService;
//
//    /**
//     * Mocks JsonWebToken Decoder
//     * Needed due to JWT Authentication Config
//     */
//    @MockBean
//    private JwtDecoder jwtDecoder;
//
//    /**
//     * Lists used for testing
//     */
//    List<City> cities = new ArrayList<>();
//    List<CityIdName> cityIdNames = new ArrayList<>();
//
//    @BeforeEach
//    void setUp()
//    {
//        /**
//         * Loads list with 2 test cities.
//         */
//        City c1 = new City("Test City 1, USA");
//        City c2 = new City("Test City 2, USA");
//        CityIdName c3 = new CityIdName(1, "Test City 3, USA");
//        CityIdName c4 = new CityIdName(1, "Test City 4, USA");
//
//        cities.add(c1);
//        cities.add(c2);
//        cityIdNames.add(c3);
//        cityIdNames.add(c4);
//    }
//
//    @AfterEach
//    void tearDown()
//    {
//    }
//
//    @Test
//    void listAllCities() throws Exception
//    {
//        /**
//         * Instantiates a new mapper
//         * and maps the cities list to string
//         */
//        ObjectMapper mapper = new ObjectMapper();
//        String res = mapper.writeValueAsString(cities);
//
//        /**
//         * Tells Mockito to return the cities list when CityService.findAll() is called
//         */
//        Mockito.when(service.findAll()).thenReturn(cities);
//
//        /**
//         * Mocks network request using only the web layer (doesn't start server)
//         * and asserts the JSON returned == cities (mapped as string)
//         */
//        this.mockMvc.perform(get("/cities/all")).andDo(print()).andExpect(status().isOk())
//            .andExpect(content().json(res));
//    }
//
//    @Test
//    void getCityById() throws Exception
//    {
//        /**
//         * Maps object stored at cities[1] to string
//         */
//        ObjectMapper mapper = new ObjectMapper();
//        String res = mapper.writeValueAsString(cities.get(1));
//
//        /**
//         * Tells Mockito to return the object stored at index 1 of cites when UserServices.findCityById(1) is called
//         */
//        Mockito.when(service.findCityById(1)).thenReturn(cities.get(1));
//
//        /**
//         * Mocks network request and asserts the JSON returned == cities[1] (mapped as string)
//         */
//        this.mockMvc.perform(get("/cities/city/1")).andDo(print()).andExpect(status().isOk())
//            .andExpect(content().json(res));
//    }
//
//    @Test
//    void listAllCityIds() throws Exception
//    {
//        /**
//         * Maps list of City's names + id's to string
//         */
//        ObjectMapper mapper = new ObjectMapper();
//        String res = mapper.writeValueAsString(cityIdNames);
//
//        /**
//         * Tells Mockito to return the cityIdNames list when CityServices.findAllIds() is called
//         */
//        Mockito.when(service.findAllIds()).thenReturn(cityIdNames);
//
//        /**
//         * Mocks a GET network request asserts the JSON returned == cityIdNames (mapped as string)
//         */
//        this.mockMvc.perform(get("/cities/allid")).andDo(print()).andExpect(status().isOk())
//            .andExpect(content().json(res));
//
//    }
//
//    @Test
//    void getAverageCity() throws Exception
//    {
//        /**
//         * Maps City stored at index 0 of cities to string
//         */
//        ObjectMapper mapper = new ObjectMapper();
//        String res = mapper.writeValueAsString(cities.get(0));
//
//        /**
//         * Tells Mockito to return City at index 0 of cities when CityService.findAverageCity() is called
//         */
//        Mockito.when(service.findAverageCity()).thenReturn(cities.get(0));
//
//        /**
//         * Mocks a Get network request and asserts the JSON returned == cities[0]
//         */
//        this.mockMvc.perform(get("/cities/avg")).andDo(print()).andExpect(status().isOk())
//            .andExpect(content().json(res));
//    }
//}