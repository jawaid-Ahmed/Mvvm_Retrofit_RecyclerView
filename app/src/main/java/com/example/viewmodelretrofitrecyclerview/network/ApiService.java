package com.example.viewmodelretrofitrecyclerview.network;

import com.example.viewmodelretrofitrecyclerview.model.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/photos")
    Call<List<MovieModel>> getMovies();
}
