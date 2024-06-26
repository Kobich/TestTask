package com.example.testtask.APi

import com.example.testtask.APi.DataUserModel.DataModel
import retrofit.Call
import retrofit.http.GET

// This interface defines an API
// service for getting random jokes.
interface ApiService {
    // This annotation specifies that the HTTP method
    // is GET and the endpoint URL is "jokes/random".
    @GET("api/")
    // This method returns a Call object with a generic
    // type of DataModel, which represents
    // the data model for the response.
    fun getUserData(): Call<DataModel>

}