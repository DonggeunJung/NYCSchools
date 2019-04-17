package com.donggeunjung.nycschools.model;
/*
 * SchoolScore.java : SAT score data class
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
public class SchoolScore {
    private String dbn;
    private String school_name;
    private String num_of_sat_test_takers;
    private String sat_critical_reading_avg_score;
    private String sat_math_avg_score;
    private String sat_writing_avg_score;

    // Constructor
    public SchoolScore(String dbn, String school_name, String num_of_sat_test_takers, String sat_critical_reading_avg_score, String sat_math_avg_score, String sat_writing_avg_score) {
        this.dbn = dbn;
        this.school_name = school_name;
        this.num_of_sat_test_takers = num_of_sat_test_takers;
        this.sat_critical_reading_avg_score = sat_critical_reading_avg_score;
        this.sat_math_avg_score = sat_math_avg_score;
        this.sat_writing_avg_score = sat_writing_avg_score;
    }

    // Return DBN(Key of school)
    public String getDbn() {
        return dbn;
    }

    // Return school name
    public String getSchool_name() {
        return school_name;
    }

    // Return the count of test takers
    public String getNum_of_sat_test_takers() {
        return num_of_sat_test_takers;
    }

    // Return reading average score
    public String getSat_critical_reading_avg_score() {
        return sat_critical_reading_avg_score;
    }

    // Return math average score
    public String getSat_math_avg_score() {
        return sat_math_avg_score;
    }

    // Return writing average score
    public String getSat_writing_avg_score() {
        return sat_writing_avg_score;
    }
}
