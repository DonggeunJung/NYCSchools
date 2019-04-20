package com.donggeunjung.nycschools.model;

import com.donggeunjung.nycschools.Util;
/*
 * SchoolSimple.java : School simple data class
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
public class SchoolSimple {
    protected String dbn;
    protected String school_name;
    protected String phone_number;
    protected String school_email;

    public SchoolSimple() { }

    // Constructor
    public SchoolSimple(String dbn, String school_name, String phone_number, String school_email) {
        this.dbn = dbn;
        this.school_name = school_name;
        this.phone_number = phone_number;
        this.school_email = school_email;
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

    // Search particular word in School name
    public boolean searchName(String strSearch) {
        return Util.isIncluded(school_name, strSearch);
    }
}
