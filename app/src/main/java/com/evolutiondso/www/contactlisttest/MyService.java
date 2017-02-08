package com.evolutiondso.www.contactlisttest;

import com.evolutiondso.www.contactlisttest.Model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Albrtx on 06/02/2017.
 */

public interface MyService {

    @GET("technical-challenge/Contacts.json")
    Call<List<Result>> all();

}
