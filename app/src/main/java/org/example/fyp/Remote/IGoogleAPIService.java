package org.example.fyp.Remote;

import org.example.fyp.Model.MyPlaces;
import org.example.fyp.Model.PlaceDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by nimesh on 19/02/2018.
 */

public interface IGoogleAPIService {
    @GET
    Call<MyPlaces> getNearByPlaces (@Url String url);

    @GET
    Call<PlaceDetail> getDetailPlace (@Url String url);
}
