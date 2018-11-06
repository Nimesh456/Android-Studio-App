package org.example.fyp;

import org.example.fyp.Model.MyPlaces;
import org.example.fyp.Model.Results;
import org.example.fyp.Remote.IGoogleAPIService;
import org.example.fyp.Remote.RetrofitClient;

/**
 * Created by nimesh on 19/02/2018.
 */

public class Common {

    public static Results currentResult;

    private static final String GOOGLE_API_URL = "https://maps.googleapis.com/";

    public static IGoogleAPIService getGoogleAPIServie()
    {
        return RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService.class);
    }
}
