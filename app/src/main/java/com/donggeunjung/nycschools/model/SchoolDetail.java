package com.donggeunjung.nycschools.model;
/*
 * SchoolDetail.java : School detail data class
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
public class SchoolDetail {
    private String dbn;
    private String school_name;
    private String fax_number;
    private String city;
    private String state_code;
    private String zip;
    private String location;
    private String website;
    private String subway;
    private String bus;
    private String total_students;
    private String overview_paragraph;

    // Constructor
    public SchoolDetail(String dbn, String school_name, String location, String zip, String website, String state_code, String subway, String fax_number, String city, String total_students, String bus, String overview_paragraph) {
        this.dbn = dbn;
        this.school_name = school_name;
        this.location = location;
        this.zip = zip;
        this.website = website;
        this.state_code = state_code;
        this.subway = subway;
        this.fax_number = fax_number;
        this.city = city;
        this.total_students = total_students;
        this.bus = bus;
        this.overview_paragraph = overview_paragraph;
    }

    // Return DBN(Key of school)
    public String getDbn() {
        return dbn;
    }

    // Return school name
    public String getSchool_name() {
        return school_name;
    }

    // Return location
    public String getLocation() {
        return location;
    }

    // Return zip code
    public String getZip() {
        return zip;
    }

    // Return web site address
    public String getWebsite() {
        return website;
    }

    // Return State code
    public String getState_code() {
        return state_code;
    }

    // Return subway lists
    public String getSubway() {
        return subway;
    }

    // Return Fax number
    public String getFax_number() {
        return fax_number;
    }

    // Return city name
    public String getCity() {
        return city;
    }

    // Return total students count
    public String getTotal_students() {
        return total_students;
    }

    // Return bus lists
    public String getBus() {
        return bus;
    }

    // Return overview paragraph
    public String getOverview_paragraph() {
        return overview_paragraph;
    }
}
