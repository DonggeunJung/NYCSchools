package com.donggeunjung.nycschools.model;
/*
 * NycSchool.java : School simple data class
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
public class NycSchool {
    private String dbn;
    private String school_name;
    private String phone_number;
    private String school_email;

    // Constructor
    public NycSchool(String dbn, String school_name, String phone_number, String school_email) {
        this.dbn = dbn;
        this.school_name = school_name;
        this.phone_number = phone_number;
        this.school_email = school_email;
    }

    // Search particular word in School name
    public boolean searchName(String strSearch) {
        // When school name does not exist return false
        if( school_name == null || school_name.length() < 1 )
            return false;

        // When search word is too short return true
        strSearch = strSearch.trim();
        if( strSearch.length() < 2 )
            return true;

        // Change both String to Lower case characters
        strSearch = strSearch.toLowerCase();
        String strName = school_name.toLowerCase();

        // When search word is included in School name, return true
        if( strName.indexOf(strSearch) >= 0 )
            return true;

        return false;
    }

    // Return DBN(Key of school)
    public String getDbn() {
        return dbn;
    }

    // Return school name
    public String getSchool_name() {
        return school_name;
    }

    // Return phone number
    public String getPhone_number() {
        return phone_number;
    }

    // Return school eMail
    public String getSchool_email() {
        return school_email;
    }

}
