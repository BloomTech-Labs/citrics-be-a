package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.config.H2ServerConfiguration;
import com.lambdaschool.foundation.models.City;
import com.lambdaschool.foundation.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class CityServiceImplTest
{
    /**
     * Connects CityService to test
     */
    @Autowired
    private CityService cityService;

    /**
     * Mocks up the CityRepository
     */
    @MockBean
    private CityRepository cityRepository;

    /**
     * Mocks up the CustomErrorDetails class
     * Needed because custom error messages on exceptions are turned on?
     * clarify further with JM
     */
    @MockBean
    private HelperFunctions helperFunctions;

    /**
     * Mocks up the H2ServerConfiguration class
     * Needed because this test file starts the server
     * before running test? clarify further with JM
     */
    @MockBean
    private H2ServerConfiguration h2ServerConfiguration;


    @Test
    void findAll() throws Exception
    {
        /**
         * Mock up repository
         * Dummy data to test
         */
        City c1 = new City("Test City 1, USA");
        City c2 = new City("Test City 2, USA");

        /**
         * Tells Mockito when the CityRepository calls its findAll() method
         * to return a list containing c1 + c2.
         * We mock the call through the repository instead of the service,
         * because findAll() isn't a service method, it is a passthrough method
         * which runs on the repository
         */
        Mockito.doReturn(Arrays.asList(c1, c2)).when(cityRepository).findAll();

        /**
         * Execute the service call
         */
        List<City> cities = cityService.findAll();

        /**
         * Assert expected result
         */
        assertEquals(2, cities.size(), "findAll() should return 2 test cities.");
    }

    @Test
    void findCityById() throws Exception
    {
        /**
         * Mock up repository
         * New dummy City to test
         */
        City test = new City("Test City 1, USA");

        /**
         * When Mockito calls the repositories method findById(1) it returns the test city.
         * doReturn() syntax requires an optional to be returned instead of the actual objecy
         * the Optional type is a way to explicitly say there may not be a value (fewer NullPointerExceptions)
         */
        Mockito.doReturn(Optional.of(test)).when(cityRepository).findById(1l);

        /**
         * Executes the service call
         */
        City c1 = cityService.findCityById(1);

        /**
         * Assert the expected results
         */
        assertEquals("Test City 1, USA", c1.getName());
    }

    @Test
    void save()
    {
        /**
         * Mock up repository
         * new dummy City to test
         */
        City c1 = new City("Test City 1, USA");

        /**
         * When Mockito calls CityRepository's save method it returns
         * the dummy c1 object. We can declare c1 in the doReturn() method
         * because the save method does not return an exception and always returns
         * a city object even if its empty? Check with JM for clarification
         */
        Mockito.doReturn(c1).when(cityRepository).save(any());

        /**
         * Executes service call
         * While the save method does interact with the repository
         * the business logic happens in CityServiceImp
         */
        City c2 = cityService.save(c1);

        /**
         * Assert the expected objects match
         */
        assertSame(c1, c2);
    }



    @Test
    void findByName() throws Exception
    {
        /**
         * Mock up repository
         * new dummy city to test
         */
        City c1 = new City("Test City 1, USA");

        /**
         * When Mockito calls CityRepository's findByCitynamestate() it returns
         * the dummy new c1 object. This one requires a city to be returned instead of an optional
         * because this is a custom repository method and may return null?
         * check with JM for clarification.
         */
        Mockito.doReturn(c1).when(cityRepository).findByName(any());

        /**
         * Executes the service call
         */
        City c2 = cityService.findByName("test");

        /**
         * Assert the expected results
         */
        assertSame(c1, c2);
    }


}