package org.example.fyp;

/**
 * Created by nimesh on 26/03/2018.
 */

public class Disease {
    public String symptoms;
    public String search_name;
    public String details;

    public Disease() {

    }

    public Disease(String symptoms, String search_name, String details) {
        this.symptoms = symptoms;
        this.search_name = search_name;
        this.details = details;
    }


    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDetails(){
        return details;
    }

    public void setDetails(String details){
        this.details = details;
    }

    public String getSearch_name() {
        return search_name;
    }

    public void setSearch_name(String search_name) {
        this.search_name = search_name;
    }
}
