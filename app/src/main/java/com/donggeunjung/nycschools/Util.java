package com.donggeunjung.nycschools;

/*
 * Util.java : Contains useful functions.
 *             All functions can be called unless making instance(object)
 * Author : DONGGEUN JUNG (Dennis)
 * Date : Apr.16.2019
 */
public class Util {

    // Search particular word in Text
    public static boolean isIncluded(String strText, String strSearch) {
        // When Text string does not exist return false
        if( strText == null || strText.length() < 1 )
            return false;

        // When search word is too short return true
        strSearch = strSearch.trim();
        if( strSearch.length() < 2 )
            return true;

        // Change both String to Lower case characters
        strSearch = strSearch.toLowerCase();
        strText = strText.toLowerCase();

        // When search word is included in Text string, return true
        if( strText.indexOf(strSearch) >= 0 )
            return true;

        return false;
    }
}
