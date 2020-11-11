//package com.lambdaschool.foundation.models;
//
///**
// * special class for city id + name schema
// */
//public class CityIdName
//{
//    /**
//     * city id, already established, pulled from existing data in DB
//     */
//    private long cityid;
//
//    /**
//     * city name, already established, pulled from existing data in DB
//     */
//    private String citynamestate;
//
//    /**
//     * Default constructor
//     */
//    public CityIdName()
//    {
//    }
//
//    /**
//     * Main constructor
//     * @param cityid id of city in local DB
//     * @param citynamestate name of city in local DB
//     */
//    public CityIdName(
//        long cityid,
//        String citynamestate)
//    {
//        this.cityid = cityid;
//        this.citynamestate = citynamestate;
//    }
//
//    /**
//     * Getter for cityid
//     * @return city id
//     */
//    public long getCityid()
//    {
//        return cityid;
//    }
//
//    /**
//     * Setter for cityid
//     * @param cityid city id
//     */
//    public void setCityid(long cityid)
//    {
//        this.cityid = cityid;
//    }
//
//    /**
//     * Getter for citynamestate
//     * @return Cityname + state
//     */
//    public String getCitynamestate()
//    {
//        return citynamestate;
//    }
//
//    /**
//     * Setter for citynamestate
//     * @param citynamestate CityName + state
//     */
//    public void setCitynamestate(String citynamestate)
//    {
//        this.citynamestate = citynamestate;
//    }
//
//    /**
//     * Override default toString method
//     * @return String representation of object
//     */
//    @Override
//    public String toString()
//    {
//        return "CityIdName{" +
//            "cityid=" + cityid +
//            ", citynamestate='" + citynamestate + '\'' +
//            '}';
//    }
//}
